package com.called.uuid.model;

import lombok.Getter;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.UUID;

@MappedSuperclass
@Getter
public abstract class CodigoUUID {
    @Id
    @Type(type = "pg-uuid")
    @Column(updatable = false, unique = true, nullable = false)
    private final UUID codigo;

    public CodigoUUID() {
        this.codigo = UUID.randomUUID();
    }

    @Override
    public int hashCode() {
        return codigo.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof CodigoUUID)) {
            return false;
        }
        CodigoUUID other = (CodigoUUID) obj;
        return getCodigo().equals(other.getCodigo());
    }
}
