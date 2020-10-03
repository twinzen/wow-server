package com.wow.server.user.following;

import com.wow.server.data.model.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface FollowerInformationMapper {

    FollowerInformationDTO toFollowerInformationDTO(User user);

    List<FollowerInformationDTO> toFollowerInformationDTOs(List<User> users);
}
