package utils;

import generic.constants;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class baseTest extends constants
{
    protected WebDriver driver ;
    @BeforeMethod
    public  void configuration() throws IOException {
        
        driver=new driverBuilder(browsertype).getDriver();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(url);
    }
    
    @DataProvider(name="testdata")
    public Object[][] testfeed(){
        
        String[][] test= new String[][]{
                
                { "Round Neck Shirt 14", "M","White"},
                { "Round Neck Shirt 6", "M","White" }
        };
        
        
        
        return test;
    }

    @AfterMethod
    public  void tearDown()
    {
        driver.quit();
    }
}
