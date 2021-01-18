package Steps.backup.Perform_Credit_Allocation;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.BaseClass;
import utils.SeleniumDriver;

import java.io.FileInputStream;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;


public class Perform_Credit_Allocation extends BaseClass{

		public Properties Pro;

		public Double CurrentOutstandingLiability;
		public Double UnallocatedCreditAmount;

		//we will be deducting 1,000
		public Double AmountDeductedFromUnallocatedCredit = 1000.00;
		public String ReferenceNumber;
		
		@Before(order=0)
		public void method1()throws Exception
		{
		   // this.S=S;
			Pro=new Properties();
			FileInputStream fip= new FileInputStream(".\\src\\test\\resources\\Objects\\object.properties");
			Pro.load(fip);
		}

		@After(order=0)
		public void AfterSelenium()
		{
			driver.quit();
		}
	    

	    @Given("^Open trips URL$")
	    public void loadTripsLink() throws Throwable {
			driver = BaseClass.getDriver();
            driver.get(Pro.getProperty("MRA_BackOffice_URL"));
            driver.manage().window().maximize();
	    	
	    }

	    @Then("^Login as Revenue Officer$")
	    public void login_as_revenue_officer(DataTable data) throws Throwable {
	        
	    	List<List<String>> obj = data.asLists();
	  
	    	driver.findElement(By.id(Pro.getProperty("BackOffice_UserName_ID"))).clear();
	    	driver.findElement(By.id(Pro.getProperty("BackOffice_UserName_ID"))).sendKeys(obj.get(0).get(0));
	    	driver.findElement(By.id(Pro.getProperty("BackOffice_Password_ID"))).clear();
	    	driver.findElement(By.id(Pro.getProperty("BackOffice_Password_ID"))).sendKeys(obj.get(0).get(1));
	    	driver.findElement(By.id(Pro.getProperty("BackOffice_Login_ID"))).click();
	        
	    	
	    }

		@And("^Click on Taxpayer accounting > Manage Credit Allocation$")
		public void click_on_taxpayer_accounting_manage_credit_allocation() throws Throwable {

			SeleniumDriver.waitForPageToLoad();
			driver.findElement(By.xpath("//*[@id=\"MenuForm:j_idt29\"]/ul/li[2]")).click();
			driver.findElement(By.xpath("//*[@id=\"MenuForm:j_idt29\"]/ul/li[2]/ul/li[7]")).click();

		}

