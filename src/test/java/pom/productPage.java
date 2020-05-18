package pom;

import model.cart;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.builder.productBuilder;

public class productPage extends tabActions
{
    productPage(WebDriver driver)
    {
        super(driver);
        PageFactory.initElements(driver,this);
    }
    
    @FindBy(xpath = "//span[contains(text(),'Add to cart')]/parent::button") private WebElement addToCartButton;
    @FindBy(xpath = "/html/body/div[1]/div/div[1]/button") private WebElement closeAddedCartPopup;
    
    @FindBy(id = "SingleOptionSelector-1") private WebElement productSize;
    @FindBy(id = "SingleOptionSelector-0") private WebElement productColor;
    
    public productPage clickOnAddToCart()
    {
        click(addToCartButton);
        click(closeAddedCartPopup);
        cart.addProductToCart(new productBuilder(getDriverInstance()).autoBuildProduct());
        return this;
    }
    
    public productPage addProductToCart(String size, String Colour)
    {
        selectByVisibleText(productSize,size);
        selectByVisibleText(productColor,Colour);
        clickOnAddToCart();
        return this;
    }
    
    
    
 
}
