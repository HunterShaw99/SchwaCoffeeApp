package com.schwa.schwacoffe.models;

import com.schwa.schwacoffe.models.constants.Dairy;
import com.schwa.schwacoffe.models.constants.Flavor;
import com.schwa.schwacoffe.models.constants.Size;
import javafx.scene.image.Image;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

//each coffee model will have: a name, the chosen size, the chosen milk, the chosen flavors,
//                            the total price, and the prices per size.
public class CoffeeModel {

    private UUID itemID;
    private String name;
    private Size size;
    private Dairy milk;
    private List<Flavor> flavors;
    private BigDecimal price;
    private Image image;

    public CoffeeModel() {
        itemID = UUID.randomUUID();
        this.price = BigDecimal.ZERO;
        this.flavors = new ArrayList<Flavor>();
    }

    public CoffeeModel(String imageURL) {
        itemID = UUID.randomUUID();
        image = new Image(imageURL);
        this.price = BigDecimal.ZERO;
        this.flavors = new ArrayList<Flavor>();
    }

    public UUID getItemID() {
        return this.itemID;
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

    public void setSize(Size size) {
        this.size = size;
    }

    public Size getSize() {
        return size;
    }

    public void setMilk(Dairy milk) {
        this.milk = milk;
    }

    public Dairy getMilk() {
        return milk;
    }

    public String getMilkValue() {
        return milk.getDairy();
    }

    public String getFlavors() {
        String str = flavors.toString();
        return str.substring(1, str.length()-1);
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void addFlavor(Flavor flavor) {
        flavors.add(flavor);
    }

    public void removeFlavor(Flavor flavor) {
        flavors.remove(flavor);
    }

    public Image getImage() {
        return image;
    }
}
