package com.wow.server.user;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UserDTO extends UserMinimalDTO {
    String userName;
    String email;
    LocalDate firstTradeDate;
    Integer riskProfile;
    String assetSizeRange;
    LocalDate dateOfBirth;
    String educationLevel;
    String haveChild;
    String maritalStatus;
    String monthlyIncomeRange;
    String introduction;
}
