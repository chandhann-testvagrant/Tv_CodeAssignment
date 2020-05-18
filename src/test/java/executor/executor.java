package executor;


import model.product;
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
   
    
    @Test(priority = 1,dataProvider = "testdata")
    public void validatingSearch(String name,String size,String color) {
        basicActions BA= new basicActions(driver);
        
       homePage home_page= BA.getInstance(welcomePage.class)
        //homePage home_page= new welcomePage(driver)
                .enterPassword(password);

        productPage product_page=home_page.search(name)
                .verifySearchResult()
                .openFirstResult()
                .clickOnAddToCart();
        
        product product= new productBuilder(driver)
                .autoBuildProduct();
    
        product_page.tabClickOnCart()
                .increaseQuantityOfProductByOne(product)
                .verifyProductDetail(product);
        
    }
    
    
    @Test(priority = 2,dataProvider = "testdata")
    public void ValidateAddingProductOfDifferentSize(String name,String size,String color) {
    
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

