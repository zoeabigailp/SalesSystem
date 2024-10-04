package com.salessystem.SalesSystem.repository;

import com.salessystem.SalesSystem.models.CategoryModel;
import com.salessystem.SalesSystem.models.CustomerModel;
import com.salessystem.SalesSystem.models.ProductModel;
import com.salessystem.SalesSystem.models.SalesModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
class SaleRepositoryTest {

    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @BeforeEach
    void setUp() {
        //create customer
        CustomerModel customer = CustomerModel.builder()
                        .name("customer")
                        .surname("test")
                        .phone("12345678")
                        .build();
        testEntityManager.persistAndFlush(customer);
        //create category
        CategoryModel category = CategoryModel.builder()
                        .nameCategory("lacteo")
                        .build();
        testEntityManager.persistAndFlush(category);
        //create product
        ProductModel prod = ProductModel.builder()
                        .codProd(100L)
                        .name("arroz")
                        .description("marolio")
                        .stock(10)
                        .category(category)
                        .build();
        testEntityManager.persistAndFlush(prod);
        //create sale
        testEntityManager.persistAndFlush(
                SalesModel.builder()
                        .description("sale one")
                        .price(23.4F)
                        .saleDate(LocalDate.parse("2024-09-11"))
                        .customer(customer)
                        .productList(List.of(prod))
                        .build()
        );
        // create two sale
        testEntityManager.persistAndFlush(
                SalesModel.builder()
                        .description("sale two")
                        .price(20.4F)
                        .saleDate(LocalDate.parse("2024-08-11"))
                        .customer(customer)
                        .productList(List.of(prod))
                        .build()
        );

    }
    @Test
    public void testFilterSaleByDateValid(){
        List<SalesModel> saleList = saleRepository.filterSaleByDate(LocalDate.parse("2024-08-11"), LocalDate.parse( "2024-09-11"));

        //tam
        Assertions.assertEquals(2, saleList.size());
    }
    @Test
    public void testfilterSaleByDateNoValid(){
        List<SalesModel> saleList = saleRepository.filterSaleByDate(LocalDate.parse("2023-08-11"), LocalDate.parse( "2023-09-11"));

        //tam
        Assertions.assertEquals(0, saleList.size());
    }
}