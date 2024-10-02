package WaffarXWEB;

import com.shaft.driver.SHAFT;
import com.shaft.validation.Validations;
import org.openqa.selenium.By;
import org.testng.annotations.*;
import static org.openqa.selenium.Keys.ENTER;

public class Cashout_Methods
{
    SHAFT.GUI.WebDriver driver;

    @BeforeMethod
    public void setupBrowser()
    {
        driver = new SHAFT.GUI.WebDriver(); // to open browser
        driver.browser().navigateToURL("https://portal-test.waffarx.com/en-eg"); //to navigate to URL
    }

    private void Open_AccountSettings_Page()
    {
        By Register_Button = By.xpath("//*[@id='heatmapArea']/main/div[2]/div[1]/button");
        driver.element().waitToBeReady(Register_Button); // Wait for the button to be clickable
        driver.element().clickUsingJavascript(Register_Button); // To force click on this element

        By AlreadyMember_Button = By.xpath("//*[@id='newSignUp']/div/div/div/div[4]/a") ;
        driver.element().clickUsingJavascript(AlreadyMember_Button); // To force click on this element

        By Email = By.id("LoginEmail");
        driver.element().type(Email, "ahmedyahia529@gmail.com");

        By Password = By.id("LoginPassword");
        driver.element().type(Password, "Ay555555");

        By SignIN_Button = By.xpath("//*[@id=\"Login\"]/div[4]/input");
        driver.element().keyPress(SignIN_Button, ENTER);

        By userinfo = By.xpath("//*[@id=\"fixedHeader\"]/div[3]/div/div[2]/div[3]") ;
        driver.element().hover(userinfo);

        By AccountSettings_choice= By.linkText("Account Settings") ;
        driver.element().click(AccountSettings_choice);
    }

    private void Open_Credit()
    {
        Open_AccountSettings_Page();
        By CashOutList = By.xpath("//*[@id=\"ChooseMethodDiv\"]/div[2]/div/div/div[1]/div") ;
        driver.element().select(CashOutList, "Credit Card");
    }

    @Test(priority = 1)
    public void CreditCard_Name_SymbolsAndChars()
    {
        Open_Credit() ;
        By Name = By.id("AccountHolderName");
        driver.element().type(Name , "@#$555");
        By Update = By.id("UpdateCreditCard");
        driver.element().scrollToElement(Update);
        driver.element().click(Update) ;
        By Error = By.id("AccountHolderName-error");
        driver.element().verifyThat(Error).text().isEqualTo("Sorry, Arabic and special characters are not allowed, please make sure you enter a valid input.").perform();
    }

    @Test(priority = 2)
    public void CreditCard_Error_WhenInsert_WrongLimitation_InCardNumber()
    {
        Open_Credit() ;
//        By Name = By.id("AccountHolderName");
//        driver.element().type(Name, "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
//
//        By BankList = By.id("CreditBankID") ;
//        driver.element().select(BankList, "Arab African International Bank");
//
//        By BranchList = By.id("CreditBranchID") ;
//        driver.element().select(BranchList, "ROXY");

        By CreditCardNumber = By.id("CreditCardNo") ;
        driver.element().scrollToElement(CreditCardNumber);
        driver.element().type(CreditCardNumber,"12345");

//        By declare_checkbox = By.id("CreditIAgree");
//        driver.element().click(declare_checkbox) ;

        By Update = By.id("UpdateCreditCard");
        driver.element().scrollToElement(Update);
        driver.element().click(Update) ;

        By Error = By.id("CreditCardNo-error");
        driver.element().verifyThat(Error).text().isEqualTo("Credit card minimum length is 16 character").perform();
    }

//    @AfterMethod
//    public void CloseDriver()
//    {
//        if (driver != null)
//        {
//            driver.quit();
//        }
//    }
}

//    private void Open_Fawry()
//    {
//        Login();
//        By CashOutList = By.xpath("//*[@id=\"ChooseMethodDiv\"]/div[2]/div/div/div[1]/div") ;
//        driver.element().select(CashOutList, "Fawry");
//    }
//
//    private void Open_EWallet()
//    {
//        Login();
//        By CashOutList = By.xpath("//*[@id=\"ChooseMethodDiv\"]/div[2]/div/div/div[1]/div") ;
//        driver.element().select(CashOutList, "E-Wallets");
//    }


//    @Test(priority = 1)
//    public void Fawry_UpdateName_SymbolsAndChars()
//    {
//        Open_Fawry() ;
//        By Name = By.id("FawryName");
//        driver.element().type(Name, "@#@##3333");
//
//        By Mobile = By.id("FawryNumber");
//        driver.element().click(Mobile) ;
//
//        By Error = By.id("FawryName-error");
//        driver.element().verifyThat(Error).text().isEqualTo("Sorry, Arabic and special characters are not allowed, please make sure you enter a valid input.").perform();
//    }
//
//    @Test(priority = 1)
//    public void Fawry_Name_Emtpy()
//    {
//        Open_Fawry() ;
//
//        By Name = By.id("FawryName");
//        driver.element().type(Name, " ");
//
//        By Update = By.id("UpdateFawryBtn");
//        driver.element().click(Update) ;
//
//        By Error = By.id("FawryName-error");
//        driver.element().verifyThat(Error).text().isEqualTo("This field is required.").perform();
//    }

//    @Test(priority = 2)
//    public void Fawry_UpdateName_EmptyFields()
//    {
//        Open_Fawry() ;
////        By Name = By.id("FawryName");
////        driver.element().type(Name, "");
////
////        By Mobile = By.id("FawryNumber");
////        driver.element().type(Mobile, "01277249447");
//
//        By robot = By.xpath("//*[@id=\"recaptcha-anchor\"]/div[1]");
//        driver.element().click(robot) ;
//
////        By Update_Button = By.id("UpdateFawryBtn");
////        driver.element().click(Update_Button) ;
//    }

//    @Test(priority = 1)
//    public void EWallet_Name_SymbolsAndChars()
//    {
//        Open_EWallet() ;
//
//        By Name = By.id("WalletName");
//        driver.element().type(Name, "@#@##3333");
//
//        By Update = By.id("UpdateWalletBtn");
//        driver.element().click(Update) ;
//
//        By Error = By.id("WalletName-error");
//        driver.element().verifyThat(Error).text().isEqualTo("Sorry, Arabic and special characters are not allowed, please make sure you enter a valid input.").perform();
//    }
