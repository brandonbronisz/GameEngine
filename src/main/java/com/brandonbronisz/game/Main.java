package com.brandonbronisz.game;

import com.brandonbronisz.engine.GameEngine;
import com.brandonbronisz.engine.IGameLogic;

public class Main {

    public static void main(String[] args) {
        try {
            System.setProperty("java.awt.headless", "true");
            boolean vSync = true;

            IGameLogic gameLogic = new DummyGame();
            GameEngine gameEng = new GameEngine("Engine", 600, 480, vSync, gameLogic);
            gameEng.start();
        } catch (Exception excp) {
            excp.printStackTrace();
            System.exit(-1);
        }
    }
}