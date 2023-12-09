package com.example.stick_hero_final_project;

import org.junit.jupiter.api.*;
import javafx.application.Platform;
import javafx.scene.control.Label;

import java.io.IOException;
import java.nio.FloatBuffer;
import java.util.concurrent.CountDownLatch;

import static org.junit.jupiter.api.Assertions.*;

public class StickHeroTest {

    @BeforeAll
    public static void initJFX() {
        // Initialize JavaFX Toolkit
        Platform.startup(() -> {
            // Any initialization code if needed
        });
        Platform.setImplicitExit(false);
    }

    @AfterAll
    public static void cleanUp() {
        // Clean up JavaFX Toolkit
        Platform.exit();
    }
    @Test
    public void StickheroNullity() {
        CountDownLatch latch = new CountDownLatch(1);

        Platform.runLater(() -> {
            StickHero p = new StickHero(new PlayerCreate(0, 0, 0, 0), 10, 0, 100, 30, 0, false, true, new Label("HI"),false, false);
            try {
                p.back_create();

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            assertNotNull(p); // Example assertion, replace with your actual test

            // Release the latch to signal that the JavaFX operations are done
            latch.countDown();
        });

        try {
            // Wait for the JavaFX operations to complete on the Application Thread
            latch.await();
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void stickTest(){
        Stick s = new Stick(0,0,0,0);
        s.setHt(0);
        for (int i =0;i<5;i++){
            s.extend(10);
        }
        assertEquals(50,s.getHt());
    }
    @Test
    public void cherryTest(){
        Pillar p1 = new Pillar(9,99,9,9);
        Pillar p2 = new Pillar(9,99,90,9);
        Pillar p3 = new Pillar(9,99,67,9);
        Pillar p4 = new Pillar(9,990,9,9);
        PlayerCreate player = new PlayerCreate(0,0,0,0);
        Cheery c = Cheery.Cheery_getinstance(p1,p2,player);
        Cheery c2 = Cheery.Cheery_getinstance(p3,p4,player);
        if (c.equals(c2)){
            System.out.println(c.toString());
            System.out.println(c2.toString());
            assert(true);
        }
        else {
            assert(false);
        }
    }


}
