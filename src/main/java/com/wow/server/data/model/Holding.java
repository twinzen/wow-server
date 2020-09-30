package com.wow.server.data.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "TB_HOLDING")
public class Holding {

	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	Long holdingId;
	
	Long productId;
	
	Long userId;
	
	BigDecimal quantity;
	
	BigDecimal avgPurchasePrice;
	
	LocalDateTime creationDateTime;
	
	LocalDateTime updateDateTime;

	public Long getHoldingId() {
		return holdingId;
	}

	public void setHoldingId(Long holdingId) {
		this.holdingId = holdingId;
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

	public BigDecimal getQuantity() {
		return quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getAvgPurchasePrice() {
		return avgPurchasePrice;
	}

	public void setAvgPurchasePrice(BigDecimal avgPurchasePrice) {
		this.avgPurchasePrice = avgPurchasePrice;
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
