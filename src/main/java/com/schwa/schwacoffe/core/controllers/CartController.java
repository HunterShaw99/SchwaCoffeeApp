package com.schwa.schwacoffe.core.controllers;

import com.schwa.schwacoffe.core.controllers.CoffeeCellFactory;
import com.schwa.schwacoffe.core.data.CartManager;
import com.schwa.schwacoffe.models.CoffeeModel;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.List;

public class CartController {

    @FXML
    private Button BackButton;

    @FXML
    private Label CartLabel;

    @FXML
    private ListView<CoffeeModel> CartListView;

    @FXML
    private Label CartTitleLabel;

    @FXML
    private Label CartTotalLabel;

    @FXML
    private HBox HeaderBar;

    @FXML
    private Label PickUpLabel;

    @FXML
    private Slider PickupSlider;

    @FXML
    private Button PlaceOrderButton;

    @FXML
    private Button PlaceOrderButton1;

    private CartManager cartManager;

    private Stage stage;
    private Scene scene;
    private Parent root;

    File baristaView = new File("orders.txt");

    public void initialize() {
        cartManager = CartManager.GetInstance();
        CartListView.setItems(cartManager.GetCartItems());
        CartListView.setCellFactory(new CoffeeCellFactory());
    }

    @FXML
    void CancelOrderClicked(MouseEvent event) {
        System.out.println("Order Canceled");
    }

    @FXML
    void MenuButtonClicked(MouseEvent event) throws IOException {
        System.out.println("Menu Button Clicked");
        for (Object c : CartListView.getItems().toArray()) {
            System.out.println(c.toString());
        }

        //switch scenes
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Menu.fxml"));
        root = loader.load();

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void PlaceOrderClicked(MouseEvent event) throws IOException {
        System.out.println("Order Placed");

        CreateBaristaFile();
        WriteBaristaFile();

        //switch scenes
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Confirmation-view.fxml"));
        root = loader.load();

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }

    void CreateBaristaFile() {
        try {
            if (baristaView.createNewFile()) {
            }
            else {
                System.out.println("File already exists");
            }
        } catch (IOException e) {
            System.out.println("ERROR");
        }
    }

    void WriteBaristaFile() throws FileNotFoundException {
        cartManager = CartManager.GetInstance();
        ObservableList<CoffeeModel> orders = cartManager.GetCartItems();

        PrintWriter writer = new PrintWriter("orders.txt");
        String names[] = new String[5], sizes[] = new String[5], milk[] = new String[5], flavors[] = new String[5];
        int i = 0;

        writer.println("Customer Order: ");
        for (CoffeeModel item : orders) {
            names[i] = item.getName();
            sizes[i] = item.getSize();
            milk[i] = item.getMilk();
            flavors[i] = String.valueOf(item.getFlavors());

            writer.println("   Item " + (i+1) + ":  " + names[i] + "    " + sizes[i] + "    " + milk[i] + "    " + flavors[i]);
            writer.println();
            i++;
        }
        writer.close();
    }
}

