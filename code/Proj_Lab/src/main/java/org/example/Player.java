package org.example;

import java.util.List;

public class Player {
    //private int score;
    //private boolean isInsect;
    //private Tekton currentTekton;
    //private int[] effects = new int[4];

    public void interactWithSpore(List<Spore> spores) {
        // Implementation
    }

    public void interactWithYarn(Yarn yarn) {
        // Implementation
    }

    public void move(Tekton tekton) {
        //this.currentTekton = tekton;
    }

    public void setIsInsect(boolean b) {
        //this.isInsect = b;
    }

    public void getIsInsect() {
        //return isInsect;
    }

    public void setCurrentTekton(Tekton t) {
        //this.currentTekton = t;
    }

    public Tekton getCurrentTekton() {
        return new Tekton();
    }

    public void setEffects(int[] effects) {
        // Implementation
    }

    public void getEffects() {
        // return effects;
    }

    public void setScore(int i) {
        // this.score = i;
    }

    public void getScore() {
        // return score;
    }
}