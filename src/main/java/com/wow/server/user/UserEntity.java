package com.wow.server.user;

import com.wow.server.holding.HoldingEntity;
import com.wow.server.watchitem.WatchItemEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "TB_USER")
@Getter
@Setter
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String userName;
    private String email;
    private String password;
    private String displayName;
    private LocalDate firstTradeDate;
    private Integer riskProfile;
    private String assetSizeRange;
    private LocalDate dateOfBirth;
    private String educationLevel;
    private String haveChild;
    private String maritalStatus;
    private String monthlyIncomeRange;
    private String introduction;
    private LocalDateTime creationDateTime;
    private LocalDateTime updateDateTime;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "TB_FOLLOW",
            joinColumns = @JoinColumn(name = "userId"),
            inverseJoinColumns = @JoinColumn(name = "followerId")
    )
    private List<UserEntity> following;

    @ManyToMany(mappedBy = "following", fetch = FetchType.LAZY)
    private List<UserEntity> followers;

    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            orphanRemoval = true
    )
    private List<HoldingEntity> holdings;

    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            orphanRemoval = true
    )
    private List<WatchItemEntity> watchItems;

    public void addHolding(HoldingEntity holding) {
        holdings.add(holding);
        holding.setUser(this);
    }

    public void removeHolding(HoldingEntity holding) {
        holdings.remove(holding);
        holding.setUser(null);
    }

    public void addWatchItem(WatchItemEntity watchItem) {
        watchItems.add(watchItem);
        watchItem.setUser(this);
    }

    public void removeWatchItem(WatchItemEntity watchItem) {
        watchItems.remove(watchItem);
        watchItem.setUser(null);
    }

}
