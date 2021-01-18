package Steps.backup.Portal_Statement_Request;

import java.io.FileInputStream;
import java.util.List;
import java.util.Properties;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import utils.SeleniumDriver;


public class Portal_Statement_Request {

	public static WebDriver driver = SeleniumDriver.getDriver();
	public WebDriverWait wait;
	public Scenario scenario = null;
	public Properties Pro;
	public JavascriptExecutor jse = (JavascriptExecutor)driver;

	@Before(order = 0)
	public void method1() throws Exception {
		// this.S=S;
		Pro = new Properties();
		FileInputStream fip = new FileInputStream(".\\src\\test\\resources\\Objects\\object.properties");
		Pro.load(fip);
	}


	@Given("^User is in browser and launches the app URL$")
	public void user_is_in_browser_and_launches_the_app_url() throws Throwable {

		driver.get(Pro.getProperty("PORTAL_URL"));
		driver.manage().window().maximize();

	}

	@Then("^Login to portal$")
	public void portalLogin(DataTable data) throws Throwable {

		SeleniumDriver.waitForPageToLoad();
		List < List < String >> obj = data.asLists();

		driver.findElement(By.id(Pro.getProperty("USERNAME_ID"))).sendKeys(obj.get(0).get(0));
		driver.findElement(By.id(Pro.getProperty("PASSWORD_ID"))).sendKeys(obj.get(0).get(1));
		driver.findElement(By.id(Pro.getProperty("LOGIN_ID"))).click();


	}

	@Then("^Verify Home Screen Buttons$")
	public void verify_home_screen_buttons(DataTable data) throws Throwable {

		SeleniumDriver.waitForPageToLoad();
		List < List < String >> obj = data.asLists();
		String buttons[] = {
				obj.get(0).get(0),
				obj.get(1).get(0),
				obj.get(2).get(0),
				obj.get(3).get(0)
		};

		for(int i = 0; i<buttons.length; i++)
		{
			WebElement Button = driver.findElement(By.xpath("//button[contains(text(),'"+buttons[i]+"')]"));
			if(Button.isDisplayed())
			{
				Assert.assertTrue("Button '"+buttons[i]+"' found",true);
			}
			else{
				Assert.fail("Button '"+buttons[i]+"' not found");
			}

		}



	}

	@Then("^Verify Tax Accounts Table$")
	public void verify_tax_accounts_table(DataTable data) throws Throwable {
		List<List<String>> obj = data.asLists();
		String columns[] = {obj.get(0).get(0),obj.get(1).get(0)};

		//check if columns exist
		for (String column : columns) {
			WebElement Table_Column = driver.findElement(By.xpath("//th[contains(text(),'" + column + "')]"));


			if (Table_Column.isDisplayed()) {

				System.out.println("Column : " + column + " found ");
				Assert.assertTrue(true);
				Thread.sleep(2000);
			} else {

				Assert.fail();
				System.out.println("Column : " + column + " not found ");

			}
		}
	}

	@Then("^Click MY TAX button$")
	public void click_my_tax_button() throws Throwable {

		SeleniumDriver.waitForPageToLoad();
		driver.findElement(By.id(Pro.getProperty("My_Tax_Button_ID"))).click();

	}

	@Then("^Verify my tax account table columns$")
	public void verify_my_tax_account_table_columns(DataTable data) throws Throwable {
        SeleniumDriver.waitForPageToLoad();
		List<List<String>> obj = data.asLists();
		String columns[] = {obj.get(0).get(0),obj.get(1).get(0),obj.get(2).get(0)};

		//check if columns exist
		for(int i = 0; i < columns.length; i++)
		{
			WebElement Table_Column = driver.findElement(By.xpath("//th[contains(text(),'"+columns[i]+"')]"));


			if(Table_Column.isDisplayed())
			{

				System.out.println( "Column : " + columns[i] + " found ");
				Assert.assertTrue(true);
				Thread.sleep(2000);
			}
			else
			{

				Assert.fail();
				System.out.println( "Column : " + columns[i] + " not found ");

			}
		}
	}

