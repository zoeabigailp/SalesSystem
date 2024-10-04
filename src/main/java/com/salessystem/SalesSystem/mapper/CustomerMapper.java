package com.salessystem.SalesSystem.mapper;

import com.salessystem.SalesSystem.dto.CustomerDTO;
import com.salessystem.SalesSystem.dto.ProductDTO;
import com.salessystem.SalesSystem.models.CustomerModel;
import com.salessystem.SalesSystem.models.ProductModel;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {

    //Convert DTO to Model
    public CustomerModel convertDtoToModel(CustomerDTO customer){
        return new CustomerModel(
                customer.getName(),
                customer.getSurname(),
                customer.getPhone());
    }

    //Convert Model To DTO
    public CustomerDTO convertModelToDto(CustomerModel customer){
        return new CustomerDTO(
                customer.getName(),
                customer.getSurname(),
                customer.getPhone());
    }
}
