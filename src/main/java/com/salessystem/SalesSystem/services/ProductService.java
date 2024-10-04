package com.salessystem.SalesSystem.services;

import com.salessystem.SalesSystem.dto.ProductDTO;
import com.salessystem.SalesSystem.exceptions.CategoryNoExistsException;
import com.salessystem.SalesSystem.exceptions.IdIsExistsException;
import com.salessystem.SalesSystem.exceptions.IdNoExistsException;
import com.salessystem.SalesSystem.mapper.ProductMapper;
import com.salessystem.SalesSystem.models.CategoryModel;
import com.salessystem.SalesSystem.models.ProductModel;
import com.salessystem.SalesSystem.repository.CategoryRepository;
import com.salessystem.SalesSystem.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;


@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductMapper productMapper;

    private Logger LOGGER = LoggerFactory.getLogger(ProductService.class);

    @Transactional
    public ProductDTO createProduct(ProductDTO productDTO) throws CategoryNoExistsException, IdIsExistsException {
        productDTO.setCategory(normalize(productDTO.getCategory()));
        productDTO.setName(normalize(productDTO.getName()));

        // Exists category ? yes: create product , no: no createe product
        CategoryModel cat = categoryRepository.findByNameCategory(productDTO.getCategory()).orElseThrow(
                () -> new CategoryNoExistsException("Category does not exist")
        );
        // Exists product?
        if(productRepository.existsById(productDTO.getCodProd())) {
            throw new IdIsExistsException("Product with code:  " + productDTO.getCodProd() + " already exists");
        }

        //exists, create product
        ProductModel product = productMapper.ConvertDtoToModel(productDTO);
        product.setCategory(cat);

        //save product
        productRepository.save(product);

        return productDTO;
    }

    private String normalize(String text){
        text = text.trim();
        text = text.toLowerCase();
        return text;
    }

    public List<ProductDTO> findAllProducts(){
        List<ProductModel> listModel = productRepository.findAll();
        //Convert model to dto list and return
        return  productMapper.convertListModelToDto(listModel);
    }

    public ProductDTO getProductById(long id)throws IdNoExistsException{
        return productMapper.convertModelToDto(productRepository.findById(id).orElseThrow(
                () -> new IdNoExistsException("No exists product")
        ));
    }

    @Transactional
    public void deleteProductById(long id) throws IdNoExistsException{
        if (!productRepository.existsById(id)){
            throw new IdNoExistsException("Product does not exist");
        }
        productRepository.deleteById(id);
    }

    @Transactional
    public ProductDTO updateProductById(long id, ProductDTO product) throws IdNoExistsException{
        ProductModel prodMod = productRepository.findById(id).orElseThrow(
                () -> new IdNoExistsException("Product does not exist for update")
        );

        prodMod.setName(normalize(product.getName()));
        prodMod.setDescription(normalize(product.getDescription()));
        prodMod.setStock(product.getStock());

        return productMapper.convertModelToDto(productRepository.save(prodMod));
    }

    public List<ProductDTO> filterProdByCategory(String cat) throws CategoryNoExistsException {
        // exists category?
        Long idCategory = categoryRepository.findIdByCategory(cat);
        if(idCategory == null ) throw new CategoryNoExistsException("Category does not exist");

        //find by id
       List<ProductModel> productModel = productRepository.findProdByIdOfCategory(idCategory);

       //return product
       return productMapper.convertListModelToDto(productModel);
    }
}
