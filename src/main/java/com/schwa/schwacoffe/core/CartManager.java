package com.schwa.schwacoffe.core;

import com.schwa.schwacoffe.CoffeeModel;

import java.math.BigDecimal;
import java.util.List;

public class CartManager {

    private volatile static CartManager instance = null;
    private static List<CoffeeModel> _currentOrder;

    private CartManager() {

    }

    public static CartManager getInstance() {
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
        //TODO: Implement add functionality
        _currentOrder.add(toAdd);
    }

    public static void RemoveBeverage(CoffeeModel toRemove) {
        //TODO: Implement remove functionality
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
        //TODO: Implement functionality for getting cart total
        for (CoffeeModel c : _currentOrder) {
            cartTotal.add(c.getPrice());
        }
        return cartTotal;
    }

}
