package pom;

import model.Cart;
import model.Product;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.TextHelper;
import utils.builder.ProductBuilder;

public class ProductPage extends TabActions
{
    ProductPage(WebDriver driver)
    {
        super(driver);
        PageFactory.initElements(driver,this);
    }
    
    @FindBy(xpath = "//span[contains(text(),'Add to cart')]/parent::button") private WebElement addToCartButton;
    @FindBy(xpath = "/html/body/div[1]/div/div[1]/button") private WebElement closeAddedCartPopup;
    
    @FindBy(id = "SingleOptionSelector-1") private WebElement productSize;
    @FindBy(id = "SingleOptionSelector-0") private WebElement productColor;
    
    public Cart clickOnAddToCart(Cart cart)
    {
        click(addToCartButton);
        click(closeAddedCartPopup);
        
       return  cart.addProductToCart(autoBuildProduct());
       
    }
    
    public Cart addProductToCart(String size, String Colour,Cart cart)
    {
        selectByVisibleText(productSize,size);
        selectByVisibleText(productColor,Colour);
        return clickOnAddToCart(cart);
    }
    
    @FindBy(xpath = "//*[@id=\"ProductSection-product-template\"]/div/div[2]/div[1]/h1") private WebElement productName;
    @FindBy(xpath = "//*[@id=\"ProductSection-product-template\"]/div/div[2]/div[1]/div/dl/div[1]/div[1]/dd/span") private WebElement productPrice;
    
    public Product autoBuildProduct()
    {
        
        
        Product product= new  ProductBuilder()
                .withName(getText(productName))
                .withColor(getFirstSelectedOption(productColor))
                .withSize(getFirstSelectedOption(productSize))
                .withPrice(TextHelper.cleanPrice(getText(productPrice)))
                .build();
       
        return product;
    }
    
    
 
}
