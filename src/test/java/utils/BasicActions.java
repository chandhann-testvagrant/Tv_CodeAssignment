package utils;

import generic.Constants;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.SessionId;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import java.io.File;
import java.io.IOException;
import java.util.Date;

public class BasicActions
{
    private  WebDriver driver;
    private WebDriverWait wait;
    public BasicActions(WebDriver driver)
    {
        this.driver=driver;
        wait=new WebDriverWait(this.driver, Constants.WebDriverWaitInSec);
    }
    
    public <TPage > TPage getInstance(Class<TPage> pageClass) {
        try {
            
            return PageFactory.initElements(driver, pageClass);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
    
    
    public byte[] takeScreenshot(String name)
    {
        System.setProperty("org.uncommons.reportng.escape-output", "false");
        String currentPath=System.getProperty("user.dir")+"/screenshot";
        File DestFile=new File(currentPath+"/"+name+".png");

        //Convert web driver object to TakeScreenshot
        TakesScreenshot scrShot =((TakesScreenshot)driver);
        //Call getScreenshotAs method to create image file
        File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
    
        try {
            FileUtils.copyFile(SrcFile, DestFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    
        // Add screenshot name in report in order to attach screenshot in ReportNg
        Reporter.log("<a href=\"" + name + "\"><p align=\"left\">Error screenshot at " + new Date()+ "</p>");
        Reporter.log("<p><img width=\"1024\" src=\"" + DestFile.getAbsoluteFile() + "\" alt=\"screenshot at " + new Date()+ "\"/></p></a><br />");
    
        return scrShot.getScreenshotAs(OutputType.BYTES);
        
    }

    
    
    public void assertWithScreenshot(String expected,String actual,String field)
    {
        if(!expected.equals(actual))
        {
            takeScreenshot(field);
            Assert.assertEquals(actual,expected,field);
        }
    }
    
    public void assertTrueWithScreenshot(boolean flag,String field)
    {
        if(!flag)
        {
            takeScreenshot(field);
            Assert.assertTrue(flag,field);
        }
    }
    
  

    protected  void click(WebElement ele)
    {
        int attempts = 0;
        while(attempts < 5)
        {
            try
            {
                wait.until(ExpectedConditions.elementToBeClickable(ele)).click();
                break;
            } catch(StaleElementReferenceException e){}

            attempts++;
        }
    }
    
    protected  void selectByVisibleText(WebElement ele,String text)
    {
        int attempts = 0;
        while(attempts < 5)
        {
            try
            {
               
                wait.until(ExpectedConditions.elementToBeClickable(ele));
                new Select(ele).selectByVisibleText(text);
                break;
            } catch(StaleElementReferenceException e){}
            
            attempts++;
        }
    }
    
    
    protected String getText(WebElement ele)
    {
        int attempts = 0;
        String text = "";
        while(attempts < 5)
        {
            try
            {
                wait.until(ExpectedConditions.visibilityOf(ele));
                text= ele.getText();
                break;
            } catch(StaleElementReferenceException e){}
            
            attempts++;
        }
        return text.trim();
    }
    
    protected String getFirstSelectedOption(WebElement ele)
    {
        int attempts = 0;
        String text = "";
        while(attempts < 5)
        {
            try
            {
                wait.until(ExpectedConditions.visibilityOf(ele));
                text= new Select(ele).getFirstSelectedOption().getText();
                break;
            } catch(StaleElementReferenceException e){}
            
            attempts++;
        }
        return text.trim();
    }
    
    protected void scrollTOElement(WebElement element)
    {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
       
    }
    
    protected String getValue(WebElement ele)
    {
        int attempts = 0;
        String text = "";
        while(attempts < 5)
        {
            try
            {
                wait.until(ExpectedConditions.elementToBeClickable(ele));
                text= ele.getAttribute("value");
                break;
            } catch(StaleElementReferenceException e){}
            
            attempts++;
        }
        return text.trim();
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
    
    protected boolean waitTillElementClickable(WebElement element,int timeOutInSec)
    {
        boolean flag=false;
        try{
            new WebDriverWait(driver,timeOutInSec).until(ExpectedConditions.elementToBeClickable(element));flag=true;}catch (TimeoutException e){}
        return flag;
    }
    
    public void sleep(long timeOutInMilliSec)
    {
        try{Thread.sleep(timeOutInMilliSec);}catch (InterruptedException e){}
    }
    
    protected void type(WebElement ele, String value)
    {
        int attempts = 0;
        while(attempts < 5)
        {
            try
            {
                wait.until(ExpectedConditions.visibilityOf(ele)).clear();
                ele.sendKeys(value);
                break;
            } catch(StaleElementReferenceException e){}

            attempts++;
        }
    }
    
    protected void type(WebElement ele, Keys keys)
    {
        int attempts = 0;
        while(attempts < 5)
        {
            try
            {
                wait.until(ExpectedConditions.visibilityOf(ele));
                ele.sendKeys(keys.ENTER);
                break;
            } catch(StaleElementReferenceException e){}
            
            attempts++;
        }
    }

    protected void waitForAlertAndAccept(long timeoutInSec)
    {
        new WebDriverWait(driver,timeoutInSec).until(ExpectedConditions.alertIsPresent()).accept();
    }
    
    protected  WebDriver getDriverInstance()
    {
        return driver;
    }
    
    protected SessionId getSessionID(){
        SessionId sessionid = ((RemoteWebDriver) driver).getSessionId();
        return sessionid;
    
    }
    protected <T extends BasicActions> void reinitializePage(T obj)
    {
        
        PageFactory.initElements(getDriverInstance(),obj);
        
        
    }
}
