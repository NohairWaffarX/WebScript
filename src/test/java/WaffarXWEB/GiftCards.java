package WaffarXWEB;
import com.shaft.driver.SHAFT;
import org.checkerframework.checker.units.qual.C;
import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.openqa.selenium.Keys.*;

public class GiftCards {
    SHAFT.GUI.WebDriver driver;
    By SubTotal,TotalAmount,CardAmount,number,Error , Close;
    @BeforeMethod
    public void setupBrowser() {
        driver = new SHAFT.GUI.WebDriver(); //to open browser
        driver.browser().navigateToURL("https://portal-test.waffarx.com/en-eg"); //to navigate to URL
    }

    private void Open_GiftCards_Page() {
        By Register_Button = By.xpath("//*[@id='heatmapArea']/main/div[2]/div[1]/button");
        driver.element().waitToBeReady(Register_Button); // Wait for the button to be clickable
        driver.element().clickUsingJavascript(Register_Button); // To force click on this element

        By AlreadyMember_Button = By.xpath("//*[@id='newSignUp']/div/div/div/div[4]/a");
        driver.element().clickUsingJavascript(AlreadyMember_Button); // To force click on this element

        By Email = By.id("LoginEmail");
        driver.element().type(Email, "gnohair@gmail.com");

        By Password = By.id("LoginPassword");
        driver.element().type(Password, "Ng555555");

        By SignIN_Button = By.xpath("//*[@id=\"Login\"]/div[4]/input");
        driver.element().keyPress(SignIN_Button, ENTER);

        By Tab_GiftCards = By.linkText("Gift Cards");
        driver.element().click(Tab_GiftCards);

        By CloseBrowser_extension = By.xpath("//*[@id=\"closeAds\"]/i") ;
        driver.element().clickUsingJavascript(CloseBrowser_extension);

        By Amazon_GiftCards = By.xpath("//*[@id=\"heatmapArea\"]/main/div/div[3]/div[2]/div/a/img");
        driver.element().click(Amazon_GiftCards);

        driver.element().clickUsingJavascript(CloseBrowser_extension);

        SubTotal = By.id("subtotal");
        TotalAmount = By.id("totalamount");
        CardAmount = By.id("SelectedAmount");
        number = By.id("numberInput") ;
        Error = By.id("numberInput-error") ;
    }

    @Test(priority = 1)
    public void CheckThat_WhenChoose_value_AllDataAppearCorrect() {
        Open_GiftCards_Page();
        By choice_1000 = By.xpath("//*[@id=\"paygiftcard\"]/div[2]/a[7]");
        driver.element().scrollToElement(choice_1000);
        driver.element().clickUsingJavascript(choice_1000);

        driver.element().verifyThat(SubTotal).text().isEqualTo("1000").perform();
        driver.element().verifyThat(TotalAmount).text().isEqualTo("1000").perform();
        driver.element().verifyThat(CardAmount).text().isEqualTo("1000").perform();
    }

    @Test(priority = 2)
    public void CheckThat_Plus_And_Minus_WorkCorrectly() {
        Open_GiftCards_Page();
        By Plus_Button = By.xpath("//*[@id=\"plusBtn\"]/i") ;
        driver.element().scrollToElement(Plus_Button) ;
        By Minus_Button = By.xpath("//*[@id=\"minusBtn\"]/i") ;

        for (int i=0 ; i<=5 ; i++) {
            driver.element().click(Plus_Button);
        }

        driver.element().verifyThat(SubTotal).text().isEqualTo("110").perform();
        driver.element().verifyThat(TotalAmount).text().isEqualTo("110").perform();
        driver.element().verifyThat(CardAmount).text().isEqualTo("110").perform();

        for(int i=0 ; i<=8 ; i++) {
            driver.element().click(Minus_Button);
        }

        driver.element().verifyThat(SubTotal).text().isEqualTo("20").perform();
        driver.element().verifyThat(TotalAmount).text().isEqualTo("20").perform();
        driver.element().verifyThat(CardAmount).text().isEqualTo("20").perform();
    }

    @Test(priority = 3)
    public void CheckThat_WhenInsert_value_AllDataAppearCorrect() {
        Open_GiftCards_Page();
        driver.element().type(number , "5000") ;
        driver.element().keyPress(number , TAB) ;
        driver.element().verifyThat(SubTotal).text().isEqualTo("5000").perform();
        driver.element().verifyThat(TotalAmount).text().isEqualTo("5000").perform();
        driver.element().verifyThat(CardAmount).text().isEqualTo("5000").perform();
    }

    @Test(priority = 4)
    public void CheckThat_ErrorAppear_When_NumberLessThanMin() {
        Open_GiftCards_Page();
        driver.element().type(number , "0") ;
        driver.element().keyPress(number , TAB) ;
        driver.element().verifyThat(Error).text().isEqualTo("The amount must be between 1 and 6000").perform();
    }

    @Test(priority = 5)
    public void CheckThat_ErrorAppear_When_NumberGreaterThanMax() {
        Open_GiftCards_Page();
        driver.element().type(number , "7000") ;
        driver.element().keyPress(number , TAB) ;
        driver.element().verifyThat(Error).text().isEqualTo("The amount must be between 1 and 6000").perform();
    }

    @Test(priority = 6)
    public void CheckThat_Increase_AndDecrease_Arrow_WorkCorrect() {
        Open_GiftCards_Page();

        for (int i=0 ; i<=10 ; i++) {
            driver.element().keyPress(number , ARROW_UP) ;
        }
        driver.element().verifyThat(SubTotal).text().isEqualTo("61").perform();
        driver.element().verifyThat(TotalAmount).text().isEqualTo("61").perform();
        driver.element().verifyThat(CardAmount).text().isEqualTo("61").perform();

        for (int i=0 ; i<=20 ; i++) {
            driver.element().keyPress(number , ARROW_DOWN) ;
        }
        driver.element().verifyThat(SubTotal).text().isEqualTo("40").perform();
        driver.element().verifyThat(TotalAmount).text().isEqualTo("40").perform();
        driver.element().verifyThat(CardAmount).text().isEqualTo("40").perform();
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