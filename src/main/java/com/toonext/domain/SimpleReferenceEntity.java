package com.toonext.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.toonext.localization.constants.LanguageCode;
import org.jdbi.v3.core.mapper.reflect.ColumnName;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class SimpleReferenceEntity extends AppEntity<UUID> {

    @ColumnName("loc_name")
    private Map<LanguageCode, String> locName = new HashMap<LanguageCode, String>();

    public void setLocName(Map<LanguageCode, String> locName) {
        this.locName = locName;
    }

    public Map<LanguageCode, String> getLocName() {
        return locName;
    }

    @JsonSetter("loc_name")
    public void setLocName(String val, LanguageCode languageCode) {
        this.locName.put(languageCode, val);
    }

    @JsonIgnore
    public boolean isWasRead() {
        return true;
    }


}
