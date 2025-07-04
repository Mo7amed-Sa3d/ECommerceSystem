package com.company;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Cart {
    private final Map<Product, Integer> items = new HashMap<>();
    Cart(){}
    public void addItem(Product product, int quantity) {
        if (!product.isAvailable(quantity)) {
            String message = product.getName() +  " Product is unavailable";
            throw new IllegalStateException(message);
        }
        items.merge(product, quantity, Integer::sum);
    }

    public Map<Product, Integer> getItems() {
        return Collections.unmodifiableMap(items);
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public void clear() {
        items.clear();
    }

}
