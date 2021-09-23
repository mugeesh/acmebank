package com.htisec.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

//import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@Entity
@Table(name="ACCOUNT")
public class Account implements Serializable{


	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ACCOUNTID")
	private Long accountId;
	
	@NotNull
	@Column(name = "BALANCE")
	@Min(value = 0, message = "account balance must be positive")
	private BigDecimal balance;

	public Account() {
		super();
	}

	public Account(Long accountId,
			@NotNull @Min(value = 0, message = "account balance must be positive") BigDecimal balance) {
	
		this.accountId = accountId;
		this.balance = balance;
	}

}
