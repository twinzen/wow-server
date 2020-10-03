package com.wow.server.user;

import lombok.Data;

@Data
public class UserMinimalDTO {
    private Long userId;
    private String displayName;
}
