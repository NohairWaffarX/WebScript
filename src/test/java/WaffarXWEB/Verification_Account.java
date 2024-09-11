package WaffarXWEB;
import com.shaft.driver.SHAFT;
import org.openqa.selenium.By;
import org.testng.annotations.*;

import static org.bouncycastle.its.ITSValidityPeriod.Unit.seconds;
import static org.openqa.selenium.Keys.ENTER;

public class Verification_Account
{
    SHAFT.GUI.WebDriver driver;

    @BeforeMethod
    public void setupBrowser()
    {
        driver = new SHAFT.GUI.WebDriver(); // to open browser
        driver.browser().navigateToURL("https://www.waffarx.com/en-eg"); // to navigate to URL
    }

    private void Open_PopupOF_VerifyAccount()
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

        By Verify_Account_Button = By.xpath("//*[@id=\"verify-Banner\"]/p/button") ;
        driver.element().click(Verify_Account_Button) ;
    }

    @Test(priority = 1)
    public void Check_that_ErrorAppear_whenInsert_Number_AlreadyUsed()
    {
        Open_PopupOF_VerifyAccount() ;

        By PhoneNumber_Field = By.id("phone");
        driver.element().type(PhoneNumber_Field, "01277249447");

        By SendCode_Button = By.id("SendCode");
        driver.element().click(SendCode_Button);

        By Error= By.className("ExistedMobNumber") ;
        driver.element().verifyThat(Error).text().isEqualTo("This mobile number has already been registered.").perform();
    }

    @Test(priority = 2)
    public void Check_that_ErrorAppear_whenInsert_LessThan_MinLimitation_InMobileNumber()
    {
        Open_PopupOF_VerifyAccount() ;

        By PhoneNumber_Field = By.id("phone");
        driver.element().type(PhoneNumber_Field, "012");

        By SendCode_Button = By.id("SendCode");
        driver.element().click(SendCode_Button);

        By Error= By.id("phone-error") ;
        driver.element().verifyThat(Error).text().isEqualTo("Sorry, Arabic and special characters are not allowed, please make sure you enter a valid input.").perform();
    }

    @Test(priority = 3)
    public void Check_that_ErrorAppear_whenInsert_GreaterThan_MaxLimitation_InMobileNumber()
    {
        Open_PopupOF_VerifyAccount() ;

        By PhoneNumber_Field = By.id("phone");
        driver.element().type(PhoneNumber_Field, "0127724999999999888");

        By SendCode_Button = By.id("SendCode");
        driver.element().click(SendCode_Button);

        By Error= By.id("WrongEgMobNumber") ;
        driver.element().verifyThat(Error).text().isEqualTo("Please make sure that phone number is true, contains only numbers and consist of 11 number.").perform();

    }
    @Test(priority = 4)
    public void Verify_Account_exceed3times() throws InterruptedException {
        Open_PopupOF_VerifyAccount() ;

        By PhoneNumber_Field = By.id("phone");
        driver.element().type(PhoneNumber_Field, "01067802082");

        By SendCode_Button = By.id("SendCode");
        driver.element().click(SendCode_Button);

        By ReSend = By.id("ReSendCode");
        Thread.sleep(70000);
        driver.element().click(ReSend);

        Thread.sleep(100000);
        driver.element().click(ReSend) ;

        Thread.sleep(100000);
        driver.element().click(ReSend) ;

        Thread.sleep(100000);
        driver.element().click(ReSend) ;

        By Error= By.id("ExceedCount") ;
        driver.element().verifyThat(Error).text().isEqualTo("You exceed the number of times the activation code has been sent, please contact customer service to complete the activation process").perform();
    }

    @Test(priority = 5 , dependsOnMethods = { "Verify_Account_exceed3times" })
    public void Check_that_ErrorAppear_whenInsert_WrongVerificationCode() {
        Open_PopupOF_VerifyAccount();

        By VerificationCode_Text = By.xpath("(//*[@id='VerificationCode'])[2]");
        driver.element().type(VerificationCode_Text, "88888");

        By VerifyAccount_Button = By.xpath("//*[@id=\"VerifyUserPhoneNumber\"]/input");
        driver.element().click(VerifyAccount_Button);

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