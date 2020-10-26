package com.foggger.domain.entities.message;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

public enum Module {

    CHECK_ALL,
    APPOINTMENT,
    BIOMETRIC,
    EDUCATION,
    INCENTIVE,
    JOURNAL,
    MEDICATION,
    MOTIVATION,
    RULES,
    SURVEY;

    //Returns all modules except Check All value
    public static List<Module> getAllModules() {
        return Arrays.asList(APPOINTMENT, BIOMETRIC, EDUCATION, INCENTIVE, JOURNAL, MEDICATION, MOTIVATION, RULES, SURVEY);
    }

    //Returns all modules except Check All value
    public static Module getModule(String shortName) {
        for (Module module : getAllModules()) {
            if (shortName.toUpperCase().substring(0, 2).equals(module.name().substring(0, 2).toUpperCase())) {
                return module;
            }
        }
        throw new NoSuchElementException("Cannot find module by short name: " + shortName);
    }

    //Returns all modules except Check All value
    public static List<Module> getModules(List<String> shortNames) {
        List<Module> res = new ArrayList<>();
        for (String shortName : shortNames) {
            res.add(getModule(shortName));
        }
        return res;
    }
}
