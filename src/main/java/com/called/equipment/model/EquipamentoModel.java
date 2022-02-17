package com.called.equipment.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.called.unity.model.UnidadeModel;
import com.called.uuid.model.CodigoUUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NamedEntityGraphs({
        @NamedEntityGraph(
                name = "graph.EquipamentoUnidade",
                attributeNodes = {
                        @NamedAttributeNode(value = "unidade"),
                }
        )})

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "equipamentos")
public class EquipamentoModel extends CodigoUUID {

    private String descricao;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JsonBackReference
    private UnidadeModel unidade;
}
