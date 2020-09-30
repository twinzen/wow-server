package com.wow.server.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class HoldingDTO {
    private Long holdingId;
    private Long productId;
    private Long userId;
    private BigDecimal quantity;
    private BigDecimal avgPurchasePrice;
    private ProductDTO product;
}
