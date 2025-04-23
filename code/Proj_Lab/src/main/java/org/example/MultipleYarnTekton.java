package org.example;

public class MultipleYarnTekton implements TektonStrategy {
    @Override
    public void doEffect(Tekton T) {
        //lets the tekton have multiple yarns
        // default
        System.out.println("MultipleYarnTekton.doEffect() called");
        T.deleteYarnsOnTekton();
    }

}
