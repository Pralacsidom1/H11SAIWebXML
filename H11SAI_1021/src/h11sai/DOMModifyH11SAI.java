package h11sai;

import java.io.File;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.*;

public class DOMModifyH11SAI {
    public static void main(String[] args) throws Exception {
        Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new File("H11SAIXML.xml"));
        doc.getDocumentElement().normalize();

        // a) vendeg v1 nev + kor
        NodeList vendegek = doc.getElementsByTagName("vendeg");
        for (int i = 0; i < vendegek.getLength(); i++) {
            Element v = (Element) vendegek.item(i);
            if (v.getAttribute("id").equals("v1")) {
                v.getElementsByTagName("nev").item(0).setTextContent("Kovacs Tamas");
                v.getElementsByTagName("kor").item(0).setTextContent("21");
            }
        }
        // b) gyakornok e_gy -> e3
        NodeList gyak = doc.getElementsByTagName("gyakornok");
        for (int i = 0; i < gyak.getLength(); i++) ((Element) gyak.item(i)).setAttribute("e_gy", "e3");

        // c) all rendeles removed (live list: remove from the end)
        NodeList r = doc.getElementsByTagName("rendeles");
        for (int i = r.getLength() - 1; i >= 0; i--) r.item(i).getParentNode().removeChild(r.item(i));

        // drop whitespace-only text nodes so INDENT output has no blank lines
        NodeList all = doc.getElementsByTagName("*");
        for (int i = 0; i < all.getLength(); i++)
            for (Node n = all.item(i).getFirstChild(), nx; n != null; n = nx) {
                nx = n.getNextSibling();
                if (n.getNodeType() == Node.TEXT_NODE && n.getTextContent().isBlank()) n.getParentNode().removeChild(n);
            }

        Transformer t = TransformerFactory.newInstance().newTransformer();
        t.setOutputProperty(OutputKeys.INDENT, "yes");
        t.transform(new DOMSource(doc), new StreamResult(System.out));
    }
}
