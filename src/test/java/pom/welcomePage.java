package pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class welcomePage extends tabActions
{
    private welcomePage(WebDriver driver)
    {
        super(driver);
        PageFactory.initElements(driver,this);
    }
    
    private static int count =0;
    private static welcomePage obj;
    public static welcomePage getInstance(WebDriver driver){
        
        if(count==0){
            obj=new welcomePage(driver);
            count++;
        }
        
        return obj;
    }
    
    @FindBy(xpath = "/html/body/div[1]/header/div/div/a") WebElement enterYourPasswordButton;
    @FindBy(xpath = "//*[@id='Password']") WebElement passwordField;
    @FindBy(xpath = "//*[@id='login_form']/div/span/button") WebElement enterButton;
   

    public homePage enterPassword(String password)
    {
        click(enterYourPasswordButton);
        type(passwordField,password);
        click(enterButton);
        return homePage.getInstance(getDriverInstance());
    }








}
