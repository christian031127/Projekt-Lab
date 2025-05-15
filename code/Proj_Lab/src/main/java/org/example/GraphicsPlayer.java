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
        Image imagePlayer=null;
        try {
            if(player.getIsInsect()){
                imagePlayer = ImageIO.read(getClass().getResource("/images/bug_1.png"));
            }

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if (imagePlayer != null) {
            g.drawImage(imagePlayer, x, y,32,32, null);
        }
    }
}
