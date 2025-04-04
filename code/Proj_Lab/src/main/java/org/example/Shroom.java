package org.example;


import static org.example.Main.T1;
import static org.example.Main.igazHamisKerdes;
import static org.example.Main.scanner;

public class Shroom extends  GameObject {
    // private int team_id;
    // private Tekton tekton;
    // private int _age;
    // private int lastEject;

    public boolean isOld() {
        System.out.println("Gomba isOld() called.");
        if(igazHamisKerdes("Elég idős a gomba?")) {
            return true;
        }
        return false;
    }

    public void ejectSpore(Tekton T) {
        System.out.println("Shroom.ejectSpore() called");

        System.out.println("1. Spóra szórása szomszédos tektonra \n2. Spóra szórása szomszéd szomszédjára");
        System.out.println("Válassz egy lehetőséget: ");
        try {
            int user_input = Integer.parseInt(scanner.nextLine());
            if(user_input != 1 && user_input != 2) {
                System.out.println("Hibás válasz!");
                return;
            }

            new Shroom().getAge();
            if (user_input == 2 && !igazHamisKerdes("Elég idős a gomba, hogy ilyen messzire szórjon spórát?")) {
                System.out.println("A gomba nem elég idős, sikertelen spóra szórás.");
                return;
            }

            new Shroom().isSporeReady();
            T1.addSpore(new NumbingSpore());

        } catch (NumberFormatException e) {
            System.out.println("Hibás válasz!");
        }
    }

    public void isSporeReady() {
        System.out.println("Shroom.isSporeReady() called");
    }

    public void die() { 
        System.out.println("Gomba die() called.");
    }

    public void age() {
        System.out.println("Gomba age() called.");
        if(!isOld()) {
            System.out.println("Gomba nem elég idős a halálhoz.");
            return;
        }
        die();
        System.out.println("Gomba meghalt.");
    }

    public void getTeam_id() {
        // return team_id;
    }

    public Tekton getTekton() {
        return new Tekton();
    }

    public void getAge() {
        System.out.println("Shroom.GetAge() called!");
        // return _age;
    }

    public void getLastEject() {
        // return lastEject;
    }

    public void setTeam_id(int id) {
        // this.team_id = id;
    }

    public void setTekton(Tekton T) {
        // this.tekton = T;
    }
}
