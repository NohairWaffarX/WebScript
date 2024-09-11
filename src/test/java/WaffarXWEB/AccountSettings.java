package WaffarXWEB;
import com.shaft.driver.SHAFT;
import org.openqa.selenium.By;
import org.testng.annotations.*;
import static org.openqa.selenium.Keys.ENTER;

public class AccountSettings
{
    SHAFT.GUI.WebDriver driver;

    @BeforeMethod
    public void setupBrowser()
    {
        driver = new SHAFT.GUI.WebDriver(); // to open browser
        driver.browser().navigateToURL("https://www.waffarx.com/en-eg"); // to navigate to URL
    }

    private void OpenAccountSettings_Page()
    {
        By Register_Button = By.xpath("//*[@id='heatmapArea']/main/div[2]/div[1]/button");
        driver.element().waitToBeReady(Register_Button); // Wait for the button to be clickable
        driver.element().clickUsingJavascript(Register_Button); // To force click on this element

        By AlreadyMember_Button = By.xpath("//*[@id='newSignUp']/div/div/div/div[4]/a") ;
        driver.element().clickUsingJavascript(AlreadyMember_Button); // To force click on this element

        By Email = By.id("LoginEmail");
        driver.element().type(Email, "gnohair@gmail.com");

        By Password = By.id("LoginPassword");
        driver.element().type(Password, "Ng555555");

        By SignIN_Button = By.xpath("//*[@id=\"Login\"]/div[4]/input");
        driver.element().keyPress(SignIN_Button, ENTER);

        By userinfo = By.xpath("//*[@id=\"fixedHeader\"]/div[3]/div/div[2]/div[3]/div/i") ;
        driver.element().hover(userinfo);

        By AccountSettings_choice= By.linkText("Account Settings") ;
        driver.element().click(AccountSettings_choice);
    }

    @Test(priority = 1)
    public void UpdateName_Check_that_ErrorAppear_whenFields_AreEmpty()
    {
        OpenAccountSettings_Page() ;

        By Update_name= By.linkText("Update Name") ;
        driver.element().click(Update_name);

        By submitButton = By.xpath("//*[@id=\"SettingsResetNamesForm\"]/input[4]");
        driver.element().click(submitButton);

        By FirstName_Error= By.id("UserFirstNames-error") ;
        driver.element().verifyThat(FirstName_Error).text().isEqualTo("This field is required.").perform();

        By SecondName_Error= By.id("UserLastNames-error") ;
        driver.element().verifyThat(SecondName_Error).text().isEqualTo("This field is required.").perform();
    }

    @Test(priority = 2)
    public void UpdateName_Check_that_ErrorAppear_whenInsert_SymbolsAndSymbols_inNameFields()
    {
        OpenAccountSettings_Page() ;

        By Update_name= By.linkText("Update Name") ;
        driver.element().click(Update_name);

        By FirstName = By.id("UserFirstNames");
        driver.element().type(FirstName, "@#@#2333");

        By LastName = By.id("UserLastNames");
        driver.element().type(LastName, "@#@#2333");

        By submitButton = By.xpath("//*[@id=\"SettingsResetNamesForm\"]/input[4]");
        driver.element().click(submitButton);

        By FirstName_Error= By.id("UserFirstNames-error") ;
        driver.element().verifyThat(FirstName_Error).text().isEqualTo("Sorry, Numbers and special characters are not allowed, please make sure you enter a valid input.").perform();

        By SecondName_Error= By.id("UserLastNames-error") ;
        driver.element().verifyThat(SecondName_Error).text().isEqualTo("Sorry, Numbers and special characters are not allowed, please make sure you enter a valid input.").perform();
    }

    @Test(priority = 3)
    public void UpdateName_Check_that_ErrorAppear_whenInsert_lessThanMin_inNameFields()
    {
        OpenAccountSettings_Page() ;

        By Update_name= By.linkText("Update Name") ;
        driver.element().click(Update_name);

        By FirstName = By.id("UserFirstNames");
        driver.element().type(FirstName, "nn");

        By LastName = By.id("UserLastNames");
        driver.element().type(LastName, "nn");

        By submitButton = By.xpath("//*[@id=\"SettingsResetNamesForm\"]/input[4]");
        driver.element().click(submitButton);

        By FirstName_Error= By.id("UserFirstNames-error") ;
        driver.element().verifyThat(FirstName_Error).text().isEqualTo("First name minimum length is 3 character").perform();

        By SecondName_Error= By.id("UserLastNames-error") ;
        driver.element().verifyThat(SecondName_Error).text().isEqualTo("Last name minimum length is 3 character").perform();
    }

    @Test(priority = 4)
    public void UpdateName_Check_that_ErrorAppear_whenInsert_GreaterThanMax_inNameFields()
    {
        OpenAccountSettings_Page() ;

        By Update_name= By.linkText("Update Name") ;
        driver.element().click(Update_name);

        By FirstName = By.id("UserFirstNames");
        driver.element().type(FirstName, "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");

        By LastName = By.id("UserLastNames");
        driver.element().type(LastName, "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");

        By submitButton = By.xpath("//*[@id=\"SettingsResetNamesForm\"]/input[4]");
        driver.element().click(submitButton);

        By FirstName_Error= By.id("UserFirstNames-error") ;
        driver.element().verifyThat(FirstName_Error).text().isEqualTo("First name maximum length is 50 character").perform();

        By SecondName_Error= By.id("UserLastNames-error") ;
        driver.element().verifyThat(SecondName_Error).text().isEqualTo("Last name maximum length is 50 character").perform();
    }

