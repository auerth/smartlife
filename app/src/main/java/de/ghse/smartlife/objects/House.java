package de.ghse.smartlife.objects;

import java.util.ArrayList;

public class House {
    // Attribute
    private ArrayList<Room> arrayListRoom = new ArrayList<>();
    private String name;
    private int port;
    private String ip;
    // Attribute

    // Methoden
    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getIp() {
        return this.ip;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getPort() {
        return this.port;
    }

    //get all rooms
    public Room getRoom(int pos) {
        return arrayListRoom.get(pos);
    }
    public int getArrayListRoomSize(){
        return arrayListRoom.size();
    }

    //Add room to array List
    public void addRoom(Room room) {
        this.arrayListRoom.add(room);
    }

    //Remove room from arrayList
    public void removeRoom(int pos) {
        arrayListRoom.remove(pos);
    }

    //get name
    public String getName() {
        return name;
    }

    //set name
    public void setName(String name) {
        this.name = name;
    }


}