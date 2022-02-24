package com.schwa.schwacoffe.models;

import javafx.scene.image.Image;

import java.math.BigDecimal;
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
    private BigDecimal price;
    private BigDecimal basePrice;
    private Image image;

    public CoffeeModel() {
        this.basePrice = BigDecimal.ZERO;
        this.flavors = new ArrayList<>();
        size = "";
        milk = "";
    }

    public String toString() {
        return name+" Price:"+price.toString()+" Flavors:"+flavors.toString();
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
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    public BigDecimal getPrice() {
        return price;
    }
    public void setBasePrice(BigDecimal basePrice) {
        this.basePrice = basePrice;
    }
    public BigDecimal getBasePrice() {
        return basePrice;
    }
    public void addFlavor(String flavor) {
        flavors.add(flavor);
    }
    public void removeFlavor(String flavor) {
        flavors.remove(flavor);
    }
    public void setImage(Image image) {
        this.image = image;
    }
    public Image getImage() {
        return image;
    }
}