package com.toonext.adapter;


import com.fasterxml.jackson.annotation.JsonSetter;
import com.toonext.constants.LanguageCode;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class SimpleReferenceEntity extends AppEntity<UUID> {

    private Map<LanguageCode, String> locName = new HashMap<LanguageCode, String>();

    @JsonSetter("loc_name")
    public void setLocName(Map<LanguageCode, String> locName) {
        this.locName = locName;
    }


    public Map<LanguageCode, String> getLocName() {
        return locName;
    }

    public void setLocName(String val, LanguageCode languageCode) {
        this.locName.put(languageCode, val);
    }

    public boolean isWasRead() {
        return true;
    }


}
