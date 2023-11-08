package com.example.stick_hero_final_project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {
    public Button playButton;
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to Game");
    }

    public void onPlayButtonClick(ActionEvent actionEvent) throws IOException {
        // Load the FXML file for the new scene
        Stage stage = (Stage) playButton.getScene().getWindow();

        // Close the current stage
        stage.close();
        stck_hero p = new stck_hero();
        p.opdil();
    }

}