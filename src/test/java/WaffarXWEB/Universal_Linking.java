package WaffarXWEB;
import com.shaft.driver.SHAFT;
import com.shaft.validation.Validations;
import org.openqa.selenium.By;
import org.testng.annotations.*;
import static org.openqa.selenium.Keys.ENTER;

public class Universal_Linking
{
    SHAFT.GUI.WebDriver driver;

    @BeforeMethod
    public void setupBrowser()
    {
        driver = new SHAFT.GUI.WebDriver(); // to open browser
        driver.browser().navigateToURL("https://www.waffarx.com/en-eg"); // to navigate to URL
    }

    private void Open_HelpPage()
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
    }

    @Test(priority = 1)
    public void Check_that_All_UniversalLinking_InRefer_WorkCorrectly() {
        Open_HelpPage();

        By ReferChoice = By.xpath("//*[@id=\"heatmapArea\"]/main/div[2]/div/div[1]/ul/li[1]/h3/a");
        driver.element().clickUsingJavascript(ReferChoice);

        String originalWindowHandler = driver.browser().getWindowHandle();

        By ThisPage_Link = By.linkText("this page");
        driver.element().click(ThisPage_Link);

        for (String windowHandle : driver.getDriver().getWindowHandles()) {
            if (!originalWindowHandler.contentEquals(windowHandle)) {
                driver.browser().switchToWindow(windowHandle);
                break;
            }
        }
        String actualURL_Refer = driver.browser().getCurrentURL();
        Validations.assertThat().object((Object) actualURL_Refer).isEqualTo((Object) "https://www.waffarx.com/en-us/referral").perform();
    }

        @Test(priority = 2)
        public void Check_that_All_UniversalLinking_InPrivacyAndSecurity_WorkCorrectly()
        {
            Open_HelpPage();

            By PrivacyAndSecurity = By.xpath("//*[@id=\"heatmapArea\"]/main/div[2]/div/div[1]/ul/li[2]/h3/a");
            driver.element().clickUsingJavascript(PrivacyAndSecurity);

            String originalWindowHandler = driver.browser().getWindowHandle();

            By PrivacyAgreement_Link = By.linkText("Privacy Agreement.");
            driver.element().click(PrivacyAgreement_Link);

            for (String windowHandle : driver.getDriver().getWindowHandles()) {
                if (!originalWindowHandler.contentEquals(windowHandle)) {
                    driver.browser().switchToWindow(windowHandle);
                    break;
                }
            }

            String actualURL_PrivacyAgreement = driver.browser().getCurrentURL();
            Validations.assertThat().object((Object) actualURL_PrivacyAgreement).isEqualTo((Object) "https://www.waffarx.com/en-us/privacypolicy").perform();

            driver.getDriver().switchTo().window(originalWindowHandler);

            ///////////// link of Privacy policy //////////////////////
            By PrivacyPolicy_Link = By.linkText("privacy policy");
            driver.element().click(PrivacyPolicy_Link);

            for (String windowHandle : driver.getDriver().getWindowHandles()) {
                if (!originalWindowHandler.contentEquals(windowHandle)) {
                    driver.browser().switchToWindow(windowHandle);
                    break;
                }
            }
            String actualURL_PrivacyPolicy = driver.browser().getCurrentURL();
            Validations.assertThat().object((Object) actualURL_PrivacyPolicy).isEqualTo((Object) "https://www.waffarx.com/en-us/privacypolicy").perform();

            driver.getDriver().switchTo().window(originalWindowHandler);
        }

    @Test(priority = 3)
    public void Check_that_All_UniversalLinking_InMyAccount_WorkCorrectly()
    {
        Open_HelpPage();

        By MyAccountChoice = By.xpath("//*[@id=\"heatmapArea\"]/main/div[2]/div/div[1]/ul/li[4]/h3/a");
        driver.element().clickUsingJavascript(MyAccountChoice);
        String originalWindowHandler = driver.browser().getWindowHandle();
        //***************  link of EmailSubscription **************** //
        By EmailSubscription_Link = By.xpath("//*[@id=\"1029\"]/div/p[4]/a");
        driver.element().click(EmailSubscription_Link);

        for (String windowHandle : driver.getDriver().getWindowHandles()) {
            if (!originalWindowHandler.contentEquals(windowHandle)) {
                driver.browser().switchToWindow(windowHandle);
                break;
            }
        }
        String actualURL_EmailSubscription = driver.browser().getCurrentURL();
        Validations.assertThat().object((Object) actualURL_EmailSubscription).isEqualTo((Object) "https://www.waffarx.com/en-us/emailsubscription").perform();

        driver.getDriver().switchTo().window(originalWindowHandler);

        //*************** link of Shopping Trips **************** //
        By ShoppingTrips_Link = By.xpath("//*[@id=\"1027\"]/div/p[3]/a");
        driver.element().click(ShoppingTrips_Link);

        for (String windowHandle : driver.getDriver().getWindowHandles()) {
            if (!originalWindowHandler.contentEquals(windowHandle)) {
                driver.browser().switchToWindow(windowHandle);
            }
        }
        String actualURL_ShoppingTrips= driver.browser().getCurrentURL();
        Validations.assertThat().object((Object) actualURL_ShoppingTrips).isEqualTo((Object) "https://www.waffarx.com/en-eg/transactions").perform();

        driver.getDriver().switchTo().window(originalWindowHandler);

        //*************** link of contactUS **************** //

        By contactUS_Link = By.xpath("//*[@id=\"1027\"]/div/p[6]/a");
        driver.element().click(contactUS_Link);

        for (String windowHandle : driver.getDriver().getWindowHandles()) {
            if (!originalWindowHandler.contentEquals(windowHandle)) {
                driver.browser().switchToWindow(windowHandle);
            }
        }
        String actualURL_contactUS= driver.browser().getCurrentURL();
        Validations.assertThat().object((Object) actualURL_contactUS).isEqualTo((Object) "https://www.waffarx.com/en-eg/help").perform();

        driver.getDriver().switchTo().window(originalWindowHandler);

        //*************** link of My Cash Back Balance  **************** //
        By MyCashBackBalance_Link = By.xpath("//*[@id=\"1027\"]/div/p[8]/a");
        driver.element().click(MyCashBackBalance_Link);

        By CashBackTitle = By.xpath("//*[@id=\"heatmapArea\"]/main/div/div/div[2]/div/div[3]/h2");
        driver.element().verifyThat(CashBackTitle).isVisible().perform();

        driver.browser().navigateBack();

        //*************** link of  CashOut History **************** //
        By CashOutHistory_Link = By.xpath("//*[@id=\"1027\"]/div/p[10]/a");
        driver.element().click(CashOutHistory_Link);

        By DetailsTitle = By.xpath("//*[@id=\"heatmapArea\"]/main/div/div/div[2]/div/div[1]/h2");
        driver.element().verifyThat(DetailsTitle).isVisible().perform();

        driver.browser().navigateBack();

        //*************** link of My Account Settings **************** //
        By MyAccountSettings_Link = By.xpath("//*[@id=\"1027\"]/div/p[11]/a");
        driver.element().click(MyAccountSettings_Link);

        By Update_name= By.linkText("Update Name") ;
        driver.element().verifyThat(Update_name).isVisible().perform();

        driver.browser().navigateBack();

        //*************** link of My ContactUS **************** //
        By ContactUS_Link2 = By.xpath("//*[@id=\"1030\"]/div/p/a");
        driver.element().click(ContactUS_Link2);

        for (String windowHandle : driver.getDriver().getWindowHandles()) {
            if (!originalWindowHandler.contentEquals(windowHandle)) {
                driver.browser().switchToWindow(windowHandle);
            }
        }
        String actualURL_contactUS2 = driver.browser().getCurrentURL();
        Validations.assertThat().object((Object) actualURL_contactUS2).isEqualTo((Object) "https://www.waffarx.com/en-us/help").perform();
    }

    @Test(priority = 4)
    public void Check_that_All_UniversalLinking_InMyCashOut_WorkCorrectly()
    {
        Open_HelpPage();

        By MyCashOut_Choice = By.xpath("//*[@id=\"heatmapArea\"]/main/div[2]/div/div[1]/ul/li[5]/h3/a");
        driver.element().clickUsingJavascript(MyCashOut_Choice);

        //*************** link of My ContactUS **************** //
        By ContactUS_Link = By.xpath("//*[@id=\"1005\"]/div/p[6]/a");
        driver.element().click(ContactUS_Link);

        By Title = By.xpath("//*[@id=\"heatmapArea\"]/main/div[2]/div/div[2]/div/h2");
        driver.element().verifyThat(Title).isVisible().perform();
        driver.browser().navigateBack();

        //*************** link of Account page **************** //
        By AccountPage_Link = By.xpath("//*[@id=\"1008\"]/div/p[2]/a");
        driver.element().click(AccountPage_Link);

        By Update_name= By.linkText("Update Name") ;
        driver.element().verifyThat(Update_name).isVisible().perform();
        driver.browser().navigateBack();

        //*************** link of Account settings **************** //
        By AccountSettings_Link = By.xpath("//*[@id=\"1008\"]/div/b/p[23]/a");
        driver.element().click(AccountSettings_Link);

        By Update_name2 = By.linkText("Update Name") ;
        driver.element().verifyThat(Update_name2).isVisible().perform();
    }

    @Test(priority = 5)
    public void Check_that_All_UniversalLinking_In_EarningCashBack_WorkCorrectly()
    {
        Open_HelpPage();
        //*************** link of CustomerService **************** //
        By EarningCashBack_Choice = By.xpath("//*[@id=\"heatmapArea\"]/main/div[2]/div/div[1]/ul/li[6]/h3/a");
        driver.element().clickUsingJavascript(EarningCashBack_Choice);

        String originalWindowHandler = driver.browser().getWindowHandle();

        By CustomerService_Link = By.xpath("//*[@id=\"1004\"]/div/p[3]/a");
        driver.element().click(CustomerService_Link);

        for (String windowHandle : driver.getDriver().getWindowHandles()) {
            if (!originalWindowHandler.contentEquals(windowHandle)) {
                driver.browser().switchToWindow(windowHandle);
                break;
            }
        }
        String actualURL_contactUS= driver.browser().getCurrentURL();
        Validations.assertThat().object((Object) actualURL_contactUS).isEqualTo((Object) "https://www.waffarx.com/en-us/help").perform();
        driver.getDriver().switchTo().window(originalWindowHandler);

        //*************** link of CashBack **************** //
        By CashbackPage_Link = By.xpath("//*[@id=\"1068\"]/div/p[2]/a");
        driver.element().click(CashbackPage_Link);

        By CashBackTitle = By.xpath("//*[@id=\"heatmapArea\"]/main/div/div/div[2]/div/div[3]/h2");
        driver.element().verifyThat(CashBackTitle).isVisible().perform();
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