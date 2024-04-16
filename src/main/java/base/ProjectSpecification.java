package base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import io.cucumber.testng.AbstractTestNGCucumberTests;

public class ProjectSpecification{
public ChromeDriver driver;
public String excelFileName;
public static Properties prop;
public static ExtentHtmlReporter reporter;
public static ExtentReports report;
public static ExtentTest test, createNode;
public String TestCaseName, Description, AuthorName, Categeroy;
@BeforeSuite
public void reportGeneration() {
reporter=new ExtentHtmlReporter("./Reports/result.html");
//reporter.setAppendExisting(true);
report=new ExtentReports();
report.attachReporter(reporter);
}
@BeforeClass
public void testDetails() {
	test = report.createTest(TestCaseName, Description);
	test.assignAuthor(AuthorName);
	test.assignCategory(Categeroy);	

}

public int takesnap() throws IOException {
	int ran=(int) (Math.random() * 90000);
	File src = driver.getScreenshotAs(OutputType.FILE);
	File des=new File("./snaps/img"+ran+".png");
	FileUtils.copyFile(src, des);
	return ran;

}

public void reportStep(String msg, String status) throws IOException {
	if (status.equalsIgnoreCase("pass")) {
		createNode.pass(msg, MediaEntityBuilder.createScreenCaptureFromPath(".././snaps/img"+takesnap()+".png").build());
	} else if(status.equalsIgnoreCase("fail")) {
		createNode.fail(msg, MediaEntityBuilder.createScreenCaptureFromPath(".././snaps/img"+takesnap()+".png").build());
	}

}

	@BeforeMethod
	public void startApp() throws InterruptedException, IOException {
		 createNode = test.createNode(TestCaseName);
		FileInputStream fis=new FileInputStream("./src/main/resources/LoginPage.properties");
		 prop=new Properties();
		prop.load(fis);
		 driver  = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://leaftaps.com/opentaps/control/main");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.id("username")).sendKeys(prop.getProperty("UserName"));
		driver.findElement(By.id("password")).sendKeys(prop.getProperty("Password"));
		driver.findElement(By.className("decorativeSubmit")).click();
		Thread.sleep(4000);
		driver.findElement(By.xpath("//a[contains(text(), 'CRM/SFA')]")).click();
		
	}
@DataProvider(name = "Fname")
	public String[][] dataProvideExcel() throws IOException {
	
	ExcelData dataP=new ExcelData();
	String[][] dataPass = dataP.dataPass(excelFileName);
	return dataPass;
	}
@AfterMethod
public void endUp() {
	driver.close();

}
@AfterSuite
public void endFlush() {
	
report.flush();
}

}
