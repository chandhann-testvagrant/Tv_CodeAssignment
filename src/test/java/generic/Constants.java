package generic;


import utils.builder.PropertyBuilder;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Constants
{
    private final static PropertyBuilder properties= new PropertyBuilder();
    public final static String browserType =properties.getBrowserType();
    public final static String url=properties.getUrl();
    public final static String password=properties.getPasswordOfUrl();
    public final static String environment=properties.getEnvironment();
    public final static boolean headlessMode=properties.getHeadlessMode();
    public final static int WebDriverWaitInSec=20;
    
}
