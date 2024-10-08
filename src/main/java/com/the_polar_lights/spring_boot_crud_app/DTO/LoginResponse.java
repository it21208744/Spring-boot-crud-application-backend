package com.the_polar_lights.spring_boot_crud_app.DTO;

public class LoginResponse {
    private String message;
    private String email; // You can include the user's email if needed
    private String token; // Placeholder for a future JWT token

    // Constructor
    public LoginResponse(String message, String email, String token) {
        this.message = message;
        this.email = email;
        this.token = token;
    }

    // Getters and Setters
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
