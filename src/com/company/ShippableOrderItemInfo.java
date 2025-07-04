package com.company;

public class ShippableOrderItemInfo implements ShippableOrderItem{
    private String name;
    private double weight;

    public ShippableOrderItemInfo(String name, double weight) {
        this.name = name;
        this.weight = weight;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public double getWeight() {
        return this.weight;
    }
}
