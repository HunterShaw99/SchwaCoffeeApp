package com.schwa.schwacoffe.models.constants;

import java.math.BigDecimal;

public abstract class CoffeePrice {
    public static final BigDecimal SMALL_COST = BigDecimal.valueOf(3.45);
    public static final BigDecimal MEDIUM_COST = BigDecimal.valueOf(4.55);
    public static final BigDecimal LARGE_COST = BigDecimal.valueOf(5.75);
    public static final BigDecimal FLAVOR_COST = BigDecimal.valueOf(0.25);
    public static final BigDecimal DAIRY_COST = BigDecimal.valueOf(0.75);
    public static final BigDecimal NON_DAIRY_COST = BigDecimal.valueOf(1.25);
}
