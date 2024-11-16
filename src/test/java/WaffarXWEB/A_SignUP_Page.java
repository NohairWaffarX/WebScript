package WaffarXWEB;
import com.shaft.driver.SHAFT;
import org.openqa.selenium.By;
import org.testng.annotations.*;
import java.util.UUID;
import static org.openqa.selenium.Keys.ENTER;

public class A_SignUP_Page
{
    SHAFT.GUI.WebDriver driver;
    By FullName , JoinNow_Button,Password,ConfirmPassword,NameError,EmailError,Email,PasswordError,ConfirmPasswordError ;
    @BeforeMethod
    public void setupBrowser()
    {
        driver = new SHAFT.GUI.WebDriver();
        driver.browser().navigateToURL("https://portal-test.waffarx.com/en-eg");
    }
    private void retryType(By locator, String text, int maxRetries) {
        int attempt = 0;
        while (attempt < maxRetries) {
            try {
                driver.element().type(locator, text);
                System.out.println("Successfully typed into the element on attempt " + (attempt + 1));
                return; // Exit the method if typing is successful
            } catch (org.openqa.selenium.ElementNotInteractableException e) {
                System.out.println("Element not interactable on attempt " + (attempt + 1) + ". Retrying...");
                attempt++;
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                    throw new RuntimeException("Interrupted while waiting to retry type.", ie);
                }
            }
        }
        throw new RuntimeException("Failed to type into the element after " + maxRetries + " attempts.");
    }

    private void clickRegisterButton()
    {
        By Register_Button = By.xpath("//*[@id='heatmapArea']/main/div[2]/div[1]/button");
        driver.element().waitToBeReady(Register_Button ,true);
        driver.element().clickUsingJavascript(Register_Button);

        FullName = By.id("RegisterFirstName");
        Email = By.id("RegisterEmail");
        JoinNow_Button = By.xpath("//*[@id=\"SignupMob\"]/div[7]/input");
        Password = By.id("RegisterPassword");
        ConfirmPassword = By.id("RegisterConfirmPassword");
        NameError = By.id("RegisterFirstName-error");
        EmailError = By.id("RegisterEmail-error");
        PasswordError = By.id("RegisterPassword-error");
        ConfirmPasswordError = By.id("RegisterConfirmPassword-error");
    }

    ///////////////////////////////   Test cases of Name /////////////////////////////////////////
    @Test
    public void A_Check_that_ErrorAppear_whenNameIs_SymbolsANDChars()
    {
        clickRegisterButton();
        retryType(FullName, "@#$456", 40);
        driver.element().keyPress(JoinNow_Button, ENTER);
        driver.element().verifyThat(NameError).text().isEqualTo("Full name must be characters only").perform();
    }

    @Test
    public void B_Check_that_ErrorAppear_whenName_isEmpty()
    {
        clickRegisterButton() ;
        retryType(FullName, " ", 40);
        driver.element().keyPress(JoinNow_Button, ENTER);
        driver.element().verifyThat(NameError).text().isEqualTo("Full name minimum length is 3 character").perform();
    }

    @Test
    public void C_Check_that_Name_has_max_limitation_as_50char()
    {
        clickRegisterButton() ;
        retryType(FullName, "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA", 40);
        driver.element().keyPress(JoinNow_Button, ENTER);
        driver.element().verifyThat(NameError).text().isEqualTo("Full name maximum length is 50 character").perform();
    }

    @Test
    public void D_Check_that_Name_has_min_limitation_as_3char()
    {
        clickRegisterButton() ;
        retryType(FullName, "AA", 40);
        driver.element().keyPress(JoinNow_Button, ENTER);
        driver.element().verifyThat(NameError).text().isEqualTo("Full name minimum length is 3 character").perform();
    }
