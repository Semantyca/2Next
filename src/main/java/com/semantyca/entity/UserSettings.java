package com.semantyca.entity;

import java.util.ArrayList;
import java.util.List;

public class UserSettings {
    private String defaultLang = "ENG";
    private List<String > substites = new ArrayList<>();

    public UserSettings() {

    }

    public String getDefaultLang() {
        return defaultLang;
    }

    public void setDefaultLang(final String defaultLang) {
        this.defaultLang = defaultLang;
    }

    public List<String> getSubstitutes() {
        return substites;
    }

    public void setSubstitutes(final List<String> substitues) {
        this.substites = substitues;
    }
}
