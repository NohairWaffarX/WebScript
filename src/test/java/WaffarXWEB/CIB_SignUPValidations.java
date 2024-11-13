package WaffarXWEB;
import com.shaft.driver.SHAFT;
import org.openqa.selenium.By;
import org.testng.annotations.*;
import java.util.UUID;
import static org.openqa.selenium.Keys.ENTER;

public class CIB_SignUPValidations
{
    SHAFT.GUI.WebDriver driver;
    By FullName ,Email , Password , ConfirmPassword,JoinNow_Button, PasswordError, NameError,EmailError ,
            ConfirmPasswordError , CloseBrowser_extension;
    ;
    @BeforeMethod
    public void setupBrowser()
    {
        driver = new SHAFT.GUI.WebDriver();
        driver.browser().navigateToURL("https://portal-test.waffarx.com/en-eg");
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

    private void clickCIBButton() {
        By Register_Button = By.xpath("//*[@id='heatmapArea']/main/div[2]/div[1]/button");
        driver.element().waitToBeReady(Register_Button);
        driver.element().clickUsingJavascript(Register_Button);
//        retryClick(Register_Button, 5); // Retry up to 5 times

        By CIB_Button = By.xpath("//*[@id=\"newSignUp\"]/div/div/div/div[1]/a[2]") ;
//        driver.element().click(CIB_Button);
        retryClick(CIB_Button, 5); // Retry up to 5 times

        CloseBrowser_extension = By.xpath("//*[@id=\"closeAds\"]/i") ;
//        driver.element().clickUsingJavascript(CloseBrowser_extension);
        retryClick(CloseBrowser_extension, 5); // Retry up to 5 times

        By Next = By.className("cib-btn") ;
      //  driver.element().click(Next);
        retryClick(Next, 5); // Retry up to 5 times
      //  driver.element().clickUsingJavascript(CloseBrowser_extension);
        retryClick(CloseBrowser_extension, 5); // Retry up to 5 times
      //  driver.element().click(Next);
        retryClick(Next, 5); // Retry up to 5 times


        FullName  = By.id("RegisterFirstName");
        Password = By.id("RegisterPassword");
        Email = By.id("RegisterEmail");
        ConfirmPassword = By.id("RegisterConfirmPassword");
        JoinNow_Button = By.xpath("//*[@id=\"SignupMob\"]/div[7]/input");
        NameError = By.id("RegisterFirstName-error");
        EmailError = By.id("RegisterEmail-error");
        PasswordError = By.id("RegisterPassword-error");
        ConfirmPasswordError = By.id("RegisterConfirmPassword-error");
    }



    @Test
    public void A_Check_that_ErrorAppear_whenNameIs_SymbolsANDChars()
    {
        clickCIBButton();
        driver.element().type(FullName, "@#$456");
        driver.element().keyPress(JoinNow_Button, ENTER);
        driver.element().verifyThat(NameError).text().isEqualTo("Full name must be characters only").perform();
    }

    @Test
    public void B_Check_that_ErrorAppear_whenName_isEmpty()
    {
        clickCIBButton() ;
        driver.element().type(FullName, " ");
        driver.element().keyPress(JoinNow_Button, ENTER);
        driver.element().verifyThat(NameError).text().isEqualTo("Full name minimum length is 3 character").perform();
    }

    @Test
    public void C_Check_that_Name_has_max_limitation_as_50char()
    {
        clickCIBButton() ;
        driver.element().type(FullName, "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");  //60Chars
        driver.element().keyPress(JoinNow_Button, ENTER);
        driver.element().verifyThat(NameError).text().isEqualTo("Full name maximum length is 50 character").perform();
    }

    @Test(priority = 4)
    public void D_Check_that_Name_has_min_limitation_as_3char()
    {
        clickCIBButton() ;
        driver.element().type(FullName, "AA");
        driver.element().keyPress(JoinNow_Button, ENTER);
        driver.element().verifyThat(NameError).text().isEqualTo("Full name minimum length is 3 character").perform();
    }
    ///////////////////////////////   Test cases of Email /////////////////////////////////////////
    @Test
    public void E_Check_that_ErrorAppear_whenEmail_isEmpty()
    {
        clickCIBButton() ;
        driver.element().type(Email, " ");
        driver.element().keyPress(JoinNow_Button, ENTER);
        driver.element().verifyThat(EmailError).text().isEqualTo("The e-mail that you entered is wrong").perform();
    }

    @Test
    public void F_Check_that_ErrorAppear_whenInsert_WrongFormat_inEmail()
    {
        clickCIBButton() ;
        driver.element().type(Email, "nohair");
        driver.element().keyPress(JoinNow_Button, ENTER);
        driver.element().verifyThat(EmailError).text().isEqualTo("The e-mail that you entered is wrong").perform();
    }
    ///////////////////////////////   Test cases of Password /////////////////////////////////////////
    @Test
    public void G_Check_that_ErrorAppear_whenPasswordANDConfirmPassword_areEmpty()
    {
        clickCIBButton() ;
        driver.element().type(Password, " ");
        driver.element().type(ConfirmPassword, " ");
        driver.element().keyPress(JoinNow_Button, ENTER);
        driver.element().verifyThat(PasswordError).text().isEqualTo("Your password should be a minimum of 8 characters and a maximum of 20 characters. It should also contain the following: 1 Uppercase Letter, 1 Lowercase Letter, & 1 Number.").perform();
        driver.element().verifyThat(ConfirmPasswordError).text().isEqualTo("Confirm Password must be at least 8 characters").perform();
    }

    @Test
    public void H_Check_that_ErrorAppear_whenPasswordANDConfirmPassword_NotMatch()
    {
        clickCIBButton() ;
        driver.element().type(Password, "Qw222222");
        driver.element().type(ConfirmPassword, "As333333");
        driver.element().keyPress(JoinNow_Button, ENTER);
        driver.element().verifyThat(ConfirmPasswordError).text().isEqualTo("The new password and confirm password do not match.").perform();
    }

    @Test
    public void I_Check_that_ErrorAppear_whenPassword_less_than_8chars()   //password less than 8 chars
    {
        clickCIBButton() ;
        driver.element().type(Password, "Qw22222");
        driver.element().type(ConfirmPassword, "Qw22222");
        driver.element().keyPress(JoinNow_Button, ENTER);
        driver.element().verifyThat(PasswordError).text().isEqualTo("Your password should be a minimum of 8 characters and a maximum of 20 characters. It should also contain the following: 1 Uppercase Letter, 1 Lowercase Letter, & 1 Number.").perform();
    }

    @Test
    public void J_Check_that_ErrorAppear_whenPassword_Greater_than_20chars()   //password greater than 20 chars
    {
        clickCIBButton() ;
        driver.element().type(Password, "Qw2222222222222222222");  //21 chars
        driver.element().type(ConfirmPassword, "Qw2222222222222222222");
        driver.element().keyPress(JoinNow_Button, ENTER);
        driver.element().verifyThat(PasswordError).text().isEqualTo("Your password should be a minimum of 8 characters and a maximum of 20 characters. It should also contain the following: 1 Uppercase Letter, 1 Lowercase Letter, & 1 Number.").perform();
    }

    @Test
    public void K_Check_that_ErrorAppear_whenPassword_notContain_small_letter()   //password don't contain small letter
    {
        clickCIBButton() ;
        driver.element().type(Password, "QQ222222");
        driver.element().type(ConfirmPassword, "QQ222222");
        driver.element().keyPress(JoinNow_Button, ENTER);
        driver.element().verifyThat(PasswordError).text().isEqualTo("Your password should be a minimum of 8 characters and a maximum of 20 characters. It should also contain the following: 1 Uppercase Letter, 1 Lowercase Letter, & 1 Number.").perform();
    }

    @Test
    public void L_Check_that_ErrorAppear_whenPassword_notContain_Capital_letter() //password don't contain capital letter
    {
        clickCIBButton() ;
        driver.element().type(Password, "qq222222");
        driver.element().type(ConfirmPassword, "qq222222");
        driver.element().keyPress(JoinNow_Button, ENTER);
        driver.element().verifyThat(PasswordError).text().isEqualTo("Your password should be a minimum of 8 characters and a maximum of 20 characters. It should also contain the following: 1 Uppercase Letter, 1 Lowercase Letter, & 1 Number.").perform();
    }

    @Test
    public void M_Check_that_ErrorAppear_whenPassword_notContain_Number()   //password don't contain Number
    {
        clickCIBButton() ;
        driver.element().type(Password, "QQwwwwww");
        driver.element().type(ConfirmPassword, "QQwwwwww");
        driver.element().keyPress(JoinNow_Button, ENTER);
        driver.element().verifyThat(PasswordError).text().isEqualTo("Your password should be a minimum of 8 characters and a maximum of 20 characters. It should also contain the following: 1 Uppercase Letter, 1 Lowercase Letter, & 1 Number.").perform();
    }

    private static String getRandomEmail()
    {
        String randomUsername = UUID.randomUUID().toString().replace("-", "").substring(0, 8);
        String domain = "AutomationTest.com";
        return randomUsername + "@" + domain;
    }

    @Test
    public void N_Check_that_SignUP_WorkCorrectly()   //password don't contain Number
    {
        clickCIBButton() ;
        driver.element().type(FullName, "Nohair");
        driver.element().type(Email, getRandomEmail());
        driver.element().type(Password, "Qw222222");
        driver.element().type(ConfirmPassword, "Qw222222");
        driver.element().keyPress(JoinNow_Button, ENTER);

      //  driver.element().clickUsingJavascript(CloseBrowser_extension);
        retryClick(CloseBrowser_extension, 5); // Retry up to 5 times

        By AddCard = By.xpath("//*[@id=\"AddCardSubmit\"]/div[1]/input");
        driver.element().verifyThat(AddCard).isVisible().perform();
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
