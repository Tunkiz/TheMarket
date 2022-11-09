package com.darkCoders.TheMarket.controllers;

import com.darkCoders.TheMarket.models.Product;
import com.darkCoders.TheMarket.models.dtos.ProductDTO;
import com.darkCoders.TheMarket.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/products")
@CrossOrigin(origins = "http://localhost:4200")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping(value = "{categoryId}")
    public ResponseEntity<ProductDTO> createProduct(@RequestBody final Product product, @PathVariable final long categoryId){
        Product newProduct = productService.addProduct(categoryId,product);
        return new ResponseEntity<>(ProductDTO.from(newProduct), HttpStatus.OK);
    }
    @GetMapping(value = "{id}")
    public ResponseEntity<ProductDTO> getProduct(@PathVariable final long id){
        Product product = productService.getProduct(id);
        return new ResponseEntity<>(ProductDTO.from(product), HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<ProductDTO>> getProducts(){
        List<Product> products = productService.getProducts();
        List<ProductDTO> productDTOS = products.stream().map(ProductDTO::from).collect(Collectors.toList());
        return new ResponseEntity<>(productDTOS, HttpStatus.OK);
    }
    @PutMapping(value = "{id}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable final long id, @RequestBody final Product product){
        Product  productToUpdate = productService.updateProduct(id, product);
        return new ResponseEntity<>(ProductDTO.from(productToUpdate), HttpStatus.OK);
    }
    @DeleteMapping(value = "{id}")
    public ResponseEntity<ProductDTO> deleteProduct(@PathVariable final long id){
        Product productToDelete = productService.deleteProduct(id);
        return new ResponseEntity<>(ProductDTO.from(productToDelete), HttpStatus.OK);
    }
    @GetMapping(value = "{name}")
    public ResponseEntity<ProductDTO> searchProductByName(@PathVariable final String name){
        return new ResponseEntity<>(ProductDTO.from(productService.searchProductByName(name)), HttpStatus.OK);
    }

}
