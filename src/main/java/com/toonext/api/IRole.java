package com.toonext.api;

import com.toonext.constants.LanguageCode;

import java.util.Map;

public interface IRole {

    String getName();

    void setName(String name);

    Map<LanguageCode, String> getLocName();

    void setLocName(Map<LanguageCode, String> locName);

    Map<LanguageCode, String> getLocalizedDescr();


}
