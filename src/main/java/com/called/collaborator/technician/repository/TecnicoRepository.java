package com.called.collaborator.technician.repository;

import com.called.collaborator.technician.model.TecnicoModel;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TecnicoRepository extends JpaRepository<TecnicoModel, UUID> {

    @EntityGraph(value = "graph.TecnicoUsuario", type = EntityGraph.EntityGraphType.LOAD)
    Optional<TecnicoModel> findByUsuario_Email(String email);

    @EntityGraph(value = "graph.TecnicoUsuario", type = EntityGraph.EntityGraphType.LOAD)
    List<TecnicoModel> findAllByOrderByUsuario_NomeAsc();

}
