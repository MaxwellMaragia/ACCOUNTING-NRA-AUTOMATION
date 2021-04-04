package Steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.BaseClass;
import utils.BaseClass;

import java.io.File;
import java.io.FileInputStream;
import java.security.Key;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;


public class Taxpayer_And_Revenue_Accounting extends BaseClass {

    public JavascriptExecutor jse = (JavascriptExecutor) driver;
    public Double CurrentOutstandingLiability;
    public Double UnallocatedCreditAmount;

    //we will be deducting 1,000
    public Double AmountDeductedFromUnallocatedCredit = 1000.00;
    public String ReferenceNumber;
    public static sharedatastep sharedata;

    public Taxpayer_And_Revenue_Accounting (sharedatastep sharedata) {

        Taxpayer_And_Revenue_Accounting.sharedata=sharedata;

    }
    @Before(order = 0)
    public void method1() throws Exception {
        // this.S=S;
        Pro = new Properties();
        FileInputStream fip = new FileInputStream(".\\src\\test\\resources\\Objects\\object.properties");
        Pro.load(fip);
    }

    //..................................Maxwell Maragia........................................//

    @Given("^User is in browser and launches the app URL$")
    public void user_is_in_browser_and_launches_the_app_url() {

        driver.get(Pro.getProperty("MRA_BackOffice_URL"));
        driver.manage().window().maximize();

    }

    @Given("^Launch portal in browser$")
    public void launch_portal_url() {

        driver.get(Pro.getProperty("PORTAL_URL"));
        driver.manage().window().maximize();

    }

    @Then("^Login to portal$")
    public void portalLogin(DataTable data) throws Throwable {
        List<List<String>> obj = data.asLists();
        WebDriverWait wait = new WebDriverWait(driver,120);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(Pro.getProperty("USERNAME_ID")))).sendKeys(obj.get(0).get(0));
                driver.findElement(By.id(Pro.getProperty("PASSWORD_ID"))).sendKeys(obj.get(0).get(1));
        driver.findElement(By.id(Pro.getProperty("LOGIN_ID"))).click();


    }

    @Then("^Login as Revenue Officer$")
    public void login_as_revenue_officer(DataTable data) throws Throwable {
        List<List<String>> obj = data.asLists();
        WebDriverWait wait = new WebDriverWait(driver,100);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(Pro.getProperty("BackOffice_UserName_ID")))).sendKeys(obj.get(0).get(0));
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
        BaseClass.waitForPageToLoad();
        List<List<String>> obj = data.asLists();
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
        BaseClass.waitForPageToLoad();
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

        BaseClass.waitForPageToLoad();
        List<List<String>> obj = data.asLists();
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
        BaseClass.waitForPageToLoad();
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
        BaseClass.waitForPageToLoad();
        List<List<String>> obj = data.asLists();
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
        BaseClass.waitForPageToLoad();
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
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        driver.findElement(By.id(Pro.getProperty("AccountCode_Input_ID"))).sendKeys(String.valueOf(timestamp.getTime()));
        driver.findElement(By.id(Pro.getProperty("CategoryDescription_Input_ID"))).sendKeys(Description);
        driver.findElement(By.id(Pro.getProperty("EffectiveDate_Input_ID"))).sendKeys(Keys.ENTER);
        driver.findElement(By.id(Pro.getProperty("ExpiryDate_Input_ID"))).click();
        Thread.sleep(2000);
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
        WebDriverWait wait = new WebDriverWait(driver,60);
        WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'" + SuccessMessage + "')]")));

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
        BaseClass.waitForPageToLoad();
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
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        driver.findElement(By.id(Pro.getProperty("SubCategory_Code_Input_ID"))).sendKeys(String.valueOf(timestamp.getTime()));
        driver.findElement(By.id(Pro.getProperty("SubCategory_Code_Description_ID"))).sendKeys("Sub category "+String.valueOf(timestamp.getTime()));

        //lets use javascript to send date fields since sendKeys method has inconsistent behavior in date fields
        driver.findElement(By.id(Pro.getProperty("SubCategory_EffectiveDate_Input_ID"))).sendKeys(Keys.ENTER);
        driver.findElement(By.id(Pro.getProperty("SubCategory_ExpiryDate_Input_ID"))).click();
        Thread.sleep(2000);
        driver.findElement(By.id(Pro.getProperty("SubCategory_ExpiryDate_Input_ID"))).sendKeys(ExpiryDate);

        //Click business sector division check box
        driver.findElement(By.id(Pro.getProperty("SubCategory_Business_Sector_Division_ID"))).click();

        //open status dropdown
        driver.findElement(By.xpath(Pro.getProperty("SubCategory_Status_Dropdown_XPATH"))).click();
        Thread.sleep(1000);

        //select status
        String StatusXpath = "//li[@data-label='" + Status + "']";
        driver.findElement(By.xpath(StatusXpath)).click();

        //click save button
        driver.findElement(By.id(Pro.getProperty("Category_Details_Save_Button_ID"))).click();

    }

    @Then("^Click edit category button$")
    public void click_edit_category_button() throws Throwable {
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
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        driver.findElement(By.id(Pro.getProperty("CategoryDescription_Input_ID"))).clear();
        driver.findElement(By.id(Pro.getProperty("CategoryDescription_Input_ID"))).sendKeys("Account code"+String.valueOf(timestamp.getTime()));
        //lets use javascript to send date fields since sendKeys method has inconsistent behavior in date fields

//        driver.findElement(By.id(Pro.getProperty("EffectiveDate_Input_ID"))).clear();
//        driver.findElement(By.id(Pro.getProperty("EffectiveDate_Input_ID"))).sendKeys(Keys.ENTER);
//        Thread.sleep(3000);
//        driver.findElement(By.id(Pro.getProperty("ExpiryDate_Input_ID"))).click();
//        driver.findElement(By.id(Pro.getProperty("ExpiryDate_Input_ID"))).clear();
//        driver.findElement(By.id(Pro.getProperty("ExpiryDate_Input_ID"))).sendKeys(ExpiryDate);



        //open status dropdown
        driver.findElement(By.xpath(Pro.getProperty("Category_Status_Dropdown_Status_XPATH"))).click();
        Thread.sleep(1000);

        //select status
        String StatusXpath = "//li[@data-label='" + Status + "']";
        driver.findElement(By.xpath(StatusXpath)).click();

        //click save button
        driver.findElement(By.id(Pro.getProperty("Category_Details_Save_Button_ID"))).click();

    }

    //Edit sub category
    @Then("^Edit fields \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" then click save to edit subcategory$")
    public void edit_fields_in_sub_category_then_click_save(String Code, String Description, String EffectiveDate) throws Throwable {
        Thread.sleep(3000);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        driver.findElement(By.id(Pro.getProperty("SubCategory_Code_Input_ID"))).clear();
        driver.findElement(By.id(Pro.getProperty("SubCategory_Code_Input_ID"))).sendKeys(String.valueOf(timestamp.getTime()));
        driver.findElement(By.id(Pro.getProperty("SubCategory_Code_Description_ID"))).clear();
        driver.findElement(By.id(Pro.getProperty("SubCategory_Code_Description_ID"))).sendKeys("Description"+String.valueOf(timestamp.getTime()));
        //lets use javascript to send date fields since sendKeys method has inconsistent behavior in date fields
        driver.findElement(By.id(Pro.getProperty("SubCategory_EffectiveDate_Input_ID"))).sendKeys(Keys.ENTER);

        //click save button
        driver.findElement(By.id(Pro.getProperty("SubCategory_Save_Button_ID"))).click();

    }

    //CLick treeview dropdown till seventh level
    @Then("^Click on tree view in revenue category upto seventh level$")
    public void click_on_tree_view_in_revenue_category_upto_seventh_level() throws Throwable {

        BaseClass.waitForPageToLoad();
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
        BaseClass.waitForPageToLoad();
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
        //driver.findElement(By.xpath(Pro.getProperty("Sixth_Treeview_Dropdown_XPATH"))).click();

    }

    @Then("^Click on sixth table row in treeview$")
    public void click_on_sixth_table_row_in_treeview() throws Throwable {

        Thread.sleep(1000);
        //Click on seventh table row
        driver.findElement(By.id(Pro.getProperty("Fifth_Treeview_Table_Row_ID"))).click();

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
        List<List<String>> obj = data.asLists();
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
        List<List<String>> obj = data.asLists();
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
        List<List<String>> obj = data.asLists();
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
        List<List<String>> obj = data.asLists();
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
    public void fillInFieldsToSaveLedgerAccount(String Code, String Description, String EffectiveDate, String ExpiryDate, String Status, String TaxType, String DocumentType, String TaxPayerType, String ChargeType, String BusinessSectorDivision) throws Throwable {
        Thread.sleep(3000);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        driver.findElement(By.id(Pro.getProperty("SubCategory_Code_Input_ID"))).sendKeys(String.valueOf(timestamp.getTime()));
        driver.findElement(By.id(Pro.getProperty("CategoryDescription_Input_ID"))).sendKeys("Ledger"+String.valueOf(timestamp.getTime()));
        //lets use javascript to send date fields since sendKeys method has inconsistent behavior in date fields
        driver.findElement(By.id(Pro.getProperty("EffectiveDate_Input_ID"))).sendKeys(Keys.ENTER);
        driver.findElement(By.id(Pro.getProperty("ExpiryDate_Input_ID"))).click();
        Thread.sleep(2000);
        driver.findElement(By.id(Pro.getProperty("ExpiryDate_Input_ID"))).sendKeys(ExpiryDate);

        //open status dropdown
        driver.findElement(By.xpath(Pro.getProperty("Category_Status_Dropdown_Status_XPATH"))).click();
        Thread.sleep(1000);

        //select status
        String StatusXpath = "//li[@data-label='" + Status + "']";
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
    public void fillInFieldsInLedgerAccountAccountDetails(String Code, String Description, String EffectiveDate, String ExpiryDate, String Status) throws Throwable {

        Thread.sleep(3000);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        driver.findElement(By.id(Pro.getProperty("SubCategory_Code_Input_ID"))).sendKeys(String.valueOf(timestamp.getTime()));
        driver.findElement(By.id(Pro.getProperty("CategoryDescription_Input_ID"))).sendKeys("Ledger sample "+String.valueOf(timestamp.getTime()));
        //lets use javascript to send date fields since sendKeys method has inconsistent behavior in date fields
        driver.findElement(By.id(Pro.getProperty("EffectiveDate_Input_ID"))).sendKeys(Keys.ENTER);
        driver.findElement(By.id(Pro.getProperty("ExpiryDate_Input_ID"))).click();
        driver.findElement(By.id(Pro.getProperty("ExpiryDate_Input_ID"))).sendKeys(ExpiryDate);

        //open status dropdown
        driver.findElement(By.xpath(Pro.getProperty("Category_Status_Dropdown_Status_XPATH"))).click();
        Thread.sleep(1000);

        //select status
        String StatusXpath = "//li[@data-label='" + Status + "']";
        driver.findElement(By.xpath(StatusXpath)).click();


    }

    @And("^Click on Taxpayer accounting > Manage Credit Allocation$")
    public void click_on_taxpayer_accounting_manage_credit_allocation() throws Throwable {

        WebDriverWait wait = new WebDriverWait(driver,30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"MenuForm:j_idt27\"]/ul/li[2]/a"))).click();
        driver.findElement(By.xpath("//*[@id=\"MenuForm:j_idt27\"]/ul/li[2]/ul/li[7]/a")).click();

    }

    @Then("^Verify fields \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" in Credit Allocation$")
    public void verify_fields_in_credit_allocation_page(String Tin, String TaxType, String Add, String View, String Search, String Cancel) throws Throwable {

        BaseClass.waitForPageToLoad();
        String Input_fields[] = {TaxType, Tin, Add, View, Search, Cancel};
        for (int i = 0; i < Input_fields.length; i++) {
            WebElement form_element = driver.findElement(By.id(Input_fields[i]));
            if (form_element.isDisplayed()) {
                Assert.assertTrue(true);

            } else {
                Assert.fail();
            }
        }
    }

    @Then("^Verify table columns \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\"$")
    public void verify_table_columns_in_credit_allocation_page(String TaxTypeColumn, String UnallocatedCreditPeriod, String DocumentTypeUnallocatedCredit, String UnallocatedCreditAmount, String OutstandingLiabilityPeriod, String DocumentTypeOutstandingLiability, String OutstandingLiabilityAmount) throws Throwable {

        BaseClass.waitForPageToLoad();
        String table_columns[] = {UnallocatedCreditPeriod, DocumentTypeUnallocatedCredit, UnallocatedCreditAmount, OutstandingLiabilityPeriod, DocumentTypeOutstandingLiability, OutstandingLiabilityAmount};
        for (int i = 0; i < table_columns.length; i++) {
            WebElement column = driver.findElement(By.xpath("//span[contains(text(),'" + table_columns[i] + "')]"));
            if (column.isDisplayed()) {
                Assert.assertTrue(true);
                System.out.println("Table column '" + table_columns[i] + "' has been found");

            } else {

                System.out.println("Table column '" + table_columns[i] + "' not found");
                Assert.fail();
            }
        }
    }

    @Then("^Click add button$")
    public void click_add_button() {

        BaseClass.waitForPageToLoad();
        driver.findElement(By.id(Pro.getProperty("Add_Button_ID"))).click();

    }

    @Then("^Verify input field \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\"  in credit allocation fields$")
    public void verify_input_field_in_credit_allocation_fields(String TinInput, String NameInput, String TaxTypeInput) throws Throwable {

        BaseClass.waitForPageToLoad();
        Thread.sleep(3000);
        String Input_fields[] = {TinInput, NameInput, TaxTypeInput};
        for (int i = 0; i < Input_fields.length; i++) {
            WebElement form_element = driver.findElement(By.id(Input_fields[i]));
            if (form_element.isDisplayed()) {
                Assert.assertTrue(true);

            } else {
                Assert.fail();
            }
        }
    }

    @Then("^Click find button$")
    public void clickFindButton() throws Throwable {
        BaseClass.waitForPageToLoad();
        driver.findElement(By.id(Pro.getProperty("Find_Tin_Button_ID"))).click();
    }

    @Then("^Click save$")
    public void click_save() throws Throwable {
        BaseClass.waitForPageToLoad();
        driver.findElement(By.id(Pro.getProperty("Credit_Allocation_Save_Button_ID"))).click();

    }

    @Then("^verify error message \"([^\"]*)\"$")
    public void verify_error_message(String ErrorMessage) throws Throwable {

        WebDriverWait wait = new WebDriverWait(driver,60);
        WebElement errorMessageLocator = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'" + ErrorMessage + "')]")));

        if (errorMessageLocator.isDisplayed()) {
            System.out.println("Error message ('" + ErrorMessage + "') has been displayed");
            Assert.assertTrue(true);
        } else {

            Assert.fail("Error message not shown");

        }
    }

    @Then("^Shift focus to modal$")
    public void shift_focus_to_modal() throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver,60);
        WebElement Iframe = wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("iframe")));
        driver.switchTo().frame(Iframe);
    }

    @Then("^enter tin \"([^\"]*)\" and click search$")
    public void enter_tin_and_click_search(String Tin) throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver,60);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(Pro.getProperty("Find_Entity_Tin_Input_ID")))).sendKeys(Tin);

        driver.findElement(By.id(Pro.getProperty("Find_Entity_Tin_Search_Button_ID"))).click();
    }

    @Then("^Select tax office \"([^\"]*)\"$")
    public void select_tax_office(String taxOffice) throws Throwable {
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"frmReportDetails:TAX_OFFICE\"]/div[3]")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//li[contains(text(),'" + taxOffice + "')]")).click();
    }

    @Then("^Select taxpayer category \"([^\"]*)\"$")
    public void select_taxpayer_category(String taxpayerCategory) throws Throwable {
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"frmReportDetails:Taxpayer_Category\"]/div[3]")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//li[contains(text(),'" + taxpayerCategory + "')]")).click();
    }

    @Then("^Click run report \"([^\"]*)\"$")
    public void click_run_report(String buttonID) throws Throwable {
        Thread.sleep(2000);
        driver.findElement(By.id(buttonID)).click();
    }

    @Then("^select tax type \"([^\"]*)\"$")
    public void select_tax_type(String TaxType) throws Throwable {
        Thread.sleep(15000);
        WebDriverWait wait = new WebDriverWait(driver,120);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Pro.getProperty("Tax_Type_Dropdown_XPATH")))).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//li[contains(text(),'" + TaxType + "')]")).click();
    }

    @Then("^Click suspense radio button under unallocated credit balance$")
    public void click_suspense_radio_button_under_unallocated_credit_balance() throws Throwable {
        Thread.sleep(6000);

        driver.findElement(By.xpath(Pro.getProperty("Unallocated_Credit_Suspense_XPATH"))).click();
    }

    @Then("^Select transaction with document \"([^\"]*)\" under unallocated credit$")
    public void SelectTransactionUnderUnallocatedCredit(String DocumentType) throws Throwable {

        Thread.sleep(6000);

        driver.findElement(By.id(Pro.getProperty("Unallocated_Credit_Select_ID"))).click();
        Thread.sleep(6000);
        WebElement Iframe = driver.findElement(By.tagName("iframe"));
        driver.switchTo().frame(Iframe);
        Thread.sleep(4000);
        //select document type
        driver.findElement(By.xpath(Pro.getProperty("Document_Type_Dropdown_XPATH"))).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//li[contains(text(),'" + DocumentType + "')]")).click();
        Thread.sleep(2000);
        driver.findElement(By.id(Pro.getProperty("Find_Entity_Tin_Search_Button_ID"))).click();
        //driver.findElement(By.id(Pro.getProperty("Find_Entity_Tin_Search_Button_ID"))).click();
        Thread.sleep(15000);
        driver.findElement(By.xpath(Pro.getProperty("Find_Business_Transaction_First_Table_Row_XPATH"))).click();
        Thread.sleep(2000);

        driver.findElement(By.id(Pro.getProperty("Continue_Button_ID"))).click();

        Thread.sleep(8000);
        UnallocatedCreditAmount = Double.parseDouble(driver.findElement(By.id("CreditAllocation:crBalance_input")).getAttribute("value").replaceAll(",", ""));
    }

    @Then("^Click suspense radio button under outstanding liability$")
    public void click_suspense_radio_button_under_outstanding_liability() throws Throwable {
        Thread.sleep(3000);
        driver.findElement(By.xpath(Pro.getProperty("Outstanding_Liability_Suspense_XPATH"))).click();
    }

    @Then("^Select transaction with document \"([^\"]*)\" under outstanding liability$")
    public void SelectTransactionUnderOutstandingLiability(String DocumentType) throws Throwable {

        Thread.sleep(8000);

        driver.findElement(By.id(Pro.getProperty("Outstanding_Liability_Select_iD"))).click();
        Thread.sleep(4000);
        WebElement Iframe = driver.findElement(By.tagName("iframe"));
        driver.switchTo().frame(Iframe);
        Thread.sleep(5000);
        //select document type
        driver.findElement(By.xpath(Pro.getProperty("Document_Type_Dropdown_XPATH"))).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//li[contains(text(),'" + DocumentType + "')]")).click();
        Thread.sleep(2000);
        driver.findElement(By.id(Pro.getProperty("Find_Entity_Tin_Search_Button_ID"))).click();
        //driver.findElement(By.id(Pro.getProperty("Find_Entity_Tin_Search_Button_ID"))).click();
        Thread.sleep(3000);

        //click first table row
        driver.findElement(By.xpath("//*[@id=\"SearchForm:resultsDataTable_data\"]/tr[1]/td[1]")).click();

        //click continue
        Thread.sleep(2000);
        driver.findElement(By.id("SearchForm:j_id14")).click();

        Thread.sleep(10000);

        String balanceInput = driver.findElement(By.id("CreditAllocation:drBalance_input")).getAttribute("value").replaceAll(",", "");
        CurrentOutstandingLiability = Double.parseDouble(balanceInput);

    }

    @Then("^populate allocated amount field$")
    public void populate_allocated_amount_field() {

        //we will populate using a quarter of the amount
        driver.findElement(By.id("CreditAllocation:allocatedAmount_input")).clear();
        driver.findElement(By.id("CreditAllocation:allocatedAmount_input")).sendKeys(AmountDeductedFromUnallocatedCredit.toString());

    }

    @Then("^Click submit$")
    public void click_submit() {

        driver.findElement(By.id("CreditAllocation:SaveCA")).click();

    }

    @Then("^Obtain reference number \"([^\"]*)\"$")
    public void split_string_to_obtain_reference_number(String SuccessMessage) {
        //get full message
        String FullMessage = driver.findElement(By.xpath("//span[contains(text(),'" + SuccessMessage + "')]")).getText();
        System.out.println(FullMessage);
        //Processing Completed - Reference Number - CRAL/000001959/2020

        ReferenceNumber = FullMessage.substring(41);
        System.out.println(ReferenceNumber);

    }

    @Then("^Open CRM and close modal$")
    public void open_crm_and_close_modal() throws Throwable {

        driver.get(Pro.getProperty("CRM_URL"));
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        WebElement specificframe = (driver.findElement(By.id(Pro.getProperty("CRM_ExploreCrmWindow_Frame__ID"))));
        driver.switchTo().frame(specificframe);
        WebDriverWait CloseWindow = new WebDriverWait(driver, 60);
        CloseWindow.until(ExpectedConditions.elementToBeClickable(By.id(Pro.getProperty("CRM_ExploreCrmWindow_Frame_Close_ID")))).click();
    }

    @Then("^Click on accounting application link$")
    public void click_on_accounting_application_link() throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver, 100);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Pro.getProperty("Cases_Management_Dropdown_XPATH")))).click();

        Thread.sleep(2000);
        driver.findElement(By.id(Pro.getProperty("Accounting_Application_ID"))).click();

    }

    @Then("^switch to frame$")
    public void switch_to_frame() throws Throwable {
        driver.switchTo().defaultContent();
        WebDriverWait wait = new WebDriverWait(driver, 30);
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
    public void click_on_reference_number() {

        WebElement elementLocator = driver.findElement(By.xpath(Pro.getProperty("CaseManagement_Queue_Select_ReffNo_XPATH")));

        Actions actions = new Actions(driver);
        actions.doubleClick(elementLocator).perform();

        driver.switchTo().defaultContent();

    }

    @Then("^approve transaction$")
    public void approve_transaction() throws Throwable {

        driver.switchTo().frame("contentIFrame1");
        Thread.sleep(70000);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        Actions action = new Actions(driver);
        WebElement Outcome = driver.findElement(By.id(Pro.getProperty("Taxpayer_Accounting_Approval_Outcome_ID")));
        WebElement hasLoaded = driver.findElement(By.id("header_process_tbg_approvaloutcome_lock"));

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        Thread.sleep(7000);
        if (hasLoaded.isDisplayed()) {
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        } else {
            action.doubleClick(Outcome).build().perform();
            Outcome.click();
            action.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();
        }
    }

    @And("^Reject transaction$")
    public void RejectTransaction() throws Throwable {
        driver.switchTo().frame("contentIFrame1");
        Thread.sleep(70000);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        Actions action = new Actions(driver);
        WebElement Outcome = driver.findElement(By.id(Pro.getProperty("Taxpayer_Accounting_Approval_Outcome_ID")));
        WebElement hasLoaded = driver.findElement(By.id("header_process_tbg_approvaloutcome_lock"));

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        Thread.sleep(15000);
        if (hasLoaded.isDisplayed()) {
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        } else {
            action.doubleClick(Outcome).build().perform();
            Outcome.click();
            action.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();
        }
    }

    @And("^clicks Decline from the dropdown$")
    public void clicks_Decline_from_the_dropdown() throws Throwable {
        driver.switchTo().frame("contentIFrame1");
        Thread.sleep(70000);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        Actions action = new Actions(driver);
        WebElement Outcome = driver.findElement(By.id(Pro.getProperty("Taxpayer_Accounting_Approval_Outcome_ID")));
        WebElement hasLoaded = driver.findElement(By.id("header_process_tbg_approvaloutcome_lock"));

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        Thread.sleep(15000);
        if (hasLoaded.isDisplayed()) {
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        } else {
            action.doubleClick(Outcome).build().perform();
            Outcome.click();
            action.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();
        }
    }

    @Then("^Enter Outcome Notes \"([^\"]*)\"$")
    public void enter_Outcome_Notes(String Notes) throws Throwable {
        Thread.sleep(8000);
        Actions action1 = new Actions(driver);
        WebElement element1 = driver.findElement(By.id((Pro.getProperty("Individual_NextStage_RefNum_Reject_OutComeNotes_ID"))));
        action1.sendKeys(element1, Notes).build().perform();
        Thread.sleep(5000);
    }

    @Then("^Click save CRM$")
    public void ClickSaveCRM() throws Throwable {
        driver.switchTo().defaultContent();
        driver.findElement(By.id("tbg_accountingapplication|NoRelationship|Form|Mscrm.Form.tbg_accountingapplication.Save")).click();
//    	driver.findElement(By.id("tbg_accountingapplication|NoRelationship|Form|Mscrm.Form.tbg_accountingapplication.Save")).click();
//    	driver.findElement(By.xpath(Pro.getProperty("//*[@id=\"tbg_accountingapplication|NoRelationship|Form|Mscrm.Form.tbg_accountingapplication.Save\"]"))).click();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    @Then("^Application Account Adjustment status should be \"([^\"]*)\"$")
    public void Verify_status_from_CRM(String Status) throws Throwable {
        driver.switchTo().frame("contentIFrame1");
        WebDriverWait wait = new WebDriverWait(driver,200);
        WebElement statusLabel = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[contains(text(),'" + Status + "')]")));

        if (statusLabel.isDisplayed()) {
            Assert.assertTrue("Approved", true);
        } else {
            Assert.fail("Approval failed");
        }
        Thread.sleep(2000);
    }

    @Then("^Click clear button \"([^\"]*)\" and verify field \"([^\"]*)\" has been cleared$")
    public void click_clear_button_and_verify_field_has_been_cleared(String Button, String Field) throws Throwable {

        Thread.sleep(5000);
        driver.findElement(By.id(Button)).click();
        String Amount = driver.findElement(By.id(Field)).getAttribute("value");
        if (Amount.length() > 0) {
            Assert.fail();
        } else {
            Assert.assertTrue(true);
        }

    }

    @Then("^input tin \"([^\"]*)\"$")
    public void input_tin(String tin) throws Throwable {

        BaseClass.waitForPageToLoad();
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
        BaseClass.waitForPageToLoad();
        Thread.sleep(3000);
        WebElement TinInput = driver.findElement(By.id("CreditAllocation:TIN"));
        if (TinInput.isEnabled()) {
            Assert.assertFalse("Field is not readonly", false);
        } else {
            Assert.assertTrue("Field is readonly", true);
        }
    }

    @And("^Click on Revenue Accounting System > Revenue Control Accounts > Tax Receivable Control Account$")
    public void Access_tax_receivable_control_account_page() throws Throwable {

        BaseClass.waitForPageToLoad();
        driver.findElement(By.xpath(Pro.getProperty("RevenueAccounting_Dropdown_XPATH"))).click();
        driver.findElement(By.xpath("//*[@id=\"MenuForm:j_idt27\"]/ul/li[3]/ul/li[2]/a")).click();
        driver.findElement(By.xpath("//*[@id=\"sub1\"]/ul/li[3]/a")).click();

    }

    @Then("^Verify The input fields \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\"$")
    public void verify_the_input_fields(String TaxType, String TaxOffice, String TransactionType, String DateFrom, String DateTo, String OpeningBalance, String ClosingBalance, String SearchButton) throws Throwable {
        BaseClass.waitForPageToLoad();
        String Input_fields[] = {TaxType, TaxOffice, TransactionType, DateFrom, DateTo, OpeningBalance, ClosingBalance, SearchButton};
        for (int i = 0; i < Input_fields.length; i++) {
            WebElement form_element = driver.findElement(By.id(Input_fields[i]));
            if (form_element.isDisplayed()) {
                Assert.assertTrue(true);

            } else {
                Assert.fail();
            }

        }

    }

    @And("^Click on Revenue Accounting System > Revenue Control Accounts > Total Revenue Account$")
    public void click_on_revenue_accounting_system_revenue_control_accounts_total_revenue_account() throws Throwable {
        BaseClass.waitForPageToLoad();
        driver.findElement(By.xpath(Pro.getProperty("RevenueAccounting_Dropdown_XPATH"))).click();
        driver.findElement(By.xpath("//*[@id=\"MenuForm:j_idt27\"]/ul/li[3]/ul/li[2]/a")).click();
        driver.findElement(By.xpath("//*[@id=\"sub1\"]/ul/li[4]/a")).click();
    }

    @Then("^Verify existence of table columns \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" in table$")
    public void verify_existence_of_table_columns_in_table(String Date, String TaxOfficeColumn, String TaxPayerName, String Tin, String TransactionDescription, String TaxTypeColumn, String DocumentReference, String Debit, String Credit, String Balance) throws Throwable {

        String table_columns[] = {Date, TaxOfficeColumn, TaxPayerName, Tin, TransactionDescription, TaxTypeColumn, DocumentReference, Debit, Credit, Balance};
        for (int i = 0; i < table_columns.length; i++) {
            WebElement column = driver.findElement(By.xpath("//span[contains(text(),'" + table_columns[i] + "')]"));
            if (column.isDisplayed()) {
                Assert.assertTrue(true);
                System.out.println("Table column '" + table_columns[i] + "' has been found");

            } else {
                Assert.fail();
                System.out.println("Table column '" + table_columns[i] + "' not found");
            }

        }


    }

    @Then("^Verify The input fields \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" in revenue account$")
    public void verify_the_input_fields_in_revenue_account(String TaxOffice, String DateFrom, String DateTo, String SearchButton) throws Throwable {
        BaseClass.waitForPageToLoad();
        String Input_fields[] = {TaxOffice, DateFrom, DateTo, SearchButton};
        for (int i = 0; i < Input_fields.length; i++) {
            WebElement form_element = driver.findElement(By.id(Input_fields[i]));
            if (form_element.isDisplayed()) {
                Assert.assertTrue(true);
            } else {
                Assert.fail();
            }

        }
    }

    @Then("^Verify existence of table columns \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" in revenue account table$")
    public void verify_existence_of_table_columns_in_revenue_account_table(String TaxOfficeColumn, String TransactionDescription, String Debit, String Credit, String Balance) throws Throwable {
        String table_columns[] = {TaxOfficeColumn, TransactionDescription, Debit, Credit, Balance};
        for (int i = 0; i < table_columns.length; i++) {
            WebElement column = driver.findElement(By.xpath("//span[contains(text(),'" + table_columns[i] + "')]"));
            if (column.isDisplayed()) {
                Assert.assertTrue(true);
                System.out.println("Table column in total revenue account '" + table_columns[i] + "' has been found");

            } else {
                Assert.fail();
                System.out.println("Table column '" + table_columns[i] + "' not found in total revenue account table");
            }

        }
    }

    //Verify precense of navigation links in home page
    @Then("^Verify Home Screen Buttons$")
    public void verify_home_screen_buttons(DataTable data) throws Throwable {

        WebDriverWait wait = new WebDriverWait(driver,100);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Taxpayer Notification')]")));
        List<List<String>> obj = data.asLists();
        String buttons[] = {
                obj.get(0).get(0),
                obj.get(1).get(0),
                obj.get(2).get(0),
                obj.get(3).get(0)
        };

        for (int i = 0; i < buttons.length; i++) {
            WebElement Button = driver.findElement(By.xpath("//button[contains(text(),'" + buttons[i] + "')]"));
            if (Button.isDisplayed()) {
                Assert.assertTrue("Button '" + buttons[i] + "' found", true);
            } else {
                Assert.fail("Button '" + buttons[i] + "' not found");
            }

        }


    }

    //Verify accounts table columns
    @Then("^Verify Tax Accounts Table$")
    public void verify_tax_accounts_table(DataTable data) throws Throwable {
        List<List<String>> obj = data.asLists();
        String columns[] = {obj.get(0).get(0), obj.get(1).get(0)};

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
        WebDriverWait wait = new WebDriverWait(driver,120);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(Pro.getProperty("My_Tax_Button_ID")))).click();
    }

    @Then("^Verify my tax account table columns$")
    public void verify_my_tax_account_table_columns(DataTable data) throws Throwable {
        BaseClass.waitForPageToLoad();
        List<List<String>> obj = data.asLists();
        String columns[] = {obj.get(0).get(0), obj.get(1).get(0), obj.get(2).get(0)};

        //check if columns exist
        for (int i = 0; i < columns.length; i++) {
            WebElement Table_Column = driver.findElement(By.xpath("//th[contains(text(),'" + columns[i] + "')]"));


            if (Table_Column.isDisplayed()) {

                System.out.println("Column : " + columns[i] + " found ");
                Assert.assertTrue(true);
                Thread.sleep(2000);
            } else {

                Assert.fail();
                System.out.println("Column : " + columns[i] + " not found ");

            }
        }
    }

    @Then("^Verify manage my account dropdowns$")
    public void verify_manage_my_account_dropdowns(DataTable data) {
        List<List<String>> obj = data.asLists();
        String dropdowns[] = {
                obj.get(0).get(0),
                obj.get(1).get(0),
                obj.get(2).get(0),
                obj.get(3).get(0)
        };

        for (int i = 0; i < dropdowns.length; i++) {
            WebElement Dropdown = driver.findElement(By.xpath("//div[contains(text(),'" + dropdowns[i] + "')]"));
            if (Dropdown.isDisplayed()) {
                Assert.assertTrue("Dropdown '" + dropdowns[i] + "' found", true);
            } else {
                Assert.fail("Dropdown '" + dropdowns[i] + "' not found");
            }

        }

    }

    //Click on suspence account table row
    @Then("^Select Suspense account with trans$")
    public void select_suspense_account_with_trans() throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver,120);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"id_pTableTaxAccountSummary\"]/div/div[1]/table/tbody/tr[3]/td[1]"))).click();
    }

    //Click on suspence account table row
    @Then("^Select Suspense account$")
    public void select_suspense_account() throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver,120);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Pro.getProperty("Suspence_Account_XPATH")))).click();
    }

    @Then("^Verify the input fields$")
    public void verify_input_fields(DataTable data) throws Throwable {

        WebDriverWait wait = new WebDriverWait(driver,120);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("id_startDate"))).isDisplayed();

        List<List<String>> obj = data.asLists();
        String[] elements = {obj.get(0).get(0), obj.get(1).get(0), obj.get(2).get(0)};

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

        Thread.sleep(4000);
        String columns[] = {TaxAccountType, AccountNumber, CurrentBalance, CurrentStatus, Date, DocumentType, DocumentReference, Status, Amount};

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
        String columns[] = {taxAccountType, accountNumber, currentBalance, currentStatus};
        WebDriverWait wait = new WebDriverWait(driver,120);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//th[contains(text(),'" + currentStatus + "')]")));
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
        if (Balance.equals("0.00")) {
            Assert.fail("Balance shown as 0.00");
        } else {
            Assert.assertTrue(true);
        }

    }

    @Then("^Enter end date value that is more than 365 days from current start date \"([^\"]*)\"$")
    public void enter_end_date_value_that_is_more_than_365_days_from_current_start_date(String date) throws Throwable {
        BaseClass.waitForPageToLoad();
        driver.findElement(By.id("id_endDate")).clear();
        Thread.sleep(2000);
        driver.findElement(By.id("id_endDate")).sendKeys(date);

//        Thread.sleep(2000);
       driver.findElement(By.id("id_endDate")).sendKeys(Keys.TAB);
        //jse.executeScript("document.getElementById('"+Pro.getProperty("End_Date_ID")+"').setAttribute('value', '"+date+"')");
//        driver.findElement(By.xpath(Pro.getProperty("Date_Picker_XPATH"))).click();
//        Thread.sleep(3000);
//        driver.findElement(By.xpath(Pro.getProperty("Date_XPATH"))).click();


    }

    @Then("^Search for payment details$")
    public void search_for_payment_details() throws Throwable {

        driver.findElement(By.id(Pro.getProperty("Search_ID"))).click();

    }

    @Then("^Verify error message \"([^\"]*)\"$")
    public void verify_error_message_something(String ErrorMessage) throws Throwable {

        WebDriverWait wait = new WebDriverWait(driver,30);
        WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'" + ErrorMessage + "')]")));

        if (successMessage.isDisplayed()) {
            System.out.println("Error message ('" + ErrorMessage + "') has been displayed");
            Assert.assertTrue(true);
        } else {
            Assert.fail("Error message not displayed");

        }

    }

    @Then("^Click on a suspended tax type$")
    public void click_on_a_suspended_tax_type() throws Throwable {
        //Thread.sleep(7000);
        WebDriverWait wait = new WebDriverWait(driver,120);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Suspended')]"))).click();
    }

    @Then("^Click on a de registered tax type$")
    public void click_on_a_deregistered_tax_type() throws Throwable {
        //Thread.sleep(7000);
        WebDriverWait wait = new WebDriverWait(driver,120);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'De-registered')]"))).click();

    }

    @Then("^Verify Status \"([^\"]*)\" in field \"([^\"]*)\"$")
    public void verify_status(String Status, String StatusXpath) throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver,120);
        String DisplayedStatus = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(StatusXpath))).getText();

        if (Status.equals(DisplayedStatus)) {
            Assert.assertTrue(true);
        } else {
            Assert.fail("Incorrect status displayed");
        }
    }

    @Then("^Click statement requests under tasks$")
    public void click_statement_requests_under_tasks() throws Throwable{
        Thread.sleep(15000);
        driver.findElement(By.id(Pro.getProperty("Statement_Request_ID"))).click();
    }

    @Then("^Click tax type dropdown and select tax type that has transactions$")
    public void click_tax_type_dropdown_and_select_tax_type_that_has_transactions() throws Throwable {
        Thread.sleep(7000);
        driver.findElement(By.xpath(Pro.getProperty("TaxTypeDropdownXPATH"))).click();

        String transactionsXpath = "//span[contains(text(),'Suspense')]";
        WebDriverWait wait = new WebDriverWait(driver,120);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(transactionsXpath))).click();
    }

    @Then("^Verify statement request input fields$")
    public void verify_statement_request_input_fields(DataTable data) throws Throwable {

        Thread.sleep(3000);

        List<List<String>> obj = data.asLists();
        String[] elements = {obj.get(0).get(0), obj.get(1).get(0), obj.get(2).get(0), obj.get(3).get(0), obj.get(4).get(0)};

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
    public void click_cancel_button() throws Throwable {

        WebDriverWait wait = new WebDriverWait(driver,100);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Pro.getProperty("Cancel_XPATH")))).click();

    }

    @Then("^Select month \"([^\"]*)\" and \"([^\"]*)\"$")
    public void select_month_and_year(String month, String year) throws Throwable {

        Thread.sleep(2000);
        driver.findElement(By.xpath(Pro.getProperty("MonthDropdownXPATH"))).click();
        String monthXPATH = "//span[contains(text(),'" + month + "')]";
        driver.findElement(By.xpath(monthXPATH)).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath(Pro.getProperty("YearDropdownXPATH"))).click();
        String yearXPATH = "//span[contains(text(),'" + year + "')]";
        driver.findElement(By.xpath(yearXPATH)).click();


    }

    @Then("^Click submit : portal$")
    public void click_submit_portal() throws Throwable {

        driver.findElement(By.xpath(Pro.getProperty("Submit_XPATH"))).click();


    }

    @Then("^Click download and verify download$")
    public void click_download_and_verify_download() throws Throwable {

        WebDriverWait wait = new WebDriverWait(driver,120);
        WebElement success = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[contains(text(),'Statement generated successfully')]")));
        if (success.isDisplayed()) {
            Thread.sleep(3000);
            if (driver.findElement(By.xpath(Pro.getProperty("Download_XPATH"))).isEnabled()) {
                Assert.assertTrue("Download button is enabled", true);
            }
            else {
                Assert.fail("Download button not enabled");
            }
        } else {
            Assert.fail("Download button not enabled");
        }

    }

    //...........................Maxwell Maragia..................................................//
