package utils;

import generic.Constants;
import model.Product;
import org.apache.commons.io.FileUtils;
import org.json.JSONObject;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import org.testng.ITest;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

public class BaseTest implements ITest
{
    protected WebDriver driver ;
    @BeforeMethod
    public  void configuration() throws IOException {
        
        driver=new DriverBuilder(Constants.browserType).getDriver();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(Constants.url);
    }
    protected String mTestCaseName = "";

    @AfterMethod
    public  void tearDown()
    {
       
        driver.quit();
    }
    
    @BeforeMethod(alwaysRun = true)
    public void testData(Method method, Object[] testData) {
        String testCase = "";
        if (testData != null && testData.length > 0) {
            Product product = null;
            //Check if test method has actually received required parameters
            for (Object testParameter : testData) {
                if (testParameter instanceof Product) {
                    product = (Product) testParameter;
                    break;
                }
            }
            if (product != null) {
                testCase = product.getName();
            }
        }
        this.mTestCaseName = String.format("%s(%s)", method.getName(), testCase);
    }
    
    @DataProvider(name="ProductData")
    public Object[] testfeed() throws ParseException, IOException {
    
     
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        File file=new File(classLoader.getResource("productData.json").getFile());
        String body=FileUtils.readFileToString(file, "UTF-8");
        
        
        JSONObject data= new JSONObject(body);
        
        System.out.println(data);
        
        int size=data.getJSONArray("data").length();
        
        Object [] jsonObjectOfProduct= new Object[size];
        Object [] productObjectOfProduct= new Object[size];
    
        
        for(int i=0;i<size;i++){
            jsonObjectOfProduct[i]=data.getJSONArray("data").getJSONObject(i);
    
            productObjectOfProduct[i]=FileHelper.mapValueToClass(data.getJSONArray("data").getJSONObject(i).toString());
            
        }
        
        return productObjectOfProduct;//jsonObjectOfProduct;
    }
    
    @Override
    public String getTestName() {
        return this.mTestCaseName;
    }
}
