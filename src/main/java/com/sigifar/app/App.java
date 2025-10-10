package com.sigifar.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("/com/sigifar/views/login.fxml"));
        stage.setTitle("SIGIFAR");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String resourcePath) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(resourcePath));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}
