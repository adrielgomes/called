package com.called.partner.manager.service;

import com.called.partner.manager.model.GestorUnidadeModel;

import java.util.List;
import java.util.Optional;

public interface GestorUnidadeService {
    List<GestorUnidadeModel> findByOrderByGestoresAsc();
    Optional<GestorUnidadeModel> findGestorByEmail(String email);
    void saveGestor(GestorUnidadeModel gestorUnidadeModel);

}
