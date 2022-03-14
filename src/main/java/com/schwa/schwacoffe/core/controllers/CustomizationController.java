package com.schwa.schwacoffe.core.controllers;

import com.schwa.schwacoffe.core.data.CartManager;
import com.schwa.schwacoffe.core.tasks.CartAdd;
import com.schwa.schwacoffe.models.CoffeeModel;
import com.schwa.schwacoffe.models.constants.Dairy;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;

public class CustomizationController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private ListView<CoffeeModel> customizeListView;

    @FXML
    private Button finishButton;

    @FXML
    private HBox headerBox;

    @FXML
    private Label totalPriceLabel;

    public void initialize() {
        customizeListView.setItems(CartManager.GetInstance().GetCurrentItemList());
        customizeListView.setCellFactory(new CustomizeCellFactory());
    }

    @FXML
    void AddToCart(MouseEvent event) throws IOException {
        Platform.runLater(new CartAdd());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Menu.fxml"));
        root = loader.load();

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }

}

