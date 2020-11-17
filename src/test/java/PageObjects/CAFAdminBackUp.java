package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriverService;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;


public class CAFAdminBackUp {
	public static Properties properties;
	String str = null;

	LoginPage Login_Page = new LoginPage();
	Exceptional_Entries ExceptionalEntries = new Exceptional_Entries();
	CertDatesEntries CDES = new CertDatesEntries();
	CLCInvoiceExemptions CLCInvoice_Exemptions = new CLCInvoiceExemptions();
	
	@Test
	public void Main_Class() throws InterruptedException, IOException {
		properties = new Properties();
		WebDriverManager.chromedriver().setup();

		WebDriver driver = null; 
		driver = new ChromeDriver();

		//Login Page
		Login_Page.Login_Class(driver, properties);

		//Exceptional Processing
		ExceptionalEntries.Exceptional_Processing(driver);
		ExceptionalEntries.readCSV();

		//CDES
		CDES.CDESUpdates(driver);
		CDES.readCSV();

		//CLC Invoice Exemptions
		CLCInvoice_Exemptions.CLCInvoice(driver);
		//CLCInvoice_Exemptions.readCSV();
		
		driver.quit();
	}

	//To avoid Timed out receiving message from renderer
	public static void disableSeleniumLogs() {    
		System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true");
		Logger.getLogger("org.openqa.selenium").setLevel(Level.OFF);
	}
}//End of Class
