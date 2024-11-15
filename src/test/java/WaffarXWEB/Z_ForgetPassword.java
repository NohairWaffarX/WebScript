package WaffarXWEB;
import com.shaft.driver.SHAFT;
import org.openqa.selenium.By;
import org.testng.annotations.*;

public class Z_ForgetPassword
{
    SHAFT.GUI.WebDriver driver;
    By ForgetPasswordEmail,Send_Button, EmailError,ReferEmail,ForgetPasswordMobile ,SendOTP_Button , Phone ,MobileError;
    @BeforeMethod
    public void setupBrowser()
    {
        driver = new SHAFT.GUI.WebDriver();
        driver.browser().navigateToURL("https://www.waffarx.com/en-eg");
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
    private void clickForgetPassword_Button()
    {
        By Register_Button = By.xpath("//*[@id='heatmapArea']/main/div[2]/div[1]/button");
        driver.element().waitToBeReady(Register_Button);
        driver.element().clickUsingJavascript(Register_Button);

        By AlreadyMember_Button = By.xpath("//*[@id='newSignUp']/div/div/div/div[4]/a") ;
        driver.element().clickUsingJavascript(AlreadyMember_Button);

        By ForgetPassword = By.linkText("Forgot Password?") ;
        retryClick(ForgetPassword, 30);

        ForgetPasswordEmail = By.xpath("//*[@id=\"sendOTPOne\"]/div/div/div/button[3]") ;
        ForgetPasswordMobile = By.xpath("//*[@id=\"sendOTPOne\"]/div/div/div/button[2]") ;
        Send_Button = By.xpath("(//*[@id=\"forgetpasswordsubmit\"])[1]") ;
        EmailError = By.id("FPEmailErrors");
        ReferEmail = By.xpath("(//*[@id=\"ForgetPasswordEmail\"])[1]") ;
        SendOTP_Button = By.id("SendCodeFP") ;
        Phone = By.id("UserMobileNumber") ;
        MobileError = By.id("UserMobileNumber-error") ;
    }

    @Test
    public void A_Email_Check_that_ErrorAppear_whenClickSend_withoutInsertData()
    {
        clickForgetPassword_Button() ;
        retryClick(ForgetPasswordEmail, 30);
        retryClick(Send_Button, 30);
        driver.element().verifyThat(EmailError).text().isEqualTo("The e-mail that you entered is wrong").perform();
    }

    @Test
    public void B_Email_Check_that_ErrorAppear_whenInsert_WrongFormat_inEmail()
    {
        clickForgetPassword_Button() ;
        retryClick(ForgetPasswordEmail, 30);
        retryType(ReferEmail, "gnohair", 30);
        retryClick(Send_Button, 30);
        driver.element().verifyThat(EmailError).text().isEqualTo("The e-mail that you entered is wrong").perform();
    }

    @Test
    public void C_Email_Check_that_ErrorAppear_when_InsertEmail_notFound_inWaffarX()
    {
        clickForgetPassword_Button() ;
        retryClick(ForgetPasswordEmail, 30);
        retryType(ReferEmail, "nohairrrr@nohairrrr.com", 30);
        retryClick(Send_Button, 30);
        driver.element().verifyThat(EmailError).text().isEqualTo("This email is not registered at WaffarX.").perform();
    }

    @Test
    public void D_Email_Check_that_System_TerminateSpaces()
    {
        clickForgetPassword_Button() ;
        retryClick(ForgetPasswordEmail, 30);
        retryType(ReferEmail, "      gnohair@gmail.com    ", 30);
        retryClick(Send_Button, 30);
        By Register_Button = By.xpath("//*[@id='heatmapArea']/main/div[2]/div[1]/button");
        driver.element().verifyThat(Register_Button).isVisible().perform(); ;
    }

    @Test
    public void E_Mobile_Check_that_ErrorAppear_whenClickSend_withoutInsertData()
    {
        clickForgetPassword_Button();
        retryClick(ForgetPasswordMobile, 30);
        retryClick(SendOTP_Button, 30);
        driver.element().verifyThat(MobileError).text().isEqualTo("This field is required.").perform();
    }

    @Test
    public void F_Mobile_Check_that_ErrorAppear_when_insert_LessThan_MinLimitation_inPhone()
    {
        clickForgetPassword_Button() ;
        retryClick(ForgetPasswordMobile, 30);
        retryType(Phone, "010678", 30);
        retryClick(SendOTP_Button, 30);
        driver.element().verifyThat(MobileError).text().isEqualTo("Sorry, Arabic and special characters are not allowed, please make sure you enter a valid input.").perform();
    }
    @Test
    public void G_Mobile_Check_that_ErrorAppear_when_insert_GreaterThan_MaxLimitation_inPhone()
    {
        clickForgetPassword_Button() ;
        retryClick(ForgetPasswordMobile, 30);
        retryType(Phone, "010782378237823723873", 30);
        retryClick(SendOTP_Button, 30);
        By Error = By.id("FPWrongEgMobNumber") ;
        driver.element().verifyThat(Error).text().isEqualTo("Please make sure that phone number is true, contains only numbers and consist of 11 number.").perform();
    }

    @Test
    public void H_Mobile_Check_that_ErrorAppear_when_insertNumber_NotJoin_inWaffarX()
    {
        clickForgetPassword_Button() ;
        retryClick(ForgetPasswordMobile, 30);
        retryType(Phone, "01552153666", 30);
        retryClick(SendOTP_Button, 30);
        By Error = By.id("FPErrors") ;
        driver.element().verifyThat(Error).text().isEqualTo("This mobile number not verified at waffarx!").perform();
    }

    @Test
    public void I_Mobile_Check_that_OTP_sendCorrectlyWhen_insertCorrectNumber()
    {
        clickForgetPassword_Button() ;
        retryClick(ForgetPasswordMobile, 30);
        retryType(Phone, "01277249225", 30);
        retryClick(SendOTP_Button, 30);
        By Hint = By.id("CodeSentOn") ;
        driver.element().verifyThat(Hint).isVisible().perform();
    }

    @Test
    public void J_Mobile_OTPWindow_Check_that_ErrorAppear_without_insert_code()
    {
        clickForgetPassword_Button() ;
        retryClick(ForgetPasswordMobile, 30);
        retryType(Phone, "01277249225", 30);
        retryClick(SendOTP_Button, 30);

        By Verify_Button = By.id("VerifyFPCode") ;
        retryClick(Verify_Button, 30);

        By Error = By.id("FPWrongVerify") ;
        driver.element().verifyThat(Error).text().isEqualTo("Please make sure the reset password code is correct.").perform();
    }

    @Test
    public void K_Mobile_OTPWindow_Check_that_ErrorAppear_when_insert_WrongCode()
    {
        clickForgetPassword_Button() ;
        retryClick(ForgetPasswordMobile, 30);
        retryType(Phone, "01277249225", 30);
        retryClick(SendOTP_Button, 30);

        By OTP1 = By.id("digitA") ;
        By OTP2 = By.id("digitB") ;
        By OTP3 = By.id("digitC") ;
        By OTP4 = By.id("digitD") ;
        By OTP5 = By.id("digitE") ;
        By OTP6 = By.id("digitF") ;

        driver.element().type(OTP1 , "3");
        driver.element().type(OTP2 , "3");
        driver.element().type(OTP3 , "3");
        driver.element().type(OTP4 , "3");
        driver.element().type(OTP5 , "3");
        driver.element().type(OTP6 , "3");

        By Verify_Button = By.id("VerifyFPCode") ;
        retryClick(Verify_Button, 30);

        By Error = By.id("FPWrongVerify") ;
        driver.element().verifyThat(Error).text().isEqualTo("Please make sure the reset password code is correct.").perform();
    }

    @Test
    public void L_ForgetPassword_exceed3times() throws InterruptedException {
        clickForgetPassword_Button() ;
        retryClick(ForgetPasswordMobile, 30);

        By PhoneNumber_Field = By.id("UserMobileNumber");
        retryType(PhoneNumber_Field, "01277249225", 30);

        By SendCode_Button = By.id("SendCodeFP");
        retryClick(SendCode_Button, 30);//After it first code send

        By ReSend = By.id("FPReSendCode");
        Thread.sleep(70000);
        retryClick(ReSend, 30);//After it second code send

        Thread.sleep(100000);
        retryClick(ReSend, 30);//After it third code send

        Thread.sleep(100000);
        retryClick(ReSend, 30); //After it error message appear

        By Error= By.id("FPReSendErrors") ;
        driver.element().verifyThat(Error).text().isEqualTo("You have exceeded the number of allowed attempts to send reset code . Please contact customer service to complete reset password process.").perform();
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
