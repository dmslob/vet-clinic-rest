package com.dmslob.vetclinic.entity;

import java.io.Serializable;
import java.util.Objects;

public abstract class AbstractEntity implements Serializable {

    public abstract Long getId();

    public abstract void setId(Long id);

    @Override
    public boolean equals(final Object obj) {
        if (obj == null) {
            return false;
        }
        if (!getClass().isAssignableFrom(obj.getClass())) {
            return false;
        }
        final AbstractEntity other = (AbstractEntity) obj;
        return Objects.equals(this.getId(), other.getId());
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + Objects.hashCode(this.getId());
        return hash;
    }
}
