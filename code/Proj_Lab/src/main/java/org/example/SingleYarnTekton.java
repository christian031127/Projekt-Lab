package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SingleYarnTekton implements TektonStrategy {
    private static Logger logger = Logger.getLogger("TesztLogger");

    @Override
    public void doEffect(Tekton T) {
        if (T.getYarns().size() > 1) {
            Yarn yarnToKeep = T.getYarns().get(0);
            ArrayList<Yarn> yarns_to_remove = new ArrayList<>();
            for (Yarn yarn : T.getYarns()) {
                if (!yarn.equals(yarnToKeep)) {
                    yarns_to_remove.add(yarn);
                }
            }
            for (Yarn yarn : yarns_to_remove) {
                T.removeYarn(yarn);
            }
            logger.log(Level.INFO, "SingleYarnTekton.doEffect() sikeres");
        }
        T.deleteYarnsOnTekton();
    }
}
