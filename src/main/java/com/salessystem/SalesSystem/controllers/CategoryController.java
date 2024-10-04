package com.salessystem.SalesSystem.controllers;

import com.salessystem.SalesSystem.models.CategoryModel;
import com.salessystem.SalesSystem.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public ResponseEntity<CategoryModel> createCategory(@Valid @RequestBody CategoryModel category){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(categoryService.createCategory(category));
    }

    @GetMapping
    public ResponseEntity<List<String>> findAllCategories(){
        return ResponseEntity.
                status(HttpStatus.OK)
                .body(categoryService.findAllCategories());
    }
}
