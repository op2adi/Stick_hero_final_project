package com.example.stick_hero_final_project;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Random;

public class cheery {
    public Double min(Double a,Double b){
        if (a<b){
            return a;
        }
        else {
            return b;
        }


    }
    private ImageView cherry_image ;

    public ImageView getCherry_image() {
        return cherry_image;
    }
    public cheery(Pillar p1,Pillar p2,Player_create player){
        double minX = 0;
        if(p1!=null){
            minX = p1.getPillar().getX()+p1.getPillar().getWidth();
        }
        else {
            minX = 60;
        }
        double maxX = p2.getPillar().getX();
        Image cherry = new Image("D:\\Stick_Hero_Final_Project\\src\\main\\java\\com\\example\\stick_hero_final_project\\Images\\cherry.png") ;
        cherry_image = new ImageView(cherry);
        cherry_image.setFitWidth(30);
        cherry_image.setFitHeight(30);
        Random random = new Random();
        double randomX = minX -50;
        cherry_image.setX(randomX);
        boolean dirn = random.nextBoolean();
        if (dirn){
            cherry_image.setY(500+player.getNode().getFitHeight()+5);
        }
        else {
            cherry_image.setY(500-player.getNode().getFitHeight()+10);
        }

    }
}