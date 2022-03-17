package com.schwa.schwacoffe.core.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.schwa.schwacoffe.core.data.CartManager;
import com.schwa.schwacoffe.models.CoffeeModel;
import com.schwa.schwacoffe.models.constants.CoffeePrice;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuController {

    @FXML
    public Rectangle rect;

    @FXML
    private Button CartButton;

    @FXML
    private HBox HeaderBar;

    @FXML
    private ImageView hotDrinkIcon;

    @FXML
    private ImageView coldDrinkIcon;

    @FXML
    private Button customizeButton1, customizeButton2, customizeButton3, customizeButton4, customizeButton5, customizeButton6,
            customizeButton7, customizeButton8, customizeButton9, customizeButton10, customizeButton11, customizeButton12;

    @FXML
    private Label item1Name, item2Name, item3Name, item4Name, item5Name, item6Name, item7Name, item8Name, item9Name,
            item10Name, item11Name, item12Name;

    @FXML
    private ImageView item1Picture, item2Picture, item3Picture, item4Picture, item5Picture, item6Picture, item7Picture,
            item8Picture, item9Picture, item10Picture, item11Picture, item12Picture;



    private Stage stage;
    private Scene scene;
    private Parent root;

    public void initialize() {
        item1Picture.setImage(new Image("file:src/main/resources/com/schwa/schwacoffe/Cuppuccino.PNG"));
        item2Picture.setImage(new Image("file:src/main/resources/com/schwa/schwacoffe/WhiteChoc.PNG"));
        item3Picture.setImage(new Image("file:src/main/resources/com/schwa/schwacoffe/CarmelMacc.PNG"));
        item4Picture.setImage(new Image("file:src/main/resources/com/schwa/schwacoffe/CinnamonDL.PNG"));
        item5Picture.setImage(new Image("file:src/main/resources/com/schwa/schwacoffe/ExpressoMacc.PNG"));
        item6Picture.setImage(new Image("file:src/main/resources/com/schwa/schwacoffe/PistachioL.PNG"));
        item7Picture.setImage(new Image("file:src/main/resources/com/schwa/schwacoffe/IcedCoffee.PNG"));
        item8Picture.setImage(new Image("file:src/main/resources/com/schwa/schwacoffe/IcedCarmelMacc.PNG"));
        item9Picture.setImage(new Image("file:src/main/resources/com/schwa/schwacoffe/IcedCoffeeL.PNG"));
        item10Picture.setImage(new Image("file:src/main/resources/com/schwa/schwacoffe/IcedExpresso.PNG"));
        item11Picture.setImage(new Image("file:src/main/resources/com/schwa/schwacoffe/SaltedCarmel.PNG"));
        item12Picture.setImage(new Image("file:src/main/resources/com/schwa/schwacoffe/IcedCoffeeA.PNG"));
    }

    @FXML
    void CartButtonPressed(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("cart-view.fxml"));
        root = loader.load();

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void CustomizeButtonPressed(ActionEvent event) throws IOException {
        Button btn = (Button) event.getSource();
        String id = btn.getId();
        String itemName = "";
        String image = null;

        // Retrieve name and price of item selected
        switch (id) {
            case "customizeButton1" :
                itemName = item1Name.getText();
                image = item1Picture.getImage().getUrl();
                break;
            case "customizeButton2" :
                itemName = item2Name.getText();
                image = item2Picture.getImage().getUrl();
                break;
            case "customizeButton3" :
                itemName = item3Name.getText();
                image = item3Picture.getImage().getUrl();
                break;
            case "customizeButton4" :
                itemName = item4Name.getText();
                image = item4Picture.getImage().getUrl();
                break;
            case "customizeButton5" :
                itemName = item5Name.getText();
                image = item5Picture.getImage().getUrl();
                break;
            case "customizeButton6" :
                itemName = item6Name.getText();
                image = item6Picture.getImage().getUrl();
                break;
            case "customizeButton7" :
                itemName = item7Name.getText();
                image = item7Picture.getImage().getUrl();
                break;
            case "customizeButton8" :
                itemName = item8Name.getText();
                image = item8Picture.getImage().getUrl();
                break;
            case "customizeButton9" :
                itemName = item9Name.getText();
                image = item9Picture.getImage().getUrl();
                break;
            case "customizeButton10" :
                itemName = item10Name.getText();
                image = item10Picture.getImage().getUrl();
                break;
            case "customizeButton11" :
                itemName = item11Name.getText();
                image = item11Picture.getImage().getUrl();
                break;
            case "customizeButton12" :
                itemName = item12Name.getText();
                image = item12Picture.getImage().getUrl();
                break;
        }
        CoffeeModel m = new CoffeeModel(image, CoffeePrice.MEDIUM_COST);//Default base price for all beverages is medium
        m.setName(itemName);
        System.out.println(image);
        CartManager.GetInstance().SetCurrentItem(m);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("customization-view.fxml"));
        root = loader.load();

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }
}

