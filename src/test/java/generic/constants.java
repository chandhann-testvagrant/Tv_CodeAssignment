package generic;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class constants
{
    
    public static String browsertype ;
    public static String url;
    public static String password;
    public static String environment;
    public static boolean headlessMode;
    
    static{
        Properties prop=new Properties();
        try {
            prop.load(new FileInputStream(Thread.currentThread().getContextClassLoader().getResource("build.properties").getPath()));
        } catch (IOException e) {}
        browsertype =prop.getProperty("browser.type");
        if(browsertype==null){browsertype="chrome";}
        url=prop.getProperty("url");
        password=prop.getProperty("password");
        environment=prop.getProperty("environment");
        headlessMode=Boolean.valueOf(prop.getProperty("browser.headlessMode"));
    }
    
    public static final int WebDriverWaitInSec=20;
    
}
