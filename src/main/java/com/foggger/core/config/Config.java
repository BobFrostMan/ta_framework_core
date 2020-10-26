package com.foggger.core.config;

import java.util.Properties;

public class Config {

    private Properties properties;

    public Config(Properties properties) {
        this.properties = properties;
    }

    @SuppressWarnings("unchecked")
    public <T> T get(String key) {
        return (T) properties.getProperty(key);
    }

}
