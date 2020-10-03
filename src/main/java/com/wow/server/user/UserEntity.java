package com.wow.server.user;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.*;

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
    public List<UserEntity> following;


    @ManyToMany(mappedBy = "following", fetch = FetchType.LAZY)
    public List<UserEntity> followers;

}
