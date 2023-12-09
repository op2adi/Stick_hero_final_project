package com.example.stick_hero_final_project;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CheeryTest {
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