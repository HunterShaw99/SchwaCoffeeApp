package com.schwa.schwacoffe;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.List;

//each coffee model will have: a name, the chosen size, the chosen milk, the chosen flavors,
//                            the total price, and the prices per size.
public class CoffeeModel {
    private String name;
    private String size;
    private String milk;
    private List<String> flavors;
    private double price;
    private double[] priceOptions;

    public CoffeeModel() {
        this.priceOptions = new double[3];     //0 = small, 1 = medium, 2 = large
        this.flavors = new ArrayList<>();
        size = "";
        milk = "";
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public void setSize(String size) {
        this.size = size;
    }
    public String getSize() {
        return size;
    }
    public void setMilk(String milk) {
        this.milk = milk;
    }
    public String getMilk() {
        return milk;
    }
    public List<String> getFlavors() {
        return flavors;
    }
    public void setFlavors(List<String> flavors) {
        this.flavors = flavors;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public double getPrice() {
        return price;
    }
    public void setPriceOptions(double[] priceOptions) {
        this.priceOptions = priceOptions;
    }
    public double[] getPriceOptions() {
        return priceOptions;
    }
    public void addFlavor(String flavor) {
        flavors.add(flavor);
    }
    public void removeFlavor(String flavor) {
        flavors.remove(flavor);
    }
}
