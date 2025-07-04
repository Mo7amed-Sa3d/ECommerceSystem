package com.company;

import java.util.List;

public class ShippingServices {
    List<ShippableOrderItemInfo> itemsToShip;
    private double shippingCostPerKiloGram = 30;

    ShippingServices(List<ShippableOrderItemInfo> itemsToShip) {
        this.itemsToShip = itemsToShip;
    }

    public double makeShipment() {
        ///Create a shipment using an API to the shipping company
        double totalShippingPrice = 0;
        for (ShippableOrderItem item : itemsToShip) {
            System.out.printf("Shipping product %s\n", item.getName());
            totalShippingPrice += item.getWeight() / 1000 * shippingCostPerKiloGram;
        }
        return totalShippingPrice;
    }

    public double setShippingCostPerKiloGram() {
        return shippingCostPerKiloGram;
    }
}
