package pom;

import model.Cart;
import model.Product;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import utils.TextHelper;

import java.util.Iterator;
import java.util.List;

public class CartPage extends TabActions
{
    CartPage(WebDriver driver)
    {
        super(driver);
        
        PageFactory.initElements(driver,this);
    }
    
    
    @FindBys(@FindBy(xpath = "//tr[@class='cart__row']//td[3]//input")) private List<WebElement> quantityOfItemList;
    @FindBys(@FindBy(xpath = "//tr[@class='cart__row']//td[4]/div/span"))  private List<WebElement> totalPriceOfItemList;
    @FindBys(@FindBy(xpath = "//tr[@class='cart__row']//td[2]//div[@data-cart-item-regular-price-group='']//dd"))  private List<WebElement> priceOfItemList;
    @FindBys(@FindBy(xpath = "//tr[@class='cart__row']//td[1]//li[1]")) private List<WebElement> colourOfItemList;
    @FindBys({@FindBy(xpath = "//tr[@class='cart__row']//td[1]//li[2]")})    private List<WebElement> sizeOfItemList;
    @FindBys({@FindBy(xpath = "//tr[@class='cart__row']//td[1]//div/a")})    private List<WebElement> nameOfItemList;
   
    
    public CartPage increaseQuantityOfProductByOne(Product product)
    {
        int i=0;
        Iterator<WebElement> names = nameOfItemList.iterator();
        while(names.hasNext())
        {
            if(getText(names.next()).equalsIgnoreCase(product.getName()))
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
    
    public CartPage verifyProductDetail(Product product)
    {
        int i=0;
        for(WebElement name:nameOfItemList)
        {
            String expectedName=product.getName();
            String expectedColor=product.getColor();
            String expectedSize=product.getSize();
            
            String actualName=getText(name);
            String actualColor=TextHelper.cleanColor(getText(colourOfItemList.get(i)));
            String actualSize=TextHelper.cleanSize(getText(sizeOfItemList.get(i)));
            
            if(actualName.equalsIgnoreCase(expectedName) && expectedColor.equalsIgnoreCase(actualColor) && expectedSize.equalsIgnoreCase(actualSize))
            {
                double total= TextHelper.cleanPrice(getText(totalPriceOfItemList.get(i)));
                double price=TextHelper.cleanPrice(getText(priceOfItemList.get(i)));
                
                Double expectedTotal=Integer.valueOf(getValue(quantityOfItemList.get(i)))*price;
                assertWithScreenshot(expectedTotal+"",total+"","Verifying the total field");
                
                assertWithScreenshot(product.getPrice()+"",price+"","verification of product price");
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
    
    public CartPage verifyCart(Cart cart)
    {
        List<Product> productslist= cart.getProductsFromCart();
        for (Product product:productslist)
        {
            verifyProductDetail(product);
        }
       
        return this;
    }
    
    
    
    
    
    







}
