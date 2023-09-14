package com.codecrafters.repository;

import com.codecrafters.model.Employee;

public interface EmployeeRepository {
    int retrieveEmployeePassword(int id, int password);

    boolean employeeLoginStatus(int id);

    void updateEmployeeLoginStatus(int id, boolean status);

    Employee loadEmployee(int id);
}
