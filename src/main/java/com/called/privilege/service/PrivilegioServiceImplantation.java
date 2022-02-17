package com.called.privilege.service;

import com.called.privilege.model.PrivilegioModel;
import com.called.privilege.repository.PrivilegioRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PrivilegioServiceImplantation implements PrivilegioService {

    private final PrivilegioRepository privilegioRepository;

    public PrivilegioServiceImplantation(PrivilegioRepository privilegioRepository) {
        this.privilegioRepository = privilegioRepository;
    }

    @Override
    public Optional<PrivilegioModel> findPrivilegioByNome(String nome) {
        return privilegioRepository.findByNome(nome);
    }


    @Override
    public void savePrivilegio(PrivilegioModel privilegioModel) {
        privilegioRepository.save(privilegioModel);
    }
}
