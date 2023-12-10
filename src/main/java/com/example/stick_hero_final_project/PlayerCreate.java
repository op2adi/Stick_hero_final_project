package com.example.stick_hero_final_project;

//import com.sun.javafx.tk.TKStage;
import javafx.animation.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.application.Platform;

import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.*;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

public class PlayerCreate{
    private int check_fall_flag = 0;
    private ImageView playerImageView;
    private int owl = 1;
    public void Walk(){
        String button_sound  = "src/Main/resources/com/example/stick_hero_final_project/Sounds/kick.wav";
        Media sound_button = new Media(new File(button_sound).toURI().toString());
        MediaPlayer m = new MediaPlayer(sound_button);
        m.setVolume(1);
        m.play();
    }
    public PlayerCreate(double x, double y, double width, double height) {
        //super();
        // Load the Stick hero image
        String imageUrl = ("src/Main/resources/com/example/stick_hero_final_project/Images/stick_hero_main-removebg-preview.png");
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(imageUrl);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        Image playerImage = new Image(inputStream);
        //ImageView imageView = new ImageView();
        // Create an ImageView with the Stick hero image
        playerImageView = new ImageView(playerImage);
        playerImageView.setX(x);
        playerImageView.setY(y);
        playerImageView.setFitWidth(40);
        playerImageView.setFitHeight(40);
    }

    public int getCheck_fall_flag() {
        return check_fall_flag;
    }
    private boolean Collisiondetected = false;
    public void setCheck_fall_flag(int check_fall_flag) {
        this.check_fall_flag = check_fall_flag;
    }

    public ImageView getPlayerImageView() {
        return playerImageView;
    }

    public void setPlayerImageView(ImageView playerImageView) {
        this.playerImageView = playerImageView;
    }

