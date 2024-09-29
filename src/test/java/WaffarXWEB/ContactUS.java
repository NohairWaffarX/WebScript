package WaffarXWEB;
import com.shaft.driver.SHAFT;
import org.openqa.selenium.By;
import org.testng.annotations.*;
import static org.openqa.selenium.Keys.ENTER;

public class ContactUS
{
    SHAFT.GUI.WebDriver driver;

    @BeforeMethod
    public void setupBrowser()
    {
        driver = new SHAFT.GUI.WebDriver(); // to open browser
        driver.browser().navigateToURL("https://www.waffarx.com/en-eg"); // to navigate to URL
    }

    private void Open_ContactUsPage()
    {
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

        By Help_choice= By.xpath("//*[@id=\"category-menu\"]/div/ul/li[9]/a/h2") ;
        driver.element().click(Help_choice);

        By ContactUS= By.xpath("//*[@id=\"heatmapArea\"]/main/div[2]/div/div[1]/ul/li[7]/h3/a") ;
        driver.element().click(ContactUS);
    }

    @Test(priority = 1)
    public void Check_that_TrackMyCashBack_WorkCorrectly()
    {
        Open_ContactUsPage();

        By Topic_DropDownList = By.id("eventTypes") ;
        driver.element().select(Topic_DropDownList, "Track My Cash Back");

        By Select_Store = By.id("ExitClickStores") ;
        driver.element().select(Select_Store, "Amazon.eg");

        By ShoppingTrip = By.id("shoppingTrip") ;
        driver.element().select(ShoppingTrip, "5/21/2024 11:13:04 AM - Amazon.eg");

        By OrderNumber = By.id("OrderNumber") ;
        driver.element().type( OrderNumber, "456-22ABC#EF@D") ;

        By OrderSubtotal = By.id("OrderSubtotal") ;
        driver.element().type( OrderSubtotal, "333.5") ;

        By Note = By.id("TextMessage") ;
        driver.element().type( Note, "Nohair Test") ;

        By Submit_Button = By.xpath("(//*[@id=\"online-cashout\"]/div[6]/input)[1]") ;
        driver.element().click(Submit_Button)  ;

        By Result = By.xpath("//*[@id=\"TrackCashBackResult\"]/p");
        driver.element().verifyThat(Result).text().isEqualTo("Thank You, your ticket has been submitted").perform();
    }

    @Test(priority = 2)
    public void Check_that_InstoreCashBack_WorkCorrectly()
    {
        Open_ContactUsPage();

        By Topic_DropDownList = By.id("eventTypes") ;
        driver.element().select(Topic_DropDownList, "Track my In-Store Cash Back");

        By Select_InStore = By.id("ExitClickInStores") ;
        driver.element().select(Select_InStore, "Spinneys");

        By ShoppingTrip = By.id("shoppingInStoreTrip") ;
        driver.element().select(ShoppingTrip, "7/17/2024 10:04:08 PM - Spinneys");

        By OrderNumber = By.id("InStoreOrderNumber") ;
        driver.element().type( OrderNumber, "456-22ABC#EF@D") ;

        By OrderSubtotal = By.id("InStoreOrderSubtotal") ;
        driver.element().type( OrderSubtotal, "333.5") ;

        By Note = By.id("InStoreTextMessage") ;
        driver.element().type( Note, "Nohair Test") ;

        By Submit_Button = By.xpath("(//*[@id=\"online-cashout\"]/div[6]/input)[2]") ;
        driver.element().click(Submit_Button)  ;

        By Result = By.xpath("//*[@id=\"TrackCashBackResult\"]/p");
        driver.element().verifyThat(Result).text().isEqualTo("Thank You, your ticket has been submitted").perform();
    }

    @Test(priority = 3)
    public void Check_that_TrackMyCashOut_WorkCorrectly()
    {
        Open_ContactUsPage();

        By Topic_DropDownList = By.id("eventTypes") ;
        driver.element().select(Topic_DropDownList, "Track My CashOut");

        By CashOut_Method = By.id("paymentMethod") ;
        driver.element().select(CashOut_Method, "Fawry");

        By Note = By.id("TextMessageAll") ;
        driver.element().type( Note, "Nohair Test") ;

        By Submit_Button = By.xpath("//*[@id=\"divMsgAll\"]/div[2]/input") ;
        driver.element().click(Submit_Button)  ;

        By Result = By.xpath("//*[@id=\"TrackCashBackResult\"]/p");
        driver.element().verifyThat(Result).text().isEqualTo("Thank You, your ticket has been submitted").perform();
    }

