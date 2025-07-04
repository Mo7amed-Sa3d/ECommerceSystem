package com.company;

import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {
        Product tv = new Product("TV", 5000, 10,
                new NeverExpires(), new ShippableProduct(5000));

        Product cheese = new Product("Cheese", 70, 10,
                new ExpiresOnDate(LocalDate.of(2026, 7, 15)),
                new ShippableProduct(250));

        Product scratchCard = new Product("Scratch Card", 20, 200,
                new NeverExpires(), new NotShippable());

        Customer Mohamed = new Customer("Mohamed", 50_000);

        Mohamed.addToCart(tv, 1);
        Mohamed.addToCart(cheese, 2);
        Mohamed.addToCart(scratchCard, 2);
        Mohamed.checkout();

    }
}

/*
Console Output:


** Shipment notice **
Qty   Product              Weight (g)
---------------------------------------------
1     TV                      5000.00
2     Cheese                   250.00

Total package weight: 5.50 kg

** Checkout receipt **
Qty   Product              Unit Price
---------------------------------------------
2     Scratch Card              20.00
1     TV                      5000.00
2     Cheese                    70.00
---------------------------------------------
Subtotal                     5180.00
Shipping                      157.50
Total Amount                 5337.50

Mohamed current balance 44662.5 LE



 */