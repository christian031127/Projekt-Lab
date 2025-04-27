package org.example;
import static org.example.Main.logger;

public class MultipleYarnTekton implements TektonStrategy {
    @Override
    public void doEffect(Tekton T) {
        T.deleteYarnsOnTekton();
        logger.info("doeffect() sikeres");
    }

}
