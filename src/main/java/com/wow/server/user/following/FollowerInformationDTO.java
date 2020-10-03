package com.wow.server.user.following;

import lombok.Data;

@Data
public class FollowerInformationDTO {
    private Long userId;
    private String displayName;
}
