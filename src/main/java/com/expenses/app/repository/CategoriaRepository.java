package com.expenses.app.repository;

import com.expenses.app.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
    List<Categoria> findByUserId(Integer userId);

    Optional<Categoria> findByIdAndUserId(Integer id, Integer userId);

    Boolean existsByNombreAndUserId(String nombre, Integer userId);
}