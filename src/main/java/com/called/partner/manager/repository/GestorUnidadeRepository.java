package com.called.partner.manager.repository;

import com.called.partner.manager.model.GestorUnidadeModel;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface GestorUnidadeRepository extends JpaRepository<GestorUnidadeModel, UUID> {
    @EntityGraph(value = "graph.GestorUsuarioUnidades", type = EntityGraph.EntityGraphType.LOAD)
    List<GestorUnidadeModel> findAllByOrderByUsuario_Nome();

    @EntityGraph(value = "graph.GestorUsuarioUnidades", type = EntityGraph.EntityGraphType.LOAD)
    Optional<GestorUnidadeModel> findByUsuario_Email(String email);
}
