package com.called.called.service;

import com.called.called.model.ChamadoModel;

import java.util.List;
import java.util.Optional;

public interface ChamadoService {

    Optional<ChamadoModel> findByCodigo(String uuid);
    List<ChamadoModel> findChamadosByOrderByDataAsc();
    ChamadoModel saveChamado(ChamadoModel chamadoModel);
    List<ChamadoModel> saveAllChamado(List<ChamadoModel> chamados);
}