	    @Then("^Verify fields \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" in Credit Allocation$")
		public void verify_fields_in_credit_allocation_page(String Tin, String TaxType, String Add, String View, String Search, String Cancel) throws Throwable {

			SeleniumDriver.waitForPageToLoad();
			String Input_fields[] = {TaxType,Tin,Add, View, Search, Cancel};
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

		@Then("^Verify table columns \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\"$")
		public void verify_table_columns_in_credit_allocation_page(String TaxTypeColumn, String UnallocatedCreditPeriod, String DocumentTypeUnallocatedCredit, String UnallocatedCreditAmount, String OutstandingLiabilityPeriod, String DocumentTypeOutstandingLiability, String OutstandingLiabilityAmount) throws Throwable {

			SeleniumDriver.waitForPageToLoad();
			String table_columns[] = {UnallocatedCreditPeriod ,DocumentTypeUnallocatedCredit ,UnallocatedCreditAmount ,OutstandingLiabilityPeriod, DocumentTypeOutstandingLiability, OutstandingLiabilityAmount };
			for(int i=0; i < table_columns.length; i++)
			{
				WebElement column = driver.findElement(By.xpath("//span[contains(text(),'" + table_columns[i] + "')]"));
				if(column.isDisplayed())
				{
					Assert.assertTrue(true);
					System.out.println("Table column '"+table_columns[i]+"' has been found");

				}
				else{

					System.out.println("Table column '"+table_columns[i]+"' not found");
					Assert.fail();
				}

			}
		}

		@Then("^Click add button$")
		public void click_add_button(){

			SeleniumDriver.waitForPageToLoad();
			driver.findElement(By.id(Pro.getProperty("Add_Button_ID"))).click();

		}

		@Then("^Verify input field \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\"  in credit allocation fields$")
		public void verify_input_field_in_credit_allocation_fields(String TinInput, String NameInput, String TaxTypeInput) throws Throwable {

			SeleniumDriver.waitForPageToLoad();
			String Input_fields[] = {TinInput, NameInput, TaxTypeInput};
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

		@Then("^Click find button$")
	    public void clickFindButton() throws Throwable
		{
			SeleniumDriver.waitForPageToLoad();
            driver.findElement(By.id(Pro.getProperty("Find_Tin_Button_ID"))).click();
		}

		@Then("^Click save$")
		public void click_save() throws Throwable {


            SeleniumDriver.waitForPageToLoad();
            driver.findElement(By.id(Pro.getProperty("Credit_Allocation_Save_Button_ID"))).click();


		}

		@Then("^verify error message \"([^\"]*)\"$")
		public void verify_error_message(String ErrorMessage) throws Throwable {

			Thread.sleep(8000);
			WebElement successMessage = driver.findElement(By.xpath("//span[contains(text(),'" + ErrorMessage + "')]"));

			if (successMessage.isDisplayed()) {
				System.out.println("Error message ('" +ErrorMessage+ "') has been displayed");
				Assert.assertTrue(true);
			} else {

				Assert.fail("Error message not shown");

			}

		}

	@Then("^Shift focus to modal$")
	public void shift_focus_to_modal() throws Throwable {
		Thread.sleep(5000);
		WebElement Iframe = driver.findElement(By.tagName("iframe"));
		driver.switchTo().frame(Iframe);
	}

	@Then("^enter tin \"([^\"]*)\" and click search$")
	public void enter_tin_and_click_search(String Tin) throws Throwable {
        Thread.sleep(3000);
        driver.findElement(By.id(Pro.getProperty("Find_Entity_Tin_Input_ID"))).sendKeys(Tin);
        driver.findElement(By.id(Pro.getProperty("Find_Entity_Tin_Search_Button_ID"))).click();
	}

	@Then("^select tax type \"([^\"]*)\"$")
	public void select_tax_type(String TaxType) throws Throwable {
		Thread.sleep(4000);
		driver.findElement(By.xpath(Pro.getProperty("Tax_Type_Dropdown_XPATH"))).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//li[contains(text(),'"+TaxType+"')]")).click();
	}

	@Then("^Click suspense radio button under unallocated credit balance$")
	public void click_suspense_radio_button_under_unallocated_credit_balance() throws Throwable {

		Thread.sleep(3000);
        driver.findElement(By.xpath(Pro.getProperty("Unallocated_Credit_Suspense_XPATH"))).click();
	}

	@Then("^Select transaction with document \"([^\"]*)\" under unallocated credit$")
	public void SelectTransactionUnderUnallocatedCredit(String DocumentType) throws Throwable {

		Thread.sleep(3000);

		driver.findElement(By.id(Pro.getProperty("Unallocated_Credit_Select_ID"))).click();
		Thread.sleep(3000);
		WebElement Iframe = driver.findElement(By.tagName("iframe"));
		driver.switchTo().frame(Iframe);

		//select document type
		driver.findElement(By.xpath(Pro.getProperty("Document_Type_Dropdown_XPATH"))).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//li[contains(text(),'"+DocumentType+"')]")).click();
		Thread.sleep(2000);
        driver.findElement(By.id(Pro.getProperty("Find_Entity_Tin_Search_Button_ID"))).click();
		driver.findElement(By.id(Pro.getProperty("Find_Entity_Tin_Search_Button_ID"))).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath(Pro.getProperty("Find_Business_Transaction_First_Table_Row_XPATH"))).click();
		Thread.sleep(1000);

		driver.findElement(By.id(Pro.getProperty("Continue_Button_ID"))).click();

		Thread.sleep(5000);
		UnallocatedCreditAmount = Double.parseDouble(driver.findElement(By.id("CreditAllocation:crBalance_input")).getAttribute("value").replaceAll(",",""));
	}


	@Then("^Click suspense radio button under outstanding liability$")
	public void click_suspense_radio_button_under_outstanding_liability() throws Throwable {
		Thread.sleep(3000);
		driver.findElement(By.xpath(Pro.getProperty("Outstanding_Liability_Suspense_XPATH"))).click();
	}

	@Then("^Select transaction with document \"([^\"]*)\" under outstanding liability$")
	public void SelectTransactionUnderOutstandingLiability(String DocumentType) throws Throwable {

		Thread.sleep(5000);

		driver.findElement(By.id(Pro.getProperty("Outstanding_Liability_Select_iD"))).click();
		Thread.sleep(3000);
		WebElement Iframe = driver.findElement(By.tagName("iframe"));
		driver.switchTo().frame(Iframe);

		//select document type
		driver.findElement(By.xpath(Pro.getProperty("Document_Type_Dropdown_XPATH"))).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//li[contains(text(),'"+DocumentType+"')]")).click();
		Thread.sleep(2000);
		driver.findElement(By.id(Pro.getProperty("Find_Entity_Tin_Search_Button_ID"))).click();
		driver.findElement(By.id(Pro.getProperty("Find_Entity_Tin_Search_Button_ID"))).click();
		Thread.sleep(3000);

		String balanceInput = driver.findElement(By.id("CreditAllocation:drBalance_input")).getAttribute("value").replaceAll(",","");
		CurrentOutstandingLiability = Double.parseDouble(balanceInput);

	}

	@Then("^populate allocated amount field$")
	public void populate_allocated_amount_field(){

        //we will populate using a quarter of the amount
		driver.findElement(By.id("CreditAllocation:allocatedAmount_input")).clear();
		driver.findElement(By.id("CreditAllocation:allocatedAmount_input")).sendKeys(AmountDeductedFromUnallocatedCredit.toString());

	}

	@Then("^Click submit$")
	public void click_submit(){

		driver.findElement(By.id("CreditAllocation:SaveCA")).click();

	}

	//Confirm the success message
	@Then("^Confirm saved success message \"([^\"]*)\"$")
	public void confirm_saved_success_message(String SuccessMessage) throws Throwable {
		SeleniumDriver.waitForPageToLoad();
		Thread.sleep(4000);
		WebElement successMessage = driver.findElement(By.xpath("//span[contains(text(),'" + SuccessMessage + "')]"));

		if (successMessage.isDisplayed()) {
			System.out.println("Success message ('" + SuccessMessage + "') has been displayed");
			Assert.assertTrue(true);
		} else {
			Assert.fail();

		}
	}

	@Then("^Obtain reference number \"([^\"]*)\"$")
	public void split_string_to_obtain_reference_number(String SuccessMessage)
	{
		//get full message
		String FullMessage = driver.findElement(By.xpath("//span[contains(text(),'" + SuccessMessage + "')]")).getText();
		System.out.println(FullMessage);
		//Processing Completed - Reference Number - CRAL/000001959/2020

		ReferenceNumber = FullMessage.substring(41);
		System.out.println(ReferenceNumber);

	}

	@Then("^Logout of trips and close browser$")
	public void logout_of_trips_and_close_browser(){

			SeleniumDriver.tearDown();

	}

	@Then("^Open CRM and close modal$")
	public void open_crm_and_close_modal() throws Throwable {
        driver = BaseClass.getDriver();
	    driver.get(Pro.getProperty("CRM_URL"));
		driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
		WebElement  specificframe= (driver.findElement(By.id(Pro.getProperty("CRM_ExploreCrmWindow_Frame__ID"))));
		driver.switchTo().frame(specificframe);
		WebDriverWait CloseWindow=new WebDriverWait(driver,60);
		CloseWindow.until(ExpectedConditions.elementToBeClickable(By.id(Pro.getProperty("CRM_ExploreCrmWindow_Frame_Close_ID")))).click();
	}

	@Then("^Click on accounting application link$")
	public void click_on_accounting_application_link() throws Throwable {

		driver.findElement(By.xpath(Pro.getProperty("Cases_Management_Dropdown_XPATH"))).click();
		Thread.sleep(2000);
		driver.findElement(By.id(Pro.getProperty("Accounting_Application_ID"))).click();

	}

	@Then("^switch to frame$")
	public void switch_to_frame() throws Throwable {
		driver.switchTo().defaultContent();
		WebDriverWait wait=new WebDriverWait(driver, 30);
		WebElement specificframe = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(Pro.getProperty("NextStage_Frame_ID"))));
		driver.switchTo().frame(specificframe);
		Thread.sleep(3000);

	}

