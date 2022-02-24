package com.schwa.schwacoffe.core.controllers;

import com.schwa.schwacoffe.core.data.CartManager;
import com.schwa.schwacoffe.models.CoffeeModel;
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

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void initialize() {
        ConfirmListView.setItems(CartManager.GetInstance().GetCartItems());
        ConfirmListView.setCellFactory(new CoffeeCellFactory());
    }

    @FXML
    void ExitButtonClicked(ActionEvent event) {
        Stage stage = (Stage) ExitButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void MenuButtonClicked(ActionEvent event) throws IOException {
        //switch scenes
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Menu.fxml"));
        root = loader.load();

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }
}

