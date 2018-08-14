package com.lgwind.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;




import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lgwind.entity.Tiezi;
import com.lgwind.util.Conf;
import com.lgwind.util.TieziXML;

/**
 * lgwind文件接口实体类
 * @author Lgwind
 *
 */
@Controller
@RequestMapping("/tiezi")
public class TieziController {
    
    /**
     * 获取所有帖子
     * @param request
     * @param lgwind 连接词，用于解决ajax跨域问题
     * @param rearch 查询的关键词
     * @param before 在关键词之前添加的字符串
     * @param last 在关键词之后添加的字符串
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "getAll",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String getAll( HttpServletRequest request, String search, String before, String last, String lgwind)
            throws Exception {
        List<Tiezi> list = new ArrayList<Tiezi>();
        if(search == null){
            list = TieziXML.getTiezis(Conf.defaultPath);
        }else{
            search=new String(search.getBytes("ISO-8859-1"),"utf-8");
            list = TieziXML.getTiezis(Conf.defaultPath, search, before, last);
        }
        
        if(lgwind==null){
            return list.toString();
        }
        return lgwind+"("+list+")";
    }
    
    /**
     * 获取所有帖子
     * @param request
     * @param lgwind 连接词，用于解决ajax跨域问题
     * @param rearch 查询的关键词
     * @param before 在关键词之前添加的字符串
     * @param last 在关键词之后添加的字符串
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "get",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String get( HttpServletRequest request, String filename, String lgwind)
            throws Exception {
        Tiezi tiezi = new Tiezi();
        if(filename == null){
//            filename = "F:\\学习文件\\武大吉奥\\问题解决文档\\tiezi2.xml";
//            tiezi = TieziXML.getTiezi(filename);
        }else{
            filename=new String(filename.getBytes("ISO-8859-1"),"utf-8");
            tiezi = TieziXML.getTiezi(filename);
        }
        
        if(lgwind==null){
            return tiezi.toString();
        }
        return lgwind+"("+tiezi+")";
    }
    
}
