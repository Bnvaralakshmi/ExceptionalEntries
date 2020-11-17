package RunnerFile;
import org.testng.annotations.Test;
import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import cucumber.api.testng.AbstractTestNGCucumberTests;

  //This is the Runner file 
  //@features - Specify the feature file location
  //@glue - Specify the StepDefinition location 

  	@RunWith(Cucumber.class)
  	@CucumberOptions(features={"src\\test\\resources\\features"},
  					 glue={"StepDefinition"},
  					 tags={"@CLC"}
  					 			)
  	@Test
  	public class RunnerClass extends AbstractTestNGCucumberTests
  	//public class RunnerFile 
  	{

  	}
 	