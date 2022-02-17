package com.called.rules.model;

import com.called.privilege.model.PrivilegioModel;
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
                name = "graph.RegraPrivilegios",
                attributeNodes = {
                        @NamedAttributeNode(value = "privilegios"),
                })})

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "regras", uniqueConstraints = @UniqueConstraint(columnNames = "nome"))
public class RegraModel extends CodigoUUID {

    private String nome;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<PrivilegioModel> privilegios = new HashSet<>();

}

