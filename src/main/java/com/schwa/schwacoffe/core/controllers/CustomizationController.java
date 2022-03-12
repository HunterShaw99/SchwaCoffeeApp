package com.schwa.schwacoffe.core.controllers;

import com.schwa.schwacoffe.core.data.CartManager;
import com.schwa.schwacoffe.models.CoffeeModel;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

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

}

