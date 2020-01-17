package com.semantyca.entity.traits;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Id;

public abstract class Identity implements Serializable {
    @Id
    private String id;

    public Identity() {
        this.id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof Identity)) {
            return false;
        }
        Identity other = (Identity) obj;
        return getId().equals(other.getId());
    }
}
