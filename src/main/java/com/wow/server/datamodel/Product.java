package com.wow.server.datamodel;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TB_PRODUCT")
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

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public BigDecimal getMarketCap() {
		return marketCap;
	}

	public void setMarketCap(BigDecimal marketCap) {
		this.marketCap = marketCap;
	}

	public BigDecimal getAvgVolume() {
		return avgVolume;
	}

	public void setAvgVolume(BigDecimal avgVolume) {
		this.avgVolume = avgVolume;
	}

	public BigDecimal getPeRatio() {
		return peRatio;
	}

	public void setPeRatio(BigDecimal peRatio) {
		this.peRatio = peRatio;
	}

	public BigDecimal getRevenue() {
		return revenue;
	}

	public void setRevenue(BigDecimal revenue) {
		this.revenue = revenue;
	}

	public BigDecimal getTotalCash() {
		return totalCash;
	}

	public void setTotalCash(BigDecimal totalCash) {
		this.totalCash = totalCash;
	}

	public BigDecimal getTotalDevidendYield() {
		return totalDevidendYield;
	}

	public void setTotalDevidendYield(BigDecimal totalDevidendYield) {
		this.totalDevidendYield = totalDevidendYield;
	}

	public BigDecimal getAvgDividendYield() {
		return avgDividendYield;
	}

	public void setAvgDividendYield(BigDecimal avgDividendYield) {
		this.avgDividendYield = avgDividendYield;
	}

	public String getSector() {
		return sector;
	}

	public void setSector(String sector) {
		this.sector = sector;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getPriceOneDayChange() {
		return priceOneDayChange;
	}

	public void setPriceOneDayChange(BigDecimal priceOneDayChange) {
		this.priceOneDayChange = priceOneDayChange;
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
