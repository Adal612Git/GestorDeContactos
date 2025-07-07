package com.gestordecontactos.ui;

import com.gestordecontactos.model.Contacto;
import com.gestordecontactos.service.ContactService;
import com.google.inject.Inject;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ResourceBundle;

public class MainWindowController {
    @FXML
    private TableView<Contacto> tableView;
    @FXML
    private TableColumn<Contacto, String> nameCol;
    @FXML
    private TableColumn<Contacto, String> emailCol;
    @FXML
    private TableColumn<Contacto, String> phoneCol;
    @FXML
    private TableColumn<Contacto, ?> birthdayCol;

    private final ContactService service;
    private final ResourceBundle resources;

    @Inject
    public MainWindowController(ContactService service, ResourceBundle resources) {
        this.service = service;
        this.resources = resources;
    }

    @FXML
    public void initialize() {
        nameCol.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        phoneCol.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        birthdayCol.setCellValueFactory(new PropertyValueFactory<>("fechaCumple"));

        ObservableList<Contacto> list = service.getContacts();
        tableView.setItems(list);
    }

    @FXML
    private void onAdd() throws IOException {
        openForm(new Contacto());
    }

    @FXML
    private void onEdit() throws IOException {
        Contacto selected = tableView.getSelectionModel().getSelectedItem();
        if (selected != null) {
            openForm(selected);
        }
    }

    @FXML
    private void onDelete() {
        Contacto selected = tableView.getSelectionModel().getSelectedItem();
        if (selected != null) {
            service.delete(selected);
        }
    }

    @FXML
    private void onSearch() {
        // Not implemented yet
    }

    private void openForm(Contacto contacto) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ContactForm.fxml"), resources);
        Parent root = loader.load();
        ContactFormController controller = loader.getController();
        controller.setContact(contacto);
        controller.setService(service);

        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(root));
        stage.setTitle(resources.getString("form.title"));
        stage.showAndWait();
    }
}
