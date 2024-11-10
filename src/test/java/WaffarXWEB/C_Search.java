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
        driver.element().type(Search_text, "dfjkjdfkdjk" ) ;
        driver.element().click(Search_Button);
        By Search_Result = By.linkText("Stores (0)") ;
        driver.element().verifyThat(Search_Result).isVisible().perform();
    }

    @Test
    public void B_Check_that_ResultCorrect_when_SearchWith_Amazon()
    {
        Login() ;
        driver.element().type(Search_text, "amazon" ) ;
        driver.element().click(Search_Button);
        By AmazonStore = By.xpath("//*[@id=\"heatmapArea\"]/main/div/div/div[2]/div[1]/div[1]/div[1]/h3/a") ;
        driver.element().verifyThat(AmazonStore).isVisible().perform();
        By Hatolna_ShoppingStore = By.linkText("Hatolna Shopping") ;
        driver.element().verifyThat(Hatolna_ShoppingStore).isVisible().perform();
    }

    @Test
    public void C_Check_that_ResultCorrect_when_SearchWith_2B()
    {
        Login() ;
        driver.element().type(Search_text, "2b" ) ;
        driver.element().click(Search_Button);
        By store_2b= By.xpath("//*[@id=\"heatmapArea\"]/main/div/div/div[2]/div[1]/div/div[1]/h3/a") ;
        driver.element().verifyThat(store_2b).isVisible().perform();
    }

    @Test
    public void D_Check_that_ResultCorrect_when_SearchWith_jum()
    {
        Login();
        driver.element().type(Search_text, "jum" ) ;
        driver.element().click(Search_Button);
        By store_jumia= By.xpath("//*[@id=\"heatmapArea\"]/main/div/div/div[2]/div[1]/div/div[1]/h3/a") ;
        driver.element().verifyThat(store_jumia).isVisible().perform();
    }

    @Test
    public void E_Check_that_ResultCorrect_when_SearchWith_TheHut()
    {
        Login();
        driver.element().type(Search_text, "the hut" ) ;
        driver.element().click(Search_Button);
        By store_TheHut= By.linkText("The Hut") ;
        driver.element().verifyThat(store_TheHut).isVisible().perform();
    }

    @Test
    public void F_Check_that_ResultCorrect_when_SearchWith_BTech()
    {
        Login();
        driver.element().type(Search_text, "b.tech" ) ;
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
