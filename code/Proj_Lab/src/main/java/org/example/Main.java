package org.example;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    // Add static variables here
    public static Tekton T1 = new Tekton();
    public static Tekton T2 = new Tekton();
    public static Scanner scanner = new Scanner(System.in);
    // ...

    public static void main(String[] args) {

        String sorBe = "";
        String [] command = null;

        System.out.println("Az exit paranccsal lehet kilépni");
        while(!(sorBe = scanner.nextLine().split(",")).equals("exit"){

            System.out.println("soros" + sorBe);
            command = sorBe.split(",");

            switch(command[0]){
                case "add": {

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
                case default:{
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
}