package api.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {
	
	public FileInputStream fi;
	public FileOutputStream fo;
	public XSSFWorkbook wb;
	public XSSFSheet sheet;
	public XSSFRow row;
	public XSSFCell cell;
	public CellType style;
	String path;

	public ExcelUtility(String path) {
		this.path=path;
	}
	
	public int getRowCount(String sheetName) throws IOException {
		fi=new FileInputStream(path);
		wb=new XSSFWorkbook(fi);
		sheet=wb.getSheet(sheetName);
		int rowcount=sheet.getLastRowNum();
		
		wb.close();
		fi.close();
		return rowcount;
	}
	
	public int getCellCount(String sheetName, int rowNum) throws IOException {
		fi=new FileInputStream(path);
		wb=new XSSFWorkbook(fi);
		sheet=wb.getSheet(sheetName);
		row=sheet.getRow(rowNum);
		int cellcount=row.getLastCellNum();
		
		wb.close();
		fi.close();
		return cellcount;
	}
	
	public String getCellData(String sheetName, int rowNum, int colNum) throws IOException {
		fi=new FileInputStream(path);
		wb=new XSSFWorkbook(fi);
		sheet=wb.getSheet(sheetName);
		row=sheet.getRow(rowNum);
		cell=row.getCell(colNum);
		
		//convert data type into string 
		DataFormatter formatter=new DataFormatter(); //it can format all type of data into string from
		String data;
		try {
			data=formatter.formatCellValue(cell);
		}
		catch(Exception e) {
			data=" ";
		}
		wb.close();
		fi.close();
		return data;
	}
	
	public void setCellData(String sheetName, int rowNum, int colNum, String data) throws IOException {
		File file=new File(path);
		if(!file.exists()) //if file doesn't exist then create new file
		{
			wb=new XSSFWorkbook();
			fo=new FileOutputStream(path);
			wb.write(fo);
		}
		
		fi=new FileInputStream(path);
		wb=new XSSFWorkbook(fi);
		
		if(wb.getSheetIndex(sheetName)==-1) //if sheet doesn't exist then create new sheet
			wb.createSheet(sheetName);
		sheet=wb.getSheet(sheetName);
		
		if(sheet.getRow(rowNum)==null) //if row doesn't exist then create
			sheet.createRow(rowNum);
		row=sheet.getRow(rowNum);
		
		cell=row.createCell(colNum);
		cell.setCellValue(data);
		
		fo=new FileOutputStream(path);
		wb.write(fo);
		
		wb.close();
		fi.close();
		fo.close();
	}
}





















