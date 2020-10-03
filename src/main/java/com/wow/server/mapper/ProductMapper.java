package com.wow.server.mapper;

import com.wow.server.data.model.Product;
import com.wow.server.dto.ProductDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface ProductMapper {

    ProductDTO toProductDTO(Product product);

    List<ProductDTO> toProductDTOs(List<Product> products);
}
