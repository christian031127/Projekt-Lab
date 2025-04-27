package org.example;

import java.util.logging.Level;
import java.util.logging.Logger;

public class SlowingSpore implements Spore {
    private static Logger logger = Logger.getLogger("TesztLogger");

    @Override
    public void addEffect(Player p) {

        p.setEffects(2, 1);
        logger.log(Level.INFO, "SlowingSpore.addEffect() sikeres");
    }
}
