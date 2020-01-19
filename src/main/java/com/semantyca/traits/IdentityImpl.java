package com.semantyca.traits;

import java.util.UUID;

public class IdentityImpl implements Identity {
    private String id;

    public IdentityImpl() {
        this.id = UUID.randomUUID().toString();
    }

    @Override public String getId() {
       return this.id;
    }

    @Override public void setId(final String id) {
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
