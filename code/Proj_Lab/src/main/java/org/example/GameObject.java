package org.example;

public class GameObject {
    
    protected String name;

    public GameObject(){

    }

    public GameObject(String name){
        this.name = name;
    }

    public String GetName(){
        return name;
    } 

    public void SetName(String newName){
        name = newName;
    }
}
