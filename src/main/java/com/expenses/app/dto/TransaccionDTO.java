package com.expenses.app.dto;

import com.expenses.app.model.TipoTransaccion;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;

public class TransaccionDTO {
    
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
        
        // Getters y Setters
        public TipoTransaccion getTipoTransaccion() { return tipoTransaccion; }
        public void setTipoTransaccion(TipoTransaccion tipoTransaccion) { this.tipoTransaccion = tipoTransaccion; }
        public Integer getCategoriaId() { return categoriaId; }
        public void setCategoriaId(Integer categoriaId) { this.categoriaId = categoriaId; }
        public String getDescripcion() { return descripcion; }
        public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
        public LocalDateTime getFecha() { return fecha; }
        public void setFecha(LocalDateTime fecha) { this.fecha = fecha; }
        public Double getMonto() { return monto; }
        public void setMonto(Double monto) { this.monto = monto; }
    }
    
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
        
        // Getters y Setters
        public TipoTransaccion getTipoTransaccion() { return tipoTransaccion; }
        public void setTipoTransaccion(TipoTransaccion tipoTransaccion) { this.tipoTransaccion = tipoTransaccion; }
        public Integer getCategoriaId() { return categoriaId; }
        public void setCategoriaId(Integer categoriaId) { this.categoriaId = categoriaId; }
        public String getDescripcion() { return descripcion; }
        public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
        public LocalDateTime getFecha() { return fecha; }
        public void setFecha(LocalDateTime fecha) { this.fecha = fecha; }
        public Double getMonto() { return monto; }
        public void setMonto(Double monto) { this.monto = monto; }
    }
    
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
        
        public Response() {}
        
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
        
        // Getters y Setters
        public Integer getId() { return id; }
        public void setId(Integer id) { this.id = id; }
        public TipoTransaccion getTipoTransaccion() { return tipoTransaccion; }
        public void setTipoTransaccion(TipoTransaccion tipoTransaccion) { this.tipoTransaccion = tipoTransaccion; }
        public String getCategoriaNombre() { return categoriaNombre; }
        public void setCategoriaNombre(String categoriaNombre) { this.categoriaNombre = categoriaNombre; }
        public Integer getCategoriaId() { return categoriaId; }
        public void setCategoriaId(Integer categoriaId) { this.categoriaId = categoriaId; }
        public String getDescripcion() { return descripcion; }
        public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
        public LocalDateTime getFecha() { return fecha; }
        public void setFecha(LocalDateTime fecha) { this.fecha = fecha; }
        public Double getMonto() { return monto; }
        public void setMonto(Double monto) { this.monto = monto; }
        public Integer getUserId() { return userId; }
        public void setUserId(Integer userId) { this.userId = userId; }
        public String getUserApodo() { return userApodo; }
        public void setUserApodo(String userApodo) { this.userApodo = userApodo; }
    }
}