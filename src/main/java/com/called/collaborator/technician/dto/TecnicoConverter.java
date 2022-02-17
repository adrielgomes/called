package com.called.collaborator.technician.dto;

import com.called.collaborator.technician.model.TecnicoModel;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TecnicoConverter {
    public TecnicoDto modelToDto(TecnicoModel model) {
        TecnicoDto dto = new TecnicoDto();
        dto.setNome(model.getUsuario().getNome());
        dto.setEmail(model.getUsuario().getEmail());
        dto.setLocal(model.getUsuario().getLocal().getNome());
        return dto;
    }

    public List<TecnicoDto> modelToDto(List<TecnicoModel> models) {
        return models.stream().map(this::modelToDto).collect(Collectors.toList());
    }
}
