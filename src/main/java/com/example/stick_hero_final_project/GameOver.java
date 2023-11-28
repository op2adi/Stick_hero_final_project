package com.example.stick_hero_final_project;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class GameOver {
    public Button restart;
    public Button exit_from_game;

    public void Restart(ActionEvent actionEvent) throws IOException {
        Stage stage;
        stage = (Stage) restart.getScene().getWindow();
        stage.close();
        stick_hero pop = new stick_hero(new Player_create(0,0,0,0),10,0,100,30,0,false,true,new Label("HI"));
        pop.back_create();
    }

    public void Exit(ActionEvent actionEvent) {
        Stage stage;
        stage = (Stage) exit_from_game.getScene().getWindow();
        stage.close();
    }
}