	@Then("^Verify manage my account dropdowns$")
	public void verify_manage_my_account_dropdowns(DataTable data) {
		List < List < String >> obj = data.asLists();
		String dropdowns[] = {
				obj.get(0).get(0),
				obj.get(1).get(0),
				obj.get(2).get(0),
				obj.get(3).get(0)
		};

		for(int i = 0; i<dropdowns.length; i++)
		{
			WebElement Dropdown = driver.findElement(By.xpath("//div[contains(text(),'"+dropdowns[i]+"')]"));
			if(Dropdown.isDisplayed())
			{
				Assert.assertTrue("Dropdown '"+dropdowns[i]+"' found",true);
			}
			else{
				Assert.fail("Dropdown '"+dropdowns[i]+"' not found");
			}

		}

	}

	@Then("^Select Suspense account$")
	public void select_suspense_account() throws Throwable {
		SeleniumDriver.waitForPageToLoad();
		driver.findElement(By.xpath(Pro.getProperty("Suspence_Account_XPATH"))).click();
	}

	@Then("^Verify the input fields$")
	public void verify_input_fields(DataTable data) throws Throwable {

		SeleniumDriver.waitForPageToLoad();

		List < List < String >> obj = data.asLists();
		String[] elements = {obj.get(0).get(0),obj.get(1).get(0),obj.get(2).get(0)};

		for (String element : elements) {
			WebElement InputElement = driver.findElement(By.xpath(element));
			if (InputElement.isDisplayed()) {
				Assert.assertTrue(true);
			} else {
				Assert.fail("Element '" + element + "' not found");
			}

		}

	}

	@Then("^Verify table columns \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" in my tax$")
	public void verify_table_columns_in_my_tax(String TaxAccountType, String AccountNumber, String CurrentBalance, String CurrentStatus, String Date, String DocumentType, String DocumentReference, String Status, String Amount) throws Throwable {

		String columns[] = {TaxAccountType,AccountNumber,CurrentBalance,CurrentStatus,Date,DocumentType,DocumentReference,Status,Amount};

		//check if columns exist
		for (String column : columns) {
			WebElement Table_Column = driver.findElement(By.xpath("//th[contains(text(),'" + column + "')]"));


			if (Table_Column.isDisplayed()) {

				System.out.println("Column : " + column + " found ");
				Assert.assertTrue(true);
				Thread.sleep(2000);
			} else {

				Assert.fail();
				System.out.println("Column : " + column + " not found ");

			}
		}
	}

	@Then("^Verify transaction table columns \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\"$")
	public void verify_transaction_table_columns(String taxAccountType, String accountNumber, String currentBalance, String currentStatus) throws Throwable {
		String columns[] = {taxAccountType,accountNumber,currentBalance,currentStatus};
		SeleniumDriver.waitForPageToLoad();
		//check if columns exist
		for (String column : columns) {
			WebElement Table_Column = driver.findElement(By.xpath("//th[contains(text(),'" + column + "')]"));


			if (Table_Column.isDisplayed()) {

				System.out.println("Column : " + column + " found ");
				Assert.assertTrue(true);
				Thread.sleep(2000);
			} else {

				Assert.fail();
				System.out.println("Column : " + column + " not found ");

			}
		}
	}

	@Then("^Verify current balance is not zero$")
	public void verify_current_balance_is_not_zero() throws Throwable {


		String Balance = driver.findElement(By.xpath("//*[@id=\"taxBasicInfoTable\"]/div/div/table/tbody/tr/td[3]/span[2]")).getText();
		if(Balance.equals("0.00"))
		{
			Assert.fail("Balance shown as 0.00");
		}
		else{
			Assert.assertTrue(true);
		}

	}

	@Then("^Enter end date value that is more than 365 days from current start date \"([^\"]*)\"$")
	public void enter_end_date_value_that_is_more_than_365_days_from_current_start_date(String date) throws Throwable {
		SeleniumDriver.waitForPageToLoad();

		//jse.executeScript("document.getElementById('"+Pro.getProperty("End_Date_ID")+"').setAttribute('value', '"+date+"')");
		driver.findElement(By.xpath(Pro.getProperty("Date_Picker_XPATH"))).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath(Pro.getProperty("Date_XPATH"))).click();


	}

	@Then("^Click search$")
	public void click_search() throws Throwable {

		driver.findElement(By.id(Pro.getProperty("Search_ID"))).click();

	}

	@Then("^Verify error message \"([^\"]*)\"$")
	public void verify_error_message(String ErrorMessage) throws Throwable {

		Thread.sleep(1000);
		WebElement successMessage = driver.findElement(By.xpath("//span[contains(text(),'" + ErrorMessage + "')]"));

		if (successMessage.isDisplayed()) {
			System.out.println("Error message ('" + ErrorMessage + "') has been displayed");
			Assert.assertTrue(true);
		} else {
			Assert.fail("Error message not displayed");

		}

	}

