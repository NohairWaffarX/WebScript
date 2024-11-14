package WaffarXWEB;
import com.shaft.driver.SHAFT;
import org.openqa.selenium.By;
import org.testng.annotations.*;
import static org.openqa.selenium.Keys.ENTER;

public class D_AccountSettings
{
    SHAFT.GUI.WebDriver driver;
    By Update_name , submitButton,FirstName,FirstName_Error,SecondName_Error,LastName ;

    @BeforeMethod
    public void setupBrowser()
    {
        driver = new SHAFT.GUI.WebDriver(); // to open browser
        driver.browser().navigateToURL("https://www.waffarx.com/en-eg");
    }

    private void retryClick(By locator, int maxRetries) {
        int attempt = 0;
        while (attempt < maxRetries) {
            try {
                driver.element().click(locator);
                System.out.println("Successfully clicked the element on attempt " + (attempt + 1));
                return; // Exit the method if click is successful
            } catch (org.openqa.selenium.ElementClickInterceptedException e) {
                System.out.println("Click intercepted on attempt " + (attempt + 1) + ". Retrying...");
                attempt++;
                try {
                    Thread.sleep(500); // Wait before retrying (500 ms)
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                    throw new RuntimeException("Interrupted while waiting to retry click.", ie);
                }
            }
        }
        throw new RuntimeException("Failed to click the element after " + maxRetries + " attempts.");
    }

    private void retryHover(By locator, int maxRetries) {
        int attempt = 0;
        while (attempt < maxRetries) {
            try {
                driver.element().hover(locator);
                System.out.println("Successfully clicked the element on attempt " + (attempt + 1));
                return; // Exit the method if click is successful
            } catch (org.openqa.selenium.ElementClickInterceptedException e) {
                System.out.println("Click intercepted on attempt " + (attempt + 1) + ". Retrying...");
                attempt++;
                try {
                    Thread.sleep(500); // Wait before retrying (500 ms)
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                    throw new RuntimeException("Interrupted while waiting to retry click.", ie);
                }
            }
        }
        throw new RuntimeException("Failed to click the element after " + maxRetries + " attempts.");
    }


    private void OpenAccountSettings_Page()
    {
        By Register_Button = By.xpath("//*[@id='heatmapArea']/main/div[2]/div[1]/button");
        driver.element().waitToBeReady(Register_Button);
        driver.element().clickUsingJavascript(Register_Button);

        By AlreadyMember_Button = By.xpath("//*[@id='newSignUp']/div/div/div/div[4]/a") ;
        driver.element().clickUsingJavascript(AlreadyMember_Button);

        By Email = By.id("LoginEmail");
        driver.element().type(Email, "gnohair@gmail.com");

        By Password = By.id("LoginPassword");
        driver.element().type(Password, "Ng555555");

        By SignIN_Button = By.xpath("//*[@id=\"Login\"]/div[4]/input");
        driver.element().keyPress(SignIN_Button, ENTER);

        By userinfo = By.xpath("//*[@id=\"fixedHeader\"]/div[3]/div/div[2]/div[3]/div/i") ;
        driver.element().waitToBeReady(userinfo) ;
        retryClick(userinfo, 5);
       // driver.element().hover(userinfo);

        By AccountSettings_choice= By.linkText("Account Settings") ;
       // driver.element().clickUsingJavascript(AccountSettings_choice);
        retryClick(AccountSettings_choice, 5);

        Update_name= By.linkText("Update Name") ;
        submitButton = By.xpath("//*[@id=\"SettingsResetNamesForm\"]/input[4]");
        FirstName = By.id("UserFirstNames");
        LastName = By.id("UserLastNames");
        FirstName_Error= By.id("UserFirstNames-error");
        SecondName_Error= By.id("UserLastNames-error");
    }

    @Test
    public void A_UpdateName_Check_that_ErrorAppear_whenFields_AreEmpty()
    {
        OpenAccountSettings_Page() ;
       // driver.element().click(Update_name);
        retryClick(Update_name, 5);
      //  driver.element().click(submitButton);
        retryClick(submitButton, 5);
        driver.element().verifyThat(FirstName_Error).text().isEqualTo("This field is required.").perform();
        driver.element().verifyThat(SecondName_Error).text().isEqualTo("This field is required.").perform();
    }

    @Test
    public void B_UpdateName_Check_that_ErrorAppear_whenInsert_SymbolsAndSymbols_inNameFields()
    {
        OpenAccountSettings_Page() ;
       // driver.element().click(Update_name);
        retryClick(Update_name, 5); // Retry up to 5 times
        driver.element().type(FirstName, "@#@#2333");
        driver.element().type(LastName, "@#@#2333");
      //  driver.element().click(submitButton);
        retryClick(submitButton, 5); // Retry up to 5 times
        driver.element().verifyThat(FirstName_Error).text().isEqualTo("Sorry, Numbers and special characters are not allowed, please make sure you enter a valid input.").perform();
        driver.element().verifyThat(SecondName_Error).text().isEqualTo("Sorry, Numbers and special characters are not allowed, please make sure you enter a valid input.").perform();
    }

    @Test
    public void C_UpdateName_Check_that_ErrorAppear_whenInsert_lessThanMin_inNameFields()
    {
        OpenAccountSettings_Page() ;
       // driver.element().click(Update_name);
        retryClick(Update_name, 5); // Retry up to 5 times
        driver.element().type(FirstName, "nn");
        driver.element().type(LastName, "nn");
      //  driver.element().click(submitButton);
        retryClick(submitButton, 5); // Retry up to 5 times
        driver.element().verifyThat(FirstName_Error).text().isEqualTo("First name minimum length is 3 character").perform();
        driver.element().verifyThat(SecondName_Error).text().isEqualTo("Last name minimum length is 3 character").perform();
    }

