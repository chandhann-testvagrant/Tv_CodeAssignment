package executor;


import model.product;
import org.testng.annotations.Test;
import pom.homePage;
import pom.productPage;
import pom.welcomePage;
import utils.baseTest;
import utils.basicActions;
import utils.builder.productBuilder;


public class executor extends baseTest
{
    @Test(priority = 1)
    public void validatingSearch() {
        basicActions BA= new basicActions(driver);
        
       // homePage home_page= BA.getInstance(welcomePage.class)
        homePage home_page= new welcomePage(driver)
                .enterPassword(password);

        productPage product_page=home_page.search("Round Neck Shirt 6")
                .verifySearchResult()
                .openFirstResult()
                .clickOnAddToCart();
        
        product product= new productBuilder(driver)
                .autoBuildProduct();
    
        product_page.tabClickOnCart()
                .increaseQuantityOfProductByOne(product)
                .verifyProductDetail(product);
        
    }
    
    
    @Test(priority = 2)
    public void ValidateAddingProductOfDifferentSize() {
    
        basicActions BA= new basicActions(driver);
        // homePage home_page= BA.getInstance(welcomePage.class)
        homePage home_page= new welcomePage(driver)
                .enterPassword(password);
    
    
        productPage product_page = home_page.tabClickOnHome()
                .scrollToFeaturedCollection()
                .openFirstFeaturedProduct()
                .addProductToCart("M", "White");
    
        product product= new productBuilder(driver)
                .autoBuildProduct();
        
        product_page.tabClickOnCart()
                .verifyProductDetail(product);
    }
    

}

