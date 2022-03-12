package com.schwa.schwacoffe.core.data;

import com.schwa.schwacoffe.models.CoffeeModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.math.BigDecimal;
import java.util.UUID;


/**
 * CartManager allows for ease of communication between different scenes needing data from the cart.
 * @author Hunter
 */
public class CartManager {

    private volatile static CartManager instance = null;
    private static ObservableList<CoffeeModel> _currentOrder;
    private static CoffeeModel currentItem;
    private static ObservableList<CoffeeModel> currentItemList;
    private CartManager() {
        _currentOrder = FXCollections.observableArrayList();
        currentItemList = FXCollections.observableArrayList();
    }

    /**
     * Method for getting the CartManager object.
     * @return instance of the CartManager class.
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

    /**
     * Method which allows for safe adding to the current order.
     * @param toAdd
     */
    public void AddBeverage(CoffeeModel toAdd) {
        _currentOrder.add(toAdd);
    }

    /**
     * Method to remove specified CoffeeModel item from the current order.
     * @param toRemove
     */
    public void RemoveBeverage(CoffeeModel toRemove) {
        if (_currentOrder.contains(toRemove)) _currentOrder.remove(toRemove);;
        return;
    }

    public CoffeeModel GetBeverage(UUID beverageID) {
        for (CoffeeModel e : _currentOrder) {
            if (e.getItemID().equals(beverageID)) return e;
        }
        return null;
    }

    /**
     * Method to clear the current order.
     */
    public void EmptyCart() {
        _currentOrder.clear();
    }

    /**
     * Check if the cart of the current order is empty or populated.
     * @return boolean value depending on if cart is empty or not.
     */
    public boolean IsEmpty() {
        return _currentOrder.size() == 0;
    }

    /**
     * Method that uses BigDecimal for handling monetary value. Sums the total of all CoffeeModel items in the current
     * order.
     * @return String that represents the aggregate of entire cart.
     */
    public String GetCartTotal() {
        BigDecimal cartTotal = BigDecimal.ZERO;
        for (CoffeeModel c : _currentOrder) {
            cartTotal = cartTotal.add(c.getPrice());
        }
        return "$"+cartTotal.toString();
    }

    /**
     * Method for allowing binding of the underlying ObservableList (which represents the current orders cart) to ListViews.
     * @return Returns the current orders cart as a reference.
     */
    public ObservableList<CoffeeModel> GetCartItems() {
        return _currentOrder;
    }

    /**
     * Method for specifying a CoffeeModel item. Mostly used for communication between certain pages.
     * @return Selected item.
     */
    public CoffeeModel GetCurrentItem() {
        return currentItem;
    }

    public ObservableList<CoffeeModel> GetCurrentItemList() {
        return currentItemList;
    }
    /**
     * Sets the specified CoffeeModel item to m.
     * @param m
     */
    public void SetCurrentItem(CoffeeModel m) {
        currentItemList.clear();
        currentItem = m;
        currentItemList.add(m);
    }

}
