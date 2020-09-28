package com.wow.server.mapper;

import com.wow.server.data.model.Holding;
import com.wow.server.dto.HoldingDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface HoldingMapper {

    HoldingDTO toHoldingDTO(Holding product);

    List<HoldingDTO> toHoldingDTOs(List<Holding> products);
}
