package com.example.stick_hero_final_project;

import java.util.Arrays;
import java.util.List;

public class background{
    private static final List<String> backgroundImages = Arrays.asList(
            ("src/main/resources/com/example/stick_hero_final_project/Images/back1.jpeg"),
            ("src/main/resources/com/example/stick_hero_final_project/Images/back2.jpeg"),
            ("src/main/resources/com/example/stick_hero_final_project/Images/back3.jpeg")
    );

    public List<String> getBackgroundImages() {
        return backgroundImages;
    }

}