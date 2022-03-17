package com.schwa.schwacoffe.core.tasks;

import com.schwa.schwacoffe.core.data.CartManager;
import com.schwa.schwacoffe.models.CoffeeModel;
import com.schwa.schwacoffe.models.constants.Flavor;

public class CartAdd implements Runnable {
    @Override
    public void run() {
        CoffeeModel m = CartManager.GetInstance().GetCurrentItem();
        if (m.getFlavors().size() == 0) m.addFlavor(Flavor.NONE);
        CartManager.GetInstance().AddBeverage(m);
        m = null;
        CartManager.GetInstance().SetCurrentItem(null);
    }
}
