package com.wow.server.watchitem;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "TB_WATCH_ITEM")
@Getter
@Setter
public class WatchItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long watchItemId;
    Long productId;
    Long userId;
    LocalDateTime creationDateTime;
    LocalDateTime updateDateTime;


}
