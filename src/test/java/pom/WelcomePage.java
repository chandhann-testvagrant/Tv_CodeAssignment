package pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.BasicActions;

public class WelcomePage extends BasicActions
{
    public WelcomePage(WebDriver driver)
    {
        super(driver);
        PageFactory.initElements(driver,this);
    }
    @FindBy(xpath = "/html/body/div[1]/header/div/div/a") WebElement enterYourPasswordButton;
    @FindBy(xpath = "//*[@id='Password']") WebElement passwordField;
    @FindBy(xpath = "//*[@id='login_form']/div/span/button") WebElement enterButton;
   

    public void enterPassword(String password)
    {
        click(enterYourPasswordButton);
        type(passwordField,password);
        click(enterButton);

    }








}
