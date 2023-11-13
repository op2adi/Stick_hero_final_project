package com.example.stick_hero_final_project;

import java.util.Arrays;
import java.util.List;

public class background{
    private static final List<String> backgroundImages = Arrays.asList(
            "file:/D:/Stick_Hero_Final_Project/src/main/java/com/example/stick_hero_final_project/Images/back1.jpeg",
            "file:/D:/Stick_Hero_Final_Project/src/main/java/com/example/stick_hero_final_project/Images/back2.jpeg",
            "file:/D:/Stick_Hero_Final_Project/src/main/java/com/example/stick_hero_final_project/Images/bac3.jpeg"
    );

    public List<String> getBackgroundImages() {
        return backgroundImages;
    }

}