package com.wow.server.data.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TB_FOLLOW")
@Getter
@Setter
public class Follow {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	Long followId;
	Long followingUserId;
	Long followerUserId;
	LocalDateTime creationDateTime;
	LocalDateTime updateDateTime;
}
