package com.schwa.schwacoffe.core.data;

import com.schwa.schwacoffe.CoffeeModel;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;

public class CoffeeCell extends ListCell<CoffeeModel> {
    @FXML
    private Label titleLabel;

    @FXML
    private Label priceLabel;

    @FXML
    private Label flavorLabel;
}
