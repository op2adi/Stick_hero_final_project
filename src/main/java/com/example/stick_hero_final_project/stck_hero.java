package com.example.stick_hero_final_project;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.Random;

public class stck_hero extends background implements score_interface,cherries,points_interface  {
    private Player_create player;
    private static final int height = 800;
    private static final int width = 800;
    private static final int stick_hero_height = 50;
    private int speed = 1;
    private int score = 0;
    private int  pillar_length = 100;
    private int getPillar_width = 30;
    private int cherries=0;
    private boolean keyIsPressed = false;

    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to Game");
    }


    public void opdil() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("game.fxml"));
        Parent root = loader.load();
        player = cr_pl();
        // Create a new stage
        Stage newStage = new Stage();
        newStage.setTitle("Stick_hero_game"); // Set the title of the new window
        // Set up the scene with the loaded FXML content
        Scene newScene = new Scene(root);
        newStage.setScene(newScene);
        String srt = getRandomImage();
        Image backgroundImage = new Image(srt);
        ImageView backgroundImageView = new ImageView(backgroundImage);
        System.out.println(srt);
        newStage.setWidth(600);
        newStage.setHeight(800);
        newStage.setMaxWidth(600);
        newStage.setMaxHeight(730);

// Create a BackgroundImage
        BackgroundImage background = new BackgroundImage(
                backgroundImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT
        );

// Create a Background with the BackgroundImage
        Background backgroundWithImage = new Background(background);

// Set the background for the Pane
        backgroundImageView.setFitWidth(600);
        backgroundImageView.setFitHeight(700);
        ((Pane) root).getChildren().add(backgroundImageView);
//        initialize1((Pane) root);
        // Show the new stage
        ((Pane) root).getChildren().add(player.getNode());
        player.getNode().setX(0);
        player.getNode().setY(500);
        newScene.addEventFilter(MouseEvent.MOUSE_RELEASED, event -> {
            keyIsPressed = false;
        });

        newScene.addEventFilter(MouseEvent.MOUSE_PRESSED, event -> {
            keyIsPressed = true;
            double x = event.getSceneX();
            double y = event.getSceneY();

            stick s = new stick(10, 10, 10, 10);
            ((Pane) root).getChildren().add(s.getStick());

            s.getStick().setLayoutX(player.getNode().getX() + 30);
            s.getStick().setLayoutY(player.getNode().getY() + 30);

            Timeline timeline = new Timeline( //thoda pdhna padega timeline ke baare me
                    new KeyFrame(Duration.millis(100), e -> {
                        if (keyIsPressed) {
                            s.getStick().setLayoutY(s.getStick().getLayoutY() - 10);
                            s.getStick().setHeight(s.getStick().getHeight() + 10);
//                            System.out.println("HI"); Debug statement
                        }
                    })
            );
            timeline.setCycleCount(Timeline.INDEFINITE);
            timeline.play();
        });

        newScene.addEventFilter(MouseEvent.MOUSE_RELEASED, event -> {
            keyIsPressed = false;
        });
        newStage.show();
    }


        public Node cr_pl_get_nd(){
        return (new Player_create(0,0,0,0)).getNode();
    }

    public Player_create cr_pl(){
        return (new Player_create(0,0,0,0));
    }

    public void initialize1(Pane mainPane) {
        // Set the background image
        Image backgroundImage = new Image(getRandomImage());
        BackgroundImage background = new BackgroundImage(
                backgroundImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT
        );

        mainPane.setBackground(new Background(background));
    }

    private String getRandomImage() {
        Random random = new Random();
        int index = random.nextInt(getBackgroundImages().size());
        return getBackgroundImages().get(index);
    }
    @FXML
    private void onMouseDragged(MouseEvent event) {
        double xCoordinate = event.getX();
        double yCoordinate = event.getY();
        System.out.println("Mouse dragged at coordinates: (" + xCoordinate + ", " + yCoordinate + ")");

        // You can perform any action or logic based on these coordinates here
    }

    @Override
    public void viewscore() {

    }

    @Override
    public void setscore() {

    }

    @Override
    public void increase_score() {

    }

    @Override
    public void set_cherries() {

    }

    @Override
    public void inc_cherries() {

    }

    @Override
    public void view_cherries() {

    }

    @Override
    public void relive_cherries() {

    }

    @Override
    public void display() {

    }

    @Override
    public void set_score() {

    }

    @Override
    public void increment() {

    }

    @Override
    public void perfect_increment() {

    }
}
