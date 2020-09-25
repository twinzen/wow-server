package com.wow.server.datamodel;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TB_FOLLOW")
public class Follow {

	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	Long followId;
	Long followingUserId;
	Long followerUserId;
	LocalDateTime creationDateTime;
	LocalDateTime updateDateTime;
	public Long getFollowId() {
		return followId;
	}
	public void setFollowId(Long followId) {
		this.followId = followId;
	}
	public Long getFollowingUserId() {
		return followingUserId;
	}
	public void setFollowingUserId(Long followingUserId) {
		this.followingUserId = followingUserId;
	}
	public Long getFollowerUserId() {
		return followerUserId;
	}
	public void setFollowerUserId(Long followerUserId) {
		this.followerUserId = followerUserId;
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
