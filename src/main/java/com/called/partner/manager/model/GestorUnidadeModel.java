package com.called.partner.manager.model;

import com.called.user.model.UsuarioModel;
import com.called.unity.model.UnidadeModel;
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
                name = "graph.GestorUsuarioUnidades",
                attributeNodes = {
                        @NamedAttributeNode(value = "usuario", subgraph = "subgraph.usuario"),
                        @NamedAttributeNode(value = "unidades", subgraph = "subgraph.unidades"),
                },
                subgraphs = {
                        @NamedSubgraph(name = "subgraph.unidades",
                                attributeNodes = {
                                        @NamedAttributeNode(value = "equipamentos"),
                                }),
                        @NamedSubgraph(name = "subgraph.usuario",
                                attributeNodes = {
                                        @NamedAttributeNode(value = "local"),
                                        @NamedAttributeNode(value = "regra"),
//                                        @NamedAttributeNode(value = "regra", subgraph = "subgraph.privilegios"),
                                }),
//                        @NamedSubgraph(name = "subgraph.privilegios",
//                                attributeNodes = {
//                                        @NamedAttributeNode(value = "privilegios"),
//                                })
                })})

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "gestores")
public class GestorUnidadeModel extends CodigoUUID {

    @MapsId
    @OneToOne
    private UsuarioModel usuario;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<UnidadeModel> unidades = new HashSet<>();
}
