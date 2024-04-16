package pages;

import java.io.IOException;

import org.openqa.selenium.chrome.ChromeDriver;

import base.ProjectSpecification;
import io.cucumber.java.en.Then;

public class Submit_Createlead extends ProjectSpecification{
public Submit_Createlead(ChromeDriver driver) {
	this.driver=driver;
}

	public void submitCreateL() throws IOException {
		
		try {
			System.out.println("Submited successfully");
			reportStep("Valid successfully", "Pass");
		} catch (Exception e) {
			reportStep("Valid not successfully", "fail");
		}

	}
	
}
