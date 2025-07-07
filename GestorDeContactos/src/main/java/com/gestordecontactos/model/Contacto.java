package com.gestordecontactos.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Modelo de contacto.
 */
public class Contacto {
    private final StringProperty nombre = new SimpleStringProperty();
    private final StringProperty email = new SimpleStringProperty();
    private final StringProperty telefono = new SimpleStringProperty();
    private final ObjectProperty<LocalDate> fechaCumple = new SimpleObjectProperty<>();
    private final List<Etiqueta> etiquetas = new ArrayList<>();

    public StringProperty nombreProperty() { return nombre; }
    public StringProperty emailProperty() { return email; }
    public StringProperty telefonoProperty() { return telefono; }
    public ObjectProperty<LocalDate> fechaCumpleProperty() { return fechaCumple; }
    public List<Etiqueta> getEtiquetas() { return etiquetas; }
}
