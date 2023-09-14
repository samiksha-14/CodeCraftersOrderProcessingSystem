package com.codecrafters.service;

public abstract class BaseEmployeeLoginService implements LoginService {
    public abstract boolean logout(int id, int password);
}
