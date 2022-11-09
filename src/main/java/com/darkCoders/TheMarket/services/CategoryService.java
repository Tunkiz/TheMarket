package com.darkCoders.TheMarket.services;

import com.darkCoders.TheMarket.models.Category;
import com.darkCoders.TheMarket.models.Product;
import com.darkCoders.TheMarket.models.exceptions.CategoryNotFoundException;
import com.darkCoders.TheMarket.repositories.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
    public Category addCategory(Category category){
        return categoryRepository.save(category);
    }
    public Category getCategory(long id){
        return categoryRepository.findById(id).orElseThrow(() ->  new CategoryNotFoundException(id));
    }
    public List<Category> getCategories(){
        return StreamSupport.stream(categoryRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }
    @Transactional
    public Category updateCategory(long id, Category category){
        Category categoryToUpdate = getCategory(id);
        if (Objects.nonNull(category.getName()))
            categoryToUpdate.setName(category.getName());
        return categoryToUpdate;
    }
    public Category deleteCategory(long id){
        Category categoryToDelete = getCategory(id);
        categoryRepository.delete(categoryToDelete);
        return categoryToDelete;
    }
    public Category addProductToCategory(long id,Product newProduct){
        Category category = getCategory(id);
        category.addProduct(newProduct);
        return category;
    }
}
