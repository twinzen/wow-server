package com.wow.server.watchitem;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface WatchItemMapper {

    WatchItemDTO toWatchItemDTO(WatchItemEntity watchItemEntity);

    List<WatchItemDTO> toWatchItemDTOs(List<WatchItemEntity> watchItemEntities);
}
