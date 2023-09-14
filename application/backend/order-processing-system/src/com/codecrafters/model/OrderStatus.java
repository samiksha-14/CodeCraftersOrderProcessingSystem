package com.codecrafters.model;

public class OrderStatus {
    // Fields representing the columns in the OrderStatus table
    private int orderID; // Primary Key
    private String status; // Possible values: Pending, Approved, Completed, Expired

    // Constructors

    // Default constructor
    public OrderStatus() {
    }

    // Constructor for creating an OrderStatus object with initial values
    public OrderStatus(int orderID, String status) {
        this.orderID = orderID;
        this.status = status;
    }

    // Getters and Setters

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // Override the toString() method for debugging purposes
    @Override
    public String toString() {
        return "OrderStatus{" +
                "orderID=" + orderID +
                ", status='" + status + '\'' +
                '}';
    }
}
