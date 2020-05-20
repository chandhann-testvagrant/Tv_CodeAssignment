package utils.builder;

import model.Product;

public class ProductBuilder
{
    public ProductBuilder()
    {
    
    }
    
    Product product= new Product();
    
    
    public ProductBuilder withName(String name){
        
        product.setName(name);
        return this;
    }
    
    public ProductBuilder withColor(String color){
        product.setColor(color);
        return this;
    }
    
    public ProductBuilder withSize(String size){
        product.setSize(size);
        return this;
    }
    
    public ProductBuilder withPrice(double price){
        product.setPrice(price);
        return this;
    }
    
    public Product build(){
        return product;
    }


}
