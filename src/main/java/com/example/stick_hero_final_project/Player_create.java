package com.example.stick_hero_final_project;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.scene.Scene;
import javafx.application.Platform;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

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
    public void movePlayerOnRotatedStick(stick stick, Player_create player, Scene newScene, Pillar pillar1, Pillar pillar2, Stage newstage,stick_hero sth) throws InterruptedException {
        double stickAngle = stick.getStick().getRotate(); // Get the current rotation angle of the stick
        double startx = player.getNode().getX();
        System.out.println(startx);
        double starty = player.getNode().getY();
        double pillarstartx = pillar1.getPillar().getX();
        double pillarstarty = pillar1.getPillar().getY();
        System.out.println(pillarstartx);
        double endPointX = player.getNode().getX() + stick.getStick().getHeight();
        double endPointY = player.getNode().getY();

        System.out.println("Stick angle: " + stickAngle);
        System.out.println("Start X: " + player.getNode().getX() + " Start Y: " + player.getNode().getY());
        System.out.println("Endpoint X: " + endPointX);
        System.out.println("Endpoint Y: " + endPointY);

        // Create a Timeline for the player's movement
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(player.getNode().xProperty(), player.getNode().getX())),
                new KeyFrame(Duration.seconds((endPointX-startx)/100),
                        new KeyValue(player.getNode().xProperty(), endPointX),
                        new KeyValue(player.getNode().yProperty(), endPointY)
                )
        );


        timeline.play(); // Start the timeline animation
        //Thread.sleep(200);
        timeline.setOnFinished(actionEvent -> {
            CountDownLatch latch = new CountDownLatch(3);
            if (endPointX<pillarstartx-pillar1.getPillar().getWidth()|| endPointX>pillarstartx){
                System.out.println(pillarstartx-pillar1.getPillar().getWidth());
                System.out.println(pillarstartx);
                player.getNode().setY(10000);
//                newScene.
                return;
            };
            // Create a Runnable for player move left
            Platform.runLater(() -> {
                // Animation for moving the player to the left
                TranslateTransition playerTransition = new TranslateTransition(Duration.seconds(1), player.getNode());
                playerTransition.setByX(pillar2.getPillar().getX());
                playerTransition.play();

                 //Animation for moving the stick to the left
                TranslateTransition stickTransition = new TranslateTransition(Duration.seconds(1), stick.getStick());
                stickTransition.setByX(-100000);
                stickTransition.play();

                // Animation for moving pillar1 to the left
                TranslateTransition pillar1Transition = new TranslateTransition(Duration.seconds(1), pillar1.getPillar());
                pillar1Transition.setByX(-pillar1.getPillar().getX());
                pillar1Transition.play();

                // Animation for moving pillar2 to the left
                TranslateTransition pillar2Transition = new TranslateTransition(Duration.seconds(1), pillar2.getPillar());
                pillar2Transition.setByX(-pillar2.getPillar().getX());
                pillar2Transition.play();
                player.getNode().setX(pillar2.getPillar().getX());
                player.getNode().setY(500);
            });


            // Execute the Runnables in separate threads
            //new Thread(playerMoveLeft).start();


            try {
                System.out.println("hi");
                //latch.await();
                System.out.println("HUHHUSDSDSDSD");

                //Thread.sleep(1000);
                if (sth.getSpeed()>2.5 || sth.getStick_speed_fllag()>2){
                    sth.setStick_speed_fllag(0);
                sth.setSpeed(sth.getSpeed()/2);}
                sth.setStick_speed_fllag(sth.getStick_speed_fllag()+1);
                sth.init();
                //newstage.close();
//                newScene.getRoot().getChildren().clear();

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            System.out.println("BUHU");
//            return;
//            new Thread(p)
//            try {
//                // Wait for all tasks to complete before proceeding
////                latch.await();
//                return;
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        });
//        return;




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
    }

    public ImageView getNode() {
        return playerImageView;
    }
    public void playermoveleft(Player_create player){
        player.getNode().setX(0);
        player.getNode().setY(500);
    }
}
