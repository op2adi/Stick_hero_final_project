package com.example.stick_hero_final_project;

import javafx.animation.*;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;

import java.util.*;

public class Player_create {
    private ImageView playerImageView;

    public Player_create(double x, double y, double width, double height) {
        // Load the stick hero image
        Image playerImage = new Image("D:\\Stick_Hero_Final_Project\\src\\main\\java\\com\\example\\stick_hero_final_project\\Images\\stick_hero_main-removebg-preview.png");

        // Create an ImageView with the stick hero image
        playerImageView = new ImageView(playerImage);
        playerImageView.setX(x);
        playerImageView.setY(y);
        playerImageView.setFitWidth(40);
        playerImageView.setFitHeight(40);
    }
    public void movePlayerOnRotatedStick(stick stick, Player_create player, Scene newScene) {
        double stickAngle = stick.getStick().getRotate(); // Get the current rotation angle of the stick

        double endPointX = player.getNode().getX() + stick.getStick().getHeight();
        double endPointY = player.getNode().getY();

        System.out.println("Stick angle: " + stickAngle);
        System.out.println("Start X: " + player.getNode().getX() + " Start Y: " + player.getNode().getY());
        System.out.println("Endpoint X: " + endPointX);
        System.out.println("Endpoint Y: " + endPointY);

        // Create a Timeline for the player's movement
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(player.getNode().xProperty(), player.getNode().getX())),
                new KeyFrame(Duration.seconds(0.5),
                        new KeyValue(player.getNode().xProperty(), endPointX),
                        new KeyValue(player.getNode().yProperty(), endPointY)
                )
        );

        timeline.play(); // Start the timeline animation

//        Path path = new Path();
//        path.getElements().add(new MoveTo(player.getNode().getX(), player.getNode().getY()));
//        path.getElements().add(new LineTo(endPointX, endPointY));
//
//        double playerSpeed = 500;
//        double distance = Math.sqrt(Math.pow(endPointX - player.getNode().getX(), 2) + Math.pow(endPointY - player.getNode().getY(), 2));
//        Duration duration = Duration.seconds(0.5);
//
//        System.out.println("Distance: " + distance);
//        System.out.println("Duration: " + duration);
        newScene.addEventFilter(MouseEvent.MOUSE_PRESSED, event -> {
            //do nothing
        });
        newScene.addEventFilter(MouseEvent.MOUSE_RELEASED, event -> {
            //do nothing
        });

//        PathTransition pathTransition = new PathTransition();
//        pathTransition.setDuration(duration);
//        pathTransition.setNode(player.getNode());
//        pathTransition.setPath(path);
//        pathTransition.play();
    }

    public ImageView getNode() {
        return playerImageView;
    }
}
