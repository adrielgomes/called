package com.called.privilege.model;

import com.called.rules.model.RegraModel;
import com.called.uuid.model.CodigoUUID;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "privilegios", uniqueConstraints = @UniqueConstraint(columnNames = "nome"))
public class PrivilegioModel extends CodigoUUID {

    private String nome;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "privilegios")
    @Setter(value=AccessLevel.NONE)
    @Getter(value=AccessLevel.NONE)
    private Set<RegraModel> regras = new HashSet<>();
}