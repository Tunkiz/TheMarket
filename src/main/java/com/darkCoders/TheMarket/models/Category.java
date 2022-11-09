package com.darkCoders.TheMarket.models;

import com.darkCoders.TheMarket.models.dtos.CategoryDTO;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String name;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Product> products = new ArrayList<>();

    public void addProduct(Product newProduct){
        products.add(newProduct);
    }
    public void removeProduct(Product product){
        products.remove(product);
    }

    public static Category from(CategoryDTO categoryDto){
        Category category = new Category();
        category.setId(categoryDto.getId());
        category.setProducts(categoryDto.getProducts());
        return category;
    }
}
