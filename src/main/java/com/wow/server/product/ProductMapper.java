package com.wow.server.product;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface ProductMapper {

    ProductDTO toProductDTO(ProductEntity productEntity);

    List<ProductDTO> toProductDTOs(List<ProductEntity> productEntities);
}