//-----------------------------TAXPAYER ACCOUNTING INDIVIDUAL ORGANIZATION -------------

    @Given("^User is in browser to launch application url$")
    public void user_is_in_browser_to_launch_application_url() throws Throwable {
        driver.get(Pro.getProperty("MRA_BackOffice_URL"));
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

    }

    @Then("^Enter  username \"([^\"]*)\" and  password \"([^\"]*)\" to login$")
    public void enter_username_something_and_password_something_to_login(String username, String password) throws Throwable {
        driver.findElement(By.xpath("/html/body/form/div[3]/div/div[2]/div/div[1]/div[2]/div/input")).sendKeys(username);
        driver.findElement(By.xpath("/html/body/form/div[3]/div/div[2]/div/div[1]/div[4]/div/input")).sendKeys(password);
        driver.findElement(By.xpath("/html/body/form/div[3]/div/div[2]/div/div[2]/button")).click();

    }


    @Then("Click on Taxpayer Accounting-->Taxpayer Account Enquiry")
    public void click_on_taxpayer_accountingtaxpayer_account_enquiry() throws Throwable {
        driver.findElement(By.xpath("/html/body/div[2]/form/div/ul/li[2]/a/span[1]")).click();
        driver.findElement(By.xpath("/html/body/div[2]/form/div/ul/li[2]/ul/li[1]/a")).click();

    }


    @And("^Select Entity Type \"([^\"]*)\" and enter \"([^\"]*)\"$")
    public void select_entity_type_something_and_enter_something(String entity_type, String tin) throws Throwable {
        driver.findElement(By.xpath("/html/body/div[3]/div/div[1]/div/form/div[2]/div/div/div[5]/input")).sendKeys(tin);
        //clicks the dropdown
        driver.findElement(By.cssSelector("#SearchForm\\:DTYPE")).click();
        Thread.sleep(2000);
        // selects the value you want
        WebElement dropdown = driver.findElement(By.xpath("//*[@id=\"SearchForm:DTYPE_items\"]"));
        List<WebElement> entity_list = dropdown.findElements(By.tagName("li"));
        for (WebElement li : entity_list) {
            System.out.print(000);
            if (li.getText().equals(entity_type)) {
                li.click();
                System.out.print(0000);
                //to stop the loop
                break;
            }

        }

        Thread.sleep(2000);

        driver.findElement(By.id("SearchForm:j_idt40")).click();

    }


    @Then("^Select Tax Type Account \"([^\"]*)\" and click select button$")
    public void select_tax_type_account_something_and_click_select_button(String tax_type_account_1) throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver,30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"AccountEnquiry:TaxTypeAccount\"]/div[3]"))).click();
        Thread.sleep(1500);
        driver.findElement(By.xpath("//li[contains(text(),'"+tax_type_account_1+"')]")).click();

        driver.findElement(By.id("AccountEnquiry:j_idt64")).click();
        Thread.sleep(2000);
    }

    @And("^Click on cancel button$")
    public void click_on_cancel_button() throws Throwable {
        driver.findElement(By.xpath("/html/body/div[3]/div/div[1]/div/form/div[3]/div[2]/div[2]/button")).click();
        Thread.sleep(4000);
    }

    /////////Scenario 2

    @When("^Select taxpayer$")
    public void select_taxpayer() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"SearchForm:resultsDataTable_data\"]")).click();
        Thread.sleep(2000);
    }

    @Then("^Click on view button$")
    public void click_on_view_button() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"SearchForm:j_id12\"]")).click();

    }

    @When("^Select Payments1 under \"([^\"]*)\"$")
    public void select_payments1_under_something(String tax_type_account_1) throws Throwable {
        JavascriptExecutor executor = (JavascriptExecutor) driver;

        WebElement taxTypeAccDropdown=driver.findElement(By.xpath("//*[@id=\"AccountEnquiry:TaxTypeAccount\"]/div[3]"));
        executor.executeScript("arguments[0].scrollIntoView(true);", taxTypeAccDropdown);
        taxTypeAccDropdown.click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//li[contains(text(),'"+tax_type_account_1+"')]")).click();


        Thread.sleep(4000);
    }

    @Then("^Click on thee select button$")
    public void click_on_thee_select_button() throws Throwable {
        driver.findElement(By.xpath("/html/body/div[3]/div/div[1]/div/form/div[3]/div[2]/table/tbody/tr/td[9]/button")).click();
        Thread.sleep(4000);
    }

    @When("^Select Suspense account  under \"([^\"]*)\"$")
    public void select_suspense_account_under_something(String tax_type_account_2) throws Throwable {
        JavascriptExecutor executor = (JavascriptExecutor) driver;

        WebElement taxTypeAccDropdown=driver.findElement(By.xpath("//*[@id=\"AccountEnquiry:TaxTypeAccount\"]/div[3]"));
        executor.executeScript("arguments[0].scrollIntoView(true);", taxTypeAccDropdown);
        taxTypeAccDropdown.click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//li[contains(text(),'"+tax_type_account_2+"')]")).click();


        Thread.sleep(4000);
    }

    @Then("^Verify account enquiry table column \"([^\"]*)\"$")
    public void verify_account_enquiry_table_column(String tax_type_account_2) throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver,30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[contains(text(),'"+tax_type_account_2+"')]")));



        Thread.sleep(4000);
    }

    @Then("^Click on select button$")
    public void click_on_select_button() throws Throwable {
        driver.findElement(By.xpath("/html/body/div[3]/div/div[1]/div/form/div[3]/div[2]/table/tbody/tr/td[9]/button")).click();
        Thread.sleep(4000);

    }

    //-----------------------------TAXPAYER ACCOUNTING ENQUIRY ORGANIZATION -------------
    @Then("^Click taxpayer Accounting-->Taxpayer Account Enquiry$")
    public void click_taxpayer_accountingtaxpayer_account_enquiry() throws Throwable {
        driver.findElement(By.xpath("/html/body/div[2]/form/div/ul/li[2]/a/span[1]")).click();
        driver.findElement(By.xpath("/html/body/div[2]/form/div/ul/li[2]/ul/li[1]/a")).click();
    }

    @And("^Select thee entity_type \"([^\"]*)\" and enter the \"([^\"]*)\"$")
    public void select_thee_entitytype_something_and_enter_the_something(String entity_type, String tin) throws Throwable {
        driver.findElement(By.xpath("/html/body/div[3]/div/div[1]/div/form/div[2]/div/div/div[5]/input")).sendKeys(tin);
        //clicks the dropdown
        driver.findElement(By.cssSelector("#SearchForm\\:DTYPE")).click();
        Thread.sleep(2000);
        // selects the value you want
        WebElement dropdown = driver.findElement(By.xpath("//*[@id=\"SearchForm:DTYPE_items\"]"));
        List<WebElement> entity_list = dropdown.findElements(By.tagName("li"));
        for (WebElement li : entity_list) {
            System.out.print(000);
            if (li.getText().equals(entity_type)) {
                li.click();
                System.out.print(0000);
                //to stop the loop
                break;
            }

        }

        Thread.sleep(2000);

        driver.findElement(By.xpath("//*[@id=\"SearchForm:j_idt42\"]/span")).click();
    }

    @Then("^Select Tax_type Account \"([^\"]*)\" and click select button")
    public void select_taxtype_account_something_and_click_select_button(String tax_type_account) throws Throwable {
        JavascriptExecutor executor = (JavascriptExecutor) driver;

        WebElement taxTypeAccDropdown=driver.findElement(By.xpath("//*[@id=\"AccountEnquiry:TaxTypeAccount\"]/div[3]"));
        executor.executeScript("arguments[0].scrollIntoView(true);", taxTypeAccDropdown);
        taxTypeAccDropdown.click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//li[contains(text(),'"+tax_type_account+"')]")).click();

        driver.findElement(By.id("AccountEnquiry:j_idt66")).click();
        Thread.sleep(4000);
    }

    @And("^Click on the  cancel button$")
    public void click_on_the_cancel_button() throws Throwable {
        driver.findElement(By.xpath("/html/body/div[3]/div/div[1]/div/form/div[3]/div[2]/div[2]/button")).click();
        Thread.sleep(4000);
    }

    @When("^Select a taxpayer$")
    public void select_a_taxpayer() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"SearchForm:resultsDataTable_data\"]")).click();
        Thread.sleep(2000);
    }

    @Then("^Click on the view button$")
    public void click_on_the_view_button() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"SearchForm:j_id12\"]")).click();
    }

    @When("^Select Payments under \"([^\"]*)\"$")
    public void select_payments_under_something(String tax_type_account_1) throws Throwable {
        JavascriptExecutor executor = (JavascriptExecutor) driver;

        WebElement taxTypeAccDropdown=driver.findElement(By.xpath("//*[@id=\"AccountEnquiry:TaxTypeAccount\"]/div[3]"));
        executor.executeScript("arguments[0].scrollIntoView(true);", taxTypeAccDropdown);
        taxTypeAccDropdown.click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//li[contains(text(),'"+tax_type_account_1+"')]")).click();


        Thread.sleep(4000);
    }

    @Then("^Click on the select_button$")
    public void click_on_the_selectbutton() throws Throwable {
        driver.findElement(By.xpath("/html/body/div[3]/div/div[1]/div/form/div[3]/div[2]/table/tbody/tr/td[9]/button")).click();
        Thread.sleep(4000);
    }


    @When("^Select Suspense_Account  under \"([^\"]*)\"$")
    public void select_suspenseaccount_under_something(String tax_type_account_2) throws Throwable {
        driver.findElement(By.cssSelector("#AccountEnquiry\\:TaxTypeAccount > div.ui-selectonemenu-trigger.ui-state-default.ui-corner-right")).click();
        Thread.sleep(2000);
        WebElement dropdown = driver.findElement(By.xpath("//*[@id=\"AccountEnquiry:TaxTypeAccount_items\"]"));
        List<WebElement> entity_list = dropdown.findElements(By.tagName("li"));
        for (WebElement li : entity_list) {
            System.out.print(000);
            if (li.getText().equals(tax_type_account_2)) {
                li.click();
                System.out.print(0000);
                break;

            }

        }

        Thread.sleep(4000);
    }


    @Then("^Click on the select button$")
    public void click_on_the_select_button() throws Throwable {
        driver.findElement(By.xpath("/html/body/div[3]/div/div[1]/div/form/div[3]/div[2]/table/tbody/tr/td[9]/button")).click();
        Thread.sleep(4000);
    }


    //-----------------------------TAXPAYER ACCOUNTING REPORTS 1-------------
    @Then("^click Reporting-->Reports$")
    public void click__reportingreports() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"MenuForm:j_idt29\"]/ul/li[4]/a")).click();
        driver.findElement(By.xpath("//*[@id=\"MenuForm:j_idt29\"]/ul/li[4]/ul/li[1]/a")).click();
    }

    @And("^click Account Adjustments Reports link$")
    public void click_account_adjustments_reports_link() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"ReportTree:t1:1_0:j_idt42\"]")).click();
    }

    @Then("^select the report format as \"([^\"]*)\"$")
    public void select_the_report__format_as_something(String format) throws Throwable {
        ////*[@id="frmReportDetails:ReportFormat"]/div[3]
        //#frmReportDetails\:ReportFormat_0
        //#frmReportDetails\:ReportFormat_0
        ////*[@id="frmReportDetails:ReportFormat_0"]
        //#frmReportDetails\:ReportFormat_items
        //*[@id="frmReportDetails:ReportFormat_0"]
        //#frmReportDetails\:ReportFormat > div.ui-selectonemenu-trigger.ui-state-default.ui-corner-right
        driver.findElement(By.cssSelector("#frmReportDetails\\:ReportFormat")).click();
        // selects the value you want
        WebElement dropdown = driver.findElement(By.xpath("//*[@id=\"frmReportDetails:ReportFormat_0\"]"));
        List<WebElement> entity_list = dropdown.findElements(By.tagName("li"));
        for (WebElement li : entity_list) {
            System.out.print(000);
            if (li.getText().equals(format)) {
                li.click();
                System.out.print(0000);
                //to stop the loop
                break;
            }
        }
        Thread.sleep(2000);

    }

    @And("^enter start date and  end date$")
    public void enter_start_date_and_end_date() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"frmReportDetails:StartDate_input\"]")).sendKeys("03/06/2020");

        driver.findElement(By.xpath("//*[@id=\"frmReportDetails:EndDate_input\"]")).sendKeys("19/06/2020");
        Thread.sleep(1000);
    }

    @Then("^select taxpayer category \"([^\"]*)\"$")
    public void select_taxpayer_category_something(String taxpayer_category) throws Throwable {


        //#frmReportDetails\:Taxpayer_Category_items
        //

        driver.findElement(By.cssSelector("#frmReportDetails\\:Taxpayer_Category > div.ui-selectonemenu-trigger.ui-state-default.ui-corner-right")).click();
        // selects the value you want
        WebElement dropdown = driver.findElement(By.xpath("//*[@id=\"frmReportDetails:Taxpayer_Category_items\"]"));
        List<WebElement> entity_list = dropdown.findElements(By.tagName("li"));
        for (WebElement li : entity_list) {
            System.out.print(000);
            if (li.getText().equals(taxpayer_category)) {
                li.click();
                System.out.print(0000);
                //to stop the loop
                break;
            }

        }
        Thread.sleep(2000);
    }

    @Then("^select tax office \"([^\"]*)\"$")
    public void select_tax__office_something(String tax_office) throws Throwable {
        driver.findElement(By.cssSelector("#frmReportDetails\\:TAX_OFFICE")).click();
        Thread.sleep(2000);
        WebElement dropdown = driver.findElement(By.xpath("//*[@id=\"frmReportDetails:TAX_OFFICE_items\"]"));
        List<WebElement> entity_list = dropdown.findElements(By.tagName("li"));
        for (WebElement li : entity_list) {
            System.out.print(000);
            if (li.getText().equals(tax_office)) {
                li.click();
                System.out.print(0000);
                //to stop the loop
                break;
            }

        }
        Thread.sleep(1000);

    }

    @And("^run report$")
    public void run_report() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"frmReportDetails:RunReport\"]")).click();
        Thread.sleep(2000);


    }

    // @Then("^logout$")
    // public void logout() throws Throwable {
    //    driver.findElement(By.id("//*[@id=\"Logout\"]")).click();
    //}
//-----------------------------TAXPAYER ACCOUNTING REPORTS M3_10-02-------------

    @Then("^Click_on Reporting-->Reports$")
    public void click_on_reportingreports() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"MenuForm:j_idt29\"]/ul/li[4]/a")).click();
        driver.findElement(By.xpath("//*[@id=\"MenuForm:j_idt29\"]/ul/li[4]/ul/li[1]/a")).click();
    }

    @And("^Click the Account Adjustments Reports link$")
    public void click_the_account_adjustments_reports_link() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"ReportTree:t1:1_0:j_idt42\"]")).click();
    }

    @Then("^Select thee report format as \"([^\"]*)\"$")
    public void select_the_report_format_as_something(String format) throws Throwable {
        ////*[@id="frmReportDetails:ReportFormat"]/div[3]
        //#frmReportDetails\:ReportFormat_0
        //#frmReportDetails\:ReportFormat_0
        ////*[@id="frmReportDetails:ReportFormat_0"]
        //#frmReportDetails\:ReportFormat_items
        //*[@id="frmReportDetails:ReportFormat_0"]
        //#frmReportDetails\:ReportFormat > div.ui-selectonemenu-trigger.ui-state-default.ui-corner-right
        driver.findElement(By.cssSelector("#frmReportDetails\\:ReportFormat")).click();
        Thread.sleep(2000);
        // selects the value you want
        WebElement dropdown = driver.findElement(By.xpath("//*[@id=\"frmReportDetails:ReportFormat_0\"]"));
        List<WebElement> entity_list = dropdown.findElements(By.tagName("li"));
        for (WebElement li : entity_list) {
            System.out.print(000);
            if (li.getText().equals(format)) {
                li.click();
                System.out.print(0000);
                //to stop the loop
                break;
            }
        }
        Thread.sleep(2000);

    }

    @And("^Skip the start date and  end date$")
    public void Skip_start_date_and_end_date() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"frmReportDetails:StartDate_input\"]")).sendKeys("");

        driver.findElement(By.xpath("//*[@id=\"frmReportDetails:EndDate_input\"]")).sendKeys("");
        Thread.sleep(1000);
    }

    @Then("^Select a taxpayer category \"([^\"]*)\"$")
    public void select_a_taxpayer_category_something(String taxpayer_category) throws Throwable {
        //#frmReportDetails\:Taxpayer_Category_items
        //
        driver.findElement(By.cssSelector("#frmReportDetails\\:Taxpayer_Category > div.ui-selectonemenu-trigger.ui-state-default.ui-corner-right")).click();
        // selects the value you want
        WebElement dropdown = driver.findElement(By.xpath("//*[@id=\"frmReportDetails:Taxpayer_Category_items\"]"));
        List<WebElement> entity_list = dropdown.findElements(By.tagName("li"));
        for (WebElement li : entity_list) {
            System.out.print(000);
            if (li.getText().equals(taxpayer_category)) {
                li.click();
                System.out.print(0000);
                //to stop the loop
                break;
            }

        }
        Thread.sleep(2000);
    }


    @Then("^Select a tax office \"([^\"]*)\"$")
    public void select_tax_office_something(String tax_office) throws Throwable {
        driver.findElement(By.cssSelector("#frmReportDetails\\:TAX_OFFICE > div.ui-selectonemenu-trigger.ui-state-default.ui-corner-right")).click();
        Thread.sleep(2000);
        WebElement dropdown = driver.findElement(By.xpath("//*[@id=\"frmReportDetails:TAX_OFFICE_items\"]"));
        List<WebElement> entity_list = dropdown.findElements(By.tagName("li"));
        for (WebElement li : entity_list) {
            System.out.print(000);
            if (li.getText().equals(tax_office)) {
                li.click();
                System.out.print(0000);
                //to stop the loop
                break;
            }

        }
        Thread.sleep(2000);


    }

    @And("^run the__report$")
    public void run_thereport() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"frmReportDetails:RunReport\"]")).click();
        Thread.sleep(2000);
    }


    // @Then("^Click_logout$")
    // public void Click_logout() throws Throwable {
    //   driver.findElement(By.xpath("//*[@id=\"Logout\"]")).click();
    // }
    //-----------------------------TAXPAYER ACCOUNTING REPORTS M3_10-03-------------

    @Then("^click   Reporting-->Reports$")
    public void click_reportingreports() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"MenuForm:j_idt29\"]/ul/li[4]/a")).click();
        driver.findElement(By.xpath("//*[@id=\"MenuForm:j_idt29\"]/ul/li[4]/ul/li[1]/a")).click();

    }

    @And("^Click Credit Allocation to launch$")
    public void click_credit_allocation_to_launch() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"ReportTree:t1:1_2:j_idt42\"]")).click();

    }

    @Then("^Select the report format \"([^\"]*)\"$")
    public void select_the_report_format_something(String format) throws Throwable {
        driver.findElement(By.cssSelector("#frmReportDetails\\:ReportFormat")).click();
        Thread.sleep(2000);
        // selects the value you
        WebElement dropdown = driver.findElement(By.xpath("//*[@id=\"frmReportDetails:ReportFormat_2\"]"));
        List<WebElement> entity_list = dropdown.findElements(By.tagName("li"));
        for (WebElement li : entity_list) {
            System.out.print(000);
            if (li.getText().equals(format)) {
                li.click();
                System.out.print(0000);
                //to stop the loop
                break;
            }
        }
        Thread.sleep(2000);


    }

    @Then("^select date$")
    public void select_date() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"frmReportDetails:StartDate_input\"]")).sendKeys("3/10/2019");

        driver.findElement(By.xpath("//*[@id=\"frmReportDetails:EndDate_input\"]")).sendKeys("11/10/2019");
        Thread.sleep(1000);
    }


    @Then("^select a Tax Office \"([^\"]*)\"")
    public void select_aa_tax_office_something(String taxoffice) throws Throwable {
        driver.findElement(By.cssSelector("#frmReportDetails\\:TAX_OFFICE > div.ui-selectonemenu-trigger.ui-state-default.ui-corner-right")).click();
        Thread.sleep(2000);
        WebElement dropdown = driver.findElement(By.xpath("//*[@id=\"frmReportDetails:TAX_OFFICE_items\"]"));
        List<WebElement> entity_list = dropdown.findElements(By.tagName("li"));
        for (WebElement li : entity_list) {
            System.out.print(000);
            if (li.getText().equals(taxoffice)) {
                li.click();
                System.out.print(0000);
                //to stop the loop
                break;
            }

        }
        Thread.sleep(2000);
    }


    @Then("^run the report$")
    public void run_the_report() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"frmReportDetails:RunReport\"]")).click();
        Thread.sleep(2000);

    }

    // @Then("^click logout$")
    // public void click_logout() throws Throwable {
    //  driver.findElement(By.xpath("//*[@id=\"Logout\"]")).click();

    //}


    //-----------------------------TAXPAYER ACCOUNTING REPORTS M3_10-04-------------
    @Then("^click Reporting--->Reports$")
    public void Click_reportingreports() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"MenuForm:j_idt29\"]/ul/li[4]/a")).click();
        driver.findElement(By.xpath("//*[@id=\"MenuForm:j_idt29\"]/ul/li[4]/ul/li[1]/a")).click();

    }

    @And("^Click Credit Allocation to Launch$")
    public void Click_credit_allocation_to_launch() throws Throwable {

        driver.findElement(By.xpath("//*[@id=\"ReportTree:t1:1_2:j_idt42\"]")).click();

    }

    @Then("^Select the Report Format \"([^\"]*)\"$")
    public void select_The_report_format_something(String format) throws Throwable {
        driver.findElement(By.cssSelector("#frmReportDetails\\:ReportFormat")).click();
        Thread.sleep(2000);
        // selects the value you
        WebElement dropdown = driver.findElement(By.xpath("//*[@id=\"frmReportDetails:ReportFormat_2\"]"));
        List<WebElement> entity_list = dropdown.findElements(By.tagName("li"));
        for (WebElement li : entity_list) {
            System.out.print(000);
            if (li.getText().equals(format)) {
                li.click();
                System.out.print(0000);
                //to stop the loop
                break;
            }
        }
        Thread.sleep(2000);
    }

    @Then("^select dates$")
    public void select_dates() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"frmReportDetails:StartDate_input\"]")).sendKeys("");

        driver.findElement(By.xpath("//*[@id=\"frmReportDetails:EndDate_input\"]")).sendKeys("");
        Thread.sleep(1000);
    }

    @Then("^select a Tax office \"([^\"]*)\"")
    public void select_A_tax_office_something(String taxoffice) throws Throwable {
        driver.findElement(By.cssSelector("#frmReportDetails\\:TAX_OFFICE")).click();
        Thread.sleep(2000);
        WebElement dropdown = driver.findElement(By.xpath("//*[@id=\"frmReportDetails:TAX_OFFICE_items\"]"));
        List<WebElement> entity_list = dropdown.findElements(By.tagName("li"));
        for (WebElement li : entity_list) {
            System.out.print(000);
            if (li.getText().equals(taxoffice)) {
                li.click();
                System.out.print(0000);
                //to stop the loop
                break;
            }

        }
        Thread.sleep(2000);
    }

    @Then("^run the Report$")
    public void run_the__report() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"frmReportDetails:RunReport\"]")).click();
        Thread.sleep(2000);

    }

    // @Then("^click Logout")
    //public void click___logout() throws Throwable {
    // driver.findElement(By.xpath("//*[@id=\"Logout\"]")).click();


    //}

    //-----------------------------TAXPAYER ACCOUNTING REPORTS M3_10-05-------------

    @Then("^click_on  Reporting--->Reports$")
    public void clickon_reportingreports() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"MenuForm:j_idt29\"]/ul/li[4]/a")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//*[@id=\"MenuForm:j_idt29\"]/ul/li[4]/ul/li[1]/a")).click();

    }

    @And("^click_Credit Allocation to Launch$")
    public void clickcredit_allocation_to_launch() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"ReportTree:t1:1_4:j_idt42\"]")).click();
    }

    @Then("^select on a Report Format \"([^\"]*)\"$")
    public void select_on_a_report_format_something(String format) throws Throwable {

        driver.findElement(By.cssSelector("#frmReportDetails\\:ReportFormat")).click();

        // selects the value you
        WebElement dropdown = driver.findElement(By.xpath("//*[@id=\"frmReportDetails:ReportFormat_2\"]"));
        List<WebElement> entity_list = dropdown.findElements(By.tagName("li"));
        for (WebElement li : entity_list) {
            System.out.print(000);
            if (li.getText().equals(format)) {
                li.click();
                System.out.print(0000);
                //to stop the loop
                break;
            }
        }
        Thread.sleep(2000);

    }

    @Then("^Enter TIN \"([^\"]*)\"$")
    public void enter_tin_something(String tin) throws Throwable {

        driver.findElement(By.xpath("//*[@id=\"frmReportDetails:TIN\"]")).sendKeys(tin);
    }

    @And("^select Dates$")
    public void Select_dates() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"frmReportDetails:StartDate_input\"]")).sendKeys("10/10/2019");

        driver.findElement(By.xpath("//*[@id=\"frmReportDetails:EndDate_input\"]")).sendKeys("11/10/2019");
        Thread.sleep(1000);
    }

    @And("^select tax type : nickson \"([^\"]*)\"$")
    public void select_tax_type_something(String taxtype) throws Throwable {
        //#frmReportDetails\:TAX_TYPE_label #frmReportDetails\:TAX_TYPE
        driver.findElement(By.cssSelector("#frmReportDetails\\:TAX_TYPE > div.ui-selectonemenu-trigger.ui-state-default.ui-corner-right")).click();
        Thread.sleep(2000);
        WebElement dropdown = driver.findElement(By.xpath("//*[@id=\"frmReportDetails:TAX_TYPE_items\"]"));
        List<WebElement> entity_list = dropdown.findElements(By.tagName("li"));
        for (WebElement li : entity_list) {
            System.out.print(000);
            if (li.getText().equals(taxtype)) {
                li.click();
                System.out.print(0000);
                //to stop the loop
                break;
            }

        }
        Thread.sleep(2000);

    }


    @Then("^Run the report$")
    public void Run_the_report() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"frmReportDetails:RunReport\"]")).click();
        Thread.sleep(2000);
    }

    //  @Then("^Click Logout$")
    // public void Click_Logout() throws Throwable {
    //   driver.findElement(By.xpath("//*[@id=\"Logout\"]")).click();

    // }

    //-----------------------------TAXPAYER ACCOUNTING REPORTS M3_10-06-------------

    @Then("^click_on_Reporting--->Reports$")
    public void clickonreportingreports() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"MenuForm:j_idt29\"]/ul/li[4]/a")).click();
        driver.findElement(By.xpath("//*[@id=\"MenuForm:j_idt29\"]/ul/li[4]/ul/li[1]/a")).click();
    }

    @And("^click_taxpayer account statement to Launch$")
    public void clicktaxpayer_account_statement_to_launch() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"ReportTree:t1:1_4:j_idt42\"]")).click();
    }

    @Then("^select on a report Format \"([^\"]*)\"$")
    public void Select_on_a_report_format_something(String format) throws Throwable {
        driver.findElement(By.cssSelector("#frmReportDetails\\:ReportFormat")).click();

        // selects the value you
        WebElement dropdown = driver.findElement(By.xpath("//*[@id=\"frmReportDetails:ReportFormat_2\"]"));
        List<WebElement> entity_list = dropdown.findElements(By.tagName("li"));
        for (WebElement li : entity_list) {
            System.out.print(000);
            if (li.getText().equals(format)) {
                li.click();
                System.out.print(0000);
                //to stop the loop
                break;
            }
        }
        Thread.sleep(2000);
    }

    @Then("^Enter Tin \"([^\"]*)\"$")
    public void Enter_tin_something(String tin) throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"frmReportDetails:TIN\"]")).sendKeys(tin);
    }

    @And("^select tax Type \"([^\"]*)\"$")
    public void Select_tax_type_something(String taxtype) throws Throwable {
        //#frmReportDetails\:TAX_TYPE_label #frmReportDetails\:TAX_TYPE
        driver.findElement(By.cssSelector("#frmReportDetails\\:TAX_TYPE > div.ui-selectonemenu-trigger.ui-state-default.ui-corner-right")).click();
        Thread.sleep(2000);
        WebElement dropdown = driver.findElement(By.xpath("//*[@id=\"frmReportDetails:TAX_TYPE_items\"]"));
        List<WebElement> entity_list = dropdown.findElements(By.tagName("li"));
        for (WebElement li : entity_list) {
            System.out.print(000);
            if (li.getText().equals(taxtype)) {
                li.click();
                System.out.print(0000);
                //to stop the loop
                break;
            }

        }
        Thread.sleep(2000);
    }

    @Then("^run the_report$")
    public void Run_thereport() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"frmReportDetails:RunReport\"]")).click();
        Thread.sleep(2000);
    }

    //   @Then("^Click__Logout$")
    //  public void clicklogout() throws Throwable {
    //      driver.findElement(By.xpath("//*[@id=\"Logout\"]")).click();
    //  }

    //-----------------------------TAXPAYER ACCOUNTING REPORTS M3_10-07-------------


    @Then("^click_on__Reporting--->Report$")
    public void click_onreportingreports() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"MenuForm:j_idt29\"]/ul/li[4]/a")).click();
        driver.findElement(By.xpath("//*[@id=\"MenuForm:j_idt29\"]/ul/li[4]/ul/li[1]/a")).click();
    }

    @And("^click_taxpayer account Statement to Launch$")
    public void clicktaxpayer__account_statement_to_launch() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"ReportTree:t1:1_4:j_idt42\"]")).click();
    }

    @Then("^select on a report format \"([^\"]*)\"$")
    public void select_on_a__report_format_something(String format) throws Throwable {
        driver.findElement(By.cssSelector("#frmReportDetails\\:ReportFormat")).click();

        // selects the value you
        WebElement dropdown = driver.findElement(By.xpath("//*[@id=\"frmReportDetails:ReportFormat_2\"]"));
        List<WebElement> entity_list = dropdown.findElements(By.tagName("li"));
        for (WebElement li : entity_list) {
            System.out.print(000);
            if (li.getText().equals(format)) {
                li.click();
                System.out.print(0000);
                //to stop the loop
                break;
            }
        }
        Thread.sleep(2000);
    }

    @Then("^Enter tin \"([^\"]*)\"$")
    public void enter_tin__something(String tin) throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"frmReportDetails:TIN\"]")).sendKeys(tin);

    }

    @And("^select tax_Type \"([^\"]*)\"$")
    public void select_taxtype_something(String taxtype) throws Throwable {
        //#frmReportDetails\:TAX_TYPE_label #frmReportDetails\:TAX_TYPE
        driver.findElement(By.cssSelector("#frmReportDetails\\:TAX_TYPE")).click();
        Thread.sleep(2000);
        WebElement dropdown = driver.findElement(By.xpath("//*[@id=\"frmReportDetails:TAX_TYPE_items\"]"));
        List<WebElement> entity_list = dropdown.findElements(By.tagName("li"));
        for (WebElement li : entity_list) {
            System.out.print(000);
            if (li.getText().equals(taxtype)) {
                li.click();
                System.out.print(0000);
                //to stop the loop
                break;
            }

        }
        Thread.sleep(2000);

    }

    @Then("^Select dates$")
    public void select__dates() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"frmReportDetails:StartDate_input\"]")).sendKeys("04/02/2020");

        driver.findElement(By.xpath("//*[@id=\"frmReportDetails:EndDate_input\"]")).sendKeys("05/05/2020");
        Thread.sleep(1000);
    }

    @Then("^run the_Report$")
    public void run__thereport() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"frmReportDetails:RunReport\"]")).click();
        Thread.sleep(2000);
    }

    //  @Then("^Click__logout$")
    //  public void click__logout() throws Throwable {
    //     driver.findElement(By.xpath("//*[@id=\"Logout\"]")).click();
    // }


    //-----------------------------TAXPAYER ACCOUNTING REPORTS M3_10-08-------------

    @Then("^click_on___Reporting--->Reports$")
    public void Clickonreportingreports() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"MenuForm:j_idt29\"]/ul/li[4]/a")).click();
        driver.findElement(By.xpath("//*[@id=\"MenuForm:j_idt29\"]/ul/li[4]/ul/li[1]/a")).click();
    }

    @And("^click ageing debts report  to Launch$")
    public void click_ageing_debts_report_to_launch() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"ReportTree:t1:1_1:j_idt42\"]")).click();
    }

    @And("^select tax_type \"([^\"]*)\"$")
    public void Select_taxtype_something(String taxtype) throws Throwable {
        driver.findElement(By.cssSelector("#frmReportDetails\\:TAX_TYPE")).click();
        Thread.sleep(2000);
        WebElement dropdown = driver.findElement(By.xpath("//*[@id=\"frmReportDetails:TAX_TYPE_items\"]"));
        List<WebElement> entity_list = dropdown.findElements(By.tagName("li"));
        for (WebElement li : entity_list) {
            System.out.print(000);
            if (li.getText().equals(taxtype)) {
                li.click();
                System.out.print(0000);
                //to stop the loop
                break;
            }

        }
        Thread.sleep(2000);

    }

    @Then("^select on a report_format \"([^\"]*)\"$")
    public void select_on_a_reportformat_something(String format) throws Throwable {
        driver.findElement(By.cssSelector("#frmReportDetails\\:ReportFormat")).click();

        // selects the value you
        WebElement dropdown = driver.findElement(By.xpath("//*[@id=\"frmReportDetails:ReportFormat_2\"]"));
        List<WebElement> entity_list = dropdown.findElements(By.tagName("li"));
        for (WebElement li : entity_list) {
            System.out.print(000);
            if (li.getText().equals(format)) {
                li.click();
                System.out.print(0000);
                //to stop the loop
                break;
            }
        }
        Thread.sleep(2000);
    }


    @Then("^select a taxoffice \"([^\"]*)\"$")
    public void select_a_taxoffice(String taxoffice) throws Throwable {

        driver.findElement(By.cssSelector("#frmReportDetails\\:TAX_OFFICE")).click();
        Thread.sleep(2000);
        WebElement dropdown = driver.findElement(By.xpath("//*[@id=\"frmReportDetails:TAX_OFFICE_items\"]"));
        List<WebElement> entity_list = dropdown.findElements(By.tagName("li"));
        for (WebElement li : entity_list) {
            System.out.print(000);
            if (li.getText().equals(taxoffice)) {
                li.click();
                System.out.print(0000);
                //to stop the loop
                break;
            }

        }
        Thread.sleep(2000);
    }

    @Then("^run the__Report$")
    public void Run__thereport() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"frmReportDetails:RunReport\"]")).click();
        Thread.sleep(2000);
    }

    //  @Then("^Click___logout$")
    // public void Clicklogout() throws Throwable {
    //     driver.findElement(By.xpath("//*[@id=\"Logout\"]")).click();

    // }

    //-----------------------------TAXPAYER ACCOUNTING REPORTS M3_10-09-------------
    @Then("^click__on___Reporting--->Reports$")
    public void ClickonReportingreports() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"MenuForm:j_idt29\"]/ul/li[4]/a")).click();
        driver.findElement(By.xpath("//*[@id=\"MenuForm:j_idt29\"]/ul/li[4]/ul/li[1]/a")).click();
    }

    @And("^click Ageing debts report  to Launch$")
    public void click_Ageing_debts_report_to_launch() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"ReportTree:t1:1_1:j_idt42\"]")).click();
    }

    @And("^select Tax_type \"([^\"]*)\"$")
    public void Select_Taxtype_something(String taxtype) throws Throwable {
        driver.findElement(By.cssSelector("#frmReportDetails\\:TAX_TYPE")).click();
        Thread.sleep(2000);
        WebElement dropdown = driver.findElement(By.xpath("//*[@id=\"frmReportDetails:TAX_TYPE_items\"]"));
        List<WebElement> entity_list = dropdown.findElements(By.tagName("li"));
        for (WebElement li : entity_list) {
            System.out.print(000);
            if (li.getText().equals(taxtype)) {
                li.click();
                System.out.print(0000);
                //to stop the loop
                break;
            }

        }
        Thread.sleep(2000);
    }

    @Then("^select on a Report_format \"([^\"]*)\"$")
    public void Select_on_a_reportformat_something(String format) throws Throwable {
        driver.findElement(By.cssSelector("#frmReportDetails\\:ReportFormat")).click();

        // selects the value you
        WebElement dropdown = driver.findElement(By.xpath("//*[@id=\"frmReportDetails:ReportFormat_2\"]"));
        List<WebElement> entity_list = dropdown.findElements(By.tagName("li"));
        for (WebElement li : entity_list) {
            System.out.print(000);
            if (li.getText().equals(format)) {
                li.click();
                System.out.print(0000);
                //to stop the loop
                break;
            }
        }
        Thread.sleep(2000);

    }


    @Then("^select a Taxoffice \"([^\"]*)\"$")
    public void select_a_taxoffice_something(String taxoffice) throws Throwable {
        driver.findElement(By.cssSelector("#frmReportDetails\\:TAX_OFFICE")).click();
        Thread.sleep(2000);
        WebElement dropdown = driver.findElement(By.xpath("//*[@id=\"frmReportDetails:TAX_OFFICE_items\"]"));
        List<WebElement> entity_list = dropdown.findElements(By.tagName("li"));
        for (WebElement li : entity_list) {
            System.out.print(000);
            if (li.getText().equals(taxoffice)) {
                li.click();
                System.out.print(0000);
                //to stop the loop
                break;
            }

        }
        Thread.sleep(2000);
    }

    @Then("^Run the__Report$")
    public void run__theReport() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"frmReportDetails:RunReport\"]")).click();
        Thread.sleep(2000);
    }

    // @Then("^click___logout$")
    // public void clickLogout() throws Throwable {
    //     driver.findElement(By.xpath("//*[@id=\"Logout\"]")).click();
    // }


    //----------------------------------PRINT REVENUE ACCOUNTING REPORTS----------------------
