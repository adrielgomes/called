package com.called.location.repository;

import com.called.location.model.LocalModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface LocalRepository extends JpaRepository<LocalModel, UUID> {

    Optional<LocalModel> findByNome(String nome);
    List<LocalModel> findByOrderByNomeAsc();
}
