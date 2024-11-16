package WaffarXWEB;
import com.shaft.driver.SHAFT;
import org.openqa.selenium.By;
import org.testng.annotations.*;
import static org.openqa.selenium.Keys.ENTER;

public class ContactUS
{
    SHAFT.GUI.WebDriver driver;
    By Topic_DropDownList,Result;

    @BeforeMethod
    public void setupBrowser()
    {
        driver = new SHAFT.GUI.WebDriver(); // to open browser
        driver.browser().navigateToURL("https://www.waffarx.com/en-eg"); // to navigate to URL
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

    private void Open_ContactUsPage()
    {
        By Register_Button = By.xpath("//*[@id='heatmapArea']/main/div[2]/div[1]/button");
        driver.element().waitToBeReady(Register_Button); // Wait for the button to be clickable
        driver.element().clickUsingJavascript(Register_Button); // To force click on this element

        By AlreadyMember_Button = By.xpath("//*[@id='newSignUp']/div/div/div/div[4]/a");
        driver.element().clickUsingJavascript(AlreadyMember_Button); // To force click on this element

        By Email = By.id("LoginEmail");
        retryType(Email, "gnohair@gmail.com", 30);

        By Password = By.id("LoginPassword");
        retryType(Password, "Ng555555", 30);

        By SignIN_Button = By.xpath("//*[@id=\"Login\"]/div[4]/input");
        driver.element().keyPress(SignIN_Button, ENTER);

        By Help_choice= By.xpath("//*[@id=\"category-menu\"]/div/ul/li[9]/a/h2") ;
        driver.element().click(Help_choice);

        By ContactUS= By.xpath("//*[@id=\"heatmapArea\"]/main/div[2]/div/div[1]/ul/li[7]/h3/a") ;
        driver.element().click(ContactUS);

        Topic_DropDownList = By.id("eventTypes") ;
        Result = By.xpath("//*[@id=\"TrackCashBackResult\"]/p");
    }

    @Test
    public void A_Check_that_TrackMyCashBack_WorkCorrectly()
    {
        Open_ContactUsPage();

        driver.element().select(Topic_DropDownList, "Track My Cash Back");

        By Select_Store = By.id("ExitClickStores") ;
        driver.element().select(Select_Store, "Amazon.eg");

        By ShoppingTrip = By.id("shoppingTrip") ;
        driver.element().select(ShoppingTrip, "5/21/2024 11:13:04 AM - Amazon.eg");

        By OrderNumber = By.id("OrderNumber") ;
        retryType(OrderNumber, "456-22ABC#EF@D", 30);

        By OrderSubtotal = By.id("OrderSubtotal") ;
        retryType(OrderSubtotal, "333.5", 30);

        By Note = By.id("TextMessage") ;
        retryType(Note, "Nohair Test", 30);

        By Submit_Button = By.xpath("(//*[@id=\"online-cashout\"]/div[6]/input)[1]") ;
        retryClick(Submit_Button, 30);

        driver.element().verifyThat(Result).text().isEqualTo("Thank You, your ticket has been submitted").perform();
    }

    @Test
    public void B_Check_that_InstoreCashBack_WorkCorrectly()
    {
        Open_ContactUsPage();

        driver.element().select(Topic_DropDownList, "Track my In-Store Cash Back");

        By Select_InStore = By.id("ExitClickInStores") ;
        driver.element().select(Select_InStore, "Spinneys");

        By ShoppingTrip = By.id("shoppingInStoreTrip") ;
        driver.element().select(ShoppingTrip, "7/17/2024 10:04:08 PM - Spinneys");

        By OrderNumber = By.id("InStoreOrderNumber") ;
        retryType(OrderNumber, "456-22ABC#EF@D", 30);

        By OrderSubtotal = By.id("InStoreOrderSubtotal") ;
        retryType(OrderSubtotal, "333.5", 30);

        By Note = By.id("InStoreTextMessage") ;
        retryType(Note, "Nohair Test", 30);

        By Submit_Button = By.xpath("(//*[@id=\"online-cashout\"]/div[6]/input)[2]") ;
        retryClick(Submit_Button, 30);

        driver.element().verifyThat(Result).text().isEqualTo("Thank You, your ticket has been submitted").perform();
    }

    @Test
    public void C_Check_that_TrackMyCashOut_WorkCorrectly()
    {
        Open_ContactUsPage();

        driver.element().select(Topic_DropDownList, "Track My CashOut");

        By CashOut_Method = By.id("paymentMethod") ;
        driver.element().select(CashOut_Method, "Fawry");

        By Note = By.id("TextMessageAll") ;
        retryType(Note, "Nohair Test", 30);

        By Submit_Button = By.xpath("//*[@id=\"divMsgAll\"]/div[2]/input") ;
        retryClick(Submit_Button, 30);

        driver.element().verifyThat(Result).text().isEqualTo("Thank You, your ticket has been submitted").perform();
    }

    @Test
    public void D_Check_that_TrackMyWelcomeBouns_WorkCorrectly()
    {
        Open_ContactUsPage();
        driver.element().select(Topic_DropDownList, "Track My Welcome Bonus");

        By Note = By.id("TextMessageAll") ;
        retryType(Note, "Nohair Test", 30);

        By Submit_Button = By.xpath("//*[@id=\"divMsgAll\"]/div[2]/input") ;
        retryClick(Submit_Button, 30);

        driver.element().verifyThat(Result).text().isEqualTo("Thank You, your ticket has been submitted").perform();
    }

    @Test
    public void E_Check_that_ResetMyPassword_WorkCorrectly()
    {
        Open_ContactUsPage();

        driver.element().select(Topic_DropDownList, "Reset My Password");

        By Note = By.id("TextMessageAll") ;
        retryType(Note, "Nohair Test", 30);

        By Submit_Button = By.xpath("//*[@id=\"divMsgAll\"]/div[2]/input") ;
        retryClick(Submit_Button, 30);

        driver.element().verifyThat(Result).text().isEqualTo("Thank You, your ticket has been submitted").perform();
    }

    @Test
    public void F_Check_that_EmailAddress_WorkCorrectly()
    {
        Open_ContactUsPage();

        driver.element().select(Topic_DropDownList, "Change Email Address");

        By Note = By.id("TextMessageAll") ;
        retryType(Note, "Nohair Test", 30);

        By Submit_Button = By.xpath("//*[@id=\"divMsgAll\"]/div[2]/input") ;
        retryClick(Submit_Button, 30);

        driver.element().verifyThat(Result).text().isEqualTo("Thank You, your ticket has been submitted").perform();
    }
    @Test
    public void G_Check_that_EmailPromotionsQuestions_WorkCorrectly  ()
    {
        Open_ContactUsPage();

        driver.element().select(Topic_DropDownList, "Email Promotions Questions");

        By Note = By.id("TextMessageAll") ;
        retryType(Note, "Nohair Test", 30);

        By Submit_Button = By.xpath("//*[@id=\"divMsgAll\"]/div[2]/input") ;
        retryClick(Submit_Button, 30);

        driver.element().verifyThat(Result).text().isEqualTo("Thank You, your ticket has been submitted").perform();
    }
    @Test
    public void H_Check_that_WaffarXFeedback_WorkCorrectly()
    {
        Open_ContactUsPage();

        driver.element().select(Topic_DropDownList, "WaffarX Feedback");

        By Note = By.id("TextMessageAll") ;
        retryType(Note, "Nohair Test", 30);

        By Submit_Button = By.xpath("//*[@id=\"divMsgAll\"]/div[2]/input") ;
        retryClick(Submit_Button, 30);

        driver.element().verifyThat(Result).text().isEqualTo("Thank You, your ticket has been submitted").perform();
    }

    @Test
    public void I_Check_that_Store_Feedback_WorkCorrectly()
    {
        Open_ContactUsPage();

        driver.element().select(Topic_DropDownList, "Store Feedback");

        By Note = By.id("TextMessageAll") ;
        retryType(Note, "Nohair Test", 30);

        By Submit_Button = By.xpath("//*[@id=\"divMsgAll\"]/div[2]/input") ;
        retryClick(Submit_Button, 30);

        driver.element().verifyThat(Result).text().isEqualTo("Thank You, your ticket has been submitted").perform();
    }
//    @Test(priority = 10)
//    public void Refer_issue()
//    {
//        OPen_ContactUsPage();
//
//        By Topic_DropDownList = By.id("eventTypes") ;
//        driver.element().select(Topic_DropDownList, "Refer-A-Friend Issue");
//
//        By Email = By.id("ReferFriendEmailID") ;
//        driver.element().type( Email, "N@N.com") ;
//
//        By Note = By.id("TextMessageAll") ;
//        driver.element().type( Note, "Nohair Test") ;
//
//        By Submit_Button = By.xpath("//*[@id=\"divMsgAll\"]/div[2]/input");
//        driver.element().click(Submit_Button);
//
//        By Result = By.xpath("//*[@id=\"TrackCashBackResult\"]/p");
//        driver.element().verifyThat(Result).text().isEqualTo("Thank You, your ticket has been submitted").perform();
//    }

    @Test
    public void J_Check_that_Blogger_WorkCorrectly()
    {
        Open_ContactUsPage();

        driver.element().select(Topic_DropDownList, "Blogger");

        By Note = By.id("TextMessageAll") ;
        retryType(Note, "Nohair Test", 30);

        By Submit_Button = By.xpath("//*[@id=\"divMsgAll\"]/div[2]/input") ;
        retryClick(Submit_Button, 30);

        driver.element().verifyThat(Result).text().isEqualTo("Thank You, your ticket has been submitted").perform();
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
