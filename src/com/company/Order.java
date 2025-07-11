package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Order {
    Map<Product, Integer> orderItems;
    List<ShippableOrderItemInfo> itemsToBeShipped = new ArrayList<>();
    private double totalWeight;
    private double productsPrice;
    private double shippingFee;
    private double totalAmount;

    Order(Map<Product, Integer> orderItems) {
        this.orderItems = orderItems;
        validateProducts();
        extractShippableProducts();
        this.productsPrice = calculateOrderPrice();
        this.totalWeight = calculateOrderWeight();
        this.shippingFee = processShipping();
        this.totalAmount = productsPrice + shippingFee;
    }

    private void validateProducts() {
        for (Map.Entry<Product, Integer> orderItem : orderItems.entrySet()) {
            Product product = orderItem.getKey();
            int requestedQuantity = orderItem.getValue();
            if (product.isExpired())
                throw new IllegalStateException(product.getName() + " is expired\n");
            if (product.getStockQuantity() < requestedQuantity)
                throw new IllegalStateException("There is no enough " + product.getName() + " in stock\n");
        }

    }

    private void extractShippableProducts() {
        for (Map.Entry<Product, Integer> orderItem : orderItems.entrySet()) {
            Product product = orderItem.getKey();
            if (product.requiresShipping()) {
                ShippableOrderItemInfo itemToBeShipped = new ShippableOrderItemInfo(product.getName(), product.getWeight());
                this.itemsToBeShipped.add(itemToBeShipped);
            }
        }
    }

    public double calculateOrderPrice() {
        double totalPrice = 0;
        for (Map.Entry<Product, Integer> orderItem : orderItems.entrySet()) {
            Product product = orderItem.getKey();
            int quantity = orderItem.getValue();
            totalPrice += product.getPrice() * quantity;
        }
        return totalPrice;
    }

    public double calculateOrderWeight() {
        double totalWeight = 0;
        for (Map.Entry<Product, Integer> orderItem : orderItems.entrySet()) {
            Product product = orderItem.getKey();
            if (product.requiresShipping()) {
                int quantity = orderItem.getValue();
                totalWeight += product.getWeight() * quantity;
            }
        }
        return totalWeight;
    }

    public double processShipping() {
        ShippingServices newShipment = new ShippingServices(this.itemsToBeShipped);
        return newShipment.makeShipment();
    }

    public void completeOrder() {
        for (Map.Entry<Product, Integer> orderItem : orderItems.entrySet()) {
            Product product = orderItem.getKey();
            int quantity = orderItem.getValue();
            product.reduceQuanitiy(quantity);
        }
    }

    public void printOrderSummary() {
        // ** Shipment notice **
        System.out.println("** Shipment notice **");
        System.out.printf("%-5s %-20s %10s%n", "Qty", "Product", "Weight (g)");
        System.out.println("---------------------------------------------");

        for (Map.Entry<Product, Integer> orderItem : orderItems.entrySet()) {
            Product product = orderItem.getKey();
            if (product.requiresShipping()) {
                int quantity = orderItem.getValue();
                System.out.printf("%-5d %-20s %10.2f%n",
                        quantity,
                        product.getName(),
                        product.getWeight()
                );
            }
        }
        System.out.printf("%nTotal package weight: %.2f kg%n%n", this.totalWeight / 1000.0);

        // ** Checkout receipt **
        System.out.println("** Checkout receipt **");
        System.out.printf("%-5s %-20s %10s%n", "Qty", "Product", "Unit Price");
        System.out.println("---------------------------------------------");

        for (Map.Entry<Product, Integer> orderItem : orderItems.entrySet()) {
            Product product = orderItem.getKey();
            int quantity = orderItem.getValue();
            System.out.printf("%-5d %-20s %10.2f%n",
                    quantity,
                    product.getName(),
                    product.getPrice()
            );
        }

        System.out.println("---------------------------------------------");
        System.out.printf("%-25s %10.2f%n", "Subtotal", this.productsPrice);
        System.out.printf("%-25s %10.2f%n", "Shipping", this.shippingFee);
        System.out.printf("%-25s %10.2f%n", "Total Amount", this.productsPrice + this.shippingFee);
        System.out.println();

    }

    public double getTotalAmount() {
        return totalAmount;
    }
}
