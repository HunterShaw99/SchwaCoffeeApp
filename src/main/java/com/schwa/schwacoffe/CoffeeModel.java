package com.schwa.schwacoffe;

import java.util.List;

public class CoffeeModel {
    private String type;
    private String size;
    private String milk;
    private List<String> flavors;
    private int price;

    public CoffeeModel() {

    }

    public void setType(String type) {
        this.type = type;
    }
    public String getType() {
        return type;
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
    public void addFlavor(String flavor) {
        flavors.add(flavor);
    }
    public void removeFlavor(String flavor) {
        flavors.remove(flavor);
    }
    public void setPrice(int price) {
        this.price = price;
    }
    public int getPrice() {
        return price;
    }

}
