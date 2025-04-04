package org.example;

public class SlowingSpore extends GameObject implements Spore {

    @Override
    public void addEffect(Player p){
        System.out.println("SlowingSpore.addEffect(Player p) called");
        new Player().setEffects(new int[4]);
    }
}
