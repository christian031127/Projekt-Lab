package org.example;

public class AbsorbTekton implements TektonStrategy {
    @Override
    public void doEffect() {
        System.err.println("AbsorbTekton.doEffect() meghívva");
    }

}
