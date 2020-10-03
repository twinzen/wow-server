package com.wow.server.data.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TB_USER")
@Getter
@Setter
public class User {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	Long userId;
	
	String userName;
	
	String email;
	
	String password;
	
	String displayName;
	
	LocalDate firstTradeDate;
	
	Integer riskProfile;
	
	String assetSizeRange;
	
	LocalDate dateOfBirth;
	
	String educationLevel;
	
	String haveChild;
	
	String maritalStatus;
	
	String monthlyIncomeRange;
	
	String introduction;
	
	LocalDateTime creationDateTime;
	
	LocalDateTime updateDateTime;

}
