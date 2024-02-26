package api.utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReport implements ITestListener{
	
	public ExtentSparkReporter sparkReport; //work as UI for report
	public ExtentReports extentReport; //use for to add details in report like environment, OS, project name, module name, etc...
	public ExtentTest extentTest; //use for report status passed or failed or skip
	
	String reportName;
	
	public void onStart(ITestContext context) {
		
		String time=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()); //time stamp for extent report
		reportName="Test-Report"+time+".html";
		
		sparkReport=new ExtentSparkReporter(".\\reports\\"+reportName); //report path
		
		sparkReport.config().setDocumentTitle("APIAutomationProject"); //Document title
		sparkReport.config().setReportName("Swagger User Project"); //Report name
		sparkReport.config().setTheme(Theme.DARK);
		
		extentReport= new ExtentReports();
		extentReport.attachReporter(sparkReport);
		extentReport.setSystemInfo("Application", "Swagger User Project");
		extentReport.setSystemInfo("Operating System", System.getProperty("os.name"));
		extentReport.setSystemInfo("Environment", "QA");
		extentReport.setSystemInfo("Tester", "Gagan");
	}
	
	public void onTestSuccess(ITestResult result) {
		
		extentTest=extentReport.createTest(result.getName());
		extentTest.createNode(result.getName());
		extentTest.assignCategory(result.getMethod().getGroups());
		extentTest.log(Status.PASS, "Test Passed");
	}

	public void onTestFail(ITestResult result) {
		
		extentTest=extentReport.createTest(result.getName());
		extentTest.createNode(result.getName());
		extentTest.assignCategory(result.getMethod().getGroups());
		extentTest.log(Status.FAIL, "Test Failed");
		extentTest.log(Status.FAIL, result.getThrowable().getMessage());
	}
	
	public void onTestSkip(ITestResult result) {
		
		extentTest=extentReport.createTest(result.getName());
		extentTest.createNode(result.getName());
		extentTest.assignCategory(result.getMethod().getGroups());
		extentTest.log(Status.SKIP, "Test Skiped");
		extentTest.log(Status.SKIP, result.getThrowable().getMessage());
	}
	
	public void onFinish(ITestContext context) {
		extentReport.flush();
	}
}






















