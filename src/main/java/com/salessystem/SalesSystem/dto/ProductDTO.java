package com.salessystem.SalesSystem.dto;

import com.fasterxml.jackson.annotation.*;
import jakarta.validation.constraints.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter @Getter
public class ProductDTO {

    @JsonProperty("cod")
    @JsonAlias("cod")
    @NotNull(message = "Product name must not be blank")
    private long codProd;

    @JsonProperty("product")
    @JsonAlias("product")
    @Size(min = 3, max = 20, message = "Product name must be between 3 and 20 characters" )
    @NotBlank(message = "Fill in the fields")
    private  String name;


    @Size( max = 200, message = "Description must be up to 200 characters" )
    private  String description;

    @NotNull(message = "Stock must not be null")
    private int stock;

    @JsonProperty("Category")
    @JsonAlias({"Category", "catproduct"})
    @NotBlank(message = "Category must not be blank")
    private String category;
}
