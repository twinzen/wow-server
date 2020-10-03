package com.wow.server.user;

import com.wow.server.data.projection.UserMinimalProjection;
import com.wow.server.user.UserMinimalDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface UserMinimalMapper {

    UserMinimalDTO toUserMinimalDTO(UserMinimalProjection user);

    List<UserMinimalDTO> toUserMinimalDTOs(List<UserMinimalProjection> users);
}