// 01
    @Then("^Navigate to Reporting-->Reports$")
    public void navigate_to_reportingreports() throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver,30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"MenuForm:j_idt27\"]/ul/li[4]/a"))).click();

        driver.findElement(By.xpath("//*[@id=\"MenuForm:j_idt27\"]/ul/li[4]/ul/li[1]/a")).click();
        Thread.sleep(1000);
    }

    public boolean isFileDownloaded(String downloadPath, String fileName) {
        File dir = new File(downloadPath);
        File[] dirContents = dir.listFiles();
        for (int i = 0; i < dirContents.length; i++) {
            if (dirContents[i].getName().equals(fileName)) {
                // File has been found, it can now be deleted:
                dirContents[i].delete();
                return true;
            }
        }
        return false;
    }

    @Then("^Verify file \"([^\"]*)\" has been downloaded in downloads directory \"([^\"]*)\"$")
    public void verify_file_has_been_downloaded_in_downloads_directory(String fileName, String downloadPath) throws Throwable {
        Thread.sleep(5000);
        if (isFileDownloaded(downloadPath, fileName)) {
            System.out.println(fileName + ": has been downloaded");
            Assert.assertTrue(true);
        } else {
            Assert.assertFalse(fileName + ": has not been downloaded", false);
        }
    }

    @Then("^Click  Revenue Accounting-->Revenue Ledger Details Report$")
    public void click_revenue_accountingrevenue_ledger_details_report() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"ReportTree:t1:6:j_idt42\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"ReportTree:t1:6_3:j_idt42\"]")).click();
        Thread.sleep(1000);
    }


    @And("^Select Report_Format \"([^\"]*)\"$")
    public void select_reportformat_something(String report_format) throws Throwable {
        driver.findElement(By.cssSelector("#frmReportDetails\\:ReportFormat")).click();
        // selects the value you
        WebElement dropdown = driver.findElement(By.xpath("//*[@id=\"frmReportDetails:ReportFormat_2\"]"));
        List<WebElement> entity_list = dropdown.findElements(By.tagName("li"));
        for (WebElement li : entity_list) {
            System.out.print(000);
            if (li.getText().equals(report_format)) {
                li.click();
                System.out.print(0000);
                //to stop the loop
                break;
            }
        }
        Thread.sleep(4000);

    }

    @Then("^Select report file type \"([^\"]*)\"$")
    public void select_report_file_type(String reportFormat) throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"frmReportDetails:ReportFormat\"]/div[3]"))).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//li[contains(text(),'" + reportFormat + "')]")).click();
        Thread.sleep(1000);

    }

    @And("^Enter  Start Date and click run report$")
    public void enter_start_date_and_click_run_report() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"frmReportDetails:TRANSACTION_DATE_input\"]")).sendKeys("05/07/2020");
        driver.findElement(By.xpath("//*[@id=\"frmReportDetails:RunReport\"]")).click();
        Thread.sleep(2000);
    }

    //2
    @Then("^navigate to Reporting-->Reports$")
    public void navigate_to__reportingreports() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"MenuForm:j_idt29\"]/ul/li[4]/a")).click();
        driver.findElement(By.xpath("//*[@id=\"MenuForm:j_idt29\"]/ul/li[4]/ul/li[1]/a")).click();
    }

    @Then("^Click  Revenue Accounting--->Revenue Ledger Details Report$")
    public void click_revenue_accountingchart_of_accounts_report() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"ReportTree:t1:6:j_idt42\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"ReportTree:t1:6_3:j_idt42\"]")).click();
    }
    @Then("^Select report to print \"([^\"]*)\"$")
    public void select_report_to_print(String reportType) throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver,20);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='" + reportType + "']"))).click();

    }

    @Then("^click run report$")
    public void click_run_report() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"frmReportDetails:RunReport\"]")).click();
        Thread.sleep(4000);
    }

    @And("^select Report_Format \"([^\"]*)\"$")
    public void select__reportformat_something(String report_format) throws Throwable {
        driver.findElement(By.cssSelector("#frmReportDetails\\:ReportFormat")).click();
        // selects the value you
        WebElement dropdown = driver.findElement(By.xpath("//*[@id=\"frmReportDetails:ReportFormat_2\"]"));
        List<WebElement> entity_list = dropdown.findElements(By.tagName("li"));
        for (WebElement li : entity_list) {
            System.out.print(000);
            if (li.getText().equals(report_format)) {
                li.click();
                System.out.print(0000);
                //to stop the loop
                break;
            }
        }
        Thread.sleep(4000);

        driver.findElement(By.xpath("//*[@id=\"frmReportDetails:TRANSACTION_DATE_input\"]")).sendKeys("27/07/2020");
    }


    //3
    @Then("^navigate to reporting-->Reports$")
    public void Navigate_to_reportingreports() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"MenuForm:j_idt29\"]/ul/li[4]/a")).click();
        driver.findElement(By.xpath("//*[@id=\"MenuForm:j_idt29\"]/ul/li[4]/ul/li[1]/a")).click();
    }

    @Then("^Click  Revenue Accounting-->Chart of Accounts Report$")
    public void Click_revenue_accountingchart_of_accounts_report() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"ReportTree:t1:6:j_idt42\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"ReportTree:t1:6_0:j_idt42\"]")).click();
    }

    @Then("^Click run report$")
    public void Click_run_report() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"frmReportDetails:RunReport\"]")).click();
        Thread.sleep(2000);
    }

    @And("^select Report_format \"([^\"]*)\"$")
    public void select_reportformat__something(String report_format) throws Throwable {
        driver.findElement(By.cssSelector("#frmReportDetails\\:ReportFormat")).click();
        // selects the value you
        WebElement dropdown = driver.findElement(By.xpath("//*[@id=\"frmReportDetails:ReportFormat_2\"]"));
        List<WebElement> entity_list = dropdown.findElements(By.tagName("li"));
        for (WebElement li : entity_list) {
            System.out.print(000);
            if (li.getText().equals(report_format)) {
                li.click();
                System.out.print(0000);
                //to stop the loop
                break;
            }
        }
        Thread.sleep(2000);
    }

    @And("^Select \"([^\"]*)\"$")
    public void Select_something(String tax_type) throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"frmReportDetails:TAX_TYPE\"]/div[3]"))).click();
        Thread.sleep(1000);
        Actions action = new Actions(driver);
        action.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();
    }

    //4
    @Then("^navigate To Reporting-->Reports$")
    public void Navigate_To_reportingreports() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"MenuForm:j_idt29\"]/ul/li[4]/a")).click();
        driver.findElement(By.xpath("//*[@id=\"MenuForm:j_idt29\"]/ul/li[4]/ul/li[1]/a")).click();
        Thread.sleep(1000);
    }

    @Then("^Click  Revenue Accounting--->Chart of Accounts Report$")
    public void Click_Revenue_accountingchart_of_accounts_report() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"ReportTree:t1:6:j_idt42\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"ReportTree:t1:6_0:j_idt42\"]")).click();
        Thread.sleep(1000);
    }

    @Then("^Click Run report$")
    public void click_run_Report() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"frmReportDetails:RunReport\"]")).click();
        Thread.sleep(2000);
        ;
    }

    @And("^select Report__format \"([^\"]*)\"$")
    public void select_Reportformat_something(String report_format) throws Throwable {
        driver.findElement(By.cssSelector("#frmReportDetails\\:ReportFormat")).click();
        // selects the value you
        WebElement dropdown = driver.findElement(By.xpath("//*[@id=\"frmReportDetails:ReportFormat_2\"]"));
        List<WebElement> entity_list = dropdown.findElements(By.tagName("li"));
        for (WebElement li : entity_list) {
            System.out.print(000);
            if (li.getText().equals(report_format)) {
                li.click();
                System.out.print(0000);
                //to stop the loop
                break;
            }
        }
        Thread.sleep(2000);
    }

    @Then("^Enter dates$")
    public void enter_dates() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"frmReportDetails:StartDate_input\"]")).sendKeys("10/06/2020");
        driver.findElement(By.xpath("//*[@id=\"frmReportDetails:EndDate_input\"]")).sendKeys("11/06/2020");
    }

    @And("^Select a \"([^\"]*)\"$")
    public void select_a_something(String tax_type) throws Throwable {
        driver.findElement(By.cssSelector("#frmReportDetails\\:TAX_TYPE")).click();
        Thread.sleep(2000);
        WebElement dropdown = driver.findElement(By.xpath("//*[@id=\"frmReportDetails:TAX_TYPE_items\"]"));
        List<WebElement> entity_list = dropdown.findElements(By.tagName("li"));
        for (WebElement li : entity_list) {
            System.out.print(000);
            if (li.getText().equals(tax_type)) {
                li.click();
                System.out.print(0000);
                //to stop the loop
                break;
            }

        }
        Thread.sleep(5000);
    }

    //5
    @Then("^navigate To Reporting--->Reports$")
    public void navigate__to__reportingreports() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"MenuForm:j_idt29\"]/ul/li[4]/a")).click();
        driver.findElement(By.xpath("//*[@id=\"MenuForm:j_idt29\"]/ul/li[4]/ul/li[1]/a")).click();
        Thread.sleep(1000);
    }

    @Then("^click  Revenue Accounting--->Revenue Growth Analysis Report$")
    public void click_revenue_accountingrevenue_growth_analysis_report() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"ReportTree:t1:6:j_idt42\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"ReportTree:t1:6_2:j_idt42\"]")).click();
        Thread.sleep(1000);
    }

    @And("^select Report__Format \"([^\"]*)\"$")
    public void Select_reportformat_something(String report_format) throws Throwable {
        driver.findElement(By.cssSelector("#frmReportDetails\\:ReportFormat")).click();
        // selects the value you
        WebElement dropdown = driver.findElement(By.xpath("//*[@id=\"frmReportDetails:ReportFormat_2\"]"));
        List<WebElement> entity_list = dropdown.findElements(By.tagName("li"));
        for (WebElement li : entity_list) {
            System.out.print(000);
            if (li.getText().equals(report_format)) {
                li.click();
                System.out.print(0000);
                //to stop the loop//
                break;
            }
        }
        Thread.sleep(5000);
    }

    @And("^Select the \"([^\"]*)\"$")
    public void select_the_something(String tax_type) throws Throwable {
        driver.findElement(By.cssSelector("#frmReportDetails\\:TAX_TYPE")).click();
        Thread.sleep(2000);
        WebElement dropdown = driver.findElement(By.xpath("//*[@id=\"frmReportDetails:TAX_TYPE_items\"]"));
        List<WebElement> entity_list = dropdown.findElements(By.tagName("li"));
        for (WebElement li : entity_list) {
            System.out.print(000);
            if (li.getText().equals(tax_type)) {
                li.click();
                System.out.print(0000);
                //to stop the loop
                break;
            }

        }
        Thread.sleep(2000);
    }


    @And("^Select station \"([^\"]*)\"$")
    public void select_station_something(String station) throws Throwable {
        driver.findElement(By.cssSelector("#frmReportDetails\\:TAX_OFFICE")).click();
        Thread.sleep(2000);
        WebElement dropdown = driver.findElement(By.xpath("//*[@id=\"frmReportDetails:TAX_OFFICE_items\"]"));
        List<WebElement> entity_list = dropdown.findElements(By.tagName("li"));
        for (WebElement li : entity_list) {
            System.out.print(000);
            if (li.getText().equals(station)) {
                li.click();
                System.out.print(0000);
                //to stop the loop
                break;
            }

        }
        Thread.sleep(2000);
    }

    @Then("^click on Cancel$")
    public void Click_on_cancel() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"frmReportDetails:btnCancel\"]")).click();
        Thread.sleep(4000);
    }

    //--------------------------SET REVENUE TARGET------------------------------///
//x
    @Then("^Navigate to Revenue Accounting System->Maintain Revenue Targets->Maintain Treasury Target$")
    public void navigate_to_revenue_accounting_systemmaintain_revenue_targetsmaintain_treasury_target() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"MenuForm:j_idt27\"]/ul/li[3]/a")).click();
        Thread.sleep(5000);
        driver.findElement(By.xpath("//*[@id=\"MenuForm:j_idt27\"]/ul/li[3]/ul/li[1]/a")).click();
        driver.findElement(By.xpath("//*[@id=\"sub1\"]/ul/li[1]/a")).click();
        Thread.sleep(5000);
    }

    @Then("^Click  on Add button$")
    public void click_on_add_button() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"MaintainTreasuryTarget:AddTreasuryTarget\"]")).click();
        Thread.sleep(5000);
    }

    @Then("Click update in Mantain treasury target screen")
    public void clickUpdateInMantainTreasuryTargetScreen() throws Throwable{
        Thread.sleep(2000);
        driver.findElement(By.id("MaintainTreasuryTarget:EditTreasuryTarget")).click();
    }

    @Then("Modify percentage of Management Target to {string}")
    public void modifyPercentageOfManagementTargetTo(String target) {
        WebDriverWait wait = new WebDriverWait(driver,30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("treasuryTargetAllotment:TreasuryPercentage"))).sendKeys(target);
        driver.findElement(By.id("treasuryTargetAllotment:idSave")).click();
        driver.switchTo().defaultContent();
    }

////#treasuryTargetAllotment\:TreasuryYear//*[@id="treasuryTargetAllotment:TreasuryYear_items"]//*[@id="treasuryTargetAllotment:TreasuryPercentage"]//*[@id="treasuryTargetAllotment:idSave"]

    //y
    @Then("^Navigate To Revenue Accounting System->Maintain Revenue Targets->Maintain Treasury Target$")
    public void Navigate_to_revenue_accounting_systemmaintain_revenue_targetsmaintain_treasury_target() throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver,30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"MenuForm:j_idt29\"]/ul/li[3]"))).click();

        driver.findElement(By.xpath("/html/body/div[2]/form/div/ul/li[3]/ul/li[1]")).click();
        driver.findElement(By.xpath("/html/body/div[2]/form/ul/li/ul/li[1]")).click();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        Thread.sleep(6000);
    }

    @Then("^Click  on Add Button$")
    public void Click_on_add_button() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"MaintainTreasuryTarget:AddTreasuryTarget\"]")).click();
        Thread.sleep(5000);

        Thread.sleep(3000);
        WebElement AddCategoryIframe = driver.findElement(By.tagName("iframe"));
        driver.switchTo().frame(AddCategoryIframe);
        Thread.sleep(2000);
        // driver.findElement(By.xpath("//*[@id=\"treasuryTargetAllotment:cancelButton\"]")).click();
    }


    @And("^Select \"([^\"]*)\" and percentage then click ok$")
    public void enter_required_data_and_click_ok_button(String year) throws Throwable {
//open status dropdown
        driver.findElement(By.xpath("//*[@id=\"treasuryTargetAllotment:TreasuryYear\"]/div[3]")).click();
        Thread.sleep(1000);
//select status
        String StatusXpath = "//li[@data-label='"+year+"']";
        driver.findElement(By.xpath(StatusXpath)).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"treasuryTargetAllotment:TreasuryPercentage\"]")).sendKeys("30");
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"treasuryTargetAllotment:idSave\"]")).click();
        driver.switchTo().defaultContent();
        Thread.sleep(2000);
    }

    //z
    @Then("^navigate to Revenue Accounting System->Maintain Revenue Targets->Maintain Management Target$")
    public void navigate_to_revenue_accounting__systemmaintain_revenue_targetsmaintain_treasury_target() throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver,30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"MenuForm:j_idt27\"]/ul/li[3]/a"))).click();
        driver.findElement(By.xpath("//*[@id=\"MenuForm:j_idt27\"]/ul/li[3]/ul/li[1]/a")).click();
        driver.findElement(By.xpath("//*[@id=\"sub1\"]/ul/li[2]/a")).click();

    }

    @Then("^Click  on add button$")
    public void click__on_add_button() throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver,30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"MaintainRevenueTarget:j_idt57\"]"))).click();

        WebElement AddCategoryIframe = wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("iframe")));
        driver.switchTo().frame(AddCategoryIframe);
        Thread.sleep(2000);

        driver.findElement(By.xpath("/html/body/div[1]/form/div/div/div[3]/button[2]/span")).click();
        driver.switchTo().defaultContent();
        Thread.sleep(9000);
    }

    //x_1
    @Then("^Navigate To Revenue Accounting System->Maintain Revenue Targets-->Maintain Management  Target$")
    public void navigate_to_revenue_accounting_systemmaintain_revenue_targetsmaintain_management_target() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"MenuForm:j_idt29\"]/ul/li[3]")).click();
        driver.findElement(By.xpath("/html/body/div[2]/form/div/ul/li[3]/ul/li[1]")).click();
        driver.findElement(By.xpath("/html/body/div[2]/form/ul/li/ul/li[2]/a")).click();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        Thread.sleep(6000);
    }

    @Then("^Enter the data required and click search$")
    public void enter_the_data_required_and_click_search() throws Throwable {
        //open status dropdown
        driver.findElement(By.xpath("//*[@id=\"MaintainRevenueTarget:TargetYear\"]/div[3]")).click();
        Thread.sleep(5000);
        //select status
        String StatusXpath = "//li[@data-label='2018']";
        driver.findElement(By.xpath(StatusXpath)).click();
        Thread.sleep(5000);
        //open status dropdown//*[@id="MaintainRevenueTarget:TargetMonth"]/div[3]
        driver.findElement(By.xpath("//*[@id=\"MaintainRevenueTarget:TargetMonth\"]/div[3]")).click();
        Thread.sleep(5000);
        //select status
        String MonthXpath = "//li[@data-label='January']";
        driver.findElement(By.xpath(MonthXpath)).click();
        Thread.sleep(5000);
        //open status dropdown
        driver.findElement(By.xpath("//*[@id=\"MaintainRevenueTarget:TargetTaxType\"]/div[3]")).click();
        Thread.sleep(5000);
        //select status
        String TaxtypePath = "//li[@data-label='Domestic VAT']";
        driver.findElement(By.xpath(TaxtypePath)).click();
        Thread.sleep(5000);
        //open status dropdown
        driver.findElement(By.xpath("//*[@id=\"MaintainRevenueTarget:TargetStation\"]")).click();
        Thread.sleep(1000);
        //select status /html/body/div[10]/div/ul/li[21]data-label
        String StationXpath = "/html/body/div[10]/div/ul/li[21]";
        driver.findElement(By.xpath(StationXpath)).click();
        Thread.sleep(8000);


        driver.findElement(By.xpath("//*[@id=\"MaintainRevenueTarget:j_idt55\"]")).click();
        Thread.sleep(5000);

    }

    @Then("^Select on data and update$")
    public void select_on_data_and_update() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"MaintainRevenueTarget:revenueTargetTableHandler_data\"]/tr")).click();
        driver.findElement(By.xpath("//*[@id=\"MaintainRevenueTarget:UpdateButton\"]")).click();
    }

    @Then("^Change the month to \"([^\"]*)\"$")
    public void update_tax_office_target_details(String month) throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver,10);
        WebElement AddCategoryIframe = wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("iframe")));
        driver.switchTo().frame(AddCategoryIframe);

        //open status dropdown
        driver.findElement(By.xpath("//*[@id=\"SetRevenueTarget:TargetMonth\"]/div[3]")).click();
        Thread.sleep(1000);
        //select status
        String StatusXpath = "//li[@data-label='"+month+"']";
        driver.findElement(By.xpath(StatusXpath)).click();
        Thread.sleep(1000);
    }

    @And("^Click ok$")
    public void click_ok() throws Throwable {

    }


    //y_1
    @Then("^Navigate To revenue Accounting System->Maintain Revenue Targets-->Maintain Management  Target$")
    public void navigate_to_revenue_accounting__systemmaintain_revenue_targetsmaintain_management_target() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"MenuForm:j_idt29\"]/ul/li[3]")).click();
        driver.findElement(By.xpath("/html/body/div[2]/form/div/ul/li[3]/ul/li[1]")).click();
        driver.findElement(By.xpath("/html/body/div[2]/form/ul/li/ul/li[2]/a")).click();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        Thread.sleep(6000);
    }

    @Then("^Click add button : set management target$")
    public void click_add_button_in_set_revenue_target() throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("MaintainRevenueTarget:j_idt55"))).click();
    }

    @Then("Switch to frame : backoffice")
    public void switchToFrameBackoffice() {
        WebDriverWait wait = new WebDriverWait(driver,10);
        WebElement Iframe = wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("iframe")));
        driver.switchTo().frame(Iframe);
    }

    @Then("^Fill in year as \"([^\"]*)\" and other necessary details$")
    public void enter_data(String year) throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver,10);
        WebElement AddCategoryIframe = wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("iframe")));
        driver.switchTo().frame(AddCategoryIframe);

        //open status dropdown
        driver.findElement(By.xpath("//*[@id=\"SetRevenueTarget:TargetYear\"]/div[3]")).click();
        Thread.sleep(1000);
        //select status
        String StatusXpath = "//li[@data-label='"+year+"']";
        driver.findElement(By.xpath(StatusXpath)).click();
        Thread.sleep(1000);


        //open status dropdown//*[@id="MaintainRevenueTarget:TargetMonth"]/div[3]
        driver.findElement(By.xpath("//*[@id=\"SetRevenueTarget:TargetMonth\"]/div[3]")).click();
        Thread.sleep(1000);
        //select status
        String MonthXpath = "//li[@data-label='January']";
        driver.findElement(By.xpath(MonthXpath)).click();
        Thread.sleep(1000);


        //open status dropdown
        driver.findElement(By.xpath("//*[@id=\"SetRevenueTarget:TargetStation\"]/div[3]")).click();
        Thread.sleep(1000);
        //select status
        String StationXpath = "//li[@data-label='BOMTO']";
        driver.findElement(By.xpath(StationXpath)).click();
        Thread.sleep(1000);

        //open status dropdown
        driver.findElement(By.xpath("//*[@id=\"SetRevenueTarget:TargetTaxType\"]/div[3]")).click();
        Thread.sleep(1000);
        //select status
        String TaxtypePath = "//li[@data-label='Company Income Tax']";
        driver.findElement(By.xpath(TaxtypePath)).click();
        Thread.sleep(1000);


        driver.findElement(By.xpath("//*[@id=\"SetRevenueTarget:ManagementTarget_input\"]")).sendKeys("51");
        Thread.sleep(1000);


    }

    @And("^Click the ok button$")
    public void click_the_ok_button() throws Throwable {

        driver.findElement(By.id("SetRevenueTarget:Ok")).click();
        driver.switchTo().defaultContent();

    }


    @And("^Click the ok without filling data$")
    public void click_the_ok_without_data() throws Throwable {

        driver.findElement(By.id("SetRevenueTarget:Ok")).click();


    }



