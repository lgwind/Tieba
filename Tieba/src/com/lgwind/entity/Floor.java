package com.lgwind.entity;

import java.util.ArrayList;
import java.util.List;

public class Floor {

    private int floorNum;
    private String floorContent;
    private String floorUpdate;
    private List<Room> room=new ArrayList<Room>();
    
    public Floor() {
        super();
//        Room room = new Room();
//        this.room.add(room);
        // TODO Auto-generated constructor stub
    }

    public Floor(int floorNum, String floorContent, String floorUpdate,
            List<Room> room) {
        super();
        this.floorNum = floorNum;
        this.floorContent = floorContent;
        this.floorUpdate = floorUpdate;
        this.room = room;
    }

    public int getFloorNum() {
        return floorNum;
    }

    public void setFloorNum(int floorNum) {
        this.floorNum = floorNum;
    }

    public String getFloorContent() {
        return floorContent;
    }

    public void setFloorContent(String floorContent) {
        this.floorContent = floorContent;
    }

    public String getFloorUpdate() {
        return floorUpdate;
    }

    public void setFloorUpdate(String floorUpdate) {
        this.floorUpdate = floorUpdate;
    }

    public List<Room> getRoom() {
        return room;
    }

    public void setRoom(List<Room> room) {
        this.room = room;
    }

    @Override
    public String toString() {
        return "{ \"floorNum\":\"" + floorNum + "\", "
                + "\"floorContent\":\"" + floorContent   + "\", "
                + "\"floorUpdate\":\"" + floorUpdate + "\", "
                + "\"room\":" + room + "}";
    }
    
    

}
