package com.salessystem.SalesSystem.services;

import com.salessystem.SalesSystem.exceptions.CategoryIsExistsException;
import com.salessystem.SalesSystem.models.CategoryModel;
import com.salessystem.SalesSystem.repository.CategoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    private Logger logger = LoggerFactory.getLogger(CategoryService.class);

    @Transactional
    public CategoryModel createCategory(CategoryModel category) throws CategoryIsExistsException {
        category.setNameCategory(
                normalize(category.getNameCategory())
        );

        if (categoryRepository.existCategory(category.getNameCategory()) > 0 )
        {
            throw new CategoryIsExistsException("is Exists category");
        }

        categoryRepository.save(category);

        return category;
    }

    private String normalize(String text){
        text = text.trim();
        text = text.toLowerCase();
        return text;
    }

    public List<String> findAllCategories(){
        return categoryRepository.findAllCategory();
    }
}
