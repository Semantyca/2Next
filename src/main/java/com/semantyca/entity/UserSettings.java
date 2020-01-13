package com.semantyca.entity;

import java.util.ArrayList;
import java.util.List;

public class UserSettings {
    private String login = "anonymous";
    private String userName = "anonymous";
    private Boolean authorised = Boolean.TRUE;
    private List<String> roles = new ArrayList<>();
    private List<String> availableModules = new ArrayList<>();
    private String defaultLang = "ENG";
    private String status = "REGISTERED";
    private List<String > substitues = new ArrayList<>();

    public UserSettings() {

    }

    public String getLogin() {
        return login;
    }

    public void setLogin(final String login) {
        this.login = login;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(final String userName) {
        this.userName = userName;
    }

    public Boolean getAuthorised() {
        return authorised;
    }

    public void setAuthorised(final Boolean authorised) {
        this.authorised = authorised;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(final List<String> roles) {
        this.roles = roles;
    }

    public List<String> getAvailableModules() {
        return availableModules;
    }

    public void setAvailableModules(final List<String> availableModules) {
        this.availableModules = availableModules;
    }

    public String getDefaultLang() {
        return defaultLang;
    }

    public void setDefaultLang(final String defaultLang) {
        this.defaultLang = defaultLang;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(final String status) {
        this.status = status;
    }

    public List<String> getSubstitues() {
        return substitues;
    }

    public void setSubstitues(final List<String> substitues) {
        this.substitues = substitues;
    }
}
