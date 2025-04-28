package org.example;

import java.util.logging.Level;
import java.util.logging.Logger;

public class AccelerationSpore implements Spore {
    private static Logger logger = Logger.getLogger("TesztLogger");

    @Override
    public void addEffect(Player p){

        if(p.getEffects()[2] == 1) {
            p.setEffects(2, 0);
        } else {
            p.setEffects(0, 1);
        }
        logger.log(Level.INFO, "AccelerationSpore.addEffect() sikeres");
    }
}
