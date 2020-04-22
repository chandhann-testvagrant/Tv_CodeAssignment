package utils;

import generic.Constants;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class BasicActions
{
    private WebDriver driver;
    private WebDriverWait wait;
    protected BasicActions(WebDriver driver)
    {
        this.driver=driver;
        wait=new WebDriverWait(this.driver, Constants.WebDriverWaitInSec);
    }

    protected void clickByXpath(String xpath)
    {
        int attempts = 0;
        while(attempts < 5)
        {
            try
            {
                wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
                driver.findElement(By.xpath(xpath)).click();
                break;
            } catch(StaleElementReferenceException e){}

            attempts++;
        }
    }

    protected void click(WebElement ele)
    {
        int attempts = 0;
        while(attempts < 5)
        {
            try
            {
                wait.until(ExpectedConditions.elementToBeClickable(ele));
                ele.click();
                break;
            } catch(StaleElementReferenceException e){}

            attempts++;
        }
    }

    protected void clickByID(String id)
    {
        int attempts = 0;
        while(attempts < 5)
        {
            try
            {
                wait.until(ExpectedConditions.elementToBeClickable(By.id(id)));
                driver.findElement(By.id(id)).click();
                break;
            } catch(StaleElementReferenceException e){}

            attempts++;
        }
    }

    protected void typeByXpath(String xpath,String value)
    {
        int attempts = 0;
        while(attempts < 5)
        {
            try
            {
                wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
                driver.findElement(By.xpath(xpath)).sendKeys(value);
                break;
            } catch(StaleElementReferenceException e){}

            attempts++;
        }
    }
    
    protected boolean isDisplayed(WebElement element,int longInSec)
    {
        boolean flag=false;
        
        if(!waitTillElementVisible(element,longInSec)){
            return false;
        }
        
        for(int i=0; i<=2;i++){
            try{
                flag=element.isDisplayed();
                break;
            }catch(StaleElementReferenceException e){}
        }
        return flag;
    }
    
    protected boolean waitTillElementVisible(WebElement element,int timeOutInSec)
    {
        boolean flag=false;
        try{
            new WebDriverWait(driver,timeOutInSec).until(ExpectedConditions.visibilityOf(element));flag=true;}catch (TimeoutException e){}
        return flag;
    }
    
    protected void type(WebElement ele, String value)
    {
        int attempts = 0;
        while(attempts < 5)
        {
            try
            {
                wait.until(ExpectedConditions.visibilityOf(ele));
                ele.sendKeys(value);
                break;
            } catch(StaleElementReferenceException e){}

            attempts++;
        }
    }

    protected List<WebElement> findElementsByXpath(String xpath)
    {
        List<WebElement> ele_s=new ArrayList<WebElement>();
        int attempts = 0;
        while(attempts < 5)
        {
            try
            {
                ele_s= driver.findElements(By.xpath(xpath));
                break;
            } catch(StaleElementReferenceException e){}

            attempts++;
        }

        return ele_s;
    }

    protected List<WebElement> findElementsByTagName(String tagName)
    {
        List<WebElement> ele_s=new ArrayList<WebElement>();
        int attempts = 0;
        while(attempts < 5)
        {
            try
            {
                ele_s= driver.findElements(By.tagName(tagName));
                break;
            } catch(StaleElementReferenceException e){}

            attempts++;
        }

        return ele_s;
    }

    protected List<WebElement> findElementsByClassName(String className)
    {
        List<WebElement> ele_s=new ArrayList<WebElement>();
        int attempts = 0;
        while(attempts < 5)
        {
            try
            {
                ele_s= driver.findElements(By.className(className));
                break;
            } catch(StaleElementReferenceException e){}

            attempts++;
        }

        return ele_s;
    }

    protected String getTextByXpath(String xpath)
    {
        String text="";
        int attempts = 0;
        while(attempts < 5)
        {
            try
            {
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
               text= driver.findElement(By.xpath(xpath)).getText();
                break;
            } catch(StaleElementReferenceException e){}

            attempts++;
        }
        return text;
    }

    protected void waitForAlertAndAccept(long timeoutInSec)
    {
        new WebDriverWait(driver,timeoutInSec).until(ExpectedConditions.alertIsPresent()).accept();
    }
    
    protected WebDriver getDriverInstance()
    {
        return driver;
    }
    
    protected <T extends BasicActions> void reinitializePage(T obj)
    {
        
        PageFactory.initElements(getDriverInstance(),obj);
        
        
    }
}
