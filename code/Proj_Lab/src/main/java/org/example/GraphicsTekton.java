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
        Image imageSporeNumbing=null;
        Image imageSporeSlowing=null;
        Image imageSporeSplitting=null;
        Image imageSporeAccelerating=null;
        Image imageSporeWeakening=null;

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
            imageSporeAccelerating = ImageIO.read(getClass().getResource("/images/acceleration_spore.png"));
            imageSporeWeakening = ImageIO.read(getClass().getResource("/images/weakening_spore.png"));
            if(tekton.getShroom()!=null){
                imageShroom = ImageIO.read(getClass().getResource("/images/mushroom_"+tekton.getShroom().getPlayerId()+".png"));
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        switch (tekton.getStrategy().getClass().getSimpleName()){

            case "MultipleYarnTekton":
                imageTekton = imageTektonMultiple;
                break;
            case "AbsorbTekton":
                imageTekton = imageTektonAbsorb;
                break;
            case "KeepAliveTekton":
                imageTekton = imageTektonKeepAlive;
                break;
            case "NonShroomTekton":
                imageTekton = imageTektonNonShroom;
                break;
            case "SingleYarnTekton":
                imageTekton = imageTektonSingleYarn;
                break;
            default:
                break;
        }

        if (imageTekton != null) {
            g.drawImage(imageTekton, x, y, 120,120,null);
            //Méret átalakítása
        }

        if (tekton.getShroom() != null) {
            g.drawImage(imageShroom, x, y, 70,70,null);
        }
        if (!tekton.getSpores().isEmpty()) {
            int x1 = x;
            int y1 = y;

            for (Spore spore : tekton.getSpores()) {
                if(tekton.getSpores().indexOf(spore)!=0 && tekton.getSpores().indexOf(spore)%4==0){y1+=30;x1=x;}
                switch (spore.getClass().getSimpleName()) {
                    case "NumbingSpore":
                        g.drawImage(imageSporeNumbing, x1, y1, 30,30,null);
                        x1+=30;
                        //x1 += imageSporeNumbing.getWidth(null);
                        break;
                    case "SlowingSpore":
                        g.drawImage(imageSporeSlowing, x1, y1,30,30, null);
                        x1+=30;
                        //x1 += imageSporeSlowing.getWidth(null);
                        break;
                    case "SplitterSpore":
                        g.drawImage(imageSporeSplitting, x1, y1,30,30, null);
                        x1+=30;
                        //x1 += imageSporeSplitting.getWidth(null);
                        break;
                    case "AccelerationSpore":
                        g.drawImage(imageSporeAccelerating, x1, y1,30,30, null);
                        x1+=30;
                        //x1 += imageSPoreAccelerating.getWidth(null);
                        break;
                    case "WeakeningSpore":
                        g.drawImage(imageSporeWeakening, x1, y1, 30,30,null);
                        x1+=30;
                        //x1 += imageSporeWeakening.getWidth(null);
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