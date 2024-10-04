package com.salessystem.SalesSystem.repository;

import com.salessystem.SalesSystem.models.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductModel, Long> {
    //buscar por el id
    @Query(value = "SELECT * FROM `product` WHERE category_id = :cod", nativeQuery = true)
    List<ProductModel> findProdByIdOfCategory(@Param("cod") Long cod);
}
