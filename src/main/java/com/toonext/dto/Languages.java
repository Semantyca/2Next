package com.toonext.dto;

import com.toonext.constants.LanguageCode;
import com.toonext.core.api.Language;

import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

public class Languages {
    private TreeMap<LanguageCode, String> languages;

    public static Languages valueOf(List<Language> langList) {
        Languages result = new Languages();
        result.languages = new TreeMap<>();

        for (Language lang : langList) {
            result.languages.put(lang.getCode(), lang.getLocName().get(lang.getCode()));
        }

        return result;
    }

    public SortedMap<LanguageCode, String> values() {
        return languages;
    }
}
