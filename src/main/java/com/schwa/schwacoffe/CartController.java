package com.schwa.schwacoffe;

import com.schwa.schwacoffe.core.data.CartManager;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

import java.math.BigDecimal;

public class CartController {

    @FXML
    private Button BackButton;

    @FXML
    private Label CartLabel;

    @FXML
    private ListView<String> CartListView;

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


    public void initialize() {
        CartListView.setItems(CartManager.GetCartItems());
    }

    @FXML
    void CancelOrderClicked(MouseEvent event) {
        CoffeeModel coffee = new CoffeeModel();
        coffee.setPrice(BigDecimal.valueOf(6.25));
        CartManager.GetInstance().AddBeverage(coffee);
        System.out.println("Order Canceled");
    }

    @FXML
    void MenuButtonClicked(MouseEvent event) {
        System.out.println("Menu Button Clicked");
        for (Object c : CartListView.getItems().toArray()) {
            System.out.println(c.toString());
        }
    }

    @FXML
    void PlaceOrderClicked(MouseEvent event) {
        System.out.println("Order Placed");
    }

}

