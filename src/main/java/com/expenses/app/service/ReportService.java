package com.expenses.app.service;

import com.expenses.app.dto.ReportDTO;
import com.expenses.app.model.TipoTransaccion;
import com.expenses.app.repository.TransaccionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final TransaccionRepository transaccionRepository;

    public ReportDTO.SummaryResponse getSummary(Integer userId) {
        Double totalIngresos = transaccionRepository.sumMontoByUserIdAndTipoTransaccion(userId,
                TipoTransaccion.INGRESO);
        Double totalGastos = transaccionRepository.sumMontoByUserIdAndTipoTransaccion(userId, TipoTransaccion.GASTO);
        Long totalTransacciones = (long) transaccionRepository.findByUserId(userId).size();

        return new ReportDTO.SummaryResponse(
                totalIngresos != null ? totalIngresos : 0.0,
                totalGastos != null ? totalGastos : 0.0,
                (totalIngresos != null ? totalIngresos : 0.0) - (totalGastos != null ? totalGastos : 0.0),
                totalTransacciones);
    }

    public ReportDTO.ByCategoryResponse getByCategory(Integer userId) {
        List<Object[]> resumenData = transaccionRepository.getResumenPorCategoria(userId);

        List<ReportDTO.CategorySummary> resumenPorCategoria = resumenData.stream()
                .map(data -> new ReportDTO.CategorySummary(
                        (String) data[0],
                        (Double) data[1],
                        (Double) data[2],
                        ((Double) data[1] - (Double) data[2])))
                .collect(Collectors.toList());

        Map<String, Double> gastosPorCategoria = new HashMap<>();
        Map<String, Double> ingresosPorCategoria = new HashMap<>();

        for (Object[] data : resumenData) {
            String categoria = (String) data[0];
            Double ingresos = (Double) data[1];
            Double gastos = (Double) data[2];

            if (ingresos > 0) {
                ingresosPorCategoria.put(categoria, ingresos);
            }
            if (gastos > 0) {
                gastosPorCategoria.put(categoria, gastos);
            }
        }

        return new ReportDTO.ByCategoryResponse(resumenPorCategoria, gastosPorCategoria, ingresosPorCategoria);
    }
}