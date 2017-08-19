package Import_XML_Data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Powercenter_Parameter_File_Import {

/**
 * line can start with the following chars
 * (*) remark 
 * ([) position "tree" position (global,workflow or mapping level)
 * ($) setting parameter
 * ($$) paranmeter
 * () empty
 * 
 * https://neo4j.com/
 * 
 * http://www.dlineage.com/impact-analysis-data-lineage.html
 * We use a general purpose Java based GoldParser engine and 
 * we have developed a custom SQL grammar which is written in Extended Backus-Naur Form (EBNF). 
 * Our grammar is based on ANSI/SQL syntax, 
 * 
 * http://www.goldparser.org/articles/bnf.htm
 * 
 * exicute sql as dummy csv jdbc .. cool no database needed 
 * https://stackoverflow.com/questions/29860450/how-to-parse-an-sql-query-in-java
 * 
 * @param args
 */
	
	public static void main(String[] args){

		String PARAMETERFILE    			= "C:/ETL_WORFLOWS/DBM_ALCHEMY/ALCHEMY.txt";
		ArrayList<String> listOfLines 		= new ArrayList<>();
		ArrayList<String> parameterLines 	= new ArrayList<>();
		
		// read in file into list
		try (BufferedReader br = new BufferedReader(new FileReader(PARAMETERFILE))) {
			String sCurrentLine;
			while ((sCurrentLine = br.readLine()) != null) {
				listOfLines.add(sCurrentLine);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		// analyze the lines in catagories
		String treeLocation 		= "";
		String treeLocation_part1 	= "";
		String treeLocation_part2 	= "";
		String treeLocation_part3 	= "";
		
		for (int lineNumber = 0; lineNumber < listOfLines.size(); lineNumber++) {
			// remove the pre_Strings 
			String rawLine = (listOfLines.get(lineNumber));
			if (!"".equals(rawLine)){
				if ("*".equals(rawLine.substring(0,1))){
					//What to do with comments
				} 
				//problem with double $$
				if ("$".equals(rawLine.substring(0,1))){
					if ("$$".equals(rawLine.substring(0,2))){
						String[] parts 	= rawLine.split("=");
						String part1 	= parts[0]; // setting
						String part2 	= parts[1]; // value
						parameterLines.add(treeLocation_part1);
						parameterLines.add(treeLocation_part2);
						parameterLines.add(treeLocation_part3);
						parameterLines.add(part1);
						parameterLines.add(part2);						
					} else {
						String[] parts 	= rawLine.split("=");
						String part1 	= parts[0]; // parameter
						String part2 	= parts[1]; // value
						parameterLines.add(treeLocation_part1);
						parameterLines.add(treeLocation_part2);
						parameterLines.add(treeLocation_part3);
						parameterLines.add(part1);
						parameterLines.add(part2);						
					}
				}

				if ("[".equals(rawLine.substring(0,1))){
					if ("[Service".equals(rawLine.substring(0,8))){
						treeLocation 			= treeLocation+rawLine;
					} else {
						if ("[GLOBAL".equals(rawLine.substring(0,7))){
							treeLocation 		= treeLocation+rawLine;
							//GLOBAL,GLOBAL,GLOBAL,$setting,value
							treeLocation_part1 	= "GLOBAL";
							treeLocation_part2 	= "GLOBAL";
							treeLocation_part3 	= "GLOBAL";
						} else {
						treeLocation = rawLine;
						//folder,wkf,mp,$$parameter,value
						//split into foldername 
						//split into workflow name
						//split into mapping ??
						// escape the dot if you want to split on a literal dot:
						String[] parts 		= rawLine.split("\\.");
						String part11 		= parts[0]; // Folder
						String part12 		= parts[1]; // Workflow
						part12 				= part12.substring(3).replace("]","");
						part11 				= part11.replace("[","");
						treeLocation_part1 	= ""+part11;
						treeLocation_part2 	= ""+part12;
						treeLocation_part3 	= "";						
						}
					}
				}
			}else {
				//Empty line skip 
			}
		}
		for (int parmeterSet = 0; parmeterSet < parameterLines.size(); parmeterSet++) {
			System.out.print(parameterLines.get(parmeterSet+0)+",");//folder NAME
			System.out.print(parameterLines.get(parmeterSet+1)+",");//workflow NAME
			System.out.print(parameterLines.get(parmeterSet+2)+",");//?
			System.out.print(parameterLines.get(parmeterSet+3)+",");//Parameters settings NAME
			System.out.print(parameterLines.get(parmeterSet+4));//Value 
			System.out.println();
			parmeterSet++;parmeterSet++;parmeterSet++;parmeterSet++;
		}
	}
}
