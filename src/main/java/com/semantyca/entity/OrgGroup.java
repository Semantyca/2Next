package com.semantyca.entity;

import com.semantyca.entity.traits.Identity;

public class OrgGroup extends Identity {
    private String groupName;

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(final String groupName) {
        this.groupName = groupName;
    }
}
