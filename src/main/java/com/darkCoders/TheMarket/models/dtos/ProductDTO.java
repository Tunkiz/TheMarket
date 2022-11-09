package com.darkCoders.TheMarket.models.dtos;

import com.darkCoders.TheMarket.models.Cart;
import com.darkCoders.TheMarket.models.Product;
import lombok.Data;

@Data
public class ProductDTO {
    private long id;
    private String name;
    private int quantity;
    private double price;
    public static ProductDTO from(Product product){
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setQuantity(product.getQuantity());
        productDTO.setPrice(product.getPrice());
        return productDTO;
    }
}
