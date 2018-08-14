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
    /*jaxp解析xml文档*/
    @Test
    public void read() throws Exception{
        //读取xml文档的元素值
        //1.获取工厂
        DocumentBuilderFactory factory= DocumentBuilderFactory.newInstance();
        //2.产生解析器
        DocumentBuilder builder=factory.newDocumentBuilder();
        //3.解析xml文档，得到代表文档的document对象
        Document document=builder.parse(new File(filePath));
        
        //根据标签名获取节点列表
        NodeList list=document.getElementsByTagName("售价");
        //获取节点列表里的第二个节点
        Node price=list.item(1);
        //获取节点的值
        String value=price.getTextContent();
        System.out.println(value);
    }

    @Test
    public void update() throws Exception{
        //修改xml文件标签的值
        //1.获取工厂
        DocumentBuilderFactory factory= DocumentBuilderFactory.newInstance();
        //2.产生解析器
        DocumentBuilder builder=factory.newDocumentBuilder();
        //3.解析xml文档，得到代表文档的document对象
        Document document=builder.parse(new File(filePath));

        //获取需要修改的标签节点，将修改后的值设置为20元
        Node price=document.getElementsByTagName("售价").item(0);
        price.setTextContent("20元");

        //将内存中修改后的doucement写回xml文档
        //获取工厂
        TransformerFactory tf=TransformerFactory.newInstance();
        //获取转换器
        Transformer ts=tf.newTransformer();
        //将document写回book.xml文当
        ts.transform(new DOMSource(document), new StreamResult(new File("src/xml/book1.xml")));
    }

    @Test
    public void add() throws Exception{
        //向xml当中增加标签
        //1.获取工厂
        DocumentBuilderFactory factory= DocumentBuilderFactory.newInstance();
        //2.产生解析器
        DocumentBuilder builder=factory.newDocumentBuilder();
        //3.解析xml文档，得到代表文档的document对象
        Document document=builder.parse(new File(filePath));

        //创建需要增加的节点
        Node price=document.createElement("售价");
        price.setTextContent("50元");
        //得到需要增加节点的父亲
        Node parent=document.getElementsByTagName("书").item(0);
        //把需要增加的节点挂到父节点上
        parent.appendChild(price);
        /*把要添加的标签挂到父节点的指定位置
         * parent.insertBefore(price, document.getElementsByTagName("书名").item(0));
         */

        //将内存中修改后的doucement写回xml文档
        //获取工厂
        TransformerFactory tf=TransformerFactory.newInstance();
        //获取转换器
        Transformer ts=tf.newTransformer();
        //将document写回book.xml文当
        ts.transform(new DOMSource(document), new StreamResult(new File("src/xml/book1.xml")));
    }

    @Test
    public void delete() throws Exception{
        //删除xml文当中的标签
        //1.获取工厂
        DocumentBuilderFactory factory= DocumentBuilderFactory.newInstance();
        //2.产生解析器
        DocumentBuilder builder=factory.newDocumentBuilder();
        //3.解析xml文档，得到代表文档的document对象
        Document document=builder.parse(new File(filePath));

        //得到需要删除的节点
        Node price=document.getElementsByTagName("售价").item(1);
        //由于节点不能自己删除自己，需要获取其父节点，根据其父节点删除
        price.getParentNode().removeChild(price);

        //将内存中修改后的doucement写回xml文档
        //获取工厂
        TransformerFactory tf=TransformerFactory.newInstance();
        //获取转换器
        Transformer ts=tf.newTransformer();
        //将document写回book.xml文当
        ts.transform(new DOMSource(document), new StreamResult(new File("src/xml/book1.xml")));
    }

    @Test
    public void updateAttribute() throws Exception{
        //更改xml文当中标签的属性值
        //1.获取工厂
        DocumentBuilderFactory factory= DocumentBuilderFactory.newInstance();
        //2.产生解析器
        DocumentBuilder builder=factory.newDocumentBuilder();
        //3.解析xml文档，得到代表文档的document对象
        Document document=builder.parse(new File(filePath));

        //操作xml文档的元素时，一般都把元素当作node对象，但是程序员如果发现node不好使时，就应把node强转成相应类型
        //得到需要更改属性的元素
        Element book=(Element) document.getElementsByTagName("书").item(0);
        book.setAttribute("name", "yyyyyy");
        book.setAttribute("password", "123456");

        //将内存中修改后的doucement写回xml文档
        //获取工厂
        TransformerFactory tf=TransformerFactory.newInstance();
        //获取转换器
        Transformer ts=tf.newTransformer();
        //将document写回book.xml文当
        ts.transform(new DOMSource(document), new StreamResult(new File("src/xml/book1.xml")));
    }

    public static void  list(Node node){
        //遍历book.xml文档中的所有Node
        if(node.getNodeType()==Node.ELEMENT_NODE){
            //判断node是否是Node类型
            System.out.println(node.getNodeName());
            //System.out.println(node.getTextContent());
        }
        NodeList lis=node.getChildNodes();
        for(int i=0;i<lis.getLength();i++){
            list(lis.item(i));
        }

    }

    public static void main(String args[]) throws Exception{
        //1.获取工厂
        DocumentBuilderFactory factory= DocumentBuilderFactory.newInstance();
        //2.产生解析器
        DocumentBuilder builder=factory.newDocumentBuilder();
        //3.解析xml文档，得到代表文档的document对象
        Document document=builder.parse(new File(filePath));
        //遍历book.xml文档的所有节点
        list(document);
    }

}