    public void movePlayerOnRotatedStick(Stick stick, PlayerCreate player, Scene newScene, Pillar pillar1, Pillar pillar2, Stage newstage, StickHero sth, FXMLLoader curr_loader, ImageView cherry, Pane rot)  throws InterruptedException {
        check_fall_flag = 0;
        double stickAngle = stick.getStick().getRotate(); // Get the current rotation angle of the Stick
        double startx = player.getNode().getX();
        System.out.println(startx);
        double starty = player.getNode().getY();
        double pillarstartx = pillar1.getPillar().getX();
        double pillarstarty = pillar1.getPillar().getY();
        System.out.println(pillarstartx);
        double endPointX = player.getNode().getTranslateX()+40+stick.getHt();
//        double endpt = Stick.getStick().getX();
//        System.out.println(endpt);
        double endPointY = player.getNode().getY();

        System.out.println("Stick angle: " + stickAngle);
        System.out.println("Start X: " + player.getNode().getX() + " Start Y: " + player.getNode().getY());
        System.out.println("Endpoint X: " + endPointX);
        System.out.println("Endpoint Y: " + endPointY);
        System.out.println("Lal"+(pillar1.getPillar().getX()+pillar1.getPillar().getWidth()/2));
        System.out.println("LAL_end"+(pillar1.getPillar().getX()+pillar1.getPillar().getWidth()/2+5));
        System.out.println("Lql"+pillar1.getPillar().getX());
        System.out.println(pillarstartx + pillar1.getPillar().getWidth());
//        System.out.println(pillarstartx - pillar1.getWidth());
        System.out.println("new end = "+(player.getNode().getTranslateX()+40+stick.getHt()));
        System.out.println(pillarstartx);
        System.out.println(pillar1.getPillar().getLayoutX());
        System.out.println("Height"+pillar1.getHeight());

        Timeline timeline = new Timeline();
        Timeline timeline78 = new Timeline();
        Timeline timeline80 = new Timeline();
        Timeline timeline79 = new Timeline();
// Assuming 'startX' and 'startY' are defined somewhere
        double startX = 0;
        double startY = player.getNode().getY();

// Create a KeyFrame to check the condition every 0.1 second
        KeyFrame conditionCheckFrame = new KeyFrame(Duration.seconds(0.0001), event -> {
            try {
                if (boundcheck(cherry,sth)){
//                    String perectionSound = "src/Main/resources/com/example/stick_hero_final_project/Sounds/victory.wav";
//                    Media sound_button = new Media(new File(perectionSound).toURI().toString());
//                    MediaPlayer m = new MediaPlayer(sound_button);
//                    m.play();
                };
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        KeyFrame soundcheck = new KeyFrame(Duration.seconds(0.2),event -> {
            Walk();
        });


// Add the KeyFrame to the Timeline
        timeline78.getKeyFrames().add(conditionCheckFrame);
        timeline80.getKeyFrames().add(soundcheck);
        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(2), player.getNode());
        translateTransition.setToX(endPointX-player.getNode().getFitWidth()/2); // Move the player node by endPointX
        KeyFrame conditionCheckFrame2 = new KeyFrame(Duration.seconds(0.0001), event -> {
            if (Rectangle_collison(pillar1,pillar2,sth)){
                Collisiondetected = true;
            };
            if (Collisiondetected){
                Collisiondetected = false;
                //endPointX = 50000;
                translateTransition.stop();
                translateTransition.getOnFinished().handle(null);
            }
        });
        timeline79.getKeyFrames().add(conditionCheckFrame2);
        timeline80.setCycleCount(Animation.INDEFINITE);
        translateTransition.play();

        timeline78.setCycleCount(Animation.INDEFINITE);
        timeline79.setCycleCount(Animation.INDEFINITE);
// Play the Timeline
        timeline78.play();
        timeline79.play();
        timeline80.play();
//        timeline.play();
        //Thread.sleep(200);
        translateTransition.setOnFinished(actionEvent -> {
            System.out.println("player kaha hai"+player.getNode().getTranslateX());
            timeline78.stop();
            timeline79.stop();
            timeline80.stop();
            cherry_set(sth);
            CountDownLatch latch = new CountDownLatch(3);
            if (endPointX < pillarstartx  || endPointX > pillarstartx+pillar1.getPillar().getWidth() || sth.getPostion_face()==1 || Collisiondetected) {
                sth.timeline1 = null;
//                showAlert("+1");
//                StickHero controller1  = curr_loader.getController();
//
//                Label scoreLabel1 = controller1.getView();
//                scoreLabel1.setText("GAMEOVER");
//                scoreLabel1.setStyle("-fx-font-size: 40px; -fx-font-weight: bold;");
////                Label high_score = controller.high_score_tell;
////                high_score.setStyle("-fx-font-size: 40px; -fx-font-weight: bold;");
//                scoreLabel1.setVisible(true);

                System.out.println(pillarstartx - pillar1.getPillar().getWidth());
                System.out.println(pillarstartx - pillar1.getWidth());
                System.out.println(pillarstartx);
                check_fall_flag = 1;
                Rotate rotate = new Rotate();
                rotate.setPivotX(stick.getStick().getX() + stick.getLength() / 2); // Pivot X at the center of the Stick
                rotate.setPivotY(stick.getStick().getY() + stick.getStick().getHeight()); // Pivot Y at the bottom of the Stick
                rotate.setAngle(180);
                stick.getStick().getTransforms().clear();
                stick.getStick().getTransforms().add(rotate);
                //CountDownLatch latch = new CountDownLatch(1);
                // Create a Timeline for the rotation
                Duration duration = Duration.seconds(0.1);
                Timeline timeline67 = new Timeline(
                        new KeyFrame(Duration.ZERO, new KeyValue(rotate.angleProperty(), 90)), // Start angle
                        new KeyFrame(duration, new KeyValue(rotate.angleProperty(), 180)) // End angle and duration
                );
                timeline67.play();
                //player.getNode().setY(10000); debug

                // Creating a TranslateTransition for the player
                TranslateTransition playerTransition = new TranslateTransition(Duration.seconds(1), player.getNode());
                playerTransition.setByY(600); // Move the player's node by 600 game over
                // Setting up a timeline to control the transition

                Timeline timeline1 = new Timeline(
                        new KeyFrame(Duration.ZERO, new KeyValue(player.getNode().opacityProperty(), 1.0)),
                        new KeyFrame(Duration.seconds(1), new KeyValue(player.getNode().opacityProperty(), 0.5)),
                        new KeyFrame(Duration.seconds(2), new KeyValue(player.getNode().opacityProperty(), 0.8)),
                        new KeyFrame(Duration.seconds(3), new KeyValue(player.getNode().opacityProperty(), 1.0))
                );

                // Play both the transition and the timeline in parallel
                playerTransition.play();
                timeline1.play();
                String fallSound = "src/Main/resources/com/example/stick_hero_final_project/Sounds/dead.wav";
                Media sound_button = new Media(new File(fallSound).toURI().toString());
                MediaPlayer m = new MediaPlayer(sound_button);
                m.play();
                // Add an event handler to perform an action after the animation completes
                playerTransition.setOnFinished(event -> {
                    //newstage.close();
                    FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Gameover.fxml"));
                    Scene scene = null;
                    //scoreLabel1.setVisible(false);

                    try {
                        Parent root = fxmlLoader.load();
                        scene = new Scene(root, 320, 240);
                        GameOver controller = fxmlLoader.getController();
                        // Access the Label and set its text
                        //Label scoreLabel = controller.score_tell;
                        controller.getScore_tell().setText("Your Score "+String.valueOf(sth.getScore_view()));
                        controller.getScore_tell().setStyle("-fx-font-size: 40px; -fx-font-weight: bold;");
                        Label high_score = controller.getHigh_score_tell();
                        high_score.setStyle("-fx-font-size: 40px; -fx-font-weight: bold;");
                        controller.getRestart().setStyle("-fx-background-color: #3498db; -fx-text-fill: #ffffff; -fx-padding: 10px 20px; -fx-border-color: transparent; -fx-border-radius: 5px;");
                        controller.getExit_from_game().setStyle("-fx-background-color: #3498db; -fx-text-fill: #ffffff; -fx-padding: 10px 20px; -fx-border-color: transparent; -fx-border-radius: 5px;");
                        int highScore = 0;
                        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("back_end_data.txt"))) {
                            outputStream.writeObject(sth.getScore());
                            outputStream.writeObject(sth.getCherries());

                        }
                        catch (Exception e){
                            e.printStackTrace();
                        }
                        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("meta_data.txt"))) {
                            Integer previousScore = (Integer) inputStream.readObject();
                            if (previousScore != null) {
                                highScore = previousScore;
                                System.out.println("High score read from file: " + highScore);
                            } else {
                                System.out.println("Invalid data found in the file.");
                            }
                        } catch (FileNotFoundException e) {
                            System.out.println("File not found. No high score recorded yet.");
                        } catch (IOException | ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                        catch (Exception e){
                            highScore = 0;
                        }

                        high_score.setText("High Score "+highScore);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }


                    //Stage stage = null;
                    newstage.setTitle("Stick Hero Ninja !!!");
                    newstage.setScene(scene);
                    newstage.setResizable(true);
                    return;
                });

            } else if (endPointX>=pillar1.getPillar().getX()+pillar1.getPillar().getWidth()/2 && endPointX<=pillar1.getPillar().getX()+pillar1.getPillar().getWidth()/2+5) {
//                StickHero controller1  = curr_loader.getController();
//
//                Label scoreLabel1 = controller1.getView();
//                scoreLabel1.setText("PERFECTION");
//                scoreLabel1.setStyle("-fx-font-size: 40px; -fx-font-weight: bold;");
////                Label high_score = controller.high_score_tell;
////                high_score.setStyle("-fx-font-size: 40px; -fx-font-weight: bold;");
//                scoreLabel1.setVisible(true);

                System.out.println(pillar1.getRedblock().getX());
                System.out.println(pillar1.getRedblock().getX()-pillar1.getRedblock().getWidth()/2);
                System.out.println("Perfection");
                sth.setScore(sth.getScore()+1);
                Label label = new Label("Perfection");
//                sth.setView(label);
                sth.getView().setVisible(true);
                sth.getView().setText("+1");
                sth.getView().setTextFill(Color.BLACK);
//                sth.getView().setVisible(true);
                Platform.runLater(() -> {
                    String perectionSound = "src/Main/resources/com/example/stick_hero_final_project/Sounds/victory.wav";
                    Media sound_button = new Media(new File(perectionSound).toURI().toString());
                    MediaPlayer m = new MediaPlayer(sound_button);
                    m.play();
                    sth.getView().setVisible(true);
//                sth.getView().setLayoutY(endPointY);
                    ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(2), sth.getView());
                    scaleTransition.setToX(2.5); // Scale factor for width
                    scaleTransition.setToY(2.5); // Scale factor for height

                    // Transition to fade out
                    FadeTransition fadeTransition = new FadeTransition(Duration.seconds(2), sth.getView());
                    fadeTransition.setToValue(0); // Set target opacity

                    // Sequential transition combining both scale and fade transitions
                    ParallelTransition seqTransition = new ParallelTransition(scaleTransition, fadeTransition);
                    seqTransition.play();
                });
                sth.getView().setVisible(true);
//                sth.getView().setf
//                sth.getView().setVisible(false);
                Popup popup = new Popup();
                label.setStyle(" -fx-background-color: white;");
                popup.getContent().add(label);
                label.setMinWidth(80);
                label.setMinHeight(50);
                Parent root1 = newScene.getRoot();
//                root1.getChildrenUnmodifiable().add(popup.getOwnerNode());

                Timeline timeline8 = new Timeline(new KeyFrame(Duration.seconds(0.5), ee -> {
                    if (!popup.isShowing()) {
                        popup.show(newstage);
                    }
                }));
                timeline.setCycleCount(1); // Run only once
                System.out.println("Perfection."); //DEbug
                timeline8.play();
                timeline8.setOnFinished(actionEvent1 -> {
                    popup.hide();
                });

            }
            if (check_fall_flag==0) {
                sth.rem();
                sth.remcherry();
//                StickHero controller1  = curr_loader.getController();
//
//                Label scoreLabel1 = controller1.view;
//                scoreLabel1.setText("+1");
//                scoreLabel1.setStyle("-fx-font-size: 40px; -fx-font-weight: bold;");
////                Label high_score = controller.high_score_tell;
////                high_score.setStyle("-fx-font-size: 40px; -fx-font-weight: bold;");
//                scoreLabel1.setVisible(true);
                // Create a Runnable for player move left
                // Animation for moving the player to the left
                System.out.println("Positions " + pillar2.getPillar().getX());
                System.out.println("Player Positions"+player.getNode().getX());
                sth.setCurrent_pillar(sth.getAhead_pillar());
                TranslateTransition playerTransition = new TranslateTransition(Duration.seconds(0.2), player.getNode());
                playerTransition.setByX(-player.getNode().getTranslateX());
//                    playerTransition.play();


                TranslateTransition pillar2Transition = new TranslateTransition(Duration.seconds(0.2), pillar2.getPillar());
                pillar2Transition.setByX(-1000);
//                    pillar2Transition.play();
                TranslateTransition pillar1Transition = new TranslateTransition(Duration.seconds(0.2), pillar1.getPillar());
                pillar1Transition.setByX(-pillar1.getPillar().getX());
                //CompletableFuture<Void> future = CompletableFuture.runAsync(() -> sth.createRandomPillar(rot));
//                    player.getNode().setX(0);
//                    player.getNode().setY(500);
//                    pillar1Transition.play();
                //Animation for moving the Stick to the left
                TranslateTransition stickTransition = new TranslateTransition(Duration.seconds(0.2), stick.getStick());
                stickTransition.setByX(-player.getNode().getTranslateX()-2);


//                    stickTransition.play();
                ParallelTransition parallelTransition = new ParallelTransition(
                        playerTransition,
                        pillar2Transition,
                        pillar1Transition,
                        stickTransition
                );

                parallelTransition.play();
                sth.createRandomPillar(rot);
                // Animation for moving pillar1 to the left

                // Animation for moving pillar2 to the left
                parallelTransition.setOnFinished(eve -> {
//                    player.getNode().setX(startx);
                    // Execute the Runnables in separate threads
                    //new Thread(playerMoveLeft).start();


                    try {
                        System.out.println("hi");
                        //latch.await();
                        System.out.println("HUHHUSDSDSDSD");

                        //Thread.sleep(1000);
//                        if (sth.getSpeed() > 2.5 || sth.getStick_speed_fllag() > 2) {
//                            sth.setStick_speed_fllag(0);
//                            sth.setSpeed(sth.getSpeed() / 2);
//                        }
                        //sth.setStick_speed_fllag(sth.getStick_speed_fllag() + 1);
                        owl++;
                        sth.setSpeed(sth.getSpeed()/owl);
                        sth.setScore(sth.getScore() + 1);
                        sth.setPostion_face(0);
                        File metaDataFile = new File("meta_data.txt");
//                    if (metaDataFile.length()>0 && metaDataFile.exists()){
                        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("meta_data.txt"))) {
                            Integer previousScore = (Integer) inputStream.readObject();

                            int currentScore = sth.getScore_view();

                            if (previousScore == null || currentScore > previousScore) {
                                try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("meta_data.txt"))) {
                                    outputStream.writeObject(currentScore);
                                    //outputStream.writeObject(sth.getCherries());
                                    Label label = new Label("New High Score");

                                    Popup popup = new Popup();
                                    label.setStyle(" -fx-background-color: white;");
                                    popup.getContent().add(label);
                                    label.setMinWidth(80);
                                    label.setMinHeight(50);
                                    Timeline timeline8 = new Timeline(new KeyFrame(Duration.seconds(1), ee -> {
                                        if (!popup.isShowing()) {
                                            popup.show(newstage);
                                        }
                                    }));
                                    timeline.setCycleCount(1); // Run only once
                                    System.out.println("File created and initial data written."); //DEbug
                                    timeline8.play();
                                    timeline8.setOnFinished(actionEvent1 -> {
                                        popup.hide();
                                    });
                                    System.out.println("New high score or data written to file.");
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            } else {
                                System.out.println("Current score is not higher than the previous high score."); //Debug
                            }
                        } catch (Exception e) {
                            // File doesn't exist, so create and write the current score
                            try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("meta_data.txt"))) {
                                outputStream.writeObject(sth.getScore_view());
                                //outputStream.writeObject(sth.getCherries());
                                Label label = new Label("New High Score");

                                Popup popup = new Popup();
                                label.setStyle(" -fx-background-color: white;");
                                popup.getContent().add(label);
                                label.setMinWidth(80);
                                label.setMinHeight(50);
                                Timeline timeline8 = new Timeline(new KeyFrame(Duration.seconds(0.5), ee -> {
                                    if (!popup.isShowing()) {
                                        popup.show(newstage);
                                    }
                                }));
                                timeline.setCycleCount(1); // Run only once
                                System.out.println("File created and initial data written."); //DEbug
                                timeline8.play();
                                timeline8.setOnFinished(actionEvent1 -> {
                                    popup.hide();
                                });
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
                        }

                        sth.init();
                        //newstage.close();
//                newScene.getRoot().getChildren().clear();

                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println("BUHU");
                });
            }
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
    public void playermoveleft(PlayerCreate player){
        player.getNode().setX(0);
        player.getNode().setY(500);
    }
    public void showAlert(String msg) {
        Platform.runLater(()-> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(msg);
            alert.setHeaderText(null);
            alert.setContentText(msg);

            alert.showAndWait();
        });
    }
    public boolean boundcheck(ImageView cheery_image, StickHero sth) throws InterruptedException {
//        System.out.println("mskmsdsmdskdmsdks");
        if (cheery_image!=null){
            boolean collisionDetected = playerImageView.getBoundsInParent().intersects(cheery_image.getBoundsInParent());

//        System.out.println("mskmsdsmdskdmsdks");
            if (collisionDetected){
                sth.inc_cherries();
                sth.remcherry();
//            System.out.println("halndnskdnadlaskdand");
            }
            return collisionDetected;}
        return false;
    }
    public boolean Rectangle_collison(Pillar q, Pillar p, StickHero sth){
        if (sth.getPostion_face()==1){
            boolean collisonDetected = (playerImageView.getBoundsInParent().intersects(p.getPillar().getBoundsInParent()) || playerImageView.getBoundsInParent().intersects(q.getPillar().getBoundsInParent()));
            return collisonDetected;
        }
        return false;

    }
    public void cherry_set(StickHero sth){
        Platform.runLater(()->{
            try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("cherry_data.txt"))) {
                outputStream.writeObject(sth.getCherries());
                System.out.println("Done");
                //outputStream.writeObject(sth.getCherries());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

    }

    public void back_create() {
        FileInputStream inputStream1 = null;
        try {
            inputStream1 = new FileInputStream("src/Main/resources/com/example/stick_hero_final_project/Images/back1.jpeg");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        Image backgroundImage = new Image(inputStream1);

    }
}