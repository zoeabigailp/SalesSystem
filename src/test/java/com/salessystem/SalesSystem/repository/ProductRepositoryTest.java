package com.salessystem.SalesSystem.repository;

import com.salessystem.SalesSystem.models.CategoryModel;
import com.salessystem.SalesSystem.models.ProductModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;
import java.util.List;

@DataJpaTest
@ActiveProfiles("test")
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @BeforeEach
    void setUp() {
        CategoryModel catMod = CategoryModel.builder().nameCategory("lacteo").build();
        testEntityManager.persistAndFlush((catMod));

        ProductModel prodMod = ProductModel.builder()
                .codProd(100)
                .stock(10)
                .category(catMod)
                .description("marolio")
                .name("arroz")
                .build();
        testEntityManager.persistAndFlush(prodMod);
    }

    @Test
    public void testFindProdByIdOfCategory(){
        List<ProductModel> listProd = productRepository.findProdByIdOfCategory(1L);

        //tam list
        Assertions.assertEquals(1,listProd.size());

        //cada elemento de la lista
        Assertions.assertEquals(100L, listProd.getFirst().getCodProd());
        Assertions.assertEquals("arroz", listProd.getFirst().getName());
        Assertions.assertEquals("marolio", listProd.getFirst().getDescription());
        Assertions.assertEquals(10L, listProd.getFirst().getStock());
        Assertions.assertEquals("lacteo", listProd.getFirst().getCategory().getNameCategory());
    }

}