package com.called.order.model;


import com.called.called.model.ChamadoModel;
import com.called.uuid.model.CodigoUUID;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "ordens")
public class OrdemServicoModel extends CodigoUUID {

    private String descricao;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JsonBackReference
    private ChamadoModel chamado;
}
