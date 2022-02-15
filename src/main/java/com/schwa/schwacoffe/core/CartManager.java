package com.schwa.schwacoffe.core;

public class CartManager {

    private volatile static CartManager instance = null;

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

}
