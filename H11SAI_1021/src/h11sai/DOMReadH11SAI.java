package h11sai;

import java.io.File;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.*;

public class DOMReadH11SAI {
    public static void main(String[] args) throws Exception {
        read(args.length > 0 ? args[0] : "H11SAIXML.xml");
    }

    static void read(String file) throws Exception {
        Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new File(file));
        doc.getDocumentElement().normalize();
        Element root = doc.getDocumentElement();
        System.out.println("Root element: " + root.getNodeName());
        for (Node n = root.getFirstChild(); n != null; n = n.getNextSibling())
            if (n instanceof Element e) print(e, "");
    }

    // block format: attributes, then leaf children as "tag: text", nested elements recursed
    static void print(Element e, String ind) {
        System.out.println(ind + "Current Element: " + e.getNodeName());
        NamedNodeMap at = e.getAttributes();
        for (int i = 0; i < at.getLength(); i++)
            System.out.println(ind + e.getNodeName() + " " + at.item(i).getNodeName() + ": " + at.item(i).getNodeValue());
        for (Node n = e.getFirstChild(); n != null; n = n.getNextSibling()) {
            if (!(n instanceof Element c)) continue;
            boolean nested = false;
            for (Node k = c.getFirstChild(); k != null; k = k.getNextSibling()) nested |= k instanceof Element;
            if (nested) print(c, ind + "  ");
            else System.out.println(ind + c.getNodeName() + ": " + c.getTextContent().trim());
        }
        System.out.println();
    }
}
