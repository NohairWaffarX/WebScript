package WaffarXWEB;
import com.shaft.driver.SHAFT;
import org.openqa.selenium.By;
import org.testng.annotations.*;

import java.util.UUID;

import static org.openqa.selenium.Keys.ENTER;

public class SignUP_Page
{
    SHAFT.GUI.WebDriver driver;

    @BeforeMethod
    public void setupBrowser()
    {
        driver = new SHAFT.GUI.WebDriver(); //to open browser
        driver.browser().navigateToURL("https://portal-test.waffarx.com/en-eg"); //to navigate to URL
    }

    private void clickRegisterButton()
    {
        By Register_Button = By.xpath("//*[@id='heatmapArea']/main/div[2]/div[1]/button");
        driver.element().waitToBeReady(Register_Button);
        driver.element().clickUsingJavascript(Register_Button);
    }
    ///////////////////////////////   Test cases of Name /////////////////////////////////////////
    @Test(priority = 1)
    public void Check_that_ErrorAppear_whenNameIs_SymbolsANDChars()
    {
        clickRegisterButton() ;
        By FullName = By.id("RegisterFirstName");
        By JoinNow_Button = By.xpath("//*[@id=\"SignupMob\"]/div[7]/input");

        driver.element().type(FullName, "@#$456");
        driver.element().keyPress(JoinNow_Button, ENTER);

        By Error = By.id("RegisterFirstName-error");
        driver.element().verifyThat(Error).text().isEqualTo("Full name must be characters only").perform();
    }

    @Test(priority = 2)
    public void Check_that_ErrorAppear_whenName_isEmpty()
    {
        clickRegisterButton() ;

        By FullName = By.id("RegisterFirstName");
        By JoinNow_Button = By.xpath("//*[@id=\"SignupMob\"]/div[7]/input");

        driver.element().type(FullName, " ");
        driver.element().keyPress(JoinNow_Button, ENTER);

        By Error = By.id("RegisterFirstName-error");
        driver.element().verifyThat(Error).text().isEqualTo("Full name minimum length is 3 character").perform();
    }

    @Test(priority = 3)
    public void Check_that_Name_has_max_limitation_as_50char()
    {
        clickRegisterButton() ;

        By FullName = By.id("RegisterFirstName");
        By JoinNow_Button = By.xpath("//*[@id=\"SignupMob\"]/div[7]/input");

        driver.element().type(FullName, "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");  //60Chars
        driver.element().keyPress(JoinNow_Button, ENTER);

        By Error = By.id("RegisterFirstName-error");
        driver.element().verifyThat(Error).text().isEqualTo("Full name maximum length is 50 character").perform();
    }

    @Test(priority = 4)
    public void Check_that_Name_has_min_limitation_as_3char()
    {
        clickRegisterButton() ;
        By FullName = By.id("RegisterFirstName");
        By JoinNow_Button = By.xpath("//*[@id=\"SignupMob\"]/div[7]/input");
        driver.element().type(FullName, "AA");
        driver.element().keyPress(JoinNow_Button, ENTER);
        By Error = By.id("RegisterFirstName-error");
        driver.element().verifyThat(Error).text().isEqualTo("Full name minimum length is 3 character").perform();
    }
///////////////////////////////   Test cases of Email /////////////////////////////////////////
    @Test(priority = 5)
    public void Check_that_ErrorAppear_whenEmail_isEmpty()
    {
        clickRegisterButton() ;
        By Email = By.id("RegisterEmail");
        By JoinNow_Button = By.xpath("//*[@id=\"SignupMob\"]/div[7]/input");
        driver.element().type(Email, " ");
        driver.element().keyPress(JoinNow_Button, ENTER);
        By Error = By.id("RegisterEmail-error");
        driver.element().verifyThat(Error).text().isEqualTo("The e-mail that you entered is wrong").perform();
    }

    @Test(priority = 6)
    public void Check_that_ErrorAppear_whenInsert_WrongFormat_inEmail()
    {
        clickRegisterButton() ;
        By Email = By.id("RegisterEmail");
        By JoinNow_Button = By.xpath("//*[@id=\"SignupMob\"]/div[7]/input");
        driver.element().type(Email, "nohair");
        driver.element().keyPress(JoinNow_Button, ENTER);
        By Error = By.id("RegisterEmail-error");
        driver.element().verifyThat(Error).text().isEqualTo("The e-mail that you entered is wrong").perform();
    }

