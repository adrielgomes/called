package com.called.partner.manager.dto;

import com.called.partner.manager.model.GestorUnidadeModel;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class GestorUnidadeConverter {

    public GestorUnidadeDto modelToDto(GestorUnidadeModel model) {
        GestorUnidadeDto dto = new GestorUnidadeDto();
        dto.setNome(model.getUsuario().getNome());
        dto.setEmail(model.getUsuario().getEmail());
        dto.setLocal(model.getUsuario().getLocal().getNome());
        return dto;
    }

    public List<GestorUnidadeDto> modelToDto(List<GestorUnidadeModel> models) {
        return models.stream().map(this::modelToDto).collect(Collectors.toList());
    }

}
