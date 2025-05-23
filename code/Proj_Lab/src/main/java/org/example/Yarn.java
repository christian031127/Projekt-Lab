package org.example;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Yarn {

    private static Logger logger = Logger.getLogger("TesztLogger");

    private Tekton T1;
    private Tekton T2;
    private int shroom_player_id;

    public void setTekton1(Tekton tekton1) {
        if(tekton1==null){
            T1.removeYarn(this);
            T1=null;
        }
        else{
            T1 = tekton1;
            T1.addYarn(this);
        }

        logger.log(Level.INFO,"Yarn.setTekton1() called");
    }

    public void setTekton2(Tekton tekton2) {
        if(tekton2==null){
            T2.removeYarn(this);
        }
        else{
            T2 = tekton2;
            T2.addYarn(this);
        }
        logger.log(Level.INFO,"Yarn.setTekton2() called");
    }

    public Tekton getTekton1() {
        return T1;
    }

    public Tekton getTekton2() {
        return T2;
    }

    public void setShroomPlayerId(int shroom_player_id) {
        this.shroom_player_id = shroom_player_id;
        logger.log(Level.INFO,"Yarn.setShroom_player_id() called");
    }
    public int getShroomPlayerId() {
        return shroom_player_id;
    }
    public boolean isSingleTektonYarn() {
        if(T1!=null && T2==null){
            return true;
        }
        else return false;
    }
    public boolean eatNumbInsect(Player p){
        if(p.getEffects()[1]==1){
            p.gameOver();
            return true;
        }
        return false;
    }

}
