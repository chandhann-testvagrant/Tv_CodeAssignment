package pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class productPage extends tabActions
{
    public productPage(WebDriver driver)
    {
        super(driver);
        PageFactory.initElements(driver,this);
    }
    
    
    
    
    @FindBy(xpath = "//span[contains(text(),'Add to cart')]/parent::button") private WebElement addToCartButton;
    @FindBy(xpath = "/html/body/div[1]/div/div[1]/button") private WebElement closeAddedCartPopup;
    
   
    
    public productPage clickOnAddToCart()
    {
        click(addToCartButton);
        click(closeAddedCartPopup);
        
        
        return this;
    }
    
    
    
    
    







}
