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

    @AfterMethod
    public  void tearDown()
    {
        driver.quit();
    }
}
