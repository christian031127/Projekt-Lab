package org.example;
import static org.example.Main.logger;

public class MultipleYarnTekton implements TektonStrategy {
    @Override
    public void doEffect(Tekton T) {
        logger.info("MultipleYarnTekton.doEffect() called");
        T.deleteYarnsOnTekton();
    }

}
