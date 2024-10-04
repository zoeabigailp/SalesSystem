package com.salessystem.SalesSystem.services;

import com.salessystem.SalesSystem.dto.SaleDTO;
import com.salessystem.SalesSystem.exceptions.IdNoExistsException;
import com.salessystem.SalesSystem.exceptions.OutOfStockException;
import com.salessystem.SalesSystem.models.CustomerModel;
import com.salessystem.SalesSystem.models.ProductModel;
import com.salessystem.SalesSystem.models.SalesModel;
import com.salessystem.SalesSystem.repository.CustomerRepository;
import com.salessystem.SalesSystem.repository.ProductRepository;
import com.salessystem.SalesSystem.repository.SaleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class SaleService {

    private final SaleRepository saleRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;
    private Logger logger = LoggerFactory.getLogger(SaleService.class);

    @Autowired
    public SaleService(SaleRepository saleRepository, CustomerRepository customerRepository, ProductRepository productRepository) {
        this.saleRepository = saleRepository;
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
    }

    @Transactional
    public SaleDTO createSale (SaleDTO sales)throws OutOfStockException, IllegalArgumentException{
        CustomerModel customer = customerRepository.findById(sales.getIdCustomer()).orElseThrow(
                () -> new IdNoExistsException("The customer ID does not exist")
        );
        // Get list of product
       List<ProductModel> product = productRepository.findAllById(
                sales.getProducts().keySet()
        );

       // is empty?
       if(product.isEmpty()) {
           logger.info("No products found for the given IDs");
           throw new IllegalArgumentException("No products found for the given IDs");
       }//largar exception

       //
        product.forEach(
                (pro)-> {
                    if( pro.getStock() < sales.getProducts().get(pro.getCodProd())){
                        throw new OutOfStockException("The stock of " + pro.getName() + " with cod "+ pro.getCodProd() + " is insufficient");//lanzar exception
                    }
                    pro.setStock(pro.getStock() - sales.getProducts().get(pro.getCodProd()));
                }
        );

        // Save in model
        SalesModel saleMod = new SalesModel(
                sales.getDescription(),
                sales.getPrice(),
                LocalDate.now(),
                product,
                customer
        );

        saleRepository.save(saleMod);
        return sales;
    }

    @Transactional(readOnly = true)
    public Page<SalesModel> getAllSales(Pageable pegeable){
        return saleRepository.findAll(pegeable);
    }

    public SalesModel getSaleById(long id) throws IdNoExistsException{
        return saleRepository.findById(id).orElseThrow(
                () -> new IdNoExistsException("Sale ID does not exist")
        );
    }

    @Transactional
    public void deleteSale(long id){
        saleRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<SalesModel> filterSaleByDate(LocalDate initialDate, LocalDate endDate){
        return saleRepository.filterSaleByDate(initialDate, endDate);
    }
}
