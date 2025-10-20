package com.expenses.app.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

public class CategoriaDTO {
    
    @Data
    public static class CreateRequest {
        @NotBlank
        @Size(min = 2, max = 100)
        private String nombre;
    }
    
    @Data
    public static class UpdateRequest {
        @NotBlank
        @Size(min = 2, max = 100)
        private String nombre;
    }
    
    @Data
    public static class Response {
        private Integer id;
        private String nombre;
        private Integer userId;
        private String userApodo;
        
        public Response(Integer id, String nombre, Integer userId, String userApodo) {
            this.id = id;
            this.nombre = nombre;
            this.userId = userId;
            this.userApodo = userApodo;
        }
    }
}