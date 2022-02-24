package com.schwa.schwacoffe.core.controllers;

import com.schwa.schwacoffe.core.controllers.CustomizationController;
import com.schwa.schwacoffe.models.CoffeeModel;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class testCustomizationController {

    private final CustomizationController customizationController = new CustomizationController();

    @Test
    void testCalculateSizePrice() {
        BigDecimal result;
        CoffeeModel item;

        //test empty item
        item = new CoffeeModel();
        result = customizationController.CalculateSizePrice(item);
        assertEquals(BigDecimal.valueOf(-100), result);

        //test item with no base price
        item = new CoffeeModel();
        item.setSize("Small");
        result = customizationController.CalculateSizePrice(item);
        assertEquals(BigDecimal.ZERO, result);

        //test item with a valid size picked
        item = new CoffeeModel();
        BigDecimal basePrice = BigDecimal.valueOf(3);
        item.setBasePrice(basePrice);
        item.setSize("Small");
        result = customizationController.CalculateSizePrice(item);
        assertEquals(basePrice, result);

        //test item with an invalid size picked
        item = new CoffeeModel();
        basePrice = BigDecimal.valueOf(1);
        item.setBasePrice(basePrice);
        item.setSize("XXXLarge");
        result = customizationController.CalculateSizePrice(item);
        assertEquals(BigDecimal.valueOf(-100), result);

    }

    @Test
    void testCalculateMilkPrice() {
        BigDecimal result;
        CoffeeModel item;

        //test empty item
        item = new CoffeeModel();
        result = customizationController.CalculateMilkPrice(item);
        assertEquals(BigDecimal.valueOf(-100), result);

        //test item with a valid milk picked
        item = new CoffeeModel();
        item.setMilk("Whole");
        result = customizationController.CalculateMilkPrice(item);
        assertEquals(BigDecimal.ONE, result);

        //test item with an invalid milk picked
        item = new CoffeeModel();
        item.setMilk("Chocolate Milk");
        result = customizationController.CalculateMilkPrice(item);
        assertEquals(BigDecimal.valueOf(-100), result);
    }

    @Test
    void testCalculateFlavorPrice() {
        BigDecimal result;
        CoffeeModel item;

        //test empty item
        item = new CoffeeModel();
        result = customizationController.CalculateFlavorPrice(item);
        assertEquals(BigDecimal.ZERO, result);

        //test item with 1 flavor
        item = new CoffeeModel();
        item.addFlavor("Vanilla");
        result = customizationController.CalculateFlavorPrice(item);
        assertEquals(BigDecimal.ONE, result);

        //test item with 3 flavors
        item = new CoffeeModel();
        item.addFlavor("Vanilla");
        item.addFlavor("Caramel");
        item.addFlavor("Hazelnut");
        result = customizationController.CalculateFlavorPrice(item);
        assertEquals(result, BigDecimal.valueOf(3));
    }
    @Test
    void testCalculateTotal() {
        BigDecimal result;
        CoffeeModel item;

        //test empty item
        item = new CoffeeModel();
        result = customizationController.CalculateTotal(item);
        assertEquals(BigDecimal.valueOf(-200), result);

        //test item with only milk
        item = new CoffeeModel();
        item.setMilk("Whole");
        result = customizationController.CalculateTotal(item);
        assertEquals(BigDecimal.valueOf(-99), result);

        //test item with only flavors
        item = new CoffeeModel();
        item.addFlavor("Vanilla");
        result = customizationController.CalculateTotal(item);
        assertEquals(BigDecimal.valueOf(-199), result);

        //test item with price milk and flavors
        item = new CoffeeModel();
        item.setBasePrice(BigDecimal.valueOf(4));
        item.setSize("Small");
        item.setMilk("Whole");
        item.addFlavor("Vanilla");
        item.addFlavor("Caramel");
        result = customizationController.CalculateTotal(item);
        assertEquals(BigDecimal.valueOf(7), result);

    }
}
