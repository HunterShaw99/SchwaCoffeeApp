package com.schwa.schwacoffe.core.controllers;

import com.schwa.schwacoffe.core.data.CartManager;
import com.schwa.schwacoffe.models.CoffeeModel;
import com.schwa.schwacoffe.models.constants.CoffeePrice;
import com.schwa.schwacoffe.models.constants.Dairy;
import com.schwa.schwacoffe.models.constants.Flavor;
import com.schwa.schwacoffe.models.constants.Size;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.text.NumberFormat;

public class CustomizeCell extends ListCell<CoffeeModel> {

    @FXML
    private RadioButton _2percentLabel, almondLabel, largeLabel,
            mediumLabel, noDairyLabel, nonFatLabel, smallLabel, soyLabel, wholeLabel;
    @FXML
    private CheckBox caramelCheckBox, mochaCheckBox, vanillaCheckBox,
            pumpkinCheckBox, hazelnutCheckBox, coconutCheckBox;
    @FXML
    private VBox base, flavorBox;
    @FXML
    private ImageView beverageImageView;
    @FXML
    private Label beverageNameLabel, dairyLabel, flavorLabel, largePriceLabel,
            mediumPriceLabel, sizeLabel, smallPriceLabel, diaryNoteLabel;
    @FXML
    private VBox dairyBox;
    @FXML
    private ToggleGroup dairyGroup, sizeGroup;
    @FXML
    private HBox headerHBox, sizeHBox;
    @FXML
    private Separator pageBreak;

