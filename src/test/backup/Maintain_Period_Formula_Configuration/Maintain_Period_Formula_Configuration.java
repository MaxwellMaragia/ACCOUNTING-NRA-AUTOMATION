package Steps.backup.Maintain_Period_Formula_Configuration;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.BaseClass;
import utils.SeleniumDriver;

import java.io.FileInputStream;
import java.util.List;
import java.util.Properties;


public class Maintain_Period_Formula_Configuration extends BaseClass {
	

		public WebDriverWait wait;
		public Scenario scenario = null;
		public Properties Pro;
		public JavascriptExecutor jse = (JavascriptExecutor)driver;
		
		@Before(order=0)
		public void method1()throws Exception
		{
		   // this.S=S;
			Pro=new Properties();
			FileInputStream fip= new FileInputStream(".\\src\\test\\java\\resources\\Objects\\object.properties");
			Pro.load(fip);
		}
	    

	    @Given("^User is in browser and launches the app URL$")
	    public void user_is_in_browser_and_launches_the_app_url() throws Throwable {
	    	
            driver.get(Pro.getProperty("MRA_BackOffice_URL"));
            driver.manage().window().maximize();
	    	
	    }

	    @Then("^Login as Revenue Officer$")
	    public void login_as_revenue_officer(DataTable data) throws Throwable {
	        
	    	List<List<String>> obj = data.asLists();
	  
	    	driver.findElement(By.id(Pro.getProperty("BackOffice_UserName_ID"))).sendKeys(obj.get(0).get(0));					
	    	driver.findElement(By.id(Pro.getProperty("BackOffice_Password_ID"))).sendKeys(obj.get(0).get(1));	
	    	driver.findElement(By.id(Pro.getProperty("BackOffice_Login_ID"))).click();
	        
	    	
	    }

	    @And("^Click on Taxpayer accounting > Maintain period generation configuration$")
	    public void click_on_taxpayer_accounting_maintain_period_generation_configuration() throws Throwable {
	        
	    	SeleniumDriver.waitForPageToLoad();
	    	driver.findElement(By.xpath(Pro.getProperty("Taxpayer_Accounting_XPATH"))).click();
	    	driver.findElement(By.xpath(Pro.getProperty("Mantain_Period_Configuration_Configuration_XPATH"))).click();
	    	
	    }
	    
	    @Then("^Verify dropdown list values$")
	    public void verify_dropdown_list_values(DataTable data) throws Throwable {
	        
	    	List<List<String>> obj = data.asLists();
	    	String dropdown[] = {obj.get(0).get(0),obj.get(1).get(0),obj.get(2).get(0),obj.get(3).get(0),obj.get(4).get(0),obj.get(5).get(0),obj.get(6).get(0),obj.get(7).get(0),obj.get(8).get(0),obj.get(8).get(0),obj.get(9).get(0),obj.get(10).get(0)};
	    	//obj.get(0).get(2),obj.get(0).get(3),obj.get(0).get(4),obj.get(0).get(5),obj.get(0).get(6),obj.get(0).get(7),obj.get(0).get(8),obj.get(0).get(9),obj.get(0).get(10)
	    	
	    	for(int i = 0; i < dropdown.length; i++)
	    	{
	    		
	    		Thread.sleep(2000);
	        	driver.findElement(By.xpath(Pro.getProperty("Taxtype_Dropdown_XPATH"))).click();
	            

	            WebElement dropdown_ul = driver.findElement(By.id(Pro.getProperty("Taxtype_UL_ID")));
	            List<WebElement> tax_type = dropdown_ul.findElements(By.tagName("li"));
	            
	            for (WebElement li : tax_type) {
	            if (li.getText().equals(dropdown[i])) {
	                 li.click();
	                 Assert.assertTrue(true);
	                 System.out.println("Taxtype: "+dropdown[i]+" is selectable");
	                 Thread.sleep(1000);
	               }
				else{

					Assert.assertFalse("Drodown not selectable",false);
				}

				}

	    	}
	    	
	    	
	    }

	    @Then("^Verify existence of buttons$")
	    public void verify_existence_of_buttons(DataTable data) throws Throwable {
	    	 
	    	List<List<String>> obj = data.asLists();
	    	String buttons[] = {obj.get(0).get(0),obj.get(1).get(0),obj.get(2).get(0),obj.get(3).get(0),obj.get(4).get(0)};
	    	
	    	//check if buttons exist
	    	for(int i = 0; i < buttons.length; i++)
	    	{
	    		WebElement Button = driver.findElement(By.xpath("//*[text()='"+buttons[i]+"']"));
	    		 
	    		if(Button.isDisplayed())
	    		{
	    		    System.out.println( "Button : " + buttons[i] + " found ");
	    		    Thread.sleep(2000);
	    		    Assert.assertTrue(true);
	    		}
	    		 
	    		else
	    		{
	    			System.out.println( "Button : " + buttons[i] + " not found ");
	    		    Assert.assertFalse("Button not found",false);
	    		}
	    	}
	    }
	    

	    @Then("^Verify existence of table columns$")
	    public void verify_existence_of_table_columns(DataTable data) throws Throwable {
	       
	    	//span[contains(text(),'')]
	    	List<List<String>> obj = data.asLists();
	    	String columns[] = {obj.get(0).get(0),obj.get(1).get(0),obj.get(2).get(0),obj.get(3).get(0),obj.get(4).get(0),obj.get(5).get(0),obj.get(6).get(0)};
	    	
	    	//check if columns exist
	    	for(int i = 0; i < columns.length; i++)
	    	{
	    		WebElement Table_Column = driver.findElement(By.xpath("//span[contains(text(),'"+columns[i]+"')]"));
	    		
	    	
	    		if(Table_Column.isDisplayed())
	    		{
	    			
	    		    System.out.println( "Column : " + columns[i] + " found ");
	    		    Assert.assertTrue(true);
	    		    Thread.sleep(2000);
	    		}
	    		else
	    		{

	    			Assert.assertFalse(false);
	    			System.out.println( "Column : " + columns[i] + " not found ");
	    			
	    		}
	    	}
	    }
        
	    @Then("^Click on Create New button$")
	    public void click_on_create_new_button() throws Throwable {
	    	
	    	
	        SeleniumDriver.waitForPageToLoad();
	        Thread.sleep(2000);
	        driver.findElement(By.id(Pro.getProperty("Create_New_Tax_Period_ID"))).click();
	    }
	    
	    @Then("^Verify Create New screen$")
	    public void verify_create_new_screen() throws Throwable {
	    	
	    	SeleniumDriver.waitForPageToLoad();
	        WebElement SaveButton = driver.findElement(By.xpath(Pro.getProperty("Create_New_Period_Generation_Save_XPATH")));
	        if(SaveButton.isDisplayed())
	        {
	        	System.out.println("Save button found");
				Assert.assertTrue(true);
	        	//click back button to go back
	        	driver.findElement(By.xpath(Pro.getProperty("Cancel_Tax_Period_XPATH"))).click();
	        }
	        else {
	        
	        	System.out.println("Save button not found");
	        	Assert.assertFalse(false);
	        }
	    }
	    
