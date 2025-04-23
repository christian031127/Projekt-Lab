package org.example;

public class WeakeningSpore implements Spore {

    @Override
    public void addEffect(Player p){
        System.out.println("WeakeningSpore.addEffect(Player p) called");
        new Player().setEffects(4, 1);
    }
}
