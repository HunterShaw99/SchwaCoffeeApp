package com.schwa.schwacoffe;

import com.schwa.schwacoffe.core.data.CartManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class CoffeeCell extends ListCell<CoffeeModel> {
    @FXML
    private AnchorPane base;

    @FXML
    private Label nameLabel;

    @FXML
    private Label priceLabel;

    @FXML
    private Label flavorLabel;

    @FXML
    private Label milkLabel;

    public void initialize() {
        nameLabel.setText("askjdlfh");
    }

    public CoffeeCell() {
        loadFXML();
    }

    private void loadFXML() {
        try {
            FXMLLoader loader = new FXMLLoader(CoffeeCell.class.getResource("CoffeeCell.fxml"));
            loader.setController(this);
            loader.load();
        }

        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void updateItem(CoffeeModel beverage, boolean empty) {
        super.updateItem(beverage, empty);

        if (empty || beverage == null) {
            setText(null);
            setGraphic(null);
        } else {
            nameLabel.setText(beverage.getName());
            milkLabel.setText(beverage.getMilk());
            flavorLabel.setText(beverage.getFlavors().toString());
            priceLabel.setText("$"+beverage.getPrice().toString());

            setGraphic(base);
        }
    }


}