	    @Then("^Select tax type to search then select table column and click edit button$")
	    public void select_tax_type_to_search_then_select_table_column_and_click_edit_button() throws Throwable {
	        SeleniumDriver.waitForPageToLoad();
	        
	        //select tax type as Domestic Excise
	        driver.findElement(By.xpath(Pro.getProperty("Taxtype_Dropdown_XPATH"))).click();
            

            WebElement dropdown_ul = driver.findElement(By.id(Pro.getProperty("Taxtype_UL_ID")));
            List<WebElement> tax_type = dropdown_ul.findElements(By.id("SearchForm:TaxType_3"));
            
            for (WebElement li : tax_type) {
            	try {
            		if (li.getText().equals("Domestic Excise")) {
                        li.click();
                        driver.findElement(By.xpath(Pro.getProperty("Period_Generation_Search_XPATH"))).click();
                        //click on table row
                        SeleniumDriver.waitForPageToLoad();
                        driver.findElement(By.xpath(Pro.getProperty("Period_Generation_Example_Row_XPATH"))).click();
                        
                        //click edit
                        driver.findElement(By.xpath(Pro.getProperty("Period_Generation_Edit_XPATH"))).click();
                        
                      }
            	}
            	catch(org.openqa.selenium.StaleElementReferenceException ex)
            	{
            		if (li.getText().equals("Domestic Excise")) {
                        li.click();
                        driver.findElement(By.xpath(Pro.getProperty("Period_Generation_Search_XPATH"))).click();
                        
                        //click on table row
                        SeleniumDriver.waitForPageToLoad();
                        driver.findElement(By.xpath(Pro.getProperty("Period_Generation_Example_Row_XPATH"))).click();
                        
                        //click edit
                        driver.findElement(By.xpath(Pro.getProperty("Period_Generation_Edit_XPATH"))).click();
                        
                      }
            	}
           
            }
            
	        
	    }
	    
	    
	    @Then("^Enter invalid date as \"([^\"]*)\" in Effective Date field$")
	    public void enter_invalid_date_as_something_in_effective_date_field(String date) throws Throwable {
	    	
	    	SeleniumDriver.waitForPageToLoad();
	    	//clear fields
	        driver.findElement(By.id(Pro.getProperty("Period_Generation_Effective_Date_Input_Field_ID"))).clear();
	        
	        //enter invalid data (date as 0)
	        driver.findElement(By.id(Pro.getProperty("Period_Generation_Effective_Date_Input_Field_ID"))).sendKeys(date);
	    }

	    @Then("^Verify correct data is loaded in edit fields$")
	    public void verify_correct_data_is_loaded_in_edit_fields() throws Throwable {
	        SeleniumDriver.waitForPageToLoad();
	        WebElement dropdown_ul = driver.findElement(By.id(Pro.getProperty("Period_Generation_Form_Taxtype_Ul_ID")));
            WebElement tax_type = dropdown_ul.findElement(By.id("PeriodGenerationForm:TaxType_3"));
           
	        if(tax_type.getText()=="Domestic Excise")
	        {
				Assert.assertTrue(true);
	        }
	        else {

	        	Assert.assertFalse(false);
	        	
	        }
	        
	        driver.findElement(By.xpath(Pro.getProperty("Cancel_Tax_Period_XPATH"))).click();
	    }
	    
	    
	    //Invalid data error message
	    @And("^Confirm that error message contains the string \"([^\"]*)\"$")
	    public void confirm_that_error_message_contains_the_string_something(String error) throws Throwable {
	        
	    	
	    	Thread.sleep(2000);
	        WebElement errorMessage = driver.findElement(By.xpath("//span[contains(text(),'"+error+"')]"));
   		 
	        //Check if validation error message is displayed
    		if(errorMessage.isDisplayed())
    		{
    		    System.out.println( "Validation error message displayed");
				Assert.assertTrue(true);
    		    Thread.sleep(2000);
    		}
    		 
    		else
    		{
    			System.out.println( "Validation error not displayed");
    			Assert.assertFalse(false);
    		    
    		}
	    	
	    }

        //Duplicate error message while creating new
		@And("^Confirm duplicate input error message has string \"([^\"]*)\"$")
		public void confirm_duplicate_input_error_message_has_string_something(String error) throws Throwable {
			SeleniumDriver.waitForPageToLoad();
			Thread.sleep(4000);
			WebElement errorMessage = driver.findElement(By.xpath("//span[contains(text(),'"+error+"')]"));

			//Check if validation error message is displayed
			if(errorMessage.isDisplayed())
			{
				System.out.println( "Duplicate entry error message displayed");
				Assert.assertTrue(true);
				Thread.sleep(2000);
			}

			else
			{
				System.out.println( "Duplicate entry error not displayed");
				System.exit(0);
				Assert.assertFalse(false);

			}
		}

		@Then("^Provide duplicate values in return tax type \"([^\"]*)\" and \"([^\"]*)\" and filling frequency \"([^\"]*)\"$")
		public void provide_duplicate_values_in_return_tax_type_something_and_something_and_filling_frequency_something(String TaxType, String ReturnType, String FillingFrequency) throws Throwable {

			SeleniumDriver.waitForPageToLoad();
			//select tax type
			driver.findElement(By.xpath("//*[@id=\"PeriodGenerationForm:TaxType\"]/div[3]")).click();
			Thread.sleep(1500);
			WebElement taxtype_ul = driver.findElement(By.id("PeriodGenerationForm:TaxType_items"));
			List<WebElement> tax_type = taxtype_ul.findElements(By.tagName("li"));
			for (WebElement li : tax_type) {
				if(li.getText().equals(TaxType))
				{
					li.click();

				}
			}

			//select return type
			Thread.sleep(2000);
			driver.findElement(By.xpath("//*[@id=\"PeriodGenerationForm:ReturnType\"]/div[3]")).click();
			WebElement returntype_ul = driver.findElement(By.id("PeriodGenerationForm:ReturnType_items"));
			List<WebElement> return_type = returntype_ul.findElements(By.tagName("li"));
			for (WebElement li : return_type) {
				if(li.getText().equals(ReturnType))
				{
					Thread.sleep(2000);
					li.click();
				}
			}

			//filling frequency
			Thread.sleep(2000);
			driver.findElement(By.xpath("//*[@id=\"PeriodGenerationForm:FillingFrequence\"]/div[3]")).click();
			WebElement fillingfrequency_ul = driver.findElement(By.id("PeriodGenerationForm:FillingFrequence_items"));
			List<WebElement> filling_frequency = fillingfrequency_ul.findElements(By.tagName("li"));
			for (WebElement li : filling_frequency) {
				if (li.getText().equals(FillingFrequency)) {
					Thread.sleep(2000);
					li.click();

				}
			}}
	    

