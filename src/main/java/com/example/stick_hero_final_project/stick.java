package com.example.stick_hero_final_project;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class stick {

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
        length += amount;
        stick.setHeight(length);
        stick.setLayoutY(stick.getLayoutY() - amount);  // Move the stick upward
    }

    public void reset() {
        // Reset the length of the stick
        length = 0;
        stick.setHeight(length);
    }
/*
    public void fallHorizontally(double targetX) {
        // Calculate the fall speed based on the target position
        double fallSpeed = 5; // Adjust the fall speed as needed

        // Move the stick horizontally
        AnimationTimer fallTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                double currentX = stick.getLayoutX();
                double newX = currentX + fallSpeed;
                stick.setLayoutX(newX);

                // Check if the stick reached the target position
                if (newX >= targetX - width) {
                    reset(); // Reset the stick length
                    setFalling(false); // Stop the horizontal falling
                    this.stop(); // Stop the animation timer
                }
            }
        };

        setFalling(true); // Set the stick to start falling horizontally
        fallTimer.start();
    }

*/


}
