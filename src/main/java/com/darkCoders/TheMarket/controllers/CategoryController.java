package com.darkCoders.TheMarket.controllers;

import com.darkCoders.TheMarket.models.Category;
import com.darkCoders.TheMarket.models.dtos.CategoryDTO;
import com.darkCoders.TheMarket.services.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/category")
@CrossOrigin(origins = "http://localhost:4200")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
    @PostMapping
    public ResponseEntity<CategoryDTO> createCategory(@RequestBody final Category category){
        return new ResponseEntity<>(CategoryDTO.from(categoryService.addCategory(category)), HttpStatus.CREATED);
    }
    @GetMapping(value = "{id}")
    public ResponseEntity<CategoryDTO> getCategory(@PathVariable final long id){
        return new ResponseEntity<>(CategoryDTO.from(categoryService.getCategory(id)), HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<CategoryDTO>> getCategories(){
        List<Category> categories = categoryService.getCategories();
        List<CategoryDTO> categoryDTOS = categories.stream().map(CategoryDTO::from).collect(Collectors.toList());
        return new ResponseEntity<>(categoryDTOS, HttpStatus.OK);
    }
    @PutMapping(value = "{id}")
    public ResponseEntity<CategoryDTO> updateCategory(@PathVariable final long id, @RequestBody final Category category){
        return new ResponseEntity<>(CategoryDTO.from(categoryService.updateCategory(id, category)), HttpStatus.OK);
    }
    @DeleteMapping(value = "{id}")
    public ResponseEntity<CategoryDTO> deleteCategory(@PathVariable final long id){
        return new ResponseEntity<>(CategoryDTO.from(categoryService.deleteCategory(id)), HttpStatus.OK);
    }
}
