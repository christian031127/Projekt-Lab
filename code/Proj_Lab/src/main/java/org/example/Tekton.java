package org.example;
import java.util.ArrayList;
import java.util.List;

import static org.example.Main.igazHamisKerdes;

public class Tekton {

    //public List<Tekton> getNeighbours(){
    public void getNeighbours(){
        
    }

    public void addNeighbour(Tekton tekton){
        System.out.println("Tekton.addNeighbour(Tekton tekton) meghívva");
    }

    public void removeNeighbour(Tekton tekton){
        System.out.println("Tekton.removeNeighbour(Tekton tekton) meghívva");
    }

    public Shroom getShroom(){
        return new Shroom();
    }

    public List<Spore> getSpores(){
        return new ArrayList<Spore>();
    }

    public List<Yarn> getYarns(){
        return new ArrayList<Yarn>();
    }

    public TektonStrategy getStrategy(){
        return new NonShroomTekton() {
            
        };
    }

    public void setStrategy(TektonStrategy strategy){

    }

    public void addYarn(Yarn yarn){

    }

    public void removeYarn(Yarn yarn){
        System.out.println("Tekton.removeYarn(Yarn yarn) meghívva");
    }

    public void clearYarns(){

    }

    public void addShroom(Shroom shroom){
        System.out.println("Tekton.addShroom() meghívva");
    }

    public void removeShroom(){
        
    }

    public void addSpore(Spore spore){

    }

    public void removeSpore(Spore spore){

    }

    public void removeSomeSpore(List<Spore> spores){
        System.out.println("Tekton.removeSomeSpore() meghívva");
    }

    public void split(){
        System.out.println("Tekton.split() meghívva");

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
        System.out.println("Tekton.doEffect() meghívva");
    }    

}
