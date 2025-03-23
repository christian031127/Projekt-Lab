package org.example;

import java.util.ArrayList;
import java.util.List;

import static org.example.Main.igazHamisKerdes;

public class Player {
    //private int score;
    //private boolean isInsect;
    //private Tekton currentTekton;
    //private int[] effects = new int[4];

    public void interactWithSpore(List<Spore> spores) {
        // Implementation
        if(igazHamisKerdes("Rovar vagy?")) {}
        else{
            getCurrentTekton().getShroom();
            if(!igazHamisKerdes("Van a tektonon gomba?")) {
                if(igazHamisKerdes("Van elegendő spóra a tektonon, hogy gomba nőjön?")) {
                    getCurrentTekton().addShroom(new Shroom());
                    getCurrentTekton().removeSomeSpore(new ArrayList<Spore>());
                    getCurrentTekton().doEffect();
                }
            }

        }

    }

    public void interactWithYarn(Yarn yarn) {
        // Implementation
        getIsInsect();
        if(igazHamisKerdes("Rovar vagy?")){
           getEffects();
           if(!igazHamisKerdes("Rajtad van a gyengítő spóra?")){
                yarn.setTekton1(null);
                new Tekton().removeYarn(yarn);
                yarn.setTekton2(null);
                new Tekton().removeYarn(yarn);
                System.out.println("A fonál el lett távolítva!");
           }
        }
        else{
            //fonal gomba reszerol
        }
    }

    public void move(Tekton tekton) {
        //this.currentTekton = tekton;
    }

    public void setIsInsect(boolean b) {
        //this.isInsect = b;
    }

    public void getIsInsect() {
        System.out.println("Player.getIsInsect() meghivva");
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
        System.out.println("Player.getEffects() meghivva");
        // return effects;
    }

    public void setScore(int i) {
        // this.score = i;
    }

    public void getScore() {
        // return score;
    }
}