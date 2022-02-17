package com.called.called.service;

import com.called.called.model.ChamadoModel;
import com.called.called.repository.ChamadoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ChamadoServiceImplantation implements ChamadoService {

    private final ChamadoRepository chamadoRepository;

    public ChamadoServiceImplantation(ChamadoRepository chamadoRepository) {
        this.chamadoRepository = chamadoRepository;
    }

    @Override
    public Optional<ChamadoModel> findByCodigo(String uuid) {

        return chamadoRepository.findByCodigo(UUID.fromString(uuid));
    }

    @Override
    public List<ChamadoModel> findChamadosByOrderByDataAsc() {
        return chamadoRepository.findAllByOrderByDataAberturaAsc();
    }

    @Override
    public ChamadoModel saveChamado(ChamadoModel chamadoModel) {
        return chamadoRepository.save(chamadoModel);
    }

    @Override
    public List<ChamadoModel> saveAllChamado(List<ChamadoModel> chamadoModel) {
        return chamadoRepository.saveAll(chamadoModel);
    }


}
