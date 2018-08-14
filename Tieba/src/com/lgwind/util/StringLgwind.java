package com.lgwind.util;

import java.util.ArrayList;
import java.util.List;

public class StringLgwind {
    
    /**
     * 匹配关键词 在字符串中匹配关键词（模糊查询）
     * @param content 要搜索的字符串
     * @param key 关键词
     * @return
     */
    public static boolean search(String content, String key) {
        //将关键词分割为字符串列表
        List<String> keyList = splitChineseEnglish(key);
        //遍历关键词字符串列表
        for(int i=0; i<keyList.size(); i++){
            String str = keyList.get(i);
            //若查无此字符串片段
            if(content.indexOf(str) == -1){
                return false;
            }
        }
        return true;
    }
    
    /**
     * 匹配关键词 在字符串中匹配关键词（模糊查询）
     * @param content 要搜索的字符串
     * @param key 关键词
     * @param before 在关键词前添加的内容
     * @param last 在关键词后添加的内容
     * @return
     */
    public static String search(String content, String key, String before, String last) {
        if(before == null){
            before = "";
        }
        if(last == null){
            last = "";
        }
        //将关键词分割为字符串列表
        List<String> keyList = splitChineseEnglish(key);
        //遍历关键词字符串列表
        for(int i=0; i<keyList.size(); i++){
            String str = keyList.get(i);
            //若查无此字符串片段
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
     * 关键词分割 将关键词分割为字符串列表（按中文汉子和英文字母片段分割）
     * @param key
     * @return
     */
    private static List<String> splitChineseEnglish(String key){
        List<String> list = new ArrayList<String>();
        //保存英文字符串
        String english = "";
        //遍历关键词
        for(int i=0; i<key.length(); i++){
            char c = key.charAt(i);
            //如果为英文字母
            if(c>='a'&&c<='z'||c>='A'&&c<='Z'){
                //若上一个字符串不为英文
                english += ""+c;
                // TODO
            }
            //若为非英文字符串
            else{
                //若英文字符串不为空
                if(!english.equals("")){
                    list.add(english);
                    english = "";
                }
                if(c >= 0x4E00 && c <= 0x9FA5) {  //中文汉字
                    list.add(""+c);
                }
            }
        }
        //若英文字符串不为空
        if(!english.equals("")){
            list.add(english);
            english = "";
        }
        return list;
    }
    
    public static void main(String[] args) {
        String str = "djsdlflasdf lsdljsg lsdjf&l fsldjf中文公司刚刚打过dsldj。来打发时间了地方撒ddd.dkdkdkkd";
        System.out.println(splitChineseEnglish(str));
    }

}
