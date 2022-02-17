package com.called.rules.repository;

import com.called.rules.model.RegraModel;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface RegraRepository extends JpaRepository<RegraModel, UUID> {

    @EntityGraph(value = "graph.RegraPrivilegios", type = EntityGraph.EntityGraphType.LOAD)
    Optional<RegraModel> findByNome(String nome);

    @EntityGraph(value = "graph.RegraPrivilegios", type = EntityGraph.EntityGraphType.LOAD)
    List<RegraModel> findAllByOrderByNomeAsc();
}
