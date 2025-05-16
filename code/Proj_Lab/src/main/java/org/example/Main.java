package org.example;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.*;

public class Main {

    // Add static variables here
    public static Logger logger = Logger.getLogger("MainLogger");
    public static Tekton T1 = new Tekton();
    public static Tekton T2 = new Tekton();
    public static Scanner scanner = new Scanner(System.in);
    // ...

    public static void main(String[] args) {
        int user_input = 0;

        while (user_input >= 0) {
            System.out.println("Tesztek leírása... -1: Exit\n");
            System.out.println("1. Fonál növesztés"); //2opciót felajánl: másik tektonra útként/ tektonra rá
            System.out.println("2. Fonál elrágás");
            System.out.println("3. Fonál felszívódás");
            System.out.println("4. Gomba növesztés spórából");
            System.out.println("5. Spóra szórás gombával");
            System.out.println("6. Rovar mozgás");
            System.out.println("7. Spóra elfogyasztása rovarral"); //eset utána
            System.out.println("8. Gomba halál");
            System.out.println("9. Tekton kettéválás");
            System.out.println("Choose option: ");

            try {
                user_input = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Wrong input!");
                continue;
            }

            switch (user_input) {
                case 1: {
                    new Player().interactWithYarn(new Yarn());
                    System.out.println("Teszt sikeresen lefutott.");
                    break;
                }
                case 2: {
                    new Player().getCurrentTekton().getFirst().getYarns();
                    new Player().interactWithYarn(new Yarn());
                    System.out.println("Teszt sikeresen lefutott.");
                    break;
                }
                case 3: {
                    new Tekton().doEffect();
                    System.out.println("Teszt sikeresen lefutott.");
                    break;
                }
                case 4: {
                    new Player().getCurrentTekton().getFirst().getSpores();
                    new Player().interactWithSpore(new ArrayList<Spore>());
                    System.out.println("Teszt sikeresen lefutott.");
                    break;
                }
                case 5, 6: {
                    try {
                        new Player().move(new Tekton());
                        System.out.println("Teszt sikeresen lefutott.");
                    } catch(Exception e){
                        logger.log(Level.SEVERE, "Player could not move to Tekton.");
                    }
                    break;
                }
                case 7: {
                    new Player().interactWithSpore(new Tekton().getSpores());
                    System.out.println("Teszt sikeresen lefutott.");
                    break;
                }
                case 8: {
                    new Shroom().age();
                    System.out.println("Teszt sikeresen lefutott.");
                    break;
                }
                case 9: {
                    new Map().splitTekton(new Tekton());
                    System.out.println("Teszt sikeresen lefutott.");
                    break;
                }
                // ...
                default: {
                    System.out.println("Unknown option.");
                }
            }
            System.out.print("\n\n");
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
}