//z_1

    @Then("^Navigate to Revenue Accounting system->Maintain Revenue Targets-->Maintain Management  Target$")
    public void navigate_to__revenue_accounting_systemmaintain_revenue_targetsmaintain_management_target() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"MenuForm:j_idt29\"]/ul/li[3]")).click();
        driver.findElement(By.xpath("/html/body/div[2]/form/div/ul/li[3]/ul/li[1]")).click();
        driver.findElement(By.xpath("/html/body/div[2]/form/ul/li/ul/li[2]/a")).click();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        Thread.sleep(6000);
    }

    @Then("^Search for data with year \"([^\"]*)\", Month \"([^\"]*)\", and Tax type \"([^\"]*)\"$")
    public void enter_the_test_data_required_and_click_view_button(String year, String month, String taxtype) throws Throwable {
        //open status dropdown
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"MaintainRevenueTarget:TargetYear\"]/div[3]"))).click();
        Thread.sleep(1000);
        //select status
        String StatusXpath = "//li[@data-label='"+year+"']";
        driver.findElement(By.xpath(StatusXpath)).click();
        Thread.sleep(1000);
        //open status dropdown//*[@id="MaintainRevenueTarget:TargetMonth"]/div[3]
        driver.findElement(By.xpath("//*[@id=\"MaintainRevenueTarget:TargetMonth\"]/div[3]")).click();
        Thread.sleep(1000);
        //select status
        String MonthXpath = "//li[@data-label='"+month+"']";
        driver.findElement(By.xpath(MonthXpath)).click();
        Thread.sleep(1000);

        driver.findElement(By.xpath("//*[@id=\"MaintainRevenueTarget:TargetStation\"]/div[3]")).click();
        Thread.sleep(1000);
        Actions action = new Actions(driver);
        action.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.TAB).perform();
        Thread.sleep(3000);

        driver.findElement(By.xpath("//*[@id=\"MaintainRevenueTarget:TargetTaxType\"]/div[3]")).click();
        Thread.sleep(1000);
        String TaxtypePath = "//li[@data-label='"+taxtype+"']";
        driver.findElement(By.xpath(TaxtypePath)).click();
        Thread.sleep(1000);


         driver.findElement(By.id("MaintainRevenueTarget:j_idt53")).click();
    }

    @Then("^Confirm data appears in table \"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\"$")
    public void confirmDataAppearsInTable(String year, String month, String taxtype) {
         WebDriverWait wait = new WebDriverWait(driver,10);
         WebElement yearColumn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[contains(text(),'" + year + "')]")));
         Assert.assertTrue(yearColumn.isDisplayed());
         Assert.assertTrue(driver.findElement(By.xpath("//td[contains(text(),'" + month + "')]")).isDisplayed());
         Assert.assertTrue(driver.findElement(By.xpath("//td[contains(text(),'" + taxtype + "')]")).isDisplayed());
    }

    @Then("^Verify no data is found in table$")
    public void verify_no_data_is_found_in_table() throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver,30);
        WebElement noDataXpath = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[contains(text(),'No records found.')]")));
        if (noDataXpath.isDisplayed()) {
            Assert.assertTrue("No data found in table", true);
        } else {
            Assert.assertFalse("Data found in table", false);
        }
    }


    @Then("^click cancel$")
    public void click_cancel() throws Throwable {
//*[@id="SetRevenueTarget:Cancel"]
        Thread.sleep(5000);
        driver.findElement(By.xpath("//*[@id=\"SetRevenueTarget:Cancel\"]")).click();
    }


    //x_2
    @Then("^Navigate to Revenue Accounting System->Maintain Revenue Targets->Maintain Management  Target$")
    public void navigate_to_revenue_accounting___systemmaintain_revenue_targetsmaintain_management_target() throws Throwable {
        Thread.sleep(5000);
        driver.findElement(By.xpath("//*[@id=\"MenuForm:j_idt29\"]/ul/li[3]")).click();
        driver.findElement(By.xpath("/html/body/div[2]/form/div/ul/li[3]/ul/li[1]")).click();
        driver.findElement(By.xpath("/html/body/div[2]/form/ul/li/ul/li[2]/a")).click();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        Thread.sleep(6000);
    }

    @Then("^Click  on the Add button$")
    public void click_on_the_add_button() throws Throwable {
        driver.findElement(By.xpath("/html/body/div[3]/div/div[1]/div/form/button[1]")).click();
        Thread.sleep(5000);
    }

    @Then("^Enter the data required$")
    public void enter_the_data_required() throws Throwable {
        Thread.sleep(3000);
        WebElement AddCategoryIframe = driver.findElement(By.tagName("iframe"));
        driver.switchTo().frame(AddCategoryIframe);
        Thread.sleep(2000);

        //open status dropdown
        driver.findElement(By.xpath("//*[@id=\"SetRevenueTarget:TargetYear\"]/div[3]")).click();
        Thread.sleep(5000);
        //select status
        String StatusXpath = "//li[@data-label='2018']";
        driver.findElement(By.xpath(StatusXpath)).click();
        Thread.sleep(5000);


        //open status dropdown//*[@id="MaintainRevenueTarget:TargetMonth"]/div[3]
        driver.findElement(By.xpath("//*[@id=\"SetRevenueTarget:TargetMonth\"]/div[3]")).click();
        Thread.sleep(5000);
        //select status
        String MonthXpath = "//li[@data-label='January']";
        driver.findElement(By.xpath(MonthXpath)).click();
        Thread.sleep(5000);


        //open status dropdown
        driver.findElement(By.xpath("//*[@id=\"SetRevenueTarget:TargetStation\"]/div[3]")).click();
        Thread.sleep(5000);
        //select status
        String StationXpath = "//li[@data-label='Zomba']";
        driver.findElement(By.xpath(StationXpath)).click();
        Thread.sleep(5000);

        //open status dropdown
        driver.findElement(By.xpath("//*[@id=\"SetRevenueTarget:TargetTaxType\"]/div[3]")).click();
        Thread.sleep(5000);
        //select status
        String TaxtypePath = "//li[@data-label='Domestic VAT']";
        driver.findElement(By.xpath(TaxtypePath)).click();
        Thread.sleep(5000);


        driver.findElement(By.xpath("//*[@id=\"SetRevenueTarget:ManagementTarget_input\"]")).sendKeys("81");
        Thread.sleep(2000);


        driver.findElement(By.xpath("/html/body/div[1]/form/div/div/div[3]/button[1]")).click();
        driver.switchTo().defaultContent();
        Thread.sleep(9000);


    }

//x_3

    @Then("^Navigate to revenue accounting system->Maintain Revenue Targets->Maintain Treasury Target$")
    public void Navigate_to_revenue_accounting_systemmaintain_revenue_targetsmaintain_Treasury_target() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"MenuForm:j_idt29\"]/ul/li[3]")).click();
        driver.findElement(By.xpath("/html/body/div[2]/form/div/ul/li[3]/ul/li[1]")).click();
        driver.findElement(By.xpath("/html/body/div[2]/form/ul/li/ul/li[1]")).click();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        Thread.sleep(6000);
    }

    @Then("^click on view  button$")
    public void click_on__view_button() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"MaintainTreasuryTarget:ViewCreditAllocation\"]")).click();
        Thread.sleep(6000);

    }

    @Then("^Select year to be viewed and click on view button$")
    public void select_year_to_be_viewed_and_click_on_view_button() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"MaintainTreasuryTarget:MaintainTreasuryTargetTableHandler_data\"]/tr[4]")).click();
        Thread.sleep(6000);
        driver.findElement(By.xpath("//*[@id=\"MaintainTreasuryTarget:ViewCreditAllocation\"]")).click();
        Thread.sleep(6000);
    }


//z_2

    @Then("^Navigate to Revenue Accounting System-->Maintain Revenue Targets->Maintain Treasury Target$")
    public void navigate__to_revenue_accounting_systemmaintain_revenue_targetsmaintain_treasury_target() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"MenuForm:j_idt29\"]/ul/li[3]")).click();
        driver.findElement(By.xpath("/html/body/div[2]/form/div/ul/li[3]/ul/li[1]")).click();
        driver.findElement(By.xpath("/html/body/div[2]/form/ul/li/ul/li[1]")).click();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        Thread.sleep(6000);
    }

    @Then("^select one record and click update button$")
    public void select_one_record_and_click_update_button() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"MaintainTreasuryTarget:MaintainTreasuryTargetTableHandler_data\"]/tr[4]")).click();
        Thread.sleep(6000);
        driver.findElement(By.xpath("//*[@id=\"MaintainTreasuryTarget:EditTreasuryTarget\"]")).click();
        Thread.sleep(3000);

    }

    @Then("^Edit year and treasury target$")
    public void edit_year_and_treasury_target() throws Throwable {
        Thread.sleep(3000);
        WebElement AddCategoryIframe = driver.findElement(By.tagName("iframe"));
        driver.switchTo().frame(AddCategoryIframe);
        Thread.sleep(2000);
        ////*[@id="treasuryTargetAllotment:TreasuryPercentage"]
        driver.findElement(By.xpath("//*[@id=\"treasuryTargetAllotment:TreasuryPercentage\"]")).clear();
        driver.findElement(By.xpath("//*[@id=\"treasuryTargetAllotment:TreasuryPercentage\"]")).sendKeys("78");
        Thread.sleep(6000);

    }

    @Then("^click ok$")
    public void Click_ok() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"treasuryTargetAllotment:idSave\"]")).click();
        driver.switchTo().defaultContent();
        Thread.sleep(6000);
    }

    //y_2
    @Then("^Navigate to Revenue Accounting System\\-\\-\\-\\->Maintain Revenue Targets\\->Maintain Management  Target$$")
    public void navigate_to_revenue_accounting_systemmaintain_revenue_targetsmaintain_management_Target() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"MenuForm:j_idt29\"]/ul/li[3]")).click();
        driver.findElement(By.xpath("/html/body/div[2]/form/div/ul/li[3]/ul/li[1]")).click();
        driver.findElement(By.xpath("/html/body/div[2]/form/ul/li/ul/li[2]/a")).click();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        Thread.sleep(6000);
    }

    @Then("^click view button$")
    public void click_view_button() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"MaintainRevenueTarget:j_idt62\"]")).click();
    }

    @Then("^enter all data  and click ok$")
    public void enter_all_data_and_click_ok() throws Throwable {
//open status dropdown
        driver.findElement(By.xpath("//*[@id=\"MaintainRevenueTarget:TargetYear\"]/div[3]")).click();
        Thread.sleep(5000);
        //select status
        String StatusXpath = "//li[@data-label='2083']";
        driver.findElement(By.xpath(StatusXpath)).click();
        Thread.sleep(5000);
        //open status dropdown//*[@id="MaintainRevenueTarget:TargetMonth"]/div[3]
        driver.findElement(By.xpath("//*[@id=\"MaintainRevenueTarget:TargetMonth\"]/div[3]")).click();
        Thread.sleep(5000);
        //select status
        String MonthXpath = "//li[@data-label='January']";
        driver.findElement(By.xpath(MonthXpath)).click();
        Thread.sleep(5000);
        //open status dropdown
        // driver.findElement(By.xpath("//*[@id=\"MaintainRevenueTarget:TargetStation\"]")).click();
        // Thread.sleep(5000);
        //select status
        // String StationXpath = "//li[@data-label='Zomba']";
        // driver.findElement(By.xpath(StationXpath)).click();
        // Thread.sleep(5000);
        //open status dropdown
        driver.findElement(By.xpath("//*[@id=\"MaintainRevenueTarget:TargetTaxType\"]/div[3]")).click();
        Thread.sleep(5000);
        //select status
        String TaxtypePath = "//li[@data-label='Domestic VAT']";
        driver.findElement(By.xpath(TaxtypePath)).click();
        Thread.sleep(5000);

        driver.findElement(By.xpath("//*[@id=\"MaintainRevenueTarget:j_idt55\"]")).click();
        Thread.sleep(6000);


    }


//-----------------------------Approve TaxPayer Accounting Transactions----------------------------------//
//1

    @Given("^User is in browser to launch crm application url and login$")
    public void user_is_in_browser_to_launch_crm_application_url_and_login() throws Throwable {

        driver.get("http://crmadmin:Technobrainsm1@trips-crm:5555/TripsWorkflow/main.aspx#855985721");
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        //close popup window
        //CRM_ExploreCrmWindow_Frame__ID=InlineDialog_Iframe
        //CRM_ExploreCrmWindow_Frame_Close_ID=buttonClose
        driver.switchTo().defaultContent();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        WebElement specificframe = (driver.findElement(By.id("InlineDialog_Iframe")));
        driver.switchTo().frame(specificframe);
        WebDriverWait CloseWindow = new WebDriverWait(driver, 60);
        CloseWindow.until(ExpectedConditions.elementToBeClickable(By.id("buttonClose"))).click();
    }


    @Then("^search for the task Approve Account Adjustment with  using ARN$")
    public void search_for_the_task_approve_account_adjustment_with_using_ARN() throws Throwable {
        //opening case management dropdown
        driver.findElement(By.xpath("//*[@id=\"TabCS\"]/a/span")).click();
        Thread.sleep(5000);
        //clicking accounting application
        driver.findElement(By.xpath("//*[@id=\"tbg_accountingapplication\"]/span[2]")).click();
        //switch to frame
        driver.switchTo().defaultContent();
        WebDriverWait wait = new WebDriverWait(driver, 30);
        WebElement specificframe = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("contentIFrame0")));
        driver.switchTo().frame(specificframe);
        Thread.sleep(3000);
    }

    @Then("^acquire task$")
    public void acquire_the_task() throws Throwable {
        //enter reference number in search
        Thread.sleep(3000);
        driver.findElement(By.id("crmGrid_findCriteria")).sendKeys("ACAD/000002203/2020");
        driver.findElement(By.id("crmGrid_findCriteriaButton")).click();
        Thread.sleep(5000);
        //click ARN
        WebElement elementLocator = driver.findElement(By.xpath("//*[@id=\"gridBodyTable\"]/tbody/tr/td[1]"));
        Actions actions = new Actions(driver);
        actions.doubleClick(elementLocator).perform();
        driver.switchTo().defaultContent();
        Thread.sleep(9000);

    }

    @And("^select outcome as approve$")
    public void select_outcome_as_approve() throws Throwable {
        driver.switchTo().frame("contentIFrame1");
        Thread.sleep(7000);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        Actions action = new Actions(driver);
        WebElement Outcome = driver.findElement(By.id("header_process_tbg_approvaloutcome"));
        WebElement hasLoaded = driver.findElement(By.id("header_process_tbg_approvaloutcome_lock"));

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        Thread.sleep(7000);
        if (hasLoaded.isDisplayed()) {
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        } else {
            action.doubleClick(Outcome).build().perform();
            Outcome.click();
            action.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();
        }
        Thread.sleep(10000);

    }

    @And("^click save$")
    public void Click__save() throws Throwable {
        driver.switchTo().defaultContent();
        driver.findElement(By.id("tbg_accountingapplication|NoRelationship|Form|Mscrm.Form.tbg_accountingapplication.Save")).click();
        Thread.sleep(9000);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        Thread.sleep(9000);
    }

    //2
    @Then("^Search for the task Approve Account Adjustment with  using ARN$")
    public void search_for_the_task_approve_account_adjustment_with_using_arn() throws Throwable {
        //opening case management dropdown
        driver.findElement(By.xpath("//*[@id=\"TabCS\"]/a/span")).click();
        Thread.sleep(5000);
        //clicking accounting application
        driver.findElement(By.xpath("//*[@id=\"tbg_accountingapplication\"]/span[2]")).click();
        //switch to frame
        driver.switchTo().defaultContent();
        WebDriverWait wait = new WebDriverWait(driver, 30);
        WebElement specificframe = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("contentIFrame0")));
        driver.switchTo().frame(specificframe);
        Thread.sleep(3000);
    }

    @Then("^Acquiree task$")
    public void aacquire_Task() throws Throwable {
//enter reference number in search
        Thread.sleep(3000);
        driver.findElement(By.id("crmGrid_findCriteria")).sendKeys("ACAD/000002204/2020");
        driver.findElement(By.id("crmGrid_findCriteriaButton")).click();
        Thread.sleep(5000);
        //click ARN
        WebElement elementLocator = driver.findElement(By.xpath("//*[@id=\"gridBodyTable\"]/tbody/tr/td[1]"));
        Actions actions = new Actions(driver);
        actions.doubleClick(elementLocator).perform();
        driver.switchTo().defaultContent();
        Thread.sleep(9000);
    }

    @And("^select outcome as reject without selection reason for rejection\"([^\"]*)\"$")
    public void select_outcome_as_reject_without_selection_reason_for_rejection(String notes) throws Throwable {
        driver.switchTo().frame("contentIFrame1");
        Thread.sleep(7000);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        Actions action = new Actions(driver);
        WebElement Outcome = driver.findElement(By.id("header_process_tbg_approvaloutcome"));
        WebElement hasLoaded = driver.findElement(By.id("header_process_tbg_approvaloutcome_lock"));

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        Thread.sleep(7000);
        if (hasLoaded.isDisplayed()) {
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        } else {
            action.doubleClick(Outcome).build().perform();
            Outcome.click();
            action.sendKeys(Keys.ARROW_UP).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();
            driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        }
        Thread.sleep(10000);

        //Input Notes
        Thread.sleep(3000);
        Actions action1 = new Actions(driver);
        WebElement element1 = driver.findElement(By.id("tbg_outcomenotes_i"));
        action1.sendKeys(element1, notes).build().perform();
        Thread.sleep(5000);
    }

    @And("^click Save$")
    public void click_SAVE() throws Throwable {
        driver.switchTo().defaultContent();
        driver.findElement(By.id("tbg_accountingapplication|NoRelationship|Form|Mscrm.Form.tbg_accountingapplication.Save")).click();
        Thread.sleep(9000);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        Thread.sleep(9000);
    }

    //3
    @Then("^Search for the task Approve Account Adjustment With  using ARN$")
    public void search_for_the_task_approve_account_adjustment_with_Using_arn() throws Throwable {
        //opening case management dropdown
        driver.findElement(By.xpath("//*[@id=\"TabCS\"]/a/span")).click();
        Thread.sleep(5000);
        //clicking accounting application
        driver.findElement(By.xpath("//*[@id=\"tbg_accountingapplication\"]/span[2]")).click();
        //switch to frame
        driver.switchTo().defaultContent();
        WebDriverWait wait = new WebDriverWait(driver, 30);
        WebElement specificframe = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("contentIFrame0")));
        driver.switchTo().frame(specificframe);
        Thread.sleep(3000);
    }

    @Then("^Acquire Task$")
    public void Acquiretask() throws Throwable {
//enter reference number in search
        Thread.sleep(3000);
        driver.findElement(By.id("crmGrid_findCriteria")).sendKeys("ACAD/000002204/2020");
        driver.findElement(By.id("crmGrid_findCriteriaButton")).click();
        Thread.sleep(5000);
        //click ARN
        WebElement elementLocator = driver.findElement(By.xpath("//*[@id=\"gridBodyTable\"]/tbody/tr/td[1]"));
        Actions actions = new Actions(driver);
        actions.doubleClick(elementLocator).perform();
        driver.switchTo().defaultContent();
        Thread.sleep(9000);
    }

    @And("^select on reject from outcome$")
    public void select_on_reject_from_outcome() throws Throwable {
        driver.switchTo().frame("contentIFrame1");
        Thread.sleep(7000);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        Actions action = new Actions(driver);
        WebElement Outcome = driver.findElement(By.id("header_process_tbg_approvaloutcome"));
        WebElement hasLoaded = driver.findElement(By.id("header_process_tbg_approvaloutcome_lock"));

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        Thread.sleep(7000);
        if (hasLoaded.isDisplayed()) {
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        } else {
            action.doubleClick(Outcome).build().perform();
            Outcome.click();
            action.sendKeys(Keys.ARROW_UP).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();
        }

    }

    @And("^select outcome \"([^\"]*)\" from the lIST$")
    public void select_outcome_something_from_the_list(String reason) throws Throwable {
        WebElement specificframe = driver.findElement(By.id("WebResource_AccountingRejectionDataWebResource"));
        driver.switchTo().frame(specificframe);
        Thread.sleep(9000);
        WebElement dropDown = driver.findElement(By.id("viewoption"));
        dropDown.click();

        driver.findElement(By.xpath("//option[text()='Further Information Required']")).click();

    }

    @And("^Type notes \"([^\"]*)\"$")
    public void type_notes_something(String notes) throws Throwable {
        Thread.sleep(3000);
        Actions action1 = new Actions(driver);
        WebElement element1 = driver.findElement(By.id("tbg_outcomenotes_i"));
        action1.sendKeys(element1, notes).build().perform();
        Thread.sleep(5000);
    }

    @And("^click the save button$")
    public void click_the_save_button() throws Throwable {
        driver.switchTo().defaultContent();
        driver.findElement(By.id("tbg_accountingapplication|NoRelationship|Form|Mscrm.Form.tbg_accountingapplication.Save")).click();
        Thread.sleep(9000);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        Thread.sleep(9000);

    }

    //4
    @Then("^Search for the task Approve Credit Allocation With  using ARN$")
    public void search_for_the_task_approve_credit_allocation_with_using_arn() throws Throwable {
        //opening case management dropdown
        driver.findElement(By.xpath("//*[@id=\"TabCS\"]/a/span")).click();
        Thread.sleep(5000);
        //clicking accounting application
        driver.findElement(By.xpath("//*[@id=\"tbg_accountingapplication\"]/span[2]")).click();
        //switch to frame
        driver.switchTo().defaultContent();
        WebDriverWait wait = new WebDriverWait(driver, 30);
        WebElement specificframe = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("contentIFrame0")));
        driver.switchTo().frame(specificframe);
        Thread.sleep(3000);
    }

    @Then("^Acquire____task$")
    public void acquire_____task() throws Throwable {
//enter reference number in search
        Thread.sleep(3000);
        driver.findElement(By.id("crmGrid_findCriteria")).sendKeys("ACAD/000001986/2020");
        driver.findElement(By.id("crmGrid_findCriteriaButton")).click();
        Thread.sleep(5000);
        //click ARN
        WebElement elementLocator = driver.findElement(By.xpath("//*[@id=\"gridBodyTable\"]/tbody/tr/td[1]"));
        Actions actions = new Actions(driver);
        actions.doubleClick(elementLocator).perform();
        driver.switchTo().defaultContent();
        Thread.sleep(9000);
    }

    @And("^select the outcome as approve$")
    public void select_the_outcome_as_approve() throws Throwable {
        driver.switchTo().frame("contentIFrame1");
        Thread.sleep(7000);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        Actions action = new Actions(driver);
        WebElement Outcome = driver.findElement(By.id("header_process_tbg_approvaloutcome"));
        WebElement hasLoaded = driver.findElement(By.id("header_process_tbg_approvaloutcome_lock"));

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        Thread.sleep(7000);
        if (hasLoaded.isDisplayed()) {
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        } else {
            action.doubleClick(Outcome).build().perform();
            Outcome.click();
            action.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();
        }
        Thread.sleep(10000);


    }

    @And("^click on thee save$")
    public void click_on_thee_save() throws Throwable {
        driver.switchTo().defaultContent();
        driver.findElement(By.id("tbg_accountingapplication|NoRelationship|Form|Mscrm.Form.tbg_accountingapplication.Save")).click();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        Thread.sleep(9000);
    }

    //5
    @Then("^Search for the task approve credit allocation With  using ARN$")
    public void Search_For_the_task_approve_credit_allocation_with_using_arn() throws Throwable {
        //opening case management dropdown
        driver.findElement(By.xpath("//*[@id=\"TabCS\"]/a/span")).click();
        Thread.sleep(5000);
        //clicking accounting application
        driver.findElement(By.xpath("//*[@id=\"tbg_accountingapplication\"]/span[2]")).click();
        //switch to frame
        driver.switchTo().defaultContent();
        WebDriverWait wait = new WebDriverWait(driver, 30);
        WebElement specificframe = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("contentIFrame0")));
        driver.switchTo().frame(specificframe);
        Thread.sleep(3000);
    }

    @Then("^Acquire the Task$")
    public void Acquire_The_task() throws Throwable {
//enter reference number in search
        Thread.sleep(3000);
        driver.findElement(By.id("crmGrid_findCriteria")).sendKeys("ACAD/000001986/2020");
        driver.findElement(By.id("crmGrid_findCriteriaButton")).click();
        Thread.sleep(5000);
        //click ARN
        WebElement elementLocator = driver.findElement(By.xpath("//*[@id=\"gridBodyTable\"]/tbody/tr/td[1]"));
        Actions actions = new Actions(driver);
        actions.doubleClick(elementLocator).perform();
        driver.switchTo().defaultContent();
        Thread.sleep(9000);
    }

    @And("^select the outcome as reject without selecting the reason for rejection \"([^\"]*)\"$")
    public void select_the_outcome_as_reject_without_selecting_the_reason_for_rejection(String notes) throws Throwable {
        driver.switchTo().frame("contentIFrame1");
        Thread.sleep(7000);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        Actions action = new Actions(driver);
        WebElement Outcome = driver.findElement(By.id("header_process_tbg_approvaloutcome"));
        WebElement hasLoaded = driver.findElement(By.id("header_process_tbg_approvaloutcome_lock"));

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        Thread.sleep(7000);
        if (hasLoaded.isDisplayed()) {
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        } else {
            action.doubleClick(Outcome).build().perform();
            Outcome.click();
            action.sendKeys(Keys.ARROW_UP).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();
            driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        }
        Thread.sleep(10000);

        //Input Notes
        Thread.sleep(3000);
        Actions action1 = new Actions(driver);
        WebElement element1 = driver.findElement(By.id("tbg_outcomenotes_i"));
        action1.sendKeys(element1, notes).build().perform();
        Thread.sleep(5000);
    }

    @And("^click on SAVE button$")
    public void click_on_save_button() throws Throwable {
        driver.switchTo().defaultContent();
        driver.findElement(By.id("tbg_accountingapplication|NoRelationship|Form|Mscrm.Form.tbg_accountingapplication.Save")).click();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        Thread.sleep(9000);
    }

    //6
    @Then("^search for the Task approve credit allocation With  using ARN$")
    public void search_for_the_task_approve_Credit_allocation_with_using_arn() throws Throwable {
        //opening case management dropdown
        driver.findElement(By.xpath("//*[@id=\"TabCS\"]/a/span")).click();
        Thread.sleep(5000);
        //clicking accounting application
        driver.findElement(By.xpath("//*[@id=\"tbg_accountingapplication\"]/span[2]")).click();
        //switch to frame
        driver.switchTo().defaultContent();
        WebDriverWait wait = new WebDriverWait(driver, 30);
        WebElement specificframe = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("contentIFrame0")));
        driver.switchTo().frame(specificframe);
        Thread.sleep(3000);
    }

    @Then("^acquire the Task$")
    public void acquire_the_Task() throws Throwable {
//enter reference number in search
        Thread.sleep(3000);
        driver.findElement(By.id("crmGrid_findCriteria")).sendKeys("ACAD/000001986/2020");
        driver.findElement(By.id("crmGrid_findCriteriaButton")).click();
        Thread.sleep(5000);
        //click ARN
        WebElement elementLocator = driver.findElement(By.xpath("//*[@id=\"gridBodyTable\"]/tbody/tr/td[1]"));
        Actions actions = new Actions(driver);
        actions.doubleClick(elementLocator).perform();
        driver.switchTo().defaultContent();
        Thread.sleep(9000);
    }


    @And("^select on reject$")
    public void select_on_reject() throws Throwable {
        driver.switchTo().frame("contentIFrame1");
        Thread.sleep(7000);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        Actions action = new Actions(driver);
        WebElement Outcome = driver.findElement(By.id("header_process_tbg_approvaloutcome"));
        WebElement hasLoaded = driver.findElement(By.id("header_process_tbg_approvaloutcome_lock"));

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        Thread.sleep(7000);
        if (hasLoaded.isDisplayed()) {
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        } else {
            action.doubleClick(Outcome).build().perform();
            Outcome.click();
            action.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();
        }
    }

    @Then("^select outcome  from thee list$")
    public void select_outcome_from_thee_list() throws Throwable {
        WebElement specificframe = driver.findElement(By.id("WebResource_AccountingRejectionDataWebResource"));
        driver.switchTo().frame(specificframe);

        WebElement dropDown = driver.findElement(By.id("viewoption"));
        dropDown.click();

        driver.findElement(By.xpath("//option[text()='Further Information Required']")).click();
    }


    @And("^type outcome notes \"([^\"]*)\"$")
    public void type_outcome_notes_something(String notes) throws Throwable {
        Thread.sleep(3000);
        Actions action1 = new Actions(driver);
        WebElement element1 = driver.findElement(By.id("tbg_outcomenotes_i"));
        action1.sendKeys(element1, notes).build().perform();
        Thread.sleep(5000);
    }

    @And("^click on save BUTTON$")
    public void click_on_save__button() throws Throwable {
        driver.switchTo().defaultContent();
        driver.findElement(By.id("tbg_accountingapplication|NoRelationship|Form|Mscrm.Form.tbg_accountingapplication.Save")).click();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        Thread.sleep(9000);
    }
