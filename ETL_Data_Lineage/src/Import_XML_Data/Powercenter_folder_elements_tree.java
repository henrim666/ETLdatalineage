package Import_XML_Data;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Powercenter_folder_elements_tree {

	private static final char DEFAULT_SEPARATOR = ',';
	
	public static void main(String argv[]) {
		List<String> ConnectionsFROMFIELD = 			new ArrayList<String>();
		List<String> ConnectionsFROMINSTANCE = 			new ArrayList<String>();
		List<String> ConnectionsFROMINSTANCETYPE = 		new ArrayList<String>();
		List<String> ConnectionsTOFIELD = 				new ArrayList<String>();
		List<String> ConnectionsTOINSTANCE = 			new ArrayList<String>();
		List<String> ConnectionsTOINSTANCETYPE = 		new ArrayList<String>();

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Datatypes in Java");
        
        //XSSFWorkbook workbook = new XSSFWorkbook();
        //name of excell sheet exceed 31 characters
        //XSSFSheet sheet = workbook.createSheet("Datatypes in Java");
        
		String beginValueConnection = "Source Definition";
		boolean connection_found = false;
		boolean all_not_connected = false;
		
		int xml_left_offset = 2;
		int xml_top_offset = 3;
		
		try {
			//File fXmlFile = new File("C:/ETL_WORFLOWS/REINS/wkf_RE_INSURANCE_LIFEJ.XML");
			File fXmlFile = new File("C:/ETL_WORFLOWS/AlCHEMY/wkf_Alchemy.XML");
			DocumentBuilderFactory dbFactory 	= DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder 			= dbFactory.newDocumentBuilder();
			Document doc 						= dBuilder.parse(fXmlFile);

			//optional, but recommended
			//read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
			doc.getDocumentElement().normalize();

			System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
			

			//NodeList nList = doc.getElementsByTagName("CONNECTOR");
			//<REPOSITORY>
			//<FOLDER>
			//<MAPPING>
			//<CONNECTOR>
			NodeList nList = doc.getElementsByTagName("REPOSITORY");
			System.out.println("----------------------------");
			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);
				System.out.println("\nCurrent Element :" + nNode.getNodeName());
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					System.out.println("\nCurrent Element :" + eElement.getAttribute("NAME"));
				}
			}
			nList = doc.getElementsByTagName("FOLDER");
			System.out.println("----------------------------");

			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);
				System.out.println("\nCurrent Element :" + nNode.getNodeName());
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;					
					System.out.println("\nCurrent Element :" + eElement.getAttribute("NAME"));
				}
			}
			nList = doc.getElementsByTagName("WORKFLOW");
			System.out.println("----------------------------");

			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);
				System.out.println("\nCurrent Element :" + nNode.getNodeName());
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;					
					System.out.println("\nCurrent Element :" + eElement.getAttribute("NAME"));
				}
			}					
			nList = doc.getElementsByTagName("SESSION");
			System.out.println("----------------------------");

			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);
				System.out.println("\nCurrent Element :" + nNode.getNodeName());
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;					
					System.out.println("\nCurrent Element :" + eElement.getAttribute("NAME"));
					System.out.println("\nCurrent Element :" + eElement.getAttribute("MAPPINGNAME"));
				}
			}
			
			//WORKFLOW Parameter Filename
			nList = doc.getElementsByTagName("ATTRIBUTE");
			System.out.println("----------------------------");
			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);
				//System.out.println("\nCurrent Element :" + nNode.getNodeName());
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;	
					Element mElement = (Element) nNode.getParentNode();	
					if("Parameter Filename".equals(eElement.getAttribute("NAME"))&&("WORKFLOW".equals(nNode.getParentNode().getNodeName()))){
						System.out.println("Mapping Name :"  + nNode.getParentNode().getNodeName() + " : " + mElement.getAttribute("NAME"));
						System.out.println("Current Element :" + eElement.getAttribute("NAME"));
						System.out.println("INSTANCE DBDNAME :" + eElement.getAttribute("VALUE"));
					}
				}
			}			
			
			
			//SOURCE
			
			nList = doc.getElementsByTagName("INSTANCE");
			System.out.println("----------------------------");

			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);
				//System.out.println("\nCurrent Element :" + nNode.getNodeName());
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;	
					Element mElement = (Element) nNode.getParentNode();	
					if("Target Definition".equals(eElement.getAttribute("TRANSFORMATION_TYPE"))){
						System.out.println("Mapping Name :"  + nNode.getParentNode().getNodeName() + " : " + mElement.getAttribute("NAME"));
						String dataBaseName = eElement.getAttribute("DBDNAME");
						if ("".equals(dataBaseName)){
							dataBaseName = "Oracle";
						}
						System.out.println("Target DBDNAME :" + dataBaseName);
						System.out.println("Target TRANSFORMATION_TYPE :" + eElement.getAttribute("TRANSFORMATION_NAME"));
						System.out.println("Target Table :" + eElement.getAttribute("NAME"));
					}
					if("Source Definition".equals(eElement.getAttribute("TRANSFORMATION_TYPE"))){
						System.out.println("Mapping Name :"  + nNode.getParentNode().getNodeName() + " : " + mElement.getAttribute("NAME"));
						String dataBaseName = eElement.getAttribute("DBDNAME");
						if ("".equals(dataBaseName)){
							dataBaseName = "Oracle";
						}
						System.out.println("Source DBDNAME :" +dataBaseName);
						System.out.println("Source TRANSFORMATION_TYPE :" + eElement.getAttribute("TRANSFORMATION_NAME"));
						System.out.println("Source Table :" + eElement.getAttribute("NAME"));
					}

				}
			}

			//CONNECTIONREFERENCE
			//
			nList = doc.getElementsByTagName("CONNECTIONREFERENCE");
			System.out.println("----------------------------");
			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);
				//System.out.println("\nCurrent Element :" + nNode.getNodeName());
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;	
					Element mElement = (Element) nNode.getParentNode();	
					Element mmElement = (Element) nNode.getParentNode().getParentNode();	
					Element mmmElement = (Element) nNode.getParentNode().getParentNode().getParentNode();	
					//if("Parameter Filename".equals(eElement.getAttribute("NAME"))&&("WORKFLOW".equals(nNode.getParentNode().getNodeName()))){
					    System.out.println("Mapping Name :"  + nNode.getParentNode().getNodeName() + " : " + mElement.getAttribute("NAME") + " : " +  mmElement.getAttribute("NAME")+ " : " +  mmmElement.getAttribute("NAME") );
						System.out.println("Type connection :" + eElement.getAttribute("CNXREFNAME"));
						System.out.println("VARIABLE connection :" + eElement.getAttribute("VARIABLE"));
					//}
				}
			}	
			
			
			
			
			
			
			
			//**************************************************
			//nList = doc.getElementsByTagName("CONNECTOR");
			//System.out.println("----------------------------");
