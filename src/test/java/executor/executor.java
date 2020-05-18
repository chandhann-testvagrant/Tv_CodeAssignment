package executor;


import model.product;
import org.json.JSONObject;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pom.homePage;
import pom.productPage;
import pom.welcomePage;
import utils.baseTest;
import utils.basicActions;
import utils.builder.productBuilder;


public class executor extends baseTest
{
   
    
    @Test(priority = 1,dataProvider = "ProductData")
    public void validatingSearch(JSONObject obj) {
        basicActions basic_actions= new basicActions(driver);
        
       homePage home_page= basic_actions.getInstance(welcomePage.class)
        //homePage home_page= new welcomePage(driver)
                .enterPassword(password);
            String name=obj.getString("name");
        productPage product_page=home_page.search(name)
                .verifySearchResult()
                .openFirstResult()
                .clickOnAddToCart();
        
        product product= new productBuilder(driver)
                .autoBuildProduct();
    
        product_page.tabClickOnCart()
                .increaseQuantityOfProductByOne(product)
                .verifyProductDetail(product);
        product_page.tabClickOnCart()
                .verifyCart();
        
    }
    
    
    @Test(priority = 2,dataProvider = "ProductData")
    public void ValidateAddingProductOfDifferentSize(JSONObject obj) {
    
        String name=obj.getString("name"),
                size=obj.getString("size"),
                color=obj.getString("color");
        
        basicActions BA= new basicActions(driver);
         homePage home_page= BA.getInstance(welcomePage.class)
       // homePage home_page= new welcomePage(driver)
                .enterPassword(password);
    
    
        productPage product_page = home_page.tabClickOnHome()
                .scrollToFeaturedCollection()
                .openFirstFeaturedProduct()
                .addProductToCart(size, color);
    
        product product= new productBuilder(driver)
                .autoBuildProduct();
        
        product_page.tabClickOnCart()
                .verifyProductDetail(product);
    }
    

}

