package utils.builder;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyBuilder {
    
    public PropertyBuilder(){
        try {
            prop=new Properties();
            prop.load(new FileInputStream(Thread.currentThread().getContextClassLoader().getResource("build.properties").getPath()));
        } catch (IOException e) {}
    }
        Properties prop;
    
    public String getBrowserType(){
        return prop.getProperty("browser.type");
    }
    
    public boolean getHeadlessMode(){
        return Boolean.valueOf(prop.getProperty("browser.headlessMode"));
    }
    
    public String getUrl(){
        return prop.getProperty("url");
    }
    
    public String getPasswordOfUrl(){
        return prop.getProperty("password");
    }
    
    public String getEnvironment(){
        return prop.getProperty("environment");
    }
    
    
        
        
        
        
    
}
