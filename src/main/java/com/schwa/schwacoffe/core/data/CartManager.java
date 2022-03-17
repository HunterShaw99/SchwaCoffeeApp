package com.schwa.schwacoffe.core.data;

import com.schwa.schwacoffe.core.observer.Observer;
import com.schwa.schwacoffe.core.observer.Subject;
import com.schwa.schwacoffe.models.CoffeeModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


/**
 * CartManager allows for ease of communication between different scenes needing data from the cart.
 * @author Hunter
 */
public class CartManager implements Subject {

    private volatile static CartManager instance = null;
    private static ObservableList<CoffeeModel> _currentOrder;
    private static CoffeeModel currentItem;
    private static ObservableList<CoffeeModel> currentItemList;
    private List<Observer> observers;
    private CartManager() {
        _currentOrder = FXCollections.observableArrayList();
        currentItemList = FXCollections.observableArrayList();
        observers = new ArrayList<>();
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
        notifyObservers();
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
    public BigDecimal GetCartTotal() {
        BigDecimal cartTotal = BigDecimal.ZERO;
        for (CoffeeModel c : _currentOrder) {
            cartTotal = cartTotal.add(c.getPrice());
        }
        return cartTotal;
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

    @Override
    public void registerObserver(Observer o) {
        if (!observers.contains(o)) observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        if (observers.contains(o)) observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for (Observer o: observers) {
            o.update(GetCartTotal());
        }
    }
}
