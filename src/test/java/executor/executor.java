package executor;


import model.product;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pom.homePage;
import pom.productPage;
import pom.welcomePage;
import utils.baseTest;
import utils.builder.productBuilder;


public class executor extends baseTest
{
    @Test
    
    public void validatingSearch() {
        homePage home_page= new welcomePage(driver)
                .enterPassword(password);
    
    
        productPage product_page=home_page.search("Round Neck Shirt 6")
                //.verifySearchResult()
                .openFirstResult()
                .clickOnAddToCart();
        
        product product= new productBuilder(driver)
                .autoBuildProduct();
    
        product_page.tabClickOnCart()
                .increaseQuantityOfProductByone(product)
                .verifyProductDetail(product)
                .tabClickOnHome()
                .scrollToFeaturedCollection()
                .openFirstFeaturedProduct()
                .addProductToCart("M","White");
    
        product= new productBuilder(driver)
                .autoBuildProduct();
        
                product_page.tabClickOnCart()
                        .verifyProductDetail(product);
        
    }

}