    @Test(priority = 4)
    public void Check_that_TrackMyWelcomeBouns_WorkCorrectly()
    {
        Open_ContactUsPage();

        By Topic_DropDownList = By.id("eventTypes") ;
        driver.element().select(Topic_DropDownList, "Track My Welcome Bonus");

        By Note = By.id("TextMessageAll") ;
        driver.element().type( Note, "Nohair Test") ;

        By Submit_Button = By.xpath("//*[@id=\"divMsgAll\"]/div[2]/input") ;
        driver.element().click(Submit_Button)  ;

        By Result = By.xpath("//*[@id=\"TrackCashBackResult\"]/p");
        driver.element().verifyThat(Result).text().isEqualTo("Thank You, your ticket has been submitted").perform();
    }

    @Test(priority = 5)
    public void Check_that_ResetMyPassword_WorkCorrectly()
    {
        Open_ContactUsPage();

        By Topic_DropDownList = By.id("eventTypes") ;
        driver.element().select(Topic_DropDownList, "Reset My Password");

        By Note = By.id("TextMessageAll") ;
        driver.element().type( Note, "Nohair Test") ;

        By Submit_Button = By.xpath("//*[@id=\"divMsgAll\"]/div[2]/input") ;
        driver.element().click(Submit_Button)  ;

        By Result = By.xpath("//*[@id=\"TrackCashBackResult\"]/p");
        driver.element().verifyThat(Result).text().isEqualTo("Thank You, your ticket has been submitted").perform();
    }

    @Test(priority = 6)
    public void Check_that_EmailAddress_WorkCorrectly()
    {
        Open_ContactUsPage();

        By Topic_DropDownList = By.id("eventTypes") ;
        driver.element().select(Topic_DropDownList, "Change Email Address");

        By Note = By.id("TextMessageAll") ;
        driver.element().type( Note, "Nohair Test") ;

        By Submit_Button = By.xpath("//*[@id=\"divMsgAll\"]/div[2]/input") ;
        driver.element().click(Submit_Button)  ;

        By Result = By.xpath("//*[@id=\"TrackCashBackResult\"]/p");
        driver.element().verifyThat(Result).text().isEqualTo("Thank You, your ticket has been submitted").perform();
    }
    @Test(priority = 7)
    public void Check_that_EmailPromotionsQuestions_WorkCorrectly  ()
    {
        Open_ContactUsPage();

        By Topic_DropDownList = By.id("eventTypes") ;
        driver.element().select(Topic_DropDownList, "Email Promotions Questions");

        By Note = By.id("TextMessageAll") ;
        driver.element().type( Note, "Nohair Test") ;

        By Submit_Button = By.xpath("//*[@id=\"divMsgAll\"]/div[2]/input") ;
        driver.element().click(Submit_Button)  ;

        By Result = By.xpath("//*[@id=\"TrackCashBackResult\"]/p");
        driver.element().verifyThat(Result).text().isEqualTo("Thank You, your ticket has been submitted").perform();
    }
    @Test(priority = 8)
    public void Check_that_WaffarXFeedback_WorkCorrectly()
    {
        Open_ContactUsPage();

        By Topic_DropDownList = By.id("eventTypes") ;
        driver.element().select(Topic_DropDownList, "WaffarX Feedback");

        By Note = By.id("TextMessageAll") ;
        driver.element().type( Note, "Nohair Test") ;

        By Submit_Button = By.xpath("//*[@id=\"divMsgAll\"]/div[2]/input") ;
        driver.element().click(Submit_Button)  ;

        By Result = By.xpath("//*[@id=\"TrackCashBackResult\"]/p");
        driver.element().verifyThat(Result).text().isEqualTo("Thank You, your ticket has been submitted").perform();
    }

    @Test(priority = 9)
    public void Check_that_Store_Feedback_WorkCorrectly()
    {
        Open_ContactUsPage();

        By Topic_DropDownList = By.id("eventTypes") ;
        driver.element().select(Topic_DropDownList, "Store Feedback");

        By Note = By.id("TextMessageAll") ;
        driver.element().type( Note, "Nohair Test") ;

        By Submit_Button = By.xpath("//*[@id=\"divMsgAll\"]/div[2]/input") ;
        driver.element().click(Submit_Button)  ;

        By Result = By.xpath("//*[@id=\"TrackCashBackResult\"]/p");
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

    @Test(priority = 10)
    public void Check_that_Blogger_WorkCorrectly()
    {
        Open_ContactUsPage();

        By Topic_DropDownList = By.id("eventTypes") ;
        driver.element().select(Topic_DropDownList, "Blogger");

        By Note = By.id("TextMessageAll") ;
        driver.element().type( Note, "Nohair Test") ;

        By Submit_Button = By.xpath("//*[@id=\"divMsgAll\"]/div[2]/input") ;
        driver.element().click(Submit_Button)  ;

        By Result = By.xpath("//*[@id=\"TrackCashBackResult\"]/p");
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
