package com.schwa.schwacoffe;

import com.schwa.schwacoffe.core.data.CartManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

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


    public void initialize() {
        CartListView.setItems(CartManager.GetCartItems());
        CartListView.setCellFactory(new CoffeeCellFactory());
    }

    @FXML
    void CancelOrderClicked(MouseEvent event) {
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
        String[] flavors = new String[] {"Caramel", "Vanilla"};
        CoffeeModel e = new CoffeeModel();
        e.setName("Coffee");
        e.setMilk("Whole");
        e.setPrice(BigDecimal.valueOf(5.25));
        e.setFlavors(List.of(flavors));
        CartManager.AddBeverage(e);
        System.out.println("Order Placed");
    }

}

