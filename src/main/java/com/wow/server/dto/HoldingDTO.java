package com.wow.server.dto;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class HoldingDTO {
    private Long holdingId;
    private Long productId;
    private Long userId;
    private BigDecimal quantity;
    private BigDecimal avgPurchasePrice;
    private ProductDTO product;
}
