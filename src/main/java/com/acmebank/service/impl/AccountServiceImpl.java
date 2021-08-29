package com.acmebank.service.impl;

import java.math.BigDecimal;
import java.net.SocketTimeoutException;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import com.acmebank.exception.AccountNotExistException;
import com.acmebank.exception.CustomException;
import com.acmebank.model.Account;
import com.acmebank.model.AccountTransferRequest;
import com.acmebank.repository.AccountRepository;
import com.acmebank.service.AccountService;

@Service("accountService")
public class AccountServiceImpl implements AccountService{

	private static final Logger log = LoggerFactory.getLogger(AccountServiceImpl.class);
			
	@Autowired
	private AccountRepository accountsRepository;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${endpoint.accountBalance}")
	private String fetchAccountBalanceUrl;
	
	@Override
	public Account fetchBalances(Long accountNo) {
		Account account = accountsRepository.findByAccountId(accountNo)
		.orElseThrow(() -> new AccountNotExistException("Account with Number:" + accountNo + " does not exist.", HttpStatus.NOT_FOUND));
		return account;
	}
	
	
	@Transactional
	@Override
	public void balanceTransfer(AccountTransferRequest transfer) throws AccountNotExistException, CustomException {
		Account accountFrom = accountsRepository.getAccountForUpdate(transfer.getAccountFromId())
				.orElseThrow(() -> new AccountNotExistException("Account with Number:" + transfer.getAccountFromId() + " does not exist."));
		
		Account accountTo = accountsRepository.getAccountForUpdate(transfer.getAccountToId())
				.orElseThrow(() -> new AccountNotExistException("Account with Number:" + transfer.getAccountFromId() + " does not exist."));
		
		if(accountFrom.getBalance().compareTo(transfer.getAmount()) < 0) {
			throw new CustomException("Account with Number:" + accountFrom.getAccountId()+ " don't have enough balance to transfer.");
		}
		
		accountFrom.setBalance(accountFrom.getBalance().subtract(transfer.getAmount()));
		accountTo.setBalance(accountTo.getBalance().add(transfer.getAmount()));
	}
	
	@Override
	public BigDecimal balanceCheck(Long accountId) throws CustomException {
		
		try {
			String url = fetchAccountBalanceUrl.replace("{id}", accountId.toString());
			
			log.info("checking balance from "+url);
			
			ResponseEntity<Account> balanceCheckResult = restTemplate.getForEntity(url, Account.class);
			
			if(balanceCheckResult.getStatusCode().is2xxSuccessful()) {
				if(balanceCheckResult.hasBody()) {
					return balanceCheckResult.getBody().getBalance();
				}
			}
		} catch (ResourceAccessException ex) {
			final String errorMessage = "Encounter timeout error, please check with system administrator.";
			
			if(ex.getCause() instanceof SocketTimeoutException) {
				throw new CustomException(errorMessage); //TIMEOUT_ERROR
			}
		}
		// for any other fail cases
		throw new CustomException("Encounter internal server error, please check with system administrator.");
	}
}
