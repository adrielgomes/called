package com.called.user.dto;

import com.called.user.model.UsuarioModel;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UsuarioConverter {

    public UsuarioDto modelToDto(UsuarioModel model) {
        UsuarioDto dto = new UsuarioDto();
        dto.setNome(model.getNome());
        dto.setEmail(model.getEmail());
//        dto.setRegras(model.getRegras().stream().map(RegraModel::getNome).collect(Collectors.toList()).toString());
//        dto.setLsocal(model.getLocal().getNome());
        return dto;
    }

    public List<UsuarioDto> modelToDto(List<UsuarioModel> models) {
        return models.stream().map(this::modelToDto).collect(Collectors.toList());
    }

//    public UsuarioModel dtoToModel(UsuarioDto dto) {
//        Optional<UsuarioModel> model = usuarioService.findUsuarioByEmail(dto.getEmail());
//        UsuarioModel usuarioModel = null;
//        if(model.isPresent()){
//            usuarioModel=model.get();
//        }
//        return usuarioModel;
//    }
//
//    public List<UsuarioModel> dtoToModel(List<UsuarioDto> dtos) {
//        return dtos.stream().map(this::dtoToModel).collect(Collectors.toList());
//    }
}
