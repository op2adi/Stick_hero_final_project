package com.example.stick_hero_final_project;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public class Player_create {
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

    public ImageView getNode() {
        return playerImageView;
    }
}