    public void initialize() {
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        smallPriceLabel.setText(formatter.format(CoffeePrice.SMALL_COST));
        mediumPriceLabel.setText(formatter.format(CoffeePrice.MEDIUM_COST));
        largePriceLabel.setText(formatter.format(CoffeePrice.LARGE_COST));
        diaryNoteLabel.setText("Non-dairy options are available at additional cost of "+formatter.format(CoffeePrice.NON_DAIRY_COST));
        AddEventHandlerForCheckbox(caramelCheckBox);
        AddEventHandlerForCheckbox(mochaCheckBox);
        AddEventHandlerForCheckbox(vanillaCheckBox);
        AddEventHandlerForCheckbox(pumpkinCheckBox);
        AddEventHandlerForCheckbox(hazelnutCheckBox);
        AddEventHandlerForCheckbox(coconutCheckBox);
        setDefaultDiary();
        setDefaultSize();
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

    private void setDefaultDiary() {
        noDairyLabel.setSelected(true);
        CartManager.GetInstance().GetCurrentItem().setMilk(Dairy.NONE);
    }

    private void setDefaultSize() {
        mediumLabel.setSelected(true);
        CartManager.GetInstance().GetCurrentItem().setSize(Size.MEDIUM);
        //Somewhere in code, I already set default price to medium. I am too tired to check where ATM.
        //CartManager.GetInstance().GetCurrentItem().setPrice(CartManager.GetInstance().GetCurrentItem().getPrice().add(CoffeePrice.MEDIUM_COST));
    }

    @FXML
    void setSizeEvent(MouseEvent event) {
        RadioButton r = (RadioButton)event.getSource();
        CartManager.GetInstance().GetCurrentItem().setPrice(CartManager.GetInstance().GetCurrentItem().getPrice().subtract(CoffeePrice.MEDIUM_COST));
        switch (r.getId()) {
            case "smallLabel":
                CartManager.GetInstance().GetCurrentItem().setSize(Size.SMALL);
                CartManager.GetInstance().GetCurrentItem().setPrice(CartManager.GetInstance().GetCurrentItem().getPrice().add(CoffeePrice.SMALL_COST));
                break;
            case "mediumLabel":
                CartManager.GetInstance().GetCurrentItem().setSize(Size.MEDIUM);
                CartManager.GetInstance().GetCurrentItem().setPrice(CartManager.GetInstance().GetCurrentItem().getPrice().add(CoffeePrice.MEDIUM_COST));
                break;
            case "largeLabel":
                CartManager.GetInstance().GetCurrentItem().setSize(Size.LARGE);
                CartManager.GetInstance().GetCurrentItem().setPrice(CartManager.GetInstance().GetCurrentItem().getPrice().add(CoffeePrice.LARGE_COST));
                break;
        }
    }

    @FXML
    void setDiaryEvent(MouseEvent event) {
        RadioButton r = (RadioButton)event.getSource();
        switch (r.getId()) {
            case "noDairyLabel":
                if (CartManager.GetInstance().GetCurrentItem().getMilk() != Dairy.SOY || CartManager.GetInstance().GetCurrentItem().getMilk() != Dairy.ALMOND) {
                    CartManager.GetInstance().GetCurrentItem().setPrice(CartManager.GetInstance().GetCurrentItem().getPrice().subtract(CoffeePrice.DAIRY_COST));
                } else {
                    CartManager.GetInstance().GetCurrentItem().setPrice(CartManager.GetInstance().GetCurrentItem().getPrice().subtract(CoffeePrice.NON_DAIRY_COST));
                }
                CartManager.GetInstance().GetCurrentItem().setMilk(Dairy.NONE);
                break;
            case "wholeLabel":
                CartManager.GetInstance().GetCurrentItem().setMilk(Dairy.WHOLE);
                CartManager.GetInstance().GetCurrentItem().setPrice(CartManager.GetInstance().GetCurrentItem().getPrice().add(CoffeePrice.DAIRY_COST));
                break;
            case "_2percentLabel":
                CartManager.GetInstance().GetCurrentItem().setMilk(Dairy._2PERCENT);
                CartManager.GetInstance().GetCurrentItem().setPrice(CartManager.GetInstance().GetCurrentItem().getPrice().add(CoffeePrice.DAIRY_COST));
                break;
            case "nonFatLabel":
                CartManager.GetInstance().GetCurrentItem().setMilk(Dairy.SKIM);
                CartManager.GetInstance().GetCurrentItem().setPrice(CartManager.GetInstance().GetCurrentItem().getPrice().add(CoffeePrice.DAIRY_COST));
                break;
            case "soyLabel":
                CartManager.GetInstance().GetCurrentItem().setMilk(Dairy.SOY);
                CartManager.GetInstance().GetCurrentItem().setPrice(CartManager.GetInstance().GetCurrentItem().getPrice().add(CoffeePrice.NON_DAIRY_COST));
                break;
            case "almondLabel":
                CartManager.GetInstance().GetCurrentItem().setMilk(Dairy.ALMOND);
                CartManager.GetInstance().GetCurrentItem().setPrice(CartManager.GetInstance().GetCurrentItem().getPrice().add(CoffeePrice.NON_DAIRY_COST));
                break;
        }
    }


    private void AddEventHandlerForCheckbox(CheckBox cb) {
        EventHandler<ActionEvent> event = actionEvent -> {
            //temporary
            if (cb.isSelected()) {
                switch (cb.getId()) {
                    case "caramelCheckBox":
                        CartManager.GetInstance().GetCurrentItem().addFlavor(Flavor.CARAMEL);
                        break;
                    case "mochaCheckBox":
                        CartManager.GetInstance().GetCurrentItem().addFlavor(Flavor.MOCHA);
                        break;
                    case "vanillaCheckBox":
                        CartManager.GetInstance().GetCurrentItem().addFlavor(Flavor.VANILLA);
                        break;
                    case "pumpkinCheckBox":
                        CartManager.GetInstance().GetCurrentItem().addFlavor(Flavor.PUMPKIN_SPICE);
                        break;
                    case "hazelnutCheckBox":
                        CartManager.GetInstance().GetCurrentItem().addFlavor(Flavor.HAZELNUT);
                        break;
                    case "coconutCheckBox":
                        CartManager.GetInstance().GetCurrentItem().addFlavor(Flavor.COCONUT);
                        break;
                }
                CartManager.GetInstance().GetCurrentItem().setPrice(CartManager.GetInstance().GetCurrentItem().getPrice().add(CoffeePrice.FLAVOR_COST));
            } else {
                switch (cb.getId()) {
                    case "caramelCheckBox":
                        CartManager.GetInstance().GetCurrentItem().removeFlavor(Flavor.CARAMEL);
                        break;
                    case "mochaCheckBox":
                        CartManager.GetInstance().GetCurrentItem().removeFlavor(Flavor.MOCHA);
                        break;
                    case "vanillaCheckBox":
                        CartManager.GetInstance().GetCurrentItem().removeFlavor(Flavor.VANILLA);
                        break;
                    case "pumpkinCheckBox":
                        CartManager.GetInstance().GetCurrentItem().removeFlavor(Flavor.PUMPKIN_SPICE);
                        break;
                    case "hazelnutCheckBox":
                        CartManager.GetInstance().GetCurrentItem().removeFlavor(Flavor.HAZELNUT);
                        break;
                    case "coconutCheckBox":
                        CartManager.GetInstance().GetCurrentItem().removeFlavor(Flavor.COCONUT);
                        break;
                }
                CartManager.GetInstance().GetCurrentItem().setPrice(CartManager.GetInstance().GetCurrentItem().getPrice().subtract(CoffeePrice.FLAVOR_COST));
            }
        };
        cb.setOnAction(event);
    }

}

