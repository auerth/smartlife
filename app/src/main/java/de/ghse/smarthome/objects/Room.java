package de.ghse.smarthome.objects;

import android.util.Log;

import java.util.ArrayList;


public class Room {

    // Anfang Attribute
    private String name;
    private ArrayList<Element> elementArrayList = new ArrayList<>();
    private int id;
    // Ende Attribute

    // Anfang Methoden
    public Room(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return this.id;
    }

    //Add element to elementArrayList
    public void addElement(Element element) {
        elementArrayList.add(element);
    }

    public void removeElement(int pos) {
        Log.e(getName(), "Elements: " + elementArrayList.size());
        elementArrayList.remove(pos);
    }

    public Element getElementByPosition(int pos) {
        return elementArrayList.get(pos);
    }

    public int getSizeOfElementList() {
        return elementArrayList.size();
    }
}