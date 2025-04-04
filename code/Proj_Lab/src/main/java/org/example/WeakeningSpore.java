package org.example;

public class WeakeningSpore extends GameObject implements Spore {

    @Override
    public void addEffect(Player p){
        System.out.println("WeakeningSpore.addEffect(Player p) called");
        new Player().setEffects(new int[4]);
    }
}