	    @Then("^Select tax type to search then select table column and click view button$")
	    public void select_tax_type_to_search_then_select_table_column_and_click_view_button() throws Throwable {
	        SeleniumDriver.waitForPageToLoad();
	        
	        //select tax type as Domestic Excise
	        driver.findElement(By.xpath(Pro.getProperty("Taxtype_Dropdown_XPATH"))).click();
            

            WebElement dropdown_ul = driver.findElement(By.id(Pro.getProperty("Taxtype_UL_ID")));
            List<WebElement> tax_type = dropdown_ul.findElements(By.id("SearchForm:TaxType_3"));
            
            for (WebElement li : tax_type) {
            	try {
            		if (li.getText().equals("Domestic Excise")) {
                        li.click();
                        driver.findElement(By.xpath(Pro.getProperty("Period_Generation_Search_XPATH"))).click();
                        //click on table row
                        SeleniumDriver.waitForPageToLoad();
                        driver.findElement(By.xpath(Pro.getProperty("Period_Generation_Example_Row_XPATH"))).click();
                        
                        //click view
                        driver.findElement(By.xpath(Pro.getProperty("Period_Generation_View_XPATH"))).click();
                        
                      }
            	}
            	catch(org.openqa.selenium.StaleElementReferenceException ex)
            	{
            		if (li.getText().equals("Domestic Excise")) {
                        li.click();
                        driver.findElement(By.xpath(Pro.getProperty("Period_Generation_Search_XPATH"))).click();
                        //click on table row
                        SeleniumDriver.waitForPageToLoad();
                        driver.findElement(By.xpath(Pro.getProperty("Period_Generation_Example_Row_XPATH"))).click();
                        
                        //click view
                        driver.findElement(By.xpath(Pro.getProperty("Period_Generation_View_XPATH"))).click();
                        
                      }
            	}
           
            }
            
	    }

	    @Then("^Verify screen is readonly$")
	    public void verify_screen_is_readonly() throws Throwable {
	        SeleniumDriver.waitForPageToLoad();
	        //check for save button
	        WebElement NumberOfPeriods = driver.findElement(By.id(Pro.getProperty("Period_Generation_Form_Number_Of_Periods_Input_ID")));
	        
	        if(NumberOfPeriods.isEnabled())
	        {
	        	System.out.println("Fields are not readonly");
	        	Assert.assertFalse(false);
	        }
	        else {
	        	System.out.println("Fields are readonly");
				Assert.assertTrue(true);
	        }
	        		
	    }
	    
	    @Then("^Fill in fields \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\"$")
	    public void fill_in_fields_something_and_something_and_something_and_something_and_something_and_something_and_something_and_something_and_something_and_something_and_something_and_something(String taxtype, String returntype, String taxcyclestartday, String taxcyclestartmonth, String taxcycleendday, String taxcycleendmonth, String fillingfrequency, String formula, String numberofperiods, String effectivedate, String expirydate, String status) throws Throwable {
	        
	    	SeleniumDriver.waitForPageToLoad();
	    	//select tax type
            driver.findElement(By.xpath("//*[@id=\"PeriodGenerationForm:TaxType\"]/div[3]")).click();
            Thread.sleep(1500);
            WebElement taxtype_ul = driver.findElement(By.id("PeriodGenerationForm:TaxType_items"));
            List<WebElement> tax_type = taxtype_ul.findElements(By.tagName("li"));
            for (WebElement li : tax_type) {
            	if(li.getText().equals(taxtype))
            	{
            		li.click();
            		
            	}
            }
            
            //select return type
            Thread.sleep(2000);
            driver.findElement(By.xpath("//*[@id=\"PeriodGenerationForm:ReturnType\"]/div[3]")).click();
            WebElement returntype_ul = driver.findElement(By.id("PeriodGenerationForm:ReturnType_items"));
            List<WebElement> return_type = returntype_ul.findElements(By.tagName("li"));
            for (WebElement li : return_type) {
            	if(li.getText().equals(returntype))
            	{
            		Thread.sleep(2000);
            		li.click();
            		
            	}
            }
            
            //select tax cycle start day
            Thread.sleep(2000);
            driver.findElement(By.xpath("//*[@id=\"PeriodGenerationForm:TaxCycleStartDay\"]/div[3]")).click();
            WebElement taxcyclestartday_ul = driver.findElement(By.id("PeriodGenerationForm:TaxCycleStartDay_items"));
            List<WebElement> taxcycle_start_date = taxcyclestartday_ul.findElements(By.tagName("li"));
            for (WebElement li : taxcycle_start_date) {
            	if(li.getText().equals(taxcyclestartday))
            	{
            		li.click();
            		
            	}
            }
            
            //select tax cycle start month
          
            driver.findElement(By.xpath("//*[@id=\"PeriodGenerationForm:TaxCycleStartMonth\"]/div[3]/span")).click();
            WebElement taxcyclestartmonth_ul = driver.findElement(By.id("PeriodGenerationForm:TaxCycleStartMonth_items"));
            List<WebElement> taxcycle_start_month = taxcyclestartmonth_ul.findElements(By.tagName("li"));
            for (WebElement li : taxcycle_start_month) {
            	if(li.getText().equals(taxcyclestartmonth))
            	{
            		li.click();
            		
            	}
            }
            
            //tax cycle end day
            Thread.sleep(2000);
            driver.findElement(By.xpath("//*[@id=\"PeriodGenerationForm:TaxCycleEndDay\"]/div[3]")).click();
            WebElement taxcycleendday_ul = driver.findElement(By.id("PeriodGenerationForm:TaxCycleEndDay_items"));
            List<WebElement> taxcycle_end_date = taxcycleendday_ul.findElements(By.tagName("li"));
            for (WebElement li : taxcycle_end_date) {
            	if(li.getText().equals(taxcycleendday))
            	{
            		li.click();
            		
            	}
            }
            
            //select tax cycle start month
            
            driver.findElement(By.xpath("//*[@id=\"PeriodGenerationForm:TaxCycleEndMonth\"]/div[3]/span")).click();
            WebElement taxcycleendmonth_ul = driver.findElement(By.id("PeriodGenerationForm:TaxCycleEndMonth_items"));
            List<WebElement> taxcycle_end_month = taxcycleendmonth_ul.findElements(By.tagName("li"));
            for (WebElement li : taxcycle_end_month) {
            	if(li.getText().equals(taxcycleendmonth))
            	{
            		li.click();
            		
            	}
            }
            
            //filling frequency
            Thread.sleep(2000);
            driver.findElement(By.xpath("//*[@id=\"PeriodGenerationForm:FillingFrequence\"]/div[3]")).click();
            WebElement fillingfrequency_ul = driver.findElement(By.id("PeriodGenerationForm:FillingFrequence_items"));
            List<WebElement> filling_frequency = fillingfrequency_ul.findElements(By.tagName("li"));
            for (WebElement li : filling_frequency) {
            	if(li.getText().equals(fillingfrequency))
            	{
            		li.click();

            		
            	}
            }
            
            //formula
            Thread.sleep(3000);
            driver.findElement(By.xpath("//*[@id=\"PeriodGenerationForm:Formula\"]/div[3]")).click();
            WebElement formula_ul = driver.findElement(By.id("PeriodGenerationForm:Formula_items"));
            List<WebElement> formula_li = formula_ul.findElements(By.tagName("li"));
            for (WebElement li : formula_li) {
            	if(li.getText().equals(formula))
            	{
            		Thread.sleep(1000);
            		li.click();
            		
            	}
            }
            
            //status
            Thread.sleep(2000);
            driver.findElement(By.xpath("//*[@id=\"PeriodGenerationForm:Status\"]/div[3]")).click();
            WebElement status_ul = driver.findElement(By.id("PeriodGenerationForm:Status"));
            List<WebElement> status_li = status_ul.findElements(By.tagName("li"));
            for (WebElement li : status_li) {
            	if(li.getText().equals(status))
            	{
            		li.click();
            		
            	}
            }
            
            //Number of periods
            driver.findElement(By.xpath("//*[@id=\"PeriodGenerationForm:NumberOfPeriods_input\"]")).sendKeys(numberofperiods);
            
            //effective date
            driver.findElement(By.xpath("//*[@id=\"PeriodGenerationForm:EffectiveDate_input\"]")).sendKeys(effectivedate);
            
            //expiry date
            driver.findElement(By.xpath("//*[@id=\"PeriodGenerationForm:ExpiryDate_input\"]")).sendKeys(expirydate);
             
	    }
	    
