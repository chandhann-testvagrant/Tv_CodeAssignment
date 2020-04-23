package pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.basicActions;

public class tabActions extends basicActions
{
    public tabActions(WebDriver driver)
    {
        super(driver);
        PageFactory.initElements(driver,this);
    }
    
    
    @FindBy(xpath = "//*[@id='shopify-section-header']/div[2]/header/div/div[2]/div/button[1]") private WebElement searchButton;
    @FindBy(xpath = "//*[@id='shopify-section-header']/div[2]/header/div/div[2]/div/a") private WebElement cartButton;
    @FindBy(xpath = "//*[@id='SiteNav']/li[1]/a") private WebElement homeButton;
    
    private String searchTerm;
    
    public void tabClickOnSearch()
    {
        click(searchButton);
    }
    
    public cartPage tabClickOnCart()
    {
        click(cartButton);
        return new cartPage(getDriverInstance());
    }
    
    public homePage tabClickOnHome()
    {
        click(homeButton);
        return new homePage(getDriverInstance());
    }
    
   
    
    
    
    
    







}
