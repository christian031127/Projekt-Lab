package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.HashSet;
import java.util.Set;

public class Player {

    private int player_id;
    private boolean isInsect=false;

    private List<Tekton> currentTekton=new ArrayList<Tekton>();
    private int score;
    private int[] effects = new int[5];
    private int steps_in_round = 0;

    private boolean dead = false;

    private static Logger logger = Logger.getLogger("TesztLogger");

    public boolean interactWithSpore(List<Spore> spores) {
        // Implementation
        if(getIsInsect()) {
            if((getEffects()[2] == 1 && steps_in_round >= 1 || getEffects()[0] == 0 && steps_in_round >= 2 || getEffects()[0] == 1 && steps_in_round >= 3) || spores.size()==0) {
                //Checking whether the player has any moves left in round
                return false;
            }

            for (Spore _spore : spores) {
                _spore.addEffect(this);
                this.currentTekton.getFirst().removeSpore(_spore);
                score+=25;
                break;
            }
        }
        else{
            Tekton c=null;
            for (Tekton t:currentTekton)
            {
                if(t.getSpores().contains(spores.getFirst())){
                    c=t;
                    break;
                }
            }
            if(c==null){return false;}
            if (c.getShroom() != null || steps_in_round >= 2) {
                return false;
            }
            if (c.getSpores().size() >= 3) {
                Shroom s = new Shroom(c,player_id);
                c.addShroom(s);
                c.removeSomeSpore(spores);
                c.doEffect();
                s.setTekton(c);
                score+=25;
            }
            return c.getShroom() != null;
        }
        steps_in_round++;

        return true;
    }

    public boolean interactWithYarn(Yarn yarn) {
        // Implementation
        if(getIsInsect()){
            if((getEffects()[2] == 1 && steps_in_round >= 1 || getEffects()[0] == 0 && steps_in_round >= 2 || getEffects()[0] == 1 && steps_in_round >= 3)) {
                //Checking whether the player has any moves left in the round
                return false;
            }
           if(getEffects()[4] == 1) {
               //Checking whether the player has Weakening effect active
               return false;
           }
           yarnEaten(yarn);
           yarn.getTekton1().removeYarn(yarn);
           yarn.getTekton2().removeYarn(yarn);
           yarn.setTekton1(null);
           yarn.setTekton2(null);

        }
        else {
            if(steps_in_round >= 2) {
                return false;
            }
            if(!yarn.getTekton1().getNeighbours().contains(yarn.getTekton2())){
                return false;
            }
            if(!currentTekton.contains(yarn.getTekton2())) {
                currentTekton.add(yarn.getTekton2());
            }
            if(!currentTekton.contains(yarn.getTekton1())) {
                currentTekton.add(yarn.getTekton1());
            }
            yarn.getTekton1().addYarn(yarn);
            yarn.getTekton2().addYarn(yarn);
            yarn.getTekton2().doEffect();
            yarn.getTekton1().doEffect();
            yarn.setShroomPlayerId(player_id);
        }
        steps_in_round++;
        score+=50;
        return true;
    }

    public Spore move(Tekton tekton) {
        logger.log(Level.INFO, "Player.move() called");

        if (!isInsect) {
            if(steps_in_round >= 2) {
                return new NumbingSpore();
            }
            Shroom s=null;
            for (Tekton t: currentTekton){
                if(t.getNeighbours().contains(tekton)) {
                    s=t.getShroom();
                    break;
                }
            }
            if(s != null) {
                Spore S=s.ejectSpore(tekton);
                if(S!=null) {
                    steps_in_round++;
                    score+=15;
                    return null;
                }
                else{
                    return new NumbingSpore();
                }

            }
            else{
                return new NumbingSpore();
            }
        } else {
            if((getEffects()[2] == 1 && steps_in_round >= 1 || getEffects()[0] == 0 && steps_in_round >= 2 || getEffects()[0] == 1 && steps_in_round >= 3)) {
                //Checking if the player has any steps left in the round
                return new NumbingSpore();
            }
            if(getEffects()[1] == 1) {
                //Checking whether the player has Numbing effect active
                return new NumbingSpore();
            }
            List<Yarn> yarns = this.currentTekton.getFirst().getYarns();
            boolean moved = false;
            for(Yarn y : yarns) {
                if(y.getTekton1() == tekton || y.getTekton2() == tekton) {
                    this.setCurrentTekton(tekton);
                    moved = true;
                    break;
                }
            }
            if(!moved) {
                // Ha nem null a return érték akkor sikertelen a mozgás
                return new NumbingSpore();
            }
        }
        steps_in_round++;
        score+=15;
        return null;
    }

    public void setIsInsect(boolean b) {
        this.isInsect = b;
    }

    public boolean getIsInsect() {
        return this.isInsect;
    }

    public void setCurrentTekton(Tekton tekton) {
        if(isInsect){
            this.currentTekton.clear();
            this.currentTekton.add(tekton);
        }
        else {
            this.currentTekton.add(tekton);
        }
    }

    public List<Tekton> getCurrentTekton() {
        return this.currentTekton;
    }

    public void setEffects(int index, int value) {
        this.effects[index] = value;
    }

    public int[] getEffects() {
        return this.effects;
    }

    public void addScore(int i) {
        this.score += i;
    }

    public int getScore() {
        return score;
    }

    public boolean isDead() {
        return dead;
    }

    public void gameOver(){
        dead = true;
    }

