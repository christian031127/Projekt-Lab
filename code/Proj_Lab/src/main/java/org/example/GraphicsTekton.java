package org.example;

import java.awt.*;
import java.io.IOException;

import javax.imageio.ImageIO;

public class GraphicsTekton extends GraphicsObject{

    private Tekton tekton;

    public GraphicsTekton(int x, int y, Tekton tekton) {
        super(x, y);
        this.tekton = tekton;
    }

    public Tekton getTekton() {
        return tekton;
    }

    public void draw(Graphics2D g) {
        //KEll rajzolni egy tektont
        //Ha van gomba rajta, akkor azt is
        //Ha van rajta fonál, akkor azt is
        //ha össze van kötve fonállal, akkor azt is
        // spórá is
        Image imageTekton = null;
        Image imageShroom = null;

        //Tekton kirajzolása
        try {
            imageTekton = ImageIO.read(getClass().getResource("/images/multipleYarnTekton.png"));
            imageShroom = ImageIO.read(getClass().getResource("/images/mushroom_1.png"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        if (imageTekton != null) {
            g.drawImage(imageTekton, x, y, null);
            //Méret átalakítása
        }

        if (tekton.getShroom() != null) {
            g.drawImage(imageShroom, x, y, null);
        }
        if (!tekton.getSpores().isEmpty()) {
            
        }
        if (!tekton.getYarns().isEmpty()) {
            
        }
    }
}