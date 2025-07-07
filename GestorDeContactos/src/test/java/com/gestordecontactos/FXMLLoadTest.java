package com.gestordecontactos;

import javafx.embed.swing.JFXPanel;
import javafx.fxml.FXMLLoader;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Loads FXML files to ensure they are valid and controllers can be instantiated.
 */
public class FXMLLoadTest {

    @BeforeAll
    static void initToolkit() {
        // Initializes JavaFX Toolkit
        new JFXPanel();
    }

    @Test
    void loadMainWindow() throws Exception {
        ResourceBundle bundle = ResourceBundle.getBundle("com.gestordecontactos.i18n.Messages");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MainWindow.fxml"), bundle);
        assertNotNull(loader.load());
    }

    @Test
    void loadContactForm() throws Exception {
        ResourceBundle bundle = ResourceBundle.getBundle("com.gestordecontactos.i18n.Messages");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ContactForm.fxml"), bundle);
        assertNotNull(loader.load());
    }
}
