package com.acmebank.service;

import java.math.BigDecimal;

import com.acmebank.exception.AccountNotExistException;
import com.acmebank.exception.CustomException;
import com.acmebank.model.Account;
import com.acmebank.model.AccountTransferRequest;

public interface AccountService {

	Account fetchBalances(Long accountId);

	void balanceTransfer(AccountTransferRequest acctransferRequest) throws AccountNotExistException, CustomException;

	BigDecimal balanceCheck(Long accountId) throws CustomException;

}
