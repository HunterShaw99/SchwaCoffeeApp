package com.schwa.schwacoffe.core.controllers;

import com.schwa.schwacoffe.core.data.CartManager;

import com.schwa.schwacoffe.models.CoffeeModel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.text.NumberFormat;
import java.util.ResourceBundle;

public class CustomizationController implements Initializable {
    @FXML
    private Button backButton, finishButton;
    @FXML
    private Label sizeLabel, milkLabel, flavorLabel, nameLabel, titleLabel,
                  sizePriceLabel, milkPriceLabel, flavorPriceLabel, totalPriceLabel,
                  sizeOption1PriceLabel, sizeOption2PriceLabel, sizeOption3PriceLabel,
                  milkOption1PriceLabel, milkOption2PriceLabel, milkOption3PriceLabel,
                  flavorOption1PriceLabel, flavorOption2PriceLabel, flavorOption3PriceLabel;
    @FXML
    private RadioButton sizeRadio1, sizeRadio2, sizeRadio3,
                        milkRadio1, milkRadio2, milkRadio3;
    @FXML
    private CheckBox flavorCheckBox1, flavorCheckBox2, flavorCheckBox3;

    @FXML
    private ImageView coffeeImageView;

    private ToggleGroup sizeGroup, milkGroup;

    private CoffeeModel currentItem;
    private NumberFormat currencyFormatter;
    private BigDecimal[] milkPrices, flavorPrices;
    private CartManager cartManager;

    private Stage stage;
    private Scene scene;
    private Parent root;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        cartManager = CartManager.GetInstance();

        //Radio button config
        sizeGroup = new ToggleGroup();
        sizeRadio1.setToggleGroup(sizeGroup);
        sizeRadio2.setToggleGroup(sizeGroup);
        sizeRadio3.setToggleGroup(sizeGroup);

        milkGroup = new ToggleGroup();
        milkRadio1.setToggleGroup(milkGroup);
        milkRadio2.setToggleGroup(milkGroup);
        milkRadio3.setToggleGroup(milkGroup);


        //get current item from cart manager
        currentItem = cartManager.GetCurrentItem();

        //set image
        coffeeImageView.setImage(currentItem.getImage());

        //setting label text to associated current item's data
        currencyFormatter = NumberFormat.getCurrencyInstance();

        nameLabel.setText(currentItem.getName());
        BigDecimal basePrice = currentItem.getBasePrice();
        sizeOption1PriceLabel.setText(currencyFormatter.format(basePrice));
        sizeOption2PriceLabel.setText(currencyFormatter.format(basePrice.add(BigDecimal.valueOf(1))));
        sizeOption3PriceLabel.setText(currencyFormatter.format(basePrice.add(BigDecimal.valueOf(2))));


        //these labels are always the same (prices of these things are consistent across all coffee types)
        milkPrices = new BigDecimal[]{BigDecimal.ONE, BigDecimal.ONE, BigDecimal.valueOf(1.25)};
        milkOption1PriceLabel.setText(currencyFormatter.format(milkPrices[0]));
        milkOption2PriceLabel.setText(currencyFormatter.format(milkPrices[1]));
        milkOption3PriceLabel.setText(currencyFormatter.format(milkPrices[2]));

        flavorPrices = new BigDecimal[]{BigDecimal.ONE, BigDecimal.ONE, BigDecimal.ONE};
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
        BigDecimal f;
        if (currentItem.getFlavors().contains("Vanilla")) {
            flavorCheckBox1.setSelected(true);
            f = flavorPrices[0];
        } else {
            flavorCheckBox1.setSelected(false);
            f = BigDecimal.ZERO;
        }

