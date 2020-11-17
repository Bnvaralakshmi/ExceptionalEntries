package StepDefinition;

import PageObjects.CAFAdmin;
import PageObjects.CLCInvoiceExemptions;
import PageObjects.LoginPage;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class CLC_StepDefinition extends CAFAdmin{
	
	CAFAdmin Admin_Object = new CAFAdmin();
	LoginPage Login_Page = new LoginPage();
	CLCInvoiceExemptions CLCInvoice_Exemptions = new CLCInvoiceExemptions();
	
	@Given("^CLC page is visible$")
	public void clc_page_is_visible() throws Throwable {
		Admin_Object.property_Class();
		Login_Page.Login_Class(driver_Class(), properties);
	}

	@When("^The user chooses the CSV file to load$")
	public void the_user_chooses_the_CSV_file_to_load() throws Throwable {
		//CLCInvoice_Exemptions.CLCInvoice(driver);
		CLCInvoice_Exemptions.readCSV();
		//CLCInvoice_Exemptions.completion_date();
		
	}

	@Then("^File upload is successful$")
	public void file_upload_is_successful() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		//CLCInvoice_Exemptions.CLCInvoice(driver);
		//CLCInvoice_Exemptions.readCSV();
		driver.close();
	}

}
