package com.called.called.dto;

import com.called.partner.manager.dto.GestorUnidadeDto;
import com.called.collaborator.technician.dto.TecnicoDto;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.util.List;

@Getter
@Setter
public class ChamadoDto {
    private String codigo;
    private String descricao;
    private String unidadeEquipamento;
    private String localEquipamento;
    private String codigoEquipamento;
    private String descricaoEquipamento;
    private ZonedDateTime dataAbertura;
    private GestorUnidadeDto gestor;
    private List<TecnicoDto> tecnicos;
}