package com.codecrafters.model;

public class OrderInfoCustomer extends OrderInfoBase{
    private double shippingCost;
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public OrderInfoCustomer(int orderID, String orderDate, float totalOrderValue, double shippingCost, String status) {
        super(orderID, orderDate, totalOrderValue);
        this.shippingCost = shippingCost;
        this.status = status;
    }

    public OrderInfoCustomer(int orderID, String orderDate, float totalOrderValue, double shippingCost) {
        super(orderID, orderDate, totalOrderValue);
        this.shippingCost = shippingCost;
    }

    public double getShippingCost() {
        return shippingCost;
    }

    public void setShippingCost(double shippingCost) {
        this.shippingCost = shippingCost;
    }

    @Override
    public String toString() {
        return super.toString() + "OrderInfoCustomer{" +
                "shippingCost=" + shippingCost +
                '}';
    }
}
