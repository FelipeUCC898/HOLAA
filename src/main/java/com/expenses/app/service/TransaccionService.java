package com.expenses.app.service;

import com.expenses.app.dto.TransaccionDTO;
import com.expenses.app.model.Categoria;
import com.expenses.app.model.Transaccion;
import com.expenses.app.model.User;
import com.expenses.app.repository.TransaccionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TransaccionService {

    private final TransaccionRepository transaccionRepository;
    private final UserService userService;
    private final CategoriaService categoriaService;

    public Transaccion createTransaccion(TransaccionDTO.CreateRequest request, Integer userId) {
        User user = userService.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Categoria categoria = categoriaService.findByIdAndUserId(request.getCategoriaId(), userId);

        Transaccion transaccion = new Transaccion();
        transaccion.setTipoTransaccion(request.getTipoTransaccion());
        transaccion.setCategoria(categoria);
        transaccion.setUser(user);
        transaccion.setDescripcion(request.getDescripcion());
        transaccion.setFecha(request.getFecha() != null ? request.getFecha() : java.time.LocalDateTime.now());
        transaccion.setMonto(request.getMonto());

        return transaccionRepository.save(transaccion);
    }

    public List<TransaccionDTO.Response> getTransaccionesByUser(Integer userId) {
        return transaccionRepository.findByUserId(userId).stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public Transaccion updateTransaccion(Integer id, TransaccionDTO.UpdateRequest request, Integer userId) {
        Transaccion transaccion = transaccionRepository.findByIdAndUserId(id, userId)
                .orElseThrow(() -> new RuntimeException("Transacción no encontrada"));

        Categoria categoria = categoriaService.findByIdAndUserId(request.getCategoriaId(), userId);

        transaccion.setTipoTransaccion(request.getTipoTransaccion());
        transaccion.setCategoria(categoria);
        transaccion.setDescripcion(request.getDescripcion());
        transaccion.setFecha(request.getFecha() != null ? request.getFecha() : transaccion.getFecha());
        transaccion.setMonto(request.getMonto());

        return transaccionRepository.save(transaccion);
    }

    public void deleteTransaccion(Integer id, Integer userId) {
        Transaccion transaccion = transaccionRepository.findByIdAndUserId(id, userId)
                .orElseThrow(() -> new RuntimeException("Transacción no encontrada"));
        transaccionRepository.delete(transaccion);
    }

    public TransaccionDTO.Response toResponse(Transaccion transaccion) {
        return new TransaccionDTO.Response(
                transaccion.getId(),
                transaccion.getTipoTransaccion(),
                transaccion.getCategoria().getNombre(),
                transaccion.getCategoria().getId(),
                transaccion.getDescripcion(),
                transaccion.getFecha(),
                transaccion.getMonto(),
                transaccion.getUser().getId(),
                transaccion.getUser().getApodo());
    }
}