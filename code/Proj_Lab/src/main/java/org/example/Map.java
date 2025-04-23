package org.example;

public class Map {

    //private Tektons;
    //private Players;
    //public currentPlayer;
    public static int currentTurn = 0;

    public void loadMap(){
        // read file, load map
    }
    public void splitTekton(Tekton tekton){
        System.out.println("Map.splitTekton() called");
        tekton.split();
    }
}
