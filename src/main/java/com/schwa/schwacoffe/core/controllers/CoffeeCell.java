package com.schwa.schwacoffe.core.controllers;

import com.schwa.schwacoffe.models.CoffeeModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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

    @FXML
    private ImageView coffeeImage;

    public void initialize() {
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
            milkLabel.setText(beverage.getMilkValue());
            flavorLabel.setText("Flavors: "+beverage.getFlavors().replace("[", "").replace("]", ""));
            priceLabel.setText("$"+beverage.getPrice().toString());
            coffeeImage.setImage(new Image(beverage.getImage()));
            setGraphic(base);
        }
    }


}
