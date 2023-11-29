package com.example.stick_hero_final_project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(main.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Stick Hero Ninja !!!");
        stage.setScene(scene);
        stage.setResizable(true);
        stage.setOnCloseRequest(event -> {
            // Perform actions when the user attempts to close the window will cork when press red cross buttomn although not needed
            System.out.println("Closing the application...");

        });
        stage.setWidth(600);
        stage.setHeight(800);
        stage.setMaxWidth(600);
        stage.setMaxHeight(730);
//        stage.lo
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    @Override
    public String toString() {
        return "main{}";
    }
}