package com.invoice.utilities;

import java.util.Properties;

public class PropertiesHandler {

    static Properties properties = null;

    public static String getPropertiesData(String key) {
        return properties.getProperty(key);
    }

    void loadProperties() {
        if (properties == null) {
            properties = new Properties();
        }
        try {
            properties.load(getClass().getResourceAsStream("/com/invoice/utilities/application.properties"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
