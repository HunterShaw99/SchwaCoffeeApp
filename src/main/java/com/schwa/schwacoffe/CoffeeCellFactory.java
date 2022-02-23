package com.schwa.schwacoffe;


import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

public class CoffeeCellFactory implements Callback<ListView<CoffeeModel>, ListCell<CoffeeModel>> {

        @Override
        public ListCell<CoffeeModel> call(ListView<CoffeeModel> param) {
            return new CoffeeCell();
        }


}
