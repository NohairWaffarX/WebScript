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
    private void retryClick(By locator, int maxRetries) {
        int attempt = 0;
        while (attempt < maxRetries) {
            try {
                driver.element().click(locator);
                System.out.println("Successfully clicked the element on attempt " + (attempt + 1));
                return; // Exit the method if click is successful
            } catch (org.openqa.selenium.ElementClickInterceptedException e) {
                System.out.println("Click intercepted on attempt " + (attempt + 1) + ". Retrying...");
                attempt++;
                try {
                    Thread.sleep(3000);
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
                driver.element().type(locator, text);
                System.out.println("Successfully typed into the element on attempt " + (attempt + 1));
                return; // Exit the method if typing is successful
            } catch (org.openqa.selenium.ElementNotInteractableException e) {
                System.out.println("Element not interactable on attempt " + (attempt + 1) + ". Retrying...");
                attempt++;
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                    throw new RuntimeException("Interrupted while waiting to retry type.", ie);
                }
            }
        }
        throw new RuntimeException("Failed to type into the element after " + maxRetries + " attempts.");
    }
    private void Open_Refer_Page()
    {
        By Register_Button = By.xpath("//*[@id='heatmapArea']/main/div[2]/div[1]/button");
        driver.element().waitToBeReady(Register_Button);
        driver.element().clickUsingJavascript(Register_Button);

        By AlreadyMember_Button = By.xpath("//*[@id='newSignUp']/div/div/div/div[4]/a") ;
        driver.element().clickUsingJavascript(AlreadyMember_Button);

        By Email = By.id("LoginEmail");
        retryType(Email, "gnohair@gmail.com", 40);

        By Password = By.id("LoginPassword");
        retryType(Password, "Ng555555", 40);

        By SignIN_Button = By.xpath("//*[@id=\"Login\"]/div[4]/input");
        driver.element().keyPress(SignIN_Button, ENTER);

        By Refer_button = By.linkText("Refer & Earn") ;
        retryClick(Refer_button, 40);

        UserEmail = By.id("toEmail");
        Send_Invitation_button = By.id("sendReferral");
        Error = By.className("text-muted") ;
    }

    @Test
    public void A_Check_that_ErrorAppear_whenEmail_IsEmpty()
    {
        Open_Refer_Page() ;
        retryType(UserEmail, " ", 40);
        retryClick(Send_Invitation_button, 40);
        driver.element().verifyThat(Error).text().isEqualTo("Please enter a valid email address").perform();
    }

    @Test
    public void B_Check_that_ErrorAppear_WhenInsert_WrongFormat_InEmail()
    {
        Open_Refer_Page() ;
        retryType(UserEmail, "jkjkdj", 40);
        retryClick(Send_Invitation_button, 40);
        driver.element().verifyThat(Error).text().isEqualTo("Please enter a valid email address").perform();
    }

    @Test
    public void C_Check_that_ErrorAppear_WhenRefer_yourself()
    {
        Open_Refer_Page() ;
        retryType(UserEmail, "gnohair@gmail.com", 40);
        retryClick(Send_Invitation_button, 40);
        driver.element().verifyThat(Error).text().isEqualTo("You cannot refer yourself.").perform();
    }

    @Test
    public void D_Check_that_ErrorAppear_WhenRefer_Mail_already_InWaffarX()
    {
        Open_Refer_Page() ;
        retryType(UserEmail, "mg55851@gmail.com", 40);
        retryClick(Send_Invitation_button, 40);
        driver.element().verifyThat(Error).text().isEqualTo("This email is already registered.").perform();
    }

    @Test
    public void D_Check_that_ErrorAppear_WhenMail_already_referred()
    {
        Open_Refer_Page() ;
        retryType(UserEmail, "j23134263@gmail.com", 40);
        retryClick(Send_Invitation_button, 40);
        driver.element().verifyThat(Error).text().isEqualTo("This user has already been referred.").perform();
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
