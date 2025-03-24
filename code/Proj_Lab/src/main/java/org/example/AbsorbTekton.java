package org.example;
import static org.example.Main.igazHamisKerdes;

public class AbsorbTekton implements TektonStrategy {
    @Override
    public void doEffect() {
        System.out.println("AbsorbTekton.doEffect() meghívva");
        while (igazHamisKerdes("Van még fonal a tektonon?")) {
            new Tekton().removeYarn(new Yarn());
        }
    }

}
