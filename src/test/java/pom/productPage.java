package pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class productPage extends tabActions
{
    private productPage(WebDriver driver)
    {
        super(driver);
        PageFactory.initElements(driver,this);
    }
    
    private static int count =0;
    private static productPage obj;
    public static productPage getInstance(WebDriver driver){
        
        if(count==0){
            obj=new productPage(driver);
            count++;
        }
        
        return obj;
    }
    
    
    @FindBy(xpath = "//span[contains(text(),'Add to cart')]/parent::button") private WebElement addToCartButton;
    @FindBy(xpath = "/html/body/div[1]/div/div[1]/button") private WebElement closeAddedCartPopup;
    
    @FindBy(id = "SingleOptionSelector-1") private WebElement productSize;
    @FindBy(id = "SingleOptionSelector-0") private WebElement productColor;
    
    public productPage clickOnAddToCart()
    {
        click(addToCartButton);
        click(closeAddedCartPopup);
        return getInstance(getDriverInstance());
    }
    
    public productPage addProductToCart(String size, String Colour)
    {
        selectByVisibleText(productSize,size);
        selectByVisibleText(productColor,Colour);
        
        click(addToCartButton);
        click(closeAddedCartPopup);
        return getInstance(getDriverInstance());
    }
    
 
}
