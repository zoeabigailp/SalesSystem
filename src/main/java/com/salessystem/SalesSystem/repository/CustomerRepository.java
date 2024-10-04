package com.salessystem.SalesSystem.repository;

import com.salessystem.SalesSystem.models.CustomerModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<CustomerModel, Long> {
}
/*
 Delete id
 find id

*/