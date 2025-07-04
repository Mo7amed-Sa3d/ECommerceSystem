package com.company;

import java.util.UUID;

public class Customer {
    private final UUID id;
    private final String name;
    private double balance;
    private final Cart cart = new Cart();

    public Customer(String name, double balance) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.balance = balance;
    }

    public void addToCart(Product product, int quantity) {
        cart.addItem(product, quantity);
    }
    private void pay(double amount){
        if(amount > this.balance)
            throw new IllegalStateException("Insufficient balance");
        this.balance -= amount;
    }

    public void checkout() {
        if (cart.isEmpty())
            throw new IllegalStateException("The cart is Empty");
        Order order = new Order(cart.getItems());
        if(order.getTotalAmount() > this.balance)
            throw new IllegalStateException("Insufficient balance");
        order.completeOrder();
        order.printOrderSummary();
        this.pay(order.getTotalAmount());
        System.out.println(this.name + " current balance " + this.balance + " LE");
    }

    public UUID getId() { return id; }
    public String getName() { return name; }
    public double getBalance() { return balance; }
    public Cart getCart() { return cart; }

    void setBalance(Double balance) {
        this.balance = balance;
    }
}
