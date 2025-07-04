package com.company;

public class NotShippable implements ShippingStrategy{
    @Override
    public boolean requiresShipping() {
        return false;
    }

    @Override
    public double getWeight() {
        throw new UnsupportedOperationException("Not a shippable product");
    }
}
