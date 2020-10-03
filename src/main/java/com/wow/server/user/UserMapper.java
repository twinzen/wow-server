package com.wow.server.user;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    UserDTO toUserDTO(UserEntity user);

    List<UserDTO> toUserDTOs(List<UserEntity> users);
}
