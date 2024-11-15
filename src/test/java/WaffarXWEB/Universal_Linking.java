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
    private void Open_HelpPage()
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
        retryClick(Help_choice, 30);
    }

    @Test
    public void A_Check_that_All_UniversalLinking_InRefer_WorkCorrectly() {
        Open_HelpPage();

        By ReferChoice = By.xpath("//*[@id=\"heatmapArea\"]/main/div[2]/div/div[1]/ul/li[1]/h3/a");
        driver.element().clickUsingJavascript(ReferChoice);

        String originalWindowHandler = driver.browser().getWindowHandle();

        By Page_Link = By.linkText("this page");
        retryClick(Page_Link, 30);

        for (String windowHandle : driver.getDriver().getWindowHandles()) {
            if (!originalWindowHandler.contentEquals(windowHandle)) {
                driver.browser().switchToWindow(windowHandle);
                break;
            }
        }
        String actualURL_Refer = driver.browser().getCurrentURL();
        Validations.assertThat().object((Object) actualURL_Refer).isEqualTo((Object) "https://www.waffarx.com/en-us/referral").perform();
    }

    @Test
    public void B_Check_that_All_UniversalLinking_InPrivacyAndSecurity_WorkCorrectly()
        {
            Open_HelpPage();
            By PrivacyAndSecurity = By.xpath("//*[@id=\"heatmapArea\"]/main/div[2]/div/div[1]/ul/li[2]/h3/a");
            driver.element().clickUsingJavascript(PrivacyAndSecurity);

            String originalWindowHandler = driver.browser().getWindowHandle();
            By PrivacyAgreement_Link = By.linkText("Privacy Agreement.");
            retryClick(PrivacyAgreement_Link, 30);

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
            retryClick(PrivacyPolicy_Link, 30);

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

    @Test
    public void C_Check_that_All_UniversalLinking_InMyAccount_WorkCorrectly()
    {
        Open_HelpPage();

        By MyAccountChoice = By.xpath("//*[@id=\"heatmapArea\"]/main/div[2]/div/div[1]/ul/li[4]/h3/a");
        driver.element().clickUsingJavascript(MyAccountChoice);
        String originalWindowHandler = driver.browser().getWindowHandle();
        //***************  link of EmailSubscription **************** //
        By EmailSubscription_Link = By.xpath("//*[@id=\"1029\"]/div/p[4]/a");
        driver.element().scrollToElement(EmailSubscription_Link) ;
        retryClick(EmailSubscription_Link, 30);

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
        By ShoppingTrips_Link = By.linkText("View My Shopping Trips");
        retryClick(ShoppingTrips_Link, 30);

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
        retryClick(contactUS_Link, 30);

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
        retryClick(MyCashBackBalance_Link, 30);

        By CashBackTitle = By.xpath("//*[@id=\"heatmapArea\"]/main/div/div/div[2]/div/div[3]/h2");
        driver.element().verifyThat(CashBackTitle).isVisible().perform();

        driver.browser().navigateBack();

        //*************** link of  CashOut History **************** //
        By CashOutHistory_Link = By.xpath("//*[@id=\"1027\"]/div/p[10]/a");
        retryClick(CashOutHistory_Link, 30);

        By DetailsTitle = By.xpath("//*[@id=\"heatmapArea\"]/main/div/div/div[2]/div/div[1]/h2");
        driver.element().verifyThat(DetailsTitle).isVisible().perform();

        driver.browser().navigateBack();

        //*************** link of My Account Settings **************** //
        By MyAccountSettings_Link = By.xpath("//*[@id=\"1027\"]/div/p[11]/a");
        retryClick(MyAccountSettings_Link, 30);

        By Update_name= By.linkText("Update Name") ;
        driver.element().verifyThat(Update_name).isVisible().perform();

        driver.browser().navigateBack();

        //*************** link of My ContactUS **************** //
        By ContactUS_Link2 = By.xpath("//*[@id=\"1030\"]/div/p/a");
        retryClick(ContactUS_Link2, 30);

        for (String windowHandle : driver.getDriver().getWindowHandles()) {
            if (!originalWindowHandler.contentEquals(windowHandle)) {
                driver.browser().switchToWindow(windowHandle);
            }
        }
        String actualURL_contactUS2 = driver.browser().getCurrentURL();
        Validations.assertThat().object((Object) actualURL_contactUS2).isEqualTo((Object) "https://www.waffarx.com/en-us/help").perform();
    }

    @Test
    public void D_Check_that_All_UniversalLinking_InMyCashOut_WorkCorrectly()
    {
        Open_HelpPage();

        By MyCashOut_Choice = By.xpath("//*[@id=\"heatmapArea\"]/main/div[2]/div/div[1]/ul/li[5]/h3/a");
        driver.element().clickUsingJavascript(MyCashOut_Choice);

        //*************** link of My ContactUS **************** //
        By ContactUS_Link = By.xpath("//*[@id=\"1005\"]/div/p[6]/a");
        retryClick(ContactUS_Link, 30);

        By Title = By.xpath("//*[@id=\"heatmapArea\"]/main/div[2]/div/div[2]/div/h2");
        driver.element().verifyThat(Title).isVisible().perform();
        driver.browser().navigateBack();

        //*************** link of Account page **************** //
        By AccountPage_Link = By.xpath("//*[@id=\"1008\"]/div/p[2]/a");
        retryClick(AccountPage_Link, 30);

        By Update_name= By.linkText("Update Name") ;
        driver.element().verifyThat(Update_name).isVisible().perform();
        driver.browser().navigateBack();

        //*************** link of Account settings **************** //
        By AccountSettings_Link = By.xpath("//*[@id=\"1008\"]/div/b/p[23]/a");
        retryClick(AccountSettings_Link, 30);

        By Update_name2 = By.linkText("Update Name") ;
        driver.element().verifyThat(Update_name2).isVisible().perform();
    }

    @Test
    public void E_Check_that_All_UniversalLinking_In_EarningCashBack_WorkCorrectly()
    {
        Open_HelpPage();
        //*************** link of CustomerService **************** //
        By EarningCashBack_Choice = By.xpath("//*[@id=\"heatmapArea\"]/main/div[2]/div/div[1]/ul/li[6]/h3/a");
        driver.element().clickUsingJavascript(EarningCashBack_Choice);

        String originalWindowHandler = driver.browser().getWindowHandle();

        By CustomerService_Link = By.xpath("//*[@id=\"1004\"]/div/p[3]/a");
        retryClick(CustomerService_Link, 30);

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
        retryClick(CashbackPage_Link, 30);

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
