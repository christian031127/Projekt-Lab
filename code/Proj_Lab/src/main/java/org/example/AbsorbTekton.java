package org.example;

public class AbsorbTekton implements TektonStrategy{
    @Override
    public void doEffect(Tekton T) {
        /* System.out.println("AbsorbTekton.doEffect() called");
        while (igazHamisKerdes("Van még fonal a tektonon?")) {
            // Yarn mindkét attr ugyanaz akkor remove*/
        
        for (Yarn yarn : T.getYarns()) {
            if (yarn.isSingleTektonYarn()) {
                T.removeYarn(yarn);
            }
        }
    }

}
