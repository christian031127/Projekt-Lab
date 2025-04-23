package org.example;

public class KeepAliveTekton implements TektonStrategy {
    @Override
    public void doEffect(Tekton T) {
        //keeps the yarns on this tekton alive even after disconnecting from their shroom
        // When backtracking, call this function before clearing yarns
        System.out.println("KeepAliveTekton.doEffect() called");
    }
}

/* public void deleteYarnsOnTekton(){
    System.out.println("Tekton.deleteYarnsOnTekton() called");
    // delete all yarns on tekton
    for (Yarn yarn : getYarns()) {
        if (yarn.isSingleTektonYarn()) {
            int id = yarn.getShroomPlayerId();
            boolean hasneighbour = false;
            for (Yarn yarn1 : getYarns()) {
                if (yarn1.getShroomPlayerId() == id) {
                    hasneighbour = true;
                    break;
                }
            }
            if (!hasneighbour && shroom.getTeam_id()!= yarn.getShroomPlayerId()) {
                removeYarn(yarn);
            }
        }
    }
} */