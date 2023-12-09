package com.example.stick_hero_final_project;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

//Decorator class for pillar to make it Disappear
public class VisitorDesignPattern {
    public void isvisible(Pillar p){
        p.getPillar().setVisible(false);

    }
    public void onvisible(Pillar p){
        p.getPillar().setVisible(true);
    }

}