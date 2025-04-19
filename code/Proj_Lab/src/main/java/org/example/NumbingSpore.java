package org.example;

public class NumbingSpore implements Spore{

    @Override
    public void addEffect(Player p){
        System.out.println("NumbingSpore.addEffect(Player p) called");
        new Player().setEffects(new int[4]);
    }
}
