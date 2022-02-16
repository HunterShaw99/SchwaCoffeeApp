package com.schwa.schwacoffe;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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


    }

    public void SizeGroupChanged(RadioButton rb) {
        RadioGroupChanged(rb, sizePriceLabel);
    }
    public void MilkGroupChanged(RadioButton rb) {
        RadioGroupChanged(rb, milkPriceLabel);
    }

    public void RadioGroupChanged(RadioButton rb, Label label) {
        String newText = rb.getText();
        label.setText(newText);
    }

    @FXML
    public void FinishButtonClicked(ActionEvent event) {
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
    }

    @FXML
    public void BackButtonClicked(ActionEvent event) {
        System.out.println("Back button was clicked.");
    }
}
