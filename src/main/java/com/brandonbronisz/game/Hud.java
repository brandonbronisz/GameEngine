package com.brandonbronisz.game;

import com.brandonbronisz.engine.GameItem;
import com.brandonbronisz.engine.IHud;
import com.brandonbronisz.engine.TextItem;
import com.brandonbronisz.engine.Window;
import com.brandonbronisz.engine.graphics.FontTexture;
import com.brandonbronisz.engine.graphics.Material;
import com.brandonbronisz.engine.graphics.Mesh;
import com.brandonbronisz.engine.graphics.OBJLoader;
import org.joml.Vector3f;

import java.awt.*;

/**
 * Created by brandonbronisz on 2/3/17.
 */

public class Hud implements IHud {

    private static final Font FONT = new Font("Arial", Font.PLAIN, 20);

    private static final String CHARSET = "ISO-8859-1";

    private final GameItem[] gameItems;

    private final TextItem statusTextItem;

    private final GameItem compassItem;

    public Hud(String statusText) throws Exception {
        FontTexture fontTexture = new FontTexture(FONT, CHARSET);
        this.statusTextItem = new TextItem(statusText, fontTexture);
        this.statusTextItem.getMesh().getMaterial().setColour(new Vector3f(1, 1, 1));

        // Create compass
        Mesh mesh = OBJLoader.loadMesh("/models/compass.obj");
        Material material = new Material();
        material.setColour(new Vector3f(1, 0, 0));
        mesh.setMaterial(material);
        compassItem = new GameItem(mesh);
        compassItem.setScale(40.0f);
        // Rotate to transform it to screen coordinates
        compassItem.setRotation(0f, 0f, 180f);

        // Create list that holds the items that compose the HUD
        gameItems = new GameItem[]{statusTextItem, compassItem};
    }

    public void setStatusText(String statusText) {
        this.statusTextItem.setText(statusText);
    }

    public void rotateCompass(float angle) {
        this.compassItem.setRotation(0, 0, 180 + angle);
    }

    @Override
    public GameItem[] getGameItems() {
        return gameItems;
    }

    public void updateSize(Window window) {
        this.statusTextItem.setPosition(10f, window.getHeight() - 50f, 0);
        this.compassItem.setPosition(window.getWidth() - 40f, 50f, 0);
    }
}