package com.semantyca.entity;

import java.util.List;

import lombok.Data;

@Data
public class UserPermissions {
    private List<SemantycaRole> roles;
    private List<SemantycaGroup> userGroups;

    public UserPermissions() {

    }
}
