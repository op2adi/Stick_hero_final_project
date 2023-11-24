package com.example.stick_hero_final_project;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class StickFigureDrawing extends Application {

    private final int canvasWidth = 400;
    private final int canvasHeight = 400;
    private final int platformHeight = 50;
    private final int stickLength = 100;
    private final int stickWidth = 2;

    @Override
    public void start(Stage primaryStage) {
        Canvas canvas = new Canvas(canvasWidth, canvasHeight);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        drawStickFigure(gc);

        StackPane root = new StackPane(canvas);
        Scene scene = new Scene(root, canvasWidth, canvasHeight);
        primaryStage.setTitle("Stick Figure Drawing");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void drawStickFigure(GraphicsContext gc) {
        gc.translate(200, canvasHeight - platformHeight);
        gc.rotate(45); // Rotation in degrees

        // Draw stick
        gc.beginPath();
        gc.setLineWidth(stickWidth);
        gc.moveTo(0, 0);
        gc.lineTo(0, -stickLength);
        gc.stroke();

        // Restore transformations
        gc.restore();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