    @Test(priority = 7)
    public void Check_that_ErrorAppear_whenInsert_EmailAlreadyExist()
    {
        clickRegisterButton() ;
        By FullName = By.id("RegisterFirstName");
        By Email = By.id("RegisterEmail");
        By Password = By.id("RegisterPassword");
        By ConfirmPassword = By.id("RegisterConfirmPassword");
        By JoinNow_Button = By.xpath("//*[@id=\"SignupMob\"]/div[7]/input");

        driver.element().type(FullName, "Nohair");
        driver.element().type(Email, "gnohair@gmail.com");
        driver.element().type(Password, "Qw222222");
        driver.element().type(ConfirmPassword, "Qw222222");
        driver.element().keyPress(JoinNow_Button, ENTER);

        By Error = By.xpath("//*[@id=\"heatmapArea\"]/div[17]");

        driver.element().verifyThat(Error).isVisible().perform();
    }
    ///////////////////////////////   Test cases of Password /////////////////////////////////////////
    @Test(priority = 8)
    public void Check_that_ErrorAppear_whenPasswordANDConfirmPassword_areEmpty()
    {
        clickRegisterButton() ;
        By Password = By.id("RegisterPassword");
        By ConfirmPassword = By.id("RegisterConfirmPassword");
        By JoinNow_Button = By.xpath("//*[@id=\"SignupMob\"]/div[7]/input");

        driver.element().type(Password, " ");
        driver.element().type(ConfirmPassword, " ");
        driver.element().keyPress(JoinNow_Button, ENTER);

        By PasswordError = By.id("RegisterPassword-error");
        driver.element().verifyThat(PasswordError).text().isEqualTo("Your password should be a minimum of 8 characters and a maximum of 20 characters. It should also contain the following: 1 Uppercase Letter, 1 Lowercase Letter, & 1 Number.").perform();

        By ConfirmPasswordError = By.id("RegisterConfirmPassword-error");
        driver.element().verifyThat(ConfirmPasswordError).text().isEqualTo("Confirm Password must be at least 8 characters").perform();
    }

    @Test(priority = 9)
    public void Check_that_ErrorAppear_whenPasswordANDConfirmPassword_NotMatch()
    {
        clickRegisterButton() ;
        By Password = By.id("RegisterPassword");
        By ConfirmPassword = By.id("RegisterConfirmPassword");
        By JoinNow_Button = By.xpath("//*[@id=\"SignupMob\"]/div[7]/input");

        driver.element().type(Password, "Qw222222");
        driver.element().type(ConfirmPassword, "As333333");
        driver.element().keyPress(JoinNow_Button, ENTER);

        By ConfirmPasswordError = By.id("RegisterConfirmPassword-error");
        driver.element().verifyThat(ConfirmPasswordError).text().isEqualTo("The new password and confirm password do not match.").perform();
    }

    @Test(priority = 10)
    public void Check_that_ErrorAppear_whenPassword_less_than_8chars()   //password less than 8 chars
    {
        clickRegisterButton() ;
        By Password = By.id("RegisterPassword");
        By ConfirmPassword = By.id("RegisterConfirmPassword");
        By JoinNow_Button = By.xpath("//*[@id=\"SignupMob\"]/div[7]/input");

        driver.element().type(Password, "Qw22222");
        driver.element().type(ConfirmPassword, "Qw22222");
        driver.element().keyPress(JoinNow_Button, ENTER);

        By PasswordError = By.id("RegisterPassword-error");
        driver.element().verifyThat(PasswordError).text().isEqualTo("Your password should be a minimum of 8 characters and a maximum of 20 characters. It should also contain the following: 1 Uppercase Letter, 1 Lowercase Letter, & 1 Number.").perform();
    }

