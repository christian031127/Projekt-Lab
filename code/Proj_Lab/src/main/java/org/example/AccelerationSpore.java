package org.example;


public class AccelerationSpore extends GameObject implements Spore {

    @Override
    public void addEffect(Player p){
        System.out.println("AccelerationSpore.addEffect(Player p) called");
        new Player().setEffects(new int[4]);
    }
}
