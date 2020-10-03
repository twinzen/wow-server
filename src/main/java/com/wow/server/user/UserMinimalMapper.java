package com.wow.server.user;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface UserMinimalMapper {

    UserMinimalDTO toUserMinimalDTO(UserMinimalProjection user);

    List<UserMinimalDTO> toUserMinimalDTOs(List<UserMinimalProjection> users);
}
