package com.wow.server.data.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TB_WATCH_ITEM")
public class WatchItem {

	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	Long watchItemId;
	Long productId;
	Long userId;
	LocalDateTime creationDateTime;
	LocalDateTime updateDateTime;
	public Long getWatchItemId() {
		return watchItemId;
	}
	public void setWatchItemId(Long watchItemId) {
		this.watchItemId = watchItemId;
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public LocalDateTime getCreationDateTime() {
		return creationDateTime;
	}
	public void setCreationDateTime(LocalDateTime creationDateTime) {
		this.creationDateTime = creationDateTime;
	}
	public LocalDateTime getUpdateDateTime() {
		return updateDateTime;
	}
	public void setUpdateDateTime(LocalDateTime updateDateTime) {
		this.updateDateTime = updateDateTime;
	}
	
}
