package com.salessystem.SalesSystem.repository;

import com.salessystem.SalesSystem.models.SalesModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface SaleRepository extends JpaRepository<SalesModel, Long> {
    @Query(value = "SELECT * FROM sale WHERE sale_date BETWEEN :initialDate AND :endDate", nativeQuery = true)
    List<SalesModel> filterSaleByDate(@Param("initialDate") LocalDate initialDate, @Param("endDate") LocalDate endDate);

    Page<SalesModel> findAll(Pageable pageable);
}
