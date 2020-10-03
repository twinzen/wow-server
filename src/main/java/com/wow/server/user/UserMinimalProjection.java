package com.wow.server.user;

import lombok.Value;

@Value
public class UserMinimalProjection {
    Long userId;
    String displayName;
}
