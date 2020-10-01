package com.wow.server.mapper;

import com.wow.server.data.model.WatchItem;
import com.wow.server.dto.WatchItemDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface WatchItemMapper {

    WatchItemDTO toWatchItemDTO(WatchItem watchItem);

    List<WatchItemDTO> toWatchItemDTOs(List<WatchItem> watchItems);
}
