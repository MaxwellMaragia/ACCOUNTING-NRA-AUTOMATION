package Steps.backup.RevenueControlAccounting;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.SeleniumDriver;

import java.io.FileInputStream;
import java.util.List;
import java.util.Properties;


public class RevenueControlAccounting {

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

		driver.get(Pro.getProperty("MRA_BackOffice_URL"));
		driver.manage().window().maximize();

	}

	@Then("^Login as Revenue Officer$")
	public void login_as_revenue_officer(DataTable data) throws Throwable {

		List < List < String >> obj = data.asLists();

		driver.findElement(By.id(Pro.getProperty("BackOffice_UserName_ID"))).sendKeys(obj.get(0).get(0));
		driver.findElement(By.id(Pro.getProperty("BackOffice_Password_ID"))).sendKeys(obj.get(0).get(1));
		driver.findElement(By.id(Pro.getProperty("BackOffice_Login_ID"))).click();


	}

	@And("^Click on Revenue Accounting System > Revenue Control Accounts > Tax Receivable Control Account$")
	public void Access_tax_receivable_control_account_page() throws Throwable {

		SeleniumDriver.waitForPageToLoad();
		driver.findElement(By.xpath(Pro.getProperty("RevenueAccounting_Dropdown_XPATH"))).click();
		driver.findElement(By.xpath("//*[@id=\"MenuForm:j_idt29\"]/ul/li[3]/ul/li[2]")).click();
		driver.findElement(By.xpath("//*[@id=\"sub1\"]/ul/li[3]")).click();

	}

	@Then("^Verify The input fields \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\"$")
	public void verify_the_input_fields(String TaxType, String TaxOffice, String TransactionType, String DateFrom, String DateTo, String OpeningBalance, String ClosingBalance, String SearchButton) throws Throwable {
		SeleniumDriver.waitForPageToLoad();
		String Input_fields[] = {TaxType,TaxOffice,TransactionType, DateFrom,DateTo, OpeningBalance, ClosingBalance, SearchButton};
		for(int i=0; i < Input_fields.length; i++)
		{
			WebElement form_element = driver.findElement(By.id(Input_fields[i]));
			if(form_element.isDisplayed())
			{
				Assert.assertTrue(true);

			}
			else{
				Assert.fail();
			}

		}

	}

	@And("^Click on Revenue Accounting System > Revenue Control Accounts > Total Revenue Account$")
	public void click_on_revenue_accounting_system_revenue_control_accounts_total_revenue_account() throws Throwable {
		SeleniumDriver.waitForPageToLoad();
		driver.findElement(By.xpath(Pro.getProperty("RevenueAccounting_Dropdown_XPATH"))).click();
		driver.findElement(By.xpath("//*[@id=\"MenuForm:j_idt29\"]/ul/li[3]/ul/li[2]")).click();
		driver.findElement(By.xpath("//*[@id=\"sub1\"]/ul/li[4]")).click();
	}

	@Then("^Verify existence of table columns \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" in table$")
	public void verify_existence_of_table_columns_in_table(String Date, String TaxOfficeColumn, String TaxPayerName, String Tin, String TransactionDescription, String TaxTypeColumn, String DocumentReference, String Debit, String Credit, String Balance) throws Throwable {

		String table_columns[] = {Date,TaxOfficeColumn,TaxPayerName,Tin,TransactionDescription,TaxTypeColumn ,DocumentReference ,Debit ,Credit ,Balance};
		for(int i=0; i < table_columns.length; i++)
		{
			WebElement column = driver.findElement(By.xpath("//span[contains(text(),'" + table_columns[i] + "')]"));
			if(column.isDisplayed())
			{
				Assert.assertTrue(true);
				System.out.println("Table column '"+table_columns[i]+"' has been found");

			}
			else{
				Assert.fail();
				System.out.println("Table column '"+table_columns[i]+"' not found");
			}

		}


	}

	@Then("^Verify The input fields \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" in revenue account$")
	public void verify_the_input_fields_in_revenue_account(String TaxOffice, String DateFrom, String DateTo, String SearchButton) throws Throwable {
		SeleniumDriver.waitForPageToLoad();
		String Input_fields[] = {TaxOffice, DateFrom,DateTo, SearchButton};
		for(int i=0; i < Input_fields.length; i++)
		{
			WebElement form_element = driver.findElement(By.id(Input_fields[i]));
			if(form_element.isDisplayed())
			{
				Assert.assertTrue(true);
			}
			else{
				Assert.fail();
			}

		}
	}

	@Then("^Verify existence of table columns \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" in revenue account table$")
	public void verify_existence_of_table_columns_in_revenue_account_table(String TaxOfficeColumn, String TransactionDescription, String Debit, String Credit, String Balance) throws Throwable {
		String table_columns[] = {TaxOfficeColumn, TransactionDescription, Debit ,Credit ,Balance};
		for(int i=0; i < table_columns.length; i++)
		{
			WebElement column = driver.findElement(By.xpath("//span[contains(text(),'" + table_columns[i] + "')]"));
			if(column.isDisplayed())
			{
				Assert.assertTrue(true);
				System.out.println("Table column in total revenue account '"+table_columns[i]+"' has been found");

			}
			else{
				Assert.fail();
				System.out.println("Table column '"+table_columns[i]+"' not found in total revenue account table");
			}

		}
	}


}