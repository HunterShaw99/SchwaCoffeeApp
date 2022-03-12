package com.schwa.schwacoffe.core.controllers;

import com.schwa.schwacoffe.models.CoffeeModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class CustomizeCell extends ListCell<CoffeeModel> {

    @FXML
    private VBox base;

    @FXML
    private RadioButton _2percentLabel;

    @FXML
    private RadioButton almondFlavorLabel;

    @FXML
    private RadioButton almondLabel;

    @FXML
    private ImageView beverageImageView;

    @FXML
    private Label beverageNameLabel;

    @FXML
    private RadioButton caramelLabel;

    @FXML
    private RadioButton coconutLabel;

    @FXML
    private VBox dairyBox;

    @FXML
    private ToggleGroup dairyGroup;

    @FXML
    private Label dairyLabel;

    @FXML
    private VBox flavorBox;

    @FXML
    private Label flavorLabel;

    @FXML
    private RadioButton hazelnutLabel;

    @FXML
    private HBox headerHBox;

    @FXML
    private RadioButton largeLabel;

    @FXML
    private RadioButton mediumLabel;

    @FXML
    private RadioButton mochaLabel;

    @FXML
    private RadioButton noDairyLabel;

    @FXML
    private RadioButton nonFatLabel;

    @FXML
    private Separator pageBreak;

    @FXML
    private ToggleGroup sizeGroup;

    @FXML
    private HBox sizeHBox;

    @FXML
    private Label sizeLabel;

    @FXML
    private RadioButton smallLabel;

    @FXML
    private RadioButton soyLabel;

    @FXML
    private RadioButton vanillaLabel;

    @FXML
    private RadioButton wholeLabel;

    public void initialize() {

    }

    public CustomizeCell() {
        loadFXML();
    }

    private void loadFXML() {
        try {
            FXMLLoader loader = new FXMLLoader(CustomizeCell.class.getResource("CustomizeCell.fxml"));
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
            beverageNameLabel.setText(beverage.getName());
            beverageImageView.setImage(beverage.getImage());
            setGraphic(base);
        }
    }

}

