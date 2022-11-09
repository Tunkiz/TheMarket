package com.darkCoders.TheMarket.services;

import com.darkCoders.TheMarket.models.Product;
import com.darkCoders.TheMarket.models.exceptions.ProductNotFoundException;
import com.darkCoders.TheMarket.repositories.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryService categoryService;
    public ProductService(ProductRepository productRepository, CategoryService categoryService) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
    }
    public Product addProduct(long CategoryId, Product newProduct){
        categoryService.addProductToCategory(CategoryId,newProduct);
        return productRepository.save(newProduct);
    }
    public List<Product> getProducts(){
        return StreamSupport.stream(productRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }
    public Product getProduct(long id){
        return productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
    }
    public Product deleteProduct(long id){
        Product productToDelete = getProduct(id);
        productRepository.delete(productToDelete);
        return productToDelete;
    }
    @Transactional
    public Product updateProduct(long id, Product product){
        Product productToUpdate = getProduct(id);
        if(Objects.nonNull(product.getName()))
            productToUpdate.setName(product.getName());
        if(Objects.nonNull(product.getPrice()))
            productToUpdate.setPrice(product.getPrice());

        return productToUpdate;
    }

    public Product searchProductByName(String name){
        return productRepository.findByName(name);
    }
}
