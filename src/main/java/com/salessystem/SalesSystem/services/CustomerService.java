package com.salessystem.SalesSystem.services;

import com.salessystem.SalesSystem.Validation.NormalizeValid;
import com.salessystem.SalesSystem.dto.CustomerDTO;
import com.salessystem.SalesSystem.exceptions.IdNoExistsException;
import com.salessystem.SalesSystem.mapper.CustomerMapper;
import com.salessystem.SalesSystem.models.CustomerModel;
import com.salessystem.SalesSystem.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private NormalizeValid normalizeValid;

    @Autowired
    private CustomerMapper customerMapper;

    @Transactional
    public CustomerDTO createCustomer(CustomerDTO customer){
      customer.setName(normalizeValid.NormalizeCad(customer.getName()));
      customer.setSurname(normalizeValid.NormalizeCad(customer.getSurname()));
      customer.setPhone(customer.getPhone());

      CustomerModel customerModel = customerMapper.convertDtoToModel(customer);
      customerRepository.save(customerModel);

      return customer;
    }

    @Transactional
    public void deleteCustomer(long id)throws IdNoExistsException{
        if(!customerRepository.existsById(id)) throw new IdNoExistsException("Customer with id" + id + " does not exist");
        customerRepository.deleteById(id);
    }

    @Transactional
    public CustomerDTO updateCustomer(long id, CustomerDTO customer)throws IdNoExistsException{
        // Get customer
        CustomerModel customerMod = customerRepository.findById(id).orElseThrow(
                () -> new IdNoExistsException("Customer with id " + id + " does not exist")
        );

        //
        customerMod.setName(normalizeValid.NormalizeCad(customer.getName()));
        customerMod.setSurname(normalizeValid.NormalizeCad(customer.getSurname()));

        //mapper and return
        return customerMapper.convertModelToDto(customerRepository.save(customerMod));
    }

    public CustomerModel getCustomerById(long id) throws IdNoExistsException{
        return customerRepository.findById(id).orElseThrow(
                () -> new IdNoExistsException("Customer with id " + id + " does not exist")
        );

    }
}
