package com.company;

import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {
        Product tv = new Product("TV", 5000, 10,
                new NeverExpires(), new ShippableProduct(5000));

        Product cheese = new Product("Cheese", 70, 10,
                new ExpiresOnDate(LocalDate.of(2026, 7, 15)), new ShippableProduct(250));

        Product scratchCard = new Product("Scratch Card", 20, 200,
                new NeverExpires(), new NotShippable());

        Customer Mohamed = new Customer("Mohamed", 30_000);

        Mohamed.addToCart(tv, 1);
        Mohamed.addToCart(cheese, 2);
        Mohamed.addToCart(scratchCard, 2);
        Mohamed.checkout();

    }
}
