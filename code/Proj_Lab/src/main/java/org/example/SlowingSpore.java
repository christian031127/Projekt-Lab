package org.example;

public class SlowingSpore implements Spore {

    @Override
    public void addEffect(Player p){
        System.out.println("SlowingSpore.addEffect(Player p) meghívva");
    }
}
