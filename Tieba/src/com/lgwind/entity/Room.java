package com.lgwind.entity;

public class Room {
    
    private String roomLord;
    private String roomContent;
    private String roomUpdate;
    
    public Room() {
        super();
        // TODO Auto-generated constructor stub
    }

    public Room(String roomLord, String roomContent, String roomUpdate) {
        super();
        this.roomLord = roomLord;
        this.roomContent = roomContent;
        this.roomUpdate = roomUpdate;
    }

    public String getRoomLord() {
        return roomLord;
    }

    public void setRoomLord(String roomLord) {
        this.roomLord = roomLord;
    }

    public String getRoomContent() {
        return roomContent;
    }

    public void setRoomContent(String roomContent) {
        this.roomContent = roomContent;
    }

    public String getRoomUpdate() {
        return roomUpdate;
    }

    public void setRoomUpdate(String roomUpdate) {
        this.roomUpdate = roomUpdate;
    }

    @Override
    public String toString() {
        return "{ \"roomLord\":\"" + roomLord + "\", "
               + "\"roomContent\":\"" + roomContent   + "\", "
               + "\"roomUpdate\":\"" + roomUpdate + "\"}";
    }    

}
