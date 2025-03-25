package org.example;
import java.io.Console;

import static org.example.Main.igazHamisKerdes;

import java.util.ArrayList;
import java.util.List;

public class Tekton {

    //public List<Tekton> getNeighbours(){
    public List<Tekton> getNeighbours(){
        System.out.println("Tekton.getNeighbours() called");
        return new ArrayList<Tekton>();
    }

    public void addNeighbour(Tekton tekton){
        System.out.println("Tekton.addNeighbour() called");
    }

    public void removeNeighbour(Tekton tekton){
        System.out.println("Tekton.removeNeighbour() called");
    }

    public Shroom getShroom(){
        System.out.println("Tekton.getShroom() called");
        return new Shroom();
    }

    public List<Spore> getSpores(){
        System.out.println("Tekton.getSpores() called");
        return new ArrayList<Spore>();
    }

    public List<Yarn> getYarns(){
        System.out.println("Tekton.getYarns() called");
        return new ArrayList<Yarn>();
    }

    public void getStrategy() {
        System.out.println("Tekton.getStrategy() called");
    }

    public void setStrategy(TektonStrategy strategy){
        System.out.println("Tekton.setStrategy() called");
    }

    public void addYarn(Yarn yarn){
        System.out.println("Tekton.addYarn() called");
    }

    public void removeYarn(Yarn yarn){
        System.out.println("Tekton.removeYarn() called");
    }

    public void clearYarns(){
        System.out.println("Tekton.clearYarns() called");
    }

    public void addShroom(Shroom shroom){
        System.out.println("Tekton.addShroom() called");
    }

    public void removeShroom(){
        System.out.println("Tekton.removeShroom() called");
    }

    public void addSpore(Spore spore){
        System.out.println("Tekton.addSpore() called");
    }

    public void removeSpore(Spore spore){
        System.out.println("Tekton.removeSpore() called");
    }

    public void removeSomeSpore(List<Spore> spores){
        System.out.println("Tekton.removeSomeSpore() called");
    }

    public void split(){
        System.out.println("Tekton.split() called");

        while(igazHamisKerdes("Van még fonalas összeköttetése?")){
            this.removeYarn(new Yarn());
            
            //Szomszéd
            new Tekton().removeYarn(new Yarn());
        }

        while(igazHamisKerdes("Van még szomszédja?")){
            this.removeNeighbour(new Tekton());

            //Szomszédnak hozzáadása
            new Tekton().addNeighbour(new Tekton());
        }

        //Szomszéd tekton hozzáadása
        new Tekton().addNeighbour(this);

        this.addNeighbour(new Tekton());
    }

    public void doEffect(){
        System.out.println("Tekton.doEffect() called");
        System.out.println("Milyen fajta tekton vagyok?\n1. Abszorbáló\n2. Több fonalas\n3. Gombatagadó\n4. Egy fonalas");
        System.out.println("Válassz egy lehetőséget: ");
        Console console = System.console();
        int user_input = 0;
        try {
            user_input = Integer.parseInt(console.readLine());
            if (user_input < 1 || user_input > 4) {
                throw new AssertionError("Hibás formátumú válasz!");
            }
        } catch (NumberFormatException e) {
            System.out.println("Hibás formátumú válasz!");
            return;
        } catch (AssertionError e) {
            System.out.println(e.getMessage());
            return;
        }
        switch (user_input) {
            case 1:
                new AbsorbTekton().doEffect();
                break;
            case 2:
                new MultipleYarnTekton().doEffect();
                break;
            case 3:
                new NonShroomTekton().doEffect();
                break;
            case 4:
                new SingleYarnTekton().doEffect();
                break;
            default:
                throw new AssertionError();
        }
    }    

}
