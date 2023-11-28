package com.example.stick_hero_final_project;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Pillar {

    private Rectangle pillar;
    private double width;
    private double height;

    // constructor
    public Pillar(double x, double y, double width, double height) {
        this.width = width;
        this.height = height;
        pillar = new Rectangle(width, height, Color.BLACK);
        pillar.setX(x);
        pillar.setY(y);
//        pillar.setLayoutX(x);
//        pillar.setLayoutY(y);
    }

    public Rectangle getPillar() {
        return this.pillar;
    }

    public void moveLeft(double distance) {pillar.setLayoutX(pillar.getLayoutX() - distance);return;
    }

    public Node getNode() {
        return this.pillar;
    }

}
