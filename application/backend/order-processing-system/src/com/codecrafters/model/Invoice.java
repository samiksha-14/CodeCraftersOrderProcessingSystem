package com.codecrafters.model;

import java.time.LocalDateTime;

public class Invoice {
    // Fields representing the columns in the Invoice table
    private int invoiceID; // Primary Key
    private LocalDateTime invoiceDate; // Use LocalDateTime for DATETIME
    private int orderID; // Foreign Key
    private int customerID; // Foreign Key
    private float totalGSTAmount;
    private float totalInvoiceValue;
    private int typeOfGST; // Integer representation of TypeOfGST

    // Constructors

    // Default constructor
    public Invoice() {
    }

    // Constructor for creating an Invoice object with initial values
    public Invoice(LocalDateTime invoiceDate, int orderID, int customerID, float totalGSTAmount, float totalInvoiceValue, int typeOfGST, String typeOfGSTStr) {
        this.invoiceDate = invoiceDate;
        this.orderID = orderID;
        this.customerID = customerID;
        this.totalGSTAmount = totalGSTAmount;
        this.totalInvoiceValue = totalInvoiceValue;
        this.typeOfGST = typeOfGST;
    }

    public Invoice(int invoiceID, LocalDateTime invoiceDate, int orderID, int customerID, float totalGSTAmount, float totalInvoiceValue, int typeOfGST) {
        this.invoiceID = invoiceID;
        this.invoiceDate = invoiceDate;
        this.orderID = orderID;
        this.customerID = customerID;
        this.totalGSTAmount = totalGSTAmount;
        this.totalInvoiceValue = totalInvoiceValue;
        this.typeOfGST = typeOfGST;
    }

    // Getters and Setters for accessing and modifying fields

    public int getInvoiceID() {
        return invoiceID;
    }

    public void setInvoiceID(int invoiceID) {
        this.invoiceID = invoiceID;
    }

    public LocalDateTime getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(LocalDateTime invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public float getTotalGSTAmount() {
        return totalGSTAmount;
    }

    public void setTotalGSTAmount(float totalGSTAmount) {
        this.totalGSTAmount = totalGSTAmount;
    }

    public float getTotalInvoiceValue() {
        return totalInvoiceValue;
    }

    public void setTotalInvoiceValue(float totalInvoiceValue) {
        this.totalInvoiceValue = totalInvoiceValue;
    }

    public int getTypeOfGST() {
        return typeOfGST;
    }

    public void setTypeOfGST(int typeOfGST) {
        this.typeOfGST = typeOfGST;
    }

    // Override the toString() method for debugging purposes
    @Override
    public String toString() {
        return "Invoice{" +
                "invoiceID=" + invoiceID +
                ", invoiceDate=" + invoiceDate +
                ", orderID=" + orderID +
                ", customerID=" + customerID +
                ", totalGSTAmount=" + totalGSTAmount +
                ", totalInvoiceValue=" + totalInvoiceValue +
                ", typeOfGST=" + typeOfGST +
                '}';
    }
}

