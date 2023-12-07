package com.example.stick_hero_final_project;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;

public class GameOver {
    public Rectangle restart;
    public Rectangle exit_from_game;
    public Label score_tell;
    public Label high_score_tell;
    public ImageView restart_image;
    public ImageView exit_image;

    public void Restart(MouseEvent MouseEvent) throws IOException {

//        score_tell.setText();
        Stage stage;
        if (restart != null) {
            stage = (Stage) restart.getScene().getWindow();
        } else {
            stage = (Stage) restart_image.getScene().getWindow();
        }
        stage.setOnCloseRequest(event -> {
            // Handle the close request here
            System.out.println("Close button pressed");
            // You can perform any cleanup or other actions here before exiting
            Platform.exit(); // Exit the application
        });
        stage.close();

    stick_hero pop = new stick_hero(new Player_create(0, 0, 0, 0), 10, 0, 100, 30, 0, false, true, new Label("HI"));
        pop.back_create();
    }

    public void Exit(MouseEvent mouseEvent) {
        Stage stage;
        if (exit_from_game!=null){
        stage = (Stage) exit_from_game.getScene().getWindow();}
        else {
            stage = (Stage) exit_image.getScene().getWindow();
        }
        stage.close();
        Platform.exit();
    }
}