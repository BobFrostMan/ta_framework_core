package com.foggger.core.config;

import com.foggger.core.logger.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Reads properties from property file
 */
public class PropertyFileReader implements IConfigReader {

    @Override
    public Config readConfig(String url) {
        Logger.info("Read config by URL '" + url + "'..");
        Properties props = new Properties();
        try {
            ClassLoader loader = PropertyFileReader.class.getClassLoader();
            try (InputStream is = loader.getResourceAsStream(url)) {
                props.load(is);
            }
        } catch (IOException e) {
            Logger.error("Failed to read property file", e);
            throw new RuntimeException(e);
        }
        Logger.info("Property file read successfully");
        mergePropsWithArguments(props);
        return new Config(props);
    }

    /**
     * Merge properties from command line arguments with provided properties object
     *
     * @param properties - merged properties object
     */
    private void mergePropsWithArguments(Properties properties) {
        List<String> keys = new ArrayList<>();
        keys.addAll(properties.stringPropertyNames());
        for (String key : keys) {
            properties.put(key, System.getProperty(key, properties.getProperty(key)));
        }
    }
}
