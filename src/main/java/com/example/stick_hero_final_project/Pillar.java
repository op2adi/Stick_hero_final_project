package com.example.stick_hero_final_project;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Pillar {

    private Rectangle pillar;
    private Rectangle redblock;
    private double width;

    public void setPillar(Rectangle pillar) {
        this.pillar = pillar;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    private double height;

    public Rectangle getRedblock() {
        return redblock;
    }

    public void setRedblock(Rectangle redblock) {
        this.redblock = redblock;
    }

    // constructor
    public Pillar(double x, double y, double width, double height) {
        redblock = new Rectangle(width - 4, 2, Color.RED);
        redblock.setY(y);
        redblock.setX((x+width)/2);
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
