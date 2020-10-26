package com.foggger.domain.entities.message;

public enum Action {

    TIMED_ALERT("Timed Alert"),
    CONDITIONAL_ALERT("Timed Alert"),
    TIMED_CONDITIONAL_ALERT("Timed Conditional Alert"),
    ESCALATION_ALERT("Escalation Alert");

    private String action;

    private Action(String action) {
        this.action = action;
    }

    public String getAction() {
        return action;
    }
}
