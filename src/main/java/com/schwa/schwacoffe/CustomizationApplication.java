package com.schwa.schwacoffe;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class CustomizationApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(CustomizationApplication.class.getResource("customization-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Customization Page");
        stage.setScene(scene);

        //add css
        scene.getStylesheets().add(String.valueOf(CustomizationApplication.class.getResource("customization-style.css")));

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
