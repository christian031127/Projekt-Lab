package org.example;

import java.awt.*;

public class GraphicsPlayer extends GraphicsObject{

    private Player player;

    public GraphicsPlayer(int x, int y, Player player) {
        super(x, y);
    }

    public Player getPlayer() {
        return player;
    }

    public void draw(Graphics2D g) {}
}
