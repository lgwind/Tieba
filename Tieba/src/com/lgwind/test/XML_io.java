package com.lgwind.test;
import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XML_io {
    
    private static String filePath = "D:\\LinuxWorkspace\\Tieba\\src\\com\\lgwind\\util\\book.xml";
    /*jaxp����xml�ĵ�*/
    @Test
    public void read() throws Exception{
        //��ȡxml�ĵ���Ԫ��ֵ
        //1.��ȡ����
        DocumentBuilderFactory factory= DocumentBuilderFactory.newInstance();
        //2.����������
        DocumentBuilder builder=factory.newDocumentBuilder();
        //3.����xml�ĵ����õ������ĵ���document����
        Document document=builder.parse(new File(filePath));
        
        //���ݱ�ǩ����ȡ�ڵ��б�
        NodeList list=document.getElementsByTagName("�ۼ�");
        //��ȡ�ڵ��б���ĵڶ����ڵ�
        Node price=list.item(1);
        //��ȡ�ڵ��ֵ
        String value=price.getTextContent();
        System.out.println(value);
    }

    @Test
    public void update() throws Exception{
        //�޸�xml�ļ���ǩ��ֵ
        //1.��ȡ����
        DocumentBuilderFactory factory= DocumentBuilderFactory.newInstance();
        //2.����������
        DocumentBuilder builder=factory.newDocumentBuilder();
        //3.����xml�ĵ����õ������ĵ���document����
        Document document=builder.parse(new File(filePath));

        //��ȡ��Ҫ�޸ĵı�ǩ�ڵ㣬���޸ĺ��ֵ����Ϊ20Ԫ
        Node price=document.getElementsByTagName("�ۼ�").item(0);
        price.setTextContent("20Ԫ");

        //���ڴ����޸ĺ��doucementд��xml�ĵ�
        //��ȡ����
        TransformerFactory tf=TransformerFactory.newInstance();
        //��ȡת����
        Transformer ts=tf.newTransformer();
        //��documentд��book.xml�ĵ�
        ts.transform(new DOMSource(document), new StreamResult(new File("src/xml/book1.xml")));
    }

    @Test
    public void add() throws Exception{
        //��xml�������ӱ�ǩ
        //1.��ȡ����
        DocumentBuilderFactory factory= DocumentBuilderFactory.newInstance();
        //2.����������
        DocumentBuilder builder=factory.newDocumentBuilder();
        //3.����xml�ĵ����õ������ĵ���document����
        Document document=builder.parse(new File(filePath));

        //������Ҫ���ӵĽڵ�
        Node price=document.createElement("�ۼ�");
        price.setTextContent("50Ԫ");
        //�õ���Ҫ���ӽڵ�ĸ���
        Node parent=document.getElementsByTagName("��").item(0);
        //����Ҫ���ӵĽڵ�ҵ����ڵ���
        parent.appendChild(price);
        /*��Ҫ��ӵı�ǩ�ҵ����ڵ��ָ��λ��
         * parent.insertBefore(price, document.getElementsByTagName("����").item(0));
         */

        //���ڴ����޸ĺ��doucementд��xml�ĵ�
        //��ȡ����
        TransformerFactory tf=TransformerFactory.newInstance();
        //��ȡת����
        Transformer ts=tf.newTransformer();
        //��documentд��book.xml�ĵ�
        ts.transform(new DOMSource(document), new StreamResult(new File("src/xml/book1.xml")));
    }

    @Test
    public void delete() throws Exception{
        //ɾ��xml�ĵ��еı�ǩ
        //1.��ȡ����
        DocumentBuilderFactory factory= DocumentBuilderFactory.newInstance();
        //2.����������
        DocumentBuilder builder=factory.newDocumentBuilder();
        //3.����xml�ĵ����õ������ĵ���document����
        Document document=builder.parse(new File(filePath));

        //�õ���Ҫɾ���Ľڵ�
        Node price=document.getElementsByTagName("�ۼ�").item(1);
        //���ڽڵ㲻���Լ�ɾ���Լ�����Ҫ��ȡ�丸�ڵ㣬�����丸�ڵ�ɾ��
        price.getParentNode().removeChild(price);

        //���ڴ����޸ĺ��doucementд��xml�ĵ�
        //��ȡ����
        TransformerFactory tf=TransformerFactory.newInstance();
        //��ȡת����
        Transformer ts=tf.newTransformer();
        //��documentд��book.xml�ĵ�
        ts.transform(new DOMSource(document), new StreamResult(new File("src/xml/book1.xml")));
    }

    @Test
    public void updateAttribute() throws Exception{
        //����xml�ĵ��б�ǩ������ֵ
        //1.��ȡ����
        DocumentBuilderFactory factory= DocumentBuilderFactory.newInstance();
        //2.����������
        DocumentBuilder builder=factory.newDocumentBuilder();
        //3.����xml�ĵ����õ������ĵ���document����
        Document document=builder.parse(new File(filePath));

        //����xml�ĵ���Ԫ��ʱ��һ�㶼��Ԫ�ص���node���󣬵��ǳ���Ա�������node����ʹʱ����Ӧ��nodeǿת����Ӧ����
        //�õ���Ҫ�������Ե�Ԫ��
        Element book=(Element) document.getElementsByTagName("��").item(0);
        book.setAttribute("name", "yyyyyy");
        book.setAttribute("password", "123456");

        //���ڴ����޸ĺ��doucementд��xml�ĵ�
        //��ȡ����
        TransformerFactory tf=TransformerFactory.newInstance();
        //��ȡת����
        Transformer ts=tf.newTransformer();
        //��documentд��book.xml�ĵ�
        ts.transform(new DOMSource(document), new StreamResult(new File("src/xml/book1.xml")));
    }

    public static void  list(Node node){
        //����book.xml�ĵ��е�����Node
        if(node.getNodeType()==Node.ELEMENT_NODE){
            //�ж�node�Ƿ���Node����
            System.out.println(node.getNodeName());
            //System.out.println(node.getTextContent());
        }
        NodeList lis=node.getChildNodes();
        for(int i=0;i<lis.getLength();i++){
            list(lis.item(i));
        }

    }

    public static void main(String args[]) throws Exception{
        //1.��ȡ����
        DocumentBuilderFactory factory= DocumentBuilderFactory.newInstance();
        //2.����������
        DocumentBuilder builder=factory.newDocumentBuilder();
        //3.����xml�ĵ����õ������ĵ���document����
        Document document=builder.parse(new File(filePath));
        //����book.xml�ĵ������нڵ�
        list(document);
    }

}