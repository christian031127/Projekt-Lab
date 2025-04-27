package org.example;

import java.util.logging.Level;
import java.util.logging.Logger;

public class AbsorbTekton implements TektonStrategy{
    private static Logger logger = Logger.getLogger("TesztLogger");

    @Override
    public void doEffect(Tekton T) {
        for (Yarn yarn : T.getYarns()) {
            if (yarn.isSingleTektonYarn()) {
                T.removeYarn(yarn);
            }
            logger.log(Level.INFO, "AbsorbTekton.doEffect() sikeres");
        }
    }
}
