package utils;

import generic.constants;
import model.cart;
import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
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
        cart.clearCart();
        driver.quit();
    }
    
    
    
    @DataProvider(name="ProductData")
    public Object[] testfeed(){
        
        
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        File file=new File(classLoader.getResource("productData.json").getFile());
        Scanner myReader = null;
        try {
            myReader = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String content="";
        while (myReader.hasNextLine()) {
            content =  content + myReader.nextLine();
        }
        myReader.close();
        
        JSONObject data= new JSONObject(content);
        
        int size=data.getJSONArray("data").length();
        
        Object [] test1= new Object[size];
        
        for(int i=0;i<size;i++){
            test1[i]=data.getJSONArray("data").getJSONObject(i);
        }
        
        return test1;
    }
}
