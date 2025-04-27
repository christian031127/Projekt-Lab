package org.example;
import static org.example.Main.logger;

public class KeepAliveTekton implements TektonStrategy {
    @Override
    public void doEffect(Tekton T) {
        logger.info("doEffect() sikeres");
    }
}