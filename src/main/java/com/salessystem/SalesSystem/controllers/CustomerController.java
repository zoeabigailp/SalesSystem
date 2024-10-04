package com.salessystem.SalesSystem.controllers;

import com.salessystem.SalesSystem.dto.CustomerDTO;
import com.salessystem.SalesSystem.models.CustomerModel;
import com.salessystem.SalesSystem.services.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    // Create customer
    @PostMapping
    public ResponseEntity<CustomerDTO> createCustomer(@Valid @RequestBody CustomerDTO customer){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(customerService.createCustomer(customer));
    }

    // Delete customer by id
    @DeleteMapping
    public ResponseEntity<Void> deleteCustomer(@RequestParam("id") long id){
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }

    // Update customer by id
    @PutMapping
    public ResponseEntity<CustomerDTO> updateCustomer(@RequestParam("id") long id, @RequestBody CustomerDTO customer){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(customerService.updateCustomer(id, customer));
    }

    //Get by id
    @GetMapping("/get/{id}")
    public ResponseEntity<CustomerModel> getCustomerById(@PathVariable long id){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(customerService.getCustomerById(id));
    }
}
