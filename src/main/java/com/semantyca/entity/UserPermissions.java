package com.semantyca.entity;

import java.util.List;

public class UserPermissions {
    private List<OrgRole> roles;
    private List<OrgGroup> userGroups;

    public List<OrgRole> getRoles() {
        return roles;
    }

    public void setRoles(final List<OrgRole> roles) {
        this.roles = roles;
    }

    public List<OrgGroup> getUserGroups() {
        return userGroups;
    }

    public void setUserGroups(final List<OrgGroup> userGroups) {
        this.userGroups = userGroups;
    }
}
