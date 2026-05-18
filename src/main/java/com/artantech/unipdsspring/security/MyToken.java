package com.artantech.unipdsspring.security;

public class MyToken {
    private String token;

    public MyToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
