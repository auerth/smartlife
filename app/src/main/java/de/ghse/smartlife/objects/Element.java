package de.ghse.smartlife.objects;

public class Element {

    // Attribute
    private String name;           //Name of the element
    private int id;
    private Type type;

    /*
    *Call when Element was created
     */
    public Element(String name, int id, Type type) {
        this.name = name;
        this.id = id;
        this.type = type;
    }
    // Attribute
    // Methoden

    /*
    *Get id of Element
     */
    public int getId() {
        return id;
    }

    /*
    *Get type of element
     */
    public Type getType() {
        return this.type;
    }

    /*
    *Get name of element
     */
    public String getName() {
        return name;
    }

    /*
    *Set name of Element
     */
    public void setName(String name) {
        this.name = name;
    }

    public enum Type {
        temperature, light
    }


    // Methoden
}