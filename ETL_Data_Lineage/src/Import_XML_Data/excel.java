package Import_XML_Data;


import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;



public class excel {

    private static final String FILE_NAME = "C:/share/MyFirstExcel.xlsx";
    private static final int ROW_HEADER_MAPPING_TITLE = 1;
    
    @SuppressWarnings("deprecation")
    
    public static void main(String[] args) {

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Datatypes in Java");
        Object[][] datatypes = {
                {"Datatype", "Type", "Size(in bytes)"},
                {"int", "Primitive", 2},
                {"float", "Primitive", 4},
                {"double", "Primitive", 8},
                {"char", "Primitive", 1},
                {"String", "Non-Primitive", "No fixed size"}
        };

        int rowNum = 13;
        System.out.println("Creating excel");

        for (Object[] datatype : datatypes) {
            Row row = sheet.createRow(rowNum++);
            int colNum = 0;
            for (Object field : datatype) {
                Cell cell = row.createCell(colNum++);
                if (field instanceof String) {
                    cell.setCellValue((String) field);
                } else if (field instanceof Integer) {
                    cell.setCellValue((Integer) field);
                }
            }
        }
	    CellStyle stylecenter = workbook.createCellStyle();
	    stylecenter.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	    stylecenter.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
	    
        //sheet.addMergedRegion(new CellRangeAddress(0, 0, 2, 3));
        //sheet.addMergedRegion(new CellRangeAddress(1, 1, 1, 5));
        //sheet.addMergedRegion(new CellRangeAddress(0, 0, 1, 3));
        rowNum = 1;
        Row rowtest = sheet.createRow(rowNum);
		if (rowNum == ROW_HEADER_MAPPING_TITLE){
			// merge (0) 1-9
			//
			sheet.addMergedRegion(new CellRangeAddress(rowNum,rowNum,1,9));
			Cell celltest = rowtest.createCell(1);
			celltest.setCellValue("header");
			celltest.setCellStyle(stylecenter);
		}
        
        try {
            FileOutputStream outputStream = new FileOutputStream(FILE_NAME);
			for (int i = 0; i < sheet.getRow(sheet.getLastRowNum()).getPhysicalNumberOfCells()+8 ; ++i) {
				sheet.autoSizeColumn(i);
			}

            workbook.write(outputStream);
            workbook.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Done");
    }


	}
	

