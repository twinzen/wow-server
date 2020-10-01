package com.wow.server.mapper;

import com.wow.server.data.model.User;
import com.wow.server.dto.UserDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    UserDTO toUserDTO(User user);

    List<UserDTO> toUserDTOs(List<User> users);
}
