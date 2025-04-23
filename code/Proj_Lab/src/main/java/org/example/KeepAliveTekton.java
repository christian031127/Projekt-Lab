package org.example;

public class KeepAliveTekton implements TektonStrategy {
    @Override
    public void doEffect(Tekton T) {
        //keeps the yarns on this tekton alive even after disconnecting from their shroom
        // When backtracking, call this function before clearing yarns
        System.out.println("KeepAliveTekton.doEffect() called");
    }

}
