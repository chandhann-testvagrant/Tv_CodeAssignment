package utils;

import generic.Constants;
import io.github.bonigarcia.wdm.DriverManagerType;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;

public class DriverBuilder {
    private String browser;
    DriverBuilder(String browser){
    this.browser=browser;
    }
    
    
    public WebDriver getDriver(){
        WebDriver driver=null;
        switch (browser.toLowerCase()){
            case "chrome":
                WebDriverManager.getInstance(DriverManagerType.CHROME).setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--no-sandbox");
                if(Constants.headlessMode){
                    chromeOptions.addArguments("--headless");
                }
                driver = new ChromeDriver(chromeOptions);
                break;
        
            case "firefox":
                WebDriverManager.getInstance(DriverManagerType.FIREFOX).setup();
                FirefoxOptions options = new FirefoxOptions();
                if(Constants.headlessMode){
                    options.setHeadless(true);
                }
                driver = new FirefoxDriver(options);
                break;
        
            case "safari":
                
                driver = new SafariDriver();
                break;
        }
     
        return driver;
    }
}
