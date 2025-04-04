package org.example;
import static org.example.Main.igazHamisKerdes;

public class AbsorbTekton extends GameObject implements TektonStrategy{
    @Override
    public void doEffect() {
        System.out.println("AbsorbTekton.doEffect() called");
        while (igazHamisKerdes("Van még fonal a tektonon?")) {
            new Tekton().removeYarn(new Yarn());
        }
    }

}
