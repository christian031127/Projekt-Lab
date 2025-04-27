package org.example;

import java.util.logging.Level;

import static org.example.Main.logger;

public class Yarn {

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

        logger.log(Level.FINE,"Yarn.setTekton1() called");
    }

    public void setTekton2(Tekton tekton2) {
        if(tekton2==null){
            T2.removeYarn(this);
        }
        else{
            T2 = tekton2;
            T2.addYarn(this);
        }
        logger.log(Level.FINE,"Yarn.setTekton2() called");
    }

    public Tekton getTekton1() {
        return T1;
    }

    public Tekton getTekton2() {
        return T2;
    }

    public void setShroom_player_id(int shroom_player_id) {
        this.shroom_player_id = shroom_player_id;
        logger.log(Level.FINE,"Yarn.setShroom_player_id() called");
    }
    public int getShroom_player_id() {
        return shroom_player_id;
    }
    public boolean isSingleTektonYarn() {
        if(T1!=null && T2==null){
            return true;
        }
        else return false;
    }

    public int getShroomPlayerId() {
        return 0;
    }
}
