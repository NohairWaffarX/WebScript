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

    private void ensureElementReady(By locator) {
        driver.element().waitToBeReady(locator);
    }

    private void retryClick(By locator, int maxRetries) {
        int attempt = 0;
        while (attempt < maxRetries) {
            try {
                ensureElementReady(locator);
                driver.element().click(locator);
                System.out.println("Successfully clicked the element on attempt " + (attempt + 1));
                return; // Exit the method if click is successful
            } catch (org.openqa.selenium.ElementClickInterceptedException e) {
                System.out.println("Click intercepted on attempt " + (attempt + 1) + ". Retrying...");
                attempt++;
                try {
                    Thread.sleep(800); // Wait before retrying (500 ms)
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                    throw new RuntimeException("Interrupted while waiting to retry click.", ie);
                }
            }
        }
        throw new RuntimeException("Failed to click the element after " + maxRetries + " attempts.");
    }

    private void retryType(By locator, String text, int maxRetries) {
        int attempt = 0;
        while (attempt < maxRetries) {
            try {
                ensureElementReady(locator);
                driver.element().type(locator, text);
                System.out.println("Successfully typed into the element on attempt " + (attempt + 1));
                return; // Exit the method if typing is successful
            } catch (org.openqa.selenium.ElementNotInteractableException e) {
                System.out.println("Element not interactable on attempt " + (attempt + 1) + ". Retrying...");
                attempt++;
                try {
                    Thread.sleep(800); // Wait before retrying (500 ms)
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                    throw new RuntimeException("Interrupted while waiting to retry type.", ie);
                }
            }
        }
        throw new RuntimeException("Failed to type into the element after " + maxRetries + " attempts.");
    }

    private void clickCIBButton() {
        By Register_Button = By.xpath("//*[@id='heatmapArea']/main/div[2]/div[1]/button");
        driver.element().waitToBeReady(Register_Button);
        driver.element().clickUsingJavascript(Register_Button);

        By CIB_Button = By.xpath("//*[@id=\"newSignUp\"]/div/div/div/div[1]/a[2]") ;
        retryClick(CIB_Button, 30);

        CloseBrowser_extension = By.xpath("//*[@id=\"closeAds\"]/i") ;
        retryClick(CloseBrowser_extension, 30);
        By Next1 = By.xpath("//*[@id='heatmapArea']/main/div[2]/div[2]/div[2]/a") ;
        retryClick(Next1, 30);
        retryClick(CloseBrowser_extension, 30);
        By Next2 = By.xpath("//*[@id='heatmapArea']/main/div[2]/div[3]/a") ;
        retryClick(Next2, 30);

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
        retryType(FullName, "@#$456", 30);
        driver.element().keyPress(JoinNow_Button, ENTER);
        driver.element().verifyThat(NameError).text().isEqualTo("Full name must be characters only").perform();
    }

    @Test
    public void B_Check_that_ErrorAppear_whenName_isEmpty()
    {
        clickCIBButton() ;
        retryType(FullName, " ", 30);
        driver.element().keyPress(JoinNow_Button, ENTER);
        driver.element().verifyThat(NameError).text().isEqualTo("Full name minimum length is 3 character").perform();
    }

    @Test
    public void C_Check_that_Name_has_max_limitation_as_50char()
    {
        clickCIBButton() ;
        retryType(FullName, "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA", 30);
        driver.element().keyPress(JoinNow_Button, ENTER);
        driver.element().verifyThat(NameError).text().isEqualTo("Full name maximum length is 50 character").perform();
    }

    @Test(priority = 4)
    public void D_Check_that_Name_has_min_limitation_as_3char()
    {
        clickCIBButton() ;
        retryType(FullName, "AA", 30);
        driver.element().keyPress(JoinNow_Button, ENTER);
        driver.element().verifyThat(NameError).text().isEqualTo("Full name minimum length is 3 character").perform();
    }
    ///////////////////////////////   Test cases of Email /////////////////////////////////////////
    @Test
    public void E_Check_that_ErrorAppear_whenEmail_isEmpty()
    {
        clickCIBButton() ;
        retryType(Email, " ", 30);
        driver.element().keyPress(JoinNow_Button, ENTER);
        driver.element().verifyThat(EmailError).text().isEqualTo("The e-mail that you entered is wrong").perform();
    }

    @Test
    public void F_Check_that_ErrorAppear_whenInsert_WrongFormat_inEmail()
    {
        clickCIBButton() ;
        retryType(Email, "nohair", 30);
        driver.element().keyPress(JoinNow_Button, ENTER);
        driver.element().verifyThat(EmailError).text().isEqualTo("The e-mail that you entered is wrong").perform();
    }
    ///////////////////////////////   Test cases of Password /////////////////////////////////////////
    @Test
    public void G_Check_that_ErrorAppear_whenPasswordANDConfirmPassword_areEmpty()
    {
        clickCIBButton() ;
        retryType(Password, " ", 30);
        retryType(ConfirmPassword, " ", 30);
        driver.element().keyPress(JoinNow_Button, ENTER);
        driver.element().verifyThat(PasswordError).text().isEqualTo("Your password should be a minimum of 8 characters and a maximum of 20 characters. It should also contain the following: 1 Uppercase Letter, 1 Lowercase Letter, & 1 Number.").perform();
        driver.element().verifyThat(ConfirmPasswordError).text().isEqualTo("Confirm Password must be at least 8 characters").perform();
    }

    @Test
    public void H_Check_that_ErrorAppear_whenPasswordANDConfirmPassword_NotMatch()
    {
        clickCIBButton() ;
        retryType(Password, "Qw222222", 30);
        retryType(ConfirmPassword, "As333333", 30);
        driver.element().keyPress(JoinNow_Button, ENTER);
        driver.element().verifyThat(ConfirmPasswordError).text().isEqualTo("The new password and confirm password do not match.").perform();
    }

    @Test
    public void I_Check_that_ErrorAppear_whenPassword_less_than_8chars()   //password less than 8 chars
    {
        clickCIBButton() ;
        retryType(Password, "Qw22222", 30);
        retryType(ConfirmPassword, "Qw22222", 30);
        driver.element().keyPress(JoinNow_Button, ENTER);
        driver.element().verifyThat(PasswordError).text().isEqualTo("Your password should be a minimum of 8 characters and a maximum of 20 characters. It should also contain the following: 1 Uppercase Letter, 1 Lowercase Letter, & 1 Number.").perform();
    }

    @Test
    public void J_Check_that_ErrorAppear_whenPassword_Greater_than_20chars()   //password greater than 20 chars
    {
        clickCIBButton() ;
        retryType(Password, "Qw2222222222222222222", 30);
        retryType(ConfirmPassword, "Qw2222222222222222222", 30);
        driver.element().keyPress(JoinNow_Button, ENTER);
        driver.element().verifyThat(PasswordError).text().isEqualTo("Your password should be a minimum of 8 characters and a maximum of 20 characters. It should also contain the following: 1 Uppercase Letter, 1 Lowercase Letter, & 1 Number.").perform();
    }

    @Test
    public void K_Check_that_ErrorAppear_whenPassword_notContain_small_letter()   //password don't contain small letter
    {
        clickCIBButton() ;
        retryType(Password, "QQ222222", 30);
        retryType(ConfirmPassword, "QQ222222", 30);
        driver.element().keyPress(JoinNow_Button, ENTER);
        driver.element().verifyThat(PasswordError).text().isEqualTo("Your password should be a minimum of 8 characters and a maximum of 20 characters. It should also contain the following: 1 Uppercase Letter, 1 Lowercase Letter, & 1 Number.").perform();
    }

    @Test
    public void L_Check_that_ErrorAppear_whenPassword_notContain_Capital_letter() //password don't contain capital letter
    {
        clickCIBButton() ;
        retryType(Password, "qq222222", 30);
        retryType(ConfirmPassword, "qq222222", 30);
        driver.element().keyPress(JoinNow_Button, ENTER);
        driver.element().verifyThat(PasswordError).text().isEqualTo("Your password should be a minimum of 8 characters and a maximum of 20 characters. It should also contain the following: 1 Uppercase Letter, 1 Lowercase Letter, & 1 Number.").perform();
    }

    @Test
    public void M_Check_that_ErrorAppear_whenPassword_notContain_Number()   //password don't contain Number
    {
        clickCIBButton() ;
        retryType(Password, "QQwwwwww", 30);
        retryType(ConfirmPassword, "QQwwwwww", 30);
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
        retryType(FullName, "Nohair", 30);
        retryType(Email, getRandomEmail(), 30);
        retryType(Password, "Qw222222", 30);
        retryType(ConfirmPassword, "Qw222222", 30);
        driver.element().keyPress(JoinNow_Button, ENTER);
        retryClick(CloseBrowser_extension, 30);
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
