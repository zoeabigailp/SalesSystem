package com.salessystem.SalesSystem.models;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter @ToString
@Entity
@Table(name = "sale")
@Builder
public class SalesModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idSale;

    @Column(length = 200)
    private String description;

    @Column(nullable = false)
    private float price;

    @Column(nullable = false)
    private LocalDate saleDate;


    //relation many to many with product
    @ManyToMany(
            cascade = CascadeType.MERGE,
            fetch =  FetchType.EAGER
    )
    @JoinTable(
            name = "sale_product",
            joinColumns = @JoinColumn(
                    name = "sale_id",
                    referencedColumnName = "idSale"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "product_id",
                    referencedColumnName = "codProd"
            )
    )
    List<ProductModel> productList;

    // Relation many to one with customer
    @ManyToOne(
            cascade = CascadeType.MERGE,
            fetch = FetchType.EAGER
    )
    @JoinColumn(
            name = "customer_id"
    )
    private CustomerModel customer;

    public SalesModel(String description, float price, LocalDate saleDate, List<ProductModel> productList) {
        this.description = description;
        this.price = price;
        this.saleDate = saleDate;
        this.productList = productList;
    }

    public SalesModel(String description, float price, LocalDate saleDate, List<ProductModel> productList, CustomerModel customer) {
        this.description = description;
        this.price = price;
        this.saleDate = saleDate;
        this.productList = productList;
        this.customer = customer;
    }
}
