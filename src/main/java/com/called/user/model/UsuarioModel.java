package com.called.user.model;

import com.called.location.model.LocalModel;
import com.called.rules.model.RegraModel;
import com.called.uuid.model.CodigoUUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@NamedEntityGraphs({
        @NamedEntityGraph(
                name = "graph.UsuarioLocalRegraPrivilegios",
                attributeNodes = {
                        @NamedAttributeNode(value = "local"),
                        @NamedAttributeNode(value = "regra", subgraph = "subgraph.regra"),
                },
                subgraphs = {
                        @NamedSubgraph(name = "subgraph.regra",
                                attributeNodes = @NamedAttributeNode(value = "privilegios")),
                })})

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "usuarios", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class UsuarioModel extends CodigoUUID {

    private String nome;
    private String email;
    private String senha;

    @OneToOne(fetch = FetchType.LAZY)
    private LocalModel local;

    @OneToOne(fetch = FetchType.LAZY)
    private RegraModel regra;

}