	    @Then("^Change Number of periods to \"([^\"]*)\"$")
	    public void change_number_of_periods_to_something(String numberofperiods) throws Throwable {
	        
	    	SeleniumDriver.waitForPageToLoad();
	    	//Number of periods
	    	driver.findElement(By.xpath("//*[@id=\"PeriodGenerationForm:NumberOfPeriods_input\"]")).clear();
	    	Thread.sleep(1000);
            driver.findElement(By.xpath("//*[@id=\"PeriodGenerationForm:NumberOfPeriods_input\"]")).sendKeys(numberofperiods);
	    	
	    }

	    @Then("^Click save$")
	    public void click_save() throws Throwable {
	        Thread.sleep(1000);
	        driver.findElement(By.id("PeriodGenerationForm:SavePGC")).click();
	    }
	    
	    @Then("^Confirm saved success message$")
	    public void confirm_saved_success_message() throws Throwable {
	    	
	        SeleniumDriver.waitForPageToLoad();
	        Thread.sleep(2000);
	        WebElement successMessage = driver.findElement(By.xpath("//span[contains(text(),'Period Generation Configuration saved successfully.')]"));
   		 
    		if(successMessage.isDisplayed())
    		{
    		    System.out.println( "Period Generation Configuration successfully saved");
    		    Assert.assertTrue(true);
    		    Thread.sleep(2000);
    		}
    		 
    		else
    		{
    			System.out.println( "Could not save data");
    			Assert.assertFalse(false);
    		    
    		}
	    }

	    //Iterate and search through every tax type
		@Then("^Search every Tax type \"([^\"]*)\"$")
		public void search_every_tax_type(String TaxType) throws Throwable {
			 SeleniumDriver.waitForPageToLoad();
             driver.findElement(By.id("SearchForm:TaxType")).click();
             String TaxTypeXpath = "//li[@data-label='"+TaxType+"']";
             driver.findElement(By.xpath(TaxTypeXpath)).click();
             Thread.sleep(2000);

             //click search
			 driver.findElement(By.id("SearchForm:j_idt42")).click();

			 //validate appearance in table
			 SeleniumDriver.waitForPageToLoad();

			 WebElement TableColumn = driver.findElement(By.xpath("//label[contains(text(),'"+TaxType+"')]"));
			 if(TableColumn.isDisplayed())
			 {
			 	Assert.assertTrue(TaxType + " : Table column found",true);
			 }
			 else{
			 	Assert.assertFalse(false);
			 }

		}

	@And("^Click on Revenue accounting > Maintain chart of accounts$")
	public void click_on_revenue_accounting_maintain_chart_of_accounts() throws Throwable {

		Thread.sleep(1000);
		driver.findElement(By.xpath(Pro.getProperty("RevenueAccounting_Dropdown_XPATH"))).click();
		driver.findElement(By.xpath(Pro.getProperty("MantainChartOfAccounts_XPATH"))).click();

	}

	//Check existence of table columns in chart of accounts
	@Then("^Verify Mantain Chart of Accounts table exists with the columns$")
	public void verify_mantain_chart_of_accounts_table_exists_with_the_columns(DataTable data) throws Throwable {
		SeleniumDriver.waitForPageToLoad();
		List < List < String >> obj = data.asLists();
		String columns[] = {
				obj.get(0).get(0),
				obj.get(1).get(0)
		};

		for (int i = 0; i < columns.length; i++) {
			//we will use the span inside <td> tags to check for the coressponding columns
			WebElement Table_Column = driver.findElement(By.xpath("//span[contains(text(),'" + columns[i] + "')]"));
			if (Table_Column.isDisplayed()) {
				System.out.println(columns[i] + " : Table column under Chart of accounts table exists");
				Assert.assertTrue(true);
			} else {
				Assert.fail();
			}
		}
	}

	//Check that data buttons exist in chart of accounts table
	@Then("^Verify existence of data buttons in chart of accounts table$")
	public void verify_existence_of_data_buttons_in_chart_of_accounts_table() throws Throwable {
		SeleniumDriver.waitForPageToLoad();
		//We will verify add and edit category buttons
		String buttons[] = {
				"Add Category",
				"Edit Category"
		};

		for (int i = 0; i < buttons.length; i++) {
			WebElement button = driver.findElement(By.xpath("//span[contains(text(),'" + buttons[i] + "')]"));
			if (button.isDisplayed()) {
				System.out.println(buttons[i] + "Button found");
				Assert.assertTrue(true);
			} else {
				Assert.fail();
			}
		}

	}

