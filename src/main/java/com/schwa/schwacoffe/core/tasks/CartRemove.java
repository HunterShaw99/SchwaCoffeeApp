package com.schwa.schwacoffe.core.tasks;

import com.schwa.schwacoffe.core.data.CartManager;
import com.schwa.schwacoffe.models.CoffeeModel;

public class CartRemove implements Runnable {

    private CoffeeModel m;

    public CartRemove(CoffeeModel m) {
        this.m = m;
    }

    @Override
    public void run() {
        CartManager.GetInstance().RemoveBeverage(m);
        m = null;
    }
}
