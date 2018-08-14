package com.lgwind.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class FileIO {
    
    /**
     * 读取文件(后缀不写，固定为.lgwind文件)
     * @param path 文件路径
     * @param name 文件名
     * @param format 文件内容格式 建议："GB2312"
     * @return
     */
    public static String read(String path, String name, String format){
        return read(path +"\\"+name, format); // 路径)
    }
    
    /**
     * 读取文件(后缀不写，固定为.lgwind文件)
     * @param pathname 文件路径+名字
     * @param format 文件内容格式 建议："GB2312"
     * @return
     */
    public static String read(String pathname, String format) {
        // 防止文件建立或读取失败，用catch捕捉错误并打印，也可以throw
        try {
//            pathname += ".lgwind";            
            File filename = new File(pathname); // 要读取以上路径的input.txt文件
            InputStreamReader reader = new InputStreamReader(
                    new FileInputStream(filename), format); // 建立一个输入流对象reader
            BufferedReader br = new BufferedReader(reader); // 建立一个对象，它把文件内容转成计算机能读懂的语言
            //返回的数据
            String content = "";
            //读取每行的内容
            String line = "";
            //读取一行内容
            line = br.readLine();
            while (line != null) {
                content += line+"\n";
                line = br.readLine(); // 一次读入一行数据
            }
            br.close(); // 最后记得关闭文件
            return content;
        } catch (Exception e) {
            System.out.println("读取文件失败");
            e.printStackTrace();
            return "读取文件失败";
        }

    }

    /**
     * 写入文件(后缀不写，固定为.lgwind文件)
     * @param path 文件路径
     * @param name 文件名
     * @param content 文件内容
     * @param format 文件内容格式 建议："GB2312"
     */
    public static void write(String path, String name, String content, String format) {
        write(path+"\\"+name, content, format);
    }
    
    /**
     * 写入文件(后缀不写，固定为.lgwind文件)
     * @param pathname 文件路径+名字
     * @param content
     * @param format 文件内容格式
     * @param update 是否是修改文件
     */
    public static void write(String pathname, String content, String format) {
        // 防止文件建立或读取失败，用catch捕捉错误并打印，也可以throw
        try {
//            pathname += ".lgwind";            
            File writename = new File(pathname); 
            //若文件不存在，则创建新文件
            if(!writename.exists()){
                System.out.println("新建文件：" + pathname);
                writename.createNewFile(); // 创建新文件
            }
            //若文件不存在则创建新文件
            FileOutputStream writerStream = new FileOutputStream(writename);
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(
                    writerStream, format));
            out.write(content);// 写入内容
            out.flush(); // 把缓存区内容压入文件
            out.close(); // 最后记得关闭文件
        } catch (Exception e) {
            System.out.println("写入文件失败");
            e.printStackTrace();
        }
    }
    
    /**
     * 删除文件(后缀不写，固定为.lgwind文件)
     * @param path 要删除的文件路径
     * @param name 要删除的文件名
     * @return
     */
    public static boolean delete(String path, String name) {
        return delete(path+"\\"+name);
    }
    
    /**
     * 删除文件(后缀不写，固定为.lgwind文件)
     * @param fileName 要删除的文件的文件名
     * @return 
     */
    public static boolean delete(String pathname) {
//        pathname += ".lgwind";
        File file = new File(pathname);
        // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
        if (file.exists() && file.isFile()) {
            if (file.delete()) {
                System.out.println("删除文件成功！文件：" + pathname);
                return true;
            } else {
                System.out.println("删除文件失败！文件：" + pathname);
                return false;
            }
        } else {
            System.out.println("删除文件失败：" + pathname + "文件不存在！");
            return false;
        }
    }
    
    /**
     * 获取目录下的所有文件(后缀不写，固定为.lgwind文件)
     * @param filepath 路径
     * @return
     */
    public static List<String> getFiles(String filepath){
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
//                   String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
//                   if(suffix.equals("lgwind")){
                       wjList.add(fileName);
//                   }
               }
            }
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("获取目录下的所有lgwind文件的列表失败！");
        }        
        return wjList;
    }   
    
}