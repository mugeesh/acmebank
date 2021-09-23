package com.htisec.service;

import java.math.BigDecimal;

import com.htisec.exception.AccountNotExistException;
import com.htisec.exception.CustomException;
import com.htisec.model.Account;
import com.htisec.model.AccountTransferRequest;

public interface AccountService {

	Account fetchBalances(Long accountId);

	void balanceTransfer(AccountTransferRequest acctransferRequest) throws AccountNotExistException, CustomException;

	BigDecimal balanceCheck(Long accountId) throws CustomException;

}
