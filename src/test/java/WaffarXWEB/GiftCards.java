package WaffarXWEB;
import com.shaft.driver.SHAFT;
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
        driver = new SHAFT.GUI.WebDriver();
        driver.browser().navigateToURL("https://portal-test.waffarx.com/en-eg");
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
                    Thread.sleep(2000);
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
                    Thread.sleep(2000);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                    throw new RuntimeException("Interrupted while waiting to retry type.", ie);
                }
            }
        }
        throw new RuntimeException("Failed to type into the element after " + maxRetries + " attempts.");
    }

    private void Open_GiftCards_Page() {
        By Register_Button = By.xpath("//*[@id='heatmapArea']/main/div[2]/div[1]/button");
        driver.element().waitToBeReady(Register_Button);
        driver.element().clickUsingJavascript(Register_Button);

        By AlreadyMember_Button = By.xpath("//*[@id='newSignUp']/div/div/div/div[4]/a");
        driver.element().clickUsingJavascript(AlreadyMember_Button);

        By Email = By.id("LoginEmail");
        retryType(Email, "gnohair@gmail.com", 40);

        By Password = By.id("LoginPassword");
        retryType(Password, "Ng555555", 40);

        By SignIN_Button = By.xpath("//*[@id=\"Login\"]/div[4]/input");
        driver.element().keyPress(SignIN_Button, ENTER);

        By Tab_GiftCards = By.linkText("Gift Cards");
        retryClick(Tab_GiftCards, 40);

        By CloseBrowser_extension = By.xpath("//*[@id=\"closeAds\"]/i") ;
        driver.element().clickUsingJavascript(CloseBrowser_extension);

        By Amazon_GiftCards = By.xpath("//*[@id=\"heatmapArea\"]/main/div/div[3]/div[2]/div[1]/a/img");
        retryClick(Amazon_GiftCards, 40);

        driver.element().clickUsingJavascript(CloseBrowser_extension);

        SubTotal = By.id("subtotal");
        TotalAmount = By.id("totalamount");
        CardAmount = By.id("SelectedAmount");
        number = By.id("numberInput") ;
        Error = By.id("numberInput-error") ;
    }

    @Test
    public void A_CheckThat_WhenChoose_value_AllDataAppearCorrect() {
        Open_GiftCards_Page();
        By choice_1000 = By.xpath("//*[@id=\"paygiftcard\"]/div[2]/a[7]");
        driver.element().scrollToElement(choice_1000);
        driver.element().clickUsingJavascript(choice_1000);

        driver.element().verifyThat(SubTotal).text().isEqualTo("1000").perform();
        driver.element().verifyThat(TotalAmount).text().isEqualTo("1000").perform();
        driver.element().verifyThat(CardAmount).text().isEqualTo("1000").perform();
    }

    @Test
    public void B_CheckThat_Plus_And_Minus_WorkCorrectly() {
        Open_GiftCards_Page();
        By Plus_Button = By.xpath("//*[@id=\"plusBtn\"]/i") ;
        driver.element().scrollToElement(Plus_Button) ;
        By Minus_Button = By.xpath("//*[@id=\"minusBtn\"]/i") ;

        for (int i=0 ; i<=5 ; i++) {
            retryClick(Plus_Button, 40);
        }

        driver.element().verifyThat(SubTotal).text().isEqualTo("110").perform();
        driver.element().verifyThat(TotalAmount).text().isEqualTo("110").perform();
        driver.element().verifyThat(CardAmount).text().isEqualTo("110").perform();

        for(int i=0 ; i<=8 ; i++) {
            retryClick(Minus_Button, 40);
        }

        driver.element().verifyThat(SubTotal).text().isEqualTo("20").perform();
        driver.element().verifyThat(TotalAmount).text().isEqualTo("20").perform();
        driver.element().verifyThat(CardAmount).text().isEqualTo("20").perform();
    }

    @Test
    public void C_CheckThat_WhenInsert_value_AllDataAppearCorrect() {
        Open_GiftCards_Page();
        retryType(number, "5000", 40);
        driver.element().keyPress(number , TAB) ;
        driver.element().verifyThat(SubTotal).text().isEqualTo("5000").perform();
        driver.element().verifyThat(TotalAmount).text().isEqualTo("5000").perform();
        driver.element().verifyThat(CardAmount).text().isEqualTo("5000").perform();
    }

    @Test
    public void D_CheckThat_ErrorAppear_When_NumberLessThanMin() {
        Open_GiftCards_Page();
        retryType(number, "0", 40);
        driver.element().keyPress(number , TAB) ;
        driver.element().verifyThat(Error).text().isEqualTo("The amount must be between 1 and 6000").perform();
    }

    @Test
    public void E_CheckThat_ErrorAppear_When_NumberGreaterThanMax() {
        Open_GiftCards_Page();
        retryType(number, "7000", 30);
        driver.element().keyPress(number , TAB) ;
        driver.element().verifyThat(Error).text().isEqualTo("The amount must be between 1 and 6000").perform();
    }

    @Test
    public void F_CheckThat_Increase_AndDecrease_Arrow_WorkCorrect() {
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
