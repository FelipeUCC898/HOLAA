package com.expenses.app.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

public class AuthDTO {
    
    @Data
    public static class LoginRequest {
        @NotBlank
        @Email
        private String correo;
        
        @NotBlank
        @Size(min = 6)
        private String contraseña;
    }
    
    @Data
    public static class RegisterRequest {
        @NotBlank
        @Size(min = 2, max = 50)
        private String apodo;
        
        @NotBlank
        @Email
        private String correo;
        
        @NotBlank
        @Size(min = 6)
        private String contraseña;
    }
    
    @Data
    public static class AuthResponse {
        private String token;
        private String type = "Bearer";
        private UserResponse user;
        
        public AuthResponse(String token, UserResponse user) {
            this.token = token;
            this.user = user;
        }
    }
    
    @Data
    public static class UserResponse {
        private Integer id;
        private String apodo;
        private String correo;
        
        public UserResponse(Integer id, String apodo, String correo) {
            this.id = id;
            this.apodo = apodo;
            this.correo = correo;
        }
    }
}