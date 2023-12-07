package com.example.stick_hero_final_project;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
        System.out.println("Max min "+minX+" "+p2.getPillar().getTranslateX());
        String cherr = "src/main/resources/com/example/stick_hero_final_project/Images/cherry.png";
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(cherr);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        Image cherry = new Image(inputStream) ;
        cherry_image = new ImageView(cherry);
        cherry_image.setFitWidth(30);
        cherry_image.setFitHeight(30);
        Random random = new Random();
        double randomX = (minX + maxX)/2;
        cherry_image.setX(randomX);
        System.out.println("vhhv"+cherry_image.getX());
        boolean dirn = random.nextBoolean();
        if (dirn){
            cherry_image.setY(500+player.getNode().getFitHeight()+5);
        }
        else {
            cherry_image.setY(500-player.getNode().getFitHeight()+10);
        }

    }
}