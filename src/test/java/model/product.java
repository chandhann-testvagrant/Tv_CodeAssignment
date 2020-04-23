package model;

public class product
{
    private String productName;
    private double productPrice;
    private String productColour;
    private String productSize;
    
    public String getProductName() {
        return productName;
    }
    
    public void setProductName(String productName) {
        this.productName = productName;
    }
    
    public String getProductColour() {
        return productColour;
    }
    
    public void setProductColour(String productColour) {
        this.productColour = productColour;
    }
    
    public String getProductSize() {
        return productSize;
    }
    
    public void setProductSize(String productSize) {
        this.productSize = productSize;
    }
    
    public double getProductPrice() {
        return productPrice;
    }
    
    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }
    
}
