package PageObjects;

import java.io.IOException;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

public class CertDatesEntries extends EE_UtilityFile {

	EE_UtilityFile UtilityFile = new EE_UtilityFile();
	String sql, sql_certResult, sql_certNotInvoiced;
	ResultSet rs, rs_certResult, rs_certNotInvoiced;
	Statement stmt, stmt_certNotInvoiced;
   
	public void CDESUpdates(WebDriver driver) throws IOException, InterruptedException{
				//Go Back to home page
				WebElement home_page = driver.findElement(By.xpath("//a[@class='navbar-brand']"));
				home_page.click();

				//Get WebElement corresponding to Go to CDES
				WebElement cdes = driver.findElement(By.xpath("//a[contains(text(),'Add certification dates')]"));
				Thread.sleep(1000);
				cdes.click();

				//Get WebElement corresponding to Choose CDES file button
				Thread.sleep(1000);
				WebElement CDES_files = driver.findElement(By.name("files"));
				CDES_files.sendKeys("\\\\SQA-HANFS1\\information Systems\\Software Development\\Quality Assurance\\Projects\\CAF ADMIN\\CertifcateDates_EntryStatus Update\\Test_Data\\CDES-Update.csv");
				Thread.sleep(1000);

				//Get WebElement corresponding to Go to CDES Updates
				WebElement submit_CDES_file = driver.findElement(By.xpath("//*[@id=\"form1\"]/div[2]/button"));
				submit_CDES_file.submit();
				Thread.sleep(1000);
				
				String CDES_Text = driver.findElement(By.xpath("//main[@class='pb-3']")).getText();
				System.out.println("CDES File message : " + CDES_Text);

				Reporter.log("***Certificate Date and entry status Updates*******************" + "<p>" );
				Reporter.log("CDES File message: " + CDES_Text + "<p>" );
				Reporter.log("***************************************************************" + "<p>" );		
	}//End of CDESUpdates
	
	public void readCSV() throws IOException {
		String CSV_File_Path = "\\\\SQA-HANFS1\\information Systems\\Software Development\\Quality Assurance\\Projects\\CAF ADMIN\\CertifcateDates_EntryStatus Update\\Test_Data\\CDES-Update.csv";
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
			String completion_date = csvRecord.get(3);			

			int temp_scn, temp_scn_certNotInvoiced;
				temp_scn = Integer.parseInt(csv_scn);
				temp_scn_certNotInvoiced = Integer.parseInt(csv_scn);

			// print the value to console
			System.out.println("-------CDES file--------");
			System.out.println("CDES Record No - " + csvRecord.getRecordNumber());
			System.out.println("CDES SCN : " + csv_scn);
			System.out.println("CDES product_code : " + product_code);
			System.out.println("CDES product_level : " + product_level);
			System.out.println("CDES completion_date : " + completion_date);
			System.out.println("------CDES file end---------" + "\n");
			try{
				if (conn==null)
				{
					System.out.println("connection is null...Open connection called");	
					UtilityFile.OpenConnection();
					stmt = UtilityFile.conn.createStatement();
					stmt_certNotInvoiced = UtilityFile.conn.createStatement();

					System.out.println("temp scn : " + temp_scn);

					sql = "SELECT * FROM entries where scn = "+ temp_scn;
					ResultSet rs = stmt.executeQuery(sql);
					
					System.out.println("temp scn cert not invoiced:" + temp_scn_certNotInvoiced);
					
					sql_certNotInvoiced = "SELECT * FROM cert_not_invoiced where scn = "+ temp_scn_certNotInvoiced;
					rs_certNotInvoiced = stmt_certNotInvoiced.executeQuery(sql_certNotInvoiced);

					if (!rs.isBeforeFirst() ) {    
						System.out.println("CDES SCN not present in Entries table: " + temp_scn);
						Reporter.log("CDES SCN *NOT* present in Entries table: " + temp_scn + "<p>" );
						//Reporter.log("--------------------------------------------------- " + "<p>" );
					    
						sql_certResult = "SELECT * FROM cert_result where scn = "+ temp_scn;
						rs = stmt.executeQuery(sql_certResult);
						
						System.out.println("CDES SCN is present in cert result table: " + temp_scn);
						Reporter.log("CDES SCN *IS* present in cert result table: " + temp_scn + "<p>" );
						//Reporter.log("--------------------------------------------------- " + "<p>" );
					    
					} 
					while(rs.next()){
						Reporter.log("CDES SCN present in table: " + temp_scn + "<p>" );
						System.out.println("----CDES table values-----------");
						System.out.println("CDES SCN: " + rs.getInt("scn"));
						System.out.println("CDES product_code: " + rs.getString("product_code"));
						System.out.println("CDES product_level: " + rs.getString("product_level"));
						System.out.println("CDES completion_date: " + rs.getString("completion_date"));
						System.out.println("------CDES table values end---------" + "\n");
					}
					while (rs_certNotInvoiced.next()){
						System.out.println("CDES from cert_not_invoiced SCN: " + rs_certNotInvoiced.getInt("scn"));
						System.out.println("CDES from cert_not_invoiced runid: " + rs_certNotInvoiced.getInt("run_id"));					
					}
					rs.close();
					stmt.close();
				}
			}
			catch (SQLException e) {
				System.out.println("Datababse error:");
				e.printStackTrace();
			} 
		}   
		Reporter.log("_____________________________________________________________________ " + "<p>" );
	}	//End of readCSV 
} //End of Class
