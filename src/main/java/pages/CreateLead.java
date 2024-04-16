package pages;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import base.ProjectSpecification;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

public class CreateLead extends ProjectSpecification {
	
	public CreateLead(ChromeDriver driver) {
this.driver=driver;
	}
	
	public CreateLead CreateLeadMethod (String cNames, String fNames, String lNames) throws IOException {
		try {
			driver.findElement(By.linkText("Leads")).click();
			reportStep("CLick Leads successfully", "Pass");
		} catch (Exception e) {
			
			reportStep("CLick Leads not successfully", "fail");
		}
		try {
			Thread.sleep(4000);
			driver.findElement(By.linkText("Create Lead")).click();
			reportStep("CLick createLead successfully", "Pass");
		} catch (Exception e) {
			reportStep("CLick createLead not successfully", "fail");
		}
		try {
			WebElement ele = driver.findElement(By.id("createLeadForm_companyName"));
			ele.sendKeys(cNames);
			driver.executeScript("arguments[0].style.border='solid red'", ele);
			
			driver.findElement(By.id("createLeadForm_firstName")).sendKeys(fNames);
			driver.findElement(By.id("createLeadForm_lastName")).sendKeys(lNames);
			reportStep("Enter successfully", "Pass");
		} catch (Exception e) {
			reportStep("Enter not successfully", "fail");
		}
		return this;
		
	}

	
	public Submit_Createlead clcikSubmit() throws IOException {
		try {
			driver.findElement(By.name("submitButton")).click();
			reportStep("Click successfully", "Pass");
		} catch (Exception e) {
			reportStep("Click not successfully", "Pass");
		}
		return new Submit_Createlead(driver);

	}
	
}
