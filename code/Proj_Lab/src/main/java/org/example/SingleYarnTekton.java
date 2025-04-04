package org.example;

public class SingleYarnTekton extends GameObject implements TektonStrategy {
    @Override
    public void doEffect() {
        //deletes all yarns from the tekton except one
        System.out.println("SingleYarnTekton.doEffect() called");
    }
}
