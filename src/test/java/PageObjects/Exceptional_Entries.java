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

public class Exceptional_Entries extends EE_UtilityFile {

	EE_UtilityFile UtilityFile = new EE_UtilityFile();
	String sql;
	ResultSet rs;
	Statement stmt;
	
	public void Exceptional_Processing(WebDriver driver) throws IOException, InterruptedException{
		//Get WebElement corresponding to Go to exceptional processing
				WebElement ex_entries = driver.findElement(By.xpath("/html/body/div/main/div[2]/div[1]/div/div/a"));
				Thread.sleep(1000);
				ex_entries.click();

				//Get WebElement corresponding to Choose file button
				Thread.sleep(1000);
				WebElement files = driver.findElement(By.name("files"));
				files.sendKeys("\\\\sqa-hanfs1\\Information Systems\\Operations\\Application Solutions\\Software Development\\Quality Assurance\\Projects\\CAF ADMIN\\Exceptional_Entries\\Test Data\\Exceptional_Entries_Test_Data_REG.csv");
				Thread.sleep(1000);

				//Get WebElement corresponding to Go to exceptional processing
				WebElement submit_file = driver.findElement(By.xpath("//*[@id=\"form1\"]/div[2]/button"));
				submit_file.submit();

				String Text = driver.findElement(By.xpath("//main[@class='pb-3']")).getText();
				System.out.println("Exceptionl Entries File message : " + Text);

				Reporter.log("*****************Exceptional Entries **************************" + "<p>" );
				Reporter.log("EE File message: " + Text + "<p>" );
				Reporter.log("***************************************************************" + "<p>" );
		
	}//End of Exceptional_Processing

	public void readCSV() throws IOException {
		String CSV_File_Path = "\\\\sqa-hanfs1\\Information Systems\\Operations\\Application Solutions\\Software Development\\Quality Assurance\\Projects\\CAF ADMIN\\Exceptional_Entries\\Test Data\\Exceptional_Entries_Test_Data_REG.csv";
		Reporter.log("_____________________________________________________________________ " + "<p>" );
		// read the file
		Reader reader = Files.newBufferedReader(Paths.get(CSV_File_Path));
		// parse the file into csv values
		CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);

		for (CSVRecord csvRecord : csvParser) {
			// Accessing Values by Column Index
			String centre_code = csvRecord.get(0);
			String product_code = csvRecord.get(1);
			String product_level = csvRecord.get(2);
			String process_date = csvRecord.get(3);
			String csv_scn = csvRecord.get(4);

			int temp_scn;
			temp_scn = Integer.parseInt(csv_scn);

			// print the value to console
			System.out.println("-------Exceptional Entries file--------");
			System.out.println("EE Record No - " + csvRecord.getRecordNumber());
			System.out.println("EE centre_code : " + centre_code);
			System.out.println("EE product_code : " + product_code);
			System.out.println("EE product_level : " + product_level);
			System.out.println("EE process_date : " + process_date);
			System.out.println("EE scn : " + csv_scn);
			System.out.println("------Exceptional Entries file end---------" + "\n");
			try{
				if (conn==null)
				{
					System.out.println("connection is null...Open connection called");	
					UtilityFile.OpenConnection();
					stmt = UtilityFile.conn.createStatement();

					System.out.println("temp scn : " + temp_scn);

					sql = "SELECT * FROM entries where scn = "+ temp_scn;
					ResultSet rs = stmt.executeQuery(sql);

					if (!rs.isBeforeFirst() ) {    
						System.out.println("EE SCN not present in Entries table: " + temp_scn);
						Reporter.log("EE SCN *NOT* present in Entries table: " + temp_scn + "<p>" );
						//Reporter.log("--------------------------------------------------- " + "<p>" );
					} 
					while(rs.next()){
						Reporter.log("EE SCN present in Entries table: " + temp_scn + "<p>" );
						System.out.println("----EE table values-----------");
						System.out.println("EE Centre id: " + rs.getInt("centre_id"));
						System.out.println("EE product_code: " + rs.getString("product_code"));
						System.out.println("EE product_level: " + rs.getString("product_level"));
						System.out.println("EE SCN: " + rs.getInt("scn"));
						System.out.println("EE entry_status: " + rs.getString("entry_status"));
						System.out.println("EE entry_statge: " + rs.getString("entry_stage"));
						System.out.println("EE process_date: " + rs.getString("process_date"));
						System.out.println("------EE table values end---------" + "\n");
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
