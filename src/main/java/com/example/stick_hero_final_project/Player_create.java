package com.example.stick_hero_final_project;

//import com.sun.javafx.tk.TKStage;
import javafx.animation.*;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.application.Platform;

import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.*;
import java.util.concurrent.CountDownLatch;

public class Player_create {
    private int check_fall_flag = 0;
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

    public int getCheck_fall_flag() {
        return check_fall_flag;
    }

    public void setCheck_fall_flag(int check_fall_flag) {
        this.check_fall_flag = check_fall_flag;
    }

    public ImageView getPlayerImageView() {
        return playerImageView;
    }

    public void setPlayerImageView(ImageView playerImageView) {
        this.playerImageView = playerImageView;
    }

    public void movePlayerOnRotatedStick(stick stick, Player_create player, Scene newScene, Pillar pillar1, Pillar pillar2, Stage newstage, stick_hero sth,FXMLLoader curr_loader,ImageView cherry)  throws InterruptedException {
        check_fall_flag = 0;
        double stickAngle = stick.getStick().getRotate(); // Get the current rotation angle of the stick
        double startx = player.getNode().getX();
        System.out.println(startx);
        double starty = player.getNode().getY();
        double pillarstartx = pillar1.getPillar().getX();
        double pillarstarty = pillar1.getPillar().getY();
        System.out.println(pillarstartx);
        double endPointX = player.getNode().getX()+40+stick.ht;
//        double endpt = stick.getStick().getX();
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
        System.out.println("new end = "+(player.getNode().getX()+40+stick.ht));
        System.out.println(pillarstartx);
        System.out.println(pillar1.getPillar().getLayoutX());
        System.out.println("Height"+pillar1.getHeight());

        Timeline timeline = new Timeline();
        Timeline timeline78 = new Timeline();
// Assuming 'startX' and 'startY' are defined somewhere
        double startX = player.getNode().getX();
        double startY = player.getNode().getY();

// Create a KeyFrame to check the condition every 0.1 second
        KeyFrame conditionCheckFrame = new KeyFrame(Duration.seconds(0.0001), event ->boundcheck(cherry));
// Add the KeyFrame to the Timeline
        timeline78.getKeyFrames().add(conditionCheckFrame);

// Create KeyFrames for animation
        KeyFrame endKeyFrame = new KeyFrame(Duration.seconds((endPointX - startX) / 100),
                new KeyValue(player.getNode().xProperty(), endPointX-5),
                new KeyValue(player.getNode().yProperty(), endPointY)
        );

// Add the animation KeyFrame to the Timeline
        timeline.getKeyFrames().add(endKeyFrame);
        timeline78.setCycleCount(Animation.INDEFINITE);
// Play the Timeline
        timeline78.play();
        timeline.play();
        //Thread.sleep(200);
        timeline.setOnFinished(actionEvent -> {
            timeline78.stop();
            CountDownLatch latch = new CountDownLatch(3);
            if (endPointX < pillarstartx  || endPointX > pillarstartx+pillar1.getPillar().getWidth() || sth.getPostion_face()==1) {

//                showAlert("+1");
//                stick_hero controller1  = curr_loader.getController();
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

                // Add an event handler to perform an action after the animation completes
                playerTransition.setOnFinished(event -> {
                    //newstage.close();
                    FXMLLoader fxmlLoader = new FXMLLoader(main.class.getResource("Gameover.fxml"));
                    Scene scene = null;
                    //scoreLabel1.setVisible(false);

                    try {
                        Parent root = fxmlLoader.load();
                        scene = new Scene(root, 320, 240);
                        GameOver controller = fxmlLoader.getController();
                        // Access the Label and set its text
                        Label scoreLabel = controller.score_tell;
                        scoreLabel.setText("Your Score "+String.valueOf(sth.getScore_view()));
                        scoreLabel.setStyle("-fx-font-size: 40px; -fx-font-weight: bold;");
                        Label high_score = controller.high_score_tell;
                        high_score.setStyle("-fx-font-size: 40px; -fx-font-weight: bold;");
                        int highScore = 0;
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

                        high_score.setText("High Score"+highScore);
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
//                stick_hero controller1  = curr_loader.getController();
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
//                stick_hero controller1  = curr_loader.getController();
//
//                Label scoreLabel1 = controller1.view;
//                scoreLabel1.setText("+1");
//                scoreLabel1.setStyle("-fx-font-size: 40px; -fx-font-weight: bold;");
////                Label high_score = controller.high_score_tell;
////                high_score.setStyle("-fx-font-size: 40px; -fx-font-weight: bold;");
//                scoreLabel1.setVisible(true);
                // Create a Runnable for player move left
                Platform.runLater(() -> {
                    // Animation for moving the player to the left
                    System.out.println("Positions " + pillar2.getPillar().getX());

                    TranslateTransition playerTransition = new TranslateTransition(Duration.seconds(1), player.getNode());
                    playerTransition.setByX(0);

                    playerTransition.play();
                    player.getNode().setX(0);
                    player.getNode().setY(500);

                    TranslateTransition pillar2Transition = new TranslateTransition(Duration.seconds(1), pillar2.getPillar());
                    pillar2Transition.setByX(-1000);
                    pillar2Transition.play();
                    TranslateTransition pillar1Transition = new TranslateTransition(Duration.seconds(1), pillar1.getPillar());
                    pillar1Transition.setByX(-pillar1.getPillar().getX());
                    pillar1Transition.play();
                    //Animation for moving the stick to the left
                    TranslateTransition stickTransition = new TranslateTransition(Duration.seconds(1), stick.getStick());
                    stickTransition.setByX(-100000);
                    stickTransition.play();


                    // Animation for moving pillar1 to the left

                    // Animation for moving pillar2 to the left


                });


                // Execute the Runnables in separate threads
                //new Thread(playerMoveLeft).start();


                try {
                    System.out.println("hi");
                    //latch.await();
                    System.out.println("HUHHUSDSDSDSD");

                    //Thread.sleep(1000);
                    if (sth.getSpeed() > 2.5 || sth.getStick_speed_fllag() > 2) {
                        sth.setStick_speed_fllag(0);
                        sth.setSpeed(sth.getSpeed() / 2);
                    }
                    sth.setStick_speed_fllag(sth.getStick_speed_fllag() + 1);
                    sth.setScore(sth.getScore()+1);
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
    public void playermoveleft(Player_create player){
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
    public boolean boundcheck(ImageView cheery_image){
//        System.out.println("mskmsdsmdskdmsdks");
        if (cheery_image!=null){
        boolean collisionDetected = playerImageView.getBoundsInParent().intersects(cheery_image.getBoundsInParent());
//        System.out.println("mskmsdsmdskdmsdks");
        if (collisionDetected){
//            System.out.println("halndnskdnadlaskdand");
        }
        return collisionDetected;}
        return false;
    }

}

