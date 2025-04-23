package org.example;

public class NonShroomTekton implements TektonStrategy {
    @Override
    public void doEffect(Tekton T) {
        //deletes all shrooms from the tekton
        //call when growing shroom
        System.out.println("NonShroomTekton.doEffect() called");
        if (T.getShroom() != null) {
            T.removeShroom(T.getShroom());
        }
        T.deleteYarnsOnTekton();
    }
    
}
