package com.lgwind.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Node;

import com.lgwind.entity.Floor;
import com.lgwind.entity.Room;
import com.lgwind.entity.Tiezi;

public class TieziXML {
    
    public static Tiezi getTiezi(String filename) {
      //根据文件名从xml文件中提取相应信息
        XML xml = new XML(filename);
        String title = xml.getString("title");
        String content = xml.getString("content");
        String update = xml.getString("update");
        List<Floor> floor = getFloor(filename);
        int click = xml.getInt("click");
        //将提取到的信息保存到Tiezi类中
        Tiezi tiezi = new Tiezi();
        tiezi.setTitle(title);
        tiezi.setContent(content);
        tiezi.setUpdate(update);
        tiezi.setFloor(floor);
        tiezi.setTextNum(tiezi.num());
        tiezi.setFilename(filename.replace("\\", "\\\\"));
        tiezi.setClick(click);
        return tiezi;
    }
    
    /**
     * 获取所有帖子 根据文件夹目录和关键词
     * @param filepath 文件夹目录
     * @param search 关键词
     * @param before 在关键词之前添加的字符串
     * @param last 在关键词之后添加的字符串
     * @return
     */
    public static List<Tiezi> getTiezis(String filepath, String search, String before, String last){
        List<Tiezi> list = new ArrayList<Tiezi>();
        //获取所有帖子
        List<Tiezi> tieziList = getTiezis(filepath);
        //遍历所有帖子
        for(int i=0; i<tieziList.size(); i++){
            Tiezi tiezi = tieziList.get(i);
            //在标题中查有此关键词
            if(StringLgwind.search(tiezi.getTitle(), search)){
                tiezi.setTitle(StringLgwind.search(tiezi.getTitle(), search,before,last));
                list.add(tiezi);
            }
            //在内容中查有此关键词
            else if(StringLgwind.search(tiezi.getContent(), search)){
                tiezi.setContent(StringLgwind.search(tiezi.getContent(), search,before,last));
                list.add(tiezi);
            }        
        }
        return list;
    }
    
    /**
     * 获取所有帖子 根据文件夹目录
     * @param filepath
     * @return
     */
    public static List<Tiezi> getTiezis(String filepath){
        List<Tiezi> list = new ArrayList<Tiezi>();
        //获取xml文件列表
        List<String> wjList = getFiles(filepath, "xml");
        for(int i=0; i<wjList.size(); i++){
            String filename = filepath + wjList.get(i);
            //根据文件名从xml文件中提取相应信息
            Tiezi tiezi = getTiezi(filename);
            list.add(tiezi);
        }
        return list;
    }
    
    /**
     * 获取层 根据文件名
     * @param filename
     * @return
     */
    public static List<Floor> getFloor(String filename) {
        List<Floor> list = new ArrayList<Floor>();
        //根据文件名从xml文件中提取相应信息
        XML xml = new XML(filename);
        List<Node> nodes = xml.getList("floor");
        for(int i=0; i<nodes.size(); i++) {
            //根据文件名从xml文件中提取相应信息
            int floorNum = xml.getInt("floorNum",nodes.get(i));
            String floorContent = xml.getString("floorContent",nodes.get(i));
            String floorUpdate = xml.getString("floorUpdate",nodes.get(i));
            List<Room> room = getRoom(filename, nodes.get(i));
            //将提取到的信息保存到Floor类中
            Floor floor = new Floor();
            floor.setFloorNum(floorNum);
            floor.setFloorContent(floorContent);
            floor.setFloorUpdate(floorUpdate);
            floor.setRoom(room);
            list.add(floor);
        }
        return list;
    }
    
    
    private static List<Room> getRoom(String filename, Node node) {
        List<Room> list = new ArrayList<Room>();
        //根据文件名从xml文件中提取相应信息
        XML xml = new XML(filename);
        List<Node> nodes = xml.getList("room", node);
        for(int i=0; i<nodes.size(); i++) {
            //根据文件名从xml文件中提取相应信息
            String roomLord = xml.getString("roomLord", nodes.get(i));
            String roomContent = xml.getString("roomContent", nodes.get(i));
            String roomUpdate = xml.getString("roomUpdate", nodes.get(i));
            //将提取到的信息保存到Room类中
            Room room = new Room();
            room.setRoomLord(roomLord);
            room.setRoomContent(roomContent);
            room.setRoomUpdate(roomUpdate);
            list.add(room);
        }
        return list;
    }

    /**
     * 获取所有文件列表 根据文件夹目录和文件后缀名
     * @param filepath 路径
     * @return
     */
    private static List<String> getFiles(String filepath, String suffixName){
        List<String> wjList = new ArrayList<String>();//新建一个文件集合
        try {
            File file = new File(filepath);    //File类型可以是文件也可以是文件夹
            File[] fileList = file.listFiles();//将该目录下的所有文件放置在一个File类型的数组中
            //遍历该目录下的所有文件和文件夹
            for (int i = 0; i < fileList.length; i++) {
               if (fileList[i].isFile()) {//判断是否为文件
                   //获取文件名
                   String fileName = fileList[i].getName();
                   //获取文件后缀名
                   String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
                   if(suffix.equals(suffixName)){
                       wjList.add(fileName);
                   }
               }
            }
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("获取目录下的所有xml文件的列表失败！");
        }        
        return wjList;
    }  

}
