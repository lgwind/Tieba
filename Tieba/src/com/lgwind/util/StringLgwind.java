package com.lgwind.util;

import java.util.ArrayList;
import java.util.List;

public class StringLgwind {
    
    /**
     * ƥ��ؼ��� ���ַ�����ƥ��ؼ��ʣ�ģ����ѯ��
     * @param content Ҫ�������ַ���
     * @param key �ؼ���
     * @return
     */
    public static boolean search(String content, String key) {
        //���ؼ��ʷָ�Ϊ�ַ����б�
        List<String> keyList = splitChineseEnglish(key);
        //�����ؼ����ַ����б�
        for(int i=0; i<keyList.size(); i++){
            String str = keyList.get(i);
            //�����޴��ַ���Ƭ��
            if(content.indexOf(str) == -1){
                return false;
            }
        }
        return true;
    }
    
    /**
     * ƥ��ؼ��� ���ַ�����ƥ��ؼ��ʣ�ģ����ѯ��
     * @param content Ҫ�������ַ���
     * @param key �ؼ���
     * @param before �ڹؼ���ǰ��ӵ�����
     * @param last �ڹؼ��ʺ���ӵ�����
     * @return
     */
    public static String search(String content, String key, String before, String last) {
        if(before == null){
            before = "";
        }
        if(last == null){
            last = "";
        }
        //���ؼ��ʷָ�Ϊ�ַ����б�
        List<String> keyList = splitChineseEnglish(key);
        //�����ؼ����ַ����б�
        for(int i=0; i<keyList.size(); i++){
            String str = keyList.get(i);
            //�����޴��ַ���Ƭ��
            if(content.indexOf(str) == -1){
                return content;
            }else if(!before.equals("") && !last.equals("")){
                int num = content.indexOf(str);
                int num2 = num + str.length();
                String str1 = content.substring(0, num);
                String str2 = content.substring(num2);
                content = str1 + before + str + last + str2;
            }
        }
        return content;
    }
    
    /**
     * �ؼ��ʷָ� ���ؼ��ʷָ�Ϊ�ַ����б������ĺ��Ӻ�Ӣ����ĸƬ�ηָ
     * @param key
     * @return
     */
    private static List<String> splitChineseEnglish(String key){
        List<String> list = new ArrayList<String>();
        //����Ӣ���ַ���
        String english = "";
        //�����ؼ���
        for(int i=0; i<key.length(); i++){
            char c = key.charAt(i);
            //���ΪӢ����ĸ
            if(c>='a'&&c<='z'||c>='A'&&c<='Z'){
                //����һ���ַ�����ΪӢ��
                english += ""+c;
                // TODO
            }
            //��Ϊ��Ӣ���ַ���
            else{
                //��Ӣ���ַ�����Ϊ��
                if(!english.equals("")){
                    list.add(english);
                    english = "";
                }
                if(c >= 0x4E00 && c <= 0x9FA5) {  //���ĺ���
                    list.add(""+c);
                }
            }
        }
        //��Ӣ���ַ�����Ϊ��
        if(!english.equals("")){
            list.add(english);
            english = "";
        }
        return list;
    }
    
    public static void main(String[] args) {
        String str = "djsdlflasdf lsdljsg lsdjf&l fsldjf���Ĺ�˾�ոմ��dsldj������ʱ���˵ط���ddd.dkdkdkkd";
        System.out.println(splitChineseEnglish(str));
    }

}