	@Then("^search for reference number$")
	public void search_for_reference_number() throws Throwable {

		Thread.sleep(3000);
		driver.findElement(By.id(Pro.getProperty("Search_Field_ID"))).sendKeys(ReferenceNumber);
		driver.findElement(By.id(Pro.getProperty("Search_Field_Submit_ID"))).click();
	}

	@Then("^Click on reference number$")
	public void click_on_reference_number(){

		WebElement elementLocator = driver.findElement(By.xpath(Pro.getProperty("CaseManagement_Queue_Select_ReffNo_XPATH")));

		Actions actions = new Actions(driver);
		actions.doubleClick(elementLocator).perform();

		driver.switchTo().defaultContent();

	}

	@Then("^approve transaction$")
	public void approve_transaction() throws Throwable {
		driver.switchTo().frame("contentIFrame1");
		Thread.sleep(15000);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		Actions action=new Actions(driver);
		WebElement Outcome=driver.findElement(By.id(Pro.getProperty("Taxpayer_Accounting_Approval_Outcome_ID")));
		WebElement hasLoaded= driver.findElement(By.id("header_process_tbg_approvaloutcome_lock"));

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Thread.sleep(7000);
		if(hasLoaded.isDisplayed()) {
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		}else {
			action.doubleClick(Outcome).build().perform();
			Outcome.click();
			action.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();
		}
	}

