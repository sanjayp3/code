package com;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelGenerator {
	
	private static XSSFWorkbook workbook;
	private static XSSFSheet sheet ;
	private static Map<String, Object[]> data;
	
	public static Map<String, Object[]> createExcelHeader()
	{
		//Blank workbook
        workbook = new XSSFWorkbook(); 
        
       //Create a blank sheet
        sheet = workbook.createSheet(" Automation-Execution-Results");
         
       //This data needs to be written (Object[])
       data = new TreeMap<String, Object[]>();
       data.put(""+0, new Object[] {" ID ", " MODULE NAME " , "  TESTCASE NAME  ", " RESULTS "});
       
       return data;
	}
	
	public static void generateExcelReport(Map<String, Object[]> data)
	{
		
		//Blank workbook
        workbook = new XSSFWorkbook(); 
        
       //Create a blank sheet
        sheet = workbook.createSheet(" Automation-Execution-Results");
         
       
       
		//Iterate over data and write to sheet
	      Set<String> keyset = data.keySet();
	      int rownum = 0;
	      for (String key : keyset)
	      {
	          Row row = sheet.createRow(rownum++);
	          Object [] objArr = data.get(key);
	          int cellnum = 0;
	          
	          for (Object obj : objArr)
	          {
	             Cell cell = row.createCell(cellnum++);
	             if(obj instanceof String)
	                  cell.setCellValue((String)obj);
	              else if(obj instanceof Integer)
	                  cell.setCellValue((Integer)obj);
	          }
	      }
	      try
	      {
	          //Write the workbook in file system
	          FileOutputStream out = new FileOutputStream(new File("//Users//sanjayp//Desktop//automation-execution-results.xlsx"));
	          workbook.write(out);
	          out.close();
	          System.out.println("automation-execution-results written successfully on disk.");
	      } 
	      catch (Exception e) 
	      {
	          e.printStackTrace();
	      }
	}

}
