package com.schwa.schwacoffe;

import com.schwa.schwacoffe.core.data.CartManager;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;

public class ConfirmationController {

    @FXML
    private Label AppLabel;

    @FXML
    private ListView<CoffeeModel> ConfirmListView;

    @FXML
    private HBox HeaderBar;

    private CartManager cartManager;

    public void initialize() {
        cartManager = CartManager.GetInstance();
        ConfirmListView.setItems(cartManager.GetCartItems());
        ConfirmListView.setCellFactory(new CoffeeCellFactory());
    }
}

