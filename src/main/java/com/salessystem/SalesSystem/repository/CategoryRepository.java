package com.salessystem.SalesSystem.repository;

import com.salessystem.SalesSystem.models.CategoryModel;
import jakarta.websocket.server.PathParam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<CategoryModel, Long> {
    @Query(value = "SELECT COUNT(name_category) AS cont FROM category WHERE name_category = :category" ,nativeQuery = true)
    Byte existCategory(@Param("category") String category);

    @Query(value = "SELECT name_category FROM category", nativeQuery = true)
    List<String> findAllCategory();

    Optional<CategoryModel> findByNameCategory(String name);

    @Query(value = "SELECT `id_category` FROM `category` WHERE name_category = :cat", nativeQuery = true)
    Long findIdByCategory (@Param("cat") String cat);

}
