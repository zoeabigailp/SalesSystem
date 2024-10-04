package com.salessystem.SalesSystem.controllers;

import com.salessystem.SalesSystem.dto.SaleDTO;
import com.salessystem.SalesSystem.models.SalesModel;
import com.salessystem.SalesSystem.services.SaleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/sales")
public class SaleController {

    @Autowired
    private SaleService saleService;

    // Create sale
    @PostMapping
    public ResponseEntity<SaleDTO> createSale(@Valid @RequestBody SaleDTO sales){
        return  ResponseEntity.status(HttpStatus.CREATED).body(saleService.createSale(sales));
    }

    //Get all sales
    @GetMapping("/all")
    public ResponseEntity<Page<SalesModel>> getAllSales(@RequestParam(value = "page", defaultValue = "0") int page, @RequestParam(value = "size", defaultValue = "3") int size){
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.status(HttpStatus.OK).body(saleService.getAllSales(pageable));
    }

    // Get sale by id
    @GetMapping("/select")
    public ResponseEntity<SalesModel> getSaleById(@RequestParam long id){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(saleService.getSaleById(id));
    }

    // Delete sale by id
    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteSale(@RequestParam long id){
        saleService.deleteSale(id);
        return ResponseEntity.noContent().build();
    }

    // Filter by date
    @GetMapping("/filter")
    public ResponseEntity<List<SalesModel>> filterSaleByDate(@RequestParam("initialDate")
                                                 @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate initialDate,
                                             @RequestParam("endDate")  @DateTimeFormat(iso =
                                                     DateTimeFormat.ISO.DATE) LocalDate endDate){
        return ResponseEntity.status(HttpStatus.OK).body(saleService.filterSaleByDate(initialDate, endDate));
    }
}
