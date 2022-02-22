package com.schwa.schwacoffe.models;

import java.math.BigDecimal;

public class OrderModel {

    public final BigDecimal orderTotal;

    public OrderModel(BigDecimal total) {
        this.orderTotal = total;
    }

}
