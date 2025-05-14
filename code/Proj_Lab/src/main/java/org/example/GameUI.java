package org.example;

import javax.imageio.*;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class GameUI {

    private static Map map = new Map();
    public static void main(String[] args) throws Exception {

        // JFrame létrehozása
        JFrame frame = new JFrame("Data_daddies Fungórium");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);

        // GridLayout beállítása (pl. 2 sor, 2 oszlop)
        frame.setLayout(new GridLayout(1, 2));

        // Egyedi JComponent létrehozása
        JComponent customComponent = new JComponent() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.fillRect(10, 10, getWidth() - 20, getHeight() - 20);
            }
        };

        Button startButton = new Button();
        startButton.setLabel("Start");

        JLabel playerScore = new JLabel();

        Button nextPlayer = new Button("Követező játékos");
        nextPlayer.addActionListener(e -> {
            playerScore.setText("Játékos pontja: " + map.currentPlayer.getScore());
            map.nextPlayer();
            map.repaint();
        });

        JLabel currentPlayerLabel = new JLabel("Aktuális játékos: " + map.currentPlayer);

        customComponent.add(new JLabel("Menü"));
        customComponent.add(currentPlayerLabel);
        customComponent.add(nextPlayer);
        customComponent.add(playerScore);
        customComponent.add(startButton);
        customComponent.setMinimumSize(new Dimension(100, 400));
        customComponent.setMaximumSize(new Dimension(100, 400));


        //customComponent.repaint();
        //Minden gomb híváskor meg kell hívni és az újra rajzol mindent

        // További komponensek (pl. címkék) a gridhez
        //JLabel label1 = new JLabel("Label 1", SwingConstants.CENTER);
        //JLabel label2 = new JLabel("Label 2", SwingConstants.CENTER);
        //JLabel label3 = new JLabel("Label 3", SwingConstants.CENTER);

        // Komponensek hozzáadása a frame-hez
        
        frame.add(customComponent);
        frame.add(map);
        
        map.loadMap();
        

        // Ablak megjelenítése
        frame.setVisible(true);
    }
}