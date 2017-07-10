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
    /*
    *Get ip for server
     */
    public String getIp() {
        return this.ip;
    }

    /*
    *Set ip for ServerConnection
    * @param ip ip of server
     */
    public void setIp(String ip) {
        this.ip = ip;
    }

    /*
    *Get port for ServerConnection
     */
    public int getPort() {
        return this.port;
    }

    /*
    *Set port for ServerConnection
    * @param port port of server
     */
    public void setPort(int port) {
        this.port = port;
    }

    /*
    *Get room from arrayListRoom
    * @param pos position of room to get
     */
    public Room getRoom(int pos) {
        return arrayListRoom.get(pos);
    }

    /*
    *Get size of arrayListRoom
     */
    public int getArrayListRoomSize() {
        return arrayListRoom.size();
    }

    /*
    *Add room to arrayListRoom
    * @param room room to add
     */
    public void addRoom(Room room) {
        this.arrayListRoom.add(room);
    }

    /*
    *Remove rome by position from arrayListRoom
    * @param pos position of room to remove
     */
    public void removeRoom(int pos) {
        arrayListRoom.remove(pos);
    }

    /*
    *Get name of House
     */
    public String getName() {
        return name;
    }

    /*
    *Set name of house
    * @param name name of house
     */
    public void setName(String name) {
        this.name = name;
    }


}