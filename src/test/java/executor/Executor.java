package executor;


import generic.Constants;
import model.Cart;
import model.Product;
import org.testng.annotations.Test;
import pom.HomePage;
import pom.ProductPage;
import pom.WelcomePage;
import utils.BaseTest;
import utils.BasicActions;


public class Executor extends BaseTest
{
   
    
    @Test(priority = 1,dataProvider = "ProductData")
    public void validatingSearch(Product expectedProduct) {
        BasicActions basicActions= new BasicActions(driver);
        
        Cart cart= new Cart();
        
        String name=expectedProduct.getName();
        
        
       HomePage homepage= basicActions.getInstance(WelcomePage.class)
                .enterPassword(Constants.password);
       
            
       ProductPage productPage=homepage.search(name)
                .verifySearchResult()
                .openFirstResult();
                cart= productPage.clickOnAddToCart(cart);
        
                
       Product actualProduct= productPage
                .autoBuildProduct();
    
        
       productPage.tabClickOnCart()
                .increaseQuantityOfProductByOne(actualProduct)
                .verifyProductDetail(actualProduct);
       
       
       productPage.tabClickOnCart()
                .verifyCart(cart);
        
    }
    
    
    @Test(priority = 2,dataProvider = "ProductData")
    public void validateAddingProductOfDifferentSize(Product expectedProduct) {
        BasicActions basicActions= new BasicActions(driver);
        
        Cart cart=new Cart();
        
        String name=expectedProduct.getName(),
                size=expectedProduct.getSize(),
                color=expectedProduct.getColor();
        
        
        HomePage homepage= basicActions.getInstance(WelcomePage.class)
                .enterPassword(Constants.password);
         
        ProductPage productPage = homepage.tabClickOnHome()
                .scrollToFeaturedCollection()
                .openFirstFeaturedProduct();
        
        cart=productPage.addProductToCart(size, color,cart);
    
        
        Product actualProduct= productPage
                .autoBuildProduct();
        
        productPage.tabClickOnCart()
                .verifyProductDetail(actualProduct)
                .verifyCart(cart);
    }
    

}

