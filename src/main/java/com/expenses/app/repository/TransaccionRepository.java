package com.expenses.app.repository;

import com.expenses.app.model.TipoTransaccion;
import com.expenses.app.model.Transaccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TransaccionRepository extends JpaRepository<Transaccion, Integer> {
    List<Transaccion> findByUserId(Integer userId);

    List<Transaccion> findByUserIdAndTipoTransaccion(Integer userId, TipoTransaccion tipoTransaccion);

    Optional<Transaccion> findByIdAndUserId(Integer id, Integer userId);

    @Query("SELECT COALESCE(SUM(t.monto), 0) FROM Transaccion t WHERE t.user.id = :userId AND t.tipoTransaccion = :tipo")
    Double sumMontoByUserIdAndTipoTransaccion(@Param("userId") Integer userId, @Param("tipo") TipoTransaccion tipo);

    @Query("SELECT t.categoria.nombre, SUM(t.monto) FROM Transaccion t WHERE t.user.id = :userId AND t.tipoTransaccion = :tipo GROUP BY t.categoria.nombre")
    List<Object[]> sumMontoByCategoriaAndTipo(@Param("userId") Integer userId, @Param("tipo") TipoTransaccion tipo);

    @Query("SELECT t.categoria.nombre, " +
            "COALESCE(SUM(CASE WHEN t.tipoTransaccion = 'INGRESO' THEN t.monto ELSE 0 END), 0) as ingresos, " +
            "COALESCE(SUM(CASE WHEN t.tipoTransaccion = 'GASTO' THEN t.monto ELSE 0 END), 0) as gastos " +
            "FROM Transaccion t WHERE t.user.id = :userId " +
            "GROUP BY t.categoria.nombre")
    List<Object[]> getResumenPorCategoria(@Param("userId") Integer userId);
}