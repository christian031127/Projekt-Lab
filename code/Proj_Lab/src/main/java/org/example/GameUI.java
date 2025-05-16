package org.example;

import javax.imageio.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class GameUI {

    private static Map map = new Map();
    public static void main(String[] args) throws Exception {
        map.loadMap();
        // JFrame létrehozása
        JFrame frame = new JFrame("Data_daddies Fungórium");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);

        // GridLayout beállítása (pl. 2 sor, 2 oszlop)
        frame.setLayout(new BorderLayout());

        JPanel leftPanel = new JPanel();
        leftPanel.setPreferredSize(new Dimension(400, 400)); // fix szélesség
        leftPanel.setBackground(Color.LIGHT_GRAY);

        JPanel rightPanel = new JPanel(new BorderLayout());

        // Egyedi JComponent létrehozása
        JComponent customComponent = new JComponent() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
            }
        };
        customComponent.setLayout(new BoxLayout(customComponent, BoxLayout.Y_AXIS));

        Button startButton = new Button();
        startButton.addActionListener(e->{

        });



        startButton.setLabel("Start");
        startButton.setMaximumSize(new Dimension(100, 30));



        customComponent.add(new JLabel("Menü"));
        JLabel playerScore = new JLabel("Játékos pontja: " + map.currentPlayer.getScore());
        JLabel currentPlayerLabel = new JLabel("Aktuális játékos: " + map.currentPlayer.getPlayer_id());

        Button nextPlayer = new Button("Követező játékos");
        nextPlayer.addActionListener(e -> {
            playerScore.setText("Játékos pontja: " + map.currentPlayer.getScore());
            map.nextPlayer();
            currentPlayerLabel.setText("Aktuális játékos: " + map.currentPlayer.getPlayer_id());
            map.repaint();

        });
        nextPlayer.setMaximumSize(new Dimension(150, 30));


        customComponent.add(currentPlayerLabel, BorderLayout.WEST);
        customComponent.add(playerScore, BorderLayout.EAST);

        customComponent.add(Box.createVerticalStrut(10));
        customComponent.add(nextPlayer);

        customComponent.add(Box.createVerticalStrut(10));
        customComponent.add(startButton);

        leftPanel.add(customComponent);
        rightPanel.add(map, BorderLayout.CENTER);

        //customComponent.repaint();
        //Minden gomb híváskor meg kell hívni és az újra rajzol mindent

        // További komponensek (pl. címkék) a gridhez
        //JLabel label1 = new JLabel("Label 1", SwingConstants.CENTER);
        //JLabel label2 = new JLabel("Label 2", SwingConstants.CENTER);
        //JLabel label3 = new JLabel("Label 3", SwingConstants.CENTER);

        // Komponensek hozzáadása a frame-hez

        map.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                GraphicsTekton t=map.getTektonbyClick(e.getX(),e.getY());
                t.x=0;
                map.repaint();
            }
            @Override public void mousePressed(MouseEvent e) {}
            @Override public void mouseReleased(MouseEvent e) {}
            @Override public void mouseEntered(MouseEvent e) {}
            @Override public void mouseExited(MouseEvent e) {}
        });

        frame.add(leftPanel, BorderLayout.WEST);
        frame.add(rightPanel, BorderLayout.CENTER);



        frame.pack();
        // Ablak megjelenítése
        frame.setVisible(true);
    }
}