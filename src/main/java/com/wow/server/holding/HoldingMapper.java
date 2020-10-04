package com.wow.server.holding;

import com.wow.server.product.ProductMapper;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(uses = ProductMapper.class)
public interface HoldingMapper {

    HoldingDTO toHoldingDTO(HoldingEntity product);

    List<HoldingDTO> toHoldingDTOs(List<HoldingEntity> products);
}
