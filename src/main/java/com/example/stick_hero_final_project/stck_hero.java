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
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class stck_hero extends HelloApplication {
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
        ((Pane) root).getChildren().add(player.getNode());
        // Show the new stage
        newStage.show();
    }

    public Node cr_pl_get_nd(){
        return (new Player_create(0,0,0,0)).getNode();
    }

    public Player_create cr_pl(){
        return (new Player_create(0,0,0,0));
    }
}