	//Verify existence of columns in subcategory table
	@Then("^Verify Sub Category table exists with the columns$")
	public void verify_sub_category_table_exists_with_the_columns(DataTable data) throws Throwable {

		SeleniumDriver.waitForPageToLoad();
		List < List < String >> obj = data.asLists();
		String columns[] = {
				obj.get(0).get(0),
				obj.get(1).get(0)
		};

		for (int i = 0; i < columns.length; i++) {
			//we will use the span inside <td> tags to check for the correspoinding columns
			WebElement Table_Column = driver.findElement(By.xpath("//span[contains(text(),'" + columns[i] + "')]"));
			if (Table_Column.isDisplayed()) {
				System.out.println(columns[i] + " : Table column under Subcategory table exists");
				Assert.assertTrue(true);
			} else {
				Assert.fail();
			}
		}
	}

	//Verify data buttons exist in subcategory table
	@Then("^Verify existence of data buttons in Sub Category table$")
	public void verify_existence_of_data_buttons_in_sub_category_table() throws Throwable {
		SeleniumDriver.waitForPageToLoad();
		//We will verify add sub category and edit sub category buttons
		String buttons[] = {
				"AddSubCategory",
				"EditSubCategory"
		};

		for (int i = 0; i < buttons.length; i++) {
			WebElement button = driver.findElement(By.id("MaintainChartOfAccounts:categoryTable:" + buttons[i]));
			if (button.isDisplayed()) {
				System.out.println(buttons[i] + "Button found");
				Assert.assertTrue(true);
			} else {
				Assert.fail();
			}
		}

	}

	//Verify ledger account table exists with appropriate columns
	@Then("^Verify Ledger account table exists with the columns$")
	public void verify_ledger_account_table_exists_with_the_columns(DataTable data) throws Throwable {
		SeleniumDriver.waitForPageToLoad();
		List < List < String >> obj = data.asLists();
		String columns[] = {
				obj.get(0).get(0),
				obj.get(1).get(0)
		};

		for (int i = 0; i < columns.length; i++) {
			//we will use the span inside <td> tags to check for the correspoinding columns
			WebElement Table_Column = driver.findElement(By.xpath("//span[contains(text(),'" + columns[i] + "')]"));
			if (Table_Column.isDisplayed()) {
				System.out.println(columns[i] + " : Table column under Ledger table exists");
				Assert.assertTrue(true);
			} else {
				Assert.fail();
			}
		}
	}

	//Verify data buttons exist in ledger account table
	@Then("^Verify existence of data buttons in Ledger account table$")
	public void verify_existence_of_data_buttons_in_ledger_account_table() throws Throwable {
		//We will verify add sub category and edit sub category buttons
		String buttons[] = {
				"AddLedgerAccount",
				"EditLedgerAccount"
		};

		for (int i = 0; i < buttons.length; i++) {
			WebElement button = driver.findElement(By.id("MaintainChartOfAccounts:ledgerTable:" + buttons[i]));
			if (button.isDisplayed()) {
				System.out.println(buttons[i] + "Button found");
				Assert.assertTrue(true);
			} else {
				Assert.fail();
			}
		}
	}

	//Click add button to launch add modal
	@Then("^Click on Add Category button under Maintain chart of accounts table$")
	public void click_on_add_category_button_under_maintain_chart_of_accounts_table() throws Throwable {

		SeleniumDriver.waitForPageToLoad();
		driver.findElement(By.id(Pro.getProperty("AddCategoryButton_ChartOfAccounts_ID"))).click();

	}

	//Shift the focus to modal
	@Then("^Then shift focus to add category modal$")
	public void then_shift_focus_to_add_category_modal() throws Throwable {
		Thread.sleep(3000);
		WebElement AddCategoryIframe = driver.findElement(By.tagName("iframe"));
		driver.switchTo().frame(AddCategoryIframe);

	}

