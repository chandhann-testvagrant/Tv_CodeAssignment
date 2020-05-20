package utils;

public class TextHelper {
    
    public static double cleanPrice(String price){
    
     return   Double.valueOf(price.trim().replaceAll("Rs. ","").replaceAll(",",""));
    
    }
    
    public static String cleanSize(String size){
        
        return   size.trim().replaceAll("Size: ","");
        
    }
      public static String cleanColor(String color){
        
        return   color.trim().replaceAll("Color: ","");
        
    }
    
}
