package dev;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class dev{
    public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException {
        
        File xmlFile = new File("H11SAI.xml");

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        DocumentBuilder dBuilder = factory.newDocumentBuilder();

        Document doc = dBuilder.parse(xmlFile);

        doc.getDocumentElement().normalize();

        System.out.println("Gyökér elem: " + doc.getDocumentElement().getNodeName());

        NodeList nList = doc.getElementsByTagName("etterem");

        for (int i = 0; i < nList.getLength(); i++) {
            Node nNode = nList.item(i);

            if(nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element elem = (Element) nNode;

                String id = elem.getAttribute("ekod");
                Node node1 = elem.getElementsByTagName("nev").item(0);
                String name = node1.getTextContent();
                
                Node node2 = elem.getElementsByTagName("varos").item(0);
                String city = node2.getTextContent();
                
                Node node3 = elem.getElementsByTagName("utca").item(0);
                String street = node3.getTextContent();
                
                Node node4 = elem.getElementsByTagName("hazszam").item(0);
                String number = node4.getTextContent();

                Node node5 = elem.getElementsByTagName("csillag").item(0);
                String stars = node5.getTextContent();

                String adr = city + ", " + street + " utca " + number + ".";

                System.out.println("Étteremn ID: " + id);
                System.out.println("Név "+ name);
                System.out.println("Cím: " + adr);
                System.out.println("Csillag: " + stars);
            }
        }

        NodeList BList = doc.getElementsByTagName("foszakacs");

        
        for (int i = 0; i < nList.getLength(); i++) {
            Node nNode = nList.item(i);

            if(nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element elem = (Element) nNode;

                String id = elem.getAttribute("fkod");
                Node node1 = elem.getElementsByTagName("nev").item(0);
                String name = node1.getTextContent();
                
                Node node2 = elem.getElementsByTagName("eletkor").item(0);
                String age = node2.getTextContent();
                
                Node node3 = elem.getElementsByTagName("vegzettseg").item(0);
                for (int x = 0; x < node3.si; x++) {
                NodeList vegzettsegList = vegzet
                    
                }
                String street = node3.getTextContent();
                
                Node node4 = elem.getElementsByTagName("").item(0);
                String number = node4.getTextContent();

                Node node5 = elem.getElementsByTagName("").item(0);
                String stars = node5.getTextContent();


                System.out.println("ID: " + id);
                System.out.println(""+ name);
                System.out.println("" + stars);
            }
        }
    }
}