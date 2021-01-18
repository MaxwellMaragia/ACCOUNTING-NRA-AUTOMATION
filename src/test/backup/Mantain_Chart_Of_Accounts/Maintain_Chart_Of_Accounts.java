package Steps.backup.Mantain_Chart_Of_Accounts;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.BaseClass;
import utils.SeleniumDriver;


import java.io.FileInputStream;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;


public class Maintain_Chart_Of_Accounts extends BaseClass {


	public WebDriverWait wait;
	public Scenario scenario = null;
	public Properties Pro;
	public JavascriptExecutor jse = (JavascriptExecutor)driver;
	public Double CurrentOutstandingLiability;
	public Double UnallocatedCreditAmount;

	//we will be deducting 1,000
	public Double AmountDeductedFromUnallocatedCredit = 1000.00;
	public String ReferenceNumber;

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
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
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