	@And("^Reject transaction$")
	public void RejectTransaction() throws Throwable {
		driver.switchTo().frame("contentIFrame1");
		Thread.sleep(10000);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		Actions action=new Actions(driver);
		WebElement Outcome=driver.findElement(By.id(Pro.getProperty("Taxpayer_Accounting_Approval_Outcome_ID")));
		WebElement hasLoaded= driver.findElement(By.id("header_process_tbg_approvaloutcome_lock"));

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Thread.sleep(7000);
		if(hasLoaded.isDisplayed()) {
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		}else {
			action.doubleClick(Outcome).build().perform();
			Outcome.click();
			action.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();
		}
	}

	@And("^clicks Decline from the dropdown$")
	public void clicks_Decline_from_the_dropdown() throws Throwable {
		driver.switchTo().frame("contentIFrame1");
		Thread.sleep(10000);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		Actions action=new Actions(driver);
		WebElement Outcome=driver.findElement(By.id(Pro.getProperty("Taxpayer_Accounting_Approval_Outcome_ID")));
		WebElement hasLoaded= driver.findElement(By.id("header_process_tbg_approvaloutcome_lock"));

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Thread.sleep(7000);
		if(hasLoaded.isDisplayed()) {
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		}else {
			action.doubleClick(Outcome).build().perform();
			Outcome.click();
			action.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();
		}
	}

