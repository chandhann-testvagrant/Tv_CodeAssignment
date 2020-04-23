package pom;

import model.product;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class cartPage extends tabActions
{
    public cartPage(WebDriver driver)
    {
        super(driver);
        PageFactory.initElements(driver,this);
    }
    
    
    
    
    @FindBy(xpath = "//tr[@class='cart__row']//td[3]//input") private WebElement quantityOfFirstItem;
    @FindBy(xpath = "//tr[@class='cart__row']//td[4]/div/span") private WebElement totalPriceOfFirstItem;
    @FindBy(xpath = "//tr[@class='cart__row']//td[2]//div[@data-cart-item-regular-price-group='']//dd") private WebElement priceOfFirstItem;
    @FindBy(xpath = "//tr[@class='cart__row']//td[1]//li[1]") private WebElement colourOfFirstItem;
    @FindBy(xpath = "//tr[@class='cart__row']//td[1]//li[2]") private WebElement sizeOfFirstItem;
    @FindBy(xpath = "//tr[@class='cart__row']//td[1]//div/a") private WebElement nameOfFirstItem;
    
   
    
    public cartPage increaseQuantityOfFirstProductByone()
    {
        int quantity=Integer.valueOf(getValue(quantityOfFirstItem));
        quantity++;
        type(quantityOfFirstItem,quantity+"");
        sleep(1000);
        click(totalPriceOfFirstItem);
        return this;
    }
    
    public cartPage verifyTotalPrice()
    {
        double total=Double.valueOf(getText(totalPriceOfFirstItem).trim().replaceAll("Rs. ","").replaceAll(",",""));
        double price=Double.valueOf(getText(priceOfFirstItem).trim().replaceAll("Rs. ","").replaceAll(",",""));
        
        Double expectedTotal=Integer.valueOf(getValue(quantityOfFirstItem))*price;
        assertWithScreenshot(expectedTotal+"",total+"","Verifying the total field");
        
        return this;
    }
    
    public cartPage verifyProductDetailofFirst(product product)
    {
        assertWithScreenshot(product.getProductName(),getText(nameOfFirstItem),"verification of product name");
        assertWithScreenshot(product.getProductColour(),getText(colourOfFirstItem).replaceAll("Color: ",""),"verification of product colour");
        assertWithScreenshot(product.getProductSize(),getText(sizeOfFirstItem).replaceAll("Size: ",""),"verification of product size");
        assertWithScreenshot(product.getProductPrice()+"",Double.valueOf(getText(priceOfFirstItem).trim().replaceAll("Rs. ","").replaceAll(",",""))+"","verification of product price");
        return this;
    }
    
    
    
    
    
    







}
