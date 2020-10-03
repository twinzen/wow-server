package com.wow.server.watch_item;

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
	private Long watchItemId;
	private Long productId;
	private Long userId;
	private LocalDateTime creationDateTime;
	private LocalDateTime updateDateTime;

	
}
