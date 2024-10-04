package com.salessystem.SalesSystem.services;

import com.salessystem.SalesSystem.exceptions.CategoryIsExistsException;
import com.salessystem.SalesSystem.models.CategoryModel;
import com.salessystem.SalesSystem.repository.CategoryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class CategoryServiceTest {
    //crear nativamente con mokito:
    @MockBean
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryService categoryService;

    @BeforeEach
    void setUp() {

    }
    @Test
    public void testCreateCategory(){
        //creo cat de prueba
        CategoryModel category = CategoryModel.builder().idCategory(1).nameCategory("lacteo").build();
        //Testear que el repository es llamado bien
        when(
                categoryRepository.save(
                        any(CategoryModel.class)
                )
        ).thenReturn(category);

        //
        CategoryModel categoryMod =  categoryService.createCategory(category);
        //verifico que el service llame al repository
        verify(categoryRepository).save(any(CategoryModel.class));
        //testea prueba de la normalizacion del nameCategory
        Assertions.assertEquals("lacteo", categoryMod.getNameCategory());
    }

    @Test
    public void testCreateCategoryThrowsExceptionWhenExists(){
        CategoryModel category = CategoryModel.builder().nameCategory("lacteos").build();
        when(categoryRepository.existCategory(any(String.class)) ).thenReturn((byte) 1);

        Assertions.assertThrows(CategoryIsExistsException.class,
                ()-> categoryService.createCategory(category),
                "is Exists category"
                );
    }
}