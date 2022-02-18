package com.schwa.schwacoffe;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

public class CartController {

    @FXML
    private Button BackButton;

    @FXML
    private Label CartLabel;

    @FXML
    private ListView<?> CartListView;

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

    @FXML
    void CancelOrderClicked(MouseEvent event) {
        System.out.println("Order Canceled");
    }

    @FXML
    void MenuButtonClicked(MouseEvent event) {
        System.out.println("Menu Button Clicked");
    }

    @FXML
    void PlaceOrderClicked(MouseEvent event) {
        System.out.println("Order Placed");
    }

}

