package com.mindfire.secureapi.core.model;

/**
 * Model for JWT authentication token.
 */
public class AuthToken {

    private String token;

    public AuthToken(String token){
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
