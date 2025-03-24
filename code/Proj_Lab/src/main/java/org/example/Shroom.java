package org.example;

public class Shroom {
    // private int team_id;
    // private Tekton tekton;
    // private int _age;
    // private int lastEject;

    public boolean isOld() {
        System.out.println("Gomba isOld() metódusa meghívva");
        return true;
    }

    public void ejectSpore(Tekton T) {
        System.out.println("Shroom.ejectSpore() called");
    }

    public void isSporeReady() {
        System.out.println("Shroom.isSporeReady() called");
    }

    public void die() { 
        System.out.println("Gomba die() metódusa meghívva.");
    }

    public void age() {
        System.out.println("Gomba age() metódusa meghívva.");
        if(isOld())
            die();
    }

    public void getTeam_id() {
        // return team_id;
    }

    public Tekton getTekton() {
        return new Tekton();
    }

    public void getAge() {
        System.out.println("Shroom.GetAge() called!");
        // return _age;
    }

    public void getLastEject() {
        // return lastEject;
    }

    public void setTeam_id(int id) {
        // this.team_id = id;
    }

    public void setTekton(Tekton T) {
        // this.tekton = T;
    }
}
