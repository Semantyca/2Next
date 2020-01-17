package com.semantyca.entity;

import com.semantyca.entity.traits.Identity;

public class OrgRole extends Identity {
    private String roleName;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(final String roleName) {
        this.roleName = roleName;
    }
}
