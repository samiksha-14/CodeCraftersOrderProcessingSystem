package com.codecrafters.service;

public abstract class BaseCustomerLoginService implements LoginService {
    public abstract boolean login(String name, int password);
}
