package com.schwa.schwacoffe.core.tasks;

import com.schwa.schwacoffe.core.data.CartManager;

public class ClearCart implements Runnable {

    @Override
    public void run() {
        CartManager.GetInstance().EmptyCart();
    }
}
