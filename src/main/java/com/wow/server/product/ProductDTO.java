package com.wow.server.product;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductDTO {
    private Long productId;
    private String productCode;
    private String productName;
    private BigDecimal marketCap;
    private BigDecimal avgVolume;
    private BigDecimal peRatio;
    private BigDecimal revenue;
    private BigDecimal totalCash;
    private BigDecimal totalDevidendYield;
    private BigDecimal avgDividendYield;
    private String sector;
    private String industry;
    private BigDecimal price;
    private BigDecimal priceOneDayChange;
}
