package com.schwa.schwacoffe.models;

import javafx.collections.ObservableList;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public class OrderModel {

    private final BigDecimal orderTotal;
    private final List<CoffeeModel> beverageLIST;
    private final String orderID;

    public OrderModel(BigDecimal orderTotal, ObservableList<CoffeeModel> beverageLIST) {
        this.orderTotal = orderTotal;
        this.beverageLIST = beverageLIST;
        orderID = UUID.randomUUID().toString().substring(0,11);
    }

    public BigDecimal Get_OrderTotal() {
        return orderTotal;
    }

    public ObservableList<CoffeeModel> GetBeverageList() {
        return (ObservableList<CoffeeModel>) beverageLIST;
    }

    public String GetOrderID() {
        return orderID;
    }

}
