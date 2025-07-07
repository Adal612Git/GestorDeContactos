package com.gestordecontactos;

import com.gestordecontactos.app.AppConfig;
import com.gestordecontactos.service.HelloService;
import com.google.inject.Guice;
import com.google.inject.Injector;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Aplicación principal.
 */
public class App extends Application {

    @Override
    public void start(Stage primaryStage) {
        Injector injector = Guice.createInjector(new AppConfig());
        HelloService service = injector.getInstance(HelloService.class);
        service.sayHello();

        primaryStage.setTitle("Gestor de Contactos");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
