package com.codecrafters.model;

public class InvoiceStatus {
    // Fields representing the columns in the InvoiceStatus table
    private int invoiceID; // Primary Key
    private String status; // Possible values: Paid, Unpaid

    // Constructors

    // Default constructor
    public InvoiceStatus() {
    }

    // Constructor for creating an InvoiceStatus object with initial values
    public InvoiceStatus(int invoiceID, String status) {
        this.invoiceID = invoiceID;
        this.status = status;
    }

    // Getters and Setters

    public int getInvoiceID() {
        return invoiceID;
    }

    public void setInvoiceID(int invoiceID) {
        this.invoiceID = invoiceID;
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
        return "InvoiceStatus{" +
                "invoiceID=" + invoiceID +
                ", status='" + status + '\'' +
                '}';
    }
}

