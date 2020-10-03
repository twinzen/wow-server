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
@Table(name = "TB_PRODUCT")
@Getter
@Setter
public class Product {

	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	Long productId;
	
	String productCode;
	
	String productName;
	
	BigDecimal marketCap;
	
	BigDecimal avgVolume;
	
	BigDecimal peRatio;
	
	BigDecimal revenue;
	
	BigDecimal totalCash;
	
	BigDecimal totalDevidendYield;
	
	BigDecimal avgDividendYield;
	
	String sector;
	
	String industry;
	
	BigDecimal price;
	
	BigDecimal priceOneDayChange;
	
	LocalDateTime creationDateTime;
	
	LocalDateTime updateDateTime;
}
