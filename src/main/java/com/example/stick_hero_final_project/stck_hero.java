package com.example.stick_hero_final_project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Random;

public class stck_hero extends background {
    private Player_create player;
    private static final int height = 800;
    private static final int width = 800;
    private static final int stick_hero_height = 50;
    private int speed = 1;
    private int score = 0;
    private int  pillar_length = 100;
    private int getPillar_width = 30;
    private int cherries=0;

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
}
