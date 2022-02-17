package com.called.called.model;

import com.called.equipment.model.EquipamentoModel;
import com.called.partner.manager.model.GestorUnidadeModel;
import com.called.uuid.model.CodigoUUID;
import com.called.collaborator.technician.model.TecnicoModel;
import com.called.order.model.OrdemServicoModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@NamedEntityGraphs({
        @NamedEntityGraph(
                name = "graph.ChamadoEquipamentoGestorTecnicos",
                attributeNodes = {
                        @NamedAttributeNode(value = "equipamento"),
                        @NamedAttributeNode(value = "gestor", subgraph = "subgraph.gestor"),
                        @NamedAttributeNode(value = "tecnicos", subgraph = "subgraph.tecnicos"),

                },
                subgraphs = {
                        @NamedSubgraph(name = "subgraph.tecnicos",
                                attributeNodes = {
                                        @NamedAttributeNode(value = "usuario"),
//                                        @NamedAttributeNode(value = "usuario", subgraph = "subgraph.usuario.tecnico"),
                                }),
//                        @NamedSubgraph(name = "subgraph.usuario.tecnico",
//                                attributeNodes = {
//                                        @NamedAttributeNode(value = "local"),
//                                        @NamedAttributeNode(value = "regra"),
//                                }),

                        @NamedSubgraph(name = "subgraph.gestor",
                                attributeNodes = {
                                        @NamedAttributeNode(value = "usuario"),
//                                        @NamedAttributeNode(value = "usuario", subgraph = "subgraph.usuario"),
                                        @NamedAttributeNode(value = "unidades"),
                                }),
//                        @NamedSubgraph(name = "subgraph.usuario",
//                                attributeNodes = {
//                                        @NamedAttributeNode(value = "local"),
//                                        @NamedAttributeNode(value = "regra", subgraph = "subgraph.regra"),
//                                }),
//                        @NamedSubgraph(name = "subgraph.regra",
//                                attributeNodes = {
//                                        @NamedAttributeNode(value = "privilegios"),
//                                }),
                })})

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "chamados")
public class ChamadoModel extends CodigoUUID {

    private boolean status;
    private String descricao;
    private Instant dataAbertura;

    @OneToOne(fetch = FetchType.LAZY)
    private EquipamentoModel equipamento;

    @OneToOne(fetch = FetchType.LAZY)
    private GestorUnidadeModel gestor;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<TecnicoModel> tecnicos = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<OrdemServicoModel> ordens = new HashSet<>();

}
