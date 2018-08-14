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
     * ��ȡ�ļ�(��׺��д���̶�Ϊ.lgwind�ļ�)
     * @param path �ļ�·��
     * @param name �ļ���
     * @param format �ļ����ݸ�ʽ ���飺"GB2312"
     * @return
     */
    public static String read(String path, String name, String format){
        return read(path +"\\"+name, format); // ·��)
    }
    
    /**
     * ��ȡ�ļ�(��׺��д���̶�Ϊ.lgwind�ļ�)
     * @param pathname �ļ�·��+����
     * @param format �ļ����ݸ�ʽ ���飺"GB2312"
     * @return
     */
    public static String read(String pathname, String format) {
        // ��ֹ�ļ��������ȡʧ�ܣ���catch��׽���󲢴�ӡ��Ҳ����throw
        try {
//            pathname += ".lgwind";            
            File filename = new File(pathname); // Ҫ��ȡ����·����input.txt�ļ�
            InputStreamReader reader = new InputStreamReader(
                    new FileInputStream(filename), format); // ����һ������������reader
            BufferedReader br = new BufferedReader(reader); // ����һ�����������ļ�����ת�ɼ�����ܶ���������
            //���ص�����
            String content = "";
            //��ȡÿ�е�����
            String line = "";
            //��ȡһ������
            line = br.readLine();
            while (line != null) {
                content += line+"\n";
                line = br.readLine(); // һ�ζ���һ������
            }
            br.close(); // ���ǵùر��ļ�
            return content;
        } catch (Exception e) {
            System.out.println("��ȡ�ļ�ʧ��");
            e.printStackTrace();
            return "��ȡ�ļ�ʧ��";
        }

    }

    /**
     * д���ļ�(��׺��д���̶�Ϊ.lgwind�ļ�)
     * @param path �ļ�·��
     * @param name �ļ���
     * @param content �ļ�����
     * @param format �ļ����ݸ�ʽ ���飺"GB2312"
     */
    public static void write(String path, String name, String content, String format) {
        write(path+"\\"+name, content, format);
    }
    
    /**
     * д���ļ�(��׺��д���̶�Ϊ.lgwind�ļ�)
     * @param pathname �ļ�·��+����
     * @param content
     * @param format �ļ����ݸ�ʽ
     * @param update �Ƿ����޸��ļ�
     */
    public static void write(String pathname, String content, String format) {
        // ��ֹ�ļ��������ȡʧ�ܣ���catch��׽���󲢴�ӡ��Ҳ����throw
        try {
//            pathname += ".lgwind";            
            File writename = new File(pathname); 
            //���ļ������ڣ��򴴽����ļ�
            if(!writename.exists()){
                System.out.println("�½��ļ���" + pathname);
                writename.createNewFile(); // �������ļ�
            }
            //���ļ��������򴴽����ļ�
            FileOutputStream writerStream = new FileOutputStream(writename);
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(
                    writerStream, format));
            out.write(content);// д������
            out.flush(); // �ѻ���������ѹ���ļ�
            out.close(); // ���ǵùر��ļ�
        } catch (Exception e) {
            System.out.println("д���ļ�ʧ��");
            e.printStackTrace();
        }
    }
    
    /**
     * ɾ���ļ�(��׺��д���̶�Ϊ.lgwind�ļ�)
     * @param path Ҫɾ�����ļ�·��
     * @param name Ҫɾ�����ļ���
     * @return
     */
    public static boolean delete(String path, String name) {
        return delete(path+"\\"+name);
    }
    
    /**
     * ɾ���ļ�(��׺��д���̶�Ϊ.lgwind�ļ�)
     * @param fileName Ҫɾ�����ļ����ļ���
     * @return 
     */
    public static boolean delete(String pathname) {
//        pathname += ".lgwind";
        File file = new File(pathname);
        // ����ļ�·������Ӧ���ļ����ڣ�������һ���ļ�����ֱ��ɾ��
        if (file.exists() && file.isFile()) {
            if (file.delete()) {
                System.out.println("ɾ���ļ��ɹ����ļ���" + pathname);
                return true;
            } else {
                System.out.println("ɾ���ļ�ʧ�ܣ��ļ���" + pathname);
                return false;
            }
        } else {
            System.out.println("ɾ���ļ�ʧ�ܣ�" + pathname + "�ļ������ڣ�");
            return false;
        }
    }
    
    /**
     * ��ȡĿ¼�µ������ļ�(��׺��д���̶�Ϊ.lgwind�ļ�)
     * @param filepath ·��
     * @return
     */
    public static List<String> getFiles(String filepath){
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
//                   String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
//                   if(suffix.equals("lgwind")){
                       wjList.add(fileName);
//                   }
               }
            }
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("��ȡĿ¼�µ�����lgwind�ļ����б�ʧ�ܣ�");
        }        
        return wjList;
    }   
    
}