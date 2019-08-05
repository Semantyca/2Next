package com.toonext.core.resources;

import org.jdbi.v3.core.Jdbi;

public class LanguageResource {
    private Jdbi dbi;



    public LanguageResource(Jdbi jdbi) {
        this.dbi = jdbi;
    }
}
