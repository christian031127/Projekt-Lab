package org.example;
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

    public void getSpores(){

    }

    public void getYarns(){

    }

    public TektonStrategy getStrategy(){
        return new TektonStrategy() {
            
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

    }

    public void removeShroom(){
        
    }

    public void addSpore(Spore spore){

    }

    public void removeSpore(Spore spore){

    }

    public void removeSomeSpore(List<Spore> spores){

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

    }    

}
