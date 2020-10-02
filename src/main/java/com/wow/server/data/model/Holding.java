package com.wow.server.data.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "TB_HOLDING")
@Getter
@Setter
public class Holding {

	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	Long holdingId;
	
	Long productId;
	
	Long userId;
	
	BigDecimal quantity;
	
	BigDecimal avgPurchasePrice;
	
	LocalDateTime creationDateTime;
	
	LocalDateTime updateDateTime;

}
