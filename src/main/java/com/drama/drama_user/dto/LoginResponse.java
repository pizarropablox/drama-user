package com.drama.drama_user.dto;

public class LoginResponse {
    private String message;
    private String token;

    // Constructor vacío requerido para serialización/deserialización
    public LoginResponse() {}

    // Constructor para inicializar los campos
    public LoginResponse(String message, String token) {
        this.message = message;
        this.token = token;
    }

    // Getter para message
    public String getMessage() {
        return message;
    }

    // Setter para message
    public void setMessage(String message) {
        this.message = message;
    }

    // Getter para token
    public String getToken() {
        return token;
    }

    // Setter para token
    public void setToken(String token) {
        this.token = token;
    }
}
