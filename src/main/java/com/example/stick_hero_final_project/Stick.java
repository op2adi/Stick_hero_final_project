package com.example.stick_hero_final_project;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;

public class Stick extends Thread {
    public double getHt() {
        return ht;
    }

    public void setHt(double ht) {
        this.ht = ht;
    }

    private double ht;
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
    public Stick(double x, double y, double width, double length) {
        // Initialize the Stick with width and length
        this.length = length;
        this.width = width;
        stick = new Rectangle(width, length, Color.WHITE);
        stick.setX(x);
        stick.setY(y - length);  // Adjust the y position based on the length'
        ht = 0;
    }
    public Stick nr(){
        return new Stick(0,0,0,0);
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
        // Increase the length of the Stick and adjust the position

        stick.setY(stick.getY() - amount);
        stick.setHeight(stick.getHeight() + amount);
        this.ht = stick.getHeight();
    }

    public void reset() {
        // Reset the length of the Stick
//        this;
    }

    public void fallHorizontally(Stick stick, PlayerCreate player, Pillar pillar, Pane p, Scene newscene, Pillar pillar2, Stage newstage, StickHero sth, FXMLLoader loadei, ImageView cheery) throws InterruptedException {
        p.setDisable(true);
        AtomicBoolean flag_to_check = new AtomicBoolean(false);
        Duration duration = Duration.seconds(0.1); //
        Rotate rotate = new Rotate();
        rotate.setPivotX(stick.getStick().getX() + stick.getLength() / 2); // Pivot X at the center of the Stick
        rotate.setPivotY(stick.getStick().getY() + stick.getHt()); // Pivot Y at the bottom of the Stick
        rotate.setAngle(90); // Rotate the Stick from top to bottom (90 degrees)
//        CountDownLatch latch = new CountDownLatch(1);
        // imp line piche ke transforms ko remove krega
        stick.getStick().getTransforms().clear();
        stick.getStick().getTransforms().add(rotate);
        CountDownLatch latch = new CountDownLatch(1);
        // Create a Timeline for the rotation
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(rotate.angleProperty(), 0)), // Start angle
                new KeyFrame(duration, new KeyValue(rotate.angleProperty(), 90)) // End angle and duration
        );
        timeline.play();
        timeline.setOnFinished(event -> {
            String Stickfallsound = "src/Main/resources/com/example/stick_hero_final_project/Sounds/stick_fallen.wav";
            Media stick_fall_sound =  new Media(new File(Stickfallsound).toURI().toString());
            MediaPlayer m = new MediaPlayer(stick_fall_sound);
            m.play();
            try {
                player.movePlayerOnRotatedStick(stick, player, newscene, pillar, pillar2,newstage,sth,loadei,cheery,p);

                latch.countDown();
                p.setDisable(false);
                flag_to_check.set(true);
                return;
//                return 1;
//                latch.countDown();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
//            while (!flag_to_check.get()) {
//                System.out.println(
//                        "hopoopo"
//                );
//            }

        });
    }
//        return 1;
}
        // Create a separate thread to handle the timeline and start it
//        Thread timelineThread = new Thread(() -> {
//
//            //sleep(10);// Start the rotation animation
//
//        });
////        timelineThread.start();
//
//        // Wait for the timeline thread to finish
//        try {
//            timelineThread.join();
////            latch.await();
//            return 1;
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//            return 0;


//        return 1;







