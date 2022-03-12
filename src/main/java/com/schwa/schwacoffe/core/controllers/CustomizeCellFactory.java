package com.schwa.schwacoffe.core.controllers;

import com.schwa.schwacoffe.models.CoffeeModel;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

public class CustomizeCellFactory implements Callback<ListView<CoffeeModel>, ListCell<CoffeeModel>> {

    @Override
    public ListCell<CoffeeModel> call(ListView<CoffeeModel> param) {
        return new CustomizeCell();
    }


}

