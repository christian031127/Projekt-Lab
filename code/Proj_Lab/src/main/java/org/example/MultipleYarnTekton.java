package org.example;

import java.util.logging.Level;
import java.util.logging.Logger;

public class MultipleYarnTekton implements TektonStrategy {
    private static Logger logger = Logger.getLogger("TesztLogger");

    @Override
    public void doEffect(Tekton T) {
        T.deleteYarnsOnTekton();
        logger.log(Level.INFO,"MultipleYarnTekton.doeffect() sikeres");
    }
}
