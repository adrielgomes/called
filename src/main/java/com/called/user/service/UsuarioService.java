package com.called.user.service;

import com.called.user.model.UsuarioModel;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {
    Optional<UsuarioModel> findUsuarioByEmail(String email);

    List<UsuarioModel> findByOrderByNomeAsc();

    UsuarioModel saveUsuario(UsuarioModel usuarioModel);
}
