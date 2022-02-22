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

        CoffeeModel coffee = new CoffeeModel();
        coffee.setPrice(BigDecimal.valueOf(6.25));
        coffee.setName("Coffee");
        coffee.setMilk("Whole");
        CoffeeModel coffee1 = new CoffeeModel();
        coffee1.setPrice(BigDecimal.valueOf(2.25));
        coffee1.setName("Lattee");
        coffee1.setMilk("non-fat");
        CoffeeModel coffee3 = new CoffeeModel();
        coffee3.setPrice(BigDecimal.valueOf(5.25));
        coffee3.setName("Iced Coffee");
        coffee3.setMilk("2%");
        CoffeeModel coffee4 = new CoffeeModel();
        coffee4.setPrice(BigDecimal.valueOf(4.25));
        coffee4.setName("Caramel Coffee");
        coffee4.setMilk("Whole");
        CartManager.GetInstance().AddBeverage(coffee);
        CartManager.GetInstance().AddBeverage(coffee1);
        CartManager.GetInstance().AddBeverage(coffee3);
        CartManager.GetInstance().AddBeverage(coffee4);
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

