package Import_XML_Data;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class First_load {
	
	public static void main(String argv[]) {
		List<String> ConnectionsFROMFIELD = new ArrayList<String>();
		List<String> ConnectionsFROMINSTANCE = new ArrayList<String>();
		List<String> ConnectionsFROMINSTANCETYPE = new ArrayList<String>();
		List<String> ConnectionsTOFIELD = new ArrayList<String>();
		List<String> ConnectionsTOINSTANCE = new ArrayList<String>();
		List<String> ConnectionsTOINSTANCETYPE = new ArrayList<String>();
		int i = 0;
		try {
		File fXmlFile = new File("C:/ETL_WORFLOWS/REINS/wkf_RE_INSURANCE_LIFEJ.XML");
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(fXmlFile);
		//optional, but recommended
		//read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
		doc.getDocumentElement().normalize();

		System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

		NodeList nList = doc.getElementsByTagName("CONNECTOR");

		System.out.println("----------------------------");

	
		for (int temp = 0; temp < nList.getLength(); temp++) {
			i = i+1;
			Node nNode = nList.item(temp);

			//System.out.println("\nCurrent Element :" + nNode.getNodeName());
			
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {

				Element eElement = (Element) nNode;
				System.out.println(i + "FROMFIELD : "        + eElement.getAttribute("FROMFIELD"));
				System.out.println(i + "FROMINSTANCE : "     + eElement.getAttribute("FROMINSTANCE"));
				System.out.println(i + "FROMINSTANCETYPE : " + eElement.getAttribute("FROMINSTANCETYPE"));
				System.out.println(i + "TOFIELD : "          + eElement.getAttribute("TOFIELD"));
				System.out.println(i + "TOINSTANCE : "       + eElement.getAttribute("TOINSTANCE"));
				System.out.println(i + "TOINSTANCETYPE : "   + eElement.getAttribute("TOINSTANCETYPE"));
				System.out.println("next set");
				ConnectionsFROMFIELD.add(eElement.getAttribute("FROMFIELD"));
				ConnectionsFROMINSTANCE.add(eElement.getAttribute("FROMINSTANCE"));
				ConnectionsFROMINSTANCETYPE.add(eElement.getAttribute("FROMINSTANCETYPE"));
				ConnectionsTOFIELD.add(eElement.getAttribute("TOFIELD"));
				ConnectionsTOINSTANCE.add(eElement.getAttribute("TOINSTANCE"));
				ConnectionsTOINSTANCETYPE.add(eElement.getAttribute("TOINSTANCETYPE"));

			}
		}
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	}

}
