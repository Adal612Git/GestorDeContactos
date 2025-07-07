package com.gestordecontactos.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Modelo de etiqueta.
 */
public class Etiqueta {
    private final StringProperty nombre = new SimpleStringProperty();
    private final StringProperty color = new SimpleStringProperty();

    public StringProperty nombreProperty() { return nombre; }
    public StringProperty colorProperty() { return color; }
}
