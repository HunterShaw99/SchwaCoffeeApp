package com.schwa.schwacoffe;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class CustomizationController implements Initializable {
    @FXML
    private Button backButton, finishButton;
    @FXML
    private Label sizeLabel, milkLabel, flavorLabel, nameLabel,
                  sizePriceLabel, milkPriceLabel, flavorPriceLabel, totalPriceLabel,
                  sizeOption1PriceLabel, sizeOption2PriceLabel, sizeOption3PriceLabel,
                  milkOption1PriceLabel, milkOption2PriceLabel, milkOption3PriceLabel,
                  flavorOption1PriceLabel, flavorOption2PriceLabel, flavorOption3PriceLabel;
    @FXML
    private Rectangle TODO_IMAGEVIEW;
    @FXML
    private RadioButton sizeRadio1, sizeRadio2, sizeRadio3,
                        milkRadio1, milkRadio2, milkRadio3;
    @FXML
    private CheckBox flavorCheckBox1, flavorCheckBox2, flavorCheckBox3;

    private ToggleGroup sizeGroup, milkGroup;

    private CoffeeModel currentItem;
    private NumberFormat currencyFormatter;
    private double[] milkPrices, flavorPrices;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //Radio button config
        sizeGroup = new ToggleGroup();
        sizeRadio1.setToggleGroup(sizeGroup);
        sizeRadio2.setToggleGroup(sizeGroup);
        sizeRadio3.setToggleGroup(sizeGroup);

        milkGroup = new ToggleGroup();
        milkRadio1.setToggleGroup(milkGroup);
        milkRadio2.setToggleGroup(milkGroup);
        milkRadio3.setToggleGroup(milkGroup);

        //TODO: actually get the current item (either passed in from another page somehow, or retrieved from a singleton)

        //temporary code
        currentItem = new CoffeeModel();
        currentItem.setName("Coffee Name");
        double[] priceOptions = new double[] {
                1.0, 1.5, 2.0
        };
        currentItem.setPriceOptions(priceOptions);
        currentItem.setMilk("2%");
        currentItem.setSize("Large");
        currentItem.getFlavors().add("Vanilla");
        currentItem.getFlavors().add("Hazelnut");
        //</>

        //setting label text to associated current item's data
        currencyFormatter = NumberFormat.getCurrencyInstance();

        nameLabel.setText(currentItem.getName());
        double[] prices = currentItem.getPriceOptions();
        sizeOption1PriceLabel.setText(currencyFormatter.format(prices[0]));
        sizeOption2PriceLabel.setText(currencyFormatter.format(prices[1]));
        sizeOption3PriceLabel.setText(currencyFormatter.format(prices[2]));


        //these labels are always the same (prices of these things are consistent across all coffee types)
        milkPrices = new double[]{1,1,1};
        milkOption1PriceLabel.setText(currencyFormatter.format(milkPrices[0]));
        milkOption2PriceLabel.setText(currencyFormatter.format(milkPrices[1]));
        milkOption3PriceLabel.setText(currencyFormatter.format(milkPrices[2]));

        flavorPrices = new double[]{1,1,1};
        flavorOption1PriceLabel.setText(currencyFormatter.format(flavorPrices[0]));
        flavorOption2PriceLabel.setText(currencyFormatter.format(flavorPrices[1]));
        flavorOption3PriceLabel.setText(currencyFormatter.format(flavorPrices[2]));
        flavorPriceLabel.setText(currencyFormatter.format(0));


        //listeners for the radio button groups (to update price labels)
        sizeGroup.selectedToggleProperty().addListener((ob, o, n) -> {

            RadioButton rb = (RadioButton)sizeGroup.getSelectedToggle();
            SizeGroupChanged(rb);
        });
        milkGroup.selectedToggleProperty().addListener((ob, o, n) -> {

            RadioButton rb = (RadioButton)milkGroup.getSelectedToggle();
            MilkGroupChanged(rb);
        });

        //event handlers for the flavor check boxes
        AddEventHandlerForCheckbox(flavorCheckBox1);
        AddEventHandlerForCheckbox(flavorCheckBox2);
        AddEventHandlerForCheckbox(flavorCheckBox3);


        //setting default options
        //for size
        if (currentItem.getSize() == "" || currentItem.getSize().equals("Small")) {
            sizeRadio1.setSelected(true);
        } else if (currentItem.getSize().equals("Medium")) {
            sizeRadio2.setSelected(true);
        } else if (currentItem.getSize().equals("Large")) {
            sizeRadio3.setSelected(true);
        }
        //for milk
        if (currentItem.getMilk() == "" || currentItem.getMilk().equals("Whole")) {
            milkRadio1.setSelected(true);
        } else if (currentItem.getMilk().equals("2%")) {
            milkRadio2.setSelected(true);
        } else if (currentItem.getMilk().equals("Non-fat")) {
            milkRadio3.setSelected(true);
        }
        //for flavors
        double f;
        if (currentItem.getFlavors().contains("Vanilla")) {
            flavorCheckBox1.setSelected(true);
            f = flavorPrices[0];
        } else {
            flavorCheckBox1.setSelected(false);
            f = 0;
        }

        if (currentItem.getFlavors().contains("Caramel")) {
            flavorCheckBox2.setSelected(true);
            f += flavorPrices[1];
        } else {
            flavorCheckBox2.setSelected(false);
        }
        if (currentItem.getFlavors().contains("Hazelnut")) {
            flavorCheckBox3.setSelected(true);
            f += flavorPrices[2];
        } else {
            flavorCheckBox3.setSelected(false);
        }
        flavorPriceLabel.setText(currencyFormatter.format(f));

    }

    //Adds an event handler for a given checkbox
    private void AddEventHandlerForCheckbox(CheckBox cb) {
        Label label = flavorPriceLabel;
        EventHandler<ActionEvent> event = actionEvent -> {

            //temporary
            if (cb.isSelected()) {
                currentItem.getFlavors().add(cb.getText());
            } else {
                currentItem.getFlavors().remove(cb.getText());
            }
            label.setText(currencyFormatter.format(CalculateFlavorPrice()));

            double totalPrice = CalculateTotal();
            currentItem.setPrice(totalPrice);
        };
        cb.setOnAction(event);
    }

    //What to do when the size radio group gets changed
    private void SizeGroupChanged(RadioButton rb) {
        String choice = rb.getText();
        double price = switch (choice) {
            case "Small" -> currentItem.getPriceOptions()[0];
            case "Medium" -> currentItem.getPriceOptions()[1];
            case "Large" -> currentItem.getPriceOptions()[2];
            default ->
                    //method should never reach this statement
                    -10;
        };

        currentItem.setSize(choice);
        double totalPrice = CalculateTotal();
        currentItem.setPrice(totalPrice);

        sizePriceLabel.setText(currencyFormatter.format(price));
    }


    //What to do when the milk radio group gets changed
    private void MilkGroupChanged(RadioButton rb) {
        String choice = rb.getText();
        double price = switch (choice) {
            case "Whole" -> milkPrices[0];
            case "2%" -> milkPrices[1];
            case "Non-fat" -> milkPrices[2];
            default ->
                    //method should never reach this statement
                    -10;
        };

        currentItem.setMilk(choice);
        double totalPrice = CalculateTotal();
        currentItem.setPrice(totalPrice);
        currentItem.setMilk(choice);

        milkPriceLabel.setText(currencyFormatter.format(price));
    }

    //calculates the total price of the current item based on the current selections (also updates label)
    private double CalculateTotal() {
        double total = 0;

        //size
        total += switch (currentItem.getSize()) {
            case "Small" -> currentItem.getPriceOptions()[0];
            case "Medium" -> currentItem.getPriceOptions()[1];
            case "Large" -> currentItem.getPriceOptions()[2];
            default ->
                    //method should never reach this statement
                    -10;
        };

        //milk
        total += switch (currentItem.getMilk()) {
            case "Whole" -> milkPrices[0];
            case "2%" -> milkPrices[1];
            case "Non-fat" -> milkPrices[2];
            default ->
                    //method should never reach this statement
                    -10;
        };

        //flavors
        //TODO: (maybe change this) since all flavors cost exactly 1 right now, this works
        for (String flavor: currentItem.getFlavors()) {
            total += 1;
        }

        totalPriceLabel.setText(currencyFormatter.format(total));
        return total;
    }

    //TODO: (maybe change this) since all flavors cost exactly 1 right now, this works
    private double CalculateFlavorPrice() {
        double total = 0;

        for (String flavor : currentItem.getFlavors()) {
            total += 1;
        }
        return total;
    }

    //What to do when the finish button is clicked
    @FXML
    public void FinishButtonClicked(ActionEvent event) {

        //testing stuff
        System.out.println("Finish button was clicked.");

        System.out.println("currentItem.getName() = " + currentItem.getName());
        System.out.println("currentItem.getSize() = " + currentItem.getSize());
        System.out.println("currentItem.getMilk() = " + currentItem.getMilk());
        System.out.println("currentItem.getFlavors() = " + currentItem.getFlavors());
        System.out.println("currentItem.getPrice() = " + currentItem.getPrice());
        //</>

        //current item SHOULD be completely populated with the correct choices

        SwitchToCheckoutScreen();
    }

    //What to do when the back button is clicked
    @FXML
    public void BackButtonClicked(ActionEvent event) {
        System.out.println("Back button was clicked.");

        //TODO: [maybe] discard current item

        SwitchToMenuScreen();
    }

    //TODO: this
    public void SwitchToMenuScreen(){

    }

    //TODO: this
    public void SwitchToCheckoutScreen() {

    }

}
