package com.called.unity.model;

import com.called.equipment.model.EquipamentoModel;
import com.called.location.model.LocalModel;
import com.called.uuid.model.CodigoUUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@NamedEntityGraphs({
        @NamedEntityGraph(
                name = "graph.UnidadeLocalEquipamentos",
                attributeNodes = {
                        @NamedAttributeNode(value = "local"),
                        @NamedAttributeNode(value = "equipamentos"),
                }
        )})

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "unidades", uniqueConstraints = @UniqueConstraint(columnNames = "nome"))
public class UnidadeModel extends CodigoUUID {

    private String nome;

    @OneToOne(fetch = FetchType.LAZY)
    private LocalModel local;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<EquipamentoModel> equipamentos = new HashSet<>();

}
