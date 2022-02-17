package com.called.unity.service;

import com.called.unity.model.UnidadeModel;
import com.called.unity.repository.UnidadeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UnidadeServiceImplementation implements UnidadeService {

    private final UnidadeRepository unidadeRepository;

    public UnidadeServiceImplementation(UnidadeRepository unidadeRepository) {
        this.unidadeRepository = unidadeRepository;
    }

    @Override
    public Optional<UnidadeModel> findUnidadeByNome(String nome) {
        return unidadeRepository.findByNome(nome);
    }

    @Override
    public List<UnidadeModel> findUnidadesByOrderByNomeAsc() {
        return unidadeRepository.findAllByOrderByLocalAsc();
    }


    @Override
    public UnidadeModel saveUnidade(UnidadeModel unidadeModel) {
        return unidadeRepository.save(unidadeModel);
    }

}