//
			//for (int temp = 0; temp < nList.getLength(); temp++) {
			//	Node nNode = nList.item(temp);
			//	System.out.println("\nCurrent Element :" + nNode.getNodeName());
			//	if (nNode.getNodeType() == Node.ELEMENT_NODE) {
			//		Element eElement = (Element) nNode;			
			//		Element mElement = (Element) nNode.getParentNode();	
					//Element mmElement = (Element) nNode.getParentNode().getParentNode();	
					//System.out.println("\nMother Element Name :" + mmElement.getAttribute("NAME"));
					//System.out.println("\nMother Element :"  + nNode.getParentNode().getNodeName() + " : " + mElement.getAttribute("NAME"));
					//System.out.println("\nCurrent Element FromField :" + eElement.getAttribute("FROMFIELD"));
					//System.out.println("\nCurrent Element ToField :" + eElement.getAttribute("TOFIELD"));
			//	}
			//}					
			
			
					
//					ConnectionsFROMFIELD.add(eElement.getAttribute("FROMFIELD"));
//					ConnectionsFROMINSTANCE.add(eElement.getAttribute("FROMINSTANCE"));
//					ConnectionsFROMINSTANCETYPE.add(eElement.getAttribute("FROMINSTANCETYPE"));
//					ConnectionsTOFIELD.add(eElement.getAttribute("TOFIELD"));
//					ConnectionsTOINSTANCE.add(eElement.getAttribute("TOINSTANCE"));
//					ConnectionsTOINSTANCETYPE.add(eElement.getAttribute("TOINSTANCETYPE"));
//				}
//			}
		    } catch (Exception e) {
			e.printStackTrace();
		    }
			//int Length_supertable = ConnectionsTOFIELD.size();
			//System.out.println("length supertable : " +Length_supertable);
//-----------------------------------------------------------------------------
//			start of Recursive data		
//-----------------------------------------------------------------------------			
	}
}
