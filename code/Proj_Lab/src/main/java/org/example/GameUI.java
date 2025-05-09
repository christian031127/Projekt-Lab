package org.example;

import javax.swing.*;
import java.awt.*;

public class GameUI {
    public static void main(String[] args) {
        // JFrame létrehozása
        JFrame frame = new JFrame("GridLayout JComponent példa");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);

        // GridLayout beállítása (pl. 2 sor, 2 oszlop)
        frame.setLayout(new GridLayout(2, 2));

        // Egyedi JComponent létrehozása
        JComponent customComponent = new JComponent() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.BLUE);
                g.fillRect(10, 10, getWidth() - 20, getHeight() - 20);
            }
        };

        // További komponensek (pl. címkék) a gridhez
        JLabel label1 = new JLabel("Label 1", SwingConstants.CENTER);
        JLabel label2 = new JLabel("Label 2", SwingConstants.CENTER);
        JLabel label3 = new JLabel("Label 3", SwingConstants.CENTER);

        // Komponensek hozzáadása a frame-hez
        frame.add(label1);
        frame.add(customComponent);
        frame.add(label2);
        frame.add(label3);

        // Ablak megjelenítése
        frame.setVisible(true);
    }
}