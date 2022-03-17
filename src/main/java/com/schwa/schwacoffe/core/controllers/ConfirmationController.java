package com.schwa.schwacoffe.core.controllers;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.AmazonSQSException;
import com.amazonaws.services.sqs.model.CreateQueueResult;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.schwa.schwacoffe.core.data.CartManager;
import com.schwa.schwacoffe.core.tasks.ClearCart;
import com.schwa.schwacoffe.models.CoffeeModel;
import com.schwa.schwacoffe.models.OrderModel;
import com.schwa.schwacoffe.models.constants.OrderStatus;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import java.io.IOException;
import java.math.BigDecimal;


import static com.amazonaws.regions.Regions.US_EAST_1;

public class ConfirmationController {

    @FXML
    private Label AppLabel;

    @FXML
    private ListView<CoffeeModel> ConfirmListView;

    @FXML
    private HBox HeaderBar;

    @FXML
    private Button ExitButton;

    @FXML
    private Button MenuButton;

    private final String qURL = "https://sqs.us-east-1.amazonaws.com/261944900994/schwa-coffee.fifo";
    private final String qNAME = "schwa-coffee.fifo";

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void initialize() throws JsonProcessingException {
        ConfirmListView.setItems(CartManager.GetInstance().GetCartItems());
        ConfirmListView.setCellFactory(new CoffeeCellFactory());
        SendOrder();
    }

    @FXML
    void ExitButtonClicked(ActionEvent event) {
        Stage stage = (Stage) ExitButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void MenuButtonClicked(ActionEvent event) throws IOException {
        Platform.runLater(new ClearCart());
        //switch scenes
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Menu.fxml"));
        root = loader.load();

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }

    private void SendOrder() throws JsonProcessingException {
        BigDecimal orderTotal = CartManager.GetInstance().GetCartTotal();
        OrderModel order = new OrderModel(orderTotal, CartManager.GetInstance().GetCartItems(), OrderStatus.PROCESSING);
        AmazonSQS sqs = AmazonSQSClientBuilder.standard()
                .withRegion(US_EAST_1)
                .build();

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        String orderJSON = objectMapper.writeValueAsString(order);
        SendMessageRequest send_msg_request = new SendMessageRequest()
                .withQueueUrl(qURL)
                .withMessageBody(orderJSON)
                .withDelaySeconds(0);
        send_msg_request.setMessageGroupId("coffee");
        send_msg_request.setMessageDeduplicationId(order.orderID);
        sqs.sendMessage(send_msg_request);
    }
}