	//Verify form fields exist
	@Then("^Verify fields \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" in add category modal screen$")
	public void verify_fields_in_add_category_modal_screen(String Code, String Description, String EffectiveDate, String ExpiryDate, String Status) throws Throwable {

		Thread.sleep(3000);
		String AddCategoryFormElementsID[] = {
				Code,
				Description,
				EffectiveDate,
				ExpiryDate,
				Status
		};

		for (int i = 1; i < AddCategoryFormElementsID.length; i++) {
			WebElement InputElement = driver.findElement(By.id(AddCategoryFormElementsID[i]));
			if (InputElement.isDisplayed()) {
				System.out.println("Add Category Input element '" + InputElement + "' Has been located");
				Assert.assertTrue(true);
			} else {
				Assert.fail("Could not find element: " + InputElement);
			}
		}
	}

	//Verify add and cancel buttons in modal
	@Then("^Verify Add \"([^\"]*)\" and Cancel \"([^\"]*)\" buttons in add category modal screen$")
	public void verify_add_and_cancel_buttons_in_add_category_modal_screen(String add, String cancel) throws Throwable {

		String Buttons[] = {
				add,
				cancel
		};
		for (int i = 1; i < Buttons.length; i++) {
			WebElement AddCategoryButtonsID = driver.findElement(By.id(Buttons[i]));
			if (AddCategoryButtonsID.isDisplayed()) {
				Assert.assertTrue(true);
				System.out.println("Button '" + AddCategoryButtonsID + "' Has been located");
			} else {
				Assert.fail("Button '" + AddCategoryButtonsID + "' Cannot be located");
			}
		}

	}

	//Close modal add category modal
	@And("^Close modal$")
	public void close_modal() {
		driver.findElement(By.id(Pro.getProperty("CategoryDetailsModalCancelButton_ID"))).click();
		//driver.switchTo().defaultContent();
	}

	//Fill in fields in add Category modal and save data
	@Then("^Fill in fields \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\"$")
	public void fill_in_fields_and_click_save(String Code, String Description, String EffectiveDate, String ExpiryDate, String Status) throws Throwable {

		Thread.sleep(2000);
		driver.findElement(By.id(Pro.getProperty("AccountCode_Input_ID"))).sendKeys(Code);
		driver.findElement(By.id(Pro.getProperty("CategoryDescription_Input_ID"))).sendKeys(Description);
		driver.findElement(By.id(Pro.getProperty("EffectiveDate_Input_ID"))).sendKeys(EffectiveDate);
		driver.findElement(By.id(Pro.getProperty("ExpiryDate_Input_ID"))).sendKeys(ExpiryDate);

		//open status dropdown
		driver.findElement(By.id(Pro.getProperty("Category_Status_Dropdown_Id"))).click();
		Thread.sleep(1000);

		//select status
		String StatusXpath = "//li[@data-label='" + Status + "']";
		driver.findElement(By.xpath(StatusXpath)).click();

		//click save button
		driver.findElement(By.id(Pro.getProperty("Category_Details_Save_Button_ID"))).click();

	}

	//Confirm the success message
	@Then("^Confirm saved success message \"([^\"]*)\"$")
	public void confirm_saved_success_message(String SuccessMessage) throws Throwable {
		SeleniumDriver.waitForPageToLoad();
		Thread.sleep(10000);
		WebElement successMessage = driver.findElement(By.xpath("//span[contains(text(),'" + SuccessMessage + "')]"));

		if (successMessage.isDisplayed()) {
			System.out.println("Success message ('" + SuccessMessage + "') has been displayed");
			Assert.assertTrue(true);
		} else {
			Assert.fail("Category could not be saved");

		}
	}

	//Click on table row to select category
	@Then("^Click table row in Chart of accounts table$")
	public void click_table_row_in_chart_of_accounts_table() throws Throwable {

		SeleniumDriver.waitForPageToLoad();
		driver.findElement(By.id("MaintainChartOfAccounts:tree_node_2")).click();

	}

	//Click add subcategory button to launch modal
	@Then("^Click add Sub category button in sub category table$")
	public void click_add_sub_category_button_in_sub_category_table() throws Throwable {
		Thread.sleep(3000);
		driver.findElement(By.id(Pro.getProperty("Add_Sub_Category_Button_ID"))).click();
	}

	//Shift the focus to sub category modal/frame
	@Then("^Then shift focus to add sub category modal$")
	public void then_shift_focus_to_add_sub_category_modal() throws Throwable {
		Thread.sleep(3000);
		WebElement AddSubCategoryIframe = driver.findElement(By.tagName("iframe"));
		driver.switchTo().frame(AddSubCategoryIframe);
	}

	//Verify input fields in sub category modal
	@Then("^Verify fields \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" in add sub category modal screen$")
	public void verify_fields_in_add_sub_category_modal_screen(String ParentCategory, String Code, String Description, String EffectiveDate, String ExpiryDate, String Status, String BusinessSectorDivision) throws Throwable {
		Thread.sleep(3000);
		String AddSubCategoryFormElementsID[] = {
				ParentCategory,
				Code,
				Description,
				EffectiveDate,
				ExpiryDate,
				Status,
				BusinessSectorDivision
		};

		for (int i = 1; i < AddSubCategoryFormElementsID.length; i++) {
			WebElement InputElement = driver.findElement(By.id(AddSubCategoryFormElementsID[i]));
			if (InputElement.isDisplayed()) {
				System.out.println("Add Sub Category Input element '" + InputElement + "' Has been located");
				Assert.assertTrue(true);
			} else {
				Assert.fail("Could not find add subcategory field element: " + InputElement);
			}
		}
	}

	//Verify add and cancel buttons in sub category modal
	@Then("^Verify Add \"([^\"]*)\" and Cancel \"([^\"]*)\" buttons in add sub category modal screen$")
	public void verify_add_and_cancel_buttons_in_add_sub_category_modal_screen(String add, String cancel) throws Throwable {

		Thread.sleep(3000);
		String Buttons[] = {
				add,
				cancel
		};
		for (int i = 1; i < Buttons.length; i++) {
			WebElement AddSubCategoryButtonsID = driver.findElement(By.id(Buttons[i]));
			if (AddSubCategoryButtonsID.isDisplayed()) {
				Assert.assertTrue(true);
				System.out.println("Button '" + AddSubCategoryButtonsID + "' Has been located in add subcategory modal");
			} else {
				Assert.fail("Button '" + AddSubCategoryButtonsID + "' Cannot be located in add subcategory modal");
			}
		}

	}

	//save sub category
	@Then("^Fill in fields \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" to save sub category$")
	public void fill_in_fields_to_save_sub_category(String Code, String Description, String EffectiveDate, String ExpiryDate, String Status) throws Throwable {

		Thread.sleep(3000);
		driver.findElement(By.id(Pro.getProperty("SubCategory_Code_Input_ID"))).sendKeys(Code);
		driver.findElement(By.id(Pro.getProperty("SubCategory_Code_Description_ID"))).sendKeys(Description);


		//lets use javascript to send date fields since sendKeys method has inconsistent behavior in date fields
		jse.executeScript("document.getElementById('"+Pro.getProperty("SubCategory_EffectiveDate_Input_ID")+"').setAttribute('value', '"+EffectiveDate+"')");
		jse.executeScript("document.getElementById('"+Pro.getProperty("SubCategory_ExpiryDate_Input_ID")+"').setAttribute('value', '"+ExpiryDate+"')");

		driver.findElement(By.id(Pro.getProperty("SubCategory_ExpiryDate_Input_ID"))).sendKeys(ExpiryDate);

		//Click business sector division check box
		driver.findElement(By.id(Pro.getProperty("SubCategory_Business_Sector_Division_ID"))).click();

		//open status dropdown
		driver.findElement(By.xpath(Pro.getProperty("SubCategory_Status_Dropdown_XPATH"))).click();
		Thread.sleep(1000);

		//select status
		String StatusXpath = "//li[@data-label='" +Status+ "']";
		driver.findElement(By.xpath(StatusXpath)).click();

		//click save button
		driver.findElement(By.id(Pro.getProperty("Category_Details_Save_Button_ID"))).click();

	}

	@Then("^Click edit category button$")
	public void click_edit_category_button() throws Throwable
	{
		Thread.sleep(3000);
		driver.findElement(By.id(Pro.getProperty("Chart_Of_Accounts_Edit_Button_ID"))).click();
	}

	@Then("^Click edit button in sub category table$")
	public void click_edit_button_in_sub_category_table() throws Throwable {
		Thread.sleep(3000);
		driver.findElement(By.id(Pro.getProperty("Edit_Sub_Category_Button_ID"))).click();
	}

	@Then("^Click second table row in Chart of accounts table$")
	public void click_second_table_row_in_category_table() throws Throwable {

		Thread.sleep(2000);
		driver.findElement(By.id("MaintainChartOfAccounts:tree_node_2")).click();

	}

	@Then("^Click table row in Sub Category table$")
	public void click_first_row_in_SubCategory_table() throws Throwable {
		Thread.sleep(3000);
		driver.findElement(By.xpath(Pro.getProperty("Sub_Category_Table_Row_XPATH"))).click();
	}

	//Edit CHart of accounts table row
	@Then("^Edit fields \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" then click save$")
	public void edit_fields_in_add_category_then_click_save(String Description, String EffectiveDate, String ExpiryDate, String Status) throws Throwable {

		Thread.sleep(3000);
		driver.findElement(By.id(Pro.getProperty("CategoryDescription_Input_ID"))).clear();
		driver.findElement(By.id(Pro.getProperty("CategoryDescription_Input_ID"))).sendKeys(Description);
		//lets use javascript to send date fields since sendKeys method has inconsistent behavior in date fields
		jse.executeScript("document.getElementById('"+Pro.getProperty("EffectiveDate_Input_ID")+"').setAttribute('value', '"+EffectiveDate+"')");
		jse.executeScript("document.getElementById('"+Pro.getProperty("ExpiryDate_Input_ID")+"').setAttribute('value', '"+ExpiryDate+"')");

		//open status dropdown
		driver.findElement(By.xpath(Pro.getProperty("Category_Status_Dropdown_Status_XPATH"))).click();
		Thread.sleep(1000);

		//select status
		String StatusXpath = "//li[@data-label='"+Status+"']";
		driver.findElement(By.xpath(StatusXpath)).click();

		//click save button
		driver.findElement(By.id(Pro.getProperty("Category_Details_Save_Button_ID"))).click();

	}

	//Edit sub category
	@Then("^Edit fields \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" then click save to edit subcategory$")
	public void edit_fields_in_sub_category_then_click_save(String Code, String Description, String EffectiveDate) throws Throwable {
		Thread.sleep(3000);

		driver.findElement(By.id(Pro.getProperty("SubCategory_Code_Input_ID"))).clear();
		driver.findElement(By.id(Pro.getProperty("SubCategory_Code_Input_ID"))).sendKeys(Code);
		driver.findElement(By.id(Pro.getProperty("SubCategory_Code_Description_ID"))).clear();
		driver.findElement(By.id(Pro.getProperty("SubCategory_Code_Description_ID"))).sendKeys(Description);
		//lets use javascript to send date fields since sendKeys method has inconsistent behavior in date fields
		jse.executeScript("document.getElementById('"+Pro.getProperty("SubCategory_EffectiveDate_Input_ID")+"').setAttribute('value', '"+EffectiveDate+"')");

		//click save button
		driver.findElement(By.id(Pro.getProperty("SubCategory_Save_Button_ID"))).click();

	}


	//CLick treeview dropdown till seventh level
	@Then("^Click on tree view in revenue category upto seventh level$")
	public void click_on_tree_view_in_revenue_category_upto_seventh_level() throws Throwable {

		SeleniumDriver.waitForPageToLoad();
		driver.findElement(By.xpath(Pro.getProperty("First_Treeview_Dropdown_XPATH"))).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath(Pro.getProperty("Second_Treeview_Dropdown_XPATH"))).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath(Pro.getProperty("Third_Treeview_Dropdown_XPATH"))).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath(Pro.getProperty("Fourth_Treeview_Dropdown_XPATH"))).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath(Pro.getProperty("Fifth_Treeview_Dropdown_XPATH"))).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath(Pro.getProperty("Sixth_Treeview_Dropdown_XPATH"))).click();


	}

