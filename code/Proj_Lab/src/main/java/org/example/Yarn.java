package org.example;

public class Yarn {
    public void setTekton1(Tekton tekton1) {
        if(tekton1==null){
            new Tekton().removeYarn(this);
        }
        else{
            tekton1.addYarn(this);
        }

        System.out.println("Yarn.setTekton1() called");
    }

    public void setTekton2(Tekton tekton2) {
        if(tekton2==null){
            new Tekton().removeYarn(this);
        }
        else{
            tekton2.addYarn(this);
        }
        System.out.println("Yarn.setTekton2() called");
    }

    public Tekton getTekton1() {
        return new Tekton();
    }

    public Tekton getTekton2() {
        return new Tekton();
    }
}
