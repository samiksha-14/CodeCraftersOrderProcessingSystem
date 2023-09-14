package com.codecrafters.repository;

import com.codecrafters.model.Customer;

public interface CustomerRepository {
    int retrieveCustomerPassword(int id, int password);

    int retrieveCustomerPassword(String name, int password);

    boolean checkIfCustomerExists(int id);

    boolean checkIfCustomerExists(String name);

    int getCustomerId(String name);

    void addNewCustomer(Customer customer);
}
