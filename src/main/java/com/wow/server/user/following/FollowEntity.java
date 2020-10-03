package com.wow.server.user.following;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "TB_FOLLOW")
@Getter
@Setter
public class FollowEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long followId;

    private Long userId;

    private Long followerId;

    private LocalDateTime creationDateTime;
    private LocalDateTime updateDateTime;
}
