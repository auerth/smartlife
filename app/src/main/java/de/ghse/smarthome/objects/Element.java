package de.ghse.smarthome.objects;

/**
 * Created by Thorben Auer on 03.04.2017.
 */

public class Element {

    // Anfang Attribute
    private String name;           //Name of the element
    private String description;    //Description of the element
    private boolean status;            //status of the element
    // Ende Attribute

    // Anfang Methoden

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setOn() {
        if(status = false){

        }
    }

    public void setOff() {
        if(status = true){

        }
    }

    // Ende Methoden
}
