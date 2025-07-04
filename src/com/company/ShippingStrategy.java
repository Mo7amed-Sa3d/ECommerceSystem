package com.company;

public interface ShippingStrategy {
    boolean requiresShipping();
    double getWeight();
}
