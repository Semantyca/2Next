package com.semantyca.entity;

import lombok.Data;

@Data
public class SemantycaRole {
    private String id;
    private String roleName;

    public SemantycaRole() {

    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(final String roleName) {
        this.roleName = roleName;
    }

}