	@Then("^Enter Outcome Notes \"([^\"]*)\"$")
	public void enter_Outcome_Notes(String Notes) throws Throwable {
		Thread.sleep(5000);
		Actions action1 = new Actions(driver);
		WebElement element1=driver.findElement(By.id((Pro.getProperty("Individual_NextStage_RefNum_Reject_OutComeNotes_ID"))));
		action1.sendKeys(element1, Notes).build().perform();
		Thread.sleep(5000);
	}

	@Then("^Enter Outcome Reason for Taxpayer accounting$")
	public void enter_Outcome_Reason_for_Taxpayer_accounting() throws Throwable {
		WebElement specificframe=driver.findElement(By.id("WebResource_AccountingRejectionDataWebResource"));
		driver.switchTo().frame(specificframe);

		WebElement dropDown = driver.findElement(By.id("viewoption"));
		dropDown.click();

		driver.findElement(By.xpath("//option[text()='Credit can not be allocated']")).click();

	}

	@Then("^Click save CRM$")
	public void ClickSaveCRM() throws Throwable
	{
		driver.switchTo().defaultContent();
		driver.findElement(By.id("tbg_accountingapplication|NoRelationship|Form|Mscrm.Form.tbg_accountingapplication.Save")).click();
//    	driver.findElement(By.id("tbg_accountingapplication|NoRelationship|Form|Mscrm.Form.tbg_accountingapplication.Save")).click();
//    	driver.findElement(By.xpath(Pro.getProperty("//*[@id=\"tbg_accountingapplication|NoRelationship|Form|Mscrm.Form.tbg_accountingapplication.Save\"]"))).click();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}

	@Then("^Application Account Adjustment status should be \"([^\"]*)\"$")
	public void Verify_status_from_CRM(String Status) throws Throwable {
		driver.switchTo().frame("contentIFrame1");
		Thread.sleep(5000);
		String text=driver.findElement(By.xpath("//*[@id='Status_label']")).getText();
		System.out.println(text);
		if(text.contains(Status))
		{
			Assert.assertTrue("Approved",true);
		}
		else
		{
			Assert.fail("Approval failed");
		}
		Thread.sleep(2000);
	}

	@Then("^Click clear button \"([^\"]*)\" and verify field \"([^\"]*)\" has been cleared$")
	public void click_clear_button_and_verify_field_has_been_cleared(String Button, String Field) throws Throwable {

			Thread.sleep(5000);
			driver.findElement(By.id(Button)).click();
			String Amount = driver.findElement(By.id(Field)).getAttribute("value");
			if(Amount.length()>0)
			{
				Assert.fail();
			}
			else{
				Assert.assertTrue(true);
			}

	}

	@Then("^input tin \"([^\"]*)\"$")
	public void input_tin(String tin) throws Throwable {

		SeleniumDriver.waitForPageToLoad();
		driver.findElement(By.id("SearchForm:TIN")).sendKeys(tin);

	}

	@Then("^Click search$")
	public void click_search() throws Throwable {

		driver.findElement(By.id("SearchForm:j_idt42")).click();

	}

	@Then("^Select first table row and click view$")
	public void select_first_table_row_and_click_view() throws Throwable {

		Thread.sleep(5000);
		driver.findElement(By.xpath("//*[@id=\"SearchForm:resultsDataTable_data\"]/tr[2]/td[1]")).click();
		driver.findElement(By.id("SearchForm:j_id11")).click();

	}

	@Then("^Verify fields are readonly$")
	public void verify_fields_are_readonly() throws Throwable {
         SeleniumDriver.waitForPageToLoad();
         Thread.sleep(3000);
         WebElement TinInput = driver.findElement(By.id("CreditAllocation:TIN"));
         if(TinInput.isEnabled())
		 {
		 	Assert.assertFalse("Field is not readonly",false);
		 }
         else{
         	Assert.assertTrue("Field is readonly",true);
		 }
	}

	@Then("^Close browser$")
	public void closeBrowser()
	{
		driver.close();
	}
}