	@Then("^Click on a suspended tax type$")
	public void click_on_a_suspended_tax_type() throws Throwable {
		SeleniumDriver.waitForPageToLoad();
		driver.findElement(By.xpath("//span[contains(text(),'Suspended')]")).click();

	}

	@Then("^Click on a de registered tax type$")
	public void click_on_a_deregistered_tax_type() throws Throwable {
		SeleniumDriver.waitForPageToLoad();
		driver.findElement(By.xpath("//span[contains(text(),'De-registered')]")).click();
		//driver.findElement(By.xpath(Pro.getProperty("Deregistered_XPATH"))).click();
	}

	@Then("^Verify Status \"([^\"]*)\" in field \"([^\"]*)\"$")
	public void verify_status(String Status, String StatusXpath) throws Throwable {
		SeleniumDriver.waitForPageToLoad();
		String DisplayedStatus = driver.findElement(By.xpath(Pro.getProperty(StatusXpath))).getText();
		if(Status.equals(DisplayedStatus))
		{
			Assert.assertTrue(true);
		}
		else{
			Assert.fail("Incorrect status displayed");
		}
	}

	@Then("^Click statement requests under tasks$")
	public void click_statement_requests_under_tasks(){
		SeleniumDriver.waitForPageToLoad();
		driver.findElement(By.id(Pro.getProperty("Statement_Request_ID"))).click();
	}

	@Then("^Click tax type dropdown and select tax type that has transactions$")
	public void click_tax_type_dropdown_and_select_tax_type_that_has_transactions() throws Throwable {
		SeleniumDriver.waitForPageToLoad();
		driver.findElement(By.xpath(Pro.getProperty("TaxTypeDropdownXPATH"))).click();

		Thread.sleep(1000);
		String yearXPATH = "//span[contains(text(),'Suspense')]";
		driver.findElement(By.xpath(yearXPATH)).click();
	}

	@Then("^Verify statement request input fields$")
	public void verify_statement_request_input_fields(DataTable data) throws Throwable {

		Thread.sleep(3000);

		List < List < String >> obj = data.asLists();
		String[] elements = {obj.get(0).get(0),obj.get(1).get(0),obj.get(2).get(0),obj.get(3).get(0),obj.get(4).get(0)};

		for (String element : elements) {
			WebElement InputElement = driver.findElement(By.xpath(Pro.getProperty(element)));
			if (InputElement.isDisplayed()) {
				Assert.assertTrue(true);
			} else {
				Assert.fail("Element '" + element + "' not found");
			}

		}
	}

	@Then("^Click cancel button$")
	public void click_cancel_button() throws Throwable{

		Thread.sleep(2000);
		driver.findElement(By.xpath(Pro.getProperty("Cancel_XPATH"))).click();

	}

	@Then("^Select month \"([^\"]*)\" and \"([^\"]*)\"$")
	public void select_month_and_year(String month, String year) throws Throwable {

		Thread.sleep(2000);
		driver.findElement(By.xpath(Pro.getProperty("MonthDropdownXPATH"))).click();
		String monthXPATH = "//span[contains(text(),'"+month+"')]";
		driver.findElement(By.xpath(monthXPATH)).click();

		driver.findElement(By.xpath(Pro.getProperty("YearDropdownXPATH"))).click();
		String yearXPATH = "//span[contains(text(),'"+year+"')]";
		driver.findElement(By.xpath(yearXPATH)).click();


	}

	@Then("^Click submit : portal$")
	public void click_submit_portal() throws Throwable{

		driver.findElement(By.xpath(Pro.getProperty("Submit_XPATH"))).click();


	}

	@Then("^Click download and verify download$")
	public void click_download_and_verify_download() throws Throwable{

		SeleniumDriver.waitForPageToLoad();
		Thread.sleep(4000);
		if(driver.findElement(By.xpath(Pro.getProperty("Download_XPATH"))).isEnabled())
		{
			Assert.assertTrue("Download button is enabled",true);
		}
		else{
			Assert.fail("Download button not enabled");
		}

	}

	@Then("^Logout$")
	public void logout() throws Throwable
	{
		driver.findElement(By.xpath("/html/body/trips-app/trips-nav/div/div/nav/nav-menu/div[1]/user-profile-menu/div/ul/li/a")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("id_linkUserLogout")).click();

	}



}