package h11sai;

import java.io.File;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.*;

public class DOMQueryH11SAI {
    public static void main(String[] args) throws Exception {
        Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new File("H11SAIXML.xml"));
        doc.getDocumentElement().normalize();

        System.out.println("a) Szakacsok szakkozepiskolaval:");
        NodeList sz = doc.getElementsByTagName("szakacs");
        for (int i = 0; i < sz.getLength(); i++) {
            Element e = (Element) sz.item(i);
            NodeList v = e.getElementsByTagName("vegzettseg");
            for (int j = 0; j < v.getLength(); j++)
                if (v.item(j).getTextContent().trim().equals("szakkozepiskola"))
                    System.out.println("  " + e.getAttribute("id") + " " + text(e, "nev"));
        }

        System.out.println("b) Otcsillagos ettermek:");
        NodeList et = doc.getElementsByTagName("etterem");
        for (int i = 0; i < et.getLength(); i++) {
            Element e = (Element) et.item(i);
            if (text(e, "csillag").equals("5")) System.out.println("  " + e.getAttribute("id") + " " + text(e, "nev"));
        }

        System.out.println("c) Delutanos gyakornokok:");
        NodeList gy = doc.getElementsByTagName("gyakornok");
        for (int i = 0; i < gy.getLength(); i++) {
            Element e = (Element) gy.item(i);
            if (text(e, "beosztas").equals("delutan")) System.out.println("  " + e.getAttribute("id") + " " + text(e, "nev"));
        }
    }

    static String text(Element e, String tag) {
        return e.getElementsByTagName(tag).item(0).getTextContent().trim();
    }
}
