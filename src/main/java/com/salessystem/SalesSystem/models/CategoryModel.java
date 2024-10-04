package com.salessystem.SalesSystem.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "category")
@Builder
public class CategoryModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private long idCategory;

    @JsonProperty("Category")
    @JsonAlias({"Category","namecategory"})
    @Column(name = "name_category", length = 20, nullable = false)
    @Size(min = 3, max = 20, message = "The min is 3 characters and max 20 characters")
    @NotBlank(message = "Fill in the fields")
    private String nameCategory;
}