    @Test(priority = 5)
    public void UpdateName_Check_that_ErrorAppear_when_Not_DeclareData()
    {
        OpenAccountSettings_Page() ;

        By Update_name= By.linkText("Update Name") ;
        driver.element().click(Update_name);

        By FirstName = By.id("UserFirstNames");
        driver.element().type(FirstName, "Nohair");

        By LastName = By.id("UserLastNames");
        driver.element().type(LastName, "gamal");

        By submitButton = By.xpath("//*[@id=\"SettingsResetNamesForm\"]/input[4]");
        driver.element().click(submitButton);

        By declare_Error= By.id("AddressModalMSG") ;
        driver.element().verifyThat(declare_Error).text().isEqualTo("You must declare that all information provided is true.").perform();
    }

    @Test(priority = 6)
    public void Check_that_UpdateName_WorkCorrectly()
    {
        OpenAccountSettings_Page() ;

        By Update_name= By.linkText("Update Name") ;
        driver.element().click(Update_name);

        By FirstName = By.id("UserFirstNames");
        driver.element().type(FirstName, "nohair");

        By LastName = By.id("UserLastNames");
        driver.element().type(LastName, "gamal");

        By declare_checkbox = By.id("Mailingagree");
        driver.element().click(declare_checkbox) ;

        By submitButton = By.xpath("//*[@id=\"SettingsResetNamesForm\"]/input[4]");
        driver.element().click(submitButton);

        By message = By.id("AddressModalMSG");
        driver.element().verifyThat(message).text().isEqualTo(" Data Saved").perform();
    }

    @Test(priority = 7)
    public void Check_that_UpdateGender_WorkCorrectly()
    {
        OpenAccountSettings_Page() ;

        By Update_Gender_Button = By.id("UpdateUserGender");
        driver.element().click(Update_Gender_Button) ;

        By Female_Choice = By.id("f-button");
        driver.element().click(Female_Choice) ;

        By save = By.xpath("//*[@id=\"UpdateUserGenderForm\"]/div/div[3]/input[1]") ;
        driver.element().click(save) ;

        By GenderValue= By.id("FemaleGender") ;
        driver.element().verifyThat(GenderValue).text().isEqualTo("Female").perform();

        By Update_Gender_Button2 = By.id("UpdateUserGender");
        driver.element().click(Update_Gender_Button2) ;

        By Male_Choice= By.id("m-button");
        driver.element().click(Male_Choice) ;

        By save2 = By.xpath("//*[@id=\"UpdateUserGenderForm\"]/div/div[3]/input[1]") ;
        driver.element().click(save2) ;
    }

    @Test(priority = 8)
    public void Check_that_UpdateDate_of_Birth_WorkCorrectly ()
    {
        OpenAccountSettings_Page() ;

        By Update_Date_ofBirth_Button= By.id("UpdateUserDOB");
        driver.element().click(Update_Date_ofBirth_Button) ;

        By Year_List = By.id("year");
        driver.element().waitToBeReady(Year_List);
        driver.element().clickUsingJavascript(Year_List);
        driver.element().select(Year_List, "1940");

        By Month_List = By.id("month");
        driver.element().click(Month_List) ;
        driver.element().select(Month_List, "9");

        By Day_List = By.id("day");
        driver.element().click(Day_List) ;
        driver.element().select(Day_List, "10");

        By save = By.xpath("//*[@id=\"UpdateUserBirthDateForm\"]/div[2]/input[1]") ;
        driver.element().click(save) ;

        By Date_ofBirthValue= By.id("UserDOB") ;
        driver.element().verifyThat(Date_ofBirthValue).text().isEqualTo("10/09/1940").perform();

        //////////////////// repeat steps again to put default values as 2/5/1996 /////////////////////
        By Update_Date_ofBirth_Button2= By.id("UpdateUserDOB");
        driver.element().click(Update_Date_ofBirth_Button2) ;

        By Year_List2 = By.id("year");
        driver.element().waitToBeReady(Year_List2);
        driver.element().clickUsingJavascript(Year_List2);
        driver.element().select(Year_List2, "1996");

        By Month_List2 = By.id("month");
        driver.element().click(Month_List2) ;
        driver.element().select(Month_List2, "5");

        By Day_List2 = By.id("day");
        driver.element().click(Day_List2) ;
        driver.element().select(Day_List2, "2");

        By save2 = By.xpath("//*[@id=\"UpdateUserBirthDateForm\"]/div[2]/input[1]") ;
        driver.element().click(save2) ;

        By Date_ofBirthValue2= By.id("UserDOB") ;
        driver.element().verifyThat(Date_ofBirthValue2).text().isEqualTo("02/05/1996").perform();
    }

    @AfterMethod
    public void CloseDriver()
    {
        if (driver != null)
        {
            driver.quit();
        }
    }
}