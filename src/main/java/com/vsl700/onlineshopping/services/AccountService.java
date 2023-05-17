package com.vsl700.onlineshopping.services;

public interface AccountService {
    void login(String username, String password);
    void register(String firstName, String lastName, String username, String email, String password);
}
