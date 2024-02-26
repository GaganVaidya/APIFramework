package api.utilities;

import java.io.IOException;

public class DataProvider {

	@org.testng.annotations.DataProvider(name="Data")
	public String[][] getAllData() throws IOException{
		
		String path=System.getProperty("user.dir")+"//testData//UserTestData.xlsx";
		ExcelUtility xl=new ExcelUtility(path);
		
		int rowcount=xl.getRowCount("Sheet1");
		int colcount=xl.getCellCount("Sheet1", 1);
		
		String apiData[][]=new String[rowcount][colcount];
		for(int i=1; i<=rowcount;i++) {
			for(int j=0; j<colcount; j++) {
				apiData[i-1][j]=xl.getCellData("Sheet1", i, j);
			}
		}
		return apiData;
	}
	
	@org.testng.annotations.DataProvider(name="UserNames")
	public String[] getUsername() throws IOException {
		
		String path=System.getProperty("user.dir")+"//testData//UserTestData.xlsx";
		ExcelUtility xl=new ExcelUtility(path);
		
		int rowcount=xl.getRowCount("Sheet1");
		
		String apiData[]=new String[rowcount];
		for(int i=1; i<=rowcount; i++) {
			apiData[i-1]=xl.getCellData("Sheet1", i, 1);
		}
		return apiData;
	}
}
