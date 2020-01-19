package com.semantyca.entity;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class UserSettings {
    private String defaultLang = "ENG";
    private List<String > substites = new ArrayList<>();

    public UserSettings() {

    }
}
