package com.darkCoders.TheMarket.models.dtos;

import com.darkCoders.TheMarket.models.Cart;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class CartDTO {
    private long id;
    private List<ProductDTO> products = new ArrayList<>();

    public static CartDTO from(Cart cart){
        CartDTO cartDTO = new CartDTO();
        cartDTO.setId(cart.getId());
        cartDTO.setProducts(cart.getProducts().stream().map(ProductDTO::from).collect(Collectors.toList()));
        return cartDTO;
    }
}
