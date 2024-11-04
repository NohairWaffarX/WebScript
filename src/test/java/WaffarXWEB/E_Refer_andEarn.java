package WaffarXWEB;
import com.shaft.driver.SHAFT;
import org.openqa.selenium.By;
import org.testng.annotations.*;
import static org.openqa.selenium.Keys.ENTER;

public class E_Refer_andEarn
{
    SHAFT.GUI.WebDriver driver;
    By UserEmail ,Send_Invitation_button , Error;
    @BeforeMethod
    public void setupBrowser()
    {
        driver = new SHAFT.GUI.WebDriver();
        driver.browser().navigateToURL("https://www.waffarx.com/en-eg");
    }

    private void Open_Refer_Page()
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

        By Refer_button = By.linkText("Refer & Earn") ;
        driver.element().click(Refer_button) ;

        UserEmail = By.id("toEmail");
        Send_Invitation_button = By.id("sendReferral");
        Error = By.className("text-muted") ;
    }

    @Test
    public void A_Check_that_ErrorAppear_whenEmail_IsEmpty()
    {
        Open_Refer_Page() ;
        driver.element().type(UserEmail, " ");
        driver.element().click(Send_Invitation_button) ;
        driver.element().verifyThat(Error).text().isEqualTo("Please enter a valid email address").perform();
    }

    @Test
    public void B_Check_that_ErrorAppear_WhenInsert_WrongFormat_InEmail()
    {
        Open_Refer_Page() ;
        driver.element().type(UserEmail, "jkjkdj");
        driver.element().click(Send_Invitation_button) ;
        driver.element().verifyThat(Error).text().isEqualTo("Please enter a valid email address").perform();
    }

    @Test
    public void C_Check_that_ErrorAppear_WhenRefer_yourself()
    {
        Open_Refer_Page() ;
        driver.element().type(UserEmail, "gnohair@gmail.com");
        driver.element().click(Send_Invitation_button) ;
        driver.element().verifyThat(Error).text().isEqualTo("You cannot refer yourself.").perform();
    }

    @Test
    public void D_Check_that_ErrorAppear_WhenRefer_Mail_already_InWaffarX()
    {
        Open_Refer_Page() ;
        driver.element().type(UserEmail, "mg55851@gmail.com");
        driver.element().click(Send_Invitation_button) ;
        driver.element().verifyThat(Error).text().isEqualTo("This email is already registered.").perform();
    }

    @Test
    public void D_Check_that_ErrorAppear_WhenMail_already_referred()
    {
        Open_Refer_Page() ;
        driver.element().type(UserEmail, "j23134263@gmail.com");
        driver.element().click(Send_Invitation_button) ;
        driver.element().verifyThat(Error).text().isEqualTo("This user has already been referred.").perform();
    }

//    @Test(priority = 6)
//    public void Check_that_Refer_WorkCorrectly()
//    {
//        Open_Refer_Page() ;
////        By Email = By.id("toEmail");
////        driver.element().type(Email, "refer4565@gmail.com");
////
////        By Send_Invitation_button = By.id("sendReferral");
////        driver.element().click(Send_Invitation_button) ;
////
////        By message = By.className("text-muted") ;
////        driver.element().verifyThat(message).text().isEqualTo("Email sent successfully").perform();
////
////        By OK = By.className("confirm") ;
////        driver.element().click(OK) ;
////
////        driver.browser().refreshCurrentPage();
//        By invited_Tab = By.xpath("//*[@id=\"heatmapArea\"]/main/div/div[3]/div/div/div/ul/li[1]/a");
//        driver.element().scrollToElement(invited_Tab);
//        System.out.print("ok");
////        By EmailFound = By.xpath("//*[@id=\"invited\"]/p[1]/b/text()[1]");
////        driver.element().verifyThat(EmailFound).isVisible().perform();
//    }

    @AfterMethod
    public void CloseDriver()
    {
        if (driver != null)
        {
            driver.quit();
        }
    }
}
