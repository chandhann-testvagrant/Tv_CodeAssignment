package pom;

import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import utils.BasicActions;

import java.util.List;

public class SearchResultPage extends BasicActions
{
    public SearchResultPage(WebDriver driver)
    {
        super(driver);
        PageFactory.initElements(driver,this);
    }

    @FindBys({
            @FindBy(xpath = "//div[@class='card']")
    })
    public List<WebElement> searchResult;

    public int getResultCount()
    {
        return searchResult.size();
    }


    @FindBys({
            @FindBy(tagName = "h2")
    })
    public List<WebElement> searchResult_name;
    @FindBys({
            @FindBy(xpath = "//p[@class='card__text card__text--gray']")
    })
    public List<WebElement> searchResult_tagLine;
    @FindBys({
            @FindBy(xpath = "//p[@class='card__text card__text--sm']")
    })
    public List<WebElement> searchResult_brewedDate;
    @FindBys({
            @FindBy(xpath = "//div[@class='card']//p[2]")
    })
    public List<WebElement> searchResult_description;

    public JSONObject getResultValues()
    {
        int resultCount=getResultCount();
        JSONObject result= new JSONObject();
        for(int i=0;i<resultCount;i++)
        {
            JSONObject temp=new JSONObject();

            String name=searchResult_name.get(i).getText();
            temp.put("name",name);
            temp.put("tagline",searchResult_tagLine.get(i).getText());
            temp.put("first_brewed",searchResult_brewedDate.get(i).getText());
            temp.put("description",searchResult_description.get(i).getText());
            result.put(name,temp);
        }

        return result;

    }







}
