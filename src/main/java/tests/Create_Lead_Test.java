package tests;

import java.io.IOException;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import base.ProjectSpecification;
import pages.CreateLead;

public class Create_Lead_Test extends ProjectSpecification {
	@BeforeTest
	public void setFile() {
		excelFileName="Mega";
		TestCaseName="CreateLead Validation";
		Description="Creating a new user";
		AuthorName="Megavarnan" ;
		Categeroy="Regression";		

	}

	
	@Test(dataProvider = "Fname")
	public void createLrun(String cNam, String fNam, String lNam) throws IOException {
		CreateLead create=new CreateLead(driver);
		create.CreateLeadMethod(cNam, fNam, lNam).clcikSubmit();

	}
	
	
}
