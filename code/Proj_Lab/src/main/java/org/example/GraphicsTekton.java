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
            imageSporeNumbing = ImageIO.read(getClass().getResource("/images/numbing_spore.png"));
            imageSporeSlowing = ImageIO.read(getClass().getResource("/images/slowing_spore.png"));
            imageSporeSplitting = ImageIO.read(getClass().getResource("/images/splitting_spore.png"));
            imageSPoreAccelerating = ImageIO.read(getClass().getResource("/images/accelerating_spore.png"));
            imageSporeWeakening = ImageIO.read(getClass().getResource("/images/weakening_spore.png"));
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
            x1 = x;
            for (Spore spore : tekton.getSpores()) {
                switch (spore.getClass().getSimpleName()) {
                    case "NumbingSpore":
                        g.drawImage(imageSporeNumbing, x1, y, null);
                        x1 += imageSporeNumbing.getWidth(null);
                        break;
                    case "SlowingSpore":
                        g.drawImage(imageSporeSlowing, x1, y, null);
                        x1 += imageSporeSlowing.getWidth(null);
                        break;
                    case "SplittingSpore":
                        g.drawImage(imageSporeSplitting, x1, y, null);
                        x1 += imageSporeSplitting.getWidth(null);
                        break;
                    case "AcceleratingSpore":
                        g.drawImage(imageSPoreAccelerating, x1, y, null);
                        x1 += imageSPoreAccelerating.getWidth(null);
                        break;
                    case "WeakeningSpore":
                        g.drawImage(imageSporeWeakening, x1, y, null);
                        x1 += imageSporeWeakening.getWidth(null);
                        break;
                    default:
                        break;
                }
            }
        }
        if (!tekton.getYarns().isEmpty()) {
            
        }
    }
}