package com.called.user.repository;

import com.called.user.model.UsuarioModel;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioModel, UUID> {

    @EntityGraph(value = "graph.UsuarioLocalRegraPrivilegios", type = EntityGraph.EntityGraphType.LOAD)
    Optional<UsuarioModel> findByEmail(String email);

    @EntityGraph(value = "graph.UsuarioLocalRegraPrivilegios", type = EntityGraph.EntityGraphType.LOAD)
    List<UsuarioModel> findAllByOrderByNome();
}
