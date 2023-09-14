package com.codecrafters.service;

import com.codecrafters.model.Employee;
import com.codecrafters.repository.EmployeeRepository;

public class EmployeeDetailsServiceImpl implements EmployeeDetailsService {

    EmployeeRepository repository = null;

    public EmployeeDetailsServiceImpl(EmployeeRepository repository) {
        this.repository = repository;
    }

    @Override
    public Employee fetchEmployeeDetails(int id) {
        return repository.loadEmployee(id);
    }
}
