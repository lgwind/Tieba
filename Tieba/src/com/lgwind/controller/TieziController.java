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
 * lgwind�ļ��ӿ�ʵ����
 * @author Lgwind
 *
 */
@Controller
@RequestMapping("/tiezi")
public class TieziController {
    
    /**
     * ��ȡ��������
     * @param request
     * @param lgwind ���Ӵʣ����ڽ��ajax��������
     * @param rearch ��ѯ�Ĺؼ���
     * @param before �ڹؼ���֮ǰ��ӵ��ַ���
     * @param last �ڹؼ���֮����ӵ��ַ���
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
     * ��ȡ��������
     * @param request
     * @param lgwind ���Ӵʣ����ڽ��ajax��������
     * @param rearch ��ѯ�Ĺؼ���
     * @param before �ڹؼ���֮ǰ��ӵ��ַ���
     * @param last �ڹؼ���֮����ӵ��ַ���
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "get",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String get( HttpServletRequest request, String filename, String lgwind)
            throws Exception {
        Tiezi tiezi = new Tiezi();
        if(filename == null){
//            filename = "F:\\ѧϰ�ļ�\\��󼪰�\\�������ĵ�\\tiezi2.xml";
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
