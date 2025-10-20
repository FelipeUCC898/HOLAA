package com.expenses.app.dto;

import com.expenses.app.model.TipoTransaccion;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;

public class TransaccionDTO {
    
    @Data
    public static class CreateRequest {
        @NotNull
        private TipoTransaccion tipoTransaccion;
        
        @NotNull
        private Integer categoriaId;
        
        @NotBlank
        @Size(min = 2, max = 255)
        private String descripcion;
        
        private LocalDateTime fecha;
        
        @NotNull
        @Positive
        private Double monto;
    }
    
    @Data
    public static class UpdateRequest {
        @NotNull
        private TipoTransaccion tipoTransaccion;
        
        @NotNull
        private Integer categoriaId;
        
        @NotBlank
        @Size(min = 2, max = 255)
        private String descripcion;
        
        private LocalDateTime fecha;
        
        @NotNull
        @Positive
        private Double monto;
    }
    
    @Data
    public static class Response {
        private Integer id;
        private TipoTransaccion tipoTransaccion;
        private String categoriaNombre;
        private Integer categoriaId;
        private String descripcion;
        private LocalDateTime fecha;
        private Double monto;
        private Integer userId;
        private String userApodo;
        
        public Response(Integer id, TipoTransaccion tipoTransaccion, String categoriaNombre, 
                       Integer categoriaId, String descripcion, LocalDateTime fecha, 
                       Double monto, Integer userId, String userApodo) {
            this.id = id;
            this.tipoTransaccion = tipoTransaccion;
            this.categoriaNombre = categoriaNombre;
            this.categoriaId = categoriaId;
            this.descripcion = descripcion;
            this.fecha = fecha;
            this.monto = monto;
            this.userId = userId;
            this.userApodo = userApodo;
        }
    }
}