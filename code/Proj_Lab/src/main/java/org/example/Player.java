package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.example.Main.T1;
import static org.example.Main.T2;
import static org.example.Main.igazHamisKerdes;

public class Player {
    //private int score;
    //private boolean isInsect;
    //private Tekton currentTekton;
    //private int[] effects = new int[4];

    public void interactWithSpore(List<Spore> spores) {
        // Implementation
        System.out.println("interactWithSpore(List<Spore> spores) called");
        getIsInsect();
        if(igazHamisKerdes("Rovar vagy?")) {
            int user_input7 = 0;
            Scanner scanner = new Scanner(System.in);

            System.out.println("\nMelyik típusú spórát szeretnéd elfogyasztani?");
            System.out.println("1. Bénító spóra");
            System.out.println("2. Gyengítő spóra");
            System.out.println("3. Gyorsító spóra");
            System.out.println("4. Lassító spóra");
            System.out.println("Choose option: ");
            Scanner scanner = new Scanner(System.in);
            try {
                user_input7 = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Wrong input!");
            }

            switch (user_input7) {
                case 1: {
                    new NumbingSpore().addEffect(this);
                    new Tekton().removeSpore(new NumbingSpore());
                    System.out.println("Spóra elfogyasztva.");
                    break;
                }
                case 2: {
                    new WeakeningSpore().addEffect(this);
                    new Tekton().removeSpore(new WeakeningSpore());
                    System.out.println("Spóra elfogyasztva.");
                    break;
                }
                case 3: {
                    new AccelerationSpore().addEffect(this);
                    new Tekton().removeSpore(new AccelerationSpore());
                    System.out.println("Spóra elfogyasztva.");
                    break;
                }
                case 4: {
                    new SlowingSpore().addEffect(this);
                    new Tekton().removeSpore(new SlowingSpore());
                    System.out.println("Spóra elfogyasztva.");
                    break;
                }
                default: {
                    System.out.println("Unknown option.");
                }
            }
        }
        else{
            getCurrentTekton().getShroom();
            if(!igazHamisKerdes("Van a tektonon gomba?")) {
                if(igazHamisKerdes("Van elegendő spóra a tektonon, hogy gomba nőjön?")) {
                    getCurrentTekton().addShroom(new Shroom());
                    getCurrentTekton().removeSomeSpore(new ArrayList<Spore>());
                    getCurrentTekton().doEffect();
                }
                else {
                    System.out.println("Nincs elég spóra a tektonon, nem nőhet gomba!");
                }
            }
            else {
                System.out.println("A tektonon van már gomba, nem nőhet újabb!");
            }

        }

    }

    public void interactWithYarn(Yarn yarn) {
        // Implementation
        System.out.println("interactWithYarn(Yarn yarn) called");
        getIsInsect();
        if(igazHamisKerdes("Rovar vagy?")){
           getEffects();
           if(!igazHamisKerdes("Rajtad van a gyengítő spóra?")){

                yarn.setTekton1(null);
                yarn.setTekton2(null);

                System.out.println("A fonál el lett távolítva!");
           } else {
               System.out.println("A fonál nem távolítható el!");
           }
        }
        else {
            T1.getNeighbours();
            System.out.println("Melyik tektonra szeretnéd felhelyezni a fonált?");
            System.out.println("1. Szomszédos tektonra nő a fonál\n2. A jelenlegi tektont növi be a fonál");
            System.out.println("Válassz egy lehetőséget: ");
            Scanner scanner = new Scanner(System.in);

            try {
                int user_input1 = Integer.parseInt(scanner.nextLine());
                if (user_input1 < 1 || user_input1 > 2) {
                    System.out.println("Hibás válasz!");
                    return;
                }

                new Tekton().getStrategy();

                if (igazHamisKerdes("Nőhet fonál a kiválasztott tektonra?")) {
                    new Yarn().setTekton1(T1);
                    if (user_input1 == 1) {
                        new Yarn().setTekton2(T2);
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
        System.out.println("Player.move(Tekton tekton) called");
        new Player().getIsInsect();
        if (!igazHamisKerdes("Rovar vagy?")) {
            new Shroom().ejectSpore(tekton);
        }else {
            new Tekton().getYarns();
            if (igazHamisKerdes("Van gombafonal a mozgó tektonra?")) {
                new Player().setCurrentTekton(new Tekton());
            } else {
                System.out.println("Esemény megszakítva!");
            }
        }
    }

    public void setIsInsect(boolean b) {
        System.out.println("Player.setIsInsect(boolean b) called");
    }

    public void getIsInsect() {
        System.out.println("Player.getIsInsect() called");
    }

    public void setCurrentTekton(Tekton tekton) {
        System.out.println("Player.setCurrentTekton(Tekton tekton) called");
    }

    public Tekton getCurrentTekton() {
        System.out.println("Player.getCurrentTekton() called");
        return new Tekton();
    }

    public void setEffects(int[] effects) {
        System.out.println("Player.setEffects(int[] effects) called");
    }

    public void getEffects() {
        System.out.println("Player.getEffects() called");
    }

    public void setScore(int i) {
        // this.score = i;
    }

    public void getScore() {
        // return score;
    }
}