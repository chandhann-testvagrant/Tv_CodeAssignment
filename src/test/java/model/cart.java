package model;

import java.util.ArrayList;
import java.util.List;

public class cart {
    
    private static List<product> productList= new ArrayList<product>();
    
    public static void clearCart(){
        productList= new ArrayList<product>();
    }
    
    public static void addProductToCart(product product){
        productList.add(product);
    }
    
    public static void addProductstoList(List<product> products){
        for (product product:products){
            productList.add(product);
        }
    }
    
    public static List<product> getProductsFromCart(){
        
        return productList;
    }
}
