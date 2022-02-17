package com.called.unity.repository;

import com.called.unity.model.UnidadeModel;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UnidadeRepository extends JpaRepository<UnidadeModel, UUID> {

    @EntityGraph(value = "graph.UnidadeLocalEquipamentos", type = EntityGraph.EntityGraphType.LOAD)
    Optional<UnidadeModel> findByNome(String nome);

    @EntityGraph(value = "graph.UnidadeLocalEquipamentos", type = EntityGraph.EntityGraphType.LOAD)
    List<UnidadeModel> findAllByOrderByLocalAsc();


}
