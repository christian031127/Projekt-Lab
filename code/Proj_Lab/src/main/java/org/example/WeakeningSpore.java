package org.example;

import java.util.logging.Level;
import java.util.logging.Logger;

public class WeakeningSpore implements Spore {
    private static Logger logger = Logger.getLogger("TesztLogger");

    @Override
    public void addEffect(Player p) {

        p.setEffects(4, 1);
        logger.log(Level.INFO, "WeakeningSpore.addEffect() sikeres");
    }
}
