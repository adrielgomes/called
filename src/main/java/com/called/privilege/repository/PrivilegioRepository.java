package com.called.privilege.repository;

import com.called.privilege.model.PrivilegioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PrivilegioRepository extends JpaRepository<PrivilegioModel, UUID> {

    @Query("from PrivilegioModel pri left join fetch pri.regras where pri.nome=?1")
    Optional<PrivilegioModel> findByNome(String nome);

    @Query("from PrivilegioModel pri left join fetch pri.regras")
    List<PrivilegioModel> findByOrderByNomeAsc();
}
