package com.semantyca.entity;
import java.util.UUID;

import javax.inject.Inject;
import javax.inject.Named;

import lombok.Data;

@Data
@Named
public class SemantycaUser {
    private UserSettings userSettings;
    private UserPermissions userPermissions;
    private UserCredentials credits;
    private String userName = "anonymous";
    private String userId;


    @Inject
    public SemantycaUser(UserSettings userSettings, UserPermissions userPermissions, UserCredentials credits) {
        this.userSettings = userSettings;
        this.userPermissions = userPermissions;
        this.credits = credits;
    }

    public SemantycaUser() {
        this.userId = UUID.randomUUID().toString();
    }
}
