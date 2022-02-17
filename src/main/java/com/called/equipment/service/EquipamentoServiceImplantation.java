package com.called.equipment.service;

import com.called.equipment.model.EquipamentoModel;
import com.called.equipment.repository.EquipamentoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EquipamentoServiceImplantation implements EquipamentoService {

    private final EquipamentoRepository equipamentoRepository;

    public EquipamentoServiceImplantation(EquipamentoRepository equipamentoRepository) {
        this.equipamentoRepository = equipamentoRepository;
    }

    @Override
    public Optional<EquipamentoModel> findByCodigo(String uuid) {
        return equipamentoRepository.findByCodigo(UUID.fromString(uuid));
    }

    @Override
    public List<EquipamentoModel> findEquipamentosByOrderByNome() {
        return equipamentoRepository.findAllByOrderByDescricaoAsc();
    }

    @Override
    public EquipamentoModel saveEquipamento(EquipamentoModel equipamentoModel) {
        return equipamentoRepository.save(equipamentoModel);
    }
}
