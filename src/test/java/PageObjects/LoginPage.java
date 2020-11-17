package PageObjects;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
	public void Login_Class(WebDriver driver, Properties properties) throws InterruptedException, IOException {

		driver.navigate().to(properties.getProperty("siteAddress"));

		//Get WebElement corresponding to email
		WebElement email = driver.findElement(By.id("Input_Email"));
		email.sendKeys(properties.getProperty("Email"));  

		//Get WebElement corresponding to password
		WebElement password = driver.findElement(By.xpath("//*[@id=\"Input_Password\"]"));
		Thread.sleep(1000);
		password.sendKeys(properties.getProperty("password")); 

		//Get WebElement corresponding to login (submit button)
		WebElement login = driver.findElement(By.xpath("//button[contains(text(),'Log in')]"));
		Thread.sleep(1000);
		login.click();
	}
}//End of class
