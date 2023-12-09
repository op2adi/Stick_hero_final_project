package com.example.stick_hero_final_project;

import org.junit.jupiter.api.*;
import javafx.application.Platform;
import javafx.scene.control.Label;

import java.io.IOException;
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
    public void test1() {
        CountDownLatch latch = new CountDownLatch(1);

        Platform.runLater(() -> {
            StickHero p = new StickHero(new PlayerCreate(0, 0, 0, 0), 10, 0, 100, 30, 0, false, true, new Label("HI"),false);
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
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
