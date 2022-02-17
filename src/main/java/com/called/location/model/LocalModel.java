package com.called.location.model;

import com.called.uuid.model.CodigoUUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "locais", uniqueConstraints = @UniqueConstraint(columnNames = "nome"))
public class LocalModel extends CodigoUUID {

    private String nome;
    private String zona;

}
