package com.called.user.service;

import com.called.user.repository.UsuarioRepository;
import com.called.user.model.UsuarioModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class UsuarioServiceImplantation implements UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioServiceImplantation(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public Optional<UsuarioModel> findUsuarioByEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    @Override
    public List<UsuarioModel> findByOrderByNomeAsc() {
        return usuarioRepository.findAllByOrderByNome();
    }

    @Override
    @Transactional
    public UsuarioModel saveUsuario(UsuarioModel usuarioModel) {
        return usuarioRepository.save(usuarioModel);
    }


}
