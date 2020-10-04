package com.wow.server.holding;

import com.wow.server.product.ProductEntity;
import com.wow.server.user.UserEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "TB_HOLDING")
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class HoldingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long holdingId;
    @EqualsAndHashCode.Include
    @Column(updatable = false, insertable = false)
    private Long productId;
    @EqualsAndHashCode.Include
    @Column(updatable = false, insertable = false)
    private Long userId;
    private BigDecimal quantity;
    private BigDecimal avgPurchasePrice;
    private LocalDateTime creationDateTime;
    private LocalDateTime updateDateTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    private UserEntity user;

    @OneToOne(
            fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.DETACH,
                    CascadeType.MERGE,
                    CascadeType.REFRESH,
                    CascadeType.PERSIST
            }
    )
    @JoinColumn(name = "productId", referencedColumnName = "productId")
    private ProductEntity product;

}
