package utils;

import generic.Constants;
import io.github.bonigarcia.wdm.DriverManagerType;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.util.concurrent.TimeUnit;

public class BaseTest extends Constants
{
    protected WebDriver driver ;
    @BeforeSuite
    public void configuration()
    {
        String bType=System.getProperty("browserType");
        
        System.out.println("df"+bType);
        
        switch (bType.toLowerCase()){
            case "chrome":
                WebDriverManager.getInstance(DriverManagerType.CHROME).setup();
                driver = new ChromeDriver();
                break;
            case "firefox":
                WebDriverManager.getInstance(DriverManagerType.FIREFOX).setup();
                driver = new FirefoxDriver();
                break;
            
                
        }
        
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(url);
    }

    @AfterSuite
    public void tearDown()
    {
        driver.quit();
    }
}
