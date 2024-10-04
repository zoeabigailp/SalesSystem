package com.salessystem.SalesSystem.models;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter @ToString
@Entity
@Table(name = "product")
@Builder
public class ProductModel {

    @Id
    private long codProd;

    @Column(name = "name", length = 20, nullable = false)
    private  String name;

    @Column(name = "description", length = 200)
    private  String description;

    @Column(name = "stock")
    private int stock;

    @ManyToOne(
            fetch = FetchType.EAGER
    )
    @JoinColumn(
            name = "category_id"
    )
    private CategoryModel category;

    public ProductModel(long codProd, String name, String description, int stock) {
        this.codProd = codProd;
        this.name = name;
        this.description = description;
        this.stock = stock;
    }
}
