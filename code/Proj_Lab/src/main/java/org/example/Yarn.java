package org.example;

public class Yarn {
    public void setTekton1(Tekton tekton1) {
        System.out.println("Yarn.setTekton1() meghívva");
    }

    public void setTekton2(Tekton tekton2) {
        System.out.println("Yarn.setTekton2() meghívva");
    }

    public Tekton getTekton1() {
        return new Tekton();
    }

    public Tekton getTekton2() {
        return new Tekton();
    }
}
