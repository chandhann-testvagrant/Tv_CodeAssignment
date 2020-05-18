package pom;

import model.product;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class homePage extends tabActions
{
    homePage(WebDriver driver)
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
    public homePage search(String searchTerm)
    {
        this.searchTerm=searchTerm;
        tabClickOnSearch();
        type(searchField,searchTerm);
        type(searchField, Keys.ENTER);
        return this;
    }
    
    
    public homePage verifySearchResult()
    {
        reinitializePage(this);
        for(WebElement element: searchResult)
        {
           System.out.println(getText(element)+searchTerm);
            assertTrueWithScreenshot(getText(element).toLowerCase().contains(searchTerm.toLowerCase().trim()),"Result field name contains "+searchTerm.toLowerCase().trim());
        }
        return this;
        
    }
    
    public productPage openFirstResult()
    {
        click(searchResultLink.get(0));
        return new productPage(getDriverInstance());
    }
    
    public homePage AddMultipleProducts(List<product> products)
    {
        for(product product:products)
        {
            search(product.getProductName())
                    .verifySearchResult()
                    .openFirstResult()
                    .addProductToCart(product.getProductSize(),product.getProductColour())
                    .tabClickOnHome();
        }
        
        
        return this;
    }
    
    public homePage scrollToFeaturedCollection()
    {
        scrollTOElement(featuredCollectionField);
        return this;
    }
    
    public productPage openFirstFeaturedProduct()
    {
        click(firstProductInFeaturedCollectionField);
        return new productPage(getDriverInstance());
    }
    
    
    
    
    







}
