package com.called.collaborator.technician.service;

import com.called.collaborator.technician.model.TecnicoModel;

import java.util.List;
import java.util.Optional;

public interface TecnicoService {
    Optional<TecnicoModel> findByTecnicoByEmail(String email);
    List<TecnicoModel> findAllTecnicosByOrderByNomeAsc();
    void saveTecnico(TecnicoModel tecnicoModel);

}
