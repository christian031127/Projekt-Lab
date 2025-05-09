package org.example;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Map {

    private static Logger logger = Logger.getLogger("TesztLogger");

    public Player currentPlayer;
    private static HashMap<String, Object> Tektons = new HashMap<String, Object>();
    private static HashMap<String, Object> Players = new HashMap<String, Object>();
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
                    Object uj = new Object();
                    //Van e elég argumentum?
                    if (command.length < 3) {
                        logger.log(Level.SEVERE, "Not enough arguments for 'add' command! (minimum 2 argument)");
                        throw new Exception("Not enough arguments for 'add' command! (minimum 2 argument)");
                    }
                    if (command.length == 3) {
                        switch (command[1]) {
                            case "Tekton":
                                uj = new Tekton();
                                Tekton uj2 = (Tekton) uj;
                                uj2.setStrategy(new MultipleYarnTekton());
                                Name = command[2];
                                Tektons.put(Name, uj);
                                break;

                            default:
                                logger.log(Level.SEVERE, "Cannot find gameObject type {0}!", command[1]);
                                throw new AssertionError();
                        }
                    }
                    if (command.length > 3) {
                        switch (command[1]) {
                            case "Tekton":
                                //Ez a sor lehet nem azt csinálja amit kellene
                                uj = new Tekton();
                                Tekton uj2 = (Tekton) uj;
                                switch (command[3]) {
                                    case "AbsorbTekton":
                                        uj2 = new Tekton();
                                        uj2.setStrategy(new AbsorbTekton());
                                        Name = command[2];
                                        break;
                                    case "SingleYarnTekton":
                                        uj2 = new Tekton();
                                        uj2.setStrategy(new SingleYarnTekton());
                                        Name = command[2];
                                        break;
                                    case "NonShroomTekton":
                                        uj2 = new Tekton();
                                        uj2.setStrategy(new NonShroomTekton());
                                        Name = command[2];
                                        break;
                                    case "KeepAliveTekton":
                                        uj2 = new Tekton();
                                        uj2.setStrategy(new KeepAliveTekton());
                                        Name = command[2];
                                        break;
                                    case "MultipleYarnTekton":
                                        uj2 = new Tekton();
                                        uj2.setStrategy(new MultipleYarnTekton());
                                        Name = command[2];
                                        break;
                                    default:

                                        throw new AssertionError();
                                }
                                Tektons.put(Name, uj);
                                break;

                            case "Player":
                                if (command.length != 5) {
                                    throw new Exception("add Player takes 3 argument!");
                                }

                                Player p1 = new Player();
                                p1.setIsInsect(command[4].equals("Insect"));
                                Tekton t1 = (Tekton) Tektons.get(command[3]);
                                p1.setCurrentTekton(t1);

                                uj = p1;
                                Name = command[2];
                                Players.put(Name, uj);
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
                    Tekton elso = (Tekton) Tektons.get(command[1]);
                    Tekton masodik = (Tekton) Tektons.get(command[2]);

                    elso.addNeighbour((Tekton) masodik);
                    masodik.addNeighbour((Tekton) elso);
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
        Tektons.values().remove(tekton);
        Tekton n1 = new Tekton();
        Tekton n2 = new Tekton();

        n1.addNeighbour(n2);
        n2.addNeighbour(n1);
        for(Tekton tekton1 : neighbours){
            n1.addNeighbour(tekton1);
            n2.addNeighbour(tekton1);
        }
        Tektons.put("T"+Tektons.size(),n1);
        Tektons.put("T"+Tektons.size(),n2);

    }
    public void draw(Graphics g) {}
}
