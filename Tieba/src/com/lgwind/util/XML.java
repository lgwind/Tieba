package com.lgwind.util;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XML {
    
    private String xmlPath;
    private Document document;
    
    public XML(String xmlPath) {
        super();
        this.xmlPath = xmlPath;
        document=getDocumentObject();
        // TODO Auto-generated constructor stub
    }

    /**
     * 获取xml文档
     * @param filePath
     * @return Document
     * @throws Exception
     */
    private Document getDocumentObject() {
        Document document = null;
        try{
            // 1.获取工厂
            DocumentBuilderFactory factory = DocumentBuilderFactory
                    .newInstance();
            // 2.产生解析器
            DocumentBuilder builder = factory.newDocumentBuilder();
            // 3.解析xml文档，得到代表文档的document对象
            document = builder.parse(new File(xmlPath));
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(document == null){
            System.err.println("\ngetDocumentObject()方法出现异常，获取document的值失败!");
            System.err.println("xmlPath="+xmlPath);
            System.err.println("document="+document+"\n");
        }
        return document;
    }
    
    /**
     * 获取xml文档
     * @return
     */
    public Document getDocument() {
        return document;
    }
    
    /**
     * 修改xml文档
     * @param document
     * @param xmlPath
     * @throws Exception
     */
    public void updateDocument() {
        try{
            // 获取工厂
            TransformerFactory tf = TransformerFactory.newInstance();
            // 获取转换器
            Transformer ts = tf.newTransformer();
            // 将document写回book.xml文当
            ts.transform(new DOMSource(document), new StreamResult(new File(
                    xmlPath)));
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    /**
     * 上述为xml泛用代码，下面为xml特用代码，
     * 泛用代码可离开本项目使用，特用代码一般只能在本项目中使用。
     */
    
    /**
     * 获取标签值 根据标签名
     * @param tagName 标签名
     * @return int
     */
    public int getInt(String tagName) {
        int num = 0;
        try{
            num = Integer.parseInt(getString(tagName));
        }catch(Exception e){
            e.printStackTrace();
            System.err.println("字符串转整型失败！");
            System.err.println("at class XML method getInt");
        }
        return num;
    }
    
    /**
     * 获取标签值 根据标签名
     * @param tagName 标签名
     * @return String
     */
    public String getString(String tagName) {
      //根据标签名获取节点列表
        NodeList list=document.getElementsByTagName(tagName);
        if(list.getLength()==1){
            return list.item(0).getTextContent();
        }
        else{
            System.err.println("xml文件中没有该标签或该标签不唯一！");
            return "";
        }
    }
    
    /**
     * 获取标签列表 根据标签名
     * @param tagName 标签名
     * @return List<Floor>
     */
    public List<Node> getList(String tagName) {
        List<Node> list = new ArrayList<Node>();
        //根据标签名获取节点列表
        NodeList nodeList =document.getElementsByTagName(tagName);
        //遍历节点列表转为list列表
        for(int i=0; i<nodeList.getLength(); i++){
            list.add(nodeList.item(i));
        }
        return list;
    }
    
    /**
     * 获取标签列表 根据标签名 和 父级节点
     * @param tagName 标签名
     * @param pnode 父级节点
     * @return List<Floor>
     */
    public List<Node> getList(String tagName, Node pnode) {
        List<Node> list = new ArrayList<Node>();
        //根据标签名获取节点列表
        NodeList nodeList = pnode.getChildNodes();
        //遍历节点列表转为list列表
        for(int i=0; i<nodeList.getLength(); i++){
            Node node = nodeList.item(i);
            if(node.getNodeName().equals(tagName)){
                list.add(node);
            }
        }
        return list;
    }
    
    /**
     * 获取标签值 根据父级节点和标签名称
     * @param tagName 标签名
     * @param pnode 父级节点
     * @return
     */
    public int getInt(String tagName, Node pnode) {
        int num = 0;
        try{
            num = Integer.parseInt(getString(tagName, pnode));
        }catch(Exception e){
            e.printStackTrace();
            System.err.println("字符串转整型失败！");
            System.err.println("at class XML method getInt");
        }
        return num;
    }
    
    /**
     * 获取标签值 根据父级节点和标签名称
     * @param tagName 标签名
     * @param pnode 父级节点
     * @return
     */
    public String getString(String tagName, Node pnode) {
        NodeList nodeList = pnode.getChildNodes();
        for(int i=0; i<nodeList.getLength(); i++){
            Node node = nodeList.item(i);
            if(tagName.endsWith(node.getNodeName())){
                return node.getTextContent();
            }
        }
        return "";
    }
    
    /**
     * 删除标签 根据标签名删除标签列表
     * @param tagName
     */
    public void deleteTags(String tagName) {
        NodeList list=document.getElementsByTagName(tagName);
        for(int i=0; i<list.getLength(); i++) {
            list.item(i).getParentNode().removeChild(list.item(i));
        }
        updateDocument();
    }

}