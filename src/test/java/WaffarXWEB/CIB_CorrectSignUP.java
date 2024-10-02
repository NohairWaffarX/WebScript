package WaffarXWEB;
import com.shaft.driver.SHAFT;
import org.openqa.selenium.By;
import org.testng.annotations.*;
import java.util.UUID;
import static org.openqa.selenium.Keys.ENTER;

public class CIB_CorrectSignUP
{
    SHAFT.GUI.WebDriver driver;
    By FullName ,Email , Password , ConfirmPassword,JoinNow_Button, PasswordError, NameError,EmailError ,
            ConfirmPasswordError , CloseBrowser_extension ;
    @BeforeMethod
    public void setupBrowser()
    {
        driver = new SHAFT.GUI.WebDriver();
        driver.browser().navigateToURL("https://portal-test.waffarx.com/en-eg");
    }

    private void clickCIBButton() {
        By Register_Button = By.xpath("//*[@id='heatmapArea']/main/div[2]/div[1]/button");
        driver.element().waitToBeReady(Register_Button);
        driver.element().clickUsingJavascript(Register_Button);

        By CIB_Button = By.xpath("//*[@id=\"newSignUp\"]/div/div/div/div[1]/a[2]") ;
        driver.element().click(CIB_Button);

        CloseBrowser_extension = By.xpath("//*[@id=\"closeAds\"]/i") ;
        driver.element().clickUsingJavascript(CloseBrowser_extension);

        By Next = By.className("cib-btn") ;
        driver.element().click(Next);
        driver.element().clickUsingJavascript(CloseBrowser_extension);
        driver.element().click(Next);

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

    private static String getRandomEmail()
    {
        String randomUsername = UUID.randomUUID().toString().replace("-", "").substring(0, 8);
        String domain = "AutomationTest.com";
        return randomUsername + "@" + domain;
    }

    @Test(priority = 1)
    public void Check_that_SignUp_workCorrectly()   //password don't contain Number
    {
        clickCIBButton() ;
        driver.element().type(FullName, "Nohair");
        driver.element().type(Email, getRandomEmail());
        driver.element().type(Password, "Qw222222");
        driver.element().type(ConfirmPassword, "Qw222222");
        driver.element().keyPress(JoinNow_Button, ENTER);

        driver.element().clickUsingJavascript(CloseBrowser_extension);

        By AddCard = By.xpath("//*[@id=\"AddCardSubmit\"]/div[1]/input");
        driver.element().scrollToElement(AddCard);
        driver.element().click(AddCard) ;

        By CardNumber = By.xpath("//*[@id=\"paymob_checkout\"]/div[1]/input");
        driver.element().type(CardNumber , "5264395882609963") ;

//        By AddCard = By.xpath("//*[@id=\"AddCardSubmit\"]/div[1]/input");
//        driver.element().click(AddCard) ;
//
//        By AddCard = By.xpath("//*[@id=\"AddCardSubmit\"]/div[1]/input");
//        driver.element().click(AddCard) ;
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
