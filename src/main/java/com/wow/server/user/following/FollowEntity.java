package com.wow.server.user.following;

import com.wow.server.data.model.User;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ManyToAny;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.*;

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
