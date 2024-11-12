package WaffarXWEB;
import com.shaft.driver.SHAFT;
import org.openqa.selenium.By;
import org.testng.annotations.*;
import static org.openqa.selenium.Keys.ENTER;

public class F_Verification_Account
{
    SHAFT.GUI.WebDriver driver;
    By SendCode_Button ;
    @BeforeMethod
    public void setupBrowser()
    {
        driver = new SHAFT.GUI.WebDriver(); // to open browser
        driver.browser().navigateToURL("https://www.waffarx.com/en-eg"); // to navigate to URL
    }

    private void Open_PopupOF_VerifyAccount()
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

        By Verify_Account_Button = By.xpath("//*[@id=\"verify-Banner\"]/p/button") ;
        driver.element().clickUsingJavascript(Verify_Account_Button) ;

        SendCode_Button = By.id("SendCode");
    }

    // @Test
    // public void AA_Check_that_ErrorAppear_whenInsert_Number_AlreadyUsed()
    // {
    //     PhoneNumber_Field = By.id("phone");
    //     SendCode_Button = By.id("SendCode");
    //     Open_PopupOF_VerifyAccount() ;
    //     driver.element().type(PhoneNumber_Field, "01277249225");
    //     driver.element().click(SendCode_Button);
    //     By Error= By.className("ExistedMobNumber") ;
    //     driver.element().verifyThat(Error).text().isEqualTo("This mobile number has already been registered.").perform();
    // }

    // @Test
    // public void B_Check_that_ErrorAppear_whenInsert_LessThan_MinLimitation_InMobileNumber()
    // {
    //     PhoneNumber_Field = By.id("phone");
    //     SendCode_Button = By.id("SendCode");
    //     Open_PopupOF_VerifyAccount() ;
    //     driver.element().type(PhoneNumber_Field, "012");
    //     driver.element().click(SendCode_Button);
    //     By Error= By.id("phone-error");
    //     driver.element().verifyThat(Error).text().isEqualTo("Sorry, Arabic and special characters are not allowed, please make sure you enter a valid input.").perform();
    // }

    // @Test
    // public void C_Check_that_ErrorAppear_whenInsert_GreaterThan_MaxLimitation_InMobileNumber()
    // {
    //     PhoneNumber_Field = By.id("phone");
    //     SendCode_Button = By.id("SendCode");
    //     Open_PopupOF_VerifyAccount() ;
    //     driver.element().type(PhoneNumber_Field, "0127724999999999888");
    //     driver.element().click(SendCode_Button);
    //     By Error= By.id("WrongEgMobNumber");
    //     driver.element().verifyThat(Error).text().isEqualTo("Please make sure that phone number is true, contains only numbers and consist of 11 number.").perform();
    // }
    @Test
    public void D_Verify_Account_exceed3times() throws InterruptedException {
        Open_PopupOF_VerifyAccount() ;
        By PhoneNumber_Field = By.id("phone");
        Thread.sleep(5000);
        driver.element().type(PhoneNumber_Field, "01067802082");
        driver.element().click(SendCode_Button);  // After click on it , First otp send

        By ReSend = By.id("ReSendCode");
        Thread.sleep(140000);
        driver.element().click(ReSend);  // After click on it , Second otp send

        Thread.sleep(140000);
        driver.element().click(ReSend) ; // After click on it , third otp send

        Thread.sleep(140000);
        driver.element().click(ReSend) ;// After click on it , Error Message appear

        By Error= By.id("ExceedCount") ;
        driver.element().verifyThat(Error).text().isEqualTo("You exceed the number of times the activation code has been sent, please contact customer service to complete the activation process").perform();
    }

    // @Test(dependsOnMethods = { "D_Verify_Account_exceed3times" })
    // public void E_Check_that_ErrorAppear_whenInsert_WrongVerificationCode() {
    //     Open_PopupOF_VerifyAccount();

    //     By VerificationCode_Text = By.xpath("(//*[@id='VerificationCode'])[2]");
    //     driver.element().type(VerificationCode_Text, "88888");

    //     By VerifyAccount_Button = By.xpath("//*[@id=\"VerifyUserPhoneNumber\"]/input");
    //     driver.element().click(VerifyAccount_Button);

    //     By Error = By.xpath("//*[@id=\"WrongVerify\"]");
    //     driver.element().verifyThat(Error).text().isEqualTo("Invalid verification Code!").perform();
    // }
    @AfterMethod
    public void CloseDriver()
    {
        if (driver != null)
        {
            driver.quit();
        }
    }
}
