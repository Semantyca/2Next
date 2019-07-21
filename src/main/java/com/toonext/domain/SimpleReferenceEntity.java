package com.toonext.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.toonext.localization.constants.LanguageCode;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class SimpleReferenceEntity extends AppEntity<UUID> implements ISimpleReferenceEntity {


    protected String name;

    private Map<LanguageCode, String> locName = new HashMap<LanguageCode, String>();


    @Override
    public String getTitle() {
        if (super.getTitle() == null || super.getTitle().isEmpty()) {
            return name;
        }
        return super.getTitle();
    }

    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
        super.setTitle(name);
    }

    @JsonIgnore
    @Override
    public String toString() {
        if (getId() == null) {
            return "id is null, name=" + name;
        }
        UUID id = UUID.fromString(getId().toString());
        return id.toString() + ", name=" + name;
    }

    public Map<LanguageCode, String> getLocName() {
        return locName;
    }

    public void setLocName(Map<LanguageCode, String> locName) {
        this.locName = locName;
    }

    public String getLocName(LanguageCode lang) {
        try {
            String val = locName.get(lang);
            if (val != null && (!val.isEmpty())) {
                return val;
            } else {
                return name;
            }
        } catch (Exception e) {
            return name;
        }
    }

    public void setLocName(String val, LanguageCode languageCode) {
        this.locName.put(languageCode, val);
    }



}
