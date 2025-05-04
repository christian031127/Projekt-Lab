package org.example;

import java.awt.Graphics2D;

public abstract class GraphicsObject {
    protected int x;
    protected int y;

    public GraphicsObject(int x, int y){
        this.x = x;
        this.y = y;
    }

    public void draw(Graphics2D graphics) {
    }
}
