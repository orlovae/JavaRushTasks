package com.javarush.task.task33.task3309;

import org.w3c.dom.*;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

/*
Комментарий внутри xml
*/
public class Solution {
    public static String toXmlWithComment(Object obj, String tagName, String comment) {

        StringWriter writer = new StringWriter();
        JAXBContext context = null;
        try {
            context = JAXBContext.newInstance(obj.getClass());
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(obj, writer);
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        Document document = parseDocument(writer.toString());

        walkToDocument(document.getDocumentElement(), tagName, comment, document);

        StreamResult streamResult = new StreamResult(new ByteArrayOutputStream());
        DOMSource source = new DOMSource(document);
        Transformer transformer = getTransformer();
        try {
            transformer.transform(source, streamResult);
        } catch (TransformerException e) {
            e.printStackTrace();
        }

        return streamResult.getOutputStream().toString();
    }

    private static Transformer getTransformer() {
        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer transformer = null;
        try {
            transformer = factory.newTransformer();
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.setOutputProperty(OutputKeys.STANDALONE, "no");
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        }
        return transformer;
    }

    private static Document parseDocument(String fromXML) {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setCoalescing(true);
        DocumentBuilder db;
        Document document = null;
        try {
            db = dbf.newDocumentBuilder();
            document = db.parse(new InputSource(new StringReader(fromXML)));
            document.setXmlStandalone(true);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return document;
    }

    private static void walkToDocument(Node node, String tagName,
                                       String comment, Document document){
        if (node == null) {
            return;
        }

        String nodeValue = node.getNodeValue();
        Node parent = node.getParentNode();

        if (nodeValue != null &&
                node.getNodeType() == Node.TEXT_NODE &&
                node.getTextContent().matches(".*[<>&'\"].*")) {
            Node nextSibling = node.getNextSibling();
            CDATASection cdataSection = document.createCDATASection(nodeValue);
            parent.insertBefore(cdataSection, nextSibling);
            parent.removeChild(node);
            node = cdataSection;
        } else {
            if (node.getNodeName().equals(tagName)) {
                Comment comment1 = document.createComment(comment);
                node.getParentNode().insertBefore(comment1, node);
            }
        }

        walkToDocument(node.getFirstChild(), tagName, comment, document);
        walkToDocument(node.getNextSibling(), tagName, comment, document);
    }

    @XmlRootElement(name = "first")
    public static class First {
        @XmlElement(name = "second")
        public String item1 = "some string";
        @XmlElement(name = "second")
        public String item2 = "need CDATA because of <second>";
        @XmlElement(name = "second")
        public String item3 = "";
        @XmlElement(name = "third")
        public String item4;
        @XmlElement(name = "forth")
        public Second[] third = new Second[]{new Second()};
        @XmlElement(name = "fifth")
        public String item5 = "need CDATA because of \"";
    }

    public static class Second {
        @XmlElement(name = "second")
        public String item1 = "some string";
        @XmlElement(name = "second")
        public String item2 = "need CDATA because of <second>";
    }

    public static void main(String[] args) {
        System.out.println(Solution.toXmlWithComment(new First(), "second", "it's a comment"));
    }
}
