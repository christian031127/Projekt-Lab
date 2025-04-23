package org.example;

public class SplitterSpore implements Spore{

    @Override
    public void addEffect(Player p){
        System.out.println("SplitterSpore.addEffect(Player p) called");
        new Player().setEffects(3, 1);
    }
}