        if (currentItem.getFlavors().contains("Caramel")) {
            flavorCheckBox2.setSelected(true);
            f = f.add(flavorPrices[1]);
        } else {
            flavorCheckBox2.setSelected(false);
        }
        if (currentItem.getFlavors().contains("Hazelnut")) {
            flavorCheckBox3.setSelected(true);
            f = f.add(flavorPrices[2]);
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

            BigDecimal totalPrice = CalculateTotal();
            currentItem.setPrice(totalPrice);
        };
        cb.setOnAction(event);
    }

    //What to do when the size radio group gets changed
    private void SizeGroupChanged(RadioButton rb) {
        String choice = rb.getText();
        BigDecimal price = switch (choice) {
            case "Small" -> currentItem.getBasePrice();
            case "Medium" -> currentItem.getBasePrice().add(BigDecimal.valueOf(1));
            case "Large" -> currentItem.getBasePrice().add(BigDecimal.valueOf(2));
            default ->
                    //method should never reach this statement
                    BigDecimal.valueOf(-10);
        };

        currentItem.setSize(choice);
        BigDecimal totalPrice = CalculateTotal();
        currentItem.setPrice(totalPrice);

        sizePriceLabel.setText(currencyFormatter.format(price));
    }


    //What to do when the milk radio group gets changed
    private void MilkGroupChanged(RadioButton rb) {
        String choice = rb.getText();
        BigDecimal price = switch (choice) {
            case "Whole" -> milkPrices[0];
            case "2%" -> milkPrices[1];
            case "Non-fat" -> milkPrices[2];
            default ->
                    //method should never reach this statement
                    BigDecimal.valueOf(-10);
        };

        currentItem.setMilk(choice);
        BigDecimal totalPrice = CalculateTotal();
        currentItem.setPrice(totalPrice);
        currentItem.setMilk(choice);

        milkPriceLabel.setText(currencyFormatter.format(price));
    }

    //calculates the total price of the current item based on the current selections (also updates label)
    private BigDecimal CalculateTotal() {
        BigDecimal total = BigDecimal.ZERO;

        //size
        total = total.add(switch (currentItem.getSize()) {
            case "Small" -> currentItem.getBasePrice();
            case "Medium" -> currentItem.getBasePrice().add(BigDecimal.valueOf(1));
            case "Large" -> currentItem.getBasePrice().add(BigDecimal.valueOf(2));
            default ->
                    //method should never reach this statement
                    BigDecimal.valueOf(-10);
        });

        //milk
        total = total.add(switch (currentItem.getMilk()) {
            case "Whole" -> milkPrices[0];
            case "2%" -> milkPrices[1];
            case "Non-fat" -> milkPrices[2];
            default ->
                    //method should never reach this statement
                    BigDecimal.valueOf(-10);
        });

        //flavors
        //TODO: (maybe change this) since all flavors cost exactly 1 right now, this works
        for (String flavor: currentItem.getFlavors()) {
            total = total.add(BigDecimal.ONE);
        }

        totalPriceLabel.setText(currencyFormatter.format(total));
        return total;
    }

    //TODO: (maybe change this) since all flavors cost exactly 1 right now, this works
    private BigDecimal CalculateFlavorPrice() {
        BigDecimal total = BigDecimal.ZERO;

        for (String flavor : currentItem.getFlavors()) {
            total = total.add(BigDecimal.ONE);
        }
        return total;
    }

    //What to do when the finish button is clicked
    @FXML
    public void FinishButtonClicked(ActionEvent event) throws IOException {

        //testing stuff
        System.out.println("Finish button was clicked.");

        System.out.println("currentItem.getName() = " + currentItem.getName());
        System.out.println("currentItem.getSize() = " + currentItem.getSize());
        System.out.println("currentItem.getMilk() = " + currentItem.getMilk());
        System.out.println("currentItem.getFlavors() = " + currentItem.getFlavors());
        System.out.println("currentItem.getPrice() = " + currentItem.getPrice());
        //</>

        //current item SHOULD be completely populated with the correct choices

        //add customized item to cartManager
        cartManager.AddBeverage(currentItem);

        SwitchToCheckoutScreen(event);
    }

    //What to do when the back button is clicked
    @FXML
    public void BackButtonClicked(ActionEvent event) throws IOException {
        System.out.println("Back button was clicked.");

        //TODO: [maybe] discard current item

        SwitchToMenuScreen(event);
    }

    public void SwitchToMenuScreen(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Menu.fxml"));
        root = loader.load();

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }

    public void SwitchToCheckoutScreen(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("cart-view.fxml"));
        root = loader.load();

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }

}
