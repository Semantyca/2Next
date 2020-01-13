package com.semantyca.entity;

import javax.inject.Inject;

public class UserSession {

    private UserSettings user;
    private String currentLang = "POR:pt";
    private Integer pageSize = 20;

    @Inject
    public UserSession(UserSettings user) {
        this.user = user;
    }

    public String getCurrentLang() {
        return currentLang;
    }

    public void setCurrentLang(final String currentLang) {
        this.currentLang = currentLang;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(final Integer pageSize) {
        this.pageSize = pageSize;
    }

    public UserSettings getUser() {
        return user;
    }

    public void setUser(final UserSettings user) {
        this.user = user;
    }
}
