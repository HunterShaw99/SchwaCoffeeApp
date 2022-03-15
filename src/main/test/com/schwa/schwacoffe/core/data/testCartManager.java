package com.schwa.schwacoffe.core.data;

import com.schwa.schwacoffe.models.CoffeeModel;
import com.schwa.schwacoffe.models.constants.Dairy;
import com.schwa.schwacoffe.models.constants.Size;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class testCartManager {

    private CoffeeModel e = new CoffeeModel();


    @Test
    @Order(1)
    void testEmptyCart() {
        CartManager.GetInstance().EmptyCart();
        //test to make sure cart is empty
        assertEquals(true, CartManager.GetInstance().IsEmpty());

        //test cart isn't empty
        e.setPrice(BigDecimal.valueOf(5.25));
        e.setName("Coffee");
        e.setMilk(Dairy.WHOLE);
        e.setSize(Size.SMALL);
        CartManager.GetInstance().AddBeverage(e);
        assertEquals(false, CartManager.GetInstance().IsEmpty());
        CartManager.GetInstance().EmptyCart();
    }

    @Test
    @Order(2)
    void testCurrentItem() {
        //Test no item is selected
        assertEquals(true, CartManager.GetInstance().GetCurrentItem() == null);

        //Test e is current selected item
        CartManager.GetInstance().SetCurrentItem(e);
        assertEquals(true, CartManager.GetInstance().GetCurrentItem().equals(e));

    }

    @Test
    @Order(3)
    void testGetBeverage() {
        CartManager.GetInstance().AddBeverage(e);
        assertEquals(e, CartManager.GetInstance().GetBeverage(e.getItemID()));
        CartManager.GetInstance().EmptyCart();
    }

    @Test
    @Order(4)
    void testRemoveItem() {
        CartManager.GetInstance().AddBeverage(e);
        CartManager.GetInstance().RemoveBeverage(e);
        assertEquals(true, CartManager.GetInstance().IsEmpty());
    }
}
