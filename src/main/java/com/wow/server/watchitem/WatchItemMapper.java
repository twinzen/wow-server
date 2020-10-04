package com.wow.server.watchitem;

import com.wow.server.product.ProductMapper;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(uses = ProductMapper.class)
public interface WatchItemMapper {

    WatchItemDTO toWatchItemDTO(WatchItemEntity watchItemEntity);

    List<WatchItemDTO> toWatchItemDTOs(List<WatchItemEntity> watchItemEntities);
}