    public void yarnEaten(Yarn y) {
        if (!isThereShroom(y, y.getTekton1(), new HashSet<>())) {
            deleteSystem(y, y.getTekton1(), new HashSet<>());
        }
        if (!isThereShroom(y, y.getTekton2(), new HashSet<>())) {
            deleteSystem(y, y.getTekton2(), new HashSet<>());
        }
    }

    public boolean isThereShroom(Yarn y, Tekton t, Set<Tekton> visited) {
        int playerID = y.getShroomPlayerId();

        if (t.getShroom() != null && t.getShroom().getPlayerId() == playerID) {
            return true;
        }

        if (visited.contains(t)) {
            return false;
        }
        visited.add(t);

        for (Yarn y1 : t.getYarns()) {
            if (y1.getShroomPlayerId() == playerID &&
                    !y1.isSingleTektonYarn() &&
                    y1 != y) {

                Tekton other = (y1.getTekton1() == t) ? y1.getTekton2() : y1.getTekton1();
                if (isThereShroom(y1, other, visited)) {
                    return true;
                }
            }
        }

        return false;
    }

    public boolean deleteSystem(Yarn y, Tekton t, Set<Tekton> visited) {
        int playerID = y.getShroomPlayerId();

        if (visited.contains(t)) {
            return false;
        }
        visited.add(t);



        for (int i=0; i< t.getYarns().size(); i++) {
            if (t.getYarns().get(i).getShroomPlayerId() == playerID &&
                    !t.getYarns().get(i).isSingleTektonYarn() &&
                    t.getYarns().get(i) != y){

                Tekton other = (t.getYarns().get(i).getTekton1() == t) ? t.getYarns().get(i).getTekton2() : t.getYarns().get(i).getTekton1();
                deleteSystem(t.getYarns().get(i), other, visited);



                if(t.getYarns().get(i).getTekton1()==t){
                    t.getYarns().get(i).getTekton2().removeYarn(t.getYarns().get(i));
                    t.getYarns().get(i).setTekton2(null);
                    t.getYarns().get(i).getTekton1().removeYarn(t.getYarns().get(i));
                    t.getYarns().get(i).setTekton1(null);
                }else{
                    t.getYarns().get(i).getTekton1().removeYarn(t.getYarns().get(i));
                    t.getYarns().get(i).setTekton1(null);

                    t.getYarns().get(i).getTekton2().removeYarn(t.getYarns().get(i));
                    t.getYarns().get(i).setTekton2(null);
                }

            }
        }
        score+=15;
        t.doEffect();
        return true;
    }

    public void setPlayer_id(int id){
        player_id=id;
    }
    public int getPlayer_id(){
        return player_id;
    }
    public void steps_in_round_reset(){steps_in_round=0;}

    public void handleShroomDeath(Tekton t) {
        boolean isThereShroomLeft = false;
        for(Yarn y : t.getYarns()) {
            if(!y.isSingleTektonYarn() && y.getShroomPlayerId() == player_id) {
                if(y.getTekton1() == t) {
                    if (isThereShroom(y, y.getTekton2(), new HashSet<>())) {
                        isThereShroomLeft = true;
                    }
                } else {
                    if (isThereShroom(y, y.getTekton1(), new HashSet<>())) {
                        isThereShroomLeft = true;
                    }
                }   
            }
        }
        if(!isThereShroomLeft) {
            int j = 0;
            for(int i=0;i<t.getYarns().size();i++) {
                if(!t.getYarns().get(i).isSingleTektonYarn() && t.getYarns().get(i).getShroomPlayerId() == player_id) {
                    if(t.getYarns().get(i).getTekton1() == t) {
                        if (!isThereShroom(t.getYarns().get(i), t.getYarns().get(i).getTekton2(), new HashSet<>())) {
                            deleteSystem(t.getYarns().get(i), t.getYarns().get(i).getTekton2(), new HashSet<>());
                        }
                    } else {
                        if (!isThereShroom(t.getYarns().get(i), t.getYarns().get(i).getTekton1(), new HashSet<>())) {
                            deleteSystem(t.getYarns().get(i), t.getYarns().get(i).getTekton1(), new HashSet<>());
                        }
                    }
                    if(t.getYarns().get(i).getTekton1()==t){
                        t.getYarns().get(i).getTekton2().removeYarn(t.getYarns().get(i));
                        t.getYarns().get(i).setTekton2(null);
                        if(!(t==currentTekton.get(0))) {
                            t.getYarns().get(i).getTekton1().removeYarn(t.getYarns().get(i));
                            t.getYarns().get(i).setTekton1(null);
                        }else{
                            t.getYarns().get(i-j).getTekton1().removeYarn(t.getYarns().get(i));
                            t.getYarns().get(i-j).setTekton1(null);
                            j++;
                        }

                    }else{
                        t.getYarns().get(i).getTekton1().removeYarn(t.getYarns().get(i));
                        t.getYarns().get(i).setTekton1(null);

                        if(!(t==currentTekton.get(0))) {
                            t.getYarns().get(i).getTekton2().removeYarn(t.getYarns().get(i));
                            t.getYarns().get(i).setTekton2(null);
                        }else{
                            t.getYarns().get(i-j).getTekton2().removeYarn(t.getYarns().get(i));
                            t.getYarns().get(i-j).setTekton2(null);
                            j++;
                        }
                    }

                }

            }
            
        }
    }
}