//-------------------------------------Print Return Reports-----------------------------------------//

    //------------------------UAT_M4-09-01---------------------//
    @Then("^Navigate to click Reporting-->Reports$")
    public void navigate_to_click_reportingreports() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"MenuForm:j_idt29\"]/ul/li[4]/a")).click();
        driver.findElement(By.xpath("//*[@id=\"MenuForm:j_idt29\"]/ul/li[4]/ul/li[1]/a")).click();
    }

    @And("^click Amended Returns Reports link$")
    public void click_amended_returns_reports_link() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"ReportTree:t1:5_0:j_idt42\"]")).click();
    }

    @Then("^Select the report format as \"([^\"]*)\"$")
    public void Select_the_report_format_as_something(String pdf) throws Throwable {
        driver.findElement(By.cssSelector("#frmReportDetails\\:ReportFormat")).click();
        // selects the value you
        WebElement dropdown = driver.findElement(By.xpath("//*[@id=\"frmReportDetails:ReportFormat_items\"]"));
        List<WebElement> entity_list = dropdown.findElements(By.tagName("li"));
        for (WebElement li : entity_list) {
            System.out.print(000);
            if (li.getText().equals(pdf)) {
                li.click();
                System.out.print(0000);
                //to stop the loop
                break;
            }
        }
        Thread.sleep(4000);
    }

    @And("^enter the start date and  end date provided$")
    public void enter_the_start_date_and_end_date_provided() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"frmReportDetails:StartDate_input\"]")).sendKeys("11/07/2020");

        driver.findElement(By.xpath("//*[@id=\"frmReportDetails:EndDate_input\"]")).sendKeys("12/07/2020");
        Thread.sleep(1000);
    }

    @Then("^select Taxpayer category \"([^\"]*)\"$")
    public void Select_taxpayer_category_something(String taxpayer_category) throws Throwable {
        //#frmReportDetails\:Taxpayer_Category_items
        //
        driver.findElement(By.cssSelector("#frmReportDetails\\:Taxpayer_Category")).click();
        // selects the value you want
        WebElement dropdown = driver.findElement(By.xpath("//*[@id=\"frmReportDetails:Taxpayer_Category_items\"]"));
        List<WebElement> entity_list = dropdown.findElements(By.tagName("li"));
        for (WebElement li : entity_list) {
            System.out.print(000);
            if (li.getText().equals(taxpayer_category)) {
                li.click();
                System.out.print(0000);
                //to stop the loop
                break;
            }

        }
        Thread.sleep(2000);
    }

    @Then("^select Tax office \"([^\"]*)\"$")
    public void Select_tax_office_something(String taxoffice) throws Throwable {
        driver.findElement(By.cssSelector("#frmReportDetails\\:TAX_OFFICE")).click();
        Thread.sleep(2000);
        WebElement dropdown = driver.findElement(By.xpath("//*[@id=\"frmReportDetails:TAX_OFFICE_items\"]"));
        List<WebElement> entity_list = dropdown.findElements(By.tagName("li"));
        for (WebElement li : entity_list) {
            System.out.print(000);
            if (li.getText().equals(taxoffice)) {
                li.click();
                System.out.print(0000);
                //to stop the loop
                break;
            }

        }
        Thread.sleep(2000);
    }

    @Then("^The return type \"([^\"]*)\"$")
    public void Theee_return_type_something(String return_type) throws Throwable {
        driver.findElement(By.cssSelector("#frmReportDetails\\:RETURN_TYPE")).click();
        Thread.sleep(2000);
        WebElement dropdown = driver.findElement(By.xpath("//*[@id=\"frmReportDetails:RETURN_TYPE_items\"]"));
        List<WebElement> entity_list = dropdown.findElements(By.tagName("li"));
        for (WebElement li : entity_list) {
            System.out.print(000);
            if (li.getText().equals(return_type)) {
                li.click();
                System.out.print(0000);
                //to stop the loop
                break;
            }

        }
        Thread.sleep(2000);
    }


    @And("^The business sector \"([^\"]*)\"$")
    public void the_business_sector_something(String business_sector) throws Throwable {
        driver.findElement(By.cssSelector("#frmReportDetails\\:BUSINESS_SECTOR")).click();
        Thread.sleep(2000);
        WebElement dropdown = driver.findElement(By.xpath("//*[@id=\"frmReportDetails:BUSINESS_SECTOR_items\"]"));
        List<WebElement> entity_list = dropdown.findElements(By.tagName("li"));
        for (WebElement li : entity_list) {
            System.out.print(000);
            if (li.getText().equals(business_sector)) {
                li.click();
                System.out.print(0000);
                //to stop the loop
                break;
            }

        }
        Thread.sleep(2000);
    }

    @And("^Run report$")
    public void Run_report() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"frmReportDetails:RunReport\"]")).click();
        Thread.sleep(4000);
    }


    //------------------------UAT_M4-09-02---------------------//

    @Then("^Navigate to  Reporting-->Reports$")
    public void Navigate_to__reportingreports() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"MenuForm:j_idt29\"]/ul/li[4]/a")).click();
        driver.findElement(By.xpath("//*[@id=\"MenuForm:j_idt29\"]/ul/li[4]/ul/li[1]/a")).click();
    }

    @And("^click Fact of Filing Report link$")
    public void click_fact_of_filing_report_link() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"ReportTree:t1:5_3:j_idt42\"]")).click();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Then("^Select the report Format as \"([^\"]*)\"$")
    public void select_the_report_Format_as_something(String pdf) throws Throwable {
        driver.findElement(By.cssSelector("#frmReportDetails\\:ReportFormat")).click();
        // selects the value you
        WebElement dropdown = driver.findElement(By.xpath("//*[@id=\"frmReportDetails:ReportFormat_items\"]"));
        List<WebElement> entity_list = dropdown.findElements(By.tagName("li"));
        for (WebElement li : entity_list) {
            System.out.print(000);
            if (li.getText().equals(pdf)) {
                li.click();
                System.out.print(0000);
                //to stop the loop
                break;
            }
        }
        Thread.sleep(4000);
    }

    @Then("^select Taxpayer Category \"([^\"]*)\"$")
    public void Select_taxpayer_Category_something(String taxpayer_category) throws Throwable {
        //#frmReportDetails\:Taxpayer_Category_items
        //
        driver.findElement(By.xpath("//*[@id=\"frmReportDetails:Taxpayer_Category\"]/div[3]")).click();
        // selects the value you want
        WebElement dropdown = driver.findElement(By.xpath("//*[@id=\"frmReportDetails:Taxpayer_Category_items\"]"));
        List<WebElement> entity_list = dropdown.findElements(By.tagName("li"));
        for (WebElement li : entity_list) {
            System.out.print(000);
            if (li.getText().equals(taxpayer_category)) {
                li.click();
                System.out.print(0000);
                //to stop the loop
                break;
            }

        }
        Thread.sleep(2000);
    }

    @Then("^select Tax Office \"([^\"]*)\"$")
    public void Select_Tax_office_something(String tax_office) throws Throwable {
        driver.findElement(By.cssSelector("#frmReportDetails\\:TAX_OFFICE")).click();
        Thread.sleep(2000);
        WebElement dropdown = driver.findElement(By.xpath("//*[@id=\"frmReportDetails:TAX_OFFICE_items\"]"));
        List<WebElement> entity_list = dropdown.findElements(By.tagName("li"));
        for (WebElement li : entity_list) {
            System.out.print(000);
            if (li.getText().equals(tax_office)) {
                li.click();
                System.out.print(0000);
                //to stop the loop
                break;
            }

        }
        Thread.sleep(2000);
    }

    @Then("^The return Type \"([^\"]*)\"$")
    public void the_return_type_something(String return_type) throws Throwable {
        driver.findElement(By.cssSelector("#frmReportDetails\\:RETURN_TYPE")).click();
        Thread.sleep(2000);
        WebElement dropdown = driver.findElement(By.xpath("//*[@id=\"frmReportDetails:RETURN_TYPE_items\"]"));
        List<WebElement> entity_list = dropdown.findElements(By.tagName("li"));
        for (WebElement li : entity_list) {
            System.out.print(000);
            if (li.getText().equals(return_type)) {
                li.click();
                System.out.print(0000);
                //to stop the loop
                break;
            }

        }
        Thread.sleep(2000);
    }

    @And("^enter the start Date and  end date provided$")
    public void Enter_the_start_date_and_end_date_provided() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"frmReportDetails:StartDate_input\"]")).sendKeys("11/07/2020");
        driver.findElement(By.xpath("//*[@id=\"frmReportDetails:EndDate_input\"]")).sendKeys("27/07/2020");
        Thread.sleep(1000);
    }

    @And("^The business Sector \"([^\"]*)\"$")
    public void the_Business_sector_something(String business_sector) throws Throwable {
        driver.findElement(By.cssSelector("#frmReportDetails\\:BUSINESS_SECTOR")).click();
        Thread.sleep(2000);
        WebElement dropdown = driver.findElement(By.xpath("//*[@id=\"frmReportDetails:BUSINESS_SECTOR_items\"]"));
        List<WebElement> entity_list = dropdown.findElements(By.tagName("li"));
        for (WebElement li : entity_list) {
            System.out.print(000);
            if (li.getText().equals(business_sector)) {
                li.click();
                System.out.print(0000);
                //to stop the loop
                break;
            }

        }
        Thread.sleep(2000);
    }

    @And("^Run Report$")
    public void Run_Report() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"frmReportDetails:RunReport\"]")).click();
        Thread.sleep(4000);
    }

    //------------------------UAT_M4-09-03---------------------//

    @Then("^Navigate to the Reporting-->Reports$")
    public void navigate_to_the_reportingreports() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"MenuForm:j_idt29\"]/ul/li[4]/a")).click();
        driver.findElement(By.xpath("//*[@id=\"MenuForm:j_idt29\"]/ul/li[4]/ul/li[1]/a")).click();
    }

    @And("^click Return Activity Report link$")
    public void click_return_activity_report_link() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"ReportTree:t1:5_4:j_idt42\"]")).click();
    }

    @Then("^Select the report Format As \"([^\"]*)\"$")
    public void select_the_report___format_as_something(String pdf) throws Throwable {
        driver.findElement(By.cssSelector("#frmReportDetails\\:ReportFormat")).click();
        // selects the value you
        WebElement dropdown = driver.findElement(By.xpath("//*[@id=\"frmReportDetails:ReportFormat_items\"]"));
        List<WebElement> entity_list = dropdown.findElements(By.tagName("li"));
        for (WebElement li : entity_list) {
            System.out.print(000);
            if (li.getText().equals(pdf)) {
                li.click();
                System.out.print(0000);
                //to stop the loop
                break;
            }
        }
        Thread.sleep(4000);
    }

    @Then("^Select Taxpayer Category \"([^\"]*)\"$")
    public void select_taxpayer_category__something(String taxpayer_category) throws Throwable {
//#frmReportDetails\:Taxpayer_Category_items
        //
        driver.findElement(By.xpath("//*[@id=\"frmReportDetails:Taxpayer_Category\"]/div[3]")).click();
        // selects the value you want
        WebElement dropdown = driver.findElement(By.xpath("//*[@id=\"frmReportDetails:Taxpayer_Category_items\"]"));
        List<WebElement> entity_list = dropdown.findElements(By.tagName("li"));
        for (WebElement li : entity_list) {
            System.out.print(000);
            if (li.getText().equals(taxpayer_category)) {
                li.click();
                System.out.print(0000);
                //to stop the loop
                break;
            }

        }
        Thread.sleep(2000);
    }

    @Then("^Select Tax Office \"([^\"]*)\"$")
    public void select_tax_office___Something(String tax_office) throws Throwable {
        driver.findElement(By.cssSelector("#frmReportDetails\\:TAX_OFFICE")).click();
        Thread.sleep(2000);
        WebElement dropdown = driver.findElement(By.xpath("//*[@id=\"frmReportDetails:TAX_OFFICE_items\"]"));
        List<WebElement> entity_list = dropdown.findElements(By.tagName("li"));
        for (WebElement li : entity_list) {
            System.out.print(000);
            if (li.getText().equals(tax_office)) {
                li.click();
                System.out.print(0000);
                //to stop the loop
                break;
            }

        }
        Thread.sleep(2000);
    }

    @Then("^The Return Type \"([^\"]*)\"$")
    public void The__Return_Type_something(String return_type) throws Throwable {

        driver.findElement(By.cssSelector("#frmReportDetails\\:RETURN_TYPE")).click();
        Thread.sleep(2000);
        WebElement dropdown = driver.findElement(By.xpath("//*[@id=\"frmReportDetails:RETURN_TYPE_items\"]"));
        List<WebElement> entity_list = dropdown.findElements(By.tagName("li"));
        for (WebElement li : entity_list) {
            System.out.print(000);
            if (li.getText().equals(return_type)) {
                li.click();
                System.out.print(0000);
                //to stop the loop
                break;
            }

        }
        Thread.sleep(2000);
    }


    @And("^enter the start Date and  end Date provided$")
    public void enter_the_start_date_and_end_date_Provided() throws Throwable {

        driver.findElement(By.xpath("//*[@id=\"frmReportDetails:StartDate_input\"]")).sendKeys("11/07/2020");
        driver.findElement(By.xpath("//*[@id=\"frmReportDetails:EndDate_input\"]")).sendKeys("12/07/2020");
        Thread.sleep(1000);
    }

    @And("^The Business Sector \"([^\"]*)\"$")
    public void the_business__sector_something(String business_sector) throws Throwable {
        driver.findElement(By.cssSelector("#frmReportDetails\\:BUSINESS_SECTOR")).click();
        Thread.sleep(2000);
        WebElement dropdown = driver.findElement(By.xpath("//*[@id=\"frmReportDetails:BUSINESS_SECTOR_items\"]"));
        List<WebElement> entity_list = dropdown.findElements(By.tagName("li"));
        for (WebElement li : entity_list) {
            System.out.print(000);
            if (li.getText().equals(business_sector)) {
                li.click();
                System.out.print(0000);
                //to stop the loop
                break;
            }

        }
        Thread.sleep(2000);
    }

    @And("^Run the Report$")
    public void run_The_report() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"frmReportDetails:RunReport\"]")).click();
        Thread.sleep(4000);
    }

    //------------------------UAT_M4-09-04---------------------//

    @Then("^Navigate to the Reporting--->Reports$")
    public void navigate_to_thee_reportingreports() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"MenuForm:j_idt29\"]/ul/li[4]/a")).click();
        driver.findElement(By.xpath("//*[@id=\"MenuForm:j_idt29\"]/ul/li[4]/ul/li[1]/a")).click();
    }

    @And("^click Error Returns link$")
    public void click_error_returns_link() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"ReportTree:t1:5_2:j_idt42\"]")).click();
    }

    @Then("^Select the Report Format As \"([^\"]*)\"$")
    public void select_thee_report_format_as_something(String pdf) throws Throwable {
        driver.findElement(By.cssSelector("#frmReportDetails\\:ReportFormat")).click();
        // selects the value you
        WebElement dropdown = driver.findElement(By.xpath("//*[@id=\"frmReportDetails:ReportFormat_items\"]"));
        List<WebElement> entity_list = dropdown.findElements(By.tagName("li"));
        for (WebElement li : entity_list) {
            System.out.print(000);
            if (li.getText().equals(pdf)) {
                li.click();
                System.out.print(0000);
                //to stop the loop
                break;
            }
        }
        Thread.sleep(4000);
    }

    @Then("^Select Taxpayer category \"([^\"]*)\"$")
    public void select_taxpayer__category_something(String taxpayer_category) throws Throwable {
        //#frmReportDetails\:Taxpayer_Category_items
        //
        driver.findElement(By.xpath("//*[@id=\"frmReportDetails:Taxpayer_Category\"]/div[3]")).click();
        // selects the value you want
        WebElement dropdown = driver.findElement(By.xpath("//*[@id=\"frmReportDetails:Taxpayer_Category_items\"]"));
        List<WebElement> entity_list = dropdown.findElements(By.tagName("li"));
        for (WebElement li : entity_list) {
            System.out.print(000);
            if (li.getText().equals(taxpayer_category)) {
                li.click();
                System.out.print(0000);
                //to stop the loop
                break;
            }

        }
        Thread.sleep(2000);
    }

    @Then("^Select a Tax Office \"([^\"]*)\"$")
    public void select_a_tax_office_something(String tax_office) throws Throwable {
        driver.findElement(By.cssSelector("#frmReportDetails\\:TAX_OFFICE")).click();
        Thread.sleep(2000);
        WebElement dropdown = driver.findElement(By.xpath("//*[@id=\"frmReportDetails:TAX_OFFICE_items\"]"));
        List<WebElement> entity_list = dropdown.findElements(By.tagName("li"));
        for (WebElement li : entity_list) {
            System.out.print(000);
            if (li.getText().equals(tax_office)) {
                li.click();
                System.out.print(0000);
                //to stop the loop
                break;
            }

        }
        Thread.sleep(2000);
    }

    @Then("^The Return____Type \"([^\"]*)\"$")
    public void the_return_Type____something(String return_type) throws Throwable {
        driver.findElement(By.cssSelector("#frmReportDetails\\:RETURN_TYPE")).click();
        Thread.sleep(2000);
        WebElement dropdown = driver.findElement(By.xpath("//*[@id=\"frmReportDetails:RETURN_TYPE_items\"]"));
        List<WebElement> entity_list = dropdown.findElements(By.tagName("li"));
        for (WebElement li : entity_list) {
            System.out.print(000);
            if (li.getText().equals(return_type)) {
                li.click();
                System.out.print(0000);
                //to stop the loop
                break;
            }

        }
        Thread.sleep(2000);
    }


    @And("^enter the start Date and  End Date provided$")
    public void enter_the_start_date__and_end_date_provided() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"frmReportDetails:StartDate_input\"]")).sendKeys("11/07/2020");
        driver.findElement(By.xpath("//*[@id=\"frmReportDetails:EndDate_input\"]")).sendKeys("12/07/2020");
        Thread.sleep(1000);
    }

    @And("^The Business sector \"([^\"]*)\"$")
    public void the_business_Sector_something(String business_sector) throws Throwable {
        driver.findElement(By.cssSelector("#frmReportDetails\\:BUSINESS_SECTOR")).click();
        Thread.sleep(2000);
        WebElement dropdown = driver.findElement(By.xpath("//*[@id=\"frmReportDetails:BUSINESS_SECTOR_items\"]"));
        List<WebElement> entity_list = dropdown.findElements(By.tagName("li"));
        for (WebElement li : entity_list) {
            System.out.print(000);
            if (li.getText().equals(business_sector)) {
                li.click();
                System.out.print(0000);
                //to stop the loop
                break;
            }

        }
        Thread.sleep(2000);
    }

    @And("^Run The Report$")
    public void Run_the__report() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"frmReportDetails:RunReport\"]")).click();
        Thread.sleep(4000);
    }

    //------------------------UAT_M4-09-05---------------------//

    @Then("^Navigate to click  Reporting--->Reports$")
    public void navigate_to__click_reportingreports() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"MenuForm:j_idt29\"]/ul/li[4]/a")).click();
        driver.findElement(By.xpath("//*[@id=\"MenuForm:j_idt29\"]/ul/li[4]/ul/li[1]/a")).click();
    }

    @And("^click Returns Keyed or Filed Report link$")
    public void click_returns_keyedfiled_report_link() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"ReportTree:t1:5_6:j_idt42\"]")).click();
    }

    @Then("^Select The report Format As \"([^\"]*)\"$")
    public void select_the_report__Format_as_something(String pdf) throws Throwable {
        driver.findElement(By.cssSelector("#frmReportDetails\\:ReportFormat")).click();
        // selects the value you
        WebElement dropdown = driver.findElement(By.xpath("//*[@id=\"frmReportDetails:ReportFormat_items\"]"));
        List<WebElement> entity_list = dropdown.findElements(By.tagName("li"));
        for (WebElement li : entity_list) {
            System.out.print(000);
            if (li.getText().equals(pdf)) {
                li.click();
                System.out.print(0000);
                //to stop the loop
                break;
            }
        }
        Thread.sleep(4000);
    }

    @Then("^Select a Taxpayer Category \"([^\"]*)\"$")
    public void select_a_taxpayer__category_something(String taxpayer_category) throws Throwable {
//#frmReportDetails\:Taxpayer_Category_items
        //
        driver.findElement(By.xpath("//*[@id=\"frmReportDetails:Taxpayer_Category\"]/div[3]")).click();
        // selects the value you want
        WebElement dropdown = driver.findElement(By.xpath("//*[@id=\"frmReportDetails:Taxpayer_Category_items\"]"));
        List<WebElement> entity_list = dropdown.findElements(By.tagName("li"));
        for (WebElement li : entity_list) {
            System.out.print(000);
            if (li.getText().equals(taxpayer_category)) {
                li.click();
                System.out.print(0000);
                //to stop the loop
                break;
            }

        }
        Thread.sleep(2000);
    }

    @Then("^Select the tax Office \"([^\"]*)\"$")
    public void select_the_tax_office_something(String tax_office) throws Throwable {

        driver.findElement(By.cssSelector("#frmReportDetails\\:TAX_OFFICE")).click();
        Thread.sleep(2000);
        WebElement dropdown = driver.findElement(By.xpath("//*[@id=\"frmReportDetails:TAX_OFFICE_items\"]"));
        List<WebElement> entity_list = dropdown.findElements(By.tagName("li"));
        for (WebElement li : entity_list) {
            System.out.print(000);
            if (li.getText().equals(tax_office)) {
                li.click();
                System.out.print(0000);
                //to stop the loop
                break;
            }

        }
        Thread.sleep(2000);
    }

    @Then("^A Return Type \"([^\"]*)\"$")
    public void a_return_type_something(String return_type) throws Throwable {
        driver.findElement(By.cssSelector("#frmReportDetails\\:RETURN_TYPE")).click();
        Thread.sleep(2000);
        WebElement dropdown = driver.findElement(By.xpath("//*[@id=\"frmReportDetails:RETURN_TYPE_items\"]"));
        List<WebElement> entity_list = dropdown.findElements(By.tagName("li"));
        for (WebElement li : entity_list) {
            System.out.print(000);
            if (li.getText().equals(return_type)) {
                li.click();
                System.out.print(0000);
                //to stop the loop
                break;
            }

        }
        Thread.sleep(2000);
    }


    @And("^enter the Start Date and  end Date provided$")
    public void enter_the_start_date_and__end_date_provided() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"frmReportDetails:StartDate_input\"]")).sendKeys("11/07/2020");
        driver.findElement(By.xpath("//*[@id=\"frmReportDetails:EndDate_input\"]")).sendKeys("12/07/2020");
        Thread.sleep(1000);
    }

    @And("^A Business Sector \"([^\"]*)\"$")
    public void a_business_sector_something(String business_sector) throws Throwable {
        driver.findElement(By.cssSelector("#frmReportDetails\\:BUSINESS_SECTOR")).click();
        Thread.sleep(2000);
        WebElement dropdown = driver.findElement(By.xpath("//*[@id=\"frmReportDetails:BUSINESS_SECTOR_items\"]"));
        List<WebElement> entity_list = dropdown.findElements(By.tagName("li"));
        for (WebElement li : entity_list) {
            System.out.print(000);
            if (li.getText().equals(business_sector)) {
                li.click();
                System.out.print(0000);
                //to stop the loop
                break;
            }

        }
        Thread.sleep(2000);

    }

    @And("^run a report$")
    public void run_a_report() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"frmReportDetails:RunReport\"]")).click();
        Thread.sleep(4000);
    }

//------------------------UAT_M4-09-06---------------------//

    @Then("^Navigate to the Reporting------>Reports$")
    public void navigate__to_the_reportingreports() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"MenuForm:j_idt29\"]/ul/li[4]/a")).click();
        driver.findElement(By.xpath("//*[@id=\"MenuForm:j_idt29\"]/ul/li[4]/ul/li[1]/a")).click();
    }

    @And("^click Returns Outcome Report link$")
    public void click_returns_outcome_report_link() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"ReportTree:t1:5_7:j_idt42\"]")).click();
    }

    @Then("^click the report Format As \"([^\"]*)\"$")
    public void click_the_report_format_as_something(String pdf) throws Throwable {
        driver.findElement(By.cssSelector("#frmReportDetails\\:ReportFormat")).click();
        // selects the value you
        WebElement dropdown = driver.findElement(By.xpath("//*[@id=\"frmReportDetails:ReportFormat_items\"]"));
        List<WebElement> entity_list = dropdown.findElements(By.tagName("li"));
        for (WebElement li : entity_list) {
            System.out.print(000);
            if (li.getText().equals(pdf)) {
                li.click();
                System.out.print(0000);
                //to stop the loop
                break;
            }
        }
        Thread.sleep(4000);
    }


    @Then("^click a Taxpayer Category \"([^\"]*)\"$")
    public void click_a_taxpayer_category_something(String taxpayer_category) throws Throwable {
        //#frmReportDetails\:Taxpayer_Category_items
        //
        driver.findElement(By.xpath("//*[@id=\"frmReportDetails:Taxpayer_Category\"]/div[3]")).click();
        // selects the value you want
        WebElement dropdown = driver.findElement(By.xpath("//*[@id=\"frmReportDetails:Taxpayer_Category_items\"]"));
        List<WebElement> entity_list = dropdown.findElements(By.tagName("li"));
        for (WebElement li : entity_list) {
            System.out.print(000);
            if (li.getText().equals(taxpayer_category)) {
                li.click();
                System.out.print(0000);
                //to stop the loop
                break;
            }

        }
        Thread.sleep(2000);
    }

    @Then("^click a Tax Office \"([^\"]*)\"$")
    public void click_a_tax_office_something(String tax_office) throws Throwable {
        driver.findElement(By.cssSelector("#frmReportDetails\\:TAX_OFFICE")).click();
        Thread.sleep(2000);
        WebElement dropdown = driver.findElement(By.xpath("//*[@id=\"frmReportDetails:TAX_OFFICE_items\"]"));
        List<WebElement> entity_list = dropdown.findElements(By.tagName("li"));
        for (WebElement li : entity_list) {
            System.out.print(000);
            if (li.getText().equals(tax_office)) {
                li.click();
                System.out.print(0000);
                //to stop the loop
                break;
            }

        }
        Thread.sleep(2000);
    }

    @Then("^Select a  Return Type \"([^\"]*)\"$")
    public void select_a_return_type_something(String return_type) throws Throwable {
        driver.findElement(By.cssSelector("#frmReportDetails\\:RETURN_TYPE")).click();
        Thread.sleep(2000);
        WebElement dropdown = driver.findElement(By.xpath("//*[@id=\"frmReportDetails:RETURN_TYPE_items\"]"));
        List<WebElement> entity_list = dropdown.findElements(By.tagName("li"));
        for (WebElement li : entity_list) {
            System.out.print(000);
            if (li.getText().equals(return_type)) {
                li.click();
                System.out.print(0000);
                //to stop the loop
                break;
            }

        }
        Thread.sleep(2000);
    }


    @And("^Enter start Date and  end Date provided$")
    public void enter_start_date_and_end_date_provided() throws Throwable {
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"frmReportDetails:StartDate_input\"]")).sendKeys("11/07/2019");
        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.TAB).perform();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"frmReportDetails:EndDate_input\"]")).sendKeys(Keys.ENTER);

        Thread.sleep(1000);
    }

    @And("^Select a Business Sector \"([^\"]*)\"$")
    public void select_a_business_sector_something(String business_sector) throws Throwable {
        driver.findElement(By.cssSelector("#frmReportDetails\\:BUSINESS_SECTOR")).click();
        Thread.sleep(2000);
        WebElement dropdown = driver.findElement(By.xpath("//*[@id=\"frmReportDetails:BUSINESS_SECTOR_items\"]"));
        List<WebElement> entity_list = dropdown.findElements(By.tagName("li"));
        for (WebElement li : entity_list) {
            System.out.print(000);
            if (li.getText().equals(business_sector)) {
                li.click();
                System.out.print(0000);
                //to stop the loop
                break;
            }

        }
        Thread.sleep(2000);
    }

    @And("^run Report$")
    public void run__report() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"frmReportDetails:RunReport\"]")).click();
        Thread.sleep(4000);
    }

