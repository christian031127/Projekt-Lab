package org.example;

public class Map {

    public void loadMap(){

    }
    public void splitTekton(Tekton tekton){
        System.out.println("Map.splitTekton() called");
        new Tekton().split();
    }
}
