package com.schwa.schwacoffe.models;

import com.schwa.schwacoffe.models.constants.OrderStatus;
import javafx.collections.ObservableList;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class OrderModel implements Serializable {

    public BigDecimal orderTotal;
    public ObservableList<CoffeeModel> beverageLIST;
    public final String orderID;
    public OrderStatus orderStatus;//Order Status is either {PROCESSING, DONE, CANCELLED}

    public OrderModel(BigDecimal orderTotal, ObservableList<CoffeeModel> beverageLIST, OrderStatus orderStatus) {
        this.orderTotal = orderTotal;
        this.beverageLIST = beverageLIST;
        orderID = UUID.randomUUID().toString().substring(0,11);
        this.orderStatus = orderStatus;
    }

    public BigDecimal Get_OrderTotal() {
        return orderTotal;
    }

    public ObservableList<CoffeeModel> GetBeverageList() {
        return beverageLIST;
    }

    public String GetOrderID() {
        return orderID;
    }

    public OrderStatus GetOrderStatus() {
        return orderStatus;
    }

    public void SetOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

}
