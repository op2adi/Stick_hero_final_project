package com.example.stick_hero_final_project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class HelloController {
    public Circle playButton;
    public Button clobutt;
    public ImageView clobutt1;
    public Label playButton1;
    public Circle test;
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to Game");
    }

    public void onPlayButtonClick(MouseEvent MouseEvent) throws IOException {
        String button_sound  = "src/main/resources/com/example/stick_hero_final_project/Sounds/button.mp3";
        Media sound_button = new Media(new File(button_sound).toURI().toString());
        MediaPlayer m = new MediaPlayer(sound_button);
        m.setVolume(1);
        m.play();
        // Load the FXML file for the new scene
        Stage stage;
        if (playButton!=null){
        stage = (Stage) playButton.getScene().getWindow();}
        else {
            stage = (Stage) playButton1.getScene().getWindow();
        }

        // Close the current stage
        stage.close();
        stick_hero p = new stick_hero(new Player_create(0,0,0,0),10,0,100,30,0,false,true,new Label("HI"));

        p.back_create();
    }

    public void closebutton(){
        Stage stage;
        if (clobutt!=null){
            stage = (Stage) clobutt.getScene().getWindow();}
        else {
            stage = (Stage) clobutt1.getScene().getWindow();
        }

        // Close the current stage
        stage.close();
    }

}