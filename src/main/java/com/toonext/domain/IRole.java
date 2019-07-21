package com.toonext.domain;


import com.toonext.localization.constants.LanguageCode;

import java.util.Map;

public interface IRole {

    String getName();

    void setName(String name);

    Map<LanguageCode, String> getLocName();

    void setLocName(Map<LanguageCode, String> locName);

    Map<LanguageCode, String> getLocalizedDescr();


}
