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

    private void clickForgetPassword_Button()
    {
        By Register_Button = By.xpath("//*[@id='heatmapArea']/main/div[2]/div[1]/button");
        driver.element().waitToBeReady(Register_Button);
        driver.element().clickUsingJavascript(Register_Button);

        By AlreadyMember_Button = By.xpath("//*[@id='newSignUp']/div/div/div/div[4]/a") ;
        driver.element().clickUsingJavascript(AlreadyMember_Button);

        By ForgetPassword = By.linkText("Forgot Password?") ;
        driver.element().click(ForgetPassword);

        ForgetPasswordEmail = By.xpath("//*[@id=\"sendOTPOne\"]/div/div/div/button[3]") ;
        ForgetPasswordMobile = By.xpath("//*[@id=\"sendOTPOne\"]/div/div/div/button[2]") ;
        Send_Button = By.xpath("(//*[@id=\"forgetpasswordsubmit\"])[1]") ;
        EmailError = By.id("FPEmailErrors");
        ReferEmail = By.xpath("(//*[@id=\"ForgetPasswordEmail\"])[1]") ;
        SendOTP_Button = By.id("SendCodeFP") ;
        Phone = By.id("UserMobileNumber") ;
        MobileError = By.id("UserMobileNumber-error") ;

    }

    @Test(priority = 1)
    public void A_Email_Check_that_ErrorAppear_whenClickSend_withoutInsertData()
    {
        clickForgetPassword_Button() ;
        driver.element().click(ForgetPasswordEmail);
        driver.element().click(Send_Button);
        driver.element().verifyThat(EmailError).text().isEqualTo("The e-mail that you entered is wrong").perform();
    }

    @Test(priority = 2)
    public void B_Email_Check_that_ErrorAppear_whenInsert_WrongFormat_inEmail()
    {
        clickForgetPassword_Button() ;
        driver.element().click(ForgetPasswordEmail);
        driver.element().type(ReferEmail , "gnohair");
        driver.element().click(Send_Button);
        driver.element().verifyThat(EmailError).text().isEqualTo("The e-mail that you entered is wrong").perform();
    }

    @Test(priority = 3)
    public void C_Email_Check_that_ErrorAppear_when_InsertEmail_notFound_inWaffarX()
    {
        clickForgetPassword_Button() ;
        driver.element().click(ForgetPasswordEmail);
        driver.element().type(ReferEmail , "nohairrrr@nohairrrr.com");
        driver.element().click(Send_Button);
        driver.element().verifyThat(EmailError).text().isEqualTo("This email is not registered at WaffarX.").perform();
    }

    @Test(priority = 4)
    public void D_Email_Check_that_System_TerminateSpaces()
    {
        clickForgetPassword_Button() ;
        driver.element().click(ForgetPasswordEmail);
        driver.element().type(ReferEmail , "      gnohair@gmail.com    ");
        driver.element().click(Send_Button);
        By Register_Button = By.xpath("//*[@id='heatmapArea']/main/div[2]/div[1]/button");
        driver.element().verifyThat(Register_Button).isVisible().perform(); ;
    }

    @Test(priority = 5)
    public void E_Mobile_Check_that_ErrorAppear_whenClickSend_withoutInsertData()
    {
        clickForgetPassword_Button();
        driver.element().click(ForgetPasswordMobile);
        driver.element().click(SendOTP_Button);
        driver.element().verifyThat(MobileError).text().isEqualTo("This field is required.").perform();
    }

    @Test(priority = 6)
    public void F_Mobile_Check_that_ErrorAppear_when_insert_LessThan_MinLimitation_inPhone()
    {
        clickForgetPassword_Button() ;
        driver.element().click(ForgetPasswordMobile);
        driver.element().type(Phone , "010678");
        driver.element().click(SendOTP_Button);
        driver.element().verifyThat(MobileError).text().isEqualTo("Sorry, Arabic and special characters are not allowed, please make sure you enter a valid input.").perform();
    }
    @Test(priority = 7)
    public void G_Mobile_Check_that_ErrorAppear_when_insert_GreaterThan_MaxLimitation_inPhone()
    {
        clickForgetPassword_Button() ;
        driver.element().click(ForgetPasswordMobile);
        driver.element().type(Phone , "010782378237823723873");
        driver.element().click(SendOTP_Button);
        driver.element().verifyThat(MobileError).text().isEqualTo("Please make sure that phone number is true, contains only numbers and consist of 11 number.").perform();
    }

    @Test(priority = 8)
    public void H_Mobile_Check_that_ErrorAppear_when_insertNumber_NotJoin_inWaffarX()
    {
        clickForgetPassword_Button() ;
        driver.element().click(ForgetPasswordMobile);
        driver.element().type(Phone , "01552153666");
        driver.element().click(SendOTP_Button);
        By Error = By.id("FPErrors") ;
        driver.element().verifyThat(Error).text().isEqualTo("This mobile number not verified at waffarx!").perform();
    }

    @Test(priority = 9)
    public void I_Mobile_Check_that_OTP_sendCorrectlyWhen_insertCorrectNumber()
    {
        clickForgetPassword_Button() ;
        driver.element().click(ForgetPasswordMobile);
        driver.element().type(Phone , "01277249447");
        driver.element().click(SendOTP_Button);
        By Hint = By.id("CodeSentOn") ;
        driver.element().verifyThat(Hint).isVisible().perform();
    }

    @Test(priority = 10)
    public void J_Mobile_OTPWindow_Check_that_ErrorAppear_without_insert_code()
    {
        clickForgetPassword_Button() ;
        driver.element().click(ForgetPasswordMobile);
        driver.element().type(Phone , "01277249447");
        driver.element().click(SendOTP_Button);
        By Verify_Button = By.id("VerifyFPCode") ;
        driver.element().click(Verify_Button);
        By Error = By.id("FPWrongVerify") ;
        driver.element().verifyThat(Error).text().isEqualTo("Please make sure the reset password code is correct.").perform();
    }

    @Test(priority = 11)
    public void K_Mobile_OTPWindow_Check_that_ErrorAppear_when_insert_WrongCode()
    {
        clickForgetPassword_Button() ;
        driver.element().click(ForgetPasswordMobile);
        driver.element().type(Phone , "01277249447");
        driver.element().click(SendOTP_Button);

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
        driver.element().click(Verify_Button);

        By Error = By.id("FPWrongVerify") ;
        driver.element().verifyThat(Error).text().isEqualTo("Please make sure the reset password code is correct.").perform();
    }

    @Test(priority = 12)
    public void L_ForgetPassword_exceed3times() throws InterruptedException {
        clickForgetPassword_Button() ;
        driver.element().click(ForgetPasswordMobile);

        By PhoneNumber_Field = By.id("UserMobileNumber");
        driver.element().type(PhoneNumber_Field, "01277249447");

        By SendCode_Button = By.id("SendCodeFP");
        driver.element().click(SendCode_Button);

        By ReSend = By.id("FPReSendCode");
        Thread.sleep(70000);
        driver.element().click(ReSend);

        Thread.sleep(100000);
        driver.element().click(ReSend) ;

        Thread.sleep(100000);
        driver.element().click(ReSend) ;

        Thread.sleep(100000);
        driver.element().click(ReSend) ;

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