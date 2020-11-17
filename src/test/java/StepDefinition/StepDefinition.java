package StepDefinition;
import PageObjects.CAFAdmin;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class StepDefinition extends CAFAdmin {
	
	CAFAdmin pageObject = new CAFAdmin();

	@Given("^CAF Admin login page is up and running$")
	public void caf_Admin_login_page_is_up_and_running() throws Throwable {
	}
	@Given("^user id and password entered$")
	public void user_id_and_password_entered() throws Throwable {
	}
	@When("^user  tried to login with details$")
	public void user_tried_to_login_with_details() throws Throwable {
	}
	@Then("^user is logged in successfully$")
	public void user_is_logged_in_successfully() throws Throwable {
		//pageObject.Main_Class();
		
	}
}