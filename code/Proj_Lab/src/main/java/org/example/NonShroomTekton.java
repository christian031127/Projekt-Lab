package org.example;

public class NonShroomTekton implements TektonStrategy {
    @Override
    public void doEffect(Tekton T) {
        //deletes all shrooms from the tekton
        //call when growing shroom
        System.out.println("NonShroomTekton.doEffect() called");
    }
    
}
