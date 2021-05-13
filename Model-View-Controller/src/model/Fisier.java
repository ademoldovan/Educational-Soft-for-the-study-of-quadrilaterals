package model;

import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.ArrayList;

public class Fisier {

    private final String pathname = "C:\\Users\\adeli\\Desktop\\PS\\PROIECT\\Tema 2\\MVC\\patrulater.xml";

    public void scriere(Patrulater p){
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();
            // root element
            Element rootElement = doc.createElement("Patrulater");
            doc.appendChild(rootElement);

            // supercars element
            Element supercar = doc.createElement("Punct");
            rootElement.appendChild(supercar);
            // carname element
            Element carname = doc.createElement("X");
            Attr attrType = doc.createAttribute("type");
            attrType.setValue("double");
            carname.setAttributeNode(attrType);
            carname.appendChild(doc.createTextNode(Double.toString(p.getA().getX())));
            supercar.appendChild(carname);

            Element carname1 = doc.createElement("Y");
            Attr attrType1 = doc.createAttribute("type");
            attrType1.setValue("double");
            carname1.setAttributeNode(attrType1);
            carname1.appendChild(doc.createTextNode(Double.toString(p.getA().getY())));
            supercar.appendChild(carname1);

            Element supercar1 = doc.createElement("Punct");
            rootElement.appendChild(supercar1);

            Element carname2 = doc.createElement("X");
            Attr attrType2 = doc.createAttribute("type");
            attrType2.setValue("double");
            carname2.setAttributeNode(attrType2);
            carname2.appendChild(doc.createTextNode(Double.toString(p.getB().getX())));
            supercar1.appendChild(carname2);

            Element carname3 = doc.createElement("Y");
            Attr attrType3 = doc.createAttribute("type");
            attrType3.setValue("double");
            carname3.setAttributeNode(attrType3);
            carname3.appendChild(doc.createTextNode(Double.toString(p.getB().getY())));
            supercar1.appendChild(carname3);

            Element supercar2 = doc.createElement("Punct");
            rootElement.appendChild(supercar2);

            Element carname4 = doc.createElement("X");
            Attr attrType4 = doc.createAttribute("type");
            attrType4.setValue("double");
            carname4.setAttributeNode(attrType4);
            carname4.appendChild(doc.createTextNode(Double.toString(p.getC().getX())));
            supercar2.appendChild(carname4);

            Element carname5 = doc.createElement("Y");
            Attr attrType5 = doc.createAttribute("type");
            attrType5.setValue("double");
            carname5.setAttributeNode(attrType5);
            carname5.appendChild(doc.createTextNode(Double.toString(p.getC().getY())));
            supercar2.appendChild(carname5);

            Element supercar3 = doc.createElement("Punct");
            rootElement.appendChild(supercar3);

            Element carname6 = doc.createElement("X");
            Attr attrType6 = doc.createAttribute("type");
            attrType6.setValue("double");
            carname6.setAttributeNode(attrType6);
            carname6.appendChild(doc.createTextNode(Double.toString(p.getD().getX())));
            supercar3.appendChild(carname6);

            Element carname7 = doc.createElement("Y");
            Attr attrType7 = doc.createAttribute("type");
            attrType7.setValue("double");
            carname7.setAttributeNode(attrType7);
            carname7.appendChild(doc.createTextNode(Double.toString(p.getD().getY())));
            supercar3.appendChild(carname7);

            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(pathname));
            transformer.transform(source, result);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Double> citire(){
        ArrayList<Double> coord = new ArrayList<>();
        try
        {
            File file = new File(pathname);
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(file);
            doc.getDocumentElement().normalize();
            NodeList nodeList = doc.getElementsByTagName("Punct");
            for (int itr = 0; itr < nodeList.getLength(); itr++)
            {
                Node node = nodeList.item(itr);
                if (node.getNodeType() == Node.ELEMENT_NODE)
                {
                    Element eElement = (Element) node;
                    coord.add(Double.parseDouble(eElement.getElementsByTagName("X").item(0).getTextContent()));
                    coord.add(Double.parseDouble(eElement.getElementsByTagName("Y").item(0).getTextContent()));
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return coord;
    }


}
