package org.example;

import java.awt.*;
import java.awt.image.ImageObserver;
import java.io.IOException;

import javax.imageio.ImageIO;

public class GraphicsPlayer extends GraphicsObject{

    private Player player;

    public GraphicsPlayer(int x, int y, Player player) {
        super(x, y);
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public void draw(Graphics2D g)  {
    }
}
