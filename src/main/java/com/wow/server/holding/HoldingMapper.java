package com.wow.server.holding;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface HoldingMapper {

    HoldingDTO toHoldingDTO(HoldingEntity product);

    List<HoldingDTO> toHoldingDTOs(List<HoldingEntity> products);
}
