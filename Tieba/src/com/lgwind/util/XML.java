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
     * ��ȡxml�ĵ�
     * @param filePath
     * @return Document
     * @throws Exception
     */
    private Document getDocumentObject() {
        Document document = null;
        try{
            // 1.��ȡ����
            DocumentBuilderFactory factory = DocumentBuilderFactory
                    .newInstance();
            // 2.����������
            DocumentBuilder builder = factory.newDocumentBuilder();
            // 3.����xml�ĵ����õ������ĵ���document����
            document = builder.parse(new File(xmlPath));
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(document == null){
            System.err.println("\ngetDocumentObject()���������쳣����ȡdocument��ֵʧ��!");
            System.err.println("xmlPath="+xmlPath);
            System.err.println("document="+document+"\n");
        }
        return document;
    }
    
    /**
     * ��ȡxml�ĵ�
     * @return
     */
    public Document getDocument() {
        return document;
    }
    
    /**
     * �޸�xml�ĵ�
     * @param document
     * @param xmlPath
     * @throws Exception
     */
    public void updateDocument() {
        try{
            // ��ȡ����
            TransformerFactory tf = TransformerFactory.newInstance();
            // ��ȡת����
            Transformer ts = tf.newTransformer();
            // ��documentд��book.xml�ĵ�
            ts.transform(new DOMSource(document), new StreamResult(new File(
                    xmlPath)));
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    /**
     * ����Ϊxml���ô��룬����Ϊxml���ô��룬
     * ���ô�����뿪����Ŀʹ�ã����ô���һ��ֻ���ڱ���Ŀ��ʹ�á�
     */
    
    /**
     * ��ȡ��ǩֵ ���ݱ�ǩ��
     * @param tagName ��ǩ��
     * @return int
     */
    public int getInt(String tagName) {
        int num = 0;
        try{
            num = Integer.parseInt(getString(tagName));
        }catch(Exception e){
            e.printStackTrace();
            System.err.println("�ַ���ת����ʧ�ܣ�");
            System.err.println("at class XML method getInt");
        }
        return num;
    }
    
    /**
     * ��ȡ��ǩֵ ���ݱ�ǩ��
     * @param tagName ��ǩ��
     * @return String
     */
    public String getString(String tagName) {
      //���ݱ�ǩ����ȡ�ڵ��б�
        NodeList list=document.getElementsByTagName(tagName);
        if(list.getLength()==1){
            return list.item(0).getTextContent();
        }
        else{
            System.err.println("xml�ļ���û�иñ�ǩ��ñ�ǩ��Ψһ��");
            return "";
        }
    }
    
    /**
     * ��ȡ��ǩ�б� ���ݱ�ǩ��
     * @param tagName ��ǩ��
     * @return List<Floor>
     */
    public List<Node> getList(String tagName) {
        List<Node> list = new ArrayList<Node>();
        //���ݱ�ǩ����ȡ�ڵ��б�
        NodeList nodeList =document.getElementsByTagName(tagName);
        //�����ڵ��б�תΪlist�б�
        for(int i=0; i<nodeList.getLength(); i++){
            list.add(nodeList.item(i));
        }
        return list;
    }
    
    /**
     * ��ȡ��ǩ�б� ���ݱ�ǩ�� �� �����ڵ�
     * @param tagName ��ǩ��
     * @param pnode �����ڵ�
     * @return List<Floor>
     */
    public List<Node> getList(String tagName, Node pnode) {
        List<Node> list = new ArrayList<Node>();
        //���ݱ�ǩ����ȡ�ڵ��б�
        NodeList nodeList = pnode.getChildNodes();
        //�����ڵ��б�תΪlist�б�
        for(int i=0; i<nodeList.getLength(); i++){
            Node node = nodeList.item(i);
            if(node.getNodeName().equals(tagName)){
                list.add(node);
            }
        }
        return list;
    }
    
    /**
     * ��ȡ��ǩֵ ���ݸ����ڵ�ͱ�ǩ����
     * @param tagName ��ǩ��
     * @param pnode �����ڵ�
     * @return
     */
    public int getInt(String tagName, Node pnode) {
        int num = 0;
        try{
            num = Integer.parseInt(getString(tagName, pnode));
        }catch(Exception e){
            e.printStackTrace();
            System.err.println("�ַ���ת����ʧ�ܣ�");
            System.err.println("at class XML method getInt");
        }
        return num;
    }
    
    /**
     * ��ȡ��ǩֵ ���ݸ����ڵ�ͱ�ǩ����
     * @param tagName ��ǩ��
     * @param pnode �����ڵ�
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
     * ɾ����ǩ ���ݱ�ǩ��ɾ����ǩ�б�
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