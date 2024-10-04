package com.salessystem.SalesSystem.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class SaleDTO {

    private String description;
    private float price;

    @NotNull(message = "Customer ID must not be null")
    private long idCustomer;

    @NotNull(message = "Products map must not be null")
    private Map<Long, Integer> products;
}
