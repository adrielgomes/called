package com.called.equipment.dto;

import com.called.equipment.model.EquipamentoModel;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EquipamentoConverter {
    public EquipamentoDto modelToDto(EquipamentoModel model) {
        EquipamentoDto dto = new EquipamentoDto();
        dto.setCodigo(model.getCodigo().toString());
        dto.setDescricao(model.getDescricao());
        System.out.println(model.getDescricao());
//        dto.setUnidade(model.getUnidade().getCodigo().toString());
        return dto;
    }

    public List<EquipamentoDto> modelToDto(List<EquipamentoModel> models) {
        return models.stream().map(this::modelToDto).collect(Collectors.toList());
    }

/*    public UsuarioModel dtoToModel(UsuarioDto dto) {
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
*/
}
