package org.example;

public class MultipleYarnTekton implements TektonStrategy {
    @Override
    public void doEffect() {
        //lets the tekton have multiple yarns
        System.out.println("MultipleYarnTekton.doEffect() called");
    }

}
