package com.wow.server.user.following;

import com.wow.server.user.UserEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface FollowerInformationMapper {

    FollowerInformationDTO toFollowerInformationDTO(UserEntity user);

    List<FollowerInformationDTO> toFollowerInformationDTOs(List<UserEntity> users);
}
