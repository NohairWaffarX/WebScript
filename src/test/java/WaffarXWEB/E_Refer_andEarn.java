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

    private void ensureElementReady(By locator) {
        driver.element().waitToBeReady(locator);
    }

    private void retryClick(By locator, int maxRetries) {
        int attempt = 0;
        while (attempt < maxRetries) {
            try {
                ensureElementReady(locator);
                driver.element().click(locator);
                System.out.println("Successfully clicked the element on attempt " + (attempt + 1));
                return; // Exit the method if click is successful
            } catch (org.openqa.selenium.ElementClickInterceptedException e) {
                System.out.println("Click intercepted on attempt " + (attempt + 1) + ". Retrying...");
                attempt++;
                try {
                    Thread.sleep(800); // Wait before retrying (500 ms)
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
                ensureElementReady(locator);
                driver.element().type(locator, text);
                System.out.println("Successfully typed into the element on attempt " + (attempt + 1));
                return; // Exit the method if typing is successful
            } catch (org.openqa.selenium.ElementNotInteractableException e) {
                System.out.println("Element not interactable on attempt " + (attempt + 1) + ". Retrying...");
                attempt++;
                try {
                    Thread.sleep(800); // Wait before retrying (500 ms)
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
        retryType(Email, "gnohair@gmail.com", 30);

        By Password = By.id("LoginPassword");
        retryType(Password, "Ng555555", 30);

        By SignIN_Button = By.xpath("//*[@id=\"Login\"]/div[4]/input");
        driver.element().keyPress(SignIN_Button, ENTER);

        By Refer_button = By.linkText("Refer & Earn") ;
        retryClick(Refer_button, 30);

        UserEmail = By.id("toEmail");
        Send_Invitation_button = By.id("sendReferral");
        Error = By.className("text-muted") ;
    }

    @Test
    public void A_Check_that_ErrorAppear_whenEmail_IsEmpty()
    {
        Open_Refer_Page() ;
        retryType(UserEmail, " ", 30);
        retryClick(Send_Invitation_button, 30);
        driver.element().verifyThat(Error).text().isEqualTo("Please enter a valid email address").perform();
    }

    @Test
    public void B_Check_that_ErrorAppear_WhenInsert_WrongFormat_InEmail()
    {
        Open_Refer_Page() ;
        retryType(UserEmail, "jkjkdj", 30);
        retryClick(Send_Invitation_button, 30);
        driver.element().verifyThat(Error).text().isEqualTo("Please enter a valid email address").perform();
    }

    @Test
    public void C_Check_that_ErrorAppear_WhenRefer_yourself()
    {
        Open_Refer_Page() ;
        retryType(UserEmail, "gnohair@gmail.com", 30);
        retryClick(Send_Invitation_button, 30);
        driver.element().verifyThat(Error).text().isEqualTo("You cannot refer yourself.").perform();
    }

    @Test
    public void D_Check_that_ErrorAppear_WhenRefer_Mail_already_InWaffarX()
    {
        Open_Refer_Page() ;
        retryType(UserEmail, "mg55851@gmail.com", 30);
        retryClick(Send_Invitation_button, 30);
        driver.element().verifyThat(Error).text().isEqualTo("This email is already registered.").perform();
    }

    @Test
    public void D_Check_that_ErrorAppear_WhenMail_already_referred()
    {
        Open_Refer_Page() ;
        retryType(UserEmail, "j23134263@gmail.com", 30);
        retryClick(Send_Invitation_button, 30);
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
