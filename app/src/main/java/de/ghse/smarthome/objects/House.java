package de.ghse.smarthome.objects;

import java.util.ArrayList;

/**
 * Created by Thorben Auer on 03.04.2017.
 */

public class House {
    // Anfang Attribute
    private ArrayList<Room> roomArrayList= new ArrayList<Room>();
    private String name;
    private String owner;
    // Ende Attribute

    // Anfang Methoden
    public Room getRoomById(int i) {
        return roomArrayList.get(i);
    }

    public void addRoom(Room room) {
        this.roomArrayList.add(room);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
}
