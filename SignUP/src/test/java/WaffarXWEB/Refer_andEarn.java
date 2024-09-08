package WaffarXWEB;
import com.shaft.driver.SHAFT;
import org.openqa.selenium.By;
import org.testng.annotations.*;
import static org.openqa.selenium.Keys.ENTER;

public class Refer_andEarn
{
    SHAFT.GUI.WebDriver driver;

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
    }

    @Test(priority = 1)
    public void Check_that_ErrorAppear_whenEmail_IsEmpty()
    {
        Open_Refer_Page() ;
        By Email = By.id("toEmail");
        driver.element().type(Email, " ");

        By Send_Invitation_button = By.id("sendReferral");
        driver.element().click(Send_Invitation_button) ;

       // By Error = By.xpath("//*[@id=\"heatmapArea\"]/div[12]/p");
        By Error = By.className("text-muted") ;
        driver.element().verifyThat(Error).text().isEqualTo("Please enter a valid email address").perform();
    }

    @Test(priority = 2)
    public void Check_that_ErrorAppear_WhenInsert_WrongFormat_InEmail()
    {
        Open_Refer_Page() ;
        By Email = By.id("toEmail");
        driver.element().type(Email, "jkjkdj");

        By Send_Invitation_button = By.id("sendReferral");
        driver.element().click(Send_Invitation_button) ;

       // By Error = By.xpath("//*[@id=\"heatmapArea\"]/div[12]/p");
        By Error = By.className("text-muted") ;
        driver.element().verifyThat(Error).text().isEqualTo("Please enter a valid email address").perform();
    }

    @Test(priority = 3)
    public void Check_that_ErrorAppear_WhenRefer_yourself()
    {
        Open_Refer_Page() ;
        By Email = By.id("toEmail");
        driver.element().type(Email, "gnohair@gmail.com");

        By Send_Invitation_button = By.id("sendReferral");
        driver.element().click(Send_Invitation_button) ;

        //By Error = By.xpath("//*[@id=\"heatmapArea\"]/div[12]/p") ;
        By Error = By.className("text-muted") ;
        driver.element().verifyThat(Error).text().isEqualTo("You cannot refer yourself.").perform();
    }

    @Test(priority = 4)
    public void Check_that_ErrorAppear_WhenRefer_Mail_already_InWaffarX()
    {
        Open_Refer_Page() ;
        By Email = By.id("toEmail");
        driver.element().type(Email, "mg55851@gmail.com");

        By Send_Invitation_button = By.id("sendReferral");
        driver.element().click(Send_Invitation_button) ;

        By Error = By.className("text-muted") ;
        driver.element().verifyThat(Error).text().isEqualTo("This email is already registered.").perform();
    }

    @Test(priority = 5)
    public void Check_that_ErrorAppear_WhenMail_already_referred()
    {
        Open_Refer_Page() ;
        By Email = By.id("toEmail");
        driver.element().type(Email, "j23134263@gmail.com");

        By Send_Invitation_button = By.id("sendReferral");
        driver.element().click(Send_Invitation_button) ;

        By Error = By.className("text-muted") ;
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