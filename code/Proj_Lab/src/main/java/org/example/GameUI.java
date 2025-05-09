package org.example;

import javax.imageio.*;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class GameUI {

    private static Map map = new Map();
    public static void main(String[] args) throws Exception {

        //map.loadMap();
        // JFrame létrehozása
        JFrame frame = new JFrame("Data_daddies Fungórium");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);

        // GridLayout beállítása (pl. 2 sor, 2 oszlop)
        frame.setLayout(new GridLayout(2, 2));

        JPanel imagePanel = new JPanel() {
            BufferedImage image;
            {
                try {
                    image = ImageIO.read(getClass().getResource("/images/cf0HfMe.png"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (image != null) {
                    g.drawImage(image, 0, 0, this);
                }
            }
        };

        // Egyedi JComponent létrehozása
        JComponent customComponent = new JComponent() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.BLUE);
                g.fillRect(10, 10, getWidth() - 20, getHeight() - 20);
            }
        };

        //customComponent.repaint();
        //Minden gomb híváskor meg kell hívni és az újra rajzol mindent

        // További komponensek (pl. címkék) a gridhez
        JLabel label1 = new JLabel("Label 1", SwingConstants.CENTER);
        //JLabel label2 = new JLabel("Label 2", SwingConstants.CENTER);
        JLabel label3 = new JLabel("Label 3", SwingConstants.CENTER);

        // Komponensek hozzáadása a frame-hez
        frame.add(imagePanel);
        frame.add(customComponent);
        frame.add(map);

        frame.add(label3);

        // Ablak megjelenítése
        frame.setVisible(true);
    }
}