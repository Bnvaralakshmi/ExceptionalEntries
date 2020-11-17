package PageObjects;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;


public class CAFAdmin {
	public static Properties properties;
	public static WebDriver driver;

	public void property_Class() throws InterruptedException, IOException {
		properties = new Properties();
		WebDriverManager.chromedriver().setup();
		
		try {
			FileInputStream fis = new FileInputStream("src/test/resources/properties/Properties.properties");
			properties.load(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public WebDriver driver_Class() throws InterruptedException, IOException {
		driver = new ChromeDriver();
		return driver;
	}
	
	//To avoid Timed out receiving message from renderer
	public static void disableSeleniumLogs() {    
		System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true");
		Logger.getLogger("org.openqa.selenium").setLevel(Level.OFF);
	}
}//End of Class
