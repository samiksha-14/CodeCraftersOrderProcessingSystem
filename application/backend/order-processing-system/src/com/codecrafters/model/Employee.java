package com.codecrafters.model;

import java.time.LocalDateTime;

public class Employee {
    // Fields representing the columns in the Employee table
    private int employeeID; // Primary Key and Auto-Incremented
    private String userName;
    private int password;
    private LocalDateTime lastLoggedIn;
    private boolean loggedIn;

    // Constructors

    @Override
    public String toString() {
        return "Employee{" +
                "employeeID=" + employeeID +
                ", userName='" + userName + '\'' +
                ", password=" + password +
                ", lastLoggedIn=" + lastLoggedIn +
                ", loggedIn=" + loggedIn +
                '}';
    }

    // Default constructor
    public Employee() {
    }

    // Constructor for creating an Employee object with initial values
    public Employee(String userName, int password, LocalDateTime lastLoggedIn, boolean loggedIn) {
        this.userName = userName;
        this.password = password;
        this.lastLoggedIn = lastLoggedIn;
        this.loggedIn = loggedIn;
    }

    // Getters and Setters

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getPassword() {
        return password;
    }

    public void setPassword(int password) {
        this.password = password;
    }

    public LocalDateTime getLastLoggedIn() {
        return lastLoggedIn;
    }

    public void setLastLoggedIn(LocalDateTime lastLoggedIn) {
        this.lastLoggedIn = lastLoggedIn;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }
}
