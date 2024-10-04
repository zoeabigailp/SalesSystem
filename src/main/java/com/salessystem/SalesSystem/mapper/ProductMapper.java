package com.salessystem.SalesSystem.mapper;

import com.salessystem.SalesSystem.dto.ProductDTO;
import com.salessystem.SalesSystem.models.ProductModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductMapper {

    /*
        Pasar de Dto a Model
    */
    public ProductModel ConvertDtoToModel(ProductDTO product){
    return new ProductModel(
            product.getCodProd(),
            product.getName(),
            product.getDescription(),
            product.getStock());
    }

    /*
        Pasar de Model a Dto
    */
    public ProductDTO convertModelToDto(ProductModel product){
        return new ProductDTO(
                product.getCodProd(),
                product.getName(),
                product.getDescription(),
                product.getStock(),
                product.getCategory().getNameCategory());
    }

    /*
        Pasar List Model a List Dto
    */
    public List<ProductDTO> convertListModelToDto(List<ProductModel> listModel){
        List<ProductDTO> product = new ArrayList<>();
        listModel.forEach(
                (item) -> {
                    product.add(convertModelToDto(item));
                }
        );
        return product;
    }

}
