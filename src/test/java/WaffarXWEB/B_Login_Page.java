package WaffarXWEB;
import com.shaft.driver.SHAFT;
import org.openqa.selenium.By;
import org.testng.annotations.*;
import static org.openqa.selenium.Keys.ENTER;

public class B_Login_Page
{
    SHAFT.GUI.WebDriver driver;
    By Email ,Password,Error,SignIN_Button;
    @BeforeMethod
    public void setupBrowser()
    {
        driver = new SHAFT.GUI.WebDriver(); // to open browser
        driver.browser().navigateToURL("https://www.waffarx.com/en-eg"); // to navigate to URL
    }

    private void clickAlreadyMemberButton()
    {
        By Register_Button = By.xpath("//*[@id='heatmapArea']/main/div[2]/div[1]/button");
        driver.element().waitToBeReady(Register_Button);
        driver.element().clickUsingJavascript(Register_Button);

        By AlreadyMember_Button = By.xpath("//*[@id='newSignUp']/div/div/div/div[4]/a") ;
        driver.element().clickUsingJavascript(AlreadyMember_Button); // To force click on this element
        Email = By.id("LoginEmail");
        Password = By.id("LoginPassword");
        Error = By.className("lead") ;
        SignIN_Button = By.xpath("//*[@id=\"Login\"]/div[4]/input");
    }

    @Test
    public void A_Check_that_ErrorAppear_whenInsert_WrongEmail_and_CorrectPassword()
    {
        clickAlreadyMemberButton() ;
        driver.element().type(Email, "gnohairrr@gmail.com");
        driver.element().type(Password, "Ng555555");
        driver.element().keyPress(SignIN_Button, ENTER);
        driver.element().verifyThat(Error).text().isEqualTo("Wrong Username Or Password").perform();
    }

    @Test
    public void B_Check_that_ErrorAppear_whenInsert_CorrectEmail_and_WrongPassword()
    {
        clickAlreadyMemberButton() ;
        driver.element().type(Email, "gnohair@gmail.com");
        driver.element().type(Password, "Qw22222");
        driver.element().keyPress(SignIN_Button, ENTER);
        driver.element().verifyThat(Error).text().isEqualTo("Wrong Username Or Password").perform();
    }

    @Test
    public void C_Check_that_Login_workCorrectly()
    {
        clickAlreadyMemberButton() ;
        driver.element().type(Email, "gnohair@gmail.com");
        driver.element().type(Password, "Ng555555");
        driver.element().keyPress(SignIN_Button, ENTER);

        By Search_text = By.id("searchtext");
        driver.element().verifyThat(Search_text).isVisible().perform();
    }

    @Test
    public void D_Check_that_ErrorAppear_whenInsert_WrongFormat_inEmail()
    {
        clickAlreadyMemberButton() ;
        driver.element().type(Email, "gnohair@");
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
