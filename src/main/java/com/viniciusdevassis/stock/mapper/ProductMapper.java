package com.viniciusdevassis.stock.mapper;

import com.viniciusdevassis.stock.dto.CreateProductDTO;
import com.viniciusdevassis.stock.dto.ResponseProductDTO;
import com.viniciusdevassis.stock.dto.UpdateProductDTO;
import com.viniciusdevassis.stock.entities.Product;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    //Converter um único product
    CreateProductDTO productToCreateProductDTO(Product product);
    Product createProductDTOToProduct(CreateProductDTO dto);
    ResponseProductDTO productToResponseProductDTO(Product product);
    Product responseProductDTOToProduct(ResponseProductDTO dto);
    UpdateProductDTO productToUpdateProductDTO(Product product);
    Product updateProductDTOToProduct(UpdateProductDTO dto);

    //Converter uma lista de products
    List<ResponseProductDTO> productsToListDTO(List<Product> products);
}
