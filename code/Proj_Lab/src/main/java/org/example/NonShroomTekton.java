package org.example;

import java.util.logging.Level;
import java.util.logging.Logger;

public class NonShroomTekton implements TektonStrategy {
    private static Logger logger = Logger.getLogger("TesztLogger");

    @Override
    public void doEffect(Tekton T) {
        if (T.getShroom() != null) {
            T.removeShroom();
            logger.log(Level.INFO,"NonshroomTekton.doEffect() sikeres");
        }
        T.deleteYarnsOnTekton();
    }
}
