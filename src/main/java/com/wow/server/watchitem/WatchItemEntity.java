package com.wow.server.watchitem;

import com.wow.server.product.ProductEntity;
import com.wow.server.user.UserEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "TB_WATCH_ITEM")
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class WatchItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long watchItemId;
    @EqualsAndHashCode.Include
    @Column(updatable = false, insertable = false)
    Long productId;
    @EqualsAndHashCode.Include
    @Column(updatable = false, insertable = false)
    Long userId;
    LocalDateTime creationDateTime;
    LocalDateTime updateDateTime;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    private UserEntity user;


}
