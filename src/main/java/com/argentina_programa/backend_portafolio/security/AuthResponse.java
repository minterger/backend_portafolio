package com.argentina_programa.backend_portafolio.security;

public class AuthResponse {
    private String email;
    private String accessToken;

    public AuthResponse(String email, String accessToken) {
        this.email = email;
        this.accessToken = accessToken;
    }

    public AuthResponse() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
