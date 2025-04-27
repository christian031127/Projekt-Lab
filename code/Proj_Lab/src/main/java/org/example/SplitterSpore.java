package org.example;

import java.util.logging.Level;
import java.util.logging.Logger;

public class SplitterSpore implements Spore{
    private static Logger logger = Logger.getLogger("TesztLogger");

    @Override
    public void addEffect(Player p){

        p.setEffects(3, 1);
        logger.log(Level.INFO, "SplitterSpore.addEffect() sikeres");
    }
}