///////////////////////////////   Test cases of Email /////////////////////////////////////////
    @Test
    public void E_Check_that_ErrorAppear_whenEmail_isEmpty()
    {
        clickRegisterButton() ;
        retryType(Email, " ", 40);
        driver.element().keyPress(JoinNow_Button, ENTER);
        driver.element().verifyThat(EmailError).text().isEqualTo("The e-mail that you entered is wrong").perform();
    }

    @Test
    public void F_Check_that_ErrorAppear_whenInsert_WrongFormat_inEmail()
    {
        clickRegisterButton() ;
        retryType(Email, "nohair", 40);
        driver.element().keyPress(JoinNow_Button, ENTER);
        driver.element().verifyThat(EmailError).text().isEqualTo("The e-mail that you entered is wrong").perform();
    }

    @Test
    public void G_Check_that_ErrorAppear_whenInsert_EmailAlreadyExist()
    {
        clickRegisterButton() ;
        retryType(FullName, "Nohair", 40);
        retryType(Email, "gnohair@gmail.com", 40);
        retryType(Password, "Qw222222", 40);
        retryType(ConfirmPassword, "Qw222222", 40);
        driver.element().keyPress(JoinNow_Button, ENTER);
        By Error = By.xpath("//*[@id=\"heatmapArea\"]/div[17]");
        driver.element().verifyThat(Error).isVisible().perform();
    }
    ///////////////////////////////   Test cases of Password /////////////////////////////////////////
    @Test
    public void H_Check_that_ErrorAppear_whenPasswordANDConfirmPassword_areEmpty()
    {
        clickRegisterButton() ;
        retryType(Password, " ", 40);
        retryType(ConfirmPassword, " ", 40);
        driver.element().keyPress(JoinNow_Button, ENTER);
        driver.element().verifyThat(PasswordError).text().isEqualTo("Your password should be a minimum of 8 characters and a maximum of 20 characters. It should also contain the following: 1 Uppercase Letter, 1 Lowercase Letter, & 1 Number.").perform();
        driver.element().verifyThat(ConfirmPasswordError).text().isEqualTo("Confirm Password must be at least 8 characters").perform();
    }

    @Test
    public void I_Check_that_ErrorAppear_whenPasswordANDConfirmPassword_NotMatch()
    {
        clickRegisterButton() ;
        retryType(Password, "Qw222222", 40);
        retryType(ConfirmPassword, "As333333", 40);
        driver.element().keyPress(JoinNow_Button, ENTER);
        driver.element().verifyThat(ConfirmPasswordError).text().isEqualTo("The new password and confirm password do not match.").perform();
    }

    @Test
    public void J_Check_that_ErrorAppear_whenPassword_less_than_8chars()   //password less than 8 chars
    {
        clickRegisterButton() ;
        retryType(Password, "Qw22222", 40);
        retryType(ConfirmPassword, "Qw22222", 40);
        driver.element().keyPress(JoinNow_Button, ENTER);
        driver.element().verifyThat(PasswordError).text().isEqualTo("Your password should be a minimum of 8 characters and a maximum of 20 characters. It should also contain the following: 1 Uppercase Letter, 1 Lowercase Letter, & 1 Number.").perform();
    }

    @Test
    public void K_Check_that_ErrorAppear_whenPassword_Greater_than_20chars()   //password greater than 20 chars
    {
        clickRegisterButton() ;
        retryType(Password, "Qw2222222222222222222", 40);
        retryType(ConfirmPassword, "Qw2222222222222222222", 40);
        driver.element().keyPress(JoinNow_Button, ENTER);
        driver.element().verifyThat(PasswordError).text().isEqualTo("Your password should be a minimum of 8 characters and a maximum of 20 characters. It should also contain the following: 1 Uppercase Letter, 1 Lowercase Letter, & 1 Number.").perform();
    }

    @Test
    public void L_Check_that_ErrorAppear_whenPassword_notContain_small_letter()   //password don't contain small letter
    {
        clickRegisterButton() ;
        retryType(Password, "QQ222222", 40);
        retryType(ConfirmPassword, "QQ222222", 40);
        driver.element().keyPress(JoinNow_Button, ENTER);
        driver.element().verifyThat(PasswordError).text().isEqualTo("Your password should be a minimum of 8 characters and a maximum of 20 characters. It should also contain the following: 1 Uppercase Letter, 1 Lowercase Letter, & 1 Number.").perform();
    }

    @Test
    public void M_Check_that_ErrorAppear_whenPassword_notContain_Capital_letter()   //password don't contain Big letter
    {
        clickRegisterButton() ;
        retryType(Password, "qq222222", 40);
        retryType(ConfirmPassword, "qq222222", 40);
        driver.element().keyPress(JoinNow_Button, ENTER);
        driver.element().verifyThat(PasswordError).text().isEqualTo("Your password should be a minimum of 8 characters and a maximum of 20 characters. It should also contain the following: 1 Uppercase Letter, 1 Lowercase Letter, & 1 Number.").perform();
    }

    @Test
    public void N_Check_that_ErrorAppear_whenPassword_notContain_Number()   //password don't contain Number
    {
        clickRegisterButton() ;
        retryType(Password, "QQwwwwww", 40);
        retryType(ConfirmPassword, "QQwwwwww", 40);
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
    public void O_Check_that_SignUp_workCorrectly()
    {
        clickRegisterButton() ;
        retryType(FullName, "Nohair", 40);
        retryType(Email, getRandomEmail(), 40);
        retryType(Password, "Qw222222", 40);
        retryType(ConfirmPassword, "Qw222222", 40);
        driver.element().keyPress(JoinNow_Button, ENTER);
        By Search_text = By.id("searchtext");
        driver.element().verifyThat(Search_text).isVisible() ;
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
