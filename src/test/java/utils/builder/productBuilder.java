package utils.builder;

import model.product;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.basicActions;

public class productBuilder extends basicActions
{
    public productBuilder(WebDriver driver)
    {
        super(driver);
        reinitializePage(this);
    }
    
    product product= new product();
    
    @FindBy(xpath = "//*[@id=\"ProductSection-product-template\"]/div/div[2]/div[1]/h1") private WebElement productName;
    @FindBy(xpath = "//*[@id=\"ProductSection-product-template\"]/div/div[2]/div[1]/div/dl/div[1]/div[1]/dd/span") private WebElement productPrice;
    @FindBy(id = "SingleOptionSelector-0") private WebElement productColor;
    @FindBy(id = "SingleOptionSelector-1") private WebElement productSize;
    
    
    public product autoBuildProduct()
    {
        product.setProductName(getText(productName));
        product.setProductColour(getFirstSelectedOption(productColor));
        double price=Double.valueOf(getText(productPrice).trim().replaceAll("Rs. ","").replaceAll(",",""));
        product.setProductPrice(price);
        product.setProductSize(getFirstSelectedOption(productSize));
        return product;
    }


}
