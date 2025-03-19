package org.example;

import java.io.IOException;
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
}