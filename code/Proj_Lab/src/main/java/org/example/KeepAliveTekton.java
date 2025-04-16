package org.example;

public class KeepAliveTekton extends GameObject implements TektonStrategy {
    @Override
    public void doEffect() {
        //keeps the yarns on this tekton alive even after disconnecting from their shroom
        System.out.println("KeepAliveTekton.doEffect() called");
    }

}
