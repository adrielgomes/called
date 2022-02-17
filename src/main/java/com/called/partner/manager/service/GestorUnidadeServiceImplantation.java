package com.called.partner.manager.service;

import com.called.partner.manager.repository.GestorUnidadeRepository;
import com.called.partner.manager.model.GestorUnidadeModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GestorUnidadeServiceImplantation implements GestorUnidadeService {

    private final GestorUnidadeRepository gestorUnidadeRepository;

    public GestorUnidadeServiceImplantation(GestorUnidadeRepository gestorUnidadeRepository) {
        this.gestorUnidadeRepository = gestorUnidadeRepository;
    }


    @Override
    public void saveGestor(GestorUnidadeModel gestorUnidadeModel) {
        gestorUnidadeRepository.save(gestorUnidadeModel);
    }

    @Override
    public List<GestorUnidadeModel> findByOrderByGestoresAsc() {
        return gestorUnidadeRepository.findAllByOrderByUsuario_Nome();
    }

    @Override
    public Optional<GestorUnidadeModel> findGestorByEmail(String email) {
        return gestorUnidadeRepository.findByUsuario_Email(email);
    }
}
