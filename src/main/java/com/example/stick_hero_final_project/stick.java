package com.example.stick_hero_final_project;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

import java.util.concurrent.atomic.AtomicBoolean;

public class stick extends Thread{

    private Rectangle stick;
    private double length;

    private double width;
    private boolean falling = false;

    public void setStick(Rectangle stick) {
        this.stick = stick;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    // constructor
    public stick(double x, double y, double width, double length) {
        // Initialize the stick with width and length
        this.length = length;
        this.width = width;
        stick = new Rectangle(width, length, Color.BLACK);
        stick.setLayoutX(x);
        stick.setLayoutY(y - length);  // Adjust the y position based on the length
    }

    public double getLength() {
        return length;
    }

    public double getWidth() {
        return stick.getWidth();
    }


    public Rectangle getStick() {
        return stick;
    }
    public boolean isFalling() {
        return falling;
    }

    public void setFalling(boolean falling) {
        this.falling = falling;
    }

    public void extend(double amount) {
        // Increase the length of the stick and adjust the position

        stick.setLayoutY(stick.getLayoutY() - amount);
        stick.setHeight(stick.getHeight() + amount);
    }

    public void reset() {
        // Reset the length of the stick
        length = 0;
        stick.setHeight(length);
    }
    public int fallHorizontally(stick stick, Player_create player, Pillar pillar, Pane p, Scene newscene) throws InterruptedException {
        p.setDisable(true);
        AtomicBoolean flag_to_check = new AtomicBoolean(false);
        Duration duration = Duration.seconds(0.1); //
        Rotate rotate = new Rotate();
        rotate.setPivotX(stick.getStick().getX() + stick.getLength() / 2); // Pivot X at the center of the stick
        rotate.setPivotY(stick.getStick().getY() + stick.getStick().getHeight()); // Pivot Y at the bottom of the stick
        rotate.setAngle(90); // Rotate the stick from top to bottom (90 degrees)

        // imp line piche ke transforms ko remove krega
        stick.getStick().getTransforms().clear();
        stick.getStick().getTransforms().add(rotate);

        // Create a Timeline for the rotation
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(rotate.angleProperty(), 0)), // Start angle
                new KeyFrame(duration, new KeyValue(rotate.angleProperty(), 90)) // End angle and duration
        );
        timeline.setOnFinished(event -> {
            player.movePlayerOnRotatedStick(stick, player,newscene);
            p.setDisable(false);
            flag_to_check.set(true);
        });

        // Create a separate thread to handle the timeline and start it
        Thread timelineThread = new Thread(() -> {
            timeline.play();
            try {
                sleep(10);// Start the rotation animation

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        timelineThread.start();

        // Wait for the timeline thread to finish
        try {
            timelineThread.join();
            return 1;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return 0;
        }


//        return 1;
    }






}
