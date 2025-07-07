package com.gestordecontactos.ui;

import com.gestordecontactos.model.Contacto;
import com.gestordecontactos.service.ContactService;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.controlsfx.validation.Severity;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;

import java.time.LocalDate;

public class ContactFormController {
    @FXML
    private TextField nameField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField phoneField;
    @FXML
    private DatePicker birthdayPicker;

    private final ValidationSupport validation = new ValidationSupport();

    private ContactService service;
    private Contacto contact;

    @FXML
    public void initialize() {
        validation.registerValidator(nameField, true, Validator.createEmptyValidator("Required", Severity.ERROR));
    }

    public void setService(ContactService service) {
        this.service = service;
    }

    public void setContact(Contacto c) {
        this.contact = c;
        nameField.textProperty().bindBidirectional(c.nombreProperty());
        emailField.textProperty().bindBidirectional(c.emailProperty());
        phoneField.textProperty().bindBidirectional(c.telefonoProperty());
        birthdayPicker.valueProperty().bindBidirectional(c.fechaCumpleProperty());
    }

    @FXML
    private void onSave() {
        if (validation.isInvalid()) {
            return;
        }
        if (contact != null) {
            if (contact.idProperty().get() == 0) {
                service.add(contact);
            } else {
                service.update(contact);
            }
        }
        close();
    }

    @FXML
    private void onCancel() {
        close();
    }

    private void close() {
        Stage stage = (Stage) nameField.getScene().getWindow();
        stage.close();
    }
}
