package org.example;

public class NonShroomTekton extends GameObject implements TektonStrategy {
    @Override
    public void doEffect() {
        //deletes all shrooms from the tekton
        System.out.println("NonShroomTekton.doEffect() called");
    }
    
}