    @Test(priority = 11)
    public void Check_that_ErrorAppear_whenPassword_Greater_than_20chars()   //password greater than 20 chars
    {
        clickRegisterButton() ;
        By Password = By.id("RegisterPassword");
        By ConfirmPassword = By.id("RegisterConfirmPassword");
        By JoinNow_Button = By.xpath("//*[@id=\"SignupMob\"]/div[7]/input");

        driver.element().type(Password, "Qw2222222222222222222");  //21 chars
        driver.element().type(ConfirmPassword, "Qw2222222222222222222");
        driver.element().keyPress(JoinNow_Button, ENTER);

        By PasswordError = By.id("RegisterPassword-error");
        driver.element().verifyThat(PasswordError).text().isEqualTo("Your password should be a minimum of 8 characters and a maximum of 20 characters. It should also contain the following: 1 Uppercase Letter, 1 Lowercase Letter, & 1 Number.").perform();
    }

    @Test(priority = 12)
    public void Check_that_ErrorAppear_whenPassword_notContain_small_letter()   //password don't contain small letter
    {
        clickRegisterButton() ;
        By Password = By.id("RegisterPassword");
        By ConfirmPassword = By.id("RegisterConfirmPassword");
        By JoinNow_Button = By.xpath("//*[@id=\"SignupMob\"]/div[7]/input");

        driver.element().type(Password, "QQ222222");
        driver.element().type(ConfirmPassword, "QQ222222");
        driver.element().keyPress(JoinNow_Button, ENTER);

        By PasswordError = By.id("RegisterPassword-error");
        driver.element().verifyThat(PasswordError).text().isEqualTo("Your password should be a minimum of 8 characters and a maximum of 20 characters. It should also contain the following: 1 Uppercase Letter, 1 Lowercase Letter, & 1 Number.").perform();
    }

    @Test(priority = 13)
    public void Check_that_ErrorAppear_whenPassword_notContain_Capital_letter()   //password don't contain Big letter
    {
        clickRegisterButton() ;
        By Password = By.id("RegisterPassword");
        By ConfirmPassword = By.id("RegisterConfirmPassword");
        By JoinNow_Button = By.xpath("//*[@id=\"SignupMob\"]/div[7]/input");

        driver.element().type(Password, "qq222222");
        driver.element().type(ConfirmPassword, "qq222222");
        driver.element().keyPress(JoinNow_Button, ENTER);

        By PasswordError = By.id("RegisterPassword-error");
        driver.element().verifyThat(PasswordError).text().isEqualTo("Your password should be a minimum of 8 characters and a maximum of 20 characters. It should also contain the following: 1 Uppercase Letter, 1 Lowercase Letter, & 1 Number.").perform();
    }

    @Test(priority = 14)
    public void Check_that_ErrorAppear_whenPassword_notContain_Number()   //password don't contain Number
    {
        clickRegisterButton() ;
        By Password = By.id("RegisterPassword");
        By ConfirmPassword = By.id("RegisterConfirmPassword");
        By JoinNow_Button = By.xpath("//*[@id=\"SignupMob\"]/div[7]/input");

        driver.element().type(Password, "QQwwwwww");
        driver.element().type(ConfirmPassword, "QQwwwwww");
        driver.element().keyPress(JoinNow_Button, ENTER);

        By PasswordError = By.id("RegisterPassword-error");
        driver.element().verifyThat(PasswordError).text().isEqualTo("Your password should be a minimum of 8 characters and a maximum of 20 characters. It should also contain the following: 1 Uppercase Letter, 1 Lowercase Letter, & 1 Number.").perform();

    }

    private static String getRandomEmail()
    {
        String randomUsername = UUID.randomUUID().toString().replace("-", "").substring(0, 8);
        String domain = "AutomationTest.com";
        return randomUsername + "@" + domain;
    }

    @Test(priority = 15)
    public void Check_that_SignUp_workCorrectly()
    {
        clickRegisterButton() ;
        getRandomEmail() ;

        By FullName = By.id("RegisterFirstName");
        By Email = By.id("RegisterEmail");
        By Password = By.id("RegisterPassword");
        By ConfirmPassword = By.id("RegisterConfirmPassword");
        By JoinNow_Button = By.xpath("//*[@id=\"SignupMob\"]/div[7]/input");

        driver.element().type(FullName, "Nohair");
        driver.element().type(Email, getRandomEmail());
        driver.element().type(Password, "Qw222222");
        driver.element().type(ConfirmPassword, "Qw222222");
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