package com.called.rules.service;

import com.called.rules.model.RegraModel;
import com.called.rules.repository.RegraRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RegraServiceImplantation implements RegraService {
    private final RegraRepository regraRepository;

    public RegraServiceImplantation(RegraRepository regraRepository) {
        this.regraRepository = regraRepository;
    }

    @Override
    public Optional<RegraModel> findRegraByNome(String nome) {
        return regraRepository.findByNome(nome);
    }

    @Override
    public List<RegraModel> findRegrasByOrderByNomeAsc() {
        return regraRepository.findAllByOrderByNomeAsc();
    }

    @Override
    public void saveRegra(RegraModel regraModel) {
        regraRepository.save(regraModel);
    }


}
