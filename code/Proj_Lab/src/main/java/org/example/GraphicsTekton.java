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
        Image imageTektonMultiple = null;
        Image imageTektonAbsorb = null;
        Image imageTektonKeepAlive = null;
        Image imageTektonNonShroom = null;
        Image imageTektonSingleYarn = null;
        Image imageShroom = null;

        //Tekton kirajzolása
        try {
            imageTektonMultiple = ImageIO.read(getClass().getResource("/images/multipleYarnTekton.png"));
            imageTektonAbsorb = ImageIO.read(getClass().getResource("/images/absorbTekton.png"));
            imageTektonKeepAlive = ImageIO.read(getClass().getResource("/images/keepAliveTekton.png"));
            imageTektonNonShroom = ImageIO.read(getClass().getResource("/images/nonShroomTekton.png"));
            imageTektonSingleYarn = ImageIO.read(getClass().getResource("/images/singleYarnTekton.png"));
            imageShroom = ImageIO.read(getClass().getResource("/images/mushroom_1.png"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        switch (tekton.getStrategy().toString()){

            case "MultipleYarn":
                imageTekton = imageTektonMultiple;
                break;
            case "Absorb":
                imageTekton = imageTektonAbsorb;
                break;
            case "KeepAlive":
                imageTekton = imageTektonKeepAlive;
                break;
            case "NonShroom":
                imageTekton = imageTektonNonShroom;
                break;
            case "SingleYarn":
                imageTekton = imageTektonSingleYarn;
                break;
            default:
                break;
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