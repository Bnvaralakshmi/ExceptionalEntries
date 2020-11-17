package PageObjects;

import java.io.IOException;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.testng.Reporter;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.io.Reader;
import java.nio.file.Files;
import Utility.EE_UtilityFile;
import cucumber.api.java.it.Date;

public class CLCInvoiceExemptions extends EE_UtilityFile {

	EE_UtilityFile UtilityFile = new EE_UtilityFile();
	String sql;
	ResultSet rs;
	Statement stmt;
   
	public void CLCInvoice(WebDriver driver) throws IOException, InterruptedException{
		//CLC Invoice Exemptions
				//Go Back to home page
				WebElement home_page = driver.findElement(By.xpath("//a[@class='navbar-brand']"));
				home_page.click();

				//Get WebElement corresponding to Go to CLC Invoice Exemptions
				WebElement clc_invoice = driver.findElement(By.xpath("//a[contains(text(),'Go to Invoice exemptions')]"));
				Thread.sleep(1000);
				clc_invoice.click();

				//Get WebElement corresponding to Choose CLC file button
				Thread.sleep(1000);
				WebElement CLC_files = driver.findElement(By.name("files"));
				CLC_files.sendKeys("\\\\sqa-hanfs1\\Information Systems\\Operations\\Application Solutions\\Software Development\\Quality Assurance\\Projects\\CAF ADMIN\\CLC-Exemptions\\Test-Data\\CLC_Exemptions_NEW.csv");
				Thread.sleep(1000);

				//Get WebElement corresponding to Go to CLC Invoice Exemptions
				WebElement submit_CLC_file = driver.findElement(By.xpath("//button[contains(text(),'Submit')]"));
				submit_CLC_file.submit();
				
				String CLC_Text = driver.findElement(By.xpath("//main[@class='pb-3']")).getText();
				System.out.println("CLC File message : " + CLC_Text);

				Reporter.log("******************** CLC Invoice Exemptions********************" + "<p>" );
				Reporter.log("CLC File message: " + CLC_Text + "<p>" );
				Reporter.log("***************************************************************" + "<p>" );		
	}//End of CLCInvoice
	
	public void readCSV() throws IOException, Throwable {
		String CSV_File_Path = "\\\\sqa-hanfs1\\Information Systems\\Operations\\Application Solutions\\Software Development\\Quality Assurance\\Projects\\CAF ADMIN\\CLC-Exemptions\\Test-Data\\CLC_Exemptions_NEW.csv";
		Reporter.log("_____________________________________________________________________ " + "<p>" );
		// read the file
		Reader reader = Files.newBufferedReader(Paths.get(CSV_File_Path));
		// parse the file into csv values
		CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);

		for (CSVRecord csvRecord : csvParser) {
			// Accessing Values by Column Index
			String csv_scn = csvRecord.get(0);
			String product_code = csvRecord.get(1);
			String product_level = csvRecord.get(2);
			String csv_completion_date = csvRecord.get(3);			
			String invoice_status = csvRecord.get(4);
			
			String new_csv_completion_date;

			int temp_scn;
			temp_scn = Integer.parseInt(csv_scn);

			// print the value to console
			System.out.println("-------CLC file--------");
			System.out.println("CLC Record No - " + csvRecord.getRecordNumber());
			System.out.println("CLC SCN : " + csv_scn);
			System.out.println("CLC product_code : " + product_code);
			System.out.println("CLC product_level : " + product_level);
			System.out.println("CLC csv_completion_date : " + csv_completion_date);
			System.out.println("CLC invoice_status : " + invoice_status);
			System.out.println("------CLC file end---------" + "\n");
			new_csv_completion_date = csv_completion_date;
			completion_date(new_csv_completion_date);
			
			//if (new_csv_completion_date)
//			try{
//				if (conn==null)
//				{
//					System.out.println("connection is null...Open connection called");	
//					UtilityFile.OpenConnection();
//					stmt = UtilityFile.conn.createStatement();
//
//					System.out.println("temp scn : " + temp_scn);
//
//					sql = "SELECT e.* FROM entries e "
//							+ "where e.scn = "+ temp_scn
//							+ "and e.scn not in (select cni.scn from cert_not_invoiced cni)"
//							+ "and e.scn not in (select ie.scn from invoiced_entries ie)"
//							+ "and e.scn not in (select cr.scn from cert_result cr)";
//					
//					ResultSet rs = stmt.executeQuery(sql);
//
//					if (!rs.isBeforeFirst() ) {    
//						System.out.println("CLC SCN not present in Entries table: " + temp_scn);
//						Reporter.log("CLC SCN *NOT* present in Entries table: " + temp_scn + "<p>" );
//						//Reporter.log("--------------------------------------------------- " + "<p>" );
//					} 
//					while(rs.next()){
//						Reporter.log("CLC SCN present in Entries table: " + temp_scn + "<p>" );
//						System.out.println("----CLC table values-----------");
//						System.out.println("CLC SCN: " + rs.getInt("scn"));
//						System.out.println("CLC product_code: " + rs.getString("product_code"));
//						System.out.println("CLC product_level: " + rs.getString("product_level"));
//						System.out.println("CLC completion_date: " + rs.getString("completion_date"));
//						System.out.println("CLC invoice_status: " + rs.getString("invoice_status"));
//						String Invoice_Status = rs.getString("invoice_status");
//						String tbl_complete_date = rs.getString("completion_date");
//						System.out.println("table completion date .... " + tbl_complete_date);
//						//SimpleDateFormat.parse(complete_date);
//						if(Invoice_Status != null && !Invoice_Status.trim().isEmpty())
//						 {System.out.println("invoice status is not blank - " + Invoice_Status + " Test FAIL");
//						 }
//						else
//						{System.out.println(" invoice is blank");}
//						System.out.println("------CLC table values end---------" + "\n");
//					}
//					
//					rs.close();
//					stmt.close();
//				}
//			}
//			catch (SQLException e) {
//				System.out.println("Datababse error:");
//				e.printStackTrace();
//			} 
		}   
		Reporter.log("_____________________________________________________________________ " + "<p>" );
	}	//End of readCSV 
	
	public void completion_date(String csv_completion_date) throws Throwable{
		
		//String date = "11/11/2020";
		LocalDate convertedDate = LocalDate.parse(csv_completion_date, DateTimeFormatter.ofPattern("D/m/yyyy"));
		convertedDate = convertedDate.withDayOfMonth(
		                                convertedDate.getMonth().length(convertedDate.isLeapYear()));
		System.out.println("new converted date ... " + convertedDate);
		
		
//		String str_date = csv_completion_date;
//		DateFormat formatter;
//		Date date;
//		formatter = new SimpleDateFormat("dd-MMM-yy");
//		date = (Date) formatter.parse(str_date);
//		System.out.println("new converted date ... " + str_date);
		//LocalDate today = LocalDate.now();
	//	System.out.println(today.toString());
		
		//String dateFormat = "DD/mm/yyyy";
		System.out.println("naga : date " + convertedDate.toString());
		
		//return convertedDate.now();
		//return convertedDate;
	}
} //End of Class
