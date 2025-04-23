package org.example;
import static org.example.Main.igazHamisKerdes;

public class AbsorbTekton implements TektonStrategy{
    @Override
    public void doEffect(Tekton T) {
        System.out.println("AbsorbTekton.doEffect() called");
        while (igazHamisKerdes("Van még fonal a tektonon?")) {
            // Yarn mindkét attr ugyanaz akkor remove
            T.removeYarn(new Yarn());
        }
    }

}
