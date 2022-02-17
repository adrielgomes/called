package com.called.collaborator.technician.model;

import com.called.called.model.ChamadoModel;
import com.called.user.model.UsuarioModel;
import com.called.uuid.model.CodigoUUID;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@NamedEntityGraphs({
        @NamedEntityGraph(
                name = "graph.TecnicoUsuario",
                attributeNodes = {
                        @NamedAttributeNode(value = "usuario", subgraph = "subgraph.usuario"),
                },
                subgraphs = {
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
@Table(name = "tecnicos")
public class TecnicoModel extends CodigoUUID {

    @OneToOne
    @MapsId
    private UsuarioModel usuario;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "tecnicos")
    @Setter(value = AccessLevel.NONE)
    @Getter(value = AccessLevel.NONE)
    private Set<ChamadoModel> chamados = new HashSet<>();


}
