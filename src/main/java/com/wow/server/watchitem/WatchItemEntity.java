package com.wow.server.watchitem;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
