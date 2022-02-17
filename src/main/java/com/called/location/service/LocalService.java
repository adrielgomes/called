package com.called.location.service;

import com.called.location.model.LocalModel;

import java.util.List;
import java.util.Optional;

public interface LocalService {
    Optional<LocalModel> findLocalByNome(String nome);

    List<LocalModel> findByOrderByNomeAsc();

    LocalModel saveLocal(LocalModel localModel);

}
