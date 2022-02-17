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
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class CustomizationController implements Initializable {
    @FXML
    private Button backButton, finishButton;
    @FXML
    private Label sizeLabel, milkLabel, flavorLabel,
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


        //TODO: retrieve current item from singleton (or other page passes it in if that's possible)
        //currentItem = Singleton.getInstance().getCurrentItem()
        currentItem = new CoffeeModel();        //temporary code


    }

    //Adds an event handler for a given checkbox
    private void AddEventHandlerForCheckbox(CheckBox cb) {
        Label label = flavorPriceLabel;
        EventHandler<ActionEvent> event = actionEvent -> {
            String text = label.getText();
            if (cb.isSelected()) {
                text = text + cb.getText();
            } else {
                text = text.replace(cb.getText(), "");
            }
            label.setText(text);
        };
        cb.setOnAction(event);
    }

    //What to do when the size radio group gets changed
    private void SizeGroupChanged(RadioButton rb) {
        RadioGroupChanged(rb, sizePriceLabel);
    }
    //What to do when the milk radio group gets changed
    private void MilkGroupChanged(RadioButton rb) {
        RadioGroupChanged(rb, milkPriceLabel);
    }

    //What to do when a radio group gets changed
    private void RadioGroupChanged(RadioButton rb, Label label) {
        String newText = rb.getText();
        label.setText(newText);
    }

    //What to do when the finish button is clicked
    @FXML
    public void FinishButtonClicked(ActionEvent event) {

        //testing stuff
        System.out.println("Finish button was clicked.");
        RadioButton selectedSize = (RadioButton)sizeGroup.getSelectedToggle();
        RadioButton selectedMilk = (RadioButton)milkGroup.getSelectedToggle();
        if (selectedSize != null)
            System.out.printf("You selected the: " + selectedSize.getText() + " size.\n");
        else
            System.out.println("You did not select a size.");
        if (selectedMilk != null)
            System.out.printf("You selected the: " + selectedMilk.getText() + " milk.\n");
        else
            System.out.println("You did not select a milk.");
        System.out.printf("Flavor 1:" + flavorCheckBox1.selectedProperty().getValue() + ".\n");
        System.out.printf("Flavor 2:" + flavorCheckBox2.selectedProperty().getValue() + ".\n");
        System.out.printf("Flavor 3:" + flavorCheckBox3.selectedProperty().getValue() + ".\n");


        //ensure that a size and milk are selected
        if (selectedSize == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("You must select a size.");
            return;
        }
        if (selectedMilk == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("You must select a milk.");
            return;
        }

        ApplyChoices();

        SwitchToCheckoutScreen();
    }

    //populate the current item with the currently selected choices
    private void ApplyChoices() {

        String size = ((RadioButton)sizeGroup.getSelectedToggle()).getText();
        String milk = ((RadioButton)milkGroup.getSelectedToggle()).getText();

        List<String> flavors = new ArrayList<String>();
        if (flavorCheckBox1.isSelected())
            flavors.add(flavorCheckBox1.getText());
        if (flavorCheckBox2.isSelected())
            flavors.add(flavorCheckBox2.getText());
        if (flavorCheckBox3.isSelected())
            flavors.add(flavorCheckBox3.getText());

        currentItem.setSize(size);
        currentItem.setMilk(milk);
        currentItem.setFlavors(flavors);
    }

    //What to do when the back button is clicked
    @FXML
    public void BackButtonClicked(ActionEvent event) {
        System.out.println("Back button was clicked.");

        //TODO: Singleton.discardCurrentItem() maybe

        SwitchToMenuScreen();
    }

    //TODO: this
    public void SwitchToMenuScreen(){

    }

    //TODO: this
    public void SwitchToCheckoutScreen() {

    }

}