//------------------------UAT_M4-09-07---------------------//

    @Then("^Navigate to  Reporting---->Reports$")
    public void navigate_to_Reportingreports() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"MenuForm:j_idt29\"]/ul/li[4]/a")).click();
        driver.findElement(By.xpath("//*[@id=\"MenuForm:j_idt29\"]/ul/li[4]/ul/li[1]/a")).click();
    }

    @Then("^Select a report Format As \"([^\"]*)\"$")
    public void select_a_report_format_as_something(String pdf) throws Throwable {
        driver.findElement(By.cssSelector("#frmReportDetails\\:ReportFormat")).click();
        // selects the value you
        WebElement dropdown = driver.findElement(By.xpath("//*[@id=\"frmReportDetails:ReportFormat_items\"]"));
        List<WebElement> entity_list = dropdown.findElements(By.tagName("li"));
        for (WebElement li : entity_list) {
            System.out.print(000);
            if (li.getText().equals(pdf)) {
                li.click();
                System.out.print(0000);
                //to stop the loop
                break;
            }
        }
        Thread.sleep(4000);
    }

    @Then("^Click the Taxpayer Category \"([^\"]*)\"$")
    public void click_the_taxpayer_category_something(String taxpayer_category) throws Throwable {
        //#frmReportDetails\:Taxpayer_Category_items
        //
        driver.findElement(By.xpath("//*[@id=\"frmReportDetails:Taxpayer_Category\"]/div[3]")).click();
        // selects the value you want
        WebElement dropdown = driver.findElement(By.xpath("//*[@id=\"frmReportDetails:Taxpayer_Category_items\"]"));
        List<WebElement> entity_list = dropdown.findElements(By.tagName("li"));
        for (WebElement li : entity_list) {
            System.out.print(000);
            if (li.getText().equals(taxpayer_category)) {
                li.click();
                System.out.print(0000);
                //to stop the loop
                break;
            }

        }
        Thread.sleep(2000);
    }

    @Then("^Click to select  Tax Office \"([^\"]*)\"$")
    public void click_to_select_tax_office_something(String tax_office) throws Throwable {
        driver.findElement(By.cssSelector("#frmReportDetails\\:TAX_OFFICE")).click();
        Thread.sleep(2000);
        WebElement dropdown = driver.findElement(By.xpath("//*[@id=\"frmReportDetails:TAX_OFFICE_items\"]"));
        List<WebElement> entity_list = dropdown.findElements(By.tagName("li"));
        for (WebElement li : entity_list) {
            System.out.print(000);
            if (li.getText().equals(tax_office)) {
                li.click();
                System.out.print(0000);
                //to stop the loop
                break;
            }

        }
        Thread.sleep(2000);
    }

    @Then("^click to select a Return Type \"([^\"]*)\"$")
    public void click_to_select_a_return_type_something(String return_type) throws Throwable {
        driver.findElement(By.cssSelector("#frmReportDetails\\:RETURN_TYPE")).click();
        Thread.sleep(2000);
        WebElement dropdown = driver.findElement(By.xpath("//*[@id=\"frmReportDetails:RETURN_TYPE_items\"]"));
        List<WebElement> entity_list = dropdown.findElements(By.tagName("li"));
        for (WebElement li : entity_list) {
            System.out.print(000);
            if (li.getText().equals(return_type)) {
                li.click();
                System.out.print(0000);
                //to stop the loop
                break;
            }

        }
        Thread.sleep(2000);
    }

    @And("^click Filed Returns in Loss Report link$")
    public void click_filed_returns_in_loss_report_link() throws Throwable {

    }

    @And("^Select the start Date and  end Date provided$")
    public void select_the_start_date_and_end_date_provided() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"frmReportDetails:StartDate_input\"]")).sendKeys("11/07/2020");
        driver.findElement(By.xpath("//*[@id=\"frmReportDetails:EndDate_input\"]")).sendKeys("12/07/2020");
        Thread.sleep(1000);
    }

    @And("^Click to select a  Business Sector \"([^\"]*)\"$")
    public void click_to_select_a_business_sector_something(String business_sector) throws Throwable {
        driver.findElement(By.cssSelector("#frmReportDetails\\:BUSINESS_SECTOR")).click();
        Thread.sleep(2000);
        WebElement dropdown = driver.findElement(By.xpath("//*[@id=\"frmReportDetails:BUSINESS_SECTOR_items\"]"));
        List<WebElement> entity_list = dropdown.findElements(By.tagName("li"));
        for (WebElement li : entity_list) {
            System.out.print(000);
            if (li.getText().equals(business_sector)) {
                li.click();
                System.out.print(0000);
                //to stop the loop
                break;
            }

        }
        Thread.sleep(2000);
    }

    @And("^Click to run the Report$")
    public void click_to_run_the_report() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"frmReportDetails:RunReport\"]")).click();
        Thread.sleep(4000);
    }

    //------------------------UAT_M4-09-08---------------------//

    @Then("^Click Reporting---->Reports$")
    public void click_reporting_reports() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"MenuForm:j_idt29\"]/ul/li[4]/a")).click();
        driver.findElement(By.xpath("//*[@id=\"MenuForm:j_idt29\"]/ul/li[4]/ul/li[1]/a")).click();
    }

    @And("^Click Returns By Method of Submission link$")
    public void click_returns_by_method_of_submission_link() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"ReportTree:t1:5_5:j_idt42\"]")).click();
    }


    @Then("^Select A report Format as \"([^\"]*)\"$")
    public void select_a_report_format_As_something(String pdf) throws Throwable {
        driver.findElement(By.cssSelector("#frmReportDetails\\:ReportFormat")).click();
        // selects the value you
        WebElement dropdown = driver.findElement(By.xpath("//*[@id=\"frmReportDetails:ReportFormat_items\"]"));
        List<WebElement> entity_list = dropdown.findElements(By.tagName("li"));
        for (WebElement li : entity_list) {
            System.out.print(000);
            if (li.getText().equals(pdf)) {
                li.click();
                System.out.print(0000);
                //to stop the loop
                break;
            }
        }
        Thread.sleep(4000);
    }

    @Then("^Click on  Taxpayer Category \"([^\"]*)\"$")
    public void click_on_taxpayer_category_something(String taxpayer_category) throws Throwable {
//#frmReportDetails\:Taxpayer_Category_items
        //
        driver.findElement(By.xpath("//*[@id=\"frmReportDetails:Taxpayer_Category\"]/div[3]")).click();
        // selects the value you want
        WebElement dropdown = driver.findElement(By.xpath("//*[@id=\"frmReportDetails:Taxpayer_Category_items\"]"));
        List<WebElement> entity_list = dropdown.findElements(By.tagName("li"));
        for (WebElement li : entity_list) {
            System.out.print(000);
            if (li.getText().equals(taxpayer_category)) {
                li.click();
                System.out.print(0000);
                //to stop the loop
                break;
            }

        }
        Thread.sleep(2000);
    }

    @Then("^Click on a  Tax Office \"([^\"]*)\"$")
    public void click_on_a_tax_office_something(String tax_office) throws Throwable {
        driver.findElement(By.cssSelector("#frmReportDetails\\:TAX_OFFICE")).click();
        Thread.sleep(2000);
        WebElement dropdown = driver.findElement(By.xpath("//*[@id=\"frmReportDetails:TAX_OFFICE_items\"]"));
        List<WebElement> entity_list = dropdown.findElements(By.tagName("li"));
        for (WebElement li : entity_list) {
            System.out.print(000);
            if (li.getText().equals(tax_office)) {
                li.click();
                System.out.print(0000);
                //to stop the loop
                break;
            }

        }
        Thread.sleep(2000);
    }

    @Then("^Select a Return Type \"([^\"]*)\"$")
    public void select_a_return_type_Something(String return_type) throws Throwable {
        driver.findElement(By.cssSelector("#frmReportDetails\\:RETURN_TYPE")).click();
        Thread.sleep(2000);
        WebElement dropdown = driver.findElement(By.xpath("//*[@id=\"frmReportDetails:RETURN_TYPE_items\"]"));
        List<WebElement> entity_list = dropdown.findElements(By.tagName("li"));
        for (WebElement li : entity_list) {
            System.out.print(000);
            if (li.getText().equals(return_type)) {
                li.click();
                System.out.print(0000);
                //to stop the loop
                break;
            }

        }
        Thread.sleep(2000);
    }


    @And("^enter the Start date and  end Date provided$")
    public void enter_the_start_date_and_end_Date_Provided() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"frmReportDetails:StartDate_input\"]")).sendKeys("11/07/2020");
        driver.findElement(By.xpath("//*[@id=\"frmReportDetails:EndDate_input\"]")).sendKeys("19/07/2020");
        Thread.sleep(1000);
    }

    @And("^Select Business sector \"([^\"]*)\"$")
    public void select_business_sector_something(String business_sector) throws Throwable {
        driver.findElement(By.cssSelector("#frmReportDetails\\:BUSINESS_SECTOR")).click();
        Thread.sleep(2000);
        WebElement dropdown = driver.findElement(By.xpath("//*[@id=\"frmReportDetails:BUSINESS_SECTOR_items\"]"));
        List<WebElement> entity_list = dropdown.findElements(By.tagName("li"));
        for (WebElement li : entity_list) {
            System.out.print(000);
            if (li.getText().equals(business_sector)) {
                li.click();
                System.out.print(0000);
                //to stop the loop
                break;
            }

        }
        Thread.sleep(2000);
    }

    @And("^Submission Method \"([^\"]*)\"$")
    public void submission_method_something(String method) throws Throwable {
        driver.findElement(By.cssSelector("#frmReportDetails\\:SUBMISSION_METHOD > div.ui-selectonemenu-trigger.ui-state-default.ui-corner-right")).click();
        Thread.sleep(2000);
        WebElement dropdown = driver.findElement(By.xpath("//*[@id=\"frmReportDetails:SUBMISSION_METHOD_items\"]"));
        List<WebElement> entity_list = dropdown.findElements(By.tagName("li"));
        for (WebElement li : entity_list) {
            System.out.print(000);
            if (li.getText().equals(method)) {
                li.click();
                System.out.print(0000);
                //to stop the loop
                break;
            }

        }
        Thread.sleep(2000);
    }

    @And("^Click Run the Report$")
    public void click_run_the_report() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"frmReportDetails:RunReport\"]")).click();
        Thread.sleep(4000);
    }


    //------------------------UAT_M4-09-09---------------------//

    @Then("^Navigate To Click Reporting--->Reports$")
    public void navigate_to_Click_reportingreports() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"MenuForm:j_idt29\"]/ul/li[4]/a")).click();
        driver.findElement(By.xpath("//*[@id=\"MenuForm:j_idt29\"]/ul/li[4]/ul/li[1]/a")).click();
    }

    @And("^click Returns Under Filing Extension link$")
    public void click_returns_under_filing_extension_link() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"ReportTree:t1:5_8:j_idt42\"]")).click();
    }

    @Then("^click to select the report format as \"([^\"]*)\"$")
    public void click_to_select_the_report_format_as_something(String pdf) throws Throwable {
        driver.findElement(By.cssSelector("#frmReportDetails\\:ReportFormat")).click();
        // selects the value you
        WebElement dropdown = driver.findElement(By.xpath("//*[@id=\"frmReportDetails:ReportFormat_items\"]"));
        List<WebElement> entity_list = dropdown.findElements(By.tagName("li"));
        for (WebElement li : entity_list) {
            System.out.print(000);
            if (li.getText().equals(pdf)) {
                li.click();
                System.out.print(0000);
                //to stop the loop
                break;
            }
        }
        Thread.sleep(4000);
    }

    @Then("^Select the taxpayer category \"([^\"]*)\"$")
    public void select_the_taxpayer_category_something(String taxpayer_category) throws Throwable {
        //#frmReportDetails\:Taxpayer_Category_items
        //
        driver.findElement(By.xpath("//*[@id=\"frmReportDetails:Taxpayer_Category\"]/div[3]")).click();
        // selects the value you want
        WebElement dropdown = driver.findElement(By.xpath("//*[@id=\"frmReportDetails:Taxpayer_Category_items\"]"));
        List<WebElement> entity_list = dropdown.findElements(By.tagName("li"));
        for (WebElement li : entity_list) {
            System.out.print(000);
            if (li.getText().equals(taxpayer_category)) {
                li.click();
                System.out.print(0000);
                //to stop the loop
                break;
            }

        }
        Thread.sleep(2000);
    }

    @Then("^Click to select TAX OFFICE \"([^\"]*)\"$")
    public void click_to_Select_tax_office_something(String tax_office) throws Throwable {
        driver.findElement(By.cssSelector("#frmReportDetails\\:TAX_OFFICE")).click();
        Thread.sleep(2000);
        WebElement dropdown = driver.findElement(By.xpath("//*[@id=\"frmReportDetails:TAX_OFFICE_items\"]"));
        List<WebElement> entity_list = dropdown.findElements(By.tagName("li"));
        for (WebElement li : entity_list) {
            System.out.print(000);
            if (li.getText().equals(tax_office)) {
                li.click();
                System.out.print(0000);
                //to stop the loop
                break;
            }

        }
        Thread.sleep(2000);
    }

    @Then("^select a  Return Type \"([^\"]*)\"$")
    public void select_a_return_Type_something(String return_type) throws Throwable {
        driver.findElement(By.cssSelector("#frmReportDetails\\:RETURN_TYPE")).click();
        Thread.sleep(2000);
        WebElement dropdown = driver.findElement(By.xpath("//*[@id=\"frmReportDetails:RETURN_TYPE_items\"]"));
        List<WebElement> entity_list = dropdown.findElements(By.tagName("li"));
        for (WebElement li : entity_list) {
            System.out.print(000);
            if (li.getText().equals(return_type)) {
                li.click();
                System.out.print(0000);
                //to stop the loop
                break;
            }

        }
        Thread.sleep(2000);
    }


    @And("^click to enter the Start Date and  end Date provided$")
    public void click_to_enter_the_start_date_and_end_date_provided() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"frmReportDetails:StartDate_input\"]")).sendKeys("10/07/2020");
        driver.findElement(By.xpath("//*[@id=\"frmReportDetails:EndDate_input\"]")).sendKeys("12/07/2020");
        Thread.sleep(1000);
    }

    @And("^select Business Sector \"([^\"]*)\"$")
    public void select_business__sector_something(String business_sector) throws Throwable {
        driver.findElement(By.cssSelector("#frmReportDetails\\:BUSINESS_SECTOR")).click();
        Thread.sleep(2000);
        WebElement dropdown = driver.findElement(By.xpath("//*[@id=\"frmReportDetails:BUSINESS_SECTOR_items\"]"));
        List<WebElement> entity_list = dropdown.findElements(By.tagName("li"));
        for (WebElement li : entity_list) {
            System.out.print(000);
            if (li.getText().equals(business_sector)) {
                li.click();
                System.out.print(0000);
                //to stop the loop
                break;
            }

        }
        Thread.sleep(2000);
    }

    @And("^click on Run Report$")
    public void click_on_run_report() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"frmReportDetails:RunReport\"]")).click();
        Thread.sleep(4000);
    }

    //------------------------UAT_M4-09-10---------------------//

    @Then("^Navigate to the reporting-->reports$")
    public void navigate_to_the_Reportingreports() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"MenuForm:j_idt29\"]/ul/li[4]/a")).click();
        driver.findElement(By.xpath("//*[@id=\"MenuForm:j_idt29\"]/ul/li[4]/ul/li[1]/a")).click();
    }

    @And("^click Returns Keyed Awaiting Approval link$")
    public void click_returns_keyed_awaiting_approval_link() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"ReportTree:t1:5_6:j_idt42\"]")).click();
    }

    @Then("^Select the report Format  \"([^\"]*)\"$")
    public void select_the_report__format_something(String pdf) throws Throwable {
        driver.findElement(By.cssSelector("#frmReportDetails\\:ReportFormat")).click();
        // selects the value you
        WebElement dropdown = driver.findElement(By.xpath("//*[@id=\"frmReportDetails:ReportFormat_items\"]"));
        List<WebElement> entity_list = dropdown.findElements(By.tagName("li"));
        for (WebElement li : entity_list) {
            System.out.print(000);
            if (li.getText().equals(pdf)) {
                li.click();
                System.out.print(0000);
                //to stop the loop
                break;
            }
        }
        Thread.sleep(4000);
    }

    @Then("^Select on a taxpayer category \"([^\"]*)\"$")
    public void select_on_a_taxpayer_category_something(String taxpayer_category) throws Throwable {
//#frmReportDetails\:Taxpayer_Category_items
        //
        driver.findElement(By.xpath("//*[@id=\"frmReportDetails:Taxpayer_Category\"]/div[3]")).click();
        // selects the value you want
        WebElement dropdown = driver.findElement(By.xpath("//*[@id=\"frmReportDetails:Taxpayer_Category_items\"]"));
        List<WebElement> entity_list = dropdown.findElements(By.tagName("li"));
        for (WebElement li : entity_list) {
            System.out.print(000);
            if (li.getText().equals(taxpayer_category)) {
                li.click();
                System.out.print(0000);
                //to stop the loop
                break;
            }

        }
        Thread.sleep(2000);
    }

    @Then("^select the tax Office \"([^\"]*)\"$")
    public void select_the_tax_Office_something(String tax_office) throws Throwable {
        driver.findElement(By.cssSelector("#frmReportDetails\\:TAX_OFFICE")).click();
        Thread.sleep(2000);
        WebElement dropdown = driver.findElement(By.xpath("//*[@id=\"frmReportDetails:TAX_OFFICE_items\"]"));
        List<WebElement> entity_list = dropdown.findElements(By.tagName("li"));
        for (WebElement li : entity_list) {
            System.out.print(000);
            if (li.getText().equals(tax_office)) {
                li.click();
                System.out.print(0000);
                //to stop the loop
                break;
            }

        }
        Thread.sleep(2000);
    }

    @Then("^click Return type \"([^\"]*)\"$")
    public void click_return_type_something(String return_type) throws Throwable {
        driver.findElement(By.cssSelector("#frmReportDetails\\:RETURN_TYPE")).click();
        Thread.sleep(2000);
        WebElement dropdown = driver.findElement(By.xpath("//*[@id=\"frmReportDetails:RETURN_TYPE_items\"]"));
        List<WebElement> entity_list = dropdown.findElements(By.tagName("li"));
        for (WebElement li : entity_list) {
            System.out.print(000);
            if (li.getText().equals(return_type)) {
                li.click();
                System.out.print(0000);
                //to stop the loop
                break;
            }

        }
        Thread.sleep(2000);
    }


    @And("^enter the Start Date and  End Date Provided$")
    public void enter_the_Start_date_and_end_date_provided() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"frmReportDetails:StartDate_input\"]")).sendKeys("11/07/2020");
        driver.findElement(By.xpath("//*[@id=\"frmReportDetails:EndDate_input\"]")).sendKeys("12/07/2020");
        Thread.sleep(1000);
    }

    @And("^click to Select a business Sector \"([^\"]*)\"$")
    public void click_to_select_a_Business_sector_something(String business_sector) throws Throwable {
        driver.findElement(By.cssSelector("#frmReportDetails\\:BUSINESS_SECTOR")).click();
        Thread.sleep(2000);
        WebElement dropdown = driver.findElement(By.xpath("//*[@id=\"frmReportDetails:BUSINESS_SECTOR_items\"]"));
        List<WebElement> entity_list = dropdown.findElements(By.tagName("li"));
        for (WebElement li : entity_list) {
            System.out.print(000);
            if (li.getText().equals(business_sector)) {
                li.click();
                System.out.print(0000);
                //to stop the loop
                break;
            }

        }
        Thread.sleep(2000);
    }

    @And("^Run_Report$")
    public void runreport() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"frmReportDetails:RunReport\"]")).click();
        Thread.sleep(4000);
    }

    //------------------------UAT_M4-09-11---------------------//

    @Then("^Click on the Reporting-->Reports$")
    public void click_on_the_reportingreports() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"MenuForm:j_idt29\"]/ul/li[4]/a")).click();
        driver.findElement(By.xpath("//*[@id=\"MenuForm:j_idt29\"]/ul/li[4]/ul/li[1]/a")).click();
    }

    @And("^click Employee Contribution link$")
    public void click_employee_contribution_link() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"ReportTree:t1:5_1:j_idt42\"]")).click();
    }

    @Then("^Select the report Format_As \"([^\"]*)\"$")
    public void select_the_report_formatas_something(String pdf) throws Throwable {
        driver.findElement(By.cssSelector("#frmReportDetails\\:ReportFormat")).click();
        // selects the value you
        WebElement dropdown = driver.findElement(By.xpath("//*[@id=\"frmReportDetails:ReportFormat_items\"]"));
        List<WebElement> entity_list = dropdown.findElements(By.tagName("li"));
        for (WebElement li : entity_list) {
            System.out.print(000);
            if (li.getText().equals(pdf)) {
                li.click();
                System.out.print(0000);
                //to stop the loop
                break;
            }
        }
        Thread.sleep(4000);
    }

    @Then("^Enter Employer TIN$")
    public void enter_employer_tin() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"frmReportDetails:TaxpayerTIN\"]")).sendKeys("VP0022044");
    }


    @And("^enter the start Date and  end Date Provided$")
    public void enter_the_Start_date_and_End_date_provided() throws Throwable {

        driver.findElement(By.xpath("//*[@id=\"frmReportDetails:StartDate_input\"]")).sendKeys("11/07/2020");
        driver.findElement(By.xpath("//*[@id=\"frmReportDetails:EndDate_input\"]")).sendKeys("12/07/2020");
        Thread.sleep(1000);
    }

    @And("^Run The_Report$")
    public void run__the__report() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"frmReportDetails:RunReport\"]")).click();
        Thread.sleep(4000);
    }


    //------------------------UAT_M4-09-12---------------------//

    @Then("^Navigate to the reporting--->Reports$")
    public void navigate_To_the_reportingreports() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"MenuForm:j_idt29\"]/ul/li[4]/a")).click();
        driver.findElement(By.xpath("//*[@id=\"MenuForm:j_idt29\"]/ul/li[4]/ul/li[1]/a")).click();
    }

    @Then("^Select the report Format_as \"([^\"]*)\"$")
    public void select_the__report_format_as_something(String pdf) throws Throwable {
        driver.findElement(By.cssSelector("#frmReportDetails\\:ReportFormat")).click();
        // selects the value you
        WebElement dropdown = driver.findElement(By.xpath("//*[@id=\"frmReportDetails:ReportFormat_items\"]"));
        List<WebElement> entity_list = dropdown.findElements(By.tagName("li"));
        for (WebElement li : entity_list) {
            System.out.print(000);
            if (li.getText().equals(pdf)) {
                li.click();
                System.out.print(0000);
                //to stop the loop
                break;
            }
        }
        Thread.sleep(4000);
    }

    @And("^click VAT Excess Return link$")
    public void click_vat_excess_return_link() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"ReportTree:t1:5_9:j_idt42\"]")).click();
    }

    @And("^enter the start_Date and  end Date provided$")
    public void enter_the_startdate_and_end_date_provided() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"frmReportDetails:StartDate_input\"]")).sendKeys("11/07/2020");
        driver.findElement(By.xpath("//*[@id=\"frmReportDetails:EndDate_input\"]")).sendKeys("12/07/2020");
        Thread.sleep(1000);
    }

    @And("^Enter employer tin$")
    public void enter_employer__tin() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"frmReportDetails:TIN_NUMBER\"]")).sendKeys("V2009P44");
    }

    @And("^The Business__Sector \"([^\"]*)\"$")
    public void the_businesssector_something(String business_sector) throws Throwable {
        driver.findElement(By.cssSelector("#frmReportDetails\\:BUSINESS_SECTOR")).click();
        Thread.sleep(2000);
        WebElement dropdown = driver.findElement(By.xpath("//*[@id=\"frmReportDetails:BUSINESS_SECTOR_items\"]"));
        List<WebElement> entity_list = dropdown.findElements(By.tagName("li"));
        for (WebElement li : entity_list) {
            System.out.print(000);
            if (li.getText().equals(business_sector)) {
                li.click();
                System.out.print(0000);
                //to stop the loop
                break;
            }

        }
        Thread.sleep(2000);
    }

    @And("^Click to run the_Report$")
    public void click_to_Run_the_report() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"frmReportDetails:RunReport\"]")).click();
        Thread.sleep(4000);
    }


    //------------------------UAT_M4-09-13---------------------//

    @Then("^Navigate to The Reporting----->Reports$")
    public void navigate_to_the__reportingreports() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"MenuForm:j_idt29\"]/ul/li[4]/a")).click();
        driver.findElement(By.xpath("//*[@id=\"MenuForm:j_idt29\"]/ul/li[4]/ul/li[1]/a")).click();
    }

    @And("^click on Amended Returns Report link$")
    public void click_on_amended_returns_report_link() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"ReportTree:t1:5_0:j_idt42\"]")).click();
    }

    @And("^run Thee_Report$")
    public void run_theereport() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"frmReportDetails:RunReport\"]")).click();
        Thread.sleep(4000);
    }

    @Then("^Click Taxpayer_Category \"([^\"]*)\"$")
    public void click_taxpayercategory_something(String taxpayer_category) throws Throwable {
//#frmReportDetails\:Taxpayer_Category_items
        //
        driver.findElement(By.xpath("//*[@id=\"frmReportDetails:Taxpayer_Category\"]/div[3]")).click();
        // selects the value you want
        WebElement dropdown = driver.findElement(By.xpath("//*[@id=\"frmReportDetails:Taxpayer_Category_items\"]"));
        List<WebElement> entity_list = dropdown.findElements(By.tagName("li"));
        for (WebElement li : entity_list) {
            System.out.print(000);
            if (li.getText().equals(taxpayer_category)) {
                li.click();
                System.out.print(0000);
                //to stop the loop
                break;
            }

        }
        Thread.sleep(2000);
    }

    @Then("^Select the report format_As \"([^\"]*)\"$")
    public void select_the_report_format__as_something(String pdf) throws Throwable {
        driver.findElement(By.cssSelector("#frmReportDetails\\:ReportFormat")).click();
        // selects the value you
        WebElement dropdown = driver.findElement(By.xpath("//*[@id=\"frmReportDetails:ReportFormat_items\"]"));
        List<WebElement> entity_list = dropdown.findElements(By.tagName("li"));
        for (WebElement li : entity_list) {
            System.out.print(000);
            if (li.getText().equals(pdf)) {
                li.click();
                System.out.print(0000);
                //to stop the loop
                break;
            }
        }
        Thread.sleep(4000);
    }


    @Then("^Click Tax_Office \"([^\"]*)\"$")
    public void click_taxoffice_something(String tax_office) throws Throwable {
        driver.findElement(By.cssSelector("#frmReportDetails\\:TAX_OFFICE")).click();
        Thread.sleep(2000);
        WebElement dropdown = driver.findElement(By.xpath("//*[@id=\"frmReportDetails:TAX_OFFICE_items\"]"));
        List<WebElement> entity_list = dropdown.findElements(By.tagName("li"));
        for (WebElement li : entity_list) {
            System.out.print(000);
            if (li.getText().equals(tax_office)) {
                li.click();
                System.out.print(0000);
                //to stop the loop
                break;
            }

        }
        Thread.sleep(2000);
    }

    @Then("^Click to select  Return_Type \"([^\"]*)\"$")
    public void click_to_select_returntype_something(String return_type) throws Throwable {
        driver.findElement(By.cssSelector("#frmReportDetails\\:RETURN_TYPE")).click();
        Thread.sleep(2000);
        WebElement dropdown = driver.findElement(By.xpath("//*[@id=\"frmReportDetails:RETURN_TYPE_items\"]"));
        List<WebElement> entity_list = dropdown.findElements(By.tagName("li"));
        for (WebElement li : entity_list) {
            System.out.print(000);
            if (li.getText().equals(return_type)) {
                li.click();
                System.out.print(0000);
                //to stop the loop
                break;
            }

        }
        Thread.sleep(2000);
    }


    @And("^enter the start Date and  end_Date provided$")
    public void enter_the_start_date_and_enddate_provided() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"frmReportDetails:StartDate_input\"]")).sendKeys("11/07/2020");
        driver.findElement(By.xpath("//*[@id=\"frmReportDetails:EndDate_input\"]")).sendKeys("12/07/2020");
        Thread.sleep(1000);
    }

    @And("^Click to select Business_Sector \"([^\"]*)\"$")
    public void click_to_select_businesssector_something(String business_sector) throws Throwable {
        driver.findElement(By.cssSelector("#frmReportDetails\\:BUSINESS_SECTOR")).click();
        Thread.sleep(2000);
        WebElement dropdown = driver.findElement(By.xpath("//*[@id=\"frmReportDetails:BUSINESS_SECTOR_items\"]"));
        List<WebElement> entity_list = dropdown.findElements(By.tagName("li"));
        for (WebElement li : entity_list) {
            System.out.print(000);
            if (li.getText().equals(business_sector)) {
                li.click();
                System.out.print(0000);
                //to stop the loop
                break;
            }

        }
        Thread.sleep(2000);
    }

    @And("^run The_report$")
    public void Run_the_Report() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"frmReportDetails:RunReport\"]")).click();
        Thread.sleep(4000);
    }

//------------------------UAT_M4-09-14---------------------//

    @Then("^navigate To the Reporting----->Reports$")
    public void navigate_to_the_reporting_reports() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"MenuForm:j_idt29\"]/ul/li[4]/a")).click();
        driver.findElement(By.xpath("//*[@id=\"MenuForm:j_idt29\"]/ul/li[4]/ul/li[1]/a")).click();
    }

    @And("^click Fact of Filing Report Link$")
    public void Click_fact_of_filing_report_link() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"ReportTree:t1:5_3:j_idt42\"]")).click();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Then("^Select the Report_Format As \"([^\"]*)\"$")
    public void select_the_reportformat_as_something(String pdf) throws Throwable {
        driver.findElement(By.cssSelector("#frmReportDetails\\:ReportFormat")).click();
        // selects the value you
        WebElement dropdown = driver.findElement(By.xpath("//*[@id=\"frmReportDetails:ReportFormat_items\"]"));
        List<WebElement> entity_list = dropdown.findElements(By.tagName("li"));
        for (WebElement li : entity_list) {
            System.out.print(000);
            if (li.getText().equals(pdf)) {
                li.click();
                System.out.print(0000);
                //to stop the loop
                break;
            }
        }
        Thread.sleep(4000);
    }


    @Then("^Select Tax___Office \"([^\"]*)\"$")
    public void select_taxoffice__something(String tax_office) throws Throwable {
        driver.findElement(By.cssSelector("#frmReportDetails\\:TAX_OFFICE")).click();
        Thread.sleep(2000);
        WebElement dropdown = driver.findElement(By.xpath("//*[@id=\"frmReportDetails:TAX_OFFICE_items\"]"));
        List<WebElement> entity_list = dropdown.findElements(By.tagName("li"));
        for (WebElement li : entity_list) {
            System.out.print(000);
            if (li.getText().equals(tax_office)) {
                li.click();
                System.out.print(0000);
                //to stop the loop
                break;
            }

        }
        Thread.sleep(2000);
    }

    @Then("^select Return__Type \"([^\"]*)\"$")
    public void select_returntype_Something(String return_type) throws Throwable {
        driver.findElement(By.cssSelector("#frmReportDetails\\:RETURN_TYPE")).click();
        Thread.sleep(2000);
        WebElement dropdown = driver.findElement(By.xpath("//*[@id=\"frmReportDetails:RETURN_TYPE_items\"]"));
        List<WebElement> entity_list = dropdown.findElements(By.tagName("li"));
        for (WebElement li : entity_list) {
            System.out.print(000);
            if (li.getText().equals(return_type)) {
                li.click();
                System.out.print(0000);
                //to stop the loop
                break;
            }

        }
        Thread.sleep(2000);
    }


    @And("^enter the start_Date and  end_Date provided$")
    public void enter_the_startdate_and_enddate_provided() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"frmReportDetails:StartDate_input\"]")).sendKeys("11/07/2020");
        driver.findElement(By.xpath("//*[@id=\"frmReportDetails:EndDate_input\"]")).sendKeys("12/07/2020");
        Thread.sleep(1000);
    }

    @And("^select a Business___Sector \"([^\"]*)\"$")
    public void select_a_businesssector_something(String business_sector) throws Throwable {
        driver.findElement(By.cssSelector("#frmReportDetails\\:BUSINESS_SECTOR")).click();
        Thread.sleep(2000);
        WebElement dropdown = driver.findElement(By.xpath("//*[@id=\"frmReportDetails:BUSINESS_SECTOR_items\"]"));
        List<WebElement> entity_list = dropdown.findElements(By.tagName("li"));
        for (WebElement li : entity_list) {
            System.out.print(000);
            if (li.getText().equals(business_sector)) {
                li.click();
                System.out.print(0000);
                //to stop the loop
                break;
            }

        }
        Thread.sleep(2000);
    }

    //-------------------------------------PAYE Credit-----------------------------------------//

    @Then("^Navigate to click Return Filing and Processing-->PAYE Credit$")
    public void navigate_to__click_return_filing_and_processingpaye_credit() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"MenuForm:j_idt29\"]/ul/li[11]/a/span[2]")).click();
        driver.findElement(By.xpath("//*[@id=\"MenuForm:j_idt29\"]/ul/li[11]/ul/li[6]")).click();
        Thread.sleep(7000);
    }

    @And("^Logout$")
    public void logout() throws Throwable {
        driver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//*[@id=\"Logout\"]")).click();
        Thread.sleep(8000);
    }


    @Then("^Navigate to click Return Filing and Processing--->PAYE Credit$")
    public void navigate__to_click_return_filing_and_processingpaye_credit() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"MenuForm:j_idt29\"]/ul/li[11]/a/span[2]")).click();
        driver.findElement(By.xpath("//*[@id=\"MenuForm:j_idt29\"]/ul/li[11]/ul/li[6]")).click();
        Thread.sleep(7000);
    }

    @And("^click find$")
    public void employees_tin_and_click_find() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"FormPayeCredit:FindTin\"]")).click();
        Thread.sleep(5000);

        Thread.sleep(3000);
        WebElement AddCategoryIframe = driver.findElement(By.tagName("iframe"));
        driver.switchTo().frame(AddCategoryIframe);
        Thread.sleep(2000);
    }

    @Then("^enter employee's TIN$")
    public void enter_employees_tin() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"SearchForm:accountNumber\"]")).sendKeys("P0022044");
        Thread.sleep(5000);
        //click search button
        driver.findElement(By.xpath("//*[@id=\"SearchForm:j_idt21\"]")).click();
        Thread.sleep(5000);
        driver.switchTo().defaultContent();
    }

    @Then("^Enter prompted details$")
    public void enter_prompted_details() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"FormPayeCredit:employerName\"]")).sendKeys("Seis");
        driver.findElement(By.xpath("//*[@id=\"FormPayeCredit:employerTin\"]")).sendKeys("V003445");
        driver.findElement(By.xpath("//*[@id=\"FormPayeCredit:periodFrom_input\"]")).sendKeys("11/7/2019");
        driver.findElement(By.xpath("//*[@id=\"FormPayeCredit:periodTo_input\"]")).sendKeys("11/07/2020");
        Thread.sleep(8000);
    }


    @And("^Click Search button$")
    public void click_search_button() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"FormPayeCredit:search\"]")).click();
        Thread.sleep(2000);
    }

    @Then("^Click Logout$")
    public void click_logout() throws Throwable {
        driver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//*[@id=\"Logout\"]")).click();
        Thread.sleep(8000);
    }


    //--------------------------------------Cancel Tax Return-----------------------------------------------------------------//
//1
    @Then("^navigate To the Return Filing and  Processing--->Cancel Return$")
    public void navigate_to_the_return_filing_and_processingcancel_return() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"MenuForm:j_idt29\"]/ul/li[11]/a/span[2]")).click();
        driver.findElement(By.xpath("//*[@id=\"MenuForm:j_idt29\"]/ul/li[11]/ul/li[5]")).click();
    }

    @Then("^Select return document \"([^\"]*)\" and click next$")
    public void select_return_document_something_and_click_next(String return_document) throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"FormSelection:returnType\"]/div[3]")).click();
        Thread.sleep(2000);
        WebElement dropdown = driver.findElement(By.xpath("//*[@id=\"FormSelection:returnType_items\"]"));
        List<WebElement> entity_list = dropdown.findElements(By.tagName("li"));
        for (WebElement li : entity_list) {
            System.out.print(000);
            if (li.getText().equals(return_document)) {
                li.click();
                System.out.print(0000);
                //to stop the loop
                break;
            }

        }
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"FormSelection:nextReturnButton\"]")).click();
    }


    @Then("^enter period year and period number and click search$")
    public void enter_period_year_and_period_number_and_click_search() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"SearchForm:periodyear\"]")).sendKeys("2019");
        driver.findElement(By.xpath("//*[@id=\"SearchForm:periodnumber\"]")).sendKeys("1");
        Thread.sleep(5000);
        driver.findElement(By.xpath("//*[@id=\"SearchForm:j_idt42\"]")).click();
        Thread.sleep(5000);

    }

    @And("^Select any PAYE Return and click continue$")
    public void select_any_paye_return_and_click_continue() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"SearchForm:resultsDataTable_data\"]/tr[1]")).click();
        Thread.sleep(5000);
        driver.findElement(By.xpath("//*[@id=\"SearchForm:j_id14\"]")).click();
        Thread.sleep(9000);
    }

    @And("^Click Cancel$")
    public void click__cancel() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"FlexibleFormEntity:Cancel\"]")).click();
        Thread.sleep(9000);
    }


    //2
    @Then("^navigate To the Return Filing and  Processing-->Cancel Return$")
    public void navigate_to_the_return__filing_and_processingcancel_return() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"MenuForm:j_idt29\"]/ul/li[11]/a/span[2]")).click();
        driver.findElement(By.xpath("//*[@id=\"MenuForm:j_idt29\"]/ul/li[11]/ul/li[5]")).click();
    }

    @Then("^Select a return document \"([^\"]*)\" and click next$")
    public void select_a_return_document_something_and_click_next(String return_document) throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"FormSelection:returnType\"]/div[3]")).click();
        Thread.sleep(2000);
        WebElement dropdown = driver.findElement(By.xpath("//*[@id=\"FormSelection:returnType_items\"]"));
        List<WebElement> entity_list = dropdown.findElements(By.tagName("li"));
        for (WebElement li : entity_list) {
            System.out.print(000);
            if (li.getText().equals(return_document)) {
                li.click();
                System.out.print(0000);
                //to stop the loop
                break;
            }

        }
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"FormSelection:nextReturnButton\"]")).click();
    }

    @Then("^enter a period year and period number and click search$")
    public void enter_a_period_year_and_period_number_and_click_search() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"SearchForm:periodyear\"]")).sendKeys("2019");
        driver.findElement(By.xpath("//*[@id=\"SearchForm:periodnumber\"]")).sendKeys("1");
        Thread.sleep(5000);
        driver.findElement(By.xpath("//*[@id=\"SearchForm:j_idt42\"]")).click();
        Thread.sleep(5000);
    }

    @And("^select any PAYE Return and click continue$")
    public void select_any_paye_return_and__click_continue() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"SearchForm:resultsDataTable_data\"]/tr[1]")).click();
        Thread.sleep(5000);
        driver.findElement(By.xpath("//*[@id=\"SearchForm:j_id14\"]")).click();
        Thread.sleep(9000);
    }

    @And("^click on reason cancellation \"([^\"]*)\" and click cancel return$")
    public void click_on_reason_cancellation_something_and_click_cancel_return(String reason) throws Throwable {
        //reason for cancellation
        driver.findElement(By.xpath("//*[@id=\"FlexibleFormEntity:ReasonForCancellation\"]/div[3]")).click();
        Thread.sleep(2000);
        WebElement dropdown = driver.findElement(By.xpath("//*[@id=\"FlexibleFormEntity:ReasonForCancellation_items\"]"));
        List<WebElement> entity_list = dropdown.findElements(By.tagName("li"));
        for (WebElement li : entity_list) {
            System.out.print(000);
            if (li.getText().equals(reason)) {
                li.click();
                System.out.print(0000);
                //to stop the loop
                break;
            }

        }
        Thread.sleep(2000);

        driver.findElement(By.xpath("//*[@id=\"FlexibleFormEntity:cancel\"]")).click();
        Thread.sleep(9000);

        driver.findElement(By.xpath("//*[@id=\"FlexibleFormEntity:j_idt32\"]")).click();
        Thread.sleep(7000);
        driver.switchTo().defaultContent();
        Thread.sleep(7000);
        //iframe dialog box
        // Thread.sleep(3000);
        //WebElement AddCategoryIframe = driver.findElement(By.xpath("//*[@id=\"FlexibleFormEntity:j_idt31\"]"));
        //  driver.switchTo().frame(AddCategoryIframe);
        // Thread.sleep(2000);
        // driver.findElement(By.xpath("//*[@id=\"FlexibleFormEntity:j_idt32\"]")).click();
    }

    //3
    @Then("^navigate To the Return Filing and  Processing->Cancel Return$")
    public void navigate_to_The_return_filing_and_processingcancel_return() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"MenuForm:j_idt29\"]/ul/li[11]/a/span[2]")).click();
        driver.findElement(By.xpath("//*[@id=\"MenuForm:j_idt29\"]/ul/li[11]/ul/li[5]")).click();

    }

    @Then("^select a return document \"([^\"]*)\" and click next$")
    public void select_a_return_document_something__and_click_next(String return_document) throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"FormSelection:returnType\"]/div[3]")).click();
        Thread.sleep(2000);
        WebElement dropdown = driver.findElement(By.xpath("//*[@id=\"FormSelection:returnType_items\"]"));
        List<WebElement> entity_list = dropdown.findElements(By.tagName("li"));
        for (WebElement li : entity_list) {
            System.out.print(000);
            if (li.getText().equals(return_document)) {
                li.click();
                System.out.print(0000);
                //to stop the loop
                break;
            }

        }
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"FormSelection:nextReturnButton\"]")).click();
    }

    @Then("^Enter a period year and period number and click search$")
    public void Enter_a_period_year_and_period_number_and_click_search() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"SearchForm:periodyear\"]")).sendKeys("2019");
        driver.findElement(By.xpath("//*[@id=\"SearchForm:periodnumber\"]")).sendKeys("1");
        Thread.sleep(5000);
        driver.findElement(By.xpath("//*[@id=\"SearchForm:j_idt42\"]")).click();
        Thread.sleep(5000);
    }

    @And("^select Any PAYE Return and click continue$")
    public void select_any_paye_Return_and_Click_continue() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"SearchForm:resultsDataTable_data\"]/tr[1]")).click();
        Thread.sleep(5000);
        driver.findElement(By.xpath("//*[@id=\"SearchForm:j_id14\"]")).click();
        Thread.sleep(9000);
    }

    @And("^click on Reason for cancellation \"([^\"]*)\" and click cancel$")
    public void click_on_reason_for_cancellation_something_and_click_cancel(String reason) throws Throwable {
        //reason for cancellation
        driver.findElement(By.xpath("/html/body/div[3]/form/div[2]/div[2]/div[10]/div/div/div[2]/div/div[3]")).click();
        Thread.sleep(2000);
        WebElement dropdown = driver.findElement(By.xpath("/html/body/div[6]/div/ul"));
        List<WebElement> entity_list = dropdown.findElements(By.tagName("li"));
        for (WebElement li : entity_list) {
            System.out.print(000);
            if (li.getText().equals(reason)) {
                li.click();
                System.out.print(0000);
                //to stop the loop
                break;
            }

        }
        Thread.sleep(2000);

        driver.findElement(By.xpath("//*[@id=\"FlexibleFormEntity:Cancel\"]")).click();
        Thread.sleep(9000);
    }

    //4
    @Then("^navigate To The Return Filing and  Processing->Cancel Return$")
    public void navigate__to_the_return__filing_and_processingcancel_return() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"MenuForm:j_idt29\"]/ul/li[11]/a/span[2]")).click();
        driver.findElement(By.xpath("//*[@id=\"MenuForm:j_idt29\"]/ul/li[11]/ul/li[5]")).click();

    }

    @Then("^select A return document \"([^\"]*)\" and click next$")
    public void select_A_return_document_something_and_click_next(String return_document) throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"FormSelection:returnType\"]/div[3]")).click();
        Thread.sleep(2000);
        WebElement dropdown = driver.findElement(By.xpath("//*[@id=\"FormSelection:returnType_items\"]"));
        List<WebElement> entity_list = dropdown.findElements(By.tagName("li"));
        for (WebElement li : entity_list) {
            System.out.print(000);
            if (li.getText().equals(return_document)) {
                li.click();
                System.out.print(0000);
                //to stop the loop
                break;
            }

        }
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"FormSelection:nextReturnButton\"]")).click();
    }

    @Then("^Enter A period year and period number and click search$")
    public void enter_a_period_Year_and_period_number_and_click_search() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"SearchForm:periodyear\"]")).sendKeys("2019");
        driver.findElement(By.xpath("//*[@id=\"SearchForm:periodnumber\"]")).sendKeys("1");
        Thread.sleep(5000);
        driver.findElement(By.xpath("//*[@id=\"SearchForm:j_idt42\"]")).click();
        Thread.sleep(5000);
    }

    @And("^Select Any PAYE Return and click continue$")
    public void select_any_paye_return__and_click_continue() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"SearchForm:resultsDataTable_data\"]/tr[1]")).click();
        Thread.sleep(5000);
        driver.findElement(By.xpath("//*[@id=\"SearchForm:j_id14\"]")).click();
        Thread.sleep(9000);
    }

    @And("^click On Reason for cancellation \"([^\"]*)\" and click cancel$")
    public void click_on_reason_for_Cancellation_something_and_click_cancel(String reason) throws Throwable {
        //reason for cancellation
        driver.findElement(By.xpath("/html/body/div[3]/form/div[2]/div[2]/div[10]/div/div/div[2]/div/div[3]")).click();
        Thread.sleep(2000);
        WebElement dropdown = driver.findElement(By.xpath("/html/body/div[6]/div/ul"));
        List<WebElement> entity_list = dropdown.findElements(By.tagName("li"));
        for (WebElement li : entity_list) {
            System.out.print(000);
            if (li.getText().equals(reason)) {
                li.click();
                System.out.print(0000);
                //to stop the loop
                break;
            }

        }
        Thread.sleep(2000);

        driver.findElement(By.xpath("//*[@id=\"FlexibleFormEntity:cancel\"]")).click();
        Thread.sleep(9000);
        //

        driver.findElement(By.xpath("//*[@id=\"FlexibleFormEntity:j_idt33\"]")).click();
        Thread.sleep(7000);
        driver.switchTo().defaultContent();
        Thread.sleep(7000);

        driver.findElement(By.xpath("//*[@id=\"FlexibleFormEntity:Cancel\"]")).click();
        driver.switchTo().defaultContent();
    }

    //5
    @Then("^navigate To The Return Filing and  Processing-->Cancel Return$")
    public void navigate_to_the_return_filing_and_Processingcancel_return() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"MenuForm:j_idt29\"]/ul/li[11]/a/span[2]")).click();
        driver.findElement(By.xpath("//*[@id=\"MenuForm:j_idt29\"]/ul/li[11]/ul/li[5]")).click();
    }

    @Then("^Select a return document \"([^\"]*)\" and click Next$")
    public void select_a_return_document_something_and_Click_next(String return_document) throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"FormSelection:returnType\"]/div[3]")).click();
        Thread.sleep(2000);
        WebElement dropdown = driver.findElement(By.xpath("//*[@id=\"FormSelection:returnType_items\"]"));
        List<WebElement> entity_list = dropdown.findElements(By.tagName("li"));
        for (WebElement li : entity_list) {
            System.out.print(000);
            if (li.getText().equals(return_document)) {
                li.click();
                System.out.print(0000);
                //to stop the loop
                break;
            }

        }
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"FormSelection:nextReturnButton\"]")).click();
    }

    @Then("^enter a period year and period number and click Search$")
    public void enter_a_period_year_and_period_Number_and_click_search() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"SearchForm:periodyear\"]")).sendKeys("2019");
        driver.findElement(By.xpath("//*[@id=\"SearchForm:periodnumber\"]")).sendKeys("1");
        Thread.sleep(5000);
        driver.findElement(By.xpath("//*[@id=\"SearchForm:j_idt42\"]")).click();
        Thread.sleep(5000);
    }

    @And("^select any PAYE Return and click Continue$")
    public void select_any_paye_return_And_click_continue() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"SearchForm:resultsDataTable_data\"]/tr[1]")).click();
        Thread.sleep(5000);
        driver.findElement(By.xpath("//*[@id=\"SearchForm:j_id14\"]")).click();
        Thread.sleep(9000);
    }

    @And("^click on the cancel Return$")
    public void click_on_Reason_cancellation_something_and_click_cancel_return() throws Throwable {
        Thread.sleep(7000);

        driver.findElement(By.xpath("//*[@id=\"FlexibleFormEntity:Cancel\"]")).click();
        Thread.sleep(9000);
        driver.switchTo().defaultContent();
    }


