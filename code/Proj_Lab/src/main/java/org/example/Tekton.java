package org.example;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Tekton{

    private static Logger logger = Logger.getLogger("TesztLogger");

    // add parameters
    private TektonStrategy strategy;
    private List<Tekton> neighbours = new ArrayList<Tekton>();
    private List<Yarn> yarns = new ArrayList<Yarn>();
    private List<Spore> spores = new ArrayList<Spore>();
    private Shroom shroom = null;


    public Tekton(){
        strategy = new MultipleYarnTekton();
    }

    // Spore limit for shroom growing (3?)
    public List<Tekton> getNeighbours(){
        logger.log(Level.FINE, "Tekton.getNeighbours() called");
        return neighbours;
    }

    public void addNeighbour(Tekton tekton){
        logger.log(Level.FINE, "Tekton.addNeighbour() called");
        neighbours.add(tekton);
    }

    public void removeNeighbour(Tekton tekton){
        logger.log(Level.FINE, "Tekton.removeNeighbour() called");
        neighbours.remove(tekton);
    }

    public Shroom getShroom(){
        logger.log(Level.FINE, "Tekton.getShroom() called");
        return shroom;
    }

    public List<Spore> getSpores(){
        logger.log(Level.FINE, "Tekton.getSpores() called");
        return spores;
    }

    public List<Yarn> getYarns(){
        logger.log(Level.FINE, "Tekton.getYarns() called");
        return yarns;
    }

    public TektonStrategy getStrategy() {
        logger.log(Level.FINE, "Tekton.getStrategy() called");
        return strategy;
    }

    public void setStrategy(TektonStrategy strategy){
        logger.log(Level.FINE, "Tekton.setStrategy() called");
        this.strategy = strategy;
    }

    public void addYarn(Yarn yarn){
        logger.log(Level.FINE, "Tekton.addYarn() called");
        yarns.add(yarn);
    }

    public boolean removeYarn(Yarn yarn){
        logger.log(Level.FINE, "Tekton.removeYarn() called");
        return yarns.remove(yarn);
    }

    public void clearYarns(){
        logger.log(Level.FINE, "Tekton.clearYarns() called");
        yarns = new ArrayList<Yarn>();
    }

    public void addShroom(Shroom shroom){
        logger.log(Level.FINE, "Tekton.addShroom() called");
        this.shroom = shroom;
    }

    public void removeShroom(){
        logger.log(Level.FINE, "Tekton.removeShroom() called");
        shroom = null;
    }

    public void addSpore(Spore spore){
        logger.log(Level.FINE, "Tekton.addSpore() called");
        spores.add(spore);
    }

    public void removeSpore(Spore spore){
        logger.log(Level.FINE, "Tekton.removeSpore() called");
        spores.remove(spore);
    }

    public void removeSomeSpore(List<Spore> spores_list){
        logger.log(Level.FINE, "Tekton.removeSomeSpore() called");
        Iterator<Spore> it = spores_list.iterator();

        while (it.hasNext()) {
            it.next();
            it.remove();
        }

    }

    public void split(){
        logger.log(Level.FINE, "Tekton.split() called");

        for(Yarn y : new ArrayList<>(yarns)){
            removeYarn(y);
        }

        //A Map.split ben al kell tárolni a szomszédokat a törlés előtt!
        for(Tekton t : new ArrayList<>(neighbours)){
            removeNeighbour(t);
        }
    }

    public void doEffect(){
        logger.log(Level.FINE, "Tekton.doEffect() called");
        strategy.doEffect(this);
    }


    public void deleteYarnsOnTekton(){
        for (Yarn yarn : getYarns()) {
            if (yarn.isSingleTektonYarn()) {
                int id = yarn.getShroomPlayerId();
                boolean hasneighbour = false;
                for (Yarn yarn1 : getYarns()) {
                    if (yarn1.getShroomPlayerId() == id && yarn1 != yarn) {
                        hasneighbour = true;
                        break;
                    }
                }
                if (!hasneighbour) {
                    removeYarn(yarn);
                    logger.log(Level.INFO, "deleteYarnsOnTekton() sikeres");
                }
            }
        }
    }
}
