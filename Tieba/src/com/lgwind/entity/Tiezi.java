package com.lgwind.entity;

import java.util.ArrayList;
import java.util.List;

public class Tiezi {
    
    private String title;
    private String content;
    private String update;
    private List<Floor> floor=new ArrayList<Floor>();
    private int textNum;
    private String filename;
    private int click;
    
    public Tiezi() {
        super();
//        Floor floor = new Floor();
//        this.floor.add(floor);
        // TODO Auto-generated constructor stub
    }
    
    public Tiezi(String title, String content, String update,
            List<Floor> floor, int textNum, String filename, int click) {
        super();
        this.title = title;
        this.content = content;
        this.update = update;
        this.floor = floor;
        this.textNum = textNum;
        this.filename = filename;
        this.click = click;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getUpdate() {
        return update;
    }
    public void setUpdate(String update) {
        this.update = update;
    }
    public List<Floor> getFloor() {
        return floor;
    }
    public void setFloor(List<Floor> floor) {
        this.floor = floor;
    }
    public int getTextNum() {
        return textNum;
    }
    public void setTextNum(int textNum) {
        this.textNum = textNum;
    }
    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public int getClick() {
        return click;
    }
    public void setClick(int click) {
        this.click = click;
    }
    
    @Override
    public String toString() {
        return "{ \"title\":\"" + title + "\", "
                + "\"content\":\"" + content + "\", "
                + "\"update\":\"" + update + "\", "
                + "\"floor\":" + floor + ", "
                + "\"textNum\":\""+ textNum + "\", "
                + "\"filename\":\""+ filename + "\", "
                + "\"click\":\"" + click + "\"}";
    }
    
    /**
     * ¼ÆËãÌû×Ó×ÖÊý
     */
    public int num(){
        int num = title.length() + content.length();
        for(int i=0; i<floor.size();i++){
            num += floor.get(i).getFloorContent().length();
            for(int j=0; j<floor.get(i).getRoom().size(); j++){
                num += floor.get(i).getRoom().get(j).getRoomContent().length();
            }
        }
        return num;
    }

}
