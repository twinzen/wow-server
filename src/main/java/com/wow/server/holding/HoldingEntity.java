package com.wow.server.holding;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "TB_HOLDING")
@Getter
@Setter
public class HoldingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long holdingId;
    private Long productId;
    private Long userId;
    private BigDecimal quantity;
    private BigDecimal avgPurchasePrice;
    private LocalDateTime creationDateTime;
    private LocalDateTime updateDateTime;

}
