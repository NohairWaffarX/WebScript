package WaffarXWEB;
import com.shaft.driver.SHAFT;
import org.openqa.selenium.By;
import org.testng.annotations.*;
import static org.openqa.selenium.Keys.ENTER;

public class Login_Page
{
    SHAFT.GUI.WebDriver driver;

    @BeforeMethod
    public void setupBrowser()
    {
        driver = new SHAFT.GUI.WebDriver(); // to open browser
        driver.browser().navigateToURL("https://www.waffarx.com/en-eg"); // to navigate to URL
    }

    private void clickAlreadyMemberButton()
    {
        By Register_Button = By.xpath("//*[@id='heatmapArea']/main/div[2]/div[1]/button");
        driver.element().waitToBeReady(Register_Button); // Wait for the button to be clickable
        driver.element().clickUsingJavascript(Register_Button); // To force click on this element

        By AlreadyMember_Button = By.xpath("//*[@id='newSignUp']/div/div/div/div[4]/a") ;
        driver.element().clickUsingJavascript(AlreadyMember_Button); // To force click on this element
    }

    @Test(priority = 1)
    public void Check_that_ErrorAppear_whenInsert_WrongEmail_and_CorrectPassword()
    {
        clickAlreadyMemberButton() ;

        By Email = By.id("LoginEmail");
        driver.element().type(Email, "gnohairrr@gmail.com");

        By Password = By.id("LoginPassword");
        driver.element().type(Password, "Ng555555");

        By SignIN_Button = By.xpath("//*[@id=\"Login\"]/div[4]/input");
        driver.element().keyPress(SignIN_Button, ENTER);

        By Error = By.className("lead") ;
        driver.element().verifyThat(Error).text().isEqualTo("Wrong Username Or Password").perform();
        }

    @Test(priority = 2)
    public void Check_that_ErrorAppear_whenInsert_CorrectEmail_and_WrongPassword()
    {
        clickAlreadyMemberButton() ;

        By Email = By.id("LoginEmail");
        driver.element().type(Email, "gnohair@gmail.com");

        By Password = By.id("LoginPassword");
        driver.element().type(Password, "Qw22222");

        By SignIN_Button = By.xpath("//*[@id=\"Login\"]/div[4]/input");
        driver.element().keyPress(SignIN_Button, ENTER);

        By Error = By.className("lead") ;
        driver.element().verifyThat(Error).text().isEqualTo("Wrong Username Or Password").perform();
    }

    @Test(priority = 3)
    public void Check_that_Login_workCorrectly()
    {
        clickAlreadyMemberButton() ;

        By Email = By.id("LoginEmail");
        driver.element().type(Email, "gnohair@gmail.com");

        By Password = By.id("LoginPassword");
        driver.element().type(Password, "Ng555555");

        By SignIN_Button = By.xpath("//*[@id=\"Login\"]/div[4]/input");
        driver.element().keyPress(SignIN_Button, ENTER);

        By Search_text = By.id("searchtext");
        driver.element().verifyThat(Search_text).isVisible().perform();
    }

    @Test(priority = 4)
    public void Check_that_ErrorAppear_whenInsert_WrongFormat_inEmail()
    {
        clickAlreadyMemberButton() ;
        By Email = By.id("LoginEmail");
        driver.element().type(Email, "gnohair@");

        By SignIN_Button = By.xpath("//*[@id=\"Login\"]/div[4]/input");
        driver.element().keyPress(SignIN_Button, ENTER);
        driver.element().verifyThat(SignIN_Button).isVisible().perform();
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