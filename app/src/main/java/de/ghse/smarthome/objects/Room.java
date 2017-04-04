package de.ghse.smarthome.objects;

import java.util.ArrayList;

/**
 * Created by Thorben Auer on 03.04.2017.
 */

public class Room {

    // Anfang Attribute
    private String name;
    private ArrayList<Element> elementArrayList = new ArrayList<Element>();
    private int objectcounter;
    // Ende Attribute

    // Anfang Methoden
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public void addElement(Element element){
        elementArrayList.add(element);
    }
}
