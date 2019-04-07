package com.stude.ioc;

import org.dom4j.*;
import org.dom4j.io.SAXReader;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.StringUtils;

import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

/**
 * Created on 2019/4/7.
 *
 * @author Grak
 * @since 1.0
 */
public class IocBase {

    /**
     * 通过XML配置bean路径，然后反射生成IocBase 的bean
     */
    public void show(){
        System.out.println("明天你好！！");
    }

    /**
     * XML解析方式（Dom4j、Sax、Pull）
     * 1.dom4j不适合大文件的解析，因为它是一下子将文件加载到内存中，所以有可能出现内存溢出
     * 2.sax是基于事件来对xml进行解析的，所以他可以解析大文件的xml
     * （dom4j可以对xml进行灵活的增删改查和导航，而sax没有这么强的灵活性）
     */
    public static void main(String[] args) throws DocumentException {
        //读取xml配置的bean
        ClassPathXmlApplicationContext xml = new ClassPathXmlApplicationContext("iocBaseBean.xml");
        IocBase base = (IocBase)xml.getBean("iocBase");
        base.show();

        //读取自定义xml信息
//        IocBase iocBase = new IocBase();
//        iocBase.saxrXml();

        //动态创建xml文件信息
//        iocBase.dom4jXml();

    }

    /**
     * 创建一个xml
     */
    public void dom4jXml(){
        //1.自己创建Document对象
        Document document = DocumentHelper.createDocument();
        //创建根节点
        Element root = DocumentHelper.createElement("students");
        document.setRootElement(root);
        //root下的子节点
        Element name = root.addElement("姓名");
        Element age = root.addElement("年龄");
        //子节点赋值
        name.setText("大佬牛");
        age.setText("55");
        getNodes(root);
    }

    public void saxrXml() throws DocumentException {
        SAXReader saxReader = new SAXReader();
        Document read = saxReader.read(getResourcesStream("student.xml"));
        // 获取根节点
        Element rootElement = read.getRootElement();
        getNodes(rootElement);
    }

    /**
     * 读取xml的路径，
     * getClass().getClassLoader()获取到的是resource目录
     * @param xmlPath
     * @return
     */
    private InputStream getResourcesStream(String xmlPath){
        return this.getClass().getClassLoader().getResourceAsStream(xmlPath);
    }

    public static void getNodes(Element rootElement) {
        System.out.println("获取当前名称:" + rootElement.getName());
        // 获取属性信息
        List<Attribute> attributes = rootElement.attributes();
        for (Attribute attribute : attributes) {
            System.out.println("属性:" + attribute.getName() + "---" + attribute.getText());
        }
        // 获取属性value
        String value = rootElement.getTextTrim();
        if (!StringUtils.isEmpty(value)) {
            System.out.println("value:" + value);
        }
        // 使用迭代器遍历,继续遍历子节点
        Iterator<Element> elementIterator = rootElement.elementIterator();
        while (elementIterator.hasNext()) {
            Element next = elementIterator.next();
            getNodes(next);
        }
    }
}
