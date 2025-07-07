package com.gestordecontactos;

import com.gestordecontactos.app.AppConfig;
import com.gestordecontactos.service.HelloService;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import java.util.Locale;
import java.util.ResourceBundle;
import com.google.inject.Guice;
import com.google.inject.Injector;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Aplicación principal.
 */
public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Injector injector = Guice.createInjector(new AppConfig());
        HelloService service = injector.getInstance(HelloService.class);
        service.sayHello();

        ResourceBundle bundle = ResourceBundle.getBundle("com.gestordecontactos.i18n.Messages", Locale.getDefault());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MainWindow.fxml"), bundle);
        loader.setControllerFactory(injector::getInstance);
        Parent root = loader.load();

        primaryStage.setTitle(bundle.getString("main.title"));
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