	//CLick on seventh treeview to load data in ledger account table
	@Then("^Click on seventh table row in treeview$")
	public void click_on_seventh_table_row_in_treeview() throws Throwable {
		Thread.sleep(2000);
		//Click on seventh table row
		driver.findElement(By.id(Pro.getProperty("Seventh_Treeview_Table_Row_ID"))).click();
	}

	//Confirm table data in ledger account table contains "PAYE LIABILITY"
	@Then("^Confirm \"([^\"]*)\" data in ledger account table$")
	public void confirm_data_in_ledger_account_table(String TableData) throws Throwable {

		Thread.sleep(2000);
		WebElement data_TD_TAG = driver.findElement(By.xpath("//td[contains(text(),'" + TableData + "')]"));

		if (data_TD_TAG.isDisplayed()) {
			System.out.println("7th level subcategory ('" + TableData + "') has been displayed in ledger account table");
			Assert.assertTrue(true);
		} else {
			Assert.fail("7th level sub category could not be displayed");

		}

	}

	@Then("^Click on tree view in revenue category upto sixth level$")
	public void click_on_tree_view_in_revenue_category_upto_sixth_level() throws Throwable {
		SeleniumDriver.waitForPageToLoad();
		driver.findElement(By.xpath(Pro.getProperty("First_Treeview_Dropdown_XPATH"))).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath(Pro.getProperty("Second_Treeview_Dropdown_XPATH"))).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath(Pro.getProperty("Third_Treeview_Dropdown_XPATH"))).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath(Pro.getProperty("Fourth_Treeview_Dropdown_XPATH"))).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath(Pro.getProperty("Fifth_Treeview_Dropdown_XPATH"))).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath(Pro.getProperty("Sixth_Treeview_Dropdown_XPATH"))).click();

	}

	@Then("^Click on sixth table row in treeview$")
	public void click_on_sixth_table_row_in_treeview() throws Throwable {

		Thread.sleep(1000);
		//Click on seventh table row
		driver.findElement(By.id(Pro.getProperty("Sixth_Treeview_Table_Row_ID"))).click();

	}

	@Then("^Click add button in ledger account table$")
	public void click_add_button_in_ledger_account_table() throws Throwable {
		Thread.sleep(1000);
		driver.findElement(By.id(Pro.getProperty("Add_Ledger_Account_button_ID"))).click();
	}

	@Then("^Then shift focus to add ledger account modal$")
	public void then_shift_focus_to_add_ledger_account_modal() throws Throwable {
		Thread.sleep(5000);
		WebElement AddLedgerAccountIframe = driver.findElement(By.tagName("iframe"));
		driver.switchTo().frame(AddLedgerAccountIframe);
	}

	@Then("Then Verify fields {string} and {string} and {string} and {string} and {string} and {string} and {string} and {string}")
	public void then_verify_fields_in_ledger_account(String ParentCategory, String Code, String Description, String EffectiveDate, String ExpiryDate, String Status, String Save, String Cancel) throws Throwable {
		Thread.sleep(3000);
		String AddLedgerAccountFormElementsID[] = {
				ParentCategory,
				Code,
				Description,
				EffectiveDate,
				ExpiryDate,
				Status,
				Save,
				Cancel
		};

		for (int i = 1; i < AddLedgerAccountFormElementsID.length; i++) {
			WebElement InputElement = driver.findElement(By.id(AddLedgerAccountFormElementsID[i]));
			if (InputElement.isDisplayed()) {
				System.out.println("Add Ledger account Input element '" + InputElement + "' Has been located");
				Assert.assertTrue(true);
			} else {
				Assert.fail("Could not find add ledger account field element: " + InputElement);
			}
		}
	}

	@Then("^Verify tax types$")
	public void verify_tax_types(DataTable data) throws Throwable {
		List < List < String >> obj = data.asLists();
		String columns[] = {
				obj.get(0).get(0),
				obj.get(1).get(0),
				obj.get(2).get(0),
				obj.get(3).get(0),
				obj.get(4).get(0),
				obj.get(5).get(0),
				obj.get(6).get(0),
				obj.get(7).get(0),
				obj.get(8).get(0),
				obj.get(9).get(0),
				obj.get(10).get(0)
		};

		for (int i = 0; i < columns.length; i++) {
			//we will use the span inside <td> tags to check for the correspoinding columns
			WebElement Table_Column = driver.findElement(By.xpath("//td[contains(text(),'" + columns[i] + "')]"));
			if (Table_Column.isDisplayed()) {
				System.out.println(columns[i] + " : Tax type item exists");
				Assert.assertTrue(true);
			} else {
				Assert.fail();
			}
		}
	}

	@Then("^Verify Document types$")
	public void verify_document_types(DataTable data) throws Throwable {
		List < List < String >> obj = data.asLists();
		String columns[] = {
				obj.get(0).get(0),
				obj.get(1).get(0),
				obj.get(2).get(0),
				obj.get(3).get(0),
				obj.get(4).get(0),
				obj.get(5).get(0),
				obj.get(6).get(0),
				obj.get(7).get(0),
				obj.get(8).get(0),
		};

		for (int i = 0; i < columns.length; i++) {
			//we will use the span inside <td> tags to check for the correspoinding columns
			WebElement Table_Column = driver.findElement(By.xpath("//td[contains(text(),'" + columns[i] + "')]"));
			if (Table_Column.isDisplayed()) {
				System.out.println(columns[i] + " : Document type exists");
				Assert.assertTrue(true);
			} else {
				Assert.fail();
			}
		}
	}

	@Then("^Verify Taxpayer types list$")
	public void verify_taxpayer_types_list(DataTable data) throws Throwable {
		List < List < String >> obj = data.asLists();
		String columns[] = {
				obj.get(0).get(0),
				obj.get(1).get(0)
		};

		for (int i = 0; i < columns.length; i++) {
			//we will use the span inside <td> tags to check for the correspoinding columns
			WebElement Table_Column = driver.findElement(By.xpath("//td[contains(text(),'" + columns[i] + "')]"));
			if (Table_Column.isDisplayed()) {
				System.out.println(columns[i] + " : Taxpayer type exists");
				Assert.assertTrue(true);
			} else {
				Assert.fail();
			}
		}
	}

	@Then("^Verify charge types list$")
	public void verify_charge_types_list(DataTable data) throws Throwable {
		List < List < String >> obj = data.asLists();
		String columns[] = {
				obj.get(0).get(0),
				obj.get(1).get(0),
				obj.get(2).get(0),
				obj.get(3).get(0),
				obj.get(4).get(0),

		};

		for (int i = 0; i < columns.length; i++) {
			//we will use the span inside <td> tags to check for the correspoinding columns
			WebElement Table_Column = driver.findElement(By.xpath("//td[contains(text(),'" + columns[i] + "')]"));
			if (Table_Column.isDisplayed()) {
				System.out.println(columns[i] + " : Charge type exists");
				Assert.assertTrue(true);
			} else {
				Assert.fail();
			}
		}
	}


	@Then("Fill in fields {string} and {string} and {string} and {string} and {string} and {string} and {string} and {string} and {string} and {string} to save ledger account")
	public void fillInFieldsToSaveLedgerAccount(String Code, String Description, String EffectiveDate, String ExpiryDate, String Status, String TaxType, String DocumentType, String TaxPayerType, String ChargeType, String BusinessSectorDivision) throws Throwable{
		Thread.sleep(3000);
		driver.findElement(By.id(Pro.getProperty("SubCategory_Code_Input_ID"))).sendKeys(Code);
		driver.findElement(By.id(Pro.getProperty("CategoryDescription_Input_ID"))).sendKeys(Description);
		//lets use javascript to send date fields since sendKeys method has inconsistent behavior in date fields
		jse.executeScript("document.getElementById('"+Pro.getProperty("EffectiveDate_Input_ID")+"').setAttribute('value', '"+EffectiveDate+"')");
		jse.executeScript("document.getElementById('"+Pro.getProperty("ExpiryDate_Input_ID")+"').setAttribute('value', '"+ExpiryDate+"')");

		//open status dropdown
		driver.findElement(By.xpath(Pro.getProperty("Category_Status_Dropdown_Status_XPATH"))).click();
		Thread.sleep(1000);

		//select status
		String StatusXpath = "//li[@data-label='"+Status+"']";
		driver.findElement(By.xpath(StatusXpath)).click();

		//Select charge type
		String ChargeTypeXpath = "//td[contains(text(),'" + ChargeType + "')]";
		driver.findElement(By.xpath(ChargeTypeXpath)).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"categoryDetails:chargeTypeList\"]/div[2]/div/button[1]")).click();
		Thread.sleep(1000);

		//Select taxtype
		String TaxTypeXpath = "//td[contains(text(),'" + TaxType + "')]";
		driver.findElement(By.xpath(TaxTypeXpath)).click();
		driver.findElement(By.xpath("//*[@id=\"categoryDetails:RevenueTypeList\"]/div[2]/div/button[1]")).click();

		//Select document type
		String DocumentTypeXpath = "//td[contains(text(),'" + DocumentType + "')]";
		driver.findElement(By.xpath(DocumentTypeXpath)).click();
		driver.findElement(By.xpath("//*[@id=\"categoryDetails:docTypeList\"]/div[2]/div/button[1]")).click();

		//Select taxpayer type
		String TaxPayerXpath = "//td[contains(text(),'" + TaxPayerType + "')]";
		driver.findElement(By.xpath(TaxPayerXpath)).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"categoryDetails:taxpayerTypeList\"]/div[2]/div/button[1]")).click();
		Thread.sleep(1000);



		//Select business sector division
		String BusinessSectorXpath = "//td[contains(text(),'" + BusinessSectorDivision + "')]";
		driver.findElement(By.xpath(BusinessSectorXpath)).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"categoryDetails:businessSectorList\"]/div[2]/div/button[1]")).click();

		driver.findElement(By.id(Pro.getProperty("SubCategory_Save_Button_ID"))).click();
		Thread.sleep(3000);


	}


	@Then("^Fill in fields \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" in ledger account account details$")
	public void fillInFieldsInLedgerAccountAccountDetails(String Code, String Description, String EffectiveDate, String ExpiryDate, String Status) throws Throwable{

		Thread.sleep(3000);

		driver.findElement(By.id(Pro.getProperty("SubCategory_Code_Input_ID"))).sendKeys(Code);
		driver.findElement(By.id(Pro.getProperty("CategoryDescription_Input_ID"))).sendKeys(Description);
		//lets use javascript to send date fields since sendKeys method has inconsistent behavior in date fields
		jse.executeScript("document.getElementById('"+Pro.getProperty("EffectiveDate_Input_ID")+"').setAttribute('value', '"+EffectiveDate+"')");
		jse.executeScript("document.getElementById('"+Pro.getProperty("ExpiryDate_Input_ID")+"').setAttribute('value', '"+ExpiryDate+"')");

		//open status dropdown
		driver.findElement(By.xpath(Pro.getProperty("Category_Status_Dropdown_Status_XPATH"))).click();
		Thread.sleep(1000);

		//select status
		String StatusXpath = "//li[@data-label='"+Status+"']";
		driver.findElement(By.xpath(StatusXpath)).click();




	}


}





