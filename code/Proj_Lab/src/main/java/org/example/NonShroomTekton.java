package org.example;
import static org.example.Main.logger;

public class NonShroomTekton implements TektonStrategy {
    @Override
    public void doEffect(Tekton T) {
        logger.info("NonShroomTekton.doEffect() called");
        if (T.getShroom() != null) {
            T.removeShroom();
        }
        T.deleteYarnsOnTekton();
    }
    
}
