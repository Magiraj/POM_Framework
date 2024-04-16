package base;

import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelData {

		public String[][] dataPass(String fileName) throws IOException {
		XSSFWorkbook book=new XSSFWorkbook("C:\\Users\\HP\\Desktop\\"+fileName+".xlsx");
		XSSFSheet sheet = book.getSheet("Sheet1");
		int row = sheet.getPhysicalNumberOfRows();
		System.out.println(row);
		int cell = sheet.getRow(0).getPhysicalNumberOfCells();
		System.out.println(cell);
		String[][] data=new String[row][cell]; 
		for (int i = 0; i < row; i++) {
			
			for (int j = 0; j < cell; j++) {
				
				String stringCellValue = sheet.getRow(i).getCell(j).getStringCellValue();
				data[i][j]=stringCellValue;
			}
		}
		book.close();
return data;

	}
	
}
