package com.salessystem.SalesSystem.controllers;

import com.salessystem.SalesSystem.dto.ProductDTO;
import com.salessystem.SalesSystem.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@Valid @RequestBody ProductDTO productDTO){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(productService.createProduct(productDTO));
    }

    //Get all products
    @GetMapping("/all")
    public ResponseEntity<List<ProductDTO>> findAllProducts(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(productService.findAllProducts());
    }

    //Get product by id
    @GetMapping
    public ResponseEntity<ProductDTO> getProductById(@RequestParam("id") long id){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(productService.getProductById(id));
    }
    //Delete product by id
    @DeleteMapping
    public ResponseEntity<Void> deleteProductById(@RequestParam("id") long id){
        productService.deleteProductById(id);
        return ResponseEntity.noContent().build();
    }

    //Update product by id
    @PutMapping
    public ResponseEntity<ProductDTO> updateProductById(@RequestParam("id") long id,@RequestBody ProductDTO product){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(productService.updateProductById(id, product));
    }

    //Get product by category
    @GetMapping("/filter")
    public ResponseEntity<List<ProductDTO>> filterProdByCategory(@RequestParam("category") String cat){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(productService.filterProdByCategory(cat));

    }
}
