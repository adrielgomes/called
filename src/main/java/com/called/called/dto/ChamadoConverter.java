package com.called.called.dto;


import com.called.called.model.ChamadoModel;
import com.called.called.service.ChamadoService;
import com.called.collaborator.technician.service.TecnicoService;
import com.called.partner.manager.dto.GestorUnidadeConverter;
import com.called.partner.manager.service.GestorUnidadeService;
import com.called.collaborator.technician.dto.TecnicoConverter;
import com.called.equipment.service.EquipamentoService;
import org.springframework.stereotype.Component;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ChamadoConverter {
    private ChamadoModel chamadoModel;
    private final TecnicoConverter tecnicoConverter;
    private final ChamadoService chamadoService;
    private final EquipamentoService equipamentoService;
    private final GestorUnidadeService gestorUnidadeService;
    private final TecnicoService tecnicoService;
    private final GestorUnidadeConverter gestorUnidadeConverter;

    public ChamadoConverter(TecnicoConverter tecnicoConverter, EquipamentoService equipamentoService, GestorUnidadeService gestorUnidadeService, TecnicoService tecnicoService, ChamadoService chamadoService, GestorUnidadeConverter gestorUnidadeConverter) {
        this.tecnicoConverter = tecnicoConverter;
        this.equipamentoService = equipamentoService;
        this.gestorUnidadeService = gestorUnidadeService;
        this.tecnicoService = tecnicoService;
        this.chamadoService = chamadoService;
        this.gestorUnidadeConverter = gestorUnidadeConverter;
    }

    public ChamadoDto modelToDto(ChamadoModel model) {
        ChamadoDto chamadoDto = new ChamadoDto();
        chamadoDto.setCodigo(model.getCodigo().toString());
        chamadoDto.setDescricao(model.getDescricao());
        chamadoDto.setUnidadeEquipamento(model.getEquipamento().getUnidade().getNome());
        chamadoDto.setLocalEquipamento(model.getEquipamento().getUnidade().getLocal().getNome());
        chamadoDto.setCodigoEquipamento(model.getEquipamento().getCodigo().toString());
        chamadoDto.setDescricaoEquipamento(model.getEquipamento().getDescricao());

        chamadoDto.setDataAbertura(ZonedDateTime.ofInstant(model.getDataAbertura(),
                ZoneId.of(model.getEquipamento().getUnidade().getLocal().getZona())));

        chamadoDto.setGestor(gestorUnidadeConverter.modelToDto(model.getGestor()));
        chamadoDto.setTecnicos(tecnicoConverter.modelToDto(new ArrayList<>(model.getTecnicos())));
        return chamadoDto;
    }

    public List<ChamadoDto> modelToDto(List<ChamadoModel> models) {
        return models.stream().map(this::modelToDto).collect(Collectors.toList());
    }

    public ChamadoModel dtoToModel(ChamadoDto chamadoDto) {
        if (chamadoDto.getCodigo() != null) {
            chamadoService.findByCodigo(chamadoDto.getCodigo()).ifPresent(model -> chamadoModel = model);
        } else {
            chamadoModel = new ChamadoModel();
        }
        gestorUnidadeService.findGestorByEmail(chamadoDto.getGestor().getEmail()).ifPresent((gestor) -> equipamentoService.findByCodigo(chamadoDto.getCodigoEquipamento()).ifPresent((equipamento) -> {
            chamadoModel.setEquipamento(equipamento);
            chamadoModel.setGestor(gestor);
            chamadoModel.setDataAbertura(chamadoDto.getDataAbertura().toInstant());
            chamadoModel.setDescricao(chamadoDto.getDescricao());
            chamadoModel.setStatus(false);

            if (chamadoDto.getTecnicos() != null) {
                chamadoModel.getTecnicos().clear();
                chamadoDto.getTecnicos().forEach((tecnicoDto ->
                        tecnicoService.findByTecnicoByEmail(tecnicoDto.getEmail()).ifPresent(tecnicoModel ->
                                chamadoModel.getTecnicos().add(tecnicoModel))));

            }
        }));
        return chamadoModel;
    }

    public List<ChamadoModel> dtoToModel(List<ChamadoDto> dtos) {
        return dtos.stream().map(this::dtoToModel).collect(Collectors.toList());
    }
}
