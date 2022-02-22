package com.schwa.schwacoffe.core.data;

import com.schwa.schwacoffe.CoffeeModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.math.BigDecimal;
import java.util.ArrayList;


/**
 * CartManager allows for ease of communication between different scenes needing data from the cart.
 * @author Hunter
 */
public class CartManager {

    private volatile static CartManager instance = null;
    private static ObservableList<String> _currentOrder;

    private CartManager() {
        _currentOrder = FXCollections.observableArrayList();
    }

    /**
     * Method for getting the CartManager object
     * @return instance of the CartManager class
     */
    public static CartManager GetInstance() {
        if (instance == null) {
            synchronized (CartManager.class) {
                if (instance == null) {
                    instance = new CartManager();
                }
            }
        }
        return instance;
    }

    public static void AddBeverage(CoffeeModel toAdd) {
        _currentOrder.add(toAdd.toString());
    }

    public static void RemoveBeverage(CoffeeModel toRemove) {
        _currentOrder.remove(toRemove);
    }

    public static void EmptyCart() {
        _currentOrder.clear();
    }

    public static boolean CartEmpty() {
        return _currentOrder.isEmpty();
    }

    public static BigDecimal GetCartTotal() {
        BigDecimal cartTotal = new BigDecimal(0);
        /*
        for (CoffeeModel c : _currentOrder) {
            cartTotal.add(c.getPrice());
        }
        */
        return cartTotal;
    }

    public static ObservableList<String> GetCartItems() {
        if (instance == null) {
            GetInstance();
        }
        return _currentOrder;
    }

}