    @Test
    public void D_UpdateName_Check_that_ErrorAppear_whenInsert_GreaterThanMax_inNameFields()
    {
        OpenAccountSettings_Page() ;
      //  driver.element().click(Update_name);
        retryClick(Update_name, 5); // Retry up to 5 times
        driver.element().type(FirstName, "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        driver.element().type(LastName, "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
      //  driver.element().click(submitButton);
        retryClick(submitButton, 5); // Retry up to 5 times
        driver.element().verifyThat(FirstName_Error).text().isEqualTo("First name maximum length is 50 character").perform();
        driver.element().verifyThat(SecondName_Error).text().isEqualTo("Last name maximum length is 50 character").perform();
    }

    @Test
    public void E_UpdateName_Check_that_ErrorAppear_when_Not_DeclareData()
    {
        OpenAccountSettings_Page() ;
       // driver.element().click(Update_name);
        retryClick(Update_name, 5); // Retry up to 5 times
        driver.element().type(FirstName, "Nohair");
        driver.element().type(LastName, "gamal");
       // driver.element().click(submitButton);
        retryClick(submitButton, 5); // Retry up to 5 times
        By declare_Error= By.id("AddressModalMSG") ;
        driver.element().verifyThat(declare_Error).text().isEqualTo("You must declare that all information provided is true.").perform();
    }

    @Test
    public void F_Check_that_UpdateName_WorkCorrectly()
    {
        OpenAccountSettings_Page() ;
       // driver.element().click(Update_name);
        retryClick(Update_name, 5); // Retry up to 5 times
        driver.element().type(FirstName, "nohair");
        driver.element().type(LastName, "gamal");
        By declare_checkbox = By.id("Mailingagree");
        driver.element().click(declare_checkbox) ;
      //  driver.element().click(submitButton);
        retryClick(submitButton, 5); // Retry up to 5 times
        By message = By.id("AddressModalMSG");
        driver.element().verifyThat(message).text().isEqualTo(" Data Saved").perform();
    }

    @Test
    public void G_Check_that_UpdateGender_WorkCorrectly()
    {
        OpenAccountSettings_Page() ;

        By Update_Gender_Button = By.id("UpdateUserGender");
     //   driver.element().click(Update_Gender_Button) ;
        retryClick(Update_Gender_Button, 5); // Retry up to 5 times

        By Female_Choice = By.id("f-button");
       // driver.element().click(Female_Choice) ;
        retryClick(Female_Choice, 5); // Retry up to 5 times

        By save = By.xpath("//*[@id=\"UpdateUserGenderForm\"]/div/div[3]/input[1]") ;
      //  driver.element().click(save) ;
        retryClick(save, 5);

        By GenderValue= By.id("FemaleGender") ;
        driver.element().verifyThat(GenderValue).text().isEqualTo("Female").perform();

        By Update_Gender_Button2 = By.id("UpdateUserGender");
      //  driver.element().click(Update_Gender_Button2) ;
        retryClick(Update_Gender_Button2, 5);

        By Male_Choice= By.id("m-button");
      //  driver.element().click(Male_Choice) ;
        retryClick(Male_Choice, 5);

        By save2 = By.xpath("//*[@id=\"UpdateUserGenderForm\"]/div/div[3]/input[1]") ;
       // driver.element().click(save2) ;
        retryClick(save2, 5);
    }

    @Test
    public void H_Check_that_UpdateDate_of_Birth_WorkCorrectly ()
    {
        OpenAccountSettings_Page() ;

        By Update_Date_ofBirth_Button= By.id("UpdateUserDOB");
      //  driver.element().click(Update_Date_ofBirth_Button) ;
        retryClick(Update_Date_ofBirth_Button, 5);

        By Year_List = By.id("year");
        driver.element().waitToBeReady(Year_List);
       // driver.element().clickUsingJavascript(Year_List);
        retryClick(Year_List, 5);
        driver.element().select(Year_List, "1940");

        By Month_List = By.id("month");
       // driver.element().click(Month_List) ;
        retryClick(Month_List, 5);
        driver.element().select(Month_List, "9");

        By Day_List = By.id("day");
       // driver.element().click(Day_List) ;
        retryClick(Day_List, 5);
        driver.element().select(Day_List, "10");

        By save = By.xpath("//*[@id=\"UpdateUserBirthDateForm\"]/div[2]/input[1]") ;
      //  driver.element().click(save) ;
        retryClick(save, 5);

        By Date_ofBirthValue= By.id("UserDOB") ;
        driver.element().verifyThat(Date_ofBirthValue).text().isEqualTo("10/09/1940").perform();

        //////////////////// repeat steps again to put default values as 2/5/1996 /////////////////////
        By Update_Date_ofBirth_Button2= By.id("UpdateUserDOB");
       // driver.element().click(Update_Date_ofBirth_Button2) ;
        retryClick(Update_Date_ofBirth_Button2, 5);

        By Year_List2 = By.id("year");
        driver.element().waitToBeReady(Year_List2);
      //  driver.element().clickUsingJavascript(Year_List2);
        retryClick(Year_List2, 5);
        driver.element().select(Year_List2, "1996");

        By Month_List2 = By.id("month");
      //  driver.element().click(Month_List2) ;
        retryClick(Month_List2, 5);
        driver.element().select(Month_List2, "5");

        By Day_List2 = By.id("day");
     //   driver.element().click(Day_List2) ;
        retryClick(Day_List2, 5);
        driver.element().select(Day_List2, "2");

        By save2 = By.xpath("//*[@id=\"UpdateUserBirthDateForm\"]/div[2]/input[1]") ;
    //    driver.element().click(save2) ;
        retryClick(save2, 5);

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
