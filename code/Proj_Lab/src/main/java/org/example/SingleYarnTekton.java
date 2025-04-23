package org.example;

public class SingleYarnTekton implements TektonStrategy {
    @Override
    public void doEffect(Tekton T) {
        //deletes all yarns from the tekton except one
        // call when adding yarn
        System.out.println("SingleYarnTekton.doEffect() called");
    }
}
