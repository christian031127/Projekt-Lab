package org.example;
import static org.example.Main.igazHamisKerdes;

import java.util.ArrayList;
import java.util.List;

public class Tekton {

    //public List<Tekton> getNeighbours(){
    public void getNeighbours(){
        System.out.println("Tekton.getNeighbours() called");
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
    }    

}
