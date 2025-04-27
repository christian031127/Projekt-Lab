package org.example;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.logging.*;
import org.example.*;


public class Teszt {

    public static Tekton T1 = new Tekton();
    public static Tekton T2 = new Tekton();


    public static Logger logger = Logger.getLogger("TesztLogger");
    public static Scanner scanner = new Scanner(System.in);

    //Mesterséges Tároló a tesztesetek változóinak kezelésére
    private static HashMap<String, Object> gameObjectList = new HashMap<String,Object>();
    static String Name;
    public static void main(String[] args) throws Exception {

        //ConsoleHandler consoleHandler = new ConsoleHandler();
        //logger.addHandler(consoleHandler);
        logger.setLevel(Level.INFO);
        


        FileHandler fileHandler = new FileHandler("logfile.log");  // true: append, hogy hozzáfűzze a logokat
        fileHandler.setLevel(Level.ALL);  // Minden log szint rögzítése a fájlban
        logger.addHandler(fileHandler);

        SimpleFormatter formatter = new SimpleFormatter() {
            @Override
            public synchronized String format(java.util.logging.LogRecord record) {
                String message = record.getMessage();
                Object[] params = record.getParameters();
                if (params != null && params.length > 0) {
                    // Paraméterek behelyettesítése a log üzenetbe
                    message = MessageFormat.format(message, params);
                }


                return "[" + record.getLevel() + "]: " + message + "\n";  // Csak a log üzenet, semmi extra információ
            }
        };
        fileHandler.setFormatter(formatter);



        String sorBe = "";
        String [] command = null;

        //Jelenleg a splitTekton() kivételével a gameObjectList kiváltja
        Map map = new Map();

        //System.out.println("Az exit paranccsal lehet kilépni");
        while(scanner.hasNext()){
            sorBe = scanner.nextLine();

            //Kommente figyelmen kívül hagyása
            if(sorBe.contains("#") || sorBe.contains("//")) {
                continue;
            }           
            
            command = sorBe.split(" ");
            switch(command[0]){
                case "add": {
                    Object uj = new Object();
                    //Van e elég argumentum?
                    if(command.length < 3){
                        logger.log(Level.SEVERE, "Not enough arguments for 'add' command! (minimum 2 argument)");
                        throw new Exception("Not enough arguments for 'add' command! (minimum 2 argument)");
                    }
                    if(command.length == 3){
                        switch (command[1]) {
                            case "Tekton":
                                uj = new Tekton();
                                Tekton uj2 = (Tekton)uj;
                                uj2.setStrategy(new MultipleYarnTekton());
                                Name=command[2];
                                break;
                            case "Shroom":
                                uj = new Shroom();
                                Name=command[2];
                                break;
                            
                            default:
                                logger.log(Level.SEVERE, "Cannot find gameObject type {0}!", command[1]);
                                throw new AssertionError();
                        }
                    }
                    if(command.length > 3){
                        switch (command[1]) {
                            case "Tekton":
                                //Ez a sor lehet nem azt csinálja amit kellene
                                uj = new Tekton();
                                Tekton uj2 = (Tekton)uj;
                                switch (command[3]) {
                                    case "AbsorbTekton":
                                        uj2= new Tekton();
                                        uj2.setStrategy(new AbsorbTekton());
                                        Name=command[2];
                                        break;
                                    case "SingleYarnTekton":
                                        uj2= new Tekton();
                                        uj2.setStrategy(new SingleYarnTekton());
                                        Name=command[2];
                                        break;
                                    case "NonShroomTekton":
                                        uj2= new Tekton();
                                        uj2.setStrategy(new NonShroomTekton());
                                        Name=command[2];
                                        break;
                                    case "KeepAliveTekton":
                                        uj2= new Tekton();
                                        uj2.setStrategy(new KeepAliveTekton());
                                        Name=command[2];
                                            break;
                                    default:

                                        throw new AssertionError();
                                }
                                break;
                            case "Spore":
                                switch (command[4]) {
                                    case "SlowingSpore":
                                        uj = new SlowingSpore();
                                        Name=command[2];
                                        break;
                                    case "NumbingSpore":
                                        uj = new NumbingSpore();
                                        Name=command[2];
                                        break;
                                    case "WeakeningSpore":
                                        uj = new WeakeningSpore();
                                        Name=command[2];
                                        break;
                                    case "AccelerationSpore":
                                        uj = new AccelerationSpore();
                                        Name=command[2];
                                            break;
                                    case "SplitterSpore":
                                        uj = new SplitterSpore();
                                        Name=command[2];
                                            break;
                                    default:
                                        throw new AssertionError();
                                }

                                Tekton t8 = (Tekton) gameObjectList.get(command[3]);
                                t8.addSpore((Spore)uj);

                                break;
                            case "Player":
                                if (command.length != 5) {
                                    throw new Exception("add Player takes 3 argument!");
                                }   
                                if(!command[4].equals("Shroomer") && !command[4].equals("Insect")){
                                    throw new Exception("add Player invalid argument: only [Shroomer/Insect] allowed!");
                                }

                                Player p1 = new Player();
                                p1.setIsInsect(command[4].equals("Insect"));
                                Tekton t1 = (Tekton) gameObjectList.get(command[3]);
                                p1.setCurrentTekton(t1);

                                uj = p1;
                                Name = command[2];

                                break;

                            case "Yarn":
                                if (command.length < 4){
                                    throw new Exception("add Yarn not enough arguments!");
                                }
                                uj = new Yarn();
                                Yarn uj3 = (Yarn) uj;
                                Tekton t3 = (Tekton)gameObjectList.get(command[2]);
                                Tekton t4 = (Tekton)gameObjectList.get(command[3]);
                                uj3.setTekton1(t3);
                                uj3.setTekton2(t4);

                                t3.addYarn(uj3);
                                t4.addYarn(uj3);

                                Name= command[2];
                                break;
                            default:
                                logger.log(Level.SEVERE, "Cannot find gameObject type {0}!", command[1]);
                                throw new AssertionError();
                        }
                    }
                    gameObjectList.put(Name,uj);
                    logger.log(Level.INFO, "Adding new gameObject {0}!", command[2]);
                    break;   
                }

                case "neighbour": {
                    if(command.length != 3){
                        logger.log(Level.SEVERE, "Command neighbour takes 2 arguments!");
                        throw new Exception("Command neighbour takes 2 arguments!");
                    }
                    Tekton elso = (Tekton)gameObjectList.get(command[1]);
                    Tekton masodik = (Tekton)gameObjectList.get(command[2]);

                    elso.addNeighbour((Tekton)masodik);
                    masodik.addNeighbour((Tekton)elso);
                    logger.log(Level.INFO, "Tekton " + command[1] + " Tekton " + command[2] + " are now neighbours!");
                    break;
                }
                case "yarn": {
                    if(command.length < 3 || command.length > 6){
                        logger.log(Level.SEVERE, "Command yarn takes 3 arguments!");
                        throw new Exception("Command yarn takes 3 arguments!");
                    }
                    Yarn yarn = new Yarn();
                    //Tektonon fonál növesztés
                    if(command.length == 4){
                        Player p1 = (Player) gameObjectList.get(command[1]);
                        Tekton T1 = (Tekton) gameObjectList.get(command[3]);
                        yarn.setTekton1(T1);
                        yarn.setTekton2(T1);
                        T1.addYarn(yarn);
                        p1.interactWithYarn(yarn);

                        logger.log(Level.INFO, "Yarn created on Tekton {0}", command[3]);
                    }
                    if(command.length == 5){
                        Player p1 = (Player) gameObjectList.get(command[1]);
                        Tekton T1 = (Tekton)gameObjectList.get(command[3]);
                        Tekton T2 = (Tekton)gameObjectList.get(command[4]);
                        yarn.setTekton1(T1);
                        yarn.setTekton2(T2);
                        T1.addYarn(yarn);
                        T2.addYarn(yarn);
                        p1.interactWithYarn(yarn);
                        
                        logger.log(Level.INFO, "Yarn created between Tekton {0}", command[3] + " and Tekton " + command[4]);
                        
                    }
                    gameObjectList.put(command[2], yarn);
                    break;     
                } 
                case "remove-yarn": {
                    if(command.length < 2 || command.length > 3){
                        logger.log(Level.SEVERE, "Command remove-yarn takes 2 arguments!");
                        throw new Exception("Command remove-yarn takes 2 arguments!");
                    }

                    //Tektonon fonál törlés
                    if(command.length == 2){
                        Tekton tekton = (Tekton)gameObjectList.get(command[1]);
                        List<Yarn> yarns = tekton.getYarns();
                        Yarn talalat = null;
                        for (Yarn yarn : yarns) {
                            if(yarn.getTekton1() == tekton && yarn.getTekton2() == tekton){
                                talalat = yarn;
                            }
                        }
                        if(talalat == null){
                            logger.log(Level.SEVERE, "Cannot find yarn on Tekton {0}!", command[1]);
                            throw new Exception("Cannot find yarn on Tekton!");
                        }
                        talalat.setTekton1(null);
                        talalat.setTekton2(null);
                        tekton.removeYarn(talalat);

                        //megszüntetett Yarn kitörlése, hogy a gc működjön
                        gameObjectList.remove(command[1]);
                        logger.log(Level.INFO, "Yarn removed on Tekton {0}", command[1]);
                        throw new Exception("Befejezetlen funkció!");
                    }
                    if(command.length == 3){
                        Tekton tekton1 = (Tekton)gameObjectList.get(command[1]);
                        Tekton tekton2 = (Tekton)gameObjectList.get(command[2]);
                        List<Yarn> yarns1 = tekton1.getYarns();
                        List<Yarn> yarns2 = tekton2.getYarns();
                        Yarn talalat = null;
                        for (Yarn elso : yarns1) {
                            for (Yarn masodik : yarns2) {
                                if(elso == masodik){
                                    talalat = elso;
                                    logger.log(Level.FINE, "Talált egyező yarnt!");
                                }
                            }
                        }
                        
                        if(talalat == null){
                            logger.log(Level.SEVERE, "Cannot find yarn between 2 given Tektons!");
                            throw  new Exception("Cannot find yarn between 2 given Tektons!");
                        }
                        talalat.setTekton1(null);
                        talalat.setTekton2(null);
                        tekton1.removeYarn(talalat);
                        tekton2.removeYarn(talalat);

                        //megszüntetett Yarn kitörlése, hogy a gc működjön
                        gameObjectList.remove(command[1]);
                        logger.log(Level.INFO, "Yarn removed between Tekton {0}", command[1] + " and Tekton " + command[2]);
                    }
                    break;   
                }
                case "split": {
                    if(command.length != 4){
                        logger.log(Level.SEVERE, "Command split takes 3 arguments!");
                        throw new Exception("Command split takes 3 arguments!");
                    }

                    Tekton eredeti = (Tekton)gameObjectList.get(command[1]);
                    eredeti.split();

                    Tekton elso = new Tekton();
                    Tekton masodik = new Tekton();
                    elso.setStrategy(new MultipleYarnTekton());
                    masodik.setStrategy(new MultipleYarnTekton());
                    elso.addNeighbour(masodik);
                    masodik.addNeighbour(elso);

                    gameObjectList.put(command[2], elso);
                    gameObjectList.put(command[3], masodik);

                    //Eredeti szomszédokkal mi legyen?

                    break;   
                }   
                case "move": {
                    if(command.length != 3){
                        logger.log(Level.SEVERE, "Command move takes 3 arguments!");
                        throw new Exception("Command move takes 3 arguments!");
                    }

                    Player player = (Player)gameObjectList.get(command[1]);
                    Tekton celpont = (Tekton)gameObjectList.get(command[2]);

                    Spore ret = player.move(celpont);
                    if(ret == null) {
                        logger.log(Level.INFO, "Player mozgatása sikeresen megtörtént Tekton {0}-ra!", command[2]);
                    } else {
                        logger.log(Level.WARNING, "Failed to move Player {0}", String.valueOf(command[1]) + " to Tekton " + String.valueOf(command[2]));
                    }
                    // A move függvény ellenőrzi
                    //logger.warning("Ellenőrizni, kell, hogy tud-e a megadott tektonra mozogni!")
                    break;
                }
                case "eat": {

                    //A player csak a jelen tektonról tud fogyasztani
                    Player player = (Player)gameObjectList.get(command[3]);
                        
                    switch (command[1]) {
                        case "Spore":
                            Spore spore = (Spore)gameObjectList.get(command[2]);
                            player.getCurrentTekton().removeSpore(spore);
                            //Ha nem sikerül a spóra evés, akkor a .removeSpore() nak kell kivételt dobnia!
                            break;

                        case "Yarn":
                            Yarn yarn = (Yarn)gameObjectList.get(command[2]);
                            boolean ret = player.getCurrentTekton().removeYarn(yarn);
                            if(!ret) {
                                logger.log(Level.WARNING, "Sikertelen fonal elrágás: A fonal nem létezik!");
                            } else {
                                logger.log(Level.INFO, "Sikeres fonal elrágás!");
                            }
                            //Ha nem sikerül a yarn evés, akkor a .removeYarn() nak kell kivételt dobnia!
                            break;
                        default:
                            throw new AssertionError();
                    }

                    break;      
                }

                case "eject":{
                    if(command.length != 4){
                        logger.log(Level.SEVERE, "Command move takes 4 arguments!");
                        throw new Exception("Command move takes 4 arguments!");
                    }
                    
                    Player p1 = (Player) gameObjectList.get(command[3]);
                    Tekton t1 = (Tekton) gameObjectList.get(command[2]);
                    Spore spore = p1.move(t1);
                    gameObjectList.put(command[1], spore);

                    break;
                }
                case "grow":{
                    if(command.length != 5){
                        logger.log(Level.SEVERE, "Command move takes 4 arguments!");
                        throw new Exception("Command move takes 4 arguments!");
                    }
                    Shroom s = new Shroom();
                    Player p1 = (Player) gameObjectList.get(command[3]);
                    Tekton t1 = (Tekton) gameObjectList.get(command[2]);
                    
                    p1.setCurrentTekton(t1);
                    p1.interactWithSpore(t1.getSpores());

                    int kor = Integer.parseInt(command[4]);
                    for(int i = 0; i < kor; i++){
                        s.age();
                    }
                    gameObjectList.put(command[1], s);

                    break;
                }
                default:{
                    //HELP
                    logger.log(Level.WARNING, "Command {0} not found!", command[0]);
                    break;      
                }
            }
        }
        System.out.println("Goodbye...");
    }
}