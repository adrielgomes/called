package com.called.partner.manager.repository;

import com.called.user.model.UsuarioModel;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Repository
public class TesteDTO {

    private final EntityManager em;

    public TesteDTO(EntityManager em) {
        this.em = em;
    }


    public UsuarioModel find(UUID uuid) {

        EntityGraph<?> graph = em.getEntityGraph("graph.UsuarioLocalRegraPrivilegios");

        Map<String, Object> hints = new HashMap<>();
        hints.put("javax.persistence.fetchgraph", graph);
        return em.find(UsuarioModel.class, uuid, hints);

    }
}