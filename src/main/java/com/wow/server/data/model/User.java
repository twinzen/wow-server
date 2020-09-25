package com.wow.server.data.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TB_USER")
public class User {

	@Id @GeneratedValue(strategy = GenerationType.AUTO)
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
	
	LocalDateTime creationDateTime;
	
	LocalDateTime updateDateTime;
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public LocalDate getFirstTradeDate() {
		return firstTradeDate;
	}
	public void setFirstTradeDate(LocalDate firstTradeDate) {
		this.firstTradeDate = firstTradeDate;
	}
	public Integer getRiskProfile() {
		return riskProfile;
	}
	public void setRiskProfile(Integer riskProfile) {
		this.riskProfile = riskProfile;
	}
	public String getAssetSizeRange() {
		return assetSizeRange;
	}
	public void setAssetSizeRange(String assetSizeRange) {
		this.assetSizeRange = assetSizeRange;
	}
	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getEducationLevel() {
		return educationLevel;
	}
	public void setEducationLevel(String educationLevel) {
		this.educationLevel = educationLevel;
	}
	public String getHaveChild() {
		return haveChild;
	}
	public void setHaveChild(String haveChild) {
		this.haveChild = haveChild;
	}
	public String getMaritalStatus() {
		return maritalStatus;
	}
	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}
	public String getMonthlyIncomeRange() {
		return monthlyIncomeRange;
	}
	public void setMonthlyIncomeRange(String monthlyIncomeRange) {
		this.monthlyIncomeRange = monthlyIncomeRange;
	}
	public LocalDateTime getCreationDateTime() {
		return creationDateTime;
	}
	public void setCreationDateTime(LocalDateTime creationDateTime) {
		this.creationDateTime = creationDateTime;
	}
	public LocalDateTime getUpdateDateTime() {
		return updateDateTime;
	}
	public void setUpdateDateTime(LocalDateTime updateDateTime) {
		this.updateDateTime = updateDateTime;
	}
	
	
}
