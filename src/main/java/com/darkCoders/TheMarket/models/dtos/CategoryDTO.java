package com.darkCoders.TheMarket.models.dtos;

import com.darkCoders.TheMarket.models.Category;
import com.darkCoders.TheMarket.models.Product;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;
@Data
public class CategoryDTO {
    private long id;
    private String name;
    private List<Product> products;

    public static CategoryDTO from(Category category){
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(category.getId());
        categoryDTO.setName(category.getName());
        categoryDTO.setProducts(category.getProducts().stream().collect(Collectors.toList()));
        return categoryDTO;
    }
}
