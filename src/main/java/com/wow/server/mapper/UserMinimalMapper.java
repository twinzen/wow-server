package com.wow.server.mapper;

import com.wow.server.data.projection.UserMinimalProjection;
import com.wow.server.dto.UserMinimalDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface UserMinimalMapper {

    UserMinimalDTO toUserMinimalDTO(UserMinimalProjection user);

    List<UserMinimalDTO> toUserMinimalDTOs(List<UserMinimalProjection> users);
}
