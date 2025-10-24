package com.expenses.app.dto;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

public class ReportDTO {
    
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SummaryResponse {
        private Double totalIngresos;
        private Double totalGastos;
        private Double balance;
        private Long totalTransacciones;
    }
    
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CategorySummary {
        private String categoria;
        private Double totalIngresos;
        private Double totalGastos;
        private Double balance;
    }
    
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ByCategoryResponse {
        private List<CategorySummary> resumenPorCategoria;
        private Map<String, Double> gastosPorCategoria;
        private Map<String, Double> ingresosPorCategoria;
    }
}