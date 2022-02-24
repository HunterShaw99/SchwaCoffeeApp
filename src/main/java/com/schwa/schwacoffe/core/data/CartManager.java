package com.schwa.schwacoffe.core.data;

import com.schwa.schwacoffe.models.CoffeeModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.math.BigDecimal;



/**
 * CartManager allows for ease of communication between different scenes needing data from the cart.
 * @author Hunter
 */
public class CartManager {

    private volatile static CartManager instance = null;
    private static ObservableList<CoffeeModel> _currentOrder;
    private static CoffeeModel currentItem;

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

    public void AddBeverage(CoffeeModel toAdd) {
        _currentOrder.add(toAdd);
    }

    public void RemoveBeverage(CoffeeModel toRemove) {
        _currentOrder.remove(toRemove);
    }

    public void EmptyCart() {
        _currentOrder.clear();
    }

    public boolean CartEmpty() {
        return _currentOrder.isEmpty();
    }

    public BigDecimal GetCartTotal() {
        BigDecimal cartTotal = new BigDecimal(0);
        for (CoffeeModel c : _currentOrder) {
            cartTotal.add(c.getPrice());
        }
        return cartTotal;
    }

    public ObservableList<CoffeeModel> GetCartItems() {
        if (instance == null) {
            GetInstance();
        }
        return _currentOrder;
    }

    public CoffeeModel GetCurrentItem() {
        return currentItem;
    }
    public void SetCurrentItem(CoffeeModel m) {
        currentItem = m;
    }

}
