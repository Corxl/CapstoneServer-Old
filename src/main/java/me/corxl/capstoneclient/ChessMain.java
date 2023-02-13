package me.corxl.capstoneclient;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ChessMain extends Application {

    private static final String VERSION = "1.0.0";

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ChessMain.class.getResource("main-window.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 750, 800);
        stage.setTitle("Chess " + VERSION);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}