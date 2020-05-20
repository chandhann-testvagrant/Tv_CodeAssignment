package model;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    
    private List<Product> productList= new ArrayList<Product>();
    
    public  void clearCart(){
        productList= new ArrayList<Product>();
    }
    
    public Cart addProductToCart(Product product){
        productList.add(product);
        return this;
    }
    
    public Cart addProductstoList(List<Product> products){
        for (Product product:products){
            productList.add(product);
        }
        
        return this;
    }
    
    public List<Product> getProductsFromCart(){
        
        return productList;
    }
}
