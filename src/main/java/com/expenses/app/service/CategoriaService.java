package com.expenses.app.service;

import com.expenses.app.dto.CategoriaDTO;
import com.expenses.app.model.Categoria;
import com.expenses.app.model.User;
import com.expenses.app.repository.CategoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class CategoriaService {
    
    private final CategoriaRepository categoriaRepository;
    private final UserService userService;
    
    public Categoria createCategoria(CategoriaDTO.CreateRequest request, Integer userId) {
        User user = userService.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        
        if (categoriaRepository.existsByNombreAndUserId(request.getNombre(), userId)) {
            throw new RuntimeException("Ya existe una categoría con este nombre");
        }
        
        Categoria categoria = new Categoria();
        categoria.setNombre(request.getNombre());
        categoria.setUser(user);
        
        return categoriaRepository.save(categoria);
    }
    
    @Transactional(readOnly = true)
    public List<CategoriaDTO.Response> getCategoriasByUser(Integer userId) {
        return categoriaRepository.findByUserId(userId).stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }
    
    public Categoria updateCategoria(Integer id, CategoriaDTO.UpdateRequest request, Integer userId) {
        Categoria categoria = categoriaRepository.findByIdAndUserId(id, userId)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));
        
        if (!categoria.getNombre().equals(request.getNombre()) && 
            categoriaRepository.existsByNombreAndUserId(request.getNombre(), userId)) {
            throw new RuntimeException("Ya existe una categoría con este nombre");
        }
        
        categoria.setNombre(request.getNombre());
        return categoriaRepository.save(categoria);
    }
    
    public void deleteCategoria(Integer id, Integer userId) {
        Categoria categoria = categoriaRepository.findByIdAndUserId(id, userId)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));
        categoriaRepository.delete(categoria);
    }
    
    // MÉTODO CORREGIDO - Maneja la relación lazy correctamente
    public CategoriaDTO.Response toResponse(Categoria categoria) {
        // En lugar de acceder a categoria.getUser().getApodo(), usamos una consulta eficiente
        String userApodo = userService.findById(categoria.getUser().getId())
                .map(User::getApodo)
                .orElse("Usuario no disponible");
        
        return new CategoriaDTO.Response(
            categoria.getId(),
            categoria.getNombre(),
            categoria.getUser().getId(),
            userApodo
        );
    }
    
    public Categoria findByIdAndUserId(Integer id, Integer userId) {
        return categoriaRepository.findByIdAndUserId(id, userId)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));
    }
}