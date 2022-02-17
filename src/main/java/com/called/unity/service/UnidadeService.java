package com.called.unity.service;

import com.called.unity.model.UnidadeModel;

import java.util.List;
import java.util.Optional;

public interface UnidadeService {
    Optional<UnidadeModel> findUnidadeByNome(String nome);

    List<UnidadeModel> findUnidadesByOrderByNomeAsc();

    UnidadeModel saveUnidade(UnidadeModel unidadeModel);
}
