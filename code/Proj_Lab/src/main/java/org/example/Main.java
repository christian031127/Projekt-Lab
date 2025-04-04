package org.example;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.*;
import org.example.*;


public class Main {

    public static Tekton T1 = new Tekton();
    public static Tekton T2 = new Tekton();


    public static Logger logger = Logger.getLogger("MainLogger");
    public static Scanner scanner = new Scanner(System.in);

    //Mesterséges Tároló a tesztesetek változóinak kezelésére
    private static ArrayList<GameObject> gameObjectList = new ArrayList<GameObject>();
    public static void main(String[] args) throws Exception {

        String sorBe = "";
        String [] command = null;

        //Jelenleg a splitTekton() kivételével a gameObjectList kiváltja
        Map map = new Map();

        System.out.println("Az exit paranccsal lehet kilépni");
        while(!(sorBe = scanner.nextLine()).equals("exit")){

            System.out.println("soros" + sorBe);
            command = sorBe.split(" ");

            switch(command[0]){
                case "add": {
                    GameObject uj = new GameObject();
                    //Van e elég argumentum?
                    if(command.length < 3){
                        logger.log(Level.SEVERE, "Not enough arguments for 'add' command! (minimum 2 argument)");
                        throw new Exception("Not enough arguments for 'add' command! (minimum 2 argument)");
                    }
                    if(command.length == 3){
                        switch (command[1]) {
                            case "Tekton":
                                uj = new Tekton();
                                uj.SetName(command[2]);
                                break;
                            case "Shroom":
                                uj = new Shroom();
                                uj.SetName(command[2]);
                                break;
                            case "Yarn":
                                uj = new Yarn();
                                uj.SetName(command[2]);
                                break;
                            default:
                                logger.log(Level.SEVERE, "Cannot find gameObject type {0}!", command[1]);
                                throw new AssertionError();
                        }
                    }
                    if(command.length > 3){
                        switch (command[1]) {
                            case "Tekton":
                                switch (command[3]) {
                                    case "AbsorbTekton":
                                        uj = new Tekton();
                                        uj.SetName(command[2]);
                                        uj.setStrategy(new AbsorbTekton());
                                        break;
                                    case "SingleYarnTekton":
                                        uj = new Tekton();
                                        uj.SetName(command[2]);
                                        uj.setStrategy(new SingleYarnTekton());
                                        break;
                                    case "NonShroomTekton":
                                        uj = new Tekton();
                                        uj.SetName(command[2]);
                                        uj.setStrategy(new NonShroomTekton());
                                        break;
                                    case "KeelAliveTekton":
                                        
                                        break;
                                    default:

                                        throw new AssertionError();
                                }
                                break;
                            case "Spore":
                                switch (command[3]) {
                                    case "SlowingSpore":
                                        uj = new SlowingSpore();
                                        uj.SetName(command[2]);
                                        break;
                                    case "NumbingSpore":
                                        uj = new NumbingSpore();
                                        uj.SetName(command[2]);
                                        break;
                                    case "WeakeningSpore":
                                        uj = new WeakeningSpore();
                                        uj.SetName(command[2]);
                                        break;
                                    case "AccelerationSpore":
                                        uj = new AccelerationSpore();
                                        uj.SetName(command[2]);
                                            break;
                                    case "SplitterSpore":
                                        //uj = new SplitterSpore();
                                        //uj.SetName(command[2]);
                                            break;
                                    default:

                                        throw new AssertionError();
                                }
                                break;
                            default:
                                logger.log(Level.SEVERE, "Cannot find gameObject type {0}!", command[1]);
                                throw new AssertionError();
                        }
                    }

                    gameObjectList.add(uj);
                    logger.log(Level.INFO, "Adding ");
                    break;   
                }

                case "neighbour": {

                    break;
                }
                case "yarn": {
                
                    break;     
                } 
                case "remove-yarn": {

                    break;   
                }
                case "split": {

                    break;   
                }   
                case "move": {

                    break;      
                }
                case "eat": {

                    break;      
                }
                default:{
                    //HELP
                    break;      
                }
            }
        }
        System.out.println("Goodbye...");
    }

    public static boolean igazHamisKerdes(String kerdes) {
        System.out.print(kerdes + " [Y/n][I/n]: ");
        String valasz = scanner.nextLine();

        valasz = valasz.toUpperCase();

        if (valasz.equals("Y") || valasz.equals("I")) {
            return true;
        } else {
            return false;
        }
    }

    private static GameObject findGameObject(String name) throws Exception{
        for (GameObject gameObject : gameObjectList) {
            if (gameObject.name.equals(name)) {
                return gameObject;
            }
        }
        logger.log(Level.SEVERE, "Cannot find gameObject called: {0}", name);
        throw new Exception("Nem található " + name + " nevű gameObject!");
    }
}