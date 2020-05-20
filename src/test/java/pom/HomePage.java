package pom;

import model.Cart;
import model.Product;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class HomePage extends TabActions
{
    HomePage(WebDriver driver)
    {
        super(driver);
        PageFactory.initElements(driver,this);
    }

    
    @FindBy(xpath = "//*[@id='SearchDrawer']/div/div/form/div/input[1]") private WebElement searchField;
    @FindBy(xpath = "//h2[text()='Featured collection']") private WebElement featuredCollectionField;
    @FindBy(xpath = "//h2[text()='Featured collection']/../../ul/li") private WebElement firstProductInFeaturedCollectionField;
    @FindBys({
            @FindBy(xpath="//*[@id='MainContent']/ul/li/div/div/div[2]//span")
    })
    private List<WebElement> searchResult;
    
    @FindBys({
            @FindBy(xpath="//*[@id='MainContent']/ul/li/div/a")
    })
    private List<WebElement> searchResultLink;
    
    private String searchTerm;
    public HomePage search(String searchTerm)
    {
        this.searchTerm=searchTerm;
        tabClickOnSearch();
        type(searchField,searchTerm);
        type(searchField, Keys.ENTER);
        return this;
    }
    
    
    public HomePage verifySearchResult()
    {
        reinitializePage(this);
        for(WebElement element: searchResult)
        {
           System.out.println(getText(element)+searchTerm);
            assertTrueWithScreenshot(getText(element).toLowerCase().contains(searchTerm.toLowerCase().trim()),"Result field name contains "+searchTerm.toLowerCase().trim());
        }
        return this;
        
    }
    
    public ProductPage openFirstResult()
    {
        click(searchResultLink.get(0));
        return new ProductPage(getDriverInstance());
    }
    
    public Cart AddMultipleProducts(List<Product> products, Cart cart)
    {
        for(Product product:products)
        {
            ProductPage productPage=search(product.getName())
                    .verifySearchResult()
                    .openFirstResult();
    
                cart=productPage.addProductToCart(product.getSize(),product.getColor(),cart);
                productPage.tabClickOnHome();
        }
        
        
        return cart;
    }
    
    public HomePage scrollToFeaturedCollection()
    {
        scrollTOElement(featuredCollectionField);
        return this;
    }
    
    public ProductPage openFirstFeaturedProduct()
    {
        click(firstProductInFeaturedCollectionField);
        return new ProductPage(getDriverInstance());
    }
    
    
    
    
    







}
