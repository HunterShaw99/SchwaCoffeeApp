package com.schwa.schwacoffe.core.controllers;

import com.schwa.schwacoffe.core.data.CartManager;
import com.schwa.schwacoffe.core.observer.Observer;
import com.schwa.schwacoffe.core.tasks.ClearCart;
import com.schwa.schwacoffe.models.CoffeeModel;
import javafx.application.Platform;
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
import java.io.IOException;
import java.math.BigDecimal;


public class CartController implements Observer {

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

    private Stage stage;
    private Scene scene;
    private Parent root;
    private int numInits = 0;

    //File baristaView = new File("orders.txt");

    /**
     * Method that is called every time CartController is loaded into the stage.
     * Used to bind the ListView to the current orders cart.
     * Changes the total sum of the cart each time.
     */
    public void initialize() {
        if (numInits == 0) {
            CartListView.setItems(CartManager.GetInstance().GetCartItems());
            CartListView.setCellFactory(new CoffeeCellFactory());
            CartManager.GetInstance().registerObserver(this);
            numInits++;
        }
        CartTotalLabel.setText("$"+CartManager.GetInstance().GetCartTotal());
    }

    @FXML
    void CancelOrderClicked(MouseEvent event) throws IOException {
        Platform.runLater(new ClearCart());
        //switch scenes
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Menu.fxml"));
        root = loader.load();

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void MenuButtonClicked(MouseEvent event) throws IOException {

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
        if (!CartManager.GetInstance().IsEmpty()) {
            //switch scenes
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Confirmation-view.fxml"));
            root = loader.load();

            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);

            stage.setScene(scene);
            stage.show();
        }
        CartListView.requestFocus();

    }

    @Override
    public void update(BigDecimal total) {
        CartTotalLabel.setText("$"+total);
    }
    /*
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

     */

    /*
    void WriteBaristaFile() throws FileNotFoundException {
        PrintWriter writer = new PrintWriter("orders.txt");
        String names[] = new String[5], sizes[] = new String[5], milk[] = new String[5], flavors[] = new String[5];
        int i = 0;

        writer.println("Customer Order: ");
        for (CoffeeModel item : CartManager.GetInstance().GetCartItems()) {
            names[i] = item.getName();
            sizes[i] = item.getSize().name();
            milk[i] = item.getMilkValue();
            flavors[i] = String.valueOf(item.getFlavors());

            writer.println("   Item " + (i+1) + ":  " + names[i] + "    " + sizes[i] + "    " + milk[i] + "    " + flavors[i]);
            writer.println();
            i++;
        }
        writer.close();
    }

     */
}