//--------------------------------------Print Return Processing Document-----------------------------------------------------------------//
//1

    @Then("^navigate To the Return Filing and Processing--->Lodge Return$")
    public void navigate_to_the_return_filing_and_processinglodge_return() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"MenuForm:j_idt29\"]/ul/li[11]/a/span[2]")).click();
        driver.findElement(By.xpath("//*[@id=\"MenuForm:j_idt29\"]/ul/li[11]/ul/li[1]/a")).click();
        Thread.sleep(1000);
    }

    @Then("^click on return document$")
    public void click_on_return_document() throws Throwable {
        driver.findElement(By.xpath("/html/body/div[3]/div/form/div[2]/div[2]/div[1]/div/div[1]/div[3]/button")).click();
        ////*[@id="ReturnsLodgement:searchId_dlg"]
        WebElement AddCategoryIframe = driver.findElement(By.tagName("iframe"));
        driver.switchTo().frame(AddCategoryIframe);
        Thread.sleep(5000);
    }

    @Then("^Enter required information \"([^\"]*)\" and click search$")
    public void enter_required_information_something_and_click_search(String tin) throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"SearchForm:TIN\"]")).sendKeys("P0022044");
        driver.findElement(By.xpath("//*[@id=\"SearchForm:j_idt42\"]")).click();
        Thread.sleep(5000);
        //WebElement row = driver.findElement(By.xpath("/html/body/div[3]/div/div[1]/div/form/div[6]/div[3]/table/tbody/tr[3]"));
        // row.click();
    }

    @And("^Select the data and click continue$")
    public void select_the_data_and_click_continue() throws Throwable {
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//*[@id=\"SearchForm:resultsDataTable_data\"]/tr[2]/td[4]")).click();
        Thread.sleep(9000);

        driver.findElement(By.xpath("//*[@id=\"SearchForm:j_id14\"]")).click();
        Thread.sleep(9000);
        driver.switchTo().defaultContent();
        //Liability
        driver.findElement(By.xpath("//*[@id=\"ReturnsLodgement:id_Liability_input\"]")).sendKeys("128765908");
        Thread.sleep(2000);
    }

    @And("^Click save button$")
    public void click_save_button() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"ReturnsLodgement:SaveLodgement\"]")).click();
        Thread.sleep(8000);
    }

    //2
    @Then("^navigate To the Return Filing and Processing>>>Adjust Return$")
    public void navigate_to_the_return_filing_and_processingadjust_return() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"MenuForm:j_idt29\"]/ul/li[11]/a/span[2]")).click();
        driver.findElement(By.xpath("//*[@id=\"MenuForm:j_idt29\"]/ul/li[11]/ul/li[4]")).click();
    }

    @Then("^click on Return Document \"([^\"]*)\" to select return document and click next$")
    public void click_on_Return_document_something_to_select_return_document_and_click_next(String return_document) throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"FormSelection:returnType\"]/div[3]")).click();
        Thread.sleep(2000);
        WebElement dropdown = driver.findElement(By.xpath("//*[@id=\"FormSelection:returnType_items\"]"));
        List<WebElement> entity_list = dropdown.findElements(By.tagName("li"));
        for (WebElement li : entity_list) {
            System.out.print(000);
            if (li.getText().equals(return_document)) {
                li.click();
                System.out.print(0000);
                //to stop the loop
                break;
            }

        }
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"FormSelection:nextReturnButton\"]")).click();
    }

    @Then("^Enter Required information and click search$")
    public void enter_required_information_and_click_search() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"SearchForm:periodyear\"]")).sendKeys("2019");
        driver.findElement(By.xpath("//*[@id=\"SearchForm:periodnumber\"]")).sendKeys("1");
        Thread.sleep(5000);
        driver.findElement(By.xpath("//*[@id=\"SearchForm:j_idt42\"]")).click();
        Thread.sleep(5000);
    }

    @And("^Select Data and click continue$")
    public void select_data_and_click_continue() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"SearchForm:resultsDataTable_data\"]/tr[1]")).click();
        Thread.sleep(5000);
        driver.findElement(By.xpath("//*[@id=\"SearchForm:j_id14\"]")).click();
        Thread.sleep(9000);
    }

    @Then("^Amend the return \"([^\"]*)\"$")
    public void amend_the_return_something(String reason) throws Throwable {
//select the searched data
        driver.findElement(By.xpath("//*[@id=\"FlexibleFormEntity:T_Items_data\"]/tr/td[5]")).click();
        // click update button
        driver.findElement(By.xpath("//*[@id=\"FlexibleFormEntity:T_Items:j_id2\"]")).click();
        //switch to iframe
        WebElement AddCategoryIframe = driver.findElement(By.tagName("iframe"));
        driver.switchTo().frame(AddCategoryIframe);
        Thread.sleep(5000);
        // update basic salary and wages fields
        driver.findElement(By.xpath("//*[@id=\"TestFlexibleForm:BasicSalaryAndWages_input\"]")).clear();
        driver.findElement(By.xpath("//*[@id=\"TestFlexibleForm:BasicSalaryAndWages_input\"]")).sendKeys("150000");
        //click ok button
        driver.findElement(By.xpath("//*[@id=\"TestFlexibleForm:Save\"]")).click();

        driver.switchTo().defaultContent();
        Thread.sleep(2000);

        // reason for amendment
        driver.findElement(By.xpath("/html/body/div[3]/form/div[2]/div[2]/div[6]/div/div/div[2]/div/div[3]")).click();
        Thread.sleep(2000);
        WebElement dropdown = driver.findElement(By.xpath("//*[@id=\"FlexibleFormEntity:ReasonForAmendment_items\"]"));
        List<WebElement> entity_list = dropdown.findElements(By.tagName("li"));
        for (WebElement li : entity_list) {
            System.out.print(000);
            if (li.getText().equals(reason)) {
                li.click();
                System.out.print(0000);
                //to stop the loop
                break;
            }

        }
        Thread.sleep(2000);
    }

    @And("^Click Submit button-max$")
    public void click_submit_button_max() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"FlexibleFormEntity:save\"]")).click();
        Thread.sleep(8000);
    }


    //3
    @Then("^navigate To the Return Filing and Processing--->Cancel Return$")
    public void navigate_to_the_return_filing_and_processing_cancel_return() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"MenuForm:j_idt29\"]/ul/li[11]/a/span[2]")).click();
        driver.findElement(By.xpath("//*[@id=\"MenuForm:j_idt29\"]/ul/li[11]/ul/li[5]")).click();

    }

    @Then("^click on Return document \"([^\"]*)\" to select return document and click next$")
    public void click_on_return_document_something_to_select_return_document_and_click_next(String return_document) throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"FormSelection:returnType\"]/div[3]")).click();
        Thread.sleep(2000);
        WebElement dropdown = driver.findElement(By.xpath("//*[@id=\"FormSelection:returnType_items\"]"));
        List<WebElement> entity_list = dropdown.findElements(By.tagName("li"));
        for (WebElement li : entity_list) {
            System.out.print(000);
            if (li.getText().equals(return_document)) {
                li.click();
                System.out.print(0000);
                //to stop the loop
                break;
            }

        }
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"FormSelection:nextReturnButton\"]")).click();
    }


    @Then("^Enter required information  click search$")
    public void enter_required_information_click_search() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"SearchForm:periodyear\"]")).sendKeys("2019");
        driver.findElement(By.xpath("//*[@id=\"SearchForm:periodnumber\"]")).sendKeys("1");
        Thread.sleep(5000);
        driver.findElement(By.xpath("//*[@id=\"SearchForm:j_idt42\"]")).click();
        Thread.sleep(5000);
    }

    @And("^Select the Data and click continue$")
    public void select__the_data_and_click_continue() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"SearchForm:resultsDataTable_data\"]/tr[1]")).click();
        Thread.sleep(5000);
        driver.findElement(By.xpath("//*[@id=\"SearchForm:j_id14\"]")).click();
        Thread.sleep(9000);
    }

    @And("^Click cancel return button$")
    public void click_cancel_return_button() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"FlexibleFormEntity:cancel\"]")).click();
        Thread.sleep(9000);

        driver.findElement(By.xpath("//*[@id=\"FlexibleFormEntity:j_idt32\"]")).click();
        Thread.sleep(7000);
        driver.switchTo().defaultContent();
        Thread.sleep(7000);
    }


    //--------------------------------------Adjust Tax Return-----------------------------------------------------------------//
//1
    @Then("^navigate To Return Filing and  Processing--->Amend Return$")
    public void navigate_to_return_filing_and_processingamend_return() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"MenuForm:j_idt29\"]/ul/li[11]/a/span[2]")).click();
        driver.findElement(By.xpath("//*[@id=\"MenuForm:j_idt29\"]/ul/li[11]/ul/li[4]")).click();
    }

    @Then("^Select Return document \"([^\"]*)\" and click next$")
    public void select_return_document_something_and_click_Next(String return_document) throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"FormSelection:returnType\"]/div[3]")).click();
        Thread.sleep(2000);
        WebElement dropdown = driver.findElement(By.xpath("//*[@id=\"FormSelection:returnType_items\"]"));
        List<WebElement> entity_list = dropdown.findElements(By.tagName("li"));
        for (WebElement li : entity_list) {
            System.out.print(000);
            if (li.getText().equals(return_document)) {
                li.click();
                System.out.print(0000);
                //to stop the loop
                break;
            }

        }
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"FormSelection:nextReturnButton\"]")).click();
    }

    @Then("^enter period Year and period Number and click search$")
    public void enter__period_year_and_period_number_and_click_search() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"SearchForm:periodyear\"]")).sendKeys("2019");
        driver.findElement(By.xpath("//*[@id=\"SearchForm:periodnumber\"]")).sendKeys("1");
        Thread.sleep(5000);
        driver.findElement(By.xpath("//*[@id=\"SearchForm:j_idt42\"]")).click();
        Thread.sleep(5000);
    }

    @And("^Select Any PAYE Return and click continue button$")
    public void select_any_paye_return_and_click_continue_button() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"SearchForm:resultsDataTable_data\"]/tr[1]")).click();
        Thread.sleep(5000);
        driver.findElement(By.xpath("//*[@id=\"SearchForm:j_id14\"]")).click();
        Thread.sleep(9000);
    }

    @Then("^click on update button$")
    public void click_on_update_button() throws Throwable {
        //select the row to update
        driver.findElement(By.xpath("//*[@id=\"FlexibleFormEntity:T_Items_data\"]/tr/td[5]")).click();
        // click update button
        driver.findElement(By.xpath("//*[@id=\"FlexibleFormEntity:T_Items:j_id2\"]")).click();
        //switch to iframe
        WebElement AddCategoryIframe = driver.findElement(By.tagName("iframe"));
        driver.switchTo().frame(AddCategoryIframe);
        Thread.sleep(5000);
    }


    @And("^Click the cancel Button$")
    public void Click_the_cancel_button_() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"TestFlexibleForm:CancelFFPopUp\"]")).click();
        Thread.sleep(5000);

        driver.switchTo().defaultContent();
        Thread.sleep(7000);

    }

    //2
    @Then("^Go to Return Filing and  Processing--->Amend Return$")
    public void Go_to_return_filing_and_processing___Amend__return() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"MenuForm:j_idt29\"]/ul/li[11]/a/span[2]")).click();
        driver.findElement(By.xpath("//*[@id=\"MenuForm:j_idt29\"]/ul/li[11]/ul/li[4]")).click();
    }

    @Then("^Select Return Document \"([^\"]*)\" and click on next$")
    public void select_return_document_something_and_click_on_next(String return_document) throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"FormSelection:returnType\"]/div[3]")).click();
        Thread.sleep(2000);
        WebElement dropdown = driver.findElement(By.xpath("//*[@id=\"FormSelection:returnType_items\"]"));
        List<WebElement> entity_list = dropdown.findElements(By.tagName("li"));
        for (WebElement li : entity_list) {
            System.out.print(000);
            if (li.getText().equals(return_document)) {
                li.click();
                System.out.print(0000);
                //to stop the loop
                break;
            }

        }
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"FormSelection:nextReturnButton\"]")).click();
    }

    @Then("^enter period Year,period Number,tin  and click search$")
    public void enter_period_yearperiod_numbertin_and_click_search() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"SearchForm:periodyear\"]")).sendKeys("2019");
        driver.findElement(By.xpath("//*[@id=\"SearchForm:periodnumber\"]")).sendKeys("1");
        driver.findElement(By.xpath("//*[@id=\"SearchForm:tin\"]")).sendKeys("P0021634");
        Thread.sleep(5000);
        driver.findElement(By.xpath("//*[@id=\"SearchForm:j_idt42\"]")).click();
        Thread.sleep(5000);
    }

    //@And("^Click PAYE Return and click continue button$")
    //public void click_paye_return_and_click_Continue_button() throws Throwable {
    //  driver.findElement(By.xpath("//*[@id=\"SearchForm:resultsDataTable_data\"]/tr[1]")).click();
    //  Thread.sleep(7000);
    //  driver.findElement(By.xpath("//*[@id=\"SearchForm:j_id14\"]")).click();
    // Thread.sleep(9000);
    //}


    @Then("^click on Update Button$")
    public void click_On_update_button() throws Throwable {
        //select the row to update
        driver.findElement(By.xpath("//*[@id=\"FlexibleFormEntity:T_Items_data\"]/tr")).click();
        // click update button
        driver.findElement(By.xpath("//*[@id=\"FlexibleFormEntity:T_Items:j_id2\"]")).click();
        //switch to iframe
        WebElement AddCategoryIframe = driver.findElement(By.tagName("iframe"));
        driver.switchTo().frame(AddCategoryIframe);
        Thread.sleep(3000);

    }

    @And("^Enter the TIN and Click search button$")
    public void enter_the_tin_and_click_search_button() throws Throwable {
        //click the searchicon
        driver.findElement(By.xpath("//*[@id=\"TestFlexibleForm:j_id3\"]")).click();
        driver.switchTo().defaultContent();
        Thread.sleep(5000);
        driver.switchTo().frame(1);
        //enter the tin
        driver.findElement(By.xpath("//*[@id=\"SearchForm:tin\"]")).sendKeys("P0018559");
        ///click search button
        driver.findElement(By.xpath("//*[@id=\"SearchForm:j_idt42\"]")).click();

        Thread.sleep(3000);
    }

    @Then("^edit employee particulars and click ok$")
    public void edit_employee_particulars_and_click_ok() throws Throwable {
        //switch to iframe
        WebElement AddCategoryIframe = driver.findElement(By.tagName("iframe"));
        driver.switchTo().frame(AddCategoryIframe);
        Thread.sleep(3000);
        //edit fields
        driver.findElement(By.xpath("//*[@id=\"TestFlexibleForm:Designation\"]")).clear();
        driver.findElement(By.xpath("//*[@id=\"TestFlexibleForm:Designation\"]")).sendKeys("SDE");
        driver.findElement(By.xpath("//*[@id=\"TestFlexibleForm:BasicSalaryAndWages_input\"]")).clear();
        driver.findElement(By.xpath("//*[@id=\"TestFlexibleForm:BasicSalaryAndWages_input\"]")).sendKeys("400000");
        driver.findElement(By.xpath("//*[@id=\"TestFlexibleForm:HousingAllowance_input\"]")).clear();
        driver.findElement(By.xpath("//*[@id=\"TestFlexibleForm:HousingAllowance_input\"]")).sendKeys("40000");
        driver.findElement(By.xpath("//*[@id=\"TestFlexibleForm:Overtime_input\"]")).clear();
        driver.findElement(By.xpath("//*[@id=\"TestFlexibleForm:Overtime_input\"]")).sendKeys("40000");
        driver.findElement(By.xpath("//*[@id=\"TestFlexibleForm:Commission_input\"]")).clear();
        driver.findElement(By.xpath("//*[@id=\"TestFlexibleForm:Commission_input\"]")).sendKeys("40000");
        driver.findElement(By.xpath("//*[@id=\"TestFlexibleForm:Pension_input\"]")).clear();
        driver.findElement(By.xpath("//*[@id=\"TestFlexibleForm:Pension_input\"]")).sendKeys("40000");
        driver.findElement(By.xpath("//*[@id=\"TestFlexibleForm:AnyOtherAllowances_input\"]")).clear();
        driver.findElement(By.xpath("//*[@id=\"TestFlexibleForm:AnyOtherAllowances_input\"]")).sendKeys("40000");
        driver.findElement(By.xpath("//*[@id=\"TestFlexibleForm:LeavePassage_input\"]")).clear();
        driver.findElement(By.xpath("//*[@id=\"TestFlexibleForm:LeavePassage_input\"]")).sendKeys("40000");
        driver.findElement(By.xpath("//*[@id=\"TestFlexibleForm:Gratuity_input\"]")).clear();
        driver.findElement(By.xpath("//*[@id=\"TestFlexibleForm:Gratuity_input\"]")).sendKeys("40000");
        driver.findElement(By.xpath("//*[@id=\"TestFlexibleForm:NoticePay_input\"]")).clear();
        driver.findElement(By.xpath("//*[@id=\"TestFlexibleForm:NoticePay_input\"]")).sendKeys("40000");
        driver.findElement(By.xpath("//*[@id=\"TestFlexibleForm:Bonus_input\"]")).clear();
        driver.findElement(By.xpath("//*[@id=\"TestFlexibleForm:Bonus_input\"]")).sendKeys("90000");
        driver.findElement(By.xpath("//*[@id=\"TestFlexibleForm:LeaveGrant_input\"]")).clear();
        driver.findElement(By.xpath("//*[@id=\"TestFlexibleForm:LeaveGrant_input\"]")).sendKeys("40000");
        driver.findElement(By.xpath("//*[@id=\"TestFlexibleForm:OtherSeverancePay_input\"]")).clear();
        driver.findElement(By.xpath("//*[@id=\"TestFlexibleForm:OtherSeverancePay_input\"]")).sendKeys("40000");
        driver.findElement(By.xpath("//*[@id=\"TestFlexibleForm:SeverancePay_input\"]")).clear();
        driver.findElement(By.xpath("//*[@id=\"TestFlexibleForm:SeverancePay_input\"]")).sendKeys("40000");
        driver.findElement(By.xpath("//*[@id=\"TestFlexibleForm:Stipend_input\"]")).clear();
        driver.findElement(By.xpath("//*[@id=\"TestFlexibleForm:Stipend_input\"]")).sendKeys("40000");
        driver.findElement(By.xpath("//*[@id=\"TestFlexibleForm:AnyOtherIncome_input\"]")).clear();
        driver.findElement(By.xpath("//*[@id=\"TestFlexibleForm:AnyOtherIncome_input\"]")).sendKeys("30000");
        Thread.sleep(8000);
        driver.findElement(By.xpath("//*[@id=\"TestFlexibleForm:Save\"]")).click();
        Thread.sleep(5000);
        driver.switchTo().defaultContent();
        ////*[@id="TestFlexibleForm:BasicSalaryAndWages_input"]
        Thread.sleep(7000);

    }


    @And("^Enter \"([^\"]*)\" for amendment and click submit$")
    public void enter_something_for_amendment_and_click_submit(String reason) throws Throwable {

        // reason for amendment
        driver.findElement(By.xpath("/html/body/div[3]/form/div[2]/div[2]/div[6]/div/div/div[2]/div/div[3]")).click();
        Thread.sleep(2000);
        WebElement dropdown = driver.findElement(By.xpath("//*[@id=\"FlexibleFormEntity:ReasonForAmendment_items\"]"));
        List<WebElement> entity_list = dropdown.findElements(By.tagName("li"));
        for (WebElement li : entity_list) {
            System.out.print(000);
            if (li.getText().equals(reason)) {
                li.click();
                System.out.print(0000);
                //to stop the loop
                break;
            }

        }
        Thread.sleep(2000);
    }

    @And("^Click SUBMIT$")
    public void click_on_Cancel() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"FlexibleFormEntity:save\"]")).click();
    }

    //3
    @Then("^Navigate to The Return Filing and  Processing--->Amend Return$")
    public void NAVIGATE_to_the_return_Filing_and_processing___amend_RETURN() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"MenuForm:j_idt29\"]/ul/li[11]/a/span[2]")).click();
        driver.findElement(By.xpath("//*[@id=\"MenuForm:j_idt29\"]/ul/li[11]/ul/li[4]")).click();
        Thread.sleep(7000);
    }

    @Then("^Select Return Document \"([^\"]*)\" and Click on next button$")
    public void select_return_document_something_and_click_on_next_button(String return_document) throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"FormSelection:returnType\"]/div[3]")).click();
        Thread.sleep(2000);
        WebElement dropdown = driver.findElement(By.xpath("//*[@id=\"FormSelection:returnType_items\"]"));
        List<WebElement> entity_list = dropdown.findElements(By.tagName("li"));
        for (WebElement li : entity_list) {
            System.out.print(000);
            if (li.getText().equals(return_document)) {
                li.click();
                System.out.print(0000);
                //to stop the loop
                break;
            }

        }
        Thread.sleep(3000);
        driver.findElement(By.xpath("//*[@id=\"FormSelection:nextReturnButton\"]")).click();
    }

    @Then("^enter prompted details and click search$")
    public void enter_prompted_details_and_click_search() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"SearchForm:periodyear\"]")).sendKeys("2019");
        driver.findElement(By.xpath("//*[@id=\"SearchForm:periodnumber\"]")).sendKeys("1");
        driver.findElement(By.xpath("//*[@id=\"SearchForm:tin\"]")).sendKeys("P0021634");
        Thread.sleep(5000);
        driver.findElement(By.xpath("//*[@id=\"SearchForm:j_idt42\"]")).click();
        Thread.sleep(5000);
    }

    @And("^SELECT reason_for_amendment \"([^\"]*)\"$")
    public void enter_prompted_data_and_select_reasonforamendment_something(String reason) throws Throwable {
        //data with error
        driver.findElement(By.xpath("//*[@id=\"FlexibleFormEntity:DeclarantName\"]")).clear();
        // reason for amendment
        driver.findElement(By.xpath("/html/body/div[3]/form/div[2]/div[2]/div[6]/div/div/div[2]/div/div[3]")).click();
        Thread.sleep(2000);
        WebElement dropdown = driver.findElement(By.xpath("//*[@id=\"FlexibleFormEntity:ReasonForAmendment_items\"]"));
        List<WebElement> entity_list = dropdown.findElements(By.tagName("li"));
        for (WebElement li : entity_list) {
            System.out.print(000);
            if (li.getText().equals(reason)) {
                li.click();
                System.out.print(0000);
                //to stop the loop
                break;
            }

        }
        Thread.sleep(3000);

    }

    @Then("^click on Submit Button$")
    public void click_on_submit_button() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"FlexibleFormEntity:save\"]")).click();
        Thread.sleep(6000);
        //save with errors
        //driver.findElement(By.xpath("//*[@id=\"FlexibleFormEntity:SAVE_WITH_ERROR\"]")).click();
        //Thread.sleep(8000);
        driver.switchTo().defaultContent();
    }

//4

    @Then("^Navigate to The Return Filing and  Processing>>Amend Return$")
    public void navigate_to_The_return_filing_and_processingamend_return() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"MenuForm:j_idt29\"]/ul/li[11]/a/span[2]")).click();
        driver.findElement(By.xpath("//*[@id=\"MenuForm:j_idt29\"]/ul/li[11]/ul/li[4]")).click();
    }

    @Then("^Select Return document \"([^\"]*)\" and Click on next button$")
    public void select_return_Document_something_and_click_on_next_button(String return_document) throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"FormSelection:returnType\"]/div[3]")).click();
        Thread.sleep(2000);
        WebElement dropdown = driver.findElement(By.xpath("//*[@id=\"FormSelection:returnType_items\"]"));
        List<WebElement> entity_list = dropdown.findElements(By.tagName("li"));
        for (WebElement li : entity_list) {
            System.out.print(000);
            if (li.getText().equals(return_document)) {
                li.click();
                System.out.print(0000);
                //to stop the loop
                break;
            }

        }
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"FormSelection:nextReturnButton\"]")).click();
    }

    @Then("^enter the prompted details and click search$")
    public void enter_the_prompted_details_and_click_search() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"SearchForm:periodyear\"]")).sendKeys("2019");
        driver.findElement(By.xpath("//*[@id=\"SearchForm:periodnumber\"]")).sendKeys("1");
        driver.findElement(By.xpath("//*[@id=\"SearchForm:tin\"]")).sendKeys("P0021634");
        Thread.sleep(5000);
        driver.findElement(By.xpath("//*[@id=\"SearchForm:j_idt42\"]")).click();
        Thread.sleep(5000);
    }

    @And("^enter prompted data and select reason for_Amendment \"([^\"]*)\"$")
    public void enter_prompted_data_and_select_reason_foramendment_something(String reason) throws Throwable {

        //data with error
        driver.findElement(By.xpath("//*[@id=\"FlexibleFormEntity:DeclarantName\"]")).clear();
        // reason for amendment
        driver.findElement(By.xpath("/html/body/div[3]/form/div[2]/div[2]/div[6]/div/div/div[2]/div/div[3]")).click();
        Thread.sleep(2000);
        WebElement dropdown = driver.findElement(By.xpath("//*[@id=\"FlexibleFormEntity:ReasonForAmendment_items\"]"));
        List<WebElement> entity_list = dropdown.findElements(By.tagName("li"));
        for (WebElement li : entity_list) {
            System.out.print(000);
            if (li.getText().equals(reason)) {
                li.click();
                System.out.print(0000);
                //to stop the loop
                break;
            }

        }
        Thread.sleep(2000);
    }

    @Then("^click on the submit Button$")
    public void CLICK_on_the_submit__button_() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"FlexibleFormEntity:save\"]")).click();
        Thread.sleep(8000);
    }

    @Then("^click on save with errors button$")
    public void click_on_save_with_errors_button() throws Throwable {

        //save with errors
        driver.findElement(By.xpath("//*[@id=\"FlexibleFormEntity:SAVE_WITH_ERROR\"]")).click();

        Thread.sleep(8000);
    }


    //5
    @Then("^Navigate to The Return Filing and  Processing>>>Amend Return$")
    public void navigate_to_the_return_filing__and_processingamend_return() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"MenuForm:j_idt29\"]/ul/li[11]/a/span[2]")).click();
        driver.findElement(By.xpath("//*[@id=\"MenuForm:j_idt29\"]/ul/li[11]/ul/li[4]")).click();

    }

    @Then("^Select Return document \"([^\"]*)\" and Click on Next button$")
    public void select_return_document_something_and_click_on___next_button(String return_document) throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"FormSelection:returnType\"]/div[3]")).click();
        Thread.sleep(2000);
        WebElement dropdown = driver.findElement(By.xpath("//*[@id=\"FormSelection:returnType_items\"]"));
        List<WebElement> entity_list = dropdown.findElements(By.tagName("li"));
        for (WebElement li : entity_list) {
            System.out.print(000);
            if (li.getText().equals(return_document)) {
                li.click();
                System.out.print(0000);
                //to stop the loop
                break;
            }

        }
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"FormSelection:nextReturnButton\"]")).click();
    }

    @Then("^enter the prompted Details and click search$")
    public void enter_the_prompted_Details_and_click_search() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"SearchForm:periodyear\"]")).sendKeys("2019");
        driver.findElement(By.xpath("//*[@id=\"SearchForm:periodnumber\"]")).sendKeys("1");
        driver.findElement(By.xpath("//*[@id=\"SearchForm:tin\"]")).sendKeys("P0021634");
        Thread.sleep(5000);
        driver.findElement(By.xpath("//*[@id=\"SearchForm:j_idt42\"]")).click();
        Thread.sleep(5000);
    }

    @And("^enter prompted Data and Select Reason for amendment \"([^\"]*)\"$")
    public void enter_prompted_data_and_select_reason_for_amendment_something(String reason) throws Throwable {
        //data with error
        driver.findElement(By.xpath("//*[@id=\"FlexibleFormEntity:DeclarantName\"]")).clear();
        // reason for amendment
        driver.findElement(By.xpath("/html/body/div[3]/form/div[2]/div[2]/div[6]/div/div/div[2]/div/div[3]")).click();
        Thread.sleep(2000);
        WebElement dropdown = driver.findElement(By.xpath("//*[@id=\"FlexibleFormEntity:ReasonForAmendment_items\"]"));
        List<WebElement> entity_list = dropdown.findElements(By.tagName("li"));
        for (WebElement li : entity_list) {
            System.out.print(000);
            if (li.getText().equals(reason)) {
                li.click();
                System.out.print(0000);
                //to stop the loop
                break;
            }

        }
        Thread.sleep(2000);
    }

    @Then("^click on cancel button$")
    public void Click_on_cancel_button() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"FlexibleFormEntity:Cancel\"]")).click();
        Thread.sleep(6000);
    }

    @Then("^click on submit Button$")
    public void click_on_submit_Button() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"FlexibleFormEntity:save\"]")).click();
        Thread.sleep(6000);
    }


//6

    @Then("^Click Return Filing and  Processing>>>>Amend Return$")
    public void Click_return_filing_and_processingamend_return() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"MenuForm:j_idt29\"]/ul/li[11]/a/span[2]")).click();
        driver.findElement(By.xpath("//*[@id=\"MenuForm:j_idt29\"]/ul/li[11]/ul/li[4]")).click();
    }

    @Then("^Select Return document \"([^\"]*)\" and Click On Next button$")
    public void select_return_document_something_and_click_on_Next_button(String return_document) throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"FormSelection:returnType\"]/div[3]")).click();
        Thread.sleep(2000);
        WebElement dropdown = driver.findElement(By.xpath("//*[@id=\"FormSelection:returnType_items\"]"));
        List<WebElement> entity_list = dropdown.findElements(By.tagName("li"));
        for (WebElement li : entity_list) {
            System.out.print(000);
            if (li.getText().equals(return_document)) {
                li.click();
                System.out.print(0000);
                //to stop the loop
                break;
            }

        }
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"FormSelection:nextReturnButton\"]")).click();
    }

    @Then("^enter the prompted Details and Click Search$")
    public void Enter_the_prompted_details_and_click_search() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"SearchForm:periodyear\"]")).sendKeys("2019");
        driver.findElement(By.xpath("//*[@id=\"SearchForm:periodnumber\"]")).sendKeys("1");
        driver.findElement(By.xpath("//*[@id=\"SearchForm:tin\"]")).sendKeys("P0021634");
        Thread.sleep(5000);
        driver.findElement(By.xpath("//*[@id=\"SearchForm:j_idt42\"]")).click();
        Thread.sleep(5000);
    }

    @And("^Enter Prompted Data and Select Reason FOR Amendment \"([^\"]*)\"$")
    public void enter_prompted_data_and_select_reason_For_amendment_something(String reason) throws Throwable {
        // reason for amendment
        driver.findElement(By.xpath("/html/body/div[3]/form/div[2]/div[2]/div[6]/div/div/div[2]/div/div[3]")).click();
        Thread.sleep(2000);
        WebElement dropdown = driver.findElement(By.xpath("//*[@id=\"FlexibleFormEntity:ReasonForAmendment_items\"]"));
        List<WebElement> entity_list = dropdown.findElements(By.tagName("li"));
        for (WebElement li : entity_list) {
            System.out.print(000);
            if (li.getText().equals(reason)) {
                li.click();
                System.out.print(0000);
                //to stop the loop
                break;
            }

        }
        Thread.sleep(2000);
    }

    @Then("^click on reset Button$")
    public void click_on_reset_button() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"FlexibleFormEntity:reset\"]")).click();
    }

    //......................end Nickson....................................................//

    //......................Bakam start....................................................//
    @Given("^User navigates to the login page$")
    public void user_navigates_to_the_login_page() throws Throwable {

        driver.get(Pro.getProperty("MRA_BackOffice_URL"));
    }

    @When("^Enters the username \"([^\"]*)\" and password \"([^\"]*)\" to login$")
    public void enters_the_username_something_and_password_something_to_login(String strArg1, String strArg2) throws Throwable {
        driver.findElement(By.id("loginForm:username")).sendKeys(strArg1);
        driver.findElement(By.id("loginForm:password")).sendKeys(strArg2);
        driver.findElement(By.id("loginForm:j_idt18")).click();
    }

    @Then("^User should be logged in$")
    public void user_should_be_logged_in() throws Throwable {
        String URL = driver.getCurrentUrl();
        Assert.assertEquals(URL, "http://18.202.88.7:8001/trips-ui/faces/login/Welcome.xhtml" );
    }

    @Then("^User logs out successfully$")
    public void user_logs_out_successfully() throws Throwable {
        driver.findElement(By.id("Logout")).click();
    }

