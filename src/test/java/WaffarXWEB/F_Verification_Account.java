package WaffarXWEB;
import com.shaft.driver.SHAFT;
import org.openqa.selenium.By;
import org.testng.annotations.*;
import static org.openqa.selenium.Keys.ENTER;

public class F_Verification_Account
{
    SHAFT.GUI.WebDriver driver;
    By PhoneNumber_Field , SendCode_Button ;
    @BeforeMethod
    public void setupBrowser()
    {
        driver = new SHAFT.GUI.WebDriver(); // to open browser
        driver.browser().navigateToURL("https://www.waffarx.com/en-eg"); // to navigate to URL
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

    private void Open_PopupOF_VerifyAccount()
    {
        By Register_Button = By.xpath("//*[@id='heatmapArea']/main/div[2]/div[1]/button");
        driver.element().waitToBeReady(Register_Button);
        driver.element().clickUsingJavascript(Register_Button);

        By AlreadyMember_Button = By.xpath("//*[@id='newSignUp']/div/div/div/div[4]/a") ;
        driver.element().clickUsingJavascript(AlreadyMember_Button);

        By Email = By.id("LoginEmail");
        retryType(Email, "gnohair@gmail.com", 30);

        By Password = By.id("LoginPassword");
        retryType(Password, "Ng555555", 30);

        By SignIN_Button = By.xpath("//*[@id=\"Login\"]/div[4]/input");
        driver.element().keyPress(SignIN_Button, ENTER);

        By Verify_Account_Button = By.xpath("//*[@id=\"verify-Banner\"]/p/button") ;
        driver.element().clickUsingJavascript(Verify_Account_Button) ;

        PhoneNumber_Field = By.id("phone");
        SendCode_Button = By.id("SendCode");
    }

    @Test
    public void AA_Check_that_ErrorAppear_whenInsert_Number_AlreadyUsed()
    {
        Open_PopupOF_VerifyAccount() ;
        retryType(PhoneNumber_Field, "01277249225", 20);
        retryClick(SendCode_Button, 30);
        By Error= By.className("ExistedMobNumber") ;
        driver.element().verifyThat(Error).text().isEqualTo("This mobile number has already been registered.").perform();
    }

    @Test
    public void B_Check_that_ErrorAppear_whenInsert_LessThan_MinLimitation_InMobileNumber()
    {
        Open_PopupOF_VerifyAccount() ;
        retryType(PhoneNumber_Field, "012", 30);
        retryClick(SendCode_Button, 30);
        By Error= By.id("phone-error");
        driver.element().verifyThat(Error).text().isEqualTo("Sorry, Arabic and special characters are not allowed, please make sure you enter a valid input.").perform();
    }

    @Test
    public void C_Check_that_ErrorAppear_whenInsert_GreaterThan_MaxLimitation_InMobileNumber()
    {
        Open_PopupOF_VerifyAccount() ;
        retryType(PhoneNumber_Field, "0127724999999999888", 30);
        retryClick(SendCode_Button, 30);
        By Error= By.id("WrongEgMobNumber");
        driver.element().verifyThat(Error).text().isEqualTo("Please make sure that phone number is true, contains only numbers and consist of 11 number.").perform();

    }
    @Test
    public void D_Verify_Account_exceed3times() throws InterruptedException {
        Open_PopupOF_VerifyAccount() ;
        retryType(PhoneNumber_Field, "01067802082", 30);
        retryClick(SendCode_Button, 30);

        By ReSend = By.id("ReSendCode");
        Thread.sleep(140000);
        retryClick(ReSend, 30);  // After click on it , Second otp send

        Thread.sleep(140000);
        retryClick(ReSend, 30); // After click on it , third otp send

        Thread.sleep(140000);
        retryClick(ReSend, 30);// After click on it , Error Message appear

        By Error= By.id("ExceedCount") ;
        driver.element().verifyThat(Error).text().isEqualTo("You exceed the number of times the activation code has been sent, please contact customer service to complete the activation process").perform();
    }

    @Test(dependsOnMethods = { "D_Verify_Account_exceed3times" })
    public void E_Check_that_ErrorAppear_whenInsert_WrongVerificationCode() {
        Open_PopupOF_VerifyAccount();

        By VerificationCode_Text = By.xpath("(//*[@id='VerificationCode'])[2]");
        retryType(VerificationCode_Text, "88888", 30);

        By VerifyAccount_Button = By.xpath("//*[@id=\"VerifyUserPhoneNumber\"]/input");
        retryClick(VerifyAccount_Button, 30);

        By Error = By.xpath("//*[@id=\"WrongVerify\"]");
        driver.element().verifyThat(Error).text().isEqualTo("Invalid verification Code!").perform();
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
