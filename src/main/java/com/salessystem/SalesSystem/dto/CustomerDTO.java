package com.salessystem.SalesSystem.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter @Getter
public class CustomerDTO {

    @JsonProperty("name")
    @JsonAlias("name")
    @Size(min = 3, max = 30, message = "Name must be between 3 and 30 characters long" )
    @NotBlank(message = "Name cannot be blank")
    private String name;

    @JsonProperty("surname")
    @JsonAlias("surname")
    @Size(min = 3, max = 30, message = "Surname must be between 3 and 30 characters long" )
    @NotBlank(message = "Surname cannot be blank")
    private String surname;

    @JsonProperty("phone")
    @JsonAlias("phone")
    @Size(min = 7, max = 15, message = "Phone number must be between 7 and 15 characters long")
    private String phone;
}