////-----------------------------------Generate Taxpayer Account Structure---------------------------------------------------------------------------------------------------------///

    @Given("^Navigate to Taxpayer Accounting > Taxpayer Account Enquiry$")
    public void navigate_to_taxpayer_accounting_taxpayer_account_enquiry() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"MenuForm:j_idt29\"]/ul/li[2]")).click();
        driver.findElement(By.xpath("//*[@id=\"MenuForm:j_idt29\"]/ul/li[2]/ul/li[1]/a")).click();
        Thread.sleep(3000);
    }

    @When("^When user inputs (.+) and (.+)$")
    public void when_user_inputs_and1(String category, String tin) throws Throwable {
        Actions builder = new Actions(driver);
        WebElement dropDown = driver.findElement(By.id("SearchForm:DTYPE_input"));
        List<WebElement> options = dropDown.findElements(By.tagName("option"));
        for(WebElement item : options ) {
            if(item.equals(category)) {
                builder.moveToElement(item).click();
                builder.perform();
            }
        }
        Thread.sleep(3000);
        driver.findElement(By.id("SearchForm:accountNumber")).sendKeys(tin);

        driver.findElement(By.id("SearchForm:j_idt42")).click();

        Thread.sleep(3000);
    }

    @Then("^Taxpayer Accounting Enquiry screen should  display the Individual Summary(.+)$")
    public void taxpayer_accounting_enquiry_screen_should_display_the_individual_summary(String tin) throws Throwable {
        String TIN = driver.findElement(By.xpath("//*[@id=\"AccountEnquiry:TaxpayerEnquiryTable_data\"]/tr[1]/td[2]")).getText();
        Assert.assertEquals(TIN, tin);

        String CCGT = driver.findElement(By.xpath("//*[@id=\"AccountEnquiry:TaxpayerEnquiryTable_data\"]/tr[2]/td[1]")).getText();
        Assert.assertEquals(CCGT, "Capital Gain Tax(CGT)");

    }

    @When("^user Clicks the Add button on allocation configuration$")
    public void user_clicks_the_add_button_on_allocation_configuration() throws Throwable {
        Thread.sleep(3000);
        WebElement addBtn=driver.findElement(By.id("SearchForm:j_id11"));
        addBtn.click();
    }


////-----------------------------------Maintain Allocation Rules-------------------------------------------------------------------------------------------------------------------///



    @Given("^User navigates to Taxpayer Accounting dropdown$")
    public void user_navigates_to_taxpayer_accounting_dropdown() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"MenuForm:j_idt27\"]/ul/li[2]/a")).click();
    }

    @When("^the User clicks Find Allocation Rules Configuration$")
    public void the_user_clicks_find_allocation_rules_configuration() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"MenuForm:j_idt27\"]/ul/li[2]/ul/li[4]/a")).click();

        Thread.sleep(5000);
    }

    @Then("^User is navigated to Allocation Rules Configuration page$")
    public void user_is_navigated_to_allocation_rules_configuration_page() throws Throwable {
        String URL = driver.getCurrentUrl();
        Assert.assertEquals(URL, "http://18.202.88.7:8001/trips-ui/faces/core/GenericSearch.xhtml" );
    }

    @And("^configuration Field details are displayed$")
    public void configuration_field_details_are_displayed() throws Throwable {
        Assert.assertTrue(driver.findElement(By.id("SearchForm:ConfigurationReference")).isEnabled());
        Assert.assertTrue(driver.findElement(By.id("SearchForm:expiryDate")).isEnabled());
        Assert.assertTrue(driver.findElement(By.id("SearchForm:effectiveDate")).isEnabled());
    }

    @When("^User then clicks Add button$")
    public void user_then_clicks_add_button() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"SearchForm:j_id11\"]")).click();

        Thread.sleep(2000);
    }

    @Then("^Credit Allocation tab displayed$")
    public void credit_allocation_tab_displayed() throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver,30);
        String title = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"AllocationConfiguration:AllocationLabel\"]"))).getText();
        Assert.assertEquals(title, "Allocation Configuration");

        //Get app current date
        String effectiveDate = driver.findElement(By.id("AllocationConfiguration:EffectiveDate_input")).getAttribute("value");

        //Get current date from method
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String formattedDate= formatter.format(date);

        //Assert dates are same
        Assert.assertEquals(effectiveDate, formattedDate);

    }

    @When("^user clicks on add under credit allocation$")
    public void user_clicks_on_add_under_credit_allocation() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"AllocationConfiguration:CreditAllocationAccordion:creditAllocationTableHandler:AddCreditAllocation\"]")).click();

        Thread.sleep(3000);
    }

    @Then("^NEW Allocation Method screen should be displayed$")
    public void new_allocation_method_screen_should_be_displayed() throws Throwable {
        WebElement frame = driver.findElement(By.tagName("iframe"));

        //Switch to iframe to allow interaction with modal
        driver.switchTo().frame(frame);
        Thread.sleep(3000);
        String header = driver.findElement(By.id("AllocationMethod:AllocationMethodHeader")).getText();
        Assert.assertEquals(header, "NEW Allocation Method");
        Thread.sleep(3000);
    }

    @When("^User selects \"([^\"]*)\" priority \"([^\"]*)\" and check oldest first$")
    public void user_selects_something_priority_something_and_check_oldest_first1(String strArg1, String strArg2) throws Throwable {


//  	driver.switchTo().frame(driver.findElement(By.tagName("iframe")));

        String creditAllocation = driver.findElement(By.id("AllocationMethod:AllocationType_label")).getText();
        Assert.assertEquals(creditAllocation, "Credit Allocation");

        //clicks the dropdown
        driver.findElement(By.xpath("//*[@id=\"AllocationMethod:AllocationMethodValue\"]/div[3]")).click();

        // selects the value you want
        String valueXpath = "//li[@data-label='"+strArg1+"']";
        driver.findElement(By.xpath(valueXpath)).click();

        //Enter priority
        WebElement checked = driver.findElement(By.id("AllocationMethod:Priority"));
        checked.clear();
        checked.sendKeys(strArg2);

        WebElement isChecked = driver.findElement(By.xpath("//*[@id=\"AllocationMethod:OldestFirst\"]/div[2]/span"));

        if (isChecked.isSelected()) {
            isChecked.click();
        }

        driver.findElement(By.id("AllocationMethod:ALMOk")).click();

        Thread.sleep(3000);
    }

    @Then("^Record is added successfully$")
    public void record_is_added_successfully() throws Throwable {
        Thread.sleep(4000);
        String success=driver.findElement(By.className("ui-messages-info-summary")).getText();
        Assert.assertEquals(success, "Record Added");
    }

    @And("^Allocation Rule has been added$")
    public void allocation_rule_has_been_added() throws Throwable {
        String allocationMethod = driver.findElement(By.xpath("//span[text()='Allocation Method']")).getText();
        Assert.assertEquals(allocationMethod, "Allocation Method");

        String interest = driver.findElement(By.xpath("//td[text()='INTEREST']")).getText();
        Assert.assertEquals(interest, "INTEREST");
    }

    @And("^Allocation rule saved$")
    public void input_effective_date_and_todays_date() throws Throwable {
        WebElement dateField = driver.findElement(By.id("AllocationConfiguration:EffectiveDate_input"));
        WebElement expiryField = driver.findElement(By.id("AllocationConfiguration:ExpiryDate_input"));

        // TODO negative tests for date
        //Check if by default current date in
        String today = BaseClass.randomDate();

        //clear and enter current date
        dateField.clear();
        dateField.sendKeys(today);

        expiryField.clear();
        expiryField.sendKeys(today);

        driver.findElement(By.id("AllocationConfiguration:SaveAllocConfig")).click();
        Thread.sleep(5000);
        String success=driver.findElement(By.xpath("//span[contains(text(),'Allocation Rule saved successfully')]")).getText();
        Assert.assertEquals(success, "Allocation Rule saved successfully.");

    }

    @When("^User inputs % in configuration refrence and clicks search$")
    public void user_inputs_in_the_search_field() throws Throwable {
        driver.findElement(By.id("SearchForm:ConfigurationReference")).sendKeys("%");
        driver.findElement(By.id("SearchForm:j_idt40")).click();
    }

    @Then("^Grid Table populated with search result of allocation rules$")
    public void grid_table_populated_with_search_result_of_allocation_rules() throws Throwable {
        Boolean columnExists = driver.findElements(By.xpath("//*[@id=\"SearchForm:resultsDataTable_data\"]/tr[1]")).isEmpty();
        Assert.assertFalse(columnExists);
        Thread.sleep(3000);
    }

    @When("^user selects configuration reference and clicks update$")
    public void user_selects_allocation_rules_and_clicks_update() throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        WebElement row = driver.findElement(By.xpath("//td[@role='gridcell']"));
        row.click();
        driver.findElement(By.id("SearchForm:j_id12")).click();

        Thread.sleep(3000);
    }

    @Then("^Allocation configuration screen displayed$")
    public void allocation_configuration_screen_displayed() throws Throwable {
        Thread.sleep(3000);
        String allocationConfiguration = driver.findElement(By.xpath("//label[text()='Allocation Configuration']")).getText();
        Assert.assertEquals(allocationConfiguration, "Allocation Configuration");

        String URL = driver.getCurrentUrl();
        Assert.assertEquals(URL, "http://18.202.88.7:8001/trips-ui/faces/account/config/AllocationConfig.xhtml" );

        Thread.sleep(5000);
    }

    @When("^User selects an allocation rule and click update$")
    public void user_selects_an_allocation_rule_and_click_update() throws Throwable {


        driver.findElement(By.xpath("//*[@id=\"AllocationConfiguration:CreditAllocationAccordion:creditAllocationTableHandler_data\"]/tr")).click();
        driver.findElement(By.xpath("//*[@id=\"AllocationConfiguration:CreditAllocationAccordion:creditAllocationTableHandler:EditCreditAllocation\"]")).click();
        Thread.sleep(5000);

        WebElement frame = driver.findElement(By.tagName("iframe"));
        driver.switchTo().frame(frame);

    }

    @Then("^Allocation Rule should be Modified$")
    public void allocation_rule_should_be_modified() throws Throwable {
        String success=driver.findElement(By.className("ui-messages-info-summary")).getText();
        Assert.assertEquals(success, "Record Updated");
    }

    @And("^Allocation Rule successfully modified$")
    public void allocation_rule_successfully_modified() throws Throwable {
        String success=driver.findElement(By.className("ui-messages-info-summary")).getText();
        Assert.assertEquals(success, "Record Updated");
    }

    @And("^effective date changed$")
    public void effective_date_changed() throws Throwable {
        WebElement dateField = driver.findElement(By.id("AllocationConfiguration:EffectiveDate_input"));
        WebElement expiryField = driver.findElement(By.id("AllocationConfiguration:ExpiryDate_input"));

        // TODO negative tests for date
        //Check if by default current date in
        LocalDate today = LocalDate.now();
        LocalDate tomorrow = today.plus(1, ChronoUnit.DAYS);
//    	String formattedDate = tomorrow.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT));

        String formattedDate = randomDate();
        //clear and enter current date
        dateField.clear();
        dateField.sendKeys(formattedDate);

        expiryField.clear();
        expiryField.sendKeys(formattedDate);

        driver.findElement(By.id("AllocationConfiguration:SaveAllocConfig")).click();
        Thread.sleep(3000);

        String success=driver.findElement(By.className("ui-messages-info-detail")).getText();
        Assert.assertEquals(success, "Allocation Rule saved successfully.");
    }

    @When("^user selects configuration reference and clicks view$")
    public void user_selects_configuration_reference_and_clicks_view() throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        WebElement row = driver.findElement(By.xpath("//td[@role='gridcell']"));
        row.click();
        driver.findElement(By.id("SearchForm:j_id13")).click();

        Thread.sleep(3000);
    }

    @When("^User clicks on cancel button$")
    public void user_clicks_on_cancel_button() throws Throwable {
        driver.findElement(By.id("AllocationMethod:ALMCancel")).click();
        Thread.sleep(4000);
    }

    @Then("^VIEW Allocation Method screen should be displayed$")
    public void view_allocation_method_screen_should_be_displayed() throws Throwable {
        Thread.sleep(4000);
        WebElement frame = driver.findElement(By.tagName("iframe"));
//        Assert.assertEquals(true, frame.isDisplayed());

        //Switch to iframe to allow interaction with modal
        driver.switchTo().frame(frame);
        String header = driver.findElement(By.id("AllocationMethod:AllocationMethodHeader")).getText();
        Assert.assertEquals(header, "VIEW Allocation Method");
        Thread.sleep(3000);
    }

    @Then("^System should display Allocation Configuration screen$")
    public void system_should_display_allocation_configuration_screen() throws Throwable {
        String header = driver.findElement(By.id("AllocationConfiguration:AllocationLabel")).getText();
        Assert.assertEquals(header, "Allocation Configuration");
        Thread.sleep(3000);
    }

    @And("^User selects an allocation rule and click view$")
    public void user_selects_an_allocation_rule_and_click_view() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"AllocationConfiguration:CreditAllocationAccordion:creditAllocationTableHandler_data\"]/tr")).click();
        driver.findElement(By.name("AllocationConfiguration:CreditAllocationAccordion:creditAllocationTableHandler:ViewCreditAllocation")).click();
        Thread.sleep(5000);
    }

    @When("^User selects an allocation rule and click remove$")
    public void user_selects_an_allocation_rule_and_click_remove() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"AllocationConfiguration:CreditAllocationAccordion:creditAllocationTableHandler_data\"]/tr")).click();
        driver.findElement(By.name("AllocationConfiguration:CreditAllocationAccordion:creditAllocationTableHandler:DeleteCreditAllocation")).click();
        Thread.sleep(5000);
        //Switch to iframe to allow interaction with modal
//        WebElement frame = driver.findElement(By.tagName("iframe"));
//        driver.switchTo().frame(frame);
        Thread.sleep(5000);
    }

    @Then("^User clicks yes$")
    public void user_clicks_yes() throws Throwable {
        driver.findElement(By.id("AllocationConfiguration:confirm")).click();

        Thread.sleep(3000);
    }

    @Then("^Record Deleted successfully$")
    public void record_deleted_successfully() throws Throwable {
        Thread.sleep(4000);
        String success=driver.findElement(By.className("ui-messages-info-summary")).getText();
        Assert.assertEquals(success, "Record Deleted");
    }

    @When("^user clicks on add under allocation allocation$")
    public void user_clicks_on_add_under_allocation_allocation() throws Throwable {
        driver.findElement(By.id("AllocationConfiguration:CreditAllocationAccordion:creditAllocationTableHandler:AddCreditAllocation")).click();

        Thread.sleep(3000);
    }

    @Then("^A valid error message should be displayed$")
    public void a_valid_error_message_should_be_displayed() throws Throwable {
        String priorityError=driver.findElement(By.xpath("//*[@id=\"AllocationMethod:growl\"]/div/ul/li[1]/span")).getText();
        Assert.assertEquals(priorityError, "This priority is already used in the table. Please select another priority.");

        String methodError=driver.findElement(By.xpath("//*[@id=\"AllocationMethod:growl\"]/div/ul/li[1]/span")).getText();
        Assert.assertEquals(methodError, "This priority is already used in the table. Please select another priority.");
    }

////------------------------------------------------Find Transaction screen to Manage Taxpayer Account Adjustments----------------------------------------------------------------------------//

    @Given("^user navigates to Taxpayer Accounting$")
    public void user_navigates_to_taxpayer_accounting_taxpayer_account_adjustment() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"MenuForm:j_idt27\"]/ul/li[2]/a")).click();

    }

    @When("^click Taxpayer Account Adjustment$")
    public void click_taxpayer_account_adjustment() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"MenuForm:j_idt27\"]/ul/li[2]/ul/li[6]/a")).click();
        Thread.sleep(2000);
    }

    @Then("^Manage Taxpayer Account Adjustment, screen should be displayed$")
    public void manage_taxpayer_account_adjustment_screen_should_be_displayed() throws Throwable {
        driver.findElement(By.id("SearchForm:tin")).isEnabled();
        driver.findElement(By.id("SearchForm:periodNo")).isEnabled();
        driver.findElement(By.id("SearchForm:periodYear")).isEnabled();
        driver.findElement(By.id("SearchForm:j_id14")).isEnabled();
        driver.findElement(By.id("SearchForm:j_id15")).isEnabled();
        driver.findElement(By.id("SearchForm:j_idt40")).isEnabled();
        driver.findElement(By.id("SearchForm:Cancel")).isEnabled();
    }

    @And("^user Clicks on Add button$")
    public void user_clicks_on_add_button() throws Throwable {
        driver.findElement(By.id("SearchForm:j_id14")).click();

        Thread.sleep(2000);
    }

    @Then("^Taxpayer Account Adjustment screen should be displayed$")
    public void taxpayer_account_adjustment_screen_should_be_displayed() throws Throwable {
        driver.findElement(By.id("TaxpayerAccountAdjustment:TIN")).isEnabled();
        driver.findElement(By.id("TaxpayerAccountAdjustment:FindTin")).isEnabled();
        driver.findElement(By.id("TaxpayerAccountAdjustment:EntityName")).isEnabled();
        driver.findElement(By.id("TaxpayerAccountAdjustment:Description")).isEnabled();
        driver.findElement(By.id("TaxpayerAccountAdjustment:Amount_input")).isEnabled();
        driver.findElement(By.id("TaxpayerAccountAdjustment:RevenueLedgerCode")).isEnabled();
        driver.findElement(By.id("TaxpayerAccountAdjustment:Amount_input")).isEnabled();
    }

    @When("^User Clicks on Find Button$")
    public void user_clicks_on_find_button() throws Throwable {
        driver.findElement(By.id("TaxpayerAccountAdjustment:FindTin")).click();

        Thread.sleep(2000);
    }

    @Then("^Taxpayer Account Adjustment Details Search Screen should be displayed$")
    public void taxpayer_account_adjustment_details_search_screen_should_be_displayed() throws Throwable {
        //Switch to iframe to allow interaction with modal
        WebElement frame = driver.findElement(By.tagName("iframe"));
        driver.switchTo().frame(frame);
        Thread.sleep(2000);
        driver.findElement(By.id("SearchForm:tin")).isEnabled();
        driver.findElement(By.id("SearchForm:periodNo")).isEnabled();
        driver.findElement(By.id("SearchForm:periodYear")).isEnabled();
        driver.findElement(By.id("SearchForm:j_id14")).isEnabled();
        driver.findElement(By.id("SearchForm:j_idt21")).isEnabled();

        String TIN = driver.findElement(By.id("SearchForm:resultsDataTable:j_id2")).getText();
        Assert.assertEquals(TIN," TIN" );

    }

    ///----------------------------------------------verify the process of Creating Adjustments by Revenue Officer and  Approving the adjustment by the Revenue supervisor---------------------------------------------------------------------------///

    @And("^enter Tin number (.+) and (.+) then click search$")
    public void enter_Tin_number_and_click_search(String TIN, String AccountType) throws Throwable {
        Thread.sleep(4000);
        driver.findElement(By.id("SearchForm:tin")).sendKeys(TIN);
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"SearchForm:taxType\"]/div[3]")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//li[contains(text(),'" + AccountType + "')]")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("SearchForm:j_idt21")).click();
        Thread.sleep(2000);

//        WebDriverWait wait = new WebDriverWait(driver,30);
//        WebElement taxType = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"SearchForm:resultsDataTable_data\"]/tr[1]/td[5]")));
//        //WebElement taxType = driver.findElement(By.xpath("//*[@id=\"SearchForm:resultsDataTable_data\"]/tr[1]/td[5]"));
//
//
//        if(taxType.isDisplayed()) {
//            taxType.click();
//        }
//        Thread.sleep(2000);
//
//        driver.findElement(By.id("SearchForm:j_id14")).click();
//
//        Thread.sleep(3000);

    }

    @And("^enter Tin number (.+) and click search on Taxpayer Account Adjustment$")
    public void enter_Tin_number_and_click_search_on_Taxpayer_Account_Adjustment(String TIN) throws Throwable {

        driver.findElement(By.id("SearchForm:tin")).sendKeys(TIN);

        driver.findElement(By.id("SearchForm:j_idt40")).click();
        Thread.sleep(4000);

    }

    @Then("^Click table column \"([^\"]*)\"$")
    public void click_table_column(String column) throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(column))).click();

    }

    @Then("^click on view to check match \"([^\"]*)\"$")
    public void clickOnViewToCheckMatch(String column) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement percentageColumn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(column)));
        String percentage = percentageColumn.getText();
        percentageColumn.click();
        driver.findElement(By.id("MaintainTreasuryTarget:ViewCreditAllocation")).click();

        WebElement frame = wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("iframe")));
        driver.switchTo().frame(frame);
        String percentageView = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("treasuryTargetAllotment:TreasuryPercentage"))).getAttribute("value");
        Assert.assertEquals(percentage,percentageView);
    }

    @Then("^select charge type (.+)$")
    public void select_charge_type(String chargetype) throws Throwable {
        Thread.sleep(5000);
        WebDriverWait wait = new WebDriverWait(driver,100);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"TaxpayerAccountAdjustment:ChargeType\"]/div[3]"))).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//li[contains(text(),'"+chargetype+"')]")).click();

    }

    @Then("^select adjustment type (.+)$")
    public void select_adjustment_type(String adjtype) throws Throwable {
        WebElement adjDropdown=driver.findElement(By.id("TaxpayerAccountAdjustment:AdjustmentType_label"));
        adjDropdown.click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//li[contains(text(),'"+adjtype+"')]")).click();


    }

    @Then("^give reason value (.+)$")
    public void give_reason_value(String reason) throws Throwable {
        Thread.sleep(4000);
        WebElement reasonDropdown=driver.findElement(By.id("TaxpayerAccountAdjustment:Reason_label"));
        reasonDropdown.click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//li[contains(text(),'"+reason+"')]")).click();
    }

    @Then("^enter revenue ledger code (.+) and amount (.+)$")
    public void enter_revenue_ledger_code_and_amount(String code, String amount) throws Throwable {
        driver.findElement(By.id(Pro.getProperty("Taxpayer_Account_Adj_Revenue_LedgerCode_ID"))).sendKeys(code);
        driver.findElement(By.id(Pro.getProperty("Taxpayer_Account_Adj_Amount_ID"))).sendKeys(amount);
    }

    @And("^click on submit button$")
    public void click_submit_button() throws Throwable {
        Thread.sleep(1000);
        driver.findElement(By.xpath(Pro.getProperty("Taxpayer_Account_Adj_Submit_XPATH"))).click();

    }

    @Then("^Credit reference number will generate (.+)$")
    public void credit_reference_number_will_generate(String refno) throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver,100);
        String text  = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'"+refno+"')]"))).getText();
        System.out.println(text);
        System.out.println("substring is "+ text.substring(42));
        String A_BackOffice_ARN=text.substring(42);

        sharedatastep.A_CRMARN=A_BackOffice_ARN;
        // System.out.println("Actual ARN to be used in CRM is "+"*"+text.substring(42));


        System.out.println(sharedatastep.A_CRMARN);
        System.out.println("Actual ARN to be used in CRM is " +sharedatastep.A_CRMARN);
        if(text.contains("ACAD"))
        {
            System.out.println(text);
            System.out.println("Text Verified and passed");
        }
        else
        {
            System.out.println("Text Not Verfied and failed");
            System.exit(1);
        }
        Thread.sleep(20000);
    }

    //-----------------------------Individual_Taxpayer_Approval Scenario------CRM----------------------------------------------------------------------------------------------------------------------------//


    @Given("^Open CRM URL for Accounting Module$")
    public void open_CRM_URL_for_Accounting_Module() throws Throwable {
        driver.get(Pro.getProperty("CRM_URL"));
    }

    @When("^Close Popup Window$")
    public void close_Popup_Window() throws Throwable {

        driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
        WebElement  specificframe= (driver.findElement(By.id(Pro.getProperty("CRM_ExploreCrmWindow_Frame__ID"))));
        driver.switchTo().frame(specificframe);
        WebDriverWait CloseWindow=new WebDriverWait(driver,60);
        CloseWindow.until(ExpectedConditions.elementToBeClickable(By.id(Pro.getProperty("CRM_ExploreCrmWindow_Frame_Close_ID")))).click();
    }

    @And("^Click on Case management dropdown$")
    public void click_on_case_management_dropdown() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"TabCS\"]/a/span")).click();
    }

    @And("^click on Accounting application$")
    public void click_on_accounting_application() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"tbg_accountingapplication\"]/span[2]")).click();
    }


    @When("^enters \"([^\"]*)\" in search results$")
    public void enters_something_in_search_results(String strArg1) throws Throwable {
        WebDriverWait wait=new WebDriverWait(driver, 30);
        WebElement search = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("crmGrid_findCriteria")));
        search.sendKeys(strArg1);
        search.sendKeys(Keys.ENTER);

        driver.switchTo().defaultContent();
        Thread.sleep(5000);

    }

    @When("^enters reference number in search results$")
    public void enters_in_search_results() throws Throwable {
        WebDriverWait wait=new WebDriverWait(driver, 20);
        WebElement search = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("crmGrid_findCriteria")));
        search.sendKeys(sharedatastep.A_CRMARN);
//    	search.sendKeys("ACAD/000002717/2021");
        search.sendKeys(Keys.ENTER);

        Thread.sleep(2000);
    }


    @Then("^Click selected Reference Number$")
    public void click_selected_Reference_Number() throws Throwable {
        WebDriverWait wait=new WebDriverWait(driver, 30);
        WebElement elementLocator = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Pro.getProperty("CaseManagement_Queue_Select_ReffNo_XPATH"))));

        Actions actions = new Actions(driver);
        actions.doubleClick(elementLocator).perform();

        driver.switchTo().defaultContent();
        Thread.sleep(9000);
    }

    @And("^clicks Approve from the dropdown$")
    public void clicks_Approve_from_the_dropdown() throws Throwable {
        driver.switchTo().frame("contentIFrame1");
        Thread.sleep(9000);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        Actions action=new Actions(driver);
        WebElement Outcome=driver.findElement(By.id(Pro.getProperty("Taxpayer_Accounting_Approval_Outcome_ID")));
        WebElement hasLoaded= driver.findElement(By.id("header_process_tbg_approvaloutcome_lock"));

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        Thread.sleep(7000);
        if(hasLoaded.isDisplayed()) {
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            Thread.sleep(5000);
        }else {
            action.doubleClick(Outcome).build().perform();
            Outcome.click();
            action.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();
        }

    }


    @And("^click save on accounting$")
    public void click_save_on_accounting() throws Throwable {
        driver.switchTo().defaultContent();
        Thread.sleep(2000);
        driver.findElement(By.id("tbg_accountingapplication|NoRelationship|Form|Mscrm.Form.tbg_accountingapplication.Save")).click();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

    }

    @And("^click save on exemption$")
    public void click_save_on_exemption() throws Throwable {
        driver.switchTo().defaultContent();
        Thread.sleep(2000);
        driver.findElement(By.id("tbg_exemptionapplication|NoRelationship|Form|Mscrm.Form.tbg_exemptionapplication.Save")).click();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

    }

//    @Then("^Enter Outcome Notes (.+)$")
//    public void enter_outcome_notes(String Notes) throws Throwable {
//        Thread.sleep(3000);
//        Actions action1 = new Actions(driver);
//        WebElement element1=driver.findElement(By.id((Pro.getProperty("Individual_NextStage_RefNum_Reject_OutComeNotes_ID"))));
//        action1.sendKeys(element1, Notes).build().perform();
//        Thread.sleep(5000);
//    }

    @Then("^Enter Outcome Reason for Taxpayer accounting$")
    public void enter_Outcome_Reason_for_Taxpayer_accounting() throws Throwable {
        WebElement specificframe=driver.findElement(By.xpath("//*[@id=\"WebResource_AccountingRejectionDataWebResource\"]"));
        driver.switchTo().frame(specificframe);
        WebElement dropDown = driver.findElement(By.xpath("//*[@id=\"viewoption\"]"));
        dropDown.click();

        driver.findElement(By.xpath("//option[@value='3']")).click();

    }


    @Then("^Pick the selected Reference number$")
    public void pick_the_selected_Reference_number() throws Throwable {
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Actions action=new Actions(driver);
        WebElement option=driver.findElement(By.xpath(Pro.getProperty("CaseManagement_OpenOptions_Link_XPATH")));
        action.doubleClick(option).build().perform();
        option.click();
        Thread.sleep(3000);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.findElement(By.xpath(Pro.getProperty("CaseManagement_Queue_PICK_Link_XPATH"))).click();
        Thread.sleep(5000);

    }

    @Then("^Click on appeard Reference number$")
    public void click_on_appeard_Reference_number() throws Throwable {

        Actions action=new Actions(driver);
        WebElement SelectRef=driver.findElement(By.className(Pro.getProperty("appeared_Reference_number_CLASSNAME")));
        action.moveToElement(SelectRef).doubleClick().build().perform();
        Thread.sleep(2000);
        driver.switchTo().defaultContent();
        Thread.sleep(5000);

    }

    @Then("^Click on Save button$")
    public void click_on_Save_button() throws Throwable {

        driver.findElement(By.xpath(Pro.getProperty("Maintain_period_Generation_Conf_Save_XPATH"))).click();

    }


    @When("^User Click Account adjustment application$")
    public void user_click_account_adjustment_application() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"gridBodyTable\"]/tbody/tr/td[2]")).click();
    }

    @Then("^Manage Taxpayer Account Adjustment screen displayed$")
    public void manage_taxpayer_account_adjustment_screen_displayed() throws Throwable {
        String Header = driver.findElement(By.id("SearchForm:directorHeader")).getText();
        Assert.assertEquals(Header, "TRIPS - Manage Taxpayer Account Adjustment");

        Boolean Table = driver.findElement(By.id("SearchForm:resultsDataTable_data")).isDisplayed();
        Assert.assertEquals(Table,true);
    }

    @When("^User clicks on an adjustment$")
    public void user_clicks_on_an_adjustment() throws Throwable {
        Thread.sleep(3000);
        driver.findElement(By.xpath("//*[@id=\"SearchForm:resultsDataTable_data\"]/tr[1]")).click();

        driver.findElement(By.id("SearchForm:j_id15")).click();
    }

    @Then("^User adjustment details displayed$")
    public void user_adjustment_details_displayed() throws Throwable {
        Thread.sleep(4000);
        Boolean TIN = driver.findElement(By.id("TaxpayerAccountAdjustment:TIN")).isEnabled();
        Assert.assertEquals(TIN,false);

        Boolean NAME = driver.findElement(By.id("TaxpayerAccountAdjustment:EntityName")).isEnabled();
        Assert.assertEquals(NAME,false);

        Boolean adjustmentDate = driver.findElement(By.id("TaxpayerAccountAdjustment:AdjustmentDate_input")).isEnabled();
        Assert.assertEquals(adjustmentDate,false);


        Boolean revenueLedger = driver.findElement(By.id("TaxpayerAccountAdjustment:RevenueLedgerCode")).isEnabled();
        Assert.assertEquals(revenueLedger,false);

        Boolean amount = driver.findElement(By.id("TaxpayerAccountAdjustment:Amount_input")).isEnabled();
        Assert.assertEquals(amount,false);

    }

    @Given("^user enters no data and Clicks on Submit button$")
    public void user_enters_no_data_and_clicks_on_submit_button() throws Throwable {
        Thread.sleep(2000);
        driver.findElement(By.id("TaxpayerAccountAdjustment:SaveTAA")).click();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Then("^Error Message should be displayed$")
    public void error_message_should_be_displayed() throws Throwable {
        String Error1 = driver.findElement(By.xpath("//*[@id=\"TaxpayerAccountAdjustment:growl\"]/div/ul/li[1]/span")).getText();
        Assert.assertEquals(Error1,"Adjustment Type: Validation Error: Value is required.");

        String Error2 = driver.findElement(By.xpath("//*[@id=\"TaxpayerAccountAdjustment:growl\"]/div/ul/li[2]/span")).getText();
        Assert.assertEquals(Error2,"Revenue Ledger Code: Validation Error: Value is required.");

        String Error3 = driver.findElement(By.xpath("//*[@id=\"TaxpayerAccountAdjustment:growl\"]/div/ul/li[3]/span")).getText();
        Assert.assertEquals(Error3,"Reason: Validation Error: Value is required.");

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @When("^User inputs invalid data$")
    public void user_inputs_invalid_data(DataTable table) throws Throwable {

        List<List<String>> data =table.asLists();

        List<WebElement> list = driver.findElements(By.id("TaxpayerAccountAdjustment:ChargeType_input"));
        for(WebElement option : list)
        {
            String text2= option.getText();
            if(text2.equalsIgnoreCase(data.get(0).get(1)))
            {
                Actions builder = new Actions(driver);
                builder.moveToElement(option).click();
                builder.perform();
            }
        }
        driver.findElement(By.id("TaxpayerAccountAdjustment:TransactionReference")).sendKeys(data.get(1).get(1));

        WebElement adjDate = driver.findElement(By.id("TaxpayerAccountAdjustment:AdjustmentDate_input"));
        adjDate.clear();
        adjDate.sendKeys(BaseClass.randomDate());

        //Adj Type
        List<WebElement> typelist = driver.findElements(By.id("TaxpayerAccountAdjustment:AdjustmentType_input"));
        for(WebElement option : typelist)
        {
            String text2= option.getText();
            System.out.print(text2);
            if(text2.equalsIgnoreCase(data.get(2).get(1)))
            {
                Actions builder = new Actions(driver);
                builder.moveToElement(option).click();
                builder.perform();
            }
        }
        //description
        driver.findElement(By.id("TaxpayerAccountAdjustment:Description")).sendKeys(data.get(3).get(1));

        //Click Submit
        driver.findElement(By.id("TaxpayerAccountAdjustment:SaveTAA")).click();

    }

    @Then("Enter transaction date")
    public void enterTransactionDate() throws Throwable{
        Thread.sleep(1000);
        Actions action = new Actions(driver);
        driver.findElement(By.id("frmReportDetails:TRANSACTION_DATE_input")).click();
        driver.findElement(By.id("frmReportDetails:TRANSACTION_DATE_input")).sendKeys(Keys.ENTER);
        //action.sendKeys(Keys.TAB);
        Thread.sleep(1000);
    }

    @Then("Verify fields in Treasury target allotment screen")
    public void verifyFieldsInTreasuryTargetAllotmentScreen() {
         WebDriverWait wait = new WebDriverWait(driver,30);
         wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("MaintainTreasuryTarget:AddTreasuryTarget"))).isDisplayed();
         driver.findElement(By.id("MaintainTreasuryTarget:EditTreasuryTarget")).isDisplayed();
         driver.findElement(By.id("MaintainTreasuryTarget:ViewCreditAllocation")).isDisplayed();
         driver.findElement(By.id("MaintainTreasuryTarget:Cancel")).isDisplayed();
    }
}