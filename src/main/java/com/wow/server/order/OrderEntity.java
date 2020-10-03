package com.wow.server.order;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "TB_ORDER")
@Getter
@Setter
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tradeId;
    private Long productId;
    private Long userId;
    private BigDecimal quantity;
    private BigDecimal orderPrice;
    private String orderStatus;
    private LocalDateTime creationDateTime;
    private LocalDateTime updateDateTime;
}
