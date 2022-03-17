package com.schwa.schwacoffe.core.controllers;

import com.schwa.schwacoffe.core.data.CartManager;
import com.schwa.schwacoffe.core.tasks.CartRemove;
import com.schwa.schwacoffe.models.CoffeeModel;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
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
    private Label sizeLabel;

    @FXML
    private ImageView coffeeImage;

    private CoffeeModel m;

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
            m = beverage;
            nameLabel.setText(beverage.getName());
            milkLabel.setText(beverage.getMilkValue());
            flavorLabel.setText("Flavors: "+beverage.getFlavors());
            priceLabel.setText("$"+beverage.getPrice().toString());
            sizeLabel.setText(beverage.getSize().getVal());
            coffeeImage.setImage(new Image(beverage.getImage()));
            setGraphic(base);
        }
    }

    @FXML
    void RemoveBevEvent(MouseEvent event) {
        Platform.runLater(new CartRemove(m));
        m = null;
    }


}
