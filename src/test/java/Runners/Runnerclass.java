package Runners;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = {"src/test/java/Features"},
		glue = {"Steps"} ,
		//tags = "@UAT_M3_10-08",
		dryRun = false,    //checks whether each feature has a mapped step definition
		monochrome = true,// neat output after tc run
		plugin = {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:","json:target/positive/cucumber.json", "pretty", "html:target/positive/cucumber.html"}
)

public class Runnerclass{

}