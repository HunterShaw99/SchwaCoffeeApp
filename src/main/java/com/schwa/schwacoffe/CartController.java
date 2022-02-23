package com.schwa.schwacoffe;

import com.schwa.schwacoffe.core.data.CartManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

public class CartController {

    @FXML
    private Button BackButton;

    @FXML
    private Label CartLabel;

    @FXML
    private ListView<CoffeeModel> CartListView;

    @FXML
    private Label CartTitleLabel;

    @FXML
    private Label CartTotalLabel;

    @FXML
    private HBox HeaderBar;

    @FXML
    private Label PickUpLabel;

    @FXML
    private Slider PickupSlider;

    @FXML
    private Button PlaceOrderButton;

    @FXML
    private Button PlaceOrderButton1;

    private CartManager cartManager;

    private Stage stage;
    private Scene scene;
    private Parent root;


    public void initialize() {
        cartManager = CartManager.GetInstance();
        CartListView.setItems(cartManager.GetCartItems());
        CartListView.setCellFactory(new CoffeeCellFactory());
    }

    @FXML
    void CancelOrderClicked(MouseEvent event) {
        System.out.println("Order Canceled");
    }

    @FXML
    void MenuButtonClicked(MouseEvent event) throws IOException {
        System.out.println("Menu Button Clicked");
        for (Object c : CartListView.getItems().toArray()) {
            System.out.println(c.toString());
        }

        //switch scenes
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Menu.fxml"));
        root = loader.load();

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void PlaceOrderClicked(MouseEvent event) {
        String[] flavors = new String[] {"Caramel", "Vanilla"};
        CoffeeModel e = new CoffeeModel();
        e.setName("Coffee");
        e.setMilk("Whole");
        e.setPrice(BigDecimal.valueOf(5.25));
        e.setFlavors(List.of(flavors));
        cartManager.AddBeverage(e);
        System.out.println("Order Placed");
    }

}

