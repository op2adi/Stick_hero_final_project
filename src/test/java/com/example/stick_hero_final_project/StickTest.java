package com.example.stick_hero_final_project;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StickTest {
    @Test
    public void stickTest(){
        Stick s = new Stick(0,0,0,0);
        s.setHt(0);
        for (int i =0;i<5;i++){
            s.extend(10);
        }
        assertEquals(50,s.getHt());
    }
}