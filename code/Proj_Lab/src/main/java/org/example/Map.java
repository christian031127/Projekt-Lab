package org.example;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.*;

public class Map extends JPanel{

    private static Logger logger = Logger.getLogger("TesztLogger");

    public Player currentPlayer;
    private static HashMap<String, GraphicsTekton> Tektons = new HashMap<String, GraphicsTekton>();
    private static HashMap<String, GraphicsPlayer> Players = new HashMap<String, GraphicsPlayer>();
    static String Name;

    public static int currentTurn = 0;

    public void loadMap() throws Exception {
        // read file, load map
        Scanner scanner = null;
        try {
            File file = new File("map.txt");
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            logger.log(Level.SEVERE, "Cannot find Map");
        }
        String[] command = null;
        while (scanner.hasNextLine()) {

            String line = scanner.nextLine();
            //Kommente figyelmen kívül hagyása
            if (line.contains("#") || line.contains("//")) {
                continue;
            }
            command = line.split(" ");

            switch (command[0]) {
                case "add": {
                    Tekton uj = new Tekton();
                    //Van e elég argumentum?
                    if (command.length < 3) {
                        logger.log(Level.SEVERE, "Not enough arguments for 'add' command! (minimum 2 argument)");
                        throw new Exception("Not enough arguments for 'add' command! (minimum 2 argument)");
                    }
                    if (command.length == 5) {
                        switch (command[1]) {
                            case "Tekton":
                                uj.setStrategy(new MultipleYarnTekton());
                                Name = command[2];
                                GraphicsTekton t1=new GraphicsTekton(Integer.parseInt(command[3]),Integer.parseInt(command[4]),uj);
                                Tektons.put(Name, t1);
                                break;

                            default:
                                logger.log(Level.SEVERE, "Cannot find gameObject type {0}!", command[1]);
                                throw new AssertionError();
                        }
                    }
                    if (command.length > 5) {
                        switch (command[1]) {
                            case "Tekton":
                                //Ez a sor lehet nem azt csinálja amit kellene

                                switch (command[3]) {
                                    case "AbsorbTekton":
                                        uj.setStrategy(new AbsorbTekton());
                                        Name = command[2];
                                        break;
                                    case "SingleYarnTekton":
                                        uj.setStrategy(new SingleYarnTekton());
                                        Name = command[2];
                                        break;
                                    case "NonShroomTekton":
                                        uj.setStrategy(new NonShroomTekton());
                                        Name = command[2];
                                        break;
                                    case "KeepAliveTekton":
                                        uj.setStrategy(new KeepAliveTekton());
                                        Name = command[2];
                                        break;
                                    case "MultipleYarnTekton":
                                        uj.setStrategy(new MultipleYarnTekton());
                                        Name = command[2];
                                        break;
                                    default:

                                        throw new AssertionError();
                                }
                                GraphicsTekton t=new GraphicsTekton(Integer.parseInt(command[4]),Integer.parseInt(command[5]),uj);
                                Tektons.put(Name, t);
                                break;

                            case "Player":
                                if (command.length != 7) {
                                    throw new Exception("add Player takes 3 argument!");
                                }
                                Player p1 = new Player();
                                p1.setIsInsect(command[4].equals("Insect"));
                                GraphicsTekton t1 =Tektons.get(command[3]);
                                p1.setCurrentTekton(t1.getTekton());
                                if(command[4].equals("Shroomer")){
                                    p1.getCurrentTekton().addShroom(new Shroom());
                                }
                                Name = command[2];
                                GraphicsPlayer p=new GraphicsPlayer(Integer.parseInt(command[5]),Integer.parseInt(command[6]),p1);
                                Players.put(Name, p);
                                break;
                            default:
                                logger.log(Level.SEVERE, "Cannot find gameObject type {0}!", command[1]);
                                throw new AssertionError();
                        }
                    }

                    logger.log(Level.INFO, "Adding new gameObject {0}!", command[2]);
                    break;
                }

                case "neighbour": {
                    if (command.length != 3) {
                        logger.log(Level.SEVERE, "Command neighbour takes 2 arguments!");
                        throw new Exception("Command neighbour takes 2 arguments!");
                    }
                    GraphicsTekton elso = Tektons.get(command[1]);
                    GraphicsTekton masodik =Tektons.get(command[2]);

                    elso.getTekton().addNeighbour(masodik.getTekton());
                    masodik.getTekton().addNeighbour(elso.getTekton());
                    logger.log(Level.INFO, "Tekton " + command[1] + " Tekton " + command[2] + " are now neighbours!");
                    break;
                }

                default: {
                    //HELP
                    logger.log(Level.WARNING, "Command {0} not found!", command[0]);
                    break;
                }
            }
        }

    }
    public void splitTekton(Tekton tekton){
        logger.log(Level.FINE,"Map.splitTekton() called");
        List<Tekton> neighbours = tekton.getNeighbours();
        tekton.split();

        for (GraphicsTekton t:Tektons.values()){
            if(t.getTekton().equals(tekton)){
                Tektons.values().remove(t);
                break;
            }
        }

        GraphicsTekton t3 = new GraphicsTekton(0,0,new Tekton());
        GraphicsTekton t4 = new GraphicsTekton(0,0,new Tekton());
        t3.getTekton().addNeighbour(t4.getTekton());
        t4.getTekton().addNeighbour(t3.getTekton());

        for(Tekton tf : neighbours){
            t3.getTekton().addNeighbour(tf);
            t4.getTekton().addNeighbour(tf);
        }
        Tektons.put("T"+Tektons.size()+1,t3);
        Tektons.put("T"+Tektons.size()+2,t4);


    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        //Itt kell végig iterálni és meghívni minden hashmap lakoson a draw függvényt és g t átadni neki
        //Először tektonokat kell kirajzolni
        //Utánna a playereket , mert felülrajzolja.
        for (GraphicsTekton t:Tektons.values()){
            t.draw((Graphics2D) g);
        }
        for (GraphicsPlayer p:Players.values()){
            p.draw((Graphics2D) g);
        }
        //if (image != null) {
        //    g.drawImage(image, 0, 0, this);
        //}
    }

    public void nextPlayer(){
        boolean b=false;
        List<GraphicsPlayer> playerList = new ArrayList<>(Players.values());
        for (int i = 0; i < playerList.size(); i++) {
            if (playerList.get(i).getPlayer().equals(currentPlayer)) {
                int nextIndex = (i + 1) % playerList.size();
                currentPlayer = playerList.get(nextIndex).getPlayer();
                break;
            }
        }
    }
}
