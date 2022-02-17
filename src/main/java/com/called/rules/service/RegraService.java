package com.called.rules.service;

import com.called.rules.model.RegraModel;

import java.util.List;
import java.util.Optional;
//import java.util.UUID;

public interface RegraService {
    Optional<RegraModel> findRegraByNome(String nome);

    List<RegraModel> findRegrasByOrderByNomeAsc();

    void saveRegra(RegraModel regraModel);
//    void deleteRegraByCodigo(UUID codigo);
}
