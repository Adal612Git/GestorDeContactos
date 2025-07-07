package com.gestordecontactos.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Utilidad para leer el archivo config.properties.
 */
public class ConfigProperties {
    private final Properties props = new Properties();

    public ConfigProperties() {
        try (InputStream is = getClass().getClassLoader().getResourceAsStream("config.properties")) {
            if (is != null) {
                props.load(is);
            }
        } catch (IOException e) {
            throw new RuntimeException("No se pudo cargar config.properties", e);
        }
    }

    public String get(String key, String defaultValue) {
        return props.getProperty(key, defaultValue);
    }
}
