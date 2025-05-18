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
    private static GraphicsTekton Ctekton1=null;
    private static GraphicsTekton Ctekton2=null;

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
        JLabel alert2=new JLabel();
        JLabel playerScore = new JLabel("Játékos pontja: " + map.currentPlayer.getScore());
        JLabel currentPlayerLabel = new JLabel("Aktuális játékos: " + map.currentPlayer.getPlayer_id());

        Button nextPlayer = new Button("Követező játékos");
        nextPlayer.addActionListener(e -> {
            Ctekton1=null;
            Ctekton2=null;
            alert2.setText("");
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
        customComponent.add(Box.createVerticalStrut(10));

        JLabel alert=new JLabel();
        customComponent.add(alert);
        customComponent.add(Box.createVerticalStrut(10));


        customComponent.add(alert2);
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
                alert.setText("");
                if(Ctekton1==null){                                                                                     //beallitja az elso kattintas tektonjat
                    Ctekton1=map.getTektonbyClick(e.getX(), e.getY());
                    alert2.setText("Elso tekton beallitva");
                }
                else if(Ctekton2==null){                                                                                //beallitja a masodik kattintas tektonjat
                    Ctekton2=map.getTektonbyClick(e.getX(), e.getY());
                    alert2.setText("masodik tekton beallitva");
                }

                if(Ctekton1==Ctekton2 && Ctekton2!=null){                                                               //ketto kattintas ugyan oda
                    if(map.currentPlayer.getIsInsect()){                                                                //A rovarral
                        if(!map.currentPlayer.getCurrentTekton().contains(Ctekton1.getTekton())){                       //Ez nem a jelenlegi tektonja volt
                            if(map.currentPlayer.move(Ctekton1.getTekton())==null){                                     //Sikerult mozogni
                                alert2.setText("mozgas megtortent");
                            }
                            else{                                                                                       //Nem sikerult mozogni
                                alert.setText("Erre nincs lehetőséged!");
                            }
                        }
                        else{                                                                                           //A kijelolt tekton a jelenlegi tektonja
                            if(!map.currentPlayer.interactWithSpore(Ctekton1.getTekton().getSpores())){                 //Nem sikerult sporat enni, Hogyan valasszuk ki random a sporat?
                                alert.setText("Erre nincs lehetőséged!");
                            }
                            else{                                                                                       //Sikeres spora eves
                                alert2.setText("spora eves megtortent");
                            }
                        }
                    }
                    else{                                                                                               //Gomba ketszer nyom ugyan oda
                        if(!map.currentPlayer.getCurrentTekton().contains(Ctekton1.getTekton()) || Ctekton1.getTekton().getSpores().size()<3) {     //Ez nem a jelenlegi tektonja volt

                            if(map.currentPlayer.move(Ctekton1.getTekton())==null){                                     //Nem sikerult sporat loni
                                alert.setText("Erre nincs lehetőséged!");
                            }
                            else{                                                                                       //Sikerult sporat loni
                                alert2.setText("spora szoras megtortent "+Ctekton1.getTekton().getSpores().getLast().getClass().getSimpleName());
                            }

                        }
                        else{                                                                                           //a jelenlegi tektonjara nyomott(benne van a currenttekton listaban pl: yarn miatt)
                            if(!map.currentPlayer.interactWithSpore(Ctekton2.getTekton().getSpores())){                 //Nem tud gombat noveszteni oda
                                alert.setText("Erre nincs lehetőséged!");
                            }
                            else{                                                                                       //Sikerult gombat noveszteni
                                alert2.setText("gomba novesztes megtortent");
                            }
                        }
                    }
                    Ctekton1=null;                                                                                      //visszaallitja a kijelolt tektonokat a muvelet utan
                    Ctekton2=null;
                }

                if(Ctekton1!=Ctekton2 && Ctekton2!=null && Ctekton1!=null){                                             //ketto kattintas kulonbozo tektonra
                    if(map.currentPlayer.getCurrentTekton().contains(Ctekton1.getTekton()) || map.currentPlayer.getCurrentTekton().contains(Ctekton2.getTekton())) {     //A ket kijelolt tekton kozul valamelyik tekton az ove
                        if (map.currentPlayer.getIsInsect()) {                                                          //A rovarral
                            for (Yarn y : Ctekton1.getTekton().getYarns()) {
                                if (y.getTekton1() == Ctekton2.getTekton() || y.getTekton2() == Ctekton2.getTekton()) { //Kikeresi azt a yarn ami a ketto kijelolt tekton kozott megy
                                    if (!map.currentPlayer.interactWithYarn(y)) {                                       //Nem sikerult fonalat ragni
                                        alert.setText("Erre nincs lehetőséged!");
                                    }
                                    else{                                                                               //Sikerult fonalat ragni
                                        alert2.setText("yarn elragas megtortent");
                                    }
                                    break;
                                }
                            }
                        } else {                                                                                        //A gombaval
                            Yarn y = new Yarn();
                            y.setTekton1(Ctekton1.getTekton());                                                         //letrehozza es beallitja a yarnt (mivel az interactwithyarn mar csak a yarn-t keri)
                            y.setTekton2(Ctekton2.getTekton());
                            y.setShroomPlayerId(map.currentPlayer.getPlayer_id());
                            if (!map.currentPlayer.interactWithYarn(y)) {                                               //Nem sikerult fonalat noveszteni
                                alert.setText("Erre nincs lehetőség!");
                            }
                            else{                                                                                       //Sikerult fonalat noveszteni
                                alert2.setText("yarn novesztes megtortent");
                            }

                        }
                    }
                    Ctekton1=null;                                                                                      //visszaallitja a kijelolt tektonokat a muvelet utan
                    Ctekton2=null;
                }
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