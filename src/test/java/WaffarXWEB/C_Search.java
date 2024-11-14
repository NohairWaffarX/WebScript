package WaffarXWEB;
import com.shaft.driver.SHAFT;
import org.openqa.selenium.By;
import org.testng.annotations.*;
import static org.openqa.selenium.Keys.ENTER;

public class C_Search
{
    SHAFT.GUI.WebDriver driver;
    By Search_text, Search_Button;
    @BeforeMethod
    public void setupBrowser()
    {
        driver = new SHAFT.GUI.WebDriver(); // to open browser
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
                    Thread.sleep(500); // Wait before retrying (500 ms)
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
                    Thread.sleep(800); // Wait before retrying (500 ms)
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                    throw new RuntimeException("Interrupted while waiting to retry type.", ie);
                }
            }
        }
        throw new RuntimeException("Failed to type into the element after " + maxRetries + " attempts.");
    }


    private void Login()
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
        Search_text = By.id("searchtext");
        Search_Button = By.className("search-button") ;
    }

    @Test
    public void A_Check_that_ResultCorrect_when_SearchWithDataNotFound()
    {
        Login() ;
        retryType(Search_text, "dfjkjdfkdjk", 8);
        retryClick(Search_Button, 5);
        By Search_Result = By.linkText("Stores (0)") ;
        driver.element().verifyThat(Search_Result).isVisible().perform();
    }

    @Test
    public void B_Check_that_ResultCorrect_when_SearchWith_Amazon()
    {
        Login() ;
        retryType(Search_text, "amazon", 8);
        retryClick(Search_Button, 5);
        By AmazonStore = By.xpath("//*[@id=\"heatmapArea\"]/main/div/div/div[2]/div[1]/div[1]/div[1]/h3/a") ;
        driver.element().verifyThat(AmazonStore).isVisible().perform();
        By Hatolna_ShoppingStore = By.linkText("Hatolna Shopping") ;
        driver.element().verifyThat(Hatolna_ShoppingStore).isVisible().perform();
    }

    @Test
    public void C_Check_that_ResultCorrect_when_SearchWith_2B()
    {
        Login() ;
        retryType(Search_text, "2b", 8);
        retryClick(Search_Button, 5);
        By store_2b= By.xpath("//*[@id=\"heatmapArea\"]/main/div/div/div[2]/div[1]/div/div[1]/h3/a") ;
        driver.element().verifyThat(store_2b).isVisible().perform();
    }

    @Test
    public void D_Check_that_ResultCorrect_when_SearchWith_jum()
    {
        Login();
        retryType(Search_text, "jum", 8);
        retryClick(Search_Button, 5);
        By store_jumia= By.xpath("//*[@id=\"heatmapArea\"]/main/div/div/div[2]/div[1]/div/div[1]/h3/a") ;
        driver.element().verifyThat(store_jumia).isVisible().perform();
    }

    @Test
    public void E_Check_that_ResultCorrect_when_SearchWith_TheHut()
    {
        Login();
        retryType(Search_text, "the hut", 8);
        retryClick(Search_Button, 5);
        By store_TheHut= By.linkText("The Hut") ;
        driver.element().verifyThat(store_TheHut).isVisible().perform();
    }

    @Test
    public void F_Check_that_ResultCorrect_when_SearchWith_BTech()
    {
        Login();
        retryType(Search_text, "b.tech", 8);
        retryClick(Search_Button, 5);
        By store_BTech= By.xpath("//*[@id=\"heatmapArea\"]/main/div/div/div[2]/div[1]/div/div[1]/h3/a") ;
        driver.element().verifyThat(store_BTech).isVisible().perform();
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
