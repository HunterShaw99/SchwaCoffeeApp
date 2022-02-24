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
    private NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance();
    private BigDecimal[] flavorPrices;
    private BigDecimal[] milkPrices = new BigDecimal[]{BigDecimal.ONE, BigDecimal.ONE, BigDecimal.valueOf(1.25)};

    private Stage stage;
    private Scene scene;
    private Parent root;

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


        //get current item from cart manager
        currentItem = CartManager.GetInstance().GetCurrentItem();

        //set image
        coffeeImageView.setImage(currentItem.getImage());

        //setting label text to associated current item's data

        nameLabel.setText(currentItem.getName());
        BigDecimal basePrice = currentItem.getBasePrice();
        sizeOption1PriceLabel.setText(currencyFormatter.format(basePrice));
        sizeOption2PriceLabel.setText(currencyFormatter.format(basePrice.add(BigDecimal.valueOf(1))));
        sizeOption3PriceLabel.setText(currencyFormatter.format(basePrice.add(BigDecimal.valueOf(2))));


        //these labels are always the same (prices of these things are consistent across all coffee types)
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
            label.setText(currencyFormatter.format(CalculateFlavorPrice(currentItem)));

            UpdateTotal();
        };
        cb.setOnAction(event);
    }

    //What to do when the size radio group gets changed
    private void SizeGroupChanged(RadioButton rb) {
        String choice = rb.getText();
        currentItem.setSize(choice);

        UpdateSize();
    }


    //What to do when the milk radio group gets changed
    private void MilkGroupChanged(RadioButton rb) {
        String choice = rb.getText();
        currentItem.setMilk(choice);

        UpdateMilk();
    }

    //calculates the size price and updates the label, then does the same for total
    private void UpdateSize() {
        BigDecimal price = CalculateSizePrice(currentItem);
        sizePriceLabel.setText(currencyFormatter.format(price));
        UpdateTotal();
    }

    //calculates the milk price and updates the label, then does the same for total
    private void UpdateMilk() {
        BigDecimal price = CalculateMilkPrice(currentItem);
        milkPriceLabel.setText(currencyFormatter.format(price));
        UpdateTotal();
    }


    //calculates the total price and updates both current item and the label
    private void UpdateTotal() {
        BigDecimal total = CalculateTotal(currentItem);
        currentItem.setPrice(total);
        totalPriceLabel.setText(currencyFormatter.format(total));
    }

    //calculates the total price of the current item based on the current selections (also updates label)
    public BigDecimal CalculateTotal(CoffeeModel item) {
        BigDecimal total = BigDecimal.ZERO;

        //size
        total = total.add(CalculateSizePrice(item));

        //milk
        total = total.add(CalculateMilkPrice(item));

        //flavors
        total = total.add(CalculateFlavorPrice(item));

        return total;
    }

    //calculates the price of the size
    //if no base price exists, set it to 0
    public BigDecimal CalculateSizePrice(CoffeeModel item) {
        String size = item.getSize();
        if (item.getBasePrice().equals(null))
            item.setBasePrice(BigDecimal.ZERO);

        BigDecimal price = switch (size) {
            case "Small" -> item.getBasePrice();
            case "Medium" -> item.getBasePrice().add(BigDecimal.valueOf(1));
            case "Large" -> item.getBasePrice().add(BigDecimal.valueOf(2));
            default -> BigDecimal.valueOf(-100);
        };
        return price;
    }

    public BigDecimal CalculateMilkPrice(CoffeeModel item) {
        String milk = item.getMilk();
        BigDecimal price = switch (milk) {
            case "Whole" -> milkPrices[0];
            case "2%" -> milkPrices[1];
            case "Non-fat" -> milkPrices[2];
            default -> BigDecimal.valueOf(-100);
        };
        return price;
    }

    //TODO: (maybe change this) since all flavors cost exactly 1 right now, this works
    public BigDecimal CalculateFlavorPrice(CoffeeModel item) {
        BigDecimal price = BigDecimal.ZERO;

        if (item.getFlavors().equals(null))
            return BigDecimal.ZERO;

        for (String flavor : item.getFlavors()) {
            price = price.add(BigDecimal.ONE);
        }
        return price;
    }

    //What to do when the finish button is clicked
    @FXML
    public void FinishButtonClicked(ActionEvent event) throws IOException {

        //add customized item to cartManager
        CartManager.GetInstance().AddBeverage(currentItem);

        SwitchToCheckoutScreen(event);
    }

    //What to do when the back button is clicked
    @FXML
    public void BackButtonClicked(ActionEvent event) throws IOException {
        System.out.println("Back button was clicked.");

        CartManager.GetInstance().SetCurrentItem(null);
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
