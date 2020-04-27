package pom;

import model.product;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class cartPage extends tabActions
{
    public cartPage(WebDriver driver)
    {
        super(driver);
        PageFactory.initElements(driver,this);
    }
    
    
    
    
    @FindBys(@FindBy(xpath = "//tr[@class='cart__row']//td[3]//input")) private List<WebElement> quantityOfItemList;
    @FindBys (@FindBy(xpath = "//tr[@class='cart__row']//td[4]/div/span"))  private List<WebElement> totalPriceOfItemList;
    @FindBys (@FindBy(xpath = "//tr[@class='cart__row']//td[2]//div[@data-cart-item-regular-price-group='']//dd"))  private List<WebElement> priceOfItemList;
    @FindBys(@FindBy(xpath = "//tr[@class='cart__row']//td[1]//li[1]")) private List<WebElement> colourOfItemList;
    @FindBys({@FindBy(xpath = "//tr[@class='cart__row']//td[1]//li[2]")})    private List<WebElement> sizeOfItemList;
    @FindBys({@FindBy(xpath = "//tr[@class='cart__row']//td[1]//div/a")})    private List<WebElement> nameOfItemList;
    
   
    
    public cartPage increaseQuantityOfProductByone(product product)
    {
        int i=0;
        for(WebElement name:nameOfItemList)
        {
            if(getText(name).equalsIgnoreCase(product.getProductName()))
            {
                int quantity=Integer.valueOf(getValue(quantityOfItemList.get(i)));
                quantity++;
                type(quantityOfItemList.get(i),quantity+"");
                sleep(1000);
                click(totalPriceOfItemList.get(i));
                break;
            }
            i++;
        }
        
        return this;
    }
    
    public cartPage verifyProductDetail(product product)
    {
        int i=0;
        for(WebElement name:nameOfItemList)
        {
            if(getText(name).equalsIgnoreCase(product.getProductName()) && product.getProductColour().equalsIgnoreCase(getText(colourOfItemList.get(i)).replaceAll("Color: ","")) && product.getProductSize().equalsIgnoreCase(getText(sizeOfItemList.get(i)).replaceAll("Size: ","")))
            {
                double total=Double.valueOf(getText(totalPriceOfItemList.get(i)).trim().replaceAll("Rs. ","").replaceAll(",",""));
                double price=Double.valueOf(getText(priceOfItemList.get(i)).trim().replaceAll("Rs. ","").replaceAll(",",""));
    
                Double expectedTotal=Integer.valueOf(getValue(quantityOfItemList.get(i)))*price;
                assertWithScreenshot(expectedTotal+"",total+"","Verifying the total field");
                
                assertWithScreenshot(product.getProductPrice()+"",Double.valueOf(getText(priceOfItemList.get(i)).trim().replaceAll("Rs. ","").replaceAll(",",""))+"","verification of product price");
                break;
            }
            else if(i==nameOfItemList.size()-1)
            {
                assertTrueWithScreenshot(false,"Verifying the product is present in cart");
            }
            i++;
        }
        
       
        return this;
    }
    
    
    
    
    
    







}
