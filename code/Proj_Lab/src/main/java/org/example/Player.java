package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.example.Main.*;

public class Player {

    private int player_id;
    private boolean isInsect;

    private Tekton currentTekton;
    private int score;
    private int[] effects = new int[5];
    private int steps_in_round = 0;

    private static Logger logger = Logger.getLogger("PlayerLogger");

    public void interactWithSpore(List<Spore> spores) {
        // Implementation
        if(getIsInsect()) {
            for (Spore _spore : spores) {
                _spore.addEffect(this);
                this.currentTekton.removeSpore(_spore);
            }
        }
        else{
            if (getCurrentTekton().getShroom() != null) {
                return;
            }
            if (getCurrentTekton().getSpores().size() >= 3) {
                Shroom s = new Shroom();
                getCurrentTekton().addShroom(s);
                getCurrentTekton().removeSomeSpore(spores);
                getCurrentTekton().doEffect();
            }
        }
        steps_in_round++;
    }

    public boolean interactWithYarn(Yarn yarn) {
        // Implementation
        if(getIsInsect()){
           if(getEffects()[4] == 1) {
               return false;
           }
           yarn.getTekton1().removeYarn(yarn);
           yarn.getTekton2().removeYarn(yarn);
           yarn.setTekton1(null);
           yarn.setTekton2(null);
        }
        else {
            yarn.getTekton2().doEffect();
            yarn.getTekton1().doEffect();
        }
        return true;
    }

    public Spore move(Tekton tekton) {
        if (!isInsect) {
            Shroom s = tekton.getShroom();
            if(s != null) {
                return s.ejectSpore(tekton);
            }
        } else {
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
    public void gameOver(){
        
    }
}