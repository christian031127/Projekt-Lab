package org.example;

public class SingleYarnTekton implements TektonStrategy {
    @Override
    public void doEffect(Tekton T) {
        //deletes all yarns from the tekton except one
        // call when adding yarn
        System.out.println("SingleYarnTekton.doEffect() called");
        if (T.getYarns().size() > 1) {
            Yarn yarnToKeep = T.getYarns().get(0); // Keep the first yarn
            for (Yarn yarn : T.getYarns()) {
                if (!yarn.equals(yarnToKeep)) {
                    T.removeYarn(yarn);
                }
            }
        }
        // If there are no yarns, do nothing
        T.deleteYarnsOnTekton();
    }
}
