package org.example;

import java.util.logging.Level;
import java.util.logging.Logger;

public class KeepAliveTekton implements TektonStrategy {
    private static Logger logger = Logger.getLogger("TesztLogger");

    @Override
    public void doEffect(Tekton T) {
        logger.log(Level.INFO,"KeepAliveTekton.doEffect() sikeres");
    }
}