package com.schwa.schwacoffe.core.tasks;

import com.schwa.schwacoffe.core.data.CartManager;
import com.schwa.schwacoffe.models.CoffeeModel;

public class CartAdd implements Runnable {
    @Override
    public void run() {
        CoffeeModel m = CartManager.GetInstance().GetCurrentItem();
        CartManager.GetInstance().AddBeverage(m);
        m = null;
        CartManager.GetInstance().SetCurrentItem(null);
    }
}
