package com.foggger.core.config;

/**
 * Static class that provides Config object for specified env
 */
public class ConfigProvider {

    private static Config config;

    public static void init(IConfigReader reader, String url) {
        assert reader != null;
        config = reader.readConfig(url);
    }

    public static Config provide() {
        if (config == null) {
            throw new RuntimeException("Invoke init method first, before using provide() method!");
        }
        return config;
    }

}
