package com.salessystem.SalesSystem.repository;

import com.salessystem.SalesSystem.models.CategoryModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
class CategoryRepositoryTest {

    @Autowired
    public CategoryRepository categoryRepository;

    @Autowired
    TestEntityManager testEntityManager;

    @BeforeEach
    public void setUp(){
        testEntityManager.persistAndFlush(CategoryModel.builder().nameCategory("lacteo").build());
        testEntityManager.persistAndFlush(CategoryModel.builder().nameCategory("congelado").build());
        testEntityManager.persistAndFlush(CategoryModel.builder().nameCategory("refresco").build());
        testEntityManager.persistAndFlush(CategoryModel.builder().nameCategory("snack").build());
    }

    @Test
    public void testFindById(){
        CategoryModel cat = categoryRepository.findById(1L).orElseThrow();
        Assertions.assertEquals("lacteo", cat.getNameCategory());
    }
    @Test
    public void testCountCategoryByNameExists(){
        byte countNameCategory = categoryRepository.existCategory("lacteo");
        Assertions.assertEquals(1, countNameCategory);
    }
    @Test
    public void testCountCategoryByNameNoExists(){
        byte countNameCategory = categoryRepository.existCategory("almacen");
        Assertions.assertEquals(0, countNameCategory);
    }
    @Test
    public void testFindAllCategory(){
        List<String> cat = categoryRepository.findAllCategory();
        //tam de la lista
        Assertions.assertEquals(4, cat.size());
        //ver si estan todos los elementos
        List<String> listCategories = Arrays.asList(
                "lacteo",
                "congelado",
                "refresco",
                "snack"
        );
        Assertions.assertEquals(listCategories, cat);
    }
    @Test
    public void testfindIdByCategoryExists(){
        Long id = categoryRepository.findIdByCategory("lacteo");
        Assertions.assertEquals(1, id);
    }
    @Test
    public void testfindIdByCategoryNoExists(){
        Long id = categoryRepository.findIdByCategory("lacteos");
        Assertions.assertNull(id);
    }
}