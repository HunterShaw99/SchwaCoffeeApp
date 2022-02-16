package com.schwa.schwacoffe;

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
    private ChoiceBox sizeChoiceBox, milkChoiceBox, flavorChoiceBox;
    @FXML
    private Rectangle TODO_IMAGEVIEW;
    @FXML
    private ImageView myImageView;
    @FXML
    private RadioButton sizeRadio1, sizeRadio2, sizeRadio3,
                        milkRaio1, milkRadio2, milkRadio3;
    @FXML
    private CheckBox flavorCheckBox1, flavorCheckBox2, flavorCheckBox3;

    ToggleGroup sizeGroup;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        myImageView.setImage(new Image((String)));

        //populating choice boxes
        sizeChoiceBox.getItems().addAll("Small", "Medium", "Large");
        sizeChoiceBox.setValue("Choose a size:");

        milkChoiceBox.getItems().addAll("Whole", "2%", "Non-fat");
        milkChoiceBox.setValue("Choose a milk:");


        //Radio button config
        sizeGroup = new ToggleGroup();
        sizeRadio1.setToggleGroup(sizeGroup);
        sizeRadio2.setToggleGroup(sizeGroup);
        sizeRadio3.setToggleGroup(sizeGroup);

    }


    @FXML
    public void FinishButtonClicked(ActionEvent event) {
        System.out.println("Finish button was clicked.");
        System.out.printf("You selected the: " + sizeGroup.getSelectedToggle() + ".\n");       //doesnt print clean option
    }

    @FXML
    public void BackButtonClicked(ActionEvent event) {
        System.out.println("Back button was clicked.");
    }
}
