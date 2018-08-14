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
      //�����ļ�����xml�ļ�����ȡ��Ӧ��Ϣ
        XML xml = new XML(filename);
        String title = xml.getString("title");
        String content = xml.getString("content");
        String update = xml.getString("update");
        List<Floor> floor = getFloor(filename);
        int click = xml.getInt("click");
        //����ȡ������Ϣ���浽Tiezi����
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
     * ��ȡ�������� �����ļ���Ŀ¼�͹ؼ���
     * @param filepath �ļ���Ŀ¼
     * @param search �ؼ���
     * @param before �ڹؼ���֮ǰ��ӵ��ַ���
     * @param last �ڹؼ���֮����ӵ��ַ���
     * @return
     */
    public static List<Tiezi> getTiezis(String filepath, String search, String before, String last){
        List<Tiezi> list = new ArrayList<Tiezi>();
        //��ȡ��������
        List<Tiezi> tieziList = getTiezis(filepath);
        //������������
        for(int i=0; i<tieziList.size(); i++){
            Tiezi tiezi = tieziList.get(i);
            //�ڱ����в��д˹ؼ���
            if(StringLgwind.search(tiezi.getTitle(), search)){
                tiezi.setTitle(StringLgwind.search(tiezi.getTitle(), search,before,last));
                list.add(tiezi);
            }
            //�������в��д˹ؼ���
            else if(StringLgwind.search(tiezi.getContent(), search)){
                tiezi.setContent(StringLgwind.search(tiezi.getContent(), search,before,last));
                list.add(tiezi);
            }        
        }
        return list;
    }
    
    /**
     * ��ȡ�������� �����ļ���Ŀ¼
     * @param filepath
     * @return
     */
    public static List<Tiezi> getTiezis(String filepath){
        List<Tiezi> list = new ArrayList<Tiezi>();
        //��ȡxml�ļ��б�
        List<String> wjList = getFiles(filepath, "xml");
        for(int i=0; i<wjList.size(); i++){
            String filename = filepath + wjList.get(i);
            //�����ļ�����xml�ļ�����ȡ��Ӧ��Ϣ
            Tiezi tiezi = getTiezi(filename);
            list.add(tiezi);
        }
        return list;
    }
    
    /**
     * ��ȡ�� �����ļ���
     * @param filename
     * @return
     */
    public static List<Floor> getFloor(String filename) {
        List<Floor> list = new ArrayList<Floor>();
        //�����ļ�����xml�ļ�����ȡ��Ӧ��Ϣ
        XML xml = new XML(filename);
        List<Node> nodes = xml.getList("floor");
        for(int i=0; i<nodes.size(); i++) {
            //�����ļ�����xml�ļ�����ȡ��Ӧ��Ϣ
            int floorNum = xml.getInt("floorNum",nodes.get(i));
            String floorContent = xml.getString("floorContent",nodes.get(i));
            String floorUpdate = xml.getString("floorUpdate",nodes.get(i));
            List<Room> room = getRoom(filename, nodes.get(i));
            //����ȡ������Ϣ���浽Floor����
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
        //�����ļ�����xml�ļ�����ȡ��Ӧ��Ϣ
        XML xml = new XML(filename);
        List<Node> nodes = xml.getList("room", node);
        for(int i=0; i<nodes.size(); i++) {
            //�����ļ�����xml�ļ�����ȡ��Ӧ��Ϣ
            String roomLord = xml.getString("roomLord", nodes.get(i));
            String roomContent = xml.getString("roomContent", nodes.get(i));
            String roomUpdate = xml.getString("roomUpdate", nodes.get(i));
            //����ȡ������Ϣ���浽Room����
            Room room = new Room();
            room.setRoomLord(roomLord);
            room.setRoomContent(roomContent);
            room.setRoomUpdate(roomUpdate);
            list.add(room);
        }
        return list;
    }

    /**
     * ��ȡ�����ļ��б� �����ļ���Ŀ¼���ļ���׺��
     * @param filepath ·��
     * @return
     */
    private static List<String> getFiles(String filepath, String suffixName){
        List<String> wjList = new ArrayList<String>();//�½�һ���ļ�����
        try {
            File file = new File(filepath);    //File���Ϳ������ļ�Ҳ�������ļ���
            File[] fileList = file.listFiles();//����Ŀ¼�µ������ļ�������һ��File���͵�������
            //������Ŀ¼�µ������ļ����ļ���
            for (int i = 0; i < fileList.length; i++) {
               if (fileList[i].isFile()) {//�ж��Ƿ�Ϊ�ļ�
                   //��ȡ�ļ���
                   String fileName = fileList[i].getName();
                   //��ȡ�ļ���׺��
                   String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
                   if(suffix.equals(suffixName)){
                       wjList.add(fileName);
                   }
               }
            }
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("��ȡĿ¼�µ�����xml�ļ����б�ʧ�ܣ�");
        }        
        return wjList;
    }  

}
