package com.schwa.schwacoffe;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.scene.control.cell.ChoiceBoxListCell;

import java.io.IOException;

public class MenuController {

    public static final ObservableList hotDrinks = FXCollections.observableArrayList("Cappuccino", "Pistachio Latte", "Cinnamon Dolce Latte", "White Chocolate Mocha", "Caramel Macchiato");
    public static final ObservableList coldDrinks = FXCollections.observableArrayList("Salted Caramel Cream Cold Brew", "Iced Coffee", "Iced Espresso", "Iced Pistachio Latte", "Iced Caramel Macchiato");

    @FXML
    private ListView MenuListView;

    @FXML
    private Button CartButton;

    @FXML
    private RadioButton HotButton;

    @FXML
    private RadioButton ColdButton;

    @FXML
    private ToggleGroup TempButtons;

    @FXML
    public void initialize() {
        HotButton.setSelected(false);
        ColdButton.setSelected(false);
    }

    @FXML
    private void CartButtonPressed (ActionEvent event) throws IOException {

    }

    @FXML
    private void HotButtonPressed (ActionEvent event) {
        MenuListView.setItems(hotDrinks);
        MenuListView.setCellFactory(ChoiceBoxListCell.forListView(hotDrinks));
    }

    @FXML
    private void ColdButtonPressed (ActionEvent event) {
        MenuListView.setItems(coldDrinks);
        MenuListView.setCellFactory(ChoiceBoxListCell.forListView(coldDrinks));
    }
}
