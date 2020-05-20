package utils;

import model.Product;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class FileHelper {
    
    public static Product mapValueToClass(String json)
    {
        ObjectMapper objectMapper = new ObjectMapper();
        Product product=null;
        try {
            product = objectMapper.readValue(json, Product.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return product;
    }
    
    public static Product mapValueToClass(File json)
    {
        ObjectMapper objectMapper = new ObjectMapper();
        Product product=null;
        try {
            product = objectMapper.readValue(json, Product.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return product;
    }
}
