package org.example;

public class SplitterSpore extends GameObject implements Spore{

    @Override
    public void addEffect(Player p){
        System.out.println("SplitterSpore.addEffect(Player p) called");
        new Player().setEffects(new int[4]);
    }
}
