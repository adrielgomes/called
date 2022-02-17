package com.called.collaborator.technician.service;

import com.called.collaborator.technician.model.TecnicoModel;
import com.called.collaborator.technician.repository.TecnicoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TecnicoServiceImplantation implements TecnicoService {

    private final TecnicoRepository tecnicoRepository;

    public TecnicoServiceImplantation(TecnicoRepository tecnicoRepository) {
        this.tecnicoRepository = tecnicoRepository;
    }


    @Override
    public Optional<TecnicoModel> findByTecnicoByEmail(String email) {
        return tecnicoRepository.findByUsuario_Email(email);
    }

    @Override
    public List<TecnicoModel> findAllTecnicosByOrderByNomeAsc() {
        return tecnicoRepository.findAllByOrderByUsuario_NomeAsc();
    }

    @Override
    public void saveTecnico(TecnicoModel tecnicoModel) {
        tecnicoRepository.save(tecnicoModel);
    }

}
