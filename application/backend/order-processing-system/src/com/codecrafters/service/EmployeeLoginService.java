package com.codecrafters.service;

import com.codecrafters.exception.CannotLogoutBeforeLoginEmployeeException;
import com.codecrafters.exception.EmployeeAlreadyLoggedException;
import com.codecrafters.repository.EmployeeRepository;

public class EmployeeLoginService extends BaseEmployeeLoginService {

    private EmployeeRepository repository = null;

    public EmployeeLoginService(EmployeeRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean login(int id, int password) throws EmployeeAlreadyLoggedException {

        if (repository.employeeLoginStatus(id)) {
            throw new EmployeeAlreadyLoggedException("employee already logged, id : " + id + " password" + password);
        }

        int retrievedPassword = repository.retrieveEmployeePassword(id, password);
        if (retrievedPassword == password) {
            repository.updateEmployeeLoginStatus(id, true);
            return true;
        }

        return false;
    }

    @Override
    public boolean logout(int id, int password) {

        if (!repository.employeeLoginStatus(id)) {
            throw new CannotLogoutBeforeLoginEmployeeException("employee has to be logged in before log-out, id : " + id + " password" + password);
        }
        int retrievedPassword = repository.retrieveEmployeePassword(id, password);
        if (retrievedPassword == password) {
            repository.updateEmployeeLoginStatus(id, false);
            return true;
        }

        return false;
    }


}
