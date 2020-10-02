package com.wow.server.data.projection;

import lombok.Value;

@Value
public class UserMinimalProjection {
    Long userId;
    String displayName;
}
