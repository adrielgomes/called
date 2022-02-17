package com.called.equipment.service;

import com.called.equipment.model.EquipamentoModel;

import java.util.List;
import java.util.Optional;

public interface EquipamentoService {
    Optional<EquipamentoModel> findByCodigo(String uuid);
    List<EquipamentoModel> findEquipamentosByOrderByNome();

    EquipamentoModel saveEquipamento(EquipamentoModel equipamentoModel);
}
