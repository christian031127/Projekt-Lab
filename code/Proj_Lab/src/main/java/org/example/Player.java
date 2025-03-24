package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.example.Main.*;

public class Player {
    //private int score;
    //private boolean isInsect;
    //private Tekton currentTekton;
    //private int[] effects = new int[4];

    public void interactWithSpore(List<Spore> spores) {
        // Implementation
        if(igazHamisKerdes("Rovar vagy?")) {
        }
        else{
            getCurrentTekton().getShroom();
            if(!igazHamisKerdes("Van a tektonon gomba?")) {
                if(igazHamisKerdes("Van elegendő spóra a tektonon, hogy gomba nőjön?")) {
                    getCurrentTekton().addShroom(new Shroom());
                    getCurrentTekton().removeSomeSpore(new ArrayList<Spore>());
                    getCurrentTekton().doEffect();
                }
            }

        }

    }

    public void interactWithYarn(Yarn yarn) {
        // Implementation
        getIsInsect();
        if(igazHamisKerdes("Rovar vagy?")){
           getEffects();
           if(!igazHamisKerdes("Rajtad van a gyengítő spóra?")){
                yarn.setTekton1(null);
                new Tekton().removeYarn(yarn);
                yarn.setTekton2(null);
                new Tekton().removeYarn(yarn);
                System.out.println("A fonál el lett távolítva!");
           }
        }
        else{
            Scanner scanner = new Scanner(System.in);

            System.out.println("1. Szomszédos tektonra nő a fonál\n2. A jelenlegi tektont növi be a fonál");
            System.out.println("Válassz egy lehetőséget: ");
            try {
                int user_input1 = Integer.parseInt(scanner.nextLine());
                if (user_input1 < 1 || user_input1 > 2) {
                    System.out.println("Hibás válasz!");
                    return;
                }

                if (igazHamisKerdes("Nőhet fonál a kiválasztott tektonra?")) {
                    new Yarn().setTekton1(T1);
                    T1.addYarn(new Yarn());
                    if (user_input1 == 1) {
                        new Yarn().setTekton2(T2);
                        T2.addYarn(new Yarn());
                        T2.doEffect();
                    } else {
                        new Yarn().setTekton2(T1);
                    }
                    T1.doEffect();

                } else {
                    System.out.println("A tektonra nem nőhet fonál, sikertelen növesztés!");
                }

            } catch (NumberFormatException e) {
                System.out.println("Hibás válasz!");
            }
        }
    }

    public void move(Tekton tekton) {
        System.out.println("Player.move(Tekton tekton) meghivva");
        if (igazHamisKerdes("Gomba vagy?")) {
            Scanner scanner = new Scanner(System.in);

            System.out.println("1. Spóra szórása szomszédos tektonra \n2. Spóra szórása szomszéd szomszédjára");
            System.out.println("Válassz egy lehetőséget: ");
            try {
                int user_input = Integer.parseInt(scanner.nextLine());
                if(user_input != 1 && user_input != 2) {
                    System.out.println("Hibás válasz!");
                    return;
                }

                if (user_input == 2 && !igazHamisKerdes("Elég idős a gomba, hogy ilyen messzire szórjon spórát?")) {
                    System.out.println("A gomba nem elég idős, sikertelen spóra szórás.");
                    return;
                }
                new Shroom().getAge();
                new Shroom().isSporeReady();
                new Shroom().ejectSpore(T1);
                T1.addSpore(new NumbingSpore());

            } catch (NumberFormatException e) {
                System.out.println("Hibás válasz!");
            }
        }
    }

    public void setIsInsect(boolean b) {
        System.out.println("Player.setIsInsect(boolean b) meghivva");
    }

    public void getIsInsect() {
        System.out.println("Player.getIsInsect() meghivva");
    }

    public void setCurrentTekton(Tekton tekton) {
        System.out.println("Player.setCurrentTekton(Tekton tekton) meghivva");
    }

    public Tekton getCurrentTekton() {
        System.out.println("Player.getCurrentTekton() meghivva");
        return new Tekton();
    }

    public void setEffects(int[] effects) {
        System.out.println("Player.setEffects(int[] effects) meghivva");
    }

    public void getEffects() {
        System.out.println("Player.getEffects() meghivva");
    }

    public void setScore(int i) {
        // this.score = i;
    }

    public void getScore() {
        // return score;
    }
}