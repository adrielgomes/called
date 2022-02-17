package com.called.called.repository;

import com.called.called.model.ChamadoModel;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ChamadoRepository extends JpaRepository<ChamadoModel, UUID> {
    @EntityGraph(value = "graph.ChamadoEquipamentoGestorTecnicos", type = EntityGraph.EntityGraphType.LOAD)
    Optional<ChamadoModel> findByCodigo(UUID uuid);

    @EntityGraph(value = "graph.ChamadoEquipamentoGestorTecnicos", type = EntityGraph.EntityGraphType.LOAD)
    List<ChamadoModel> findAllByOrderByDataAberturaAsc();



}
