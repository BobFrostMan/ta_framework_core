package com.foggger.domain.entities.client;

import java.util.NoSuchElementException;

public enum Language {

    EN("English"),
    DU("Dutch"),
    CH("Chinese");

    private String languangeName;

    private Language(String languangeName) {
        this.languangeName = languangeName;
    }

    public String getLanguangeName() {
        return languangeName;
    }

    public static Language getLanguage(String language) {
        for (Language lang : values()) {
            if (lang.languangeName.toLowerCase().contains(language.trim().toLowerCase())) {
                return lang;
            }
        }
        throw new NoSuchElementException("Cannot find Language object for string '" + language + "'");
    }


}
