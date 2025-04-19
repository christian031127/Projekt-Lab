package org.example;

public class NonShroomTekton implements TektonStrategy {
    @Override
    public void doEffect() {
        //deletes all shrooms from the tekton
        System.out.println("NonShroomTekton.doEffect() called");
    }
    
}
