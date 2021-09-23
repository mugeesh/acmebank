package com.htisec.dto;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountTransferResult {

	private Long accountFromId;

	private BigDecimal balanceAfterTransfer;

	// Getter Setter using lombok

}
