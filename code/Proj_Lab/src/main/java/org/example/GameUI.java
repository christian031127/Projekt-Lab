package org.example;
import java.awt.*;
import javax.imageio.*;
import javax.swing.*;

import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.border.EmptyBorder;
public class GameUI {

    private static Map map = new Map();
    private static GraphicsTekton Ctekton1=null;
    private static GraphicsTekton Ctekton2=null;

    public static void main(String[] args) throws Exception {

        // Alap frame beállítás
        JFrame frame = new JFrame("Data_daddies Fungórium");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 600);
        frame.setMinimumSize(new Dimension(700, 500));
        frame.setLocationRelativeTo(null); // Középre hozás
        frame.setLayout(new BorderLayout());

        // Színpaletta
        Color bgColor = new Color(245, 245, 250);
        Color panelBg = new Color(230, 230, 240);
        Color btnColor = new Color(70, 130, 180);
        Color btnHover = new Color(90, 150, 200);
        Color textColor = new Color(40, 40, 40);

        // Bal panel menü
        JPanel leftPanel = new JPanel();
        leftPanel.setPreferredSize(new Dimension(300, 600));
        leftPanel.setBackground(panelBg);
        leftPanel.setLayout(new BorderLayout());

        // Menü komponens - BoxLayout függőlegesen
        JPanel menuPanel = new JPanel();
        menuPanel.setBackground(panelBg);
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
        menuPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        JLabel menuTitle = new JLabel("Menü");
        menuTitle.setFont(new Font("Segoe UI", Font.BOLD, 22));
        menuTitle.setForeground(textColor);
        menuTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel currentPlayerLabel = new JLabel("Nyomd meg a Start gombot!");
        currentPlayerLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        currentPlayerLabel.setForeground(textColor);
        currentPlayerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        currentPlayerLabel.setBorder(new EmptyBorder(10, 0, 20, 0));

        JLabel playerScore = new JLabel();
        playerScore.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        playerScore.setForeground(textColor);
        playerScore.setAlignmentX(Component.CENTER_ALIGNMENT);
        playerScore.setBorder(new EmptyBorder(10, 0, 20, 0));

        JLabel alert = new JLabel();
        alert.setFont(new Font("Segoe UI", Font.ITALIC, 14));
        alert.setForeground(new Color(200, 50, 50));
        alert.setAlignmentX(Component.CENTER_ALIGNMENT);
        alert.setBorder(new EmptyBorder(10, 0, 10, 0));

        JLabel alert2 = new JLabel();
        alert2.setFont(new Font("Segoe UI", Font.ITALIC, 14));
        alert2.setForeground(new Color(20, 120, 20));
        alert2.setAlignmentX(Component.CENTER_ALIGNMENT);
        alert2.setBorder(new EmptyBorder(10, 0, 20, 0));



        JButton nextPlayer = createStyledButton("Következő játékos");
        nextPlayer.setVisible(false);
        nextPlayer.addActionListener(e -> {
            Ctekton1 = null;
            Ctekton2 = null;
            alert2.setText("");

            map.nextPlayer();
            String s = switch (map.currentPlayer.getPlayer_id()) {
                case 1 -> "Piros gomba";
                case 2 -> "Barna gomba";
                case 3 -> "Barna rovar";
                case 4 -> "Piros rovar";
                default -> "";
            };
            playerScore.setText("Játékos pontja: " + map.currentPlayer.getScore());
            currentPlayerLabel.setText("Aktuális játékos: " + s);
            map.repaint();
        });

