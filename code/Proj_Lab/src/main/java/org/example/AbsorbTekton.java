package org.example;
import static org.example.Main.logger;

public class AbsorbTekton implements TektonStrategy{
    @Override
    public void doEffect(Tekton T) {
        for (Yarn yarn : T.getYarns()) {
            if (yarn.isSingleTektonYarn()) {
                T.removeYarn(yarn);
            }
            logger.info("doEffect() sikeres");
        }
    }

}
