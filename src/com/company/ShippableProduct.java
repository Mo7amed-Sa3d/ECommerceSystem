package com.company;

public class ShippableProduct implements ShippingStrategy {

    private final double weight;
    ShippableProduct(double weight){
        this.weight = weight;
    }
    @Override
    public boolean requiresShipping() {
        return true;
    }

    @Override
    public double getWeight() {
        return this.weight;
    }
}
