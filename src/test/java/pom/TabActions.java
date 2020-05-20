package pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.BasicActions;

public class TabActions extends BasicActions
{
    public TabActions(WebDriver driver)
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
    
    public CartPage tabClickOnCart()
    {
        click(cartButton);
        return new CartPage(getDriverInstance());
    }
    
    public HomePage tabClickOnHome()
    {
        click(homeButton);
        return  new HomePage(getDriverInstance());
    }
    
   
    
    
    
    
    







}
