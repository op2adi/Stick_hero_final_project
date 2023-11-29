package com.example.stick_hero_final_project;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class ThinRectangleOnTop extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Create the base rectangle (black)
        Rectangle baseRect = new Rectangle(100, 100, Color.BLACK);

        // Create the thin rectangle to place on top (red)
        Rectangle thinRect = new Rectangle(50, 100, 4, 2); // Specify x, y, width, height
        thinRect.setFill(Color.RED);

        // Create a StackPane to stack the rectangles
        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(baseRect, thinRect);

        Scene scene = new Scene(stackPane, 200, 200);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Thin Rectangle On Top Example");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
