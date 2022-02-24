package com.schwa.schwacoffe.core.controllers;

import com.schwa.schwacoffe.core.data.CartManager;
import com.schwa.schwacoffe.models.CoffeeModel;
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


    public void initialize() {
        ConfirmListView.setItems(CartManager.GetInstance().GetCartItems());
        ConfirmListView.setCellFactory(new CoffeeCellFactory());
    }
}

