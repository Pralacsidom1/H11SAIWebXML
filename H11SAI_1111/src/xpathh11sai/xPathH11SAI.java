package xpathh11sai;

import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.io.BufferedWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.xpath.*;

import org.xml.sax.SAXException;

public class xPathH11SAI{
    public static void main(String[] args) {
        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

            Document document = documentBuilder.parse("studentH11SAI.xml");
            document.getDocumentElement().normalize();

            XPath xPath = XPathFactory.newInstance().newXPath();

            String neptuKod = "/class/student";

            System.out.println("\n Futás eredménye:");

            NodeList neptunKod = (NodeList) xPath.compile(neptuKod).evaluate(document, XPathConstants.NODESET);

            BufferedWriter writer = new BufferedWriter(new FileWriter("student1H11SAI.xml"));

            writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
            writer.write("<class>\n");

            for (int i = 0; i < neptunKod.getLength(); i++) {
                Node node = neptunKod.item(i);
                System.out.println("\naktuális elem: " + node.getNodeName());

                if (node.getNodeType() == Node.ELEMENT_NODE && node.getNodeName().equals("student")) {
                    Element element = (Element) node;

                    String id = element.getAttribute("id");
                    String keresztnev = element.getElementsByTagName("keresztnev").item(0).getTextContent();
                    String vezeteknev= element.getElementsByTagName("vezeteknev").item(0).getTextContent();
                    String becenev = element.getElementsByTagName("becenev").item(0).getTextContent();
                    String kor = element.getElementsByTagName("kor").item(0).getTextContent();

                    System.out.println("\nHallgato id: \n"+ id);
                    System.out.println("\nHallgato keresztnev\n"+ keresztnev);
                    System.out.println("\nHallgato vezeteknev\n"+ vezeteknev);
                    System.out.println("\nHallgato becenev\n"+ becenev);
                    System.out.println("\nHallgato eletkor\n"+ kor);

                    writer.write("  <student id=\"" + id + "\">\n");
                    writer.write("    <keresztnev>" + keresztnev + "</keresztnev>\n");
                    writer.write("    <vezeteknev>" + vezeteknev + "</vezeteknev>\n");
                    writer.write("    <becenev>" + becenev + "</becenev>\n");
                    writer.write("    <kor>" + kor + "</kor>\n");
                    writer.write("  </student>\n");
                }
            }
            writer.write("</class>\n");
            writer.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
