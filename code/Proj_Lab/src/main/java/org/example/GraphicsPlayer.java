package org.example;

import java.awt.*;
import java.awt.image.ImageObserver;
import java.io.IOException;

import javax.imageio.ImageIO;

public class GraphicsPlayer extends GraphicsObject{

    private Player player;
    private int DrawId;

    public GraphicsPlayer(int x, int y, Player player) {
        super(x, y);
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }
    public void setDrawId(int DrawId) {
        this.DrawId = DrawId;
    }
    public int getDrawId() {
        return DrawId;
    }
    public void draw(Graphics2D g)  {
        Image imagePlayer=null;
        try {
            if(player.getIsInsect()){
                    imagePlayer = ImageIO.read(getClass().getResource("/images/bug_"+player.getPlayer_id()+".png"));
            }

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if (imagePlayer != null) {

            g.drawImage(imagePlayer, x, y,85,85, null);
            if(DrawId>=10){
                g.setColor(new Color(DrawId * 50 % 255, 100, 150));  // determinisztikus szín különbözésre
                g.drawRect(x, y, 85, 85);
            }

        }
    }
}
