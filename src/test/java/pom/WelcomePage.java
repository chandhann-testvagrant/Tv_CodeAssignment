package pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class WelcomePage extends TabActions
{
    public WelcomePage(WebDriver driver)
    {
        super(driver);
        PageFactory.initElements(driver,this);
    }
    
 
    
    @FindBy(xpath = "/html/body/div[1]/header/div/div/a") WebElement enterYourPasswordButton;
    @FindBy(xpath = "//*[@id='Password']") WebElement passwordField;
    @FindBy(xpath = "//*[@id='login_form']/div/span/button") WebElement enterButton;
   

    public HomePage enterPassword(String password)
    {
        click(enterYourPasswordButton);
        type(passwordField,password);
        click(enterButton);
        return new HomePage(getDriverInstance());
    }








}
