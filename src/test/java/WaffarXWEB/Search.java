package WaffarXWEB;
import com.shaft.driver.SHAFT;
import org.openqa.selenium.By;
import org.testng.annotations.*;
import static org.openqa.selenium.Keys.ENTER;

public class Search
{
    SHAFT.GUI.WebDriver driver;

    @BeforeMethod
    public void setupBrowser()
    {
        driver = new SHAFT.GUI.WebDriver(); // to open browser
        driver.browser().navigateToURL("https://www.waffarx.com/en-eg"); // to navigate to URL
    }

    private void Login()
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
    }

    @Test(priority = 1)
    public void Check_that_ResultCorrect_when_SearchWithDataNotFound()
    {
        Login() ;
        By Search_text = By.id("searchtext");
        driver.element().type(Search_text, "dfjkjdfkdjk" ) ;

        By Search_Button = By.className("search-button") ;
        driver.element().click(Search_Button);

        By Search_Result = By.linkText("Stores (0)") ;
        driver.element().verifyThat(Search_Result).isVisible().perform();
    }

    @Test(priority = 2)
    public void Check_that_ResultCorrect_when_SearchWith_Amazon()
    {
        Login() ;
        By Search_text = By.id("searchtext");
        driver.element().type(Search_text, "amazon" ) ;

        By Search_Button = By.className("search-button") ;
        driver.element().click(Search_Button);

        By AmazonStore = By.xpath("//*[@id=\"heatmapArea\"]/main/div/div/div[2]/div[1]/div[1]/div[1]/h3/a") ;
        driver.element().verifyThat(AmazonStore).isVisible().perform();

        By Hatolna_ShoppingStore = By.linkText("Hatolna Shopping") ;
        driver.element().verifyThat(Hatolna_ShoppingStore).isVisible().perform();
    }

    @Test(priority = 3)
    public void Check_that_ResultCorrect_when_SearchWith_2B()
    {
        Login() ;
        By Search_text = By.id("searchtext");
        driver.element().type(Search_text, "2b" ) ;

        By Search_Button = By.className("search-button") ;
        driver.element().click(Search_Button);

        By store_2b= By.xpath("//*[@id=\"heatmapArea\"]/main/div/div/div[2]/div[1]/div/div[1]/h3/a") ;
        driver.element().verifyThat(store_2b).isVisible().perform();
    }

    @Test(priority = 4)
    public void Check_that_ResultCorrect_when_SearchWith_jum()
    {
        Login();
        By Search_text = By.id("searchtext");
        driver.element().type(Search_text, "jum" ) ;

        By Search_Button = By.className("search-button") ;
        driver.element().click(Search_Button);

        By store_jumia= By.xpath("//*[@id=\"heatmapArea\"]/main/div/div/div[2]/div[1]/div/div[1]/h3/a") ;
        driver.element().verifyThat(store_jumia).isVisible().perform();
    }

    @Test(priority = 5)
    public void Check_that_ResultCorrect_when_SearchWith_TheHut()
    {
        Login();
        By Search_text = By.id("searchtext");
        driver.element().type(Search_text, "the hut" ) ;

        By Search_Button = By.className("search-button") ;
        driver.element().click(Search_Button);

        By store_TheHut= By.linkText("The Hut") ;
        driver.element().verifyThat(store_TheHut).isVisible().perform();
    }

    @Test(priority = 6)
    public void Check_that_ResultCorrect_when_SearchWith_BTech()
    {
        Login();
        By Search_text = By.id("searchtext");
        driver.element().type(Search_text, "b.tech" ) ;

        By Search_Button = By.className("search-button") ;
        driver.element().click(Search_Button);

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