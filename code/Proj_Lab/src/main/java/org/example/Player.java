package org.example;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Player {

    private int player_id;
    private boolean isInsect;

    private Tekton currentTekton;
    private int score;
    private int[] effects = new int[5];
    private int steps_in_round = 0;

    private boolean dead = false;

    private static Logger logger = Logger.getLogger("TesztLogger");

    public boolean interactWithSpore(List<Spore> spores) {
        // Implementation
        if(getIsInsect()) {
            if((getEffects()[2] == 1 && steps_in_round >= 1 || getEffects()[0] == 0 && steps_in_round >= 2 || getEffects()[0] == 1 && steps_in_round >= 3)) {
                //Checking whether the player has any moves left in round
                return false;
            }

            for (Spore _spore : spores) {
                _spore.addEffect(this);
                this.currentTekton.removeSpore(_spore);
            }
        }
        else{
            if (getCurrentTekton().getShroom() != null || steps_in_round >= 2) {
                return false;
            }
            if (getCurrentTekton().getSpores().size() >= 3) {
                Shroom s = new Shroom();
                getCurrentTekton().addShroom(s);
                getCurrentTekton().removeSomeSpore(spores);
                getCurrentTekton().doEffect();
                s.setTekton(currentTekton);
            }
            return getCurrentTekton().getShroom() != null;
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
           yarn.getTekton1().removeYarn(yarn);
           yarn.getTekton2().removeYarn(yarn);
           yarn.setTekton1(null);
           yarn.setTekton2(null);
        }
        else {
            if(steps_in_round >= 2) {
                return false;
            }
            yarn.getTekton2().doEffect();
            yarn.getTekton1().doEffect();
        }
        steps_in_round++;
        return true;
    }

    public Spore move(Tekton tekton) {
        logger.log(Level.INFO, "Player.move() called");
        if (!isInsect) {
            if(steps_in_round >= 2) {
                return null;
            }
            Shroom s = currentTekton.getShroom();
            if(s != null) {
                steps_in_round++;
                return s.ejectSpore(tekton);
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
            List<Yarn> yarns = this.currentTekton.getYarns();
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
        return null;
    }

    public void setIsInsect(boolean b) {
        this.isInsect = b;
    }

    public boolean getIsInsect() {
        return this.isInsect;
    }

    public void setCurrentTekton(Tekton tekton) {
        this.currentTekton = tekton;
    }

    public Tekton getCurrentTekton() {
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
}