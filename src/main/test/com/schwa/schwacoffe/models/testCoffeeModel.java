package com.schwa.schwacoffe.models;

import com.schwa.schwacoffe.models.constants.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.math.BigDecimal;


public class testCoffeeModel {

    private CoffeeModel toTest = new CoffeeModel();
    private BigDecimal testCost = BigDecimal.valueOf(3.95);

    @Test
    void testSize() {
        toTest.setSize(Size.MEDIUM);
        System.out.println(toTest.getPrice());
        assertEquals(Size.MEDIUM, toTest.getSize());
    }

    @Test
    void testAddFlavor() {
        toTest.addFlavor(Flavor.CARAMEL);
        toTest.addFlavor(Flavor.VANILLA);
        assertEquals("CARAMEL, VANILLA", toTest.getFlavors());
    }

    @Test
    void testPrice() {
        toTest.setPrice(CoffeePrice.SMALL_COST);
        toTest.setPrice(toTest.getPrice().add(CoffeePrice.FLAVOR_COST));
        toTest.setPrice(toTest.getPrice().add(CoffeePrice.FLAVOR_COST));
        assertEquals(testCost, toTest.getPrice());
    }

    @Test
    void testDairy() {
        toTest.setMilk(Dairy.SOY);
        assertEquals("Soy", toTest.getMilk());
    }
}
