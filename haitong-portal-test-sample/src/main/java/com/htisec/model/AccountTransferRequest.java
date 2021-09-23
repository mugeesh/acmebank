package com.htisec.model;

import java.math.BigDecimal;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountTransferRequest {

	@NotNull
	private Long accountFromId;

	@NotNull
	private Long accountToId;

	@NotNull
	@Min(value = 0, message = "Transfer amount can not be less than zero")
	private BigDecimal amount;

	@JsonCreator
	public AccountTransferRequest(@NotNull @JsonProperty("accountFromId") Long accountFromId,
			@NotNull @JsonProperty("accountToId") Long accountToId,
			@NotNull @Min(value = 0, message = "Transfer amount can not be less than zero") @JsonProperty("amount") BigDecimal amount) {
		super();
		this.accountFromId = accountFromId;
		this.accountToId = accountToId;
		this.amount = amount;
	}

	@JsonCreator
	public AccountTransferRequest() {
		super();
	}
	
	//Getter & Setter using Lombok

}
