package com.called.equipment.repository;

import com.called.equipment.model.EquipamentoModel;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface EquipamentoRepository extends JpaRepository<EquipamentoModel, UUID> {

    @EntityGraph(value = "graph.EquipamentoUnidade", type = EntityGraph.EntityGraphType.LOAD)
    Optional<EquipamentoModel> findByCodigo(UUID codigo);

    @EntityGraph(value = "graph.EquipamentoUnidade", type = EntityGraph.EntityGraphType.LOAD)
    List<EquipamentoModel> findAllByOrderByDescricaoAsc();
}
