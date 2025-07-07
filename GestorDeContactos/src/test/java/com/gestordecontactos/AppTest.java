package com.gestordecontactos;

import com.gestordecontactos.utils.ConfigProperties;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Basic tests for the application configuration.
 */
public class AppTest {

    @Test
    void configLoadsDefaults() {
        ConfigProperties props = new ConfigProperties();
        assertEquals("jdbc:sqlite:contacts.db", props.get("db.url", ""));
    }

    @Test
    void simpleAssertion() {
        assertTrue(true);
    }
}
