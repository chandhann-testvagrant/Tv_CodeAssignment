package pom;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class homePage extends tabActions
{
    private homePage(WebDriver driver)
    {
        super(driver);
        PageFactory.initElements(driver,this);
    }
    
    private static int count =0;
    private static homePage obj;
    public static homePage getInstance(WebDriver driver){
        
        if(count==0||obj.getSessionID()==null){
            obj=new homePage(driver);
            count++;
        }
        
        return obj;
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
        return getInstance(getDriverInstance());
    }
    
    
    public homePage verifySearchResult()
    {
        reinitializePage(this);
        for(WebElement element: searchResult)
        {
            
           System.out.println(getText(element)+searchTerm);
            assertTrueWithScreenshot(getText(element).toLowerCase().contains(searchTerm.toLowerCase().trim()),"Result field name contains "+searchTerm.toLowerCase().trim());
        }
        return getInstance(getDriverInstance());
        
    }
    
    public productPage openFirstResult()
    {
        click(searchResultLink.get(0));
        return productPage.getInstance(getDriverInstance());
    }
    
    public homePage scrollToFeaturedCollection()
    {
        scrollTOElement(featuredCollectionField);
        return getInstance(getDriverInstance());
    }
    
    public productPage openFirstFeaturedProduct()
    {
        click(firstProductInFeaturedCollectionField);
        return productPage.getInstance(getDriverInstance());
    }
    
    
    
    
    







}
