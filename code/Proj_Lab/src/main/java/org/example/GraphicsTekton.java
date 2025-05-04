package org.example;

import java.awt.*;

public class GraphicsTekton extends GraphicsObject{

    private Tekton tekton;

    public GraphicsTekton(int x, int y, Tekton tekton) {
        super(x, y);
        this.tekton = tekton;
    }

    public Tekton getTekton() {
        return tekton;
    }

    public void draw(Graphics2D g) {}
}