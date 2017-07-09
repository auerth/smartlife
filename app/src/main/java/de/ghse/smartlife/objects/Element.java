package de.ghse.smartlife.objects;

import java.util.BitSet;

/**
 * Created by Thorben Auer on 03.04.2017.
 */

public class Element {

    // Anfang Attribute
    private String name;           //Name of the element
    private int id;
    private Type type;
    // Ende Attribute
    public static enum Type{
        temperature,light
    }
    private boolean on = false;

    public boolean isOn(){
        return on;
    }

    public void setOn(boolean on){
        this.on = on;
    }

    // Anfang Methoden
    public Element(String name,int id,Type type){
        this.name = name;
        this.id = id;
        this.type = type;
    }

    public int getId(){
        return id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Type getType(){
        return this.type;
    }
    //Set the Bit Code for the element to turn on


    public String getName() {
        return name;
    }


    // Ende Methoden
}