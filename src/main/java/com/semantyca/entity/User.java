package com.semantyca.entity;

import javax.inject.Inject;

import com.semantyca.entity.traits.Identity;

public class User extends Identity {
    private UserSettings userSettings;
    private UserPermissions userPermissions;
    private UserCredentials credits;
    private String userName = "anonymous";


    @Inject
    public User(UserSettings userSettings, UserPermissions userPermissions, UserCredentials credits) {
        this.userSettings = userSettings;
        this.userPermissions = userPermissions;
        this.credits = credits;
    }

    public UserSettings getUserSettings() {
        return userSettings;
    }

    public void setUserSettings(final UserSettings userSettings) {
        this.userSettings = userSettings;
    }

    public UserPermissions getUserPermissions() {
        return userPermissions;
    }

    public void setUserPermissions(final UserPermissions userPermissions) {
        this.userPermissions = userPermissions;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(final String userName) {
        this.userName = userName;
    }

    public UserCredentials getCredits() {
        return credits;
    }

    public void setCredits(final UserCredentials credits) {
        this.credits = credits;
    }
}
