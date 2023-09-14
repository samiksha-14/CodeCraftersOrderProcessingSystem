package com.codecrafters.model;

public class Cart {
    // Fields representing the columns in the Cart table
    private int cartID;
    private int orderID; // Foreign Key
    private int productID; // Foreign Key
    private int quantity;

    // Constructors

    // Default constructor
    public Cart() {
    }

    // Constructor for creating a Cart object with initial values
    public Cart(int orderID, int productID, int quantity) {
        this.orderID = orderID;
        this.productID = productID;
        this.quantity = quantity;
    }

    // Getters and Setters for accessing and modifying fields

    public int getCartID() {
        return cartID;
    }

    public void setCartID(int cartID) {
        this.cartID = cartID;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    // Override the toString() method for debugging purposes
    @Override
    public String toString() {
        return "Cart{" +
                "cartID=" + cartID +
                ", orderID=" + orderID +
                ", productID=" + productID +
                ", quantity=" + quantity +
                '}';
    }
}
