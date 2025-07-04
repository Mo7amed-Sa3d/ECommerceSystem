package com.company;

import java.util.UUID;

public class Product {
    private final UUID id;
    private final String name;
    private final double price;
    private int stockQuantity;
    private final ExpirationStrategy expirationStrategy;
    private final ShippingStrategy shippingStrategy;

    Product(String name, double price, int stockQuantity, ExpirationStrategy expirationStrategy, ShippingStrategy shippingStrategy){
        this.id = UUID.randomUUID();
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.expirationStrategy = expirationStrategy;
        this.shippingStrategy = shippingStrategy;
    }
    public UUID getId(){
        return this.id;
    }
    public boolean isAvailable(int requestedAmount){
        return requestedAmount <= stockQuantity;
    }

    public boolean isExpired() {
        return expirationStrategy.isExpired();
    }

    public boolean requiresShipping() {
        return shippingStrategy.requiresShipping();
    }

    public double getWeight() {
        return shippingStrategy.getWeight();
    }

    void reduceQuanitiy(int amountSold){
        if(amountSold > stockQuantity)
            throw new IllegalArgumentException("The amount sold is greater than the amount in stock");
        this.stockQuantity -= amountSold;
    }
    public int getStockQuantity(){
        return this.stockQuantity;
    }
    public double getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }
}