        JButton startButton = createStyledButton("Start");
        startButton.addActionListener(e -> {
            try {
                map.loadMap();
                map.repaint();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
            String s = switch (map.currentPlayer.getPlayer_id()) {
                case 1 -> "Piros gomba";
                case 2 -> "Barna gomba";
                case 3 -> "Barna rovar";
                case 4 -> "Piros rovar";
                default -> "";
            };
            nextPlayer.setVisible(true);
            startButton.setVisible(false);
            playerScore.setText("Játékos pontja: " + map.currentPlayer.getScore());
            currentPlayerLabel.setText("Aktuális játékos: " + s);
        });

        // Menü komponensek hozzáadása
        menuPanel.add(menuTitle);
        menuPanel.add(currentPlayerLabel);
        menuPanel.add(playerScore);
        menuPanel.add(alert);
        menuPanel.add(alert2);
        menuPanel.add(Box.createVerticalStrut(15));
        menuPanel.add(startButton);
        menuPanel.add(Box.createVerticalStrut(10));
        menuPanel.add(nextPlayer);

        leftPanel.add(menuPanel, BorderLayout.NORTH);

        // Jobb panel, ide jön a map
        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.setBackground(bgColor);
        rightPanel.add(map, BorderLayout.CENTER);
        map.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                alert.setText("");
                if(Ctekton1==null){                                                                                     //beallitja az elso kattintas tektonjat
                    Ctekton1=map.getTektonbyClick(e.getX(), e.getY());
                    alert2.setText("Az első Tekton ki lett választva!");
                }
                else if(Ctekton2==null){                                                                                //beallitja a masodik kattintas tektonjat
                    Ctekton2=map.getTektonbyClick(e.getX(), e.getY());
                    alert2.setText("A második Tekton ki lett választva!");
                }

                if(Ctekton1==Ctekton2 && Ctekton2!=null){                                                               //ketto kattintas ugyan oda
                    if(map.currentPlayer.getIsInsect()){                                                                //A rovarral
                        if(!map.currentPlayer.getCurrentTekton().contains(Ctekton1.getTekton())){                       //Ez nem a jelenlegi tektonja volt
                            if(map.currentPlayer.move(Ctekton1.getTekton())==null){                                     //Sikerult mozogni
                                alert2.setText("Sikeresen átmentél egy másik Tektonra!");
                            }
                            else{                                                                                       //Nem sikerult mozogni
                                alert.setText("Erre nincs lehetőséged!");
                            }
                        }
                        else{                                                                                           //A kijelolt tekton a jelenlegi tektonja
                            if(!map.currentPlayer.interactWithSpore(Ctekton1.getTekton().getSpores()) && Ctekton1.getTekton().getSpores().size()==0){                 //Nem sikerult sporat enni, Hogyan valasszuk ki random a sporat?
                                alert.setText("Erre nincs lehetőséged!");
                            }
                            else{                                                                                       //Sikeres spora eves
                                alert2.setText("Sikeresen megettél egy Spórát!");
                            }
                        }
                    }
                    else {                                                                                               //Gomba ketszer nyom ugyan oda
                        GraphicsPlayer p=map.playerOnTektonBelowEffect(Ctekton1);
                        if (p!=null) {
                            if(Ctekton1.getTekton().getYarns().getFirst().eatNumbInsect(p.getPlayer())){
                                alert2.setText("Sikeresen megetted a rovart!");
                            }
                            else{
                                alert.setText("Erre nincs lehetőséged!");
                            }
                        } else {


                            if (!map.currentPlayer.getCurrentTekton().contains(Ctekton1.getTekton()) || Ctekton1.getTekton().getSpores().size() < 3) {     //Ez nem a jelenlegi tektonja volt

                                if (map.currentPlayer.move(Ctekton1.getTekton()) != null) {                                     //Nem sikerult sporat loni
                                    alert.setText("Erre nincs lehetőséged!");
                                } else {                                                                                       //Sikerult sporat loni
                                    alert2.setText("Sikeresen spórát szórtál egy Tektonra!");
                                }

                            } else {                                                                                           //a jelenlegi tektonjara nyomott(benne van a currenttekton listaban pl: yarn miatt)
                                if (!map.currentPlayer.interactWithSpore(Ctekton2.getTekton().getSpores())) {                 //Nem tud gombat noveszteni oda
                                    alert.setText("Erre nincs lehetőséged!");
                                } else {                                                                                       //Sikerult gombat noveszteni
                                    alert2.setText("Sikeresen növesztettél egy Gombát!");
                                }
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
                                        alert2.setText("Sikeresen elrágtad a Fonalat!");
                                    }
                                    break;
                                }
                            }
                        } else {                                                                                        //A gombaval

                            if(Ctekton1.getTekton().getNeighbours().contains(Ctekton2.getTekton()) && Ctekton2.getTekton().getNeighbours().contains(Ctekton1.getTekton())){
                                Yarn y = new Yarn();
                                y.setTekton1(Ctekton1.getTekton());                                                         //letrehozza es beallitja a yarnt (mivel az interactwithyarn mar csak a yarn-t keri)
                                y.setTekton2(Ctekton2.getTekton());
                                if (!map.currentPlayer.interactWithYarn(y)) {                                               //Nem sikerult fonalat noveszteni
                                    y.setTekton1(null);
                                    y.setTekton2(null);
                                    y.setShroomPlayerId(0);
                                    alert.setText("Kifogytál a lépésekből");
                                }
                                else{                                                                                       //Sikerult fonalat noveszteni
                                    alert2.setText("Sikeresen növesztettél egy fonalat!");
                                }
                            }
                            else{
                                alert.setText("A két tekton nem szomszédos!");
                            }


                        }
                    }
                    Ctekton1=null;                                                                                      //visszaallitja a kijelolt tektonokat a muvelet utan
                    Ctekton2=null;
                }
                playerScore.setText("Játékos pontja: " + map.currentPlayer.getScore());
                map.repaint();
            }
            @Override public void mousePressed(MouseEvent e) {}
            @Override public void mouseReleased(MouseEvent e) {}
            @Override public void mouseEntered(MouseEvent e) {}
            @Override public void mouseExited(MouseEvent e) {}
        });

        frame.add(leftPanel, BorderLayout.WEST);
        frame.add(rightPanel, BorderLayout.CENTER);

        frame.setVisible(true);
    }
    private static JButton createStyledButton(String text) {
        Color btnColor = new Color(70, 130, 180);
        Color btnHover = new Color(90, 150, 200);

        JButton btn = new JButton(text);
        btn.setFocusPainted(false);
        btn.setBackground(btnColor);
        btn.setForeground(Color.white);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btn.setMaximumSize(new Dimension(200, 40));
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        btn.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn.setBackground(btnHover);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn.setBackground(btnColor);
            }
        });
        return btn;
    }
}