package com.example.stick_hero_final_project;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Random;

public class Cheery extends AbstractCheery {
    public Double min(Double a,Double b){
        if (a<b){
            return a;
        }
        else {
            return b;
        }


    }
    private static ImageView cherry_image;
    private static Cheery cheery;
    public ImageView getCherry_image() {
        return cherry_image;
    }
    public static Cheery Cheery_getinstance(Pillar p1, Pillar p2, PlayerCreate player){
        if (cheery == null){
            cheery = new Cheery(p1,p2,player);
            return cheery;
        }
        else{
            double minX = 0;
            if(p1!=null){
                minX = p1.getPillar().getX()+p1.getPillar().getWidth();
            }
            else {
                minX = 60;
            }
            double maxX = p2.getPillar().getX();
            System.out.println("Max min "+minX+" "+p2.getPillar().getTranslateX());
            String cherr = "src/Main/resources/com/example/stick_hero_final_project/Images/cherry.png";
            FileInputStream inputStream = null;
            try {
                inputStream = new FileInputStream(cherr);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            if (cherry_image!=null){
                Image cherry = new Image(inputStream) ;
                cherry_image = new ImageView(cherry);
            };
            cherry_image.setFitWidth(30);
            cherry_image.setFitHeight(30);
            Random random = new Random();
            double randomX = (minX + maxX)/2;
            cherry_image.setX(randomX);
            System.out.println("vhhv"+cherry_image.getX());
            //boolean dirn = false;
            cherry_image.setY(500 + player.getNode().getFitHeight() + 5);
            return cheery;
        }
    }
    private Cheery(Pillar p1, Pillar p2, PlayerCreate player){
        double minX = 0;
        if(p1!=null){
            minX = p1.getPillar().getX()+p1.getPillar().getWidth();
        }
        else {
            minX = 60;
        }
        double maxX = p2.getPillar().getX();
        System.out.println("Max min "+minX+" "+p2.getPillar().getTranslateX());
        String cherr = "src/Main/resources/com/example/stick_hero_final_project/Images/cherry.png";
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
        //boolean dirn = false;
        cherry_image.setY(500 + player.getNode().getFitHeight() + 5);
    };
}