package WaffarXWEB;

import com.shaft.driver.SHAFT;
import org.openqa.selenium.By;
import org.testng.annotations.*;
import static org.openqa.selenium.Keys.ENTER;

public class FavouriteStores
{
    SHAFT.GUI.WebDriver driver;

    @BeforeMethod
    public void setupBrowser()
    {
        driver = new SHAFT.GUI.WebDriver(); // to open browser
        driver.browser().navigateToURL("https://www.waffarx.com/en-eg"); // to navigate to URL
    }

    private void OpenMyFavourites_Page()
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

        By userinfo = By.xpath("//*[@id=\"fixedHeader\"]/div[3]/div/div[2]/div[3]") ;
        driver.element().hover(userinfo);

        By MyFavourites_choice= By.linkText("My Favorites") ;
        driver.element().click(MyFavourites_choice);
    }

    @Test(priority = 1)
    public void UpdateName_EmptyFields() {
        OpenMyFavourites_Page();

        By Amazon = By.xpath("(//*[@id=\"listContainer\"]/ul/li[1]/div/div[1]/i)[1]") ;
        driver.element().click(Amazon);

        By A = By.xpath("//*[@id=\"listContainer\"]/ul/li[2]") ;
        driver.element().verifyThat(A).isVisible().perform();
    }
}
