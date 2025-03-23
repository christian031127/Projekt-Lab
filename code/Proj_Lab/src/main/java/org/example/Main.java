package org.example;

import java.util.Scanner;

public class Main {

    // Add static variables here
    public static Tekton T1;
    // ...

    public static void main(String[] args) {
        int user_input = 0;
        Scanner scanner = new Scanner(System.in);

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
            }

            switch(user_input) {
                case 1: {
                    callback1();
                    break;
                }
                case 2: {
                    for(Yarn yarn : new Player().getCurrentTekton().getYarns()) {
                        if(yarn.getTekton1() == new Tekton() || yarn.getTekton2() == new Tekton()) {
                            new Player().interactWithYarn(yarn);
                        }
                    }


                    break;
                }
                case 3: {
                    new Tekton().doEffect();
                    new Tekton().getStrategy().doEffect();
                    for(Yarn yarn : new Tekton().getYarns()) {
                        if(yarn.getTekton1() == new Tekton() || yarn.getTekton2() == new Tekton()) {
                            new Tekton().removeYarn(yarn);
                        }
                    }

                    break;
                }
                case 4: {
                    new Player().interactWithSpore(new Tekton().getSpores());
                    break;
                }
                case 8: {
                    while(!new Shroom().isOld()) {
                        new Shroom().age();
                    }
                    new Shroom().die();
                    break;
                }
                case 9: {
                    new Tekton().split();
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

    public static void callback1() {
        System.out.println("Hello, I'm testing!");
    }

    public static boolean igazHamisKerdes(String kerdes){
        System.out.print(kerdes + " [Y/n][I/n]:");

        Scanner scanner = new Scanner(System.in);
        String valasz = scanner.nextLine();
        
        valasz = valasz.toUpperCase();
        
        if(valasz.equals("Y") || valasz.equals("I")){
            return true;
        }
        else{
            return false;
        }
    }
}