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

        FXMLLoader loader = new FXMLLoader(getClass().getResource("game.fxml"));
        Parent root = loader.load();

        // Create a new stage
        Stage newStage = new Stage();
        newStage.setTitle("New Scene"); // Set the title of the new window

        // Set up the scene with the loaded FXML content
        Scene newScene = new Scene(root);
        newStage.setScene(newScene);
        // Show the new stage
        newStage.show();
    }

}