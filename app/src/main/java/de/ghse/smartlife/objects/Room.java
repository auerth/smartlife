package de.ghse.smartlife.objects;

import android.util.Log;

import java.util.ArrayList;


public class Room {

    // Attribute
    private String name;
    private ArrayList<Element> arrayListElement = new ArrayList<>();
    private int id;
    // Attribute

    // Methoden

    /**
     * Call when Room was created
     *
     * @param id id of room
     */
    public Room(int id) {
        this.id = id;
    }

    /**
     * Get name of room
     */
    public String getName() {
        return name;
    }

    /**
     * Set name of room
     *
     * @param name name of room
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get id of room
     */
    public int getId() {
        return this.id;
    }

    /**
     * Get id of room
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Add Element to arrayListElement
     *
     * @param element element to add
     */
    public void addElement(Element element) {
        arrayListElement.add(element);
    }

    /**
     * Remove Element from arrayListElement
     *
     * @param pos position of element to remove
     */
    public void removeElement(int pos) {
        Log.e(getName(), "Elements: " + arrayListElement.size());
        arrayListElement.remove(pos);
    }

    /**
     * Get Element by position from arrayListElement
     *
     * @param pos position of element to get
     */
    public Element getElementByPosition(int pos) {
        return arrayListElement.get(pos);
    }

    /**
     * Get size of arrayListElement
     */
    public int getSizeOfElementList() {
        return arrayListElement.size();
    }
    // Methoden
}