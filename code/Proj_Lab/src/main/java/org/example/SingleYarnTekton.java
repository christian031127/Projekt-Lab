package org.example;
import static org.example.Main.logger;

public class SingleYarnTekton implements TektonStrategy {
    @Override
    public void doEffect(Tekton T) {
        if (T.getYarns().size() > 1) {
            Yarn yarnToKeep = T.getYarns().get(0);
            for (Yarn yarn : T.getYarns()) {
                if (!yarn.equals(yarnToKeep)) {
                    T.removeYarn(yarn);
                }
            }
            logger.info("doEffect() sikeres");
        }
        T.deleteYarnsOnTekton();
    }
}
