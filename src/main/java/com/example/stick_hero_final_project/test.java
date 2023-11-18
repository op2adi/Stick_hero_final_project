package com.example.stick_hero_final_project;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

public class test extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Create a rectangle
        double width = 100;
        double height = 50;
        Rectangle rectangle = new Rectangle(width, height, Color.BLUE);

        // Set the pivot point to the bottom center of the rectangle
        rectangle.setTranslateX(150); // TranslateX to position the rectangle
        rectangle.setTranslateY(75); // TranslateY to position the rectangle

        // Apply rotation to the rectangle from the bottom center
        Rotate rotate = new Rotate();
        rotate.setAngle(45); // Set the angle of rotation (in degrees)
        rotate.setPivotX(rectangle.getX() + width / 2); // Set pivot X to the center
        rotate.setPivotY(rectangle.getY() + height); // Set pivot Y to the bottom edge
        rectangle.getTransforms().add(rotate); // Apply rotation to the rectangle

        StackPane root = new StackPane(rectangle);
        Scene scene = new Scene(root, 300, 200);

        primaryStage.setTitle("Rectangle Rotation Example");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
