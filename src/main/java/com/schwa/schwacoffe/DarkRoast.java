package com.schwa.schwacoffe;

import java.util.Dictionary;
import java.util.Hashtable;

public class DarkRoast extends CoffeeModel {

    public DarkRoast() {

        //each kind of coffee needs a name, and its associated prices per size
        this.setName("Dark Roast");
        double[] priceOptions = new double[] {
                1.0, 1.5, 2.0
        };
        this.setPriceOptions(priceOptions);

    }
}
