package com.foggger.domain.entities.program;

public enum ProgramAccess {

    PUBLIC("Public"),
    PRIVATE("Private");

    private String val;

    private ProgramAccess(String val) {
        this.val = val;
    }

    public String getValue() {
        return val;
    }
}
