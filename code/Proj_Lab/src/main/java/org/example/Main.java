package org.example;

import java.util.Scanner;

public class Main {

    // Add static variables here
    public static Tekton T1 = new Tekton();
    public static Tekton T2 = new Tekton();
    public static Player P1 = new Player();
    public static Shroom Shroom1 = new Shroom();
    public static AbsorbTekton absorbTekton = new AbsorbTekton();

    public static NumbingSpore N1 = new NumbingSpore();
    public static WeakeningSpore W1 = new WeakeningSpore();
    public static AccelerationSpore A1 = new AccelerationSpore();
    public static SlowingSpore S1 = new SlowingSpore();
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
                continue;
            }

            switch (user_input) {
                case 1: {
                    new Player().interactWithYarn(new Yarn());
                    break;
                }
                case 2: {
                    for (Yarn yarn : new Player().getCurrentTekton().getYarns()) {
                        if (yarn.getTekton1() == new Tekton() || yarn.getTekton2() == new Tekton()) {
                            new Player().interactWithYarn(yarn);
                        }
                    }
                    System.out.println("Teszt sikeresen lefutott.");
                    break;
                }
                case 3: {
                    new Tekton().doEffect();
                    System.out.println("Teszt sikeresen lefutott.");
                    break;
                }
                case 4: {
                    new Player().interactWithSpore(new Tekton().getSpores());
                    System.out.println("Teszt sikeresen lefutott.");
                    break;
                }
                case 5: {
                    new Player().move(new Tekton());
                    break;
                }
                case 6: {
                    P1.getIsInsect();
                    if (igazHamisKerdes("Rovar vagy?")) {
                        T1.getYarns();
                        if (igazHamisKerdes("Van gombafonal a mozgó tektonra?")) {
                            P1.setCurrentTekton(T1); //T2 tektonra megy át igazából
                            P1.move(T1); //T2 tektonra megy át igazából
                        } else {
                            System.out.println("Esemény megszakítva!");
                        }
                    } else {
                        System.out.println("Esemény megszakítva!");
                    }
                    break;
                }
                case 7: {
                    //P1.interactWithSpore(T1.getSpores());
                    P1.getIsInsect();
                    if (igazHamisKerdes("Rovar vagy?")) {
                        T1.getSpores();

                        int user_input7 = 0;

                        System.out.println("\nMelyik típusú spórát szeretnéd elfogyasztani?");
                        System.out.println("1. Bénító spóra");
                        System.out.println("2. Gyengítő spóra");
                        System.out.println("3. Gyorsító spóra");
                        System.out.println("4. Lassító spóra");
                        System.out.println("Choose option: ");

                        try {
                            user_input7 = Integer.parseInt(scanner.nextLine());
                        } catch (NumberFormatException e) {
                            System.out.println("Wrong input!");
                        }

                        switch (user_input7) {
                            case 1: {
                                N1.addEffect(P1);
                                T1.removeSpore(N1);
                                System.out.println("Spóra elfogyasztva.");
                                break;
                            }
                            case 2: {
                                W1.addEffect(P1);
                                T1.removeSpore(W1);
                                System.out.println("Spóra elfogyasztva.");
                                break;
                            }
                            case 3: {
                                A1.addEffect(P1);
                                T1.removeSpore(A1);
                                System.out.println("Spóra elfogyasztva.");
                                break;
                            }
                            case 4: {
                                S1.addEffect(P1);
                                T1.removeSpore(S1);
                                System.out.println("Spóra elfogyasztva.");
                                break;
                            }
                            default: {
                                System.out.println("Unknown option.");
                            }
                        }
                    } else {
                        System.out.println("Esemény megszakítva!");
                    }
                    break;
                }
                case 8: {
                    new Shroom().age();
                    System.out.println("Teszt sikeresen lefutott.");
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

    public static boolean igazHamisKerdes(String kerdes) {
        System.out.print(kerdes + " [Y/n][I/n]: ");

        Scanner scanner = new Scanner(System.in);
        String valasz = scanner.nextLine();

        valasz = valasz.toUpperCase();

        if (valasz.equals("Y") || valasz.equals("I")) {
            return true;
        } else {
            return false;
        }
    }
}