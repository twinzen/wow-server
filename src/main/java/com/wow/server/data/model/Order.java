package com.wow.server.data.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TB_ORDER")
@Getter
@Setter
public class Order {

	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	Long tradeId;
	Long productId;
	Long userId;
	BigDecimal quantity;
	BigDecimal orderPrice;
	String orderStatus;
	LocalDateTime creationDateTime;
	LocalDateTime updateDateTime;
}
