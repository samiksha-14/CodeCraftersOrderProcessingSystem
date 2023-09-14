package com.codecrafters.service;

import com.codecrafters.repository.CustomerRepository;
import com.codecrafters.repository.EmployeeRepository;

public class CustomerLoginService extends BaseCustomerLoginService{

    private CustomerRepository repository = null;

    public CustomerLoginService(CustomerRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean login(int id, int password) {
        int retrievedPassword = repository.retrieveCustomerPassword(id,password);
        return (password == retrievedPassword);
    }

    @Override
    public boolean login(String name, int password) {
        int retrievedPassword = repository.retrieveCustomerPassword(name, password);
        return (password == retrievedPassword);
    }
}
