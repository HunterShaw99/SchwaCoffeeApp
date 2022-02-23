package com.schwa.schwacoffe;

import com.schwa.schwacoffe.core.data.CartManager;
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
import javafx.stage.Stage;

import java.io.IOException;
import java.math.BigDecimal;

public class MenuController {

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

    @FXML
    private Label item1Price, item2Price, item3Price, item4Price, item5Price, item6Price, item7Price, item8Price,
            item9Price, item10Price, item11Price, item12Price;

    private CartManager cartManager = CartManager.GetInstance();

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    void CartButtonPressed(ActionEvent event) throws IOException {
        // TODO: transfer to cart page
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
        String itemName = "", itemPrice = "";
        Image image = null;

        // Retrieve name and price of item selected
        switch (id) {
            case "customizeButton1" :
                itemName = item1Name.getText();
                itemPrice = item1Price.getText();
                image = item1Picture.getImage();
                break;
            case "customizeButton2" :
                itemName = item2Name.getText();
                itemPrice = item2Price.getText();
                image = item2Picture.getImage();
                break;
            case "customizeButton3" :
                itemName = item3Name.getText();
                itemPrice = item3Price.getText();
                image = item3Picture.getImage();
                break;
            case "customizeButton4" :
                itemName = item4Name.getText();
                itemPrice = item4Price.getText();
                image = item4Picture.getImage();
                break;
            case "customizeButton5" :
                itemName = item5Name.getText();
                itemPrice = item5Price.getText();
                image = item5Picture.getImage();
                break;
            case "customizeButton6" :
                itemName = item6Name.getText();
                itemPrice = item6Price.getText();
                image = item6Picture.getImage();
                break;
            case "customizeButton7" :
                itemName = item7Name.getText();
                itemPrice = item7Price.getText();
                image = item7Picture.getImage();
                break;
            case "customizeButton8" :
                itemName = item8Name.getText();
                itemPrice = item8Price.getText();
                image = item8Picture.getImage();
                break;
            case "customizeButton9" :
                itemName = item9Name.getText();
                itemPrice = item9Price.getText();
                image = item9Picture.getImage();
                break;
            case "customizeButton10" :
                itemName = item10Name.getText();
                itemPrice = item10Price.getText();
                image = item10Picture.getImage();
                break;
            case "customizeButton11" :
                itemName = item11Name.getText();
                itemPrice = item11Price.getText();
                image = item11Picture.getImage();
                break;
            case "customizeButton12" :
                itemName = item12Name.getText();
                itemPrice = item12Price.getText();
                image = item12Picture.getImage();
                break;
        }
        String Price = itemPrice.replace("$", "");      // Remove $ from string
        BigDecimal price = BigDecimal.valueOf(Double.parseDouble(Price));   //convert string to bigdecimal

        CoffeeModel m = new CoffeeModel();
        m.setBasePrice(price);
        m.setName(itemName);
        m.setImage(image);
        cartManager.SetCurrentItem(m);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("customization-view.fxml"));
        root = loader.load();

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }
}

