package com.gestordecontactos.service;

import com.gestordecontactos.dao.ContactoDAO;
import com.gestordecontactos.model.Contacto;
import com.google.inject.Inject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

/**
 * Service layer for managing contacts.
 */
public class ContactService {
    private final ContactoDAO dao;
    private final ObservableList<Contacto> contacts = FXCollections.observableArrayList();

    @Inject
    public ContactService(ContactoDAO dao) {
        this.dao = dao;
        loadContacts();
    }

    private void loadContacts() {
        List<Contacto> all = dao.findAll();
        contacts.setAll(all);
    }

    public ObservableList<Contacto> getContacts() {
        return contacts;
    }

    public void add(Contacto c) {
        dao.create(c);
        contacts.add(c);
    }

    public void update(Contacto c) {
        dao.update(c);
        // reload to sync
        loadContacts();
    }

    public void delete(Contacto c) {
        dao.delete(c.idProperty().get());
        contacts.remove(c);
    }
}
