package com.called.location.service;

import com.called.location.model.LocalModel;
import com.called.location.repository.LocalRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LocalServiceImplantation implements LocalService {

    private final LocalRepository localRepository;

    public LocalServiceImplantation(LocalRepository localRepository) {
        this.localRepository = localRepository;
    }

    @Override
    public Optional<LocalModel> findLocalByNome(String nome) {
        return localRepository.findByNome(nome);
    }

    @Override
    public List<LocalModel> findByOrderByNomeAsc() {
        return localRepository.findByOrderByNomeAsc();
    }

    @Override
    public LocalModel saveLocal(LocalModel localModel) {
        return localRepository.save(localModel);
    }

}
