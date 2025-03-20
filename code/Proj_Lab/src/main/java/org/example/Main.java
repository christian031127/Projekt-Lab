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
            System.out.println("1. Fonál esemény");
            System.out.println("2. Mozgás esemény");
            System.out.println("3. Spóra esemény");
            System.out.println("4. Gomba halál");
            System.out.println("5. Fonál felszívódás");
            System.out.println("6. Tekton kettéválás");
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
                    // ...
                    break;
                }
                case 6: {
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
        scanner.close();
        valasz = valasz.toUpperCase();
        
        if(valasz.equals("Y") || valasz.equals("I")){
            return true;
        }
        else{
            return false;
        }
    }
}