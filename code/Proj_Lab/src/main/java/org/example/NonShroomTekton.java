package org.example;
import static org.example.Main.logger;

public class NonShroomTekton implements TektonStrategy {
    @Override
    public void doEffect(Tekton T) {
        if (T.getShroom() != null) {
            T.removeShroom();
            logger.info("NonshroomTekton.doEffect() sikeres");
        }
        T.deleteYarnsOnTekton();